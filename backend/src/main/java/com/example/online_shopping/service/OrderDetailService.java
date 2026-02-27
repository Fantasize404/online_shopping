package com.example.online_shopping.service;

import com.example.online_shopping.dto.OrderDetailRequest;
import com.example.online_shopping.dto.OrderDetailResponse;
import com.example.online_shopping.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    public List<OrderDetailResponse> getMemberOrderDetail(OrderDetailRequest request) {
        List<Object[]> items = orderDetailRepository.getMemberOrderDetailSp(request.getMemberId());
        List<OrderDetailResponse> responses = new ArrayList<>();
        for (Object[] item : items) {
            OrderDetailResponse response = new OrderDetailResponse();
            response.setOrderId((String) item[0]);
            response.setProductId((String) item[1]);
            response.setProductName((String) item[2]);
            response.setQuantity(((Number) item[3]).intValue());
            response.setStandPrice(((Number) item[4]).intValue());
            response.setItemPrice(((Number) item[5]).intValue());
            responses.add(response);
        }
        return responses;
    }

}
