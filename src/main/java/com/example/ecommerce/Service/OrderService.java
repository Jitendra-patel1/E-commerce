package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.RequestDto.OrderRequestDto;
import com.example.ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ecommerce.Entity.Card;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Entity.Ordered;
import com.example.ecommerce.Exception.InvalidCardException;
import com.example.ecommerce.Exception.InvalidCustomerException;

public interface OrderService {


    public Ordered placeOrder(Customer customer, Card card) throws Exception;

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
