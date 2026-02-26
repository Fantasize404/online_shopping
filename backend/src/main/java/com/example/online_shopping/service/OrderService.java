package com.example.online_shopping.service;

import com.example.online_shopping.dto.CreateOrderRequest;
import com.example.online_shopping.dto.OrderItemsDto;
import com.example.online_shopping.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void createOrder(CreateOrderRequest request) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String orderId = "Ms" + timestamp;

            int totalPrice = 0;
            List<Map<String, Object>> itemsList = new ArrayList<>();

            for (OrderItemsDto item : request.getItems()) {
                int itemPrice = item.getStandPrice() * item.getQuantity();
                totalPrice += itemPrice;

                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("productId", item.getProductId());
                itemMap.put("quantity", item.getQuantity());
                itemMap.put("standPrice", item.getStandPrice());
                itemMap.put("itemPrice", itemPrice);
                itemsList.add(itemMap);
            }

            String itemsJson = objectMapper.writeValueAsString(itemsList);

            orderRepository.createOrderFullSp(orderId, request.getMemberId(), totalPrice, 1, itemsJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
