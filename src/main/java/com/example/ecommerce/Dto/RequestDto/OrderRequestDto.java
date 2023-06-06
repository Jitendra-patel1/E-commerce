package com.example.ecommerce.Dto.RequestDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {

    int customerId;

    int productId;

    int requiredQuantity;

    String cardNo;

    int cvv;
}