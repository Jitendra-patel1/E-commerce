package com.example.ecommerce.Dto.RequestDto;

import com.example.ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    String customerMobNo;
    String cardnumber;
    Integer cvv;
    Date expiryDate;
    CardType cardType;
}
