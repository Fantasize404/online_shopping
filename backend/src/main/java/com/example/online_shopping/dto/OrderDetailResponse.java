package com.example.online_shopping.dto;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private String orderId;
    private String productId;
    private String productName;
    private Integer quantity;
    private Integer standPrice;
    private Integer itemPrice;
}
