package com.joycetsai.shoppingcart.shoppingcart.dao;

import com.joycetsai.shoppingcart.shoppingcart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("select o from Order o where o.username= :username")
    public List<Order> findByUser(@Param("username")String username);


}
