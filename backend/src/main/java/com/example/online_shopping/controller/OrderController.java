package com.example.online_shopping.controller;

import com.example.online_shopping.dto.CreateOrderRequest;
import com.example.online_shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            orderService.createOrder(request);
            return ResponseEntity.ok("新增訂單成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("訂單建立失敗：" + e.getMessage());
        }
    }
}
