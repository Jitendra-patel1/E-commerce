package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.RequestDto.ProductRequestDto;
import com.example.ecommerce.Dto.ResponseDto.ProductAvailableResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ProductOutOfStockResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ProductResponseBySellerEmailIdDto;
import com.example.ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Entity.Ordered;
import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.Exception.InvalidSellerException;
import com.example.ecommerce.Exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);

    List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String productCategory);
    List<ProductResponseBySellerEmailIdDto> getProductBySellerEmailId(String email) throws InvalidSellerException;
    List<ProductOutOfStockResponseDto>  getOutOfStock();
    List<ProductAvailableResponseDto> getAvailable();

    List<ProductAvailableResponseDto> getByQuantity(Integer quantity);

    List<ProductAvailableResponseDto> cheapestProduct();

    void decreaseProductQuantity(Item item) throws Exception;

    List<ProductAvailableResponseDto> costliestProduct();

    ProductAvailableResponseDto getProduct(Integer productId, Integer sellerId) throws ProductNotFoundException, InvalidSellerException;
}
