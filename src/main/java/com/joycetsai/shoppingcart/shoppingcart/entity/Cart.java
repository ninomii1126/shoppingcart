package com.joycetsai.shoppingcart.shoppingcart.entity;

import java.util.HashMap;
import java.util.Map;


public class Cart {

    private HashMap<Integer,CartItem> items ;
    private double totalPrice;

    public void addItem(Product product){
        //check if the cart is empty
        if(items==null){
            items=new HashMap<Integer, CartItem>();
        }

        //check if the cart already contain the product
        int productId = product.getId();

        if(items.containsKey(productId)){
            items.get(productId).addAmount(1);
        }else{
            CartItem cartItem = new CartItem(product,1);
            items.put(productId,cartItem);
        }
    }

    //get total price of the shopping cart
    public double getTotalPrice(){
        double totalPrice =0;

        for(CartItem cartItem:items.values()){
            totalPrice+=cartItem.getItemPrice();
        }

        return totalPrice;
    }

    public void clearCart(){
        items.clear();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void deleteItem(Product product){
        int id = product.getId();

        items.remove(id);
    }

    public void modifyQuantity(Product product, int newQuantity){

        CartItem cartItem = items.get(product.getId());
        cartItem.setQuantity(newQuantity);
        cartItem.setItemPrice((product.getPrice())*newQuantity);

        items.replace(product.getId(), cartItem);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
