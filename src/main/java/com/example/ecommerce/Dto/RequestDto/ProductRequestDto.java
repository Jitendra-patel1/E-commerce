package com.example.ecommerce.Dto.RequestDto;

import com.example.ecommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {
    int sellerId;
    String productName;
    Integer price;
    Integer quantity;
    ProductCategory productCategory;
}
