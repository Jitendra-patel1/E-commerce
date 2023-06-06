package com.example.ecommerce.Dto.ResponseDto;

import com.example.ecommerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {
    String productName;
    String sellername;
    Integer quantity;
    ProductStatus productStatus;

}
