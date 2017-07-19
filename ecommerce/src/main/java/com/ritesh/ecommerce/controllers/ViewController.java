/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.controllers;

import com.ritesh.ecommerce.dao.Orders;
import com.ritesh.ecommerce.dao.User;
import com.ritesh.ecommerce.interfaces.OrderDataAccessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author Ritesh Kukreja
 */
@Controller("")
@SessionAttributes("userObj")
public class ViewController {       
    
    @Autowired
    OrderDataAccessInterface orderDataAccess;
    
    @GetMapping("/login")
    public String login_page() {
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(@ModelAttribute("userObj") User user, SessionStatus status) {
        status.setComplete();
        return "redirect:/login";
    }
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
    @GetMapping("/dashboard")
    public String dashboard_page(@ModelAttribute("userObj") User user) {
        return "dashboard";
    }
    
    @GetMapping("/orders/{orderId}")
    public String order_page(@ModelAttribute("userObj") User user, @PathVariable Long orderId, Model model) throws Exception {
        Orders order = orderDataAccess.get(orderId);
        model.addAttribute("order", order);
        model.addAttribute("single", true);
        return "orders";
    }
    
    @GetMapping("/orders")
    public String order_page_all(@ModelAttribute("userObj") User user) throws Exception {
        return "orders";
    }
    
    @GetMapping("/me")
    public String user(@ModelAttribute("userObj") User user, Model model) {
        model.addAttribute("user", user);
        return "details";
    }
    
    @GetMapping("/addProduct")
    public String addProduct(@ModelAttribute("userObj") User user) {
        return "addProduct";
    }
    
    @ExceptionHandler
    public String ShowFourOhFour(HttpSessionRequiredException e) {
        e.printStackTrace();
        return "redirect:/login";
    }
    
}
