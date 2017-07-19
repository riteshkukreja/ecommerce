/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.controllers;

import com.ritesh.ecommerce.dao.Orders;
import com.ritesh.ecommerce.dao.User;
import com.ritesh.ecommerce.interfaces.OrderDataAccessInterface;
import com.ritesh.ecommerce.services.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Ritesh Kukreja
 */
@Controller("")
@SessionAttributes("userObj")
public class ViewController {       
    
    @Autowired
    OrderDataAccessInterface orderDataAccess;
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
    @GetMapping("/dashboard")
    public String dashboard_page() {
        return "dashboard";
    }
    
    @GetMapping("/orders/{orderId}")
    public String order_page(@PathVariable Long orderId, Model model) throws Exception {
        Orders order = orderDataAccess.get(orderId);
        model.addAttribute("order", order);
        model.addAttribute("single", true);
        return "orders";
    }
    
    @GetMapping("/orders")
    public String order_page_all() throws Exception {
        return "orders";
    }
    
    @GetMapping("/me")
    public String user(Model model) throws Exception {
        User user = CurrentUserService.getCurrentUser();
        model.addAttribute("user", user);
        return "details";
    }
    
    @GetMapping("/addProduct")
    public String addProduct() {
        return "addProduct";
    }
    
    @ExceptionHandler
    public String ShowFourOhFour(HttpSessionRequiredException e) {
        e.printStackTrace();
        return "redirect:/login";
    }
    
}
