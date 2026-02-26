package com.example.online_shopping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Order")
@Data
public class Order {
    @Id
    @Column(name = "OrderID", length = 20)
    private String id;
    @Column(name = "MemberID")
    private String member;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "PayStatus")
    private Byte payStatus;
}
