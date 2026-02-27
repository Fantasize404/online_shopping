package com.example.online_shopping.repository;

import com.example.online_shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Procedure(procedureName = "sp_create_order_full")
    void createOrderFullSp(
            String p_order_id,
            String p_member_id,
            Integer p_total_price,
            Integer p_pay_status,
            String p_items_json
    );
}
