package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Exception.InvalidCardException;
import com.example.ecommerce.Exception.InvalidCustomerException;

public interface CartService {

   public CartResponseDto saveCart(Integer customerId, Item savaedItem);

   public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception;


}
