package com.caojie.tmall.service;

import com.caojie.tmall.dao.OrderItemDao;
import com.caojie.tmall.pojo.Order;
import com.caojie.tmall.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired ProductImageService productImageService;

    public void fill(List<Order> orders){
        for(Order od:orders){
            fill(od);
        }
    }

    public void fill(Order order){
        List<OrderItem> orderItems = listByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for(OrderItem oi:orderItems){
            total += oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
    }

    public List<OrderItem> listByOrder(Order order){
       return orderItemDao.findByOrderOrderByIdDesc(order);
    }
}
