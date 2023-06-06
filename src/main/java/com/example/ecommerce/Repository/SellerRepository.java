package com.example.ecommerce.Repository;

import com.example.ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.ecommerce.Entity.Seller;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmailId(String email);

    // Custom Query
    @Query(value = "Select * from Seller s where s.age>=:startAge and s.age<=:endAge", nativeQuery = true)
    List<Seller> findAllAge(int startAge, int endAge);
}
