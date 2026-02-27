package com.example.online_shopping.repository;

import com.example.online_shopping.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Procedure(procedureName = "sp_get_member_orderdetail")
    List<Object[]> getMemberOrderDetailSp(String memberId);
}
