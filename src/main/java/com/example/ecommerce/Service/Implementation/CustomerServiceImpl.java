package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerDeleteResponseDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerResponseByAgeDto;
import com.example.ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Entity.Cart;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.CustomerNotFoundException;
import com.example.ecommerce.Exception.MobileNumberAlreadyPresentException;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Service.CustomerService;
import com.example.ecommerce.Transformer.CustomerTransformer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNumberAlreadyPresentException {
        if(customerRepository.findByMobNo(customerRequestDto.getMobNo())!=null){
            throw new MobileNumberAlreadyPresentException("Sorry! Customer already exists!");
        }

        Customer customer= CustomerTransformer.DtoToEntity(customerRequestDto);
        //customer get cart at beginning
        Cart cart= Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();
        customer.setCart(cart);
        Customer saveCustomer=customerRepository.save(customer); //save cuatomer and card

        return CustomerTransformer.EntityToDto(saveCustomer);
    }

    @Override
    public List<CustomerResponseDto> getAll() {
        List<CustomerResponseDto> list =new ArrayList<>();
        List<Customer> lists=customerRepository.findAll();
        for(Customer customer:lists){
            CustomerResponseDto customerResponseDto=CustomerTransformer.getAllToDto(customer);
            list.add(customerResponseDto);
        }
        return list;
    }

    @Override
    public CustomerResponseDto getByMobNo(String mobNo) throws CustomerNotFoundException {
        if(customerRepository.findByMobNo(mobNo)==null){
            throw new CustomerNotFoundException("Give mobile number is not present!!!!");
        }
        Customer customer=customerRepository.findByMobNo(mobNo);
        return CustomerTransformer.getAllToDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getByCard(CardType cardType) {
        List<CustomerResponseDto> list = new ArrayList<>();
        List<Customer> lists=customerRepository.findAll();
        for(Customer customer:lists){
            if(customer.getCardList().contains(CardType.VISA)){
                CustomerResponseDto customerResponseDto=CustomerTransformer.getAllToDto(customer);
                list.add(customerResponseDto);

            }
        }
        return list;
    }

    @Override
    public CustomerResponseDto updateByEmail(String email,String mobNo) throws CustomerNotFoundException {
        if(customerRepository.findByEmailId(email)==null){
            throw  new CustomerNotFoundException("customer is not found enter valid emailId");
        }
        Customer customer=customerRepository.findByEmailId(email);
        customer.setMobNo(mobNo);
        Customer saveCustomer=customerRepository.save(customer);
        return CustomerTransformer.updateEmailToDto(saveCustomer);
    }

    @Override
    public CustomerResponseDto getByEmail(String email) throws CustomerNotFoundException {
        if(customerRepository.findByEmailId(email)==null){
            throw new CustomerNotFoundException("Give email number is not present!!!!");
        }
        Customer customer=customerRepository.findByEmailId(email);
        return CustomerTransformer.getAllToDto(customer);
    }

    @Override
    public CustomerResponseDto getById(Integer id) throws CustomerNotFoundException {
        if(customerRepository.findById(id)==null){
            throw new CustomerNotFoundException("Give mobile number is not present!!!!");
        }
        Customer customer=customerRepository.findById(id).get();
        return CustomerTransformer.getAllToDto(customer);
    }

    @Override
    public List<CustomerResponseByAgeDto> getByAge(Integer startAge, Integer endAge) {
        List<CustomerResponseByAgeDto> list =new ArrayList<>();
        List<Customer> customers=customerRepository.findAllAge(startAge,endAge);
        for(Customer customer:customers){
            CustomerResponseByAgeDto customerResponseByAgeDto=CustomerTransformer.getByAgeToDto(customer);
            list.add(customerResponseByAgeDto);
        }
        return list;
    }

    @Override
    public List<CustomerResponseByAgeDto> getByAge(Integer age) {
        List<CustomerResponseByAgeDto> list =new ArrayList<>();
        List<Customer> customers=customerRepository.findByAge(age);
        for(Customer customer:customers){
            CustomerResponseByAgeDto customerResponseByAgeDto=CustomerTransformer.getByAgeToDto(customer);
            list.add(customerResponseByAgeDto);
        }
        return list;
    }

    @Override
    public CustomerDeleteResponseDto deletById(Integer id) throws CustomerNotFoundException {
        if(customerRepository.findById(id)==null){
            throw  new CustomerNotFoundException("counstomer not present for this id");

        }
        Customer customer=customerRepository.findById(id).get();
        CustomerDeleteResponseDto customerDeleteResponseDto=CustomerTransformer.getDto(customer);
        customerRepository.deleteById(id);
        return customerDeleteResponseDto;
    }

    @Override
    public CustomerDeleteResponseDto deletByEmailId(String email) throws CustomerNotFoundException {
        if(customerRepository.findByEmailId(email)==null){
            throw  new CustomerNotFoundException("counstomer not present for this id");

        }
        Customer customer=customerRepository.findByEmailId(email);
        CustomerDeleteResponseDto customerDeleteResponseDto=CustomerTransformer.getDto(customer);
        customerRepository.findByEmailId(email);
        return customerDeleteResponseDto;
    }


}

