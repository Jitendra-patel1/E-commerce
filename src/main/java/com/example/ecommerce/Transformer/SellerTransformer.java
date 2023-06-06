package com.example.ecommerce.Transformer;

import com.example.ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.ecommerce.Dto.ResponseDto.*;
import com.example.ecommerce.Entity.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
           return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();

    }
    public static SellerResponseDtos SellerToSellResponseDto(Seller seller){

        return SellerResponseDtos.builder()
                .message("Congratulations "+seller.getName() +" you are successfully added.....")
                .build();
    }
    public static SellerResponseByEmailDto EntityToDto(Seller seller){
        return SellerResponseByEmailDto.builder()
                .emailId(seller.getEmailId())
                .sellerName(seller.getName())
                .mobNo(seller.getMobNo())
                .build();
    }
    public static SellerResponseUpdateByEmail sellerUpdateToDto(Seller seller){
       return SellerResponseUpdateByEmail.builder()
               .email(seller.getEmailId())
               .name(seller.getName())
               .name(seller.getName())
               .name(seller.getName())
               .message("Updated done... "+ seller.getName()).build();
    }
//    public static SellerResponseDto SellerDeleteToDto(Seller seller){
//        return SellerResponseDto.builder()
//                .message("Hi "+seller.getName()+" your Account Delete Successfully")
//                .build();
//    }
    public static SellerResponseDto SellerAllDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .monNo(seller.getMobNo())
                .build();
    }
    public static SellerResponseByAgeDto SellerAgetoDto(Seller seller){
        return SellerResponseByAgeDto.builder()
                .name(seller.getName())
                .email(seller.getEmailId())
                .build();
    }
}
