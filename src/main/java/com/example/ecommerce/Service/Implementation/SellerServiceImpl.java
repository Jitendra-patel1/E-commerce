package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.ecommerce.Dto.ResponseDto.*;
import com.example.ecommerce.Entity.Seller;
import com.example.ecommerce.Exception.EmailAlreadyPresentException;
import com.example.ecommerce.Exception.InvalidSellerException;
import com.example.ecommerce.Repository.SellerRepository;
import com.example.ecommerce.Service.SellerService;
import com.example.ecommerce.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDtos addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null)
            throw new EmailAlreadyPresentException("Email Id Already Present ");

        Seller seller= SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller=sellerRepository.save(seller);

        SellerResponseDtos sellerResponseDto=SellerTransformer.SellerToSellResponseDto(savedSeller);
       return sellerResponseDto;
    }

    @Override
    public SellerResponseByEmailDto getSellerByEmail(String email) throws InvalidSellerException {
        if(sellerRepository.findByEmailId(email)==null){
            throw new InvalidSellerException("Seller is not present enter valid emailId");
        }
        Seller seller =sellerRepository.findByEmailId(email);
        return SellerTransformer.EntityToDto(seller);
    }

    @Override
    public SellerResponseByEmailDto getSellerById(Integer id) throws InvalidSellerException {
        if(sellerRepository.findById(id)==null){
            throw new InvalidSellerException("Seller is not present enter valid Id");
        }
        Seller seller =sellerRepository.findById(id).get();
        return SellerTransformer.EntityToDto(seller);

    }

    @Override
    public SellerResponseUpdateByEmail updateByEmail(String email,String mobNo) throws InvalidSellerException {
        if(sellerRepository.findByEmailId(email)==null){
            throw new InvalidSellerException("Seller is not present");
        }
        Seller seller=sellerRepository.findByEmailId(email);
        seller.setMobNo(mobNo);
        Seller newSeller=sellerRepository.save(seller);

        return SellerTransformer.sellerUpdateToDto(newSeller);

    }

    @Override
    public List<SellerResponseDto> getAll() {
        List<Seller> seller=sellerRepository.findAll();
        List<SellerResponseDto> list = new ArrayList<>();
        for(Seller sellers:seller){
            SellerResponseDto sellerResponseDto=SellerTransformer.SellerAllDto(sellers);
            list.add(sellerResponseDto);


        }
        return list;
    }

    @Override
    public List<SellerResponseByAgeDto> getByAge(Integer age) throws Exception {
        List<Seller> seller = sellerRepository.findAll();
        List<SellerResponseByAgeDto> list = new ArrayList<>();

        for(Seller sellers:seller){
            if(sellers.getAge()>age){
          SellerResponseByAgeDto  sellerResponseByAgeDto=SellerTransformer.SellerAgetoDto(sellers);
                list.add(sellerResponseByAgeDto);
            }

        }
        if(list.isEmpty())throw new Exception("No seller is Present of this age");
        return list;
    }

    @Override
    public List<SellerResponseByAgeDto> getSellerByAge(Integer startAge, Integer endAge) {
        List<SellerResponseByAgeDto> list = new ArrayList<>();
        List<Seller> seller=sellerRepository.findAllAge(startAge,endAge);
        for(Seller sellers:seller){
            SellerResponseByAgeDto sellerResponseDto=SellerTransformer.SellerAgetoDto(sellers);
            list.add(sellerResponseDto);
        }
        return list;
    }

    @Override
    public SellerResponseDto deleteById(Integer id) throws InvalidSellerException {
        if(sellerRepository.findById(id)==null){
            throw new InvalidSellerException("seller is not present enter valid id!!!!!!");
        }
        Seller seller=sellerRepository.findById(id).get();
        SellerResponseDto sellerResponseDto=SellerTransformer.SellerAllDto(seller);
           sellerRepository.deleteById(id);
          return sellerResponseDto;
    }


}
