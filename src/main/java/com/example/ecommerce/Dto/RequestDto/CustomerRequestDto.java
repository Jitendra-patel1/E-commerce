package com.example.ecommerce.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDto {
    String name;
    String emailId;
    Integer age;
    String mobNo;
    String address;
}
