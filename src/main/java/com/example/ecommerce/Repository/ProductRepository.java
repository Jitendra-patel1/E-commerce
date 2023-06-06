package com.example.ecommerce.Repository;

import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Enum.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

   List<Product> findByProductCategory(ProductCategory productCategory);
   //top  5 cheapeast product
    List<Product> findTop5ByOrderByPriceAsc();
    //top 5 costilest procust
    List<Product> findTop5ByOrderByPriceDesc();
   @Query(value = "select * from product p where p.price > :price and p.product_category=:category ",nativeQuery = true)
    List<Product> getAllProductsByPriceAndCategory(int price,String category);

  // Optional<Product> findByProductIDAndSellerId(int productId,int sellerId);
}
