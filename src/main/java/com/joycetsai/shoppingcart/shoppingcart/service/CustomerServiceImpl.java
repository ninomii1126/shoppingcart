package com.joycetsai.shoppingcart.shoppingcart.service;

import com.joycetsai.shoppingcart.shoppingcart.dao.CustomerRepository;
import com.joycetsai.shoppingcart.shoppingcart.entity.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {

        Optional<Customer> result = customerRepository.findById(id);
        Customer customer=null;

        if(result.isPresent()){
            customer=result.get();
        }else {
            throw new RuntimeException("Can't find customer ID: " + id);
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        Optional<Customer> result = customerRepository.findById(id);

        if(result.isPresent()){
            customerRepository.deleteById(id);
        }else {
            throw new RuntimeException("Can't find customer ID: " + id);
        }
    }
}
