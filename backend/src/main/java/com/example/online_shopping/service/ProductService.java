package com.example.online_shopping.service;

import com.example.online_shopping.entity.Product;
import com.example.online_shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void addProduct(Product product) {
        productRepository.addProductSp(product.getId(), product.getName(), product.getPrice(), product.getQuantity());
    }

    @Transactional
    public List<Product> getAvailableProducts() {
        return productRepository.findByQuantityGreaterThan(0);
    }

}
