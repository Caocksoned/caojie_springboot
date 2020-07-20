package com.caojie.tmall.service;

import com.caojie.tmall.dao.OrderDao;
import com.caojie.tmall.pojo.Order;
import com.caojie.tmall.pojo.OrderItem;
import com.caojie.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";
    @Autowired OrderDao orderDao;

    public Page4Navigator<Order> list(int start, int size, int navigatePages){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page pageFromJPA = orderDao.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void update(Order bean){
        orderDao.save(bean);
    }

    public Order get(int oid){
        return orderDao.findOne(oid);
    }

    public void removeOrderFromOrderItem(List<Order> orders) {
        for (Order order : orders) {
            removeOrderFromOrderItem(order);
        }
    }

    private void removeOrderFromOrderItem(Order bean){
        List<OrderItem> orderItems = bean.getOrderItems();
        for(OrderItem oi:orderItems){
            oi.setOrder(null);
        }

        
    }
}
