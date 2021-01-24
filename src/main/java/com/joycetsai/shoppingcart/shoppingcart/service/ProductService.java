package com.joycetsai.shoppingcart.shoppingcart.service;

import com.joycetsai.shoppingcart.shoppingcart.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();
    public Product findById(int id);
}
