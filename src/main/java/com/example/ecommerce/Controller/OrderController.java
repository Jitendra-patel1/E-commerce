package com.example.ecommerce.Controller;

import com.example.ecommerce.Dto.RequestDto.OrderRequestDto;
import com.example.ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ecommerce.Repository.OrderedRepository;
import com.example.ecommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    // API to order and item individually
    @Autowired
    OrderService orderService;
    @Autowired
    private OrderedRepository orderedRepository;

    @PostMapping("/place")
    public OrderResponseDto placeDirectOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {

        return orderService.placeOrder(orderRequestDto);
    }
}