package com.example.ecommerce.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAvailableResponseDto {
    String name;
    Integer price;
    Integer quantity;
}
