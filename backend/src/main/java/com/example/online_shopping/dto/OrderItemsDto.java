package com.example.online_shopping.dto;

import lombok.Data;

@Data
public class OrderItemsDto {
    private String productId;
    private Integer quantity;
    private Integer standPrice;
}
