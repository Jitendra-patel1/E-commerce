package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.ecommerce.Dto.ResponseDto.*;
import com.example.ecommerce.Exception.EmailAlreadyPresentException;
import com.example.ecommerce.Exception.InvalidSellerException;

import java.util.List;

public interface SellerService {
    public SellerResponseDtos addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;

   public  SellerResponseByEmailDto getSellerByEmail(String email) throws InvalidSellerException;

   public  SellerResponseByEmailDto getSellerById(Integer id) throws InvalidSellerException;
   public SellerResponseUpdateByEmail updateByEmail(String email,String mobNo) throws InvalidSellerException;
   public List<SellerResponseDto> getAll();
   public List<SellerResponseByAgeDto> getByAge(Integer age) throws Exception;

    List<SellerResponseByAgeDto> getSellerByAge(Integer startAge, Integer endAge);

    SellerResponseDto  deleteById(Integer id) throws InvalidSellerException;
}
