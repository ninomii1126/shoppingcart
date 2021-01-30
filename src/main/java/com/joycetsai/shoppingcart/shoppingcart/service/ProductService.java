package com.joycetsai.shoppingcart.shoppingcart.service;

import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();
    public Product findById(int id);
    public List<Product> search(String searchName);
    public Page<Product> findPaginated(Pageable pageable,List<Product> theProducts);
    public List<String> getCategories();
    List<Product> findProductByCategory(String categoryName);
    List<Product> searchProductByCategory(String categoryName,String theSearchName);
}
