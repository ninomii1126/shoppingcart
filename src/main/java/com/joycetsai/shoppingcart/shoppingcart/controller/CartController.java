package com.joycetsai.shoppingcart.shoppingcart.controller;

import com.joycetsai.shoppingcart.shoppingcart.entity.*;
import com.joycetsai.shoppingcart.shoppingcart.service.OrderService;
import com.joycetsai.shoppingcart.shoppingcart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@Controller
@RequestMapping("/cart")
public class CartController {

    private Cart cart;
    private ProductService productService;
    private OrderService orderService;

    public CartController(ProductService theProductService, OrderService theOrderService) {
        productService = theProductService;
        orderService = theOrderService;
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
            cart.setTotalPrice(0.00);
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
        if(quantity==0) {
            cart.deleteItem(product);
        }else {
            this.cart.modifyQuantity(product, quantity);
        }
        //modifyId = String.format("%d",productId);

        return "redirect:/cart/modify-amount";

    }

    @GetMapping("/modify-amount")
    public String modifyPage(Model theModel) throws IOException {

        try {
            theModel.addAttribute("theCart", cart);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "cart-list";
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam("username")String userName,
                              Model theModel){

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Order order = new Order(userName,(String)(date.format(formatter)),"complete");

        Map<Integer, CartItem> items = cart.getItems();

        for(CartItem tempItem: items.values()){

            OrderItem item
                    = new OrderItem(tempItem.getProductId(), tempItem.getQuantity(),
                    tempItem.getProduct().getPrice(), tempItem.getItemPrice());

            item.setProductName((productService.findById(item.getProduct())).getName());

            order.addItem(item);
        }
        orderService.save(order);
        theModel.addAttribute("theOrder",order);

        //total price
        theModel.addAttribute("totalPrice",cart.getTotalPrice());

        return "order-detail";
    }





}
