package com.joycetsai.shoppingcart.shoppingcart.service;

import com.joycetsai.shoppingcart.shoppingcart.dao.ProductRepository;
import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public List<Product> search(String searchName) {

        List<Product> products = new ArrayList<Product>();

        products=productRepository.findByProductName(searchName);

        return products;
    }

    @Override
    public Page<Product> findPaginated(Pageable pageable, List<Product> theProducts) {

        List<Product> products = new ArrayList<>();
        products=theProducts;
        List<Product> list;

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;


        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> bookPage
                = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());

        return bookPage;
    }
}
