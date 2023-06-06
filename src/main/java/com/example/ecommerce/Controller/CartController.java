package com.example.ecommerce.Controller;

import com.example.ecommerce.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Service.CartService;
import com.example.ecommerce.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {

        try {
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(), savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        return cartService.checkOutCart(checkoutCartRequestDto);
    }

}