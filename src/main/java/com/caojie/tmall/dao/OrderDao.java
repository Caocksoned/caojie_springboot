package com.caojie.tmall.dao;

import com.caojie.tmall.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer>{
}
