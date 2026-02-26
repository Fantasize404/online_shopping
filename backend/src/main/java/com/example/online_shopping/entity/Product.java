package com.example.online_shopping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    @Column(name = "ProductID", length = 10)
    private String id;
    @Column(name = "ProductName")
    private String name;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "Quantity")
    private Integer quantity;
}
