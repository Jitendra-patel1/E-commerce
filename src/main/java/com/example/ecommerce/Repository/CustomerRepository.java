package com.example.ecommerce.Repository;

import com.example.ecommerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
    Customer findByMobNo(String mobNo);
    Customer findByEmailId(String email);
    List<Customer> findByAge(int age);
    // custom query
    @Query(value = "Select * from Customer c where c.age>=:startAge and c.age<=:endAge", nativeQuery = true)
    List<Customer> findAllAge(int startAge, int endAge);
}
