package com.joycetsai.shoppingcart.shoppingcart.service;

import com.joycetsai.shoppingcart.shoppingcart.dao.ProductRepository;
import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        Optional<Product> result = productRepository.findById(id);
        Product product=null;

        if(result.isPresent()){
            product=result.get();
        }else {
            throw new RuntimeException("Can't find product ID: " + id);
        }
        return product;
    }
}
