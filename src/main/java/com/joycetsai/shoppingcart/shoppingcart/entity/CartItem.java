package com.joycetsai.shoppingcart.shoppingcart.entity;


public class CartItem {

    private Product product;
    private double itemPrice;
    private int quantity;

    public CartItem(){

    }

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.itemPrice = (product.getPrice())*quantity;
    }

    public CartItem(Product product, double itemPrice, int quantity) {
        this.product = product;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductId(){
        return product.getId();
    }

    public void addAmount(int amount){
        this.quantity+=amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getItemPrice() {
        return itemPrice*quantity;
    }

    public void setItemPrice(double thePrice) {
        this.itemPrice=thePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
