package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerDeleteResponseDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerResponseByAgeDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.CustomerNotFoundException;
import com.example.ecommerce.Exception.MobileNumberAlreadyPresentException;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNumberAlreadyPresentException;

    List<CustomerResponseDto> getAll();

    CustomerResponseDto getByMobNo(String mobNo) throws CustomerNotFoundException;

   List<CustomerResponseDto> getByCard(CardType cardType);

    CustomerResponseDto updateByEmail(String email,String mobNo) throws CustomerNotFoundException;

    CustomerResponseDto getByEmail(String email) throws CustomerNotFoundException;

    CustomerResponseDto getById(Integer id) throws CustomerNotFoundException;


    List<CustomerResponseByAgeDto> getByAge(Integer startAge,Integer endAge);

    List<CustomerResponseByAgeDto> getByAge(Integer age);

    CustomerDeleteResponseDto deletById(Integer id) throws CustomerNotFoundException;

    CustomerDeleteResponseDto deletByEmailId(String email) throws CustomerNotFoundException;
}
