package com.example.ecommerce.Controller;

import com.example.ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.ecommerce.Dto.ResponseDto.*;
import com.example.ecommerce.Service.SellerService;
import com.sun.mail.iap.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) {
        try {
            SellerResponseDtos sellerResponseDtos = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDtos, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_by_email")
    public ResponseEntity getSellerByEmail(@RequestParam("email") String email) {

        try {
            SellerResponseByEmailDto sellerResponseByEmailDto = sellerService.getSellerByEmail(email);
            return new ResponseEntity(sellerResponseByEmailDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_by_id")
    public ResponseEntity getSellerById(@RequestParam("id") Integer id) {

        try {
            SellerResponseByEmailDto sellerResponseByEmailDto = sellerService.getSellerById(id);
            return new ResponseEntity(sellerResponseByEmailDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updatebyemail")
    public ResponseEntity updateByEmail(@RequestParam("email") String email,@RequestParam("mobNo") String mobNo){
        try{
            SellerResponseUpdateByEmail sellerResponseUpdateByEmail=sellerService.updateByEmail(email, mobNo);
            return new ResponseEntity(sellerResponseUpdateByEmail,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allseller")
    public ResponseEntity getAll(){
        List<SellerResponseDto> list = sellerService.getAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @GetMapping("/getsellerbyage")
    public ResponseEntity getByAge(@RequestParam("age") Integer age){
        try{
             List<SellerResponseByAgeDto> list=sellerService.getByAge(age);
             return new ResponseEntity(list,HttpStatus.OK);
           }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getsellerbyagerange")
    public ResponseEntity getSellerByAge(@RequestParam Integer startAge,@RequestParam Integer endAge){

        List<SellerResponseByAgeDto> list = sellerService.getSellerByAge(startAge,endAge);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @DeleteMapping("/deletebyid")
    public ResponseEntity deletById(@RequestParam Integer id){
        try {
            SellerResponseDto sellerResponseDto = sellerService.deleteById(id);
            return new ResponseEntity(sellerResponseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

        }

}


    }
