package com.example.ecommerce.Repository;

import com.example.ecommerce.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    Card findByCardnumber(String cardnumber);
}
