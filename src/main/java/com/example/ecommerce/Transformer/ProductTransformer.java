package com.example.ecommerce.Transformer;

import com.example.ecommerce.Dto.RequestDto.ProductRequestDto;
import com.example.ecommerce.Dto.ResponseDto.ProductAvailableResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ProductOutOfStockResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ProductResponseBySellerEmailIdDto;
import com.example.ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Enum.ProductStatus;
import com.example.ecommerce.Exception.ProductOutOfStockException;

public class ProductTransformer {
    public static Product DtoToEntity(ProductRequestDto productRequestDto){
        return Product.builder()
                .price(productRequestDto.getPrice())
                .name(productRequestDto.getProductName())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE) //hardcode because seller add product means available.
        .build();
    }

    public static ProductResponseDto EntityToDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .productStatus(product.getProductStatus())
                .sellername(product.getSeller().getName())
                .quantity(product.getQuantity())
                .build();
    }
    public static ProductResponseBySellerEmailIdDto productToDto(Product product){
        return ProductResponseBySellerEmailIdDto.builder()
                .name(product.getName())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
    public static ProductOutOfStockResponseDto productoutOfStockToDto(Product product){
        return ProductOutOfStockResponseDto.builder()
                .name(product.getName())
                .productCategory(product.getProductCategory())
                .build();
    }

    public static ProductAvailableResponseDto productAvailableToDto(Product product){
        return ProductAvailableResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
