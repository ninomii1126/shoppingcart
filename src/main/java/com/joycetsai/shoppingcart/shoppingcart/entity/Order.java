package com.joycetsai.shoppingcart.shoppingcart.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account")
    private String username;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "order_status")
    private String status;

    @OneToMany(mappedBy = "order",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<OrderItem> orderItems;

    public Order() {

    }

    public Order(String username, String orderDate, String status) {
        this.username = username;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    //add order item
    public void addItem(OrderItem item){

        if(orderItems == null){
            orderItems=new ArrayList<OrderItem>();
        }
        this.orderItems.add(item);

        item.setOrder(this);
    }
}
