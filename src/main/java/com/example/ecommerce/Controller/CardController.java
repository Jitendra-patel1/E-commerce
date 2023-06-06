package com.example.ecommerce.Controller;

import com.example.ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCart(@RequestBody CardRequestDto cartRequestDto) {
        try {
            CardResponseDto cardResponseDto = cardService.addCard(cartRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("/get_all_same_card")
    public ResponseEntity getCard(@RequestParam CardType cardType){
        List<CardResponseDto> list=cardService.getCard(cardType);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/get_card_with_maximum_number")
    public ResponseEntity getCards(){
        //remaining for sql query
        CardType cardType=cardService.getCards();
        return new ResponseEntity<>(cardType,HttpStatus.OK);
    }
}
