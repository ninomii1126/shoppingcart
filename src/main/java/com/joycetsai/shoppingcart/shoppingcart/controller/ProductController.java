package com.joycetsai.shoppingcart.shoppingcart.controller;

import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import com.joycetsai.shoppingcart.shoppingcart.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService theProductService) {
        productService = theProductService;
    }

    @GetMapping("/list")
    public String listProduct(Model theModel,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        //get products from DB
        List<Product> theProducts = productService.findAll();

        Page<Product> productPage
                = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize),theProducts);

        theModel.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            theModel.addAttribute("pageNumbers", pageNumbers);
        }

        theModel.addAttribute("products", theProducts);
        return "product-list";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("theSearchName") String searchName,
                                Model theModel,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        List<Product> products = productService.search(searchName);

        Page<Product> productPage
                = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize),products);

        theModel.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            theModel.addAttribute("pageNumbers", pageNumbers);
        }



        theModel.addAttribute("products",products);

        return "product-list";
    }


}
