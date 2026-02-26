package com.example.online_shopping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderDetail")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemSN")
    private Integer id;
    @Column(name = "OrderID")
    private String orderId;
    @Column(name = "ProductID")
    private String productId;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "StandPrice")
    private Integer standPrice;
    @Column(name = "ItemPrice")
    private Integer itemPrice;
}
