package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ecommerce.Entity.Card;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Exception.MobileNumberAlreadyPresentException;
import com.example.ecommerce.Repository.CardRepository;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Service.CardService;
import com.example.ecommerce.Transformer.CardTransformer;
import com.example.ecommerce.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
   @Autowired
    CardRepository cardRepository;
   @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cartRequestDto) throws InvalidCustomerException {
        Customer customer=customerRepository.findByMobNo(cartRequestDto.getCustomerMobNo());
        if(customer==null){
            throw  new InvalidCustomerException("Sorry!! The customer don't exists");
        }
        Card card= CardTransformer.DtoToEntity(cartRequestDto);
        card.setCustomer(customer);
        customer.getCardList().add(card);
        customerRepository.save(customer);

        return CardTransformer.EntityToDto(card);
    }

    @Override
    public List<CardResponseDto> getCard(CardType cardType) {
        List<Card> list=cardRepository.findAll();
        List<CardResponseDto> lists = new ArrayList<>();
        for(Card card: list){
            if(card.getCardType()==cardType){
                CardResponseDto cardResponseDto=CardTransformer.EntityToDto(card);
                lists.add(cardResponseDto);
            }
        }
        return lists;
    }

    @Override
    public CardType getCards() {

        List<Card> list=cardRepository.findAll();
        return list.get(0).getCardType();
    }

}
