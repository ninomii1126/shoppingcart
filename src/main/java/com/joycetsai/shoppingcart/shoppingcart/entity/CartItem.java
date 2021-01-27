package com.joycetsai.shoppingcart.shoppingcart.entity;


public class CartItem {

    private Product product;
    private double itemPrice;
    private int quantity;
    private String modifyQuantityId;

    public CartItem(){

    }

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.itemPrice = (product.getPrice())*quantity;
        String str1 =String.format("%d",product.getId());
        String str2 = "modifyQuantity";
        this.modifyQuantityId = str2.concat(str1);
    }

    public CartItem(Product product, double itemPrice, int quantity) {
        this.product = product;
        this.itemPrice = product.getPrice()*quantity;
        this.quantity = quantity;
        String str1 =String.format("%d",product.getId());
        String str2 = "modifyQuantity";
        this.modifyQuantityId = str2.concat(str1);
    }

    public String getModifyQuantityId() {
        return modifyQuantityId;
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
        return itemPrice;
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
