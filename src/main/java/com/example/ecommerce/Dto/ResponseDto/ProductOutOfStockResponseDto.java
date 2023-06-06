package com.example.ecommerce.Dto.ResponseDto;

import com.example.ecommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOutOfStockResponseDto {
    String name;
    ProductCategory productCategory;

}
