package com.example.ecommerce.Dto.ResponseDto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CustomerDeleteResponseDto {
    String name;
    String emailId;
    Integer age;

    String mobNo;
}
