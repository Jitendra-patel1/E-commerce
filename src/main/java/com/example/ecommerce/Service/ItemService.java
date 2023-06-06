package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Exception.ProductNotFoundException;
import com.example.ecommerce.Exception.ProductOutOfStockException;

public interface ItemService {

    public Item addItem(ItemRequestDto itemsRequestDto) throws Exception;
}
