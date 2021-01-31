package com.joycetsai.shoppingcart.shoppingcart.controller;

import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import com.joycetsai.shoppingcart.shoppingcart.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private List<Product> productsFilteredByCat;
    private Page<Product> filteredPage;
    private List<Integer> filteredPageNumbers;
    private String selectedCategory;

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
        List<String> theCategories = productService.getCategories();
        theCategories.add(0," ");
        theModel.addAttribute("categories", theCategories);
        return "product-list";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("theSearchName") String searchName,
                                Model theModel,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        List<Product> products;

        if(searchName != null) {
            products = productService.search(searchName);
        }else{
            products = productService.findAll();
        }

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

        List<String> theCategories = productService.getCategories();
        theCategories.add(0," ");
        theModel.addAttribute("categories", theCategories);
        theModel.addAttribute("products",products);

        return "product-list";
    }

    @GetMapping("list/{categoryName}")
    public String categoryFilter(@PathVariable String categoryName,
                                 Model theModel,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        productsFilteredByCat = productService.findProductByCategory(categoryName);

        filteredPage
                = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize),productsFilteredByCat);

        int totalPages = filteredPage.getTotalPages();
        if (totalPages > 0) {
            filteredPageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

        }
        theModel.addAttribute("productPage", this.filteredPage);
        theModel.addAttribute("pageNumbers", this.filteredPageNumbers);
        List<String> theCategories = productService.getCategories();
        theCategories.add(0," ");
        theModel.addAttribute("categories", theCategories);
        theModel.addAttribute("selectedCategoryName", categoryName);
        selectedCategory=categoryName;

        return "product-list";

    }


}
