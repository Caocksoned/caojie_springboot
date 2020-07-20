package com.caojie.tmall.dao;

import com.caojie.tmall.pojo.Order;
import com.caojie.tmall.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem,Integer>{
    //通过订单查询
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
}
