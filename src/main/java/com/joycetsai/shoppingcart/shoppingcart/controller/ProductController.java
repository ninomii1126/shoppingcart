package com.joycetsai.shoppingcart.shoppingcart.controller;

import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import com.joycetsai.shoppingcart.shoppingcart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService theProductService) {
        productService = theProductService;
    }

    @GetMapping("/list")
    public String ListProduct(Model theModel){
        //get products from DB
        List<Product> theProducts = productService.findAll();

        theModel.addAttribute("products", theProducts);
        return "product-list";
    }


}
