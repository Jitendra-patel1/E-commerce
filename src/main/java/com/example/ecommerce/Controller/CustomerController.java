package com.example.ecommerce.Controller;

import com.example.ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerDeleteResponseDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerResponseByAgeDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.CustomerNotFoundException;
import com.example.ecommerce.Exception.MobileNumberAlreadyPresentException;
import com.example.ecommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerrequestDto) throws MobileNumberAlreadyPresentException {
        return customerService.addCustomer(customerrequestDto);
    }

    @GetMapping("/getall")
    public ResponseEntity getAll() {
        List<CustomerResponseDto> list = customerService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);

    }

    @GetMapping("/getbymobno/{mobNo}")
    public ResponseEntity getByMobNo(@PathVariable("mobNo") String mobNo) {
        try {
            CustomerResponseDto customerResponseDto = customerService.getByMobNo(mobNo);
            return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getbyemail")
    public ResponseEntity getByEmail(@RequestParam String email){
        try {
            CustomerResponseDto customerResponseDto = customerService.getByEmail(email);
            return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/usingvisa")
    public ResponseEntity getByCard(@RequestParam CardType cardType) {

        List<CustomerResponseDto> customerResponseDto = customerService.getByCard(cardType);
        return new ResponseEntity(customerResponseDto, HttpStatus.OK);
    }

    @PutMapping("/updatebyemail")
    public ResponseEntity updateByEmail(@RequestParam String email, @RequestParam String mobNo) {
        try {
            CustomerResponseDto customerResponseDto = customerService.updateByEmail(email, mobNo);
            return new ResponseEntity(customerResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getbyid")
    public ResponseEntity getById(@RequestParam Integer id){
        try {
            CustomerResponseDto customerResponseDto = customerService.getById(id);
            return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getbyagebetween")
    public ResponseEntity getByAge(@RequestParam Integer startAge,@RequestParam Integer endAge){
        List<CustomerResponseByAgeDto> customerResponseByAgeDto=customerService.getByAge(startAge,endAge);
        return new ResponseEntity<>(customerResponseByAgeDto,HttpStatus.OK);
    }
    @GetMapping("/getByage/{age}")
    public ResponseEntity getByAge(@PathVariable("age") Integer age){
        List<CustomerResponseByAgeDto> customerResponseByAgeDto=customerService.getByAge(age);
        return new ResponseEntity<>(customerResponseByAgeDto,HttpStatus.OK);
    }

    @DeleteMapping("/deletebyid")
    public ResponseEntity deleteById(@PathVariable Integer id) throws CustomerNotFoundException {
        try {
            CustomerDeleteResponseDto customerDeleteResponseDto = customerService.deletById(id);
            return new ResponseEntity<>(customerDeleteResponseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deletebyemail")
    public ResponseEntity deleteByEmailId(@PathVariable String email){
        try {
            CustomerDeleteResponseDto customerDeleteResponseDto = customerService.deletByEmailId(email);
            return new ResponseEntity<>(customerDeleteResponseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




}
