package com.example.online_shopping.repository;

import com.example.online_shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByQuantityGreaterThan(Integer quantity);

    @Procedure(procedureName = "sp_add_product")
    void addProductSp(String p_product_id, String p_product_name, Integer p_price, Integer p_quantity);
}
