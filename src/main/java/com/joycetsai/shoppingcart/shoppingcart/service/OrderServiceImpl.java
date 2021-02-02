package com.joycetsai.shoppingcart.shoppingcart.service;

import com.joycetsai.shoppingcart.shoppingcart.dao.OrderRepository;
import com.joycetsai.shoppingcart.shoppingcart.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int id) {
        Optional<Order> result = orderRepository.findById(id);
        Order order=null;

        if(result.isPresent()){
            order=result.get();
        }else {
            throw new RuntimeException("Can't find order ID: " + id);
        }
        return order;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findByUser(String user) {

        return orderRepository.findByUser(user);
    }
}
