package com.example.ecommerce.Transformer;

import com.example.ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ecommerce.Entity.Card;

public class CardTransformer {

    public static Card DtoToEntity(CardRequestDto cardRequestDto){
        return Card.builder()
                .cvv(cardRequestDto.getCvv())
                .cardnumber(cardRequestDto.getCardnumber())
                .cardType(cardRequestDto.getCardType())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();

    }
    public static CardResponseDto EntityToDto(Card card){
        return CardResponseDto.builder()
                .custorName(card.getCustomer().getName())
                .cardNo(card.getCardnumber())
                .build();
    }
}
