package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;

import java.util.List;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cartRequestDto) throws InvalidCustomerException;

    List<CardResponseDto> getCard(CardType cardType);

    CardType getCards();
}
