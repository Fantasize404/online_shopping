package com.example.online_shopping.controller;

import com.example.online_shopping.dto.OrderDetailRequest;
import com.example.online_shopping.dto.OrderDetailResponse;
import com.example.online_shopping.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<?> getOrderDetail(@RequestBody OrderDetailRequest request) {
        try {
            List<OrderDetailResponse> responses = orderDetailService.getMemberOrderDetail(request);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
