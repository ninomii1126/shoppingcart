package com.joycetsai.shoppingcart.shoppingcart.dao;

import com.joycetsai.shoppingcart.shoppingcart.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
