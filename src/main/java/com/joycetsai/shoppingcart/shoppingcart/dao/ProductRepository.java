package com.joycetsai.shoppingcart.shoppingcart.dao;

import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.name like CONCAT('%',:theSearchName,'%') ")
    List<Product> findByProductName(@Param("theSearchName") String theSearchName);
}
