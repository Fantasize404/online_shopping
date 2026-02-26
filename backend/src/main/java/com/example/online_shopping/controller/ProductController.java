package com.example.online_shopping.controller;

import com.example.online_shopping.entity.Product;
import com.example.online_shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            String safeProductName = HtmlUtils.htmlEscape(product.getName());
            product.setName(safeProductName);
            productService.addProduct(product);
            return ResponseEntity.ok("新增商品成功");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("新增商品失敗" + e.getMessage());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Product>> getAvailableProducts() {
        return ResponseEntity.ok(productService.getAvailableProducts());
    }
}
