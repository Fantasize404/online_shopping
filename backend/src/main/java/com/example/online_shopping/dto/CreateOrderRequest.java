package com.example.online_shopping.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private String memberId;
    private List<OrderItemsDto> items;
}
