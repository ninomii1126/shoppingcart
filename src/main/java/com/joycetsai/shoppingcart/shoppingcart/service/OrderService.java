package com.joycetsai.shoppingcart.shoppingcart.service;

import com.joycetsai.shoppingcart.shoppingcart.entity.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAll();
    public Order findById(int id);
    public void save(Order order);
    public List<Order> findByUser(String user);

}
