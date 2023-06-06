package com.example.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
   //name of item get from product
    Integer requiredQuantity;
    @ManyToOne
    @JoinColumn
    Cart cart;
    @ManyToOne
    @JoinColumn
    Ordered order;
    @ManyToOne
    @JoinColumn
    Product product;

}
