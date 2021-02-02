package com.joycetsai.shoppingcart.shoppingcart.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name = "product_id")
    private int product;

    @Column(name = "quantity")
    private  int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private double amount;

    @Column(name = "product_name")
    private String productName;

    public OrderItem() {

    }

    public OrderItem(int product, int quantity, double price, double amount) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
