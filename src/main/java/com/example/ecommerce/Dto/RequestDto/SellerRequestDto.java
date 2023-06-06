package com.example.ecommerce.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRequestDto {
    String name;
    String emailId;
    Integer age;
    String mobNo;
}
