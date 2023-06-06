package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Enum.ProductStatus;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Exception.ProductNotFoundException;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Repository.ItemRepository;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Service.ItemService;
import com.example.ecommerce.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {
        //this api for creating a item

        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        if(itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product out of Stock");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        //item.setCart(customer.getCart());
        item.setProduct(product);
        //we are not setting a order because we are not odering write now

        product.getItemList().add(item);
        return itemRepository.save(item);
    }
}