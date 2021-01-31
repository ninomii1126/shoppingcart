package com.joycetsai.shoppingcart.shoppingcart.controller;

import com.joycetsai.shoppingcart.shoppingcart.entity.Cart;
import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import com.joycetsai.shoppingcart.shoppingcart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
@RequestMapping("/cart")
public class CartController {

    private Cart cart;
    private ProductService productService;
    //private String  modifyId;


    public CartController(ProductService theProductService) {
        productService = theProductService;
    }

    @PostMapping("/add")
    public String addItem(@RequestParam("productId") Integer productId){

        if(cart==null){
            cart = new Cart();
        }
        Product product = productService.findById(productId);
        cart.addItem(product);

        return "redirect:/cart/add";
    }

    @GetMapping("/add")
    public String showListAfterAdd(Model theModel){
        theModel.addAttribute("theCart",cart);
        return "cart-list";
    }

    @GetMapping("/delete")
    public String deleteItem(@RequestParam("productId") int productId){

        Product product = productService.findById(productId);

        cart.deleteItem(product);

        return "redirect:/cart/list";
    }

    @GetMapping("/list")
    public String listCartItems(Model theModel){

        if(cart==null){
            cart = new Cart();
        }
        theModel.addAttribute("theCart",this.cart);
        return "cart-list";
    }

    @GetMapping("/clear")
    public String clearCart(Model theModel){

        cart.clearCart();
        theModel.addAttribute("theCart",cart);
        return "cart-list";
    }

    @PostMapping("/modify-amount")
    public String modifyAmount(@RequestParam("productId") int productId ,
                               @RequestParam("quantity") int quantity){


        Product product = productService.findById(productId);
        this.cart.modifyQuantity(product, quantity);

        //modifyId = String.format("%d",productId);

        return "redirect:/cart/modify-amount";

    }

    @GetMapping("/modify-amount")
    public String modifyPage(Model theModel) throws IOException {

        try {
            theModel.addAttribute("theCart", cart);
//            String str = "modifyQuantity";
//            String strId = str.concat(modifyId);
//            theModel.addAttribute()

        }catch (Exception e){
            e.printStackTrace();
        }

        return "cart-list";
    }





}
