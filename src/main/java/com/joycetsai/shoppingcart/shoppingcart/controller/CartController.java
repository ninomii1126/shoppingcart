package com.joycetsai.shoppingcart.shoppingcart.controller;

import com.joycetsai.shoppingcart.shoppingcart.entity.Cart;
import com.joycetsai.shoppingcart.shoppingcart.entity.Product;
import com.joycetsai.shoppingcart.shoppingcart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cart")
public class CartController {

    private Cart cart;
    private ProductService productService;


    public CartController(ProductService theProductService) {
        productService = theProductService;
    }

    @GetMapping("/add")
    public String addItem(@RequestParam("productId") int productId, Model theModel){

        if(cart==null){
            cart = new Cart();
        }
        Product product = productService.findById(productId);
        cart.addItem(product);
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

        theModel.addAttribute("theCart",this.cart);
        return "cart-list";
    }

    @GetMapping("/clear")
    public String clearCart(Model theModel){

        cart.clearCart();
        theModel.addAttribute("theCart",cart);
        return "cart-list";
    }


    @GetMapping("/modify-amount")
    public  String modifyAmount(@RequestParam("productId") Integer productId ,
                                @RequestParam("quantity") Integer quantity,
                                Model theModel){


        Product product = productService.findById(productId);
        this.cart.modifyQuantity(product,quantity);
        theModel.addAttribute("theCart",this.cart);


        return "redirect:/cart/list";
    }


}
