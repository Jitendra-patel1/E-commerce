package com.example.ecommerce.Transformer;

import com.example.ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.ecommerce.Entity.Item;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }
}