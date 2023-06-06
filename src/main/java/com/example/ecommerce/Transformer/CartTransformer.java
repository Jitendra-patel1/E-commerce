package com.example.ecommerce.Transformer;

import com.example.ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.ecommerce.Entity.Cart;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartTransformer {



    public static CartResponseDto EntityToDto(Cart cart){
        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())

                .build();
    }
}
