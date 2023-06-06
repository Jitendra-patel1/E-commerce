package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.Dto.RequestDto.ProductRequestDto;
import com.example.ecommerce.Dto.ResponseDto.ProductAvailableResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ProductOutOfStockResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ProductResponseBySellerEmailIdDto;
import com.example.ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Entity.Ordered;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Entity.Seller;
import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.Enum.ProductStatus;
import com.example.ecommerce.Exception.InvalidSellerException;
import com.example.ecommerce.Exception.ProductNotFoundException;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Repository.SellerRepository;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller=sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch(Exception e){
            throw new InvalidSellerException("Seller doesn't exists");
        }
        Product product= ProductTransformer.DtoToEntity(productRequestDto);
        product.setSeller(seller); //check any change in indeed in seller entity

        //add product to current products of seller
        seller.getProductList().add(product);
        sellerRepository.save(seller); //save both product and seller

        //return dto
        return  ProductTransformer.EntityToDto(product);

    }


    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category){

        List<Product> products = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.EntityToDto(product));
        }

        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String productCategory) {
        List<Product> products = productRepository.getAllProductsByPriceAndCategory(price,productCategory);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.EntityToDto(product));
        }

        return productResponseDtos;
    }

    @Override
    public List<ProductResponseBySellerEmailIdDto> getProductBySellerEmailId(String email) throws InvalidSellerException {
        if (sellerRepository.findByEmailId(email)==null){
            throw new InvalidSellerException("seller not found");
        }
        Seller seller=sellerRepository.findByEmailId(email);
        List<ProductResponseBySellerEmailIdDto> productResponseBySellerEmailIdDtos =new ArrayList<>();
        List<Product> list=seller.getProductList();
        for(Product product:list){
            productResponseBySellerEmailIdDtos.add(ProductTransformer.productToDto(product));
        }
        return productResponseBySellerEmailIdDtos;
    }

    @Override
    public List<ProductOutOfStockResponseDto> getOutOfStock() {
        List<ProductOutOfStockResponseDto> lists=new ArrayList<>();
        List<Product> list=productRepository.findAll();
        for(Product products:list){
            if(products.getProductStatus()== ProductStatus.OUT_OF_STOCK){
                ProductOutOfStockResponseDto
                        productOutOfStockResponseDtos=ProductTransformer.productoutOfStockToDto(products);
                lists.add(productOutOfStockResponseDtos);
            }

        }
        return lists;
    }

    @Override
    public List<ProductAvailableResponseDto> getAvailable() {
        List<ProductAvailableResponseDto> list =new ArrayList<>();
        List<Product> lists=productRepository.findAll();
        for(Product product:lists){
            if(product.getProductStatus()==ProductStatus.AVAILABLE){
                ProductAvailableResponseDto
                        productAvailableResponseDto=ProductTransformer.productAvailableToDto(product);
                 list.add(productAvailableResponseDto);
            }
        }
        return list;
    }

    @Override
    public List<ProductAvailableResponseDto> getByQuantity(Integer quantity) {
        List<ProductAvailableResponseDto> list =new ArrayList<>();
        List<Product> lists=productRepository.findAll();
        for(Product product:lists){
            if(product.getQuantity()>quantity){
                ProductAvailableResponseDto
                        productAvailableResponseDto=ProductTransformer.productAvailableToDto(product);
                list.add(productAvailableResponseDto);
            }
        }
        return list;
    }

    @Override
    public List<ProductAvailableResponseDto> cheapestProduct() {
        List<ProductAvailableResponseDto> list =new ArrayList<>();
        List<Product> lists=productRepository.findTop5ByOrderByPriceAsc();
        for(Product product:lists){
            ProductAvailableResponseDto
                    productAvailableResponseDto=ProductTransformer.productAvailableToDto(product);
            list.add(productAvailableResponseDto);
        }
        return list;
    }
    @Override
    public List<ProductAvailableResponseDto> costliestProduct() {
        List<ProductAvailableResponseDto> list =new ArrayList<>();
        List<Product> lists=productRepository.findTop5ByOrderByPriceDesc();
        for(Product product:lists){
            ProductAvailableResponseDto
                    productAvailableResponseDto=ProductTransformer.productAvailableToDto(product);
            list.add(productAvailableResponseDto);
        }
        return list;
    }

    @Override
    public ProductAvailableResponseDto getProduct(Integer productId, Integer sellerId) throws ProductNotFoundException, InvalidSellerException {
          if(productRepository.findById(productId).get()==null){
            throw new ProductNotFoundException("product is not present for given  productId");
        }
          if(productRepository.findById(productId).get().getSeller().getId()!=sellerId){
              throw new InvalidSellerException("product is not present for given sellerId");

          }
          Product product=productRepository.findById(productId).get();

            return ProductTransformer.productAvailableToDto(product);

    }


    @Override
    public void decreaseProductQuantity(Item item) throws Exception {

        Product product = item.getProduct();
        int quantity = item.getRequiredQuantity();
        int currentQuantity = product.getQuantity();
        if(quantity>currentQuantity){
            throw new Exception("Out of stock");
        }
        product.setQuantity(currentQuantity-quantity);
        if(product.getQuantity()==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }



}



