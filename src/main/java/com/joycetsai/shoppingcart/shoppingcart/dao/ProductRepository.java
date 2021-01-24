package com.joycetsai.shoppingcart.shoppingcart.dao;

import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
