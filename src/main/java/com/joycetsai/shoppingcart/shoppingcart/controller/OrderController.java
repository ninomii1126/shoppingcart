package com.joycetsai.shoppingcart.shoppingcart.controller;

import com.joycetsai.shoppingcart.shoppingcart.entity.Order;
import com.joycetsai.shoppingcart.shoppingcart.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;


    public OrderController(OrderService theOrderService) {
        orderService = theOrderService;
    }

    @PostMapping("/list")
    public String listOrders(@RequestParam("username") String username,
                             Model theModel){

        List<Order> orders = new ArrayList<>();

        orders = orderService.findByUser(username);

        theModel.addAttribute("orders",orders);

        return "my-orders";
    }


}
