package com.example.ecommerce.Transformer;

import com.example.ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerDeleteResponseDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerResponseByAgeDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Entity.Cart;
import com.example.ecommerce.Entity.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {

    public static Customer DtoToEntity(CustomerRequestDto customerRequestDto){

       return Customer.builder()
                .age(customerRequestDto.getAge())
                .address(customerRequestDto.getAddress())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo())
                .name(customerRequestDto.getName())
                .build();
    }

    public static CustomerResponseDto EntityToDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome to Amazon !!!")
                .build();
    }
    public static CustomerResponseDto getAllToDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message(customer.getEmailId())
                .build();
    }
    public static CustomerResponseDto updateEmailToDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message(customer.getMobNo())
                .build();
    }

   public  static CustomerResponseByAgeDto getByAgeToDto(Customer customer){
        return CustomerResponseByAgeDto.builder()
                .emailId(customer.getEmailId())
                .monNo(customer.getMobNo())
                .name(customer.getName())
                .build();
   }
   public static CustomerDeleteResponseDto getDto(Customer customer){
        return CustomerDeleteResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
   }


}
