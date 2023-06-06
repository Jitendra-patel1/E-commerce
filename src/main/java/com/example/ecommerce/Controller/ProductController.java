package com.example.ecommerce.Controller;

import com.example.ecommerce.Dto.RequestDto.ProductRequestDto;
import com.example.ecommerce.Dto.ResponseDto.ProductAvailableResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ProductOutOfStockResponseDto;
import com.example.ecommerce.Dto.ResponseDto.ProductResponseBySellerEmailIdDto;
import com.example.ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addproduct(@RequestBody ProductRequestDto productResquestDto) {
        try {
            ProductResponseDto productResponseDto = productService.addProduct(productResquestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get all products of a particular category
    @GetMapping("/get/{category}")
    public ResponseEntity getAllProductsByCategory(@PathVariable("category") ProductCategory category) {
        List<ProductResponseDto> list = productService.getAllProductsByCategory(category);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //using custom sql query
    @GetMapping("/get/{price}/{category}")
    public ResponseEntity getAllProductsByPriceAndCategory(
            @PathVariable("price") int price,
            @PathVariable("category") String productCategory) {

        List<ProductResponseDto> list = productService.getAllProductsByPriceAndCategory(price, productCategory);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getproductbyemail")
    public ResponseEntity getProductBySellerEmailId(@RequestParam("email") String email) {
        try {
            List<ProductResponseBySellerEmailIdDto> productResponseBySellerEmailIdDtos = productService.getProductBySellerEmailId(email);
            return new ResponseEntity(productResponseBySellerEmailIdDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/outofstock")
    public ResponseEntity getOutOfStock() {
        List<ProductOutOfStockResponseDto> list = productService.getOutOfStock();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity getAvailable() {
        List<ProductAvailableResponseDto> list = productService.getAvailable();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/getbyquantity")
    public ResponseEntity getByQuantity(@RequestParam("quantity") Integer quantity) {
        List<ProductAvailableResponseDto> list = productService.getByQuantity(quantity);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/cheapestproduct")
    public ResponseEntity cheapestProduct() {
        List<ProductAvailableResponseDto> list = productService.cheapestProduct();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/costliestproduct")
    public ResponseEntity costliestProduct() {
        List<ProductAvailableResponseDto> list = productService.costliestProduct();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/getproduct")
    public ResponseEntity getProduct(@RequestParam Integer productId, @RequestParam Integer sellerId) {
        try {
            ProductAvailableResponseDto productAvailableResponseDto = productService.getProduct(productId, sellerId);
            return new ResponseEntity(productAvailableResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
