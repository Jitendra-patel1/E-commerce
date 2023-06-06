package com.example.ecommerce.Dto.ResponseDto;

import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.Enum.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseBySellerEmailIdDto {
    String name;
    Integer price;
    Integer quantity;

    ProductCategory productCategory;

    ProductStatus productStatus;
}
