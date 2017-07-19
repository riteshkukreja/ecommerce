/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.controllers;

import com.ritesh.ecommerce.dao.Address;
import com.ritesh.ecommerce.dao.Cart;
import com.ritesh.ecommerce.dao.JSONResponse;
import com.ritesh.ecommerce.dao.Orders;
import com.ritesh.ecommerce.dao.OrderItem;
import com.ritesh.ecommerce.dao.Payment;
import com.ritesh.ecommerce.dao.User;
import com.ritesh.ecommerce.interfaces.CartDataAccessInterface;
import com.ritesh.ecommerce.interfaces.OrderDataAccessInterface;
import com.ritesh.ecommerce.interfaces.ProductDataAccessInterface;
import com.ritesh.ecommerce.interfaces.UserDataAccessInterface;
import com.ritesh.ecommerce.services.CurrentUserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Ritesh Kukreja
 */
@Controller("")
@SessionAttributes("userObj")
public class UserController {
    
    @Autowired
    UserDataAccessInterface userDataAccess;
    
    @Autowired
    ProductDataAccessInterface productDataAccess;
    
    @Autowired
    CartDataAccessInterface cartDataAccess;
    
    @Autowired
    OrderDataAccessInterface orderDataAccess;
    
    @GetMapping("/users/all")
    @ResponseBody
    public JSONResponse users() {
        List<User> users = userDataAccess.getAll();
        return new JSONResponse(true, users);
    }
    
    @PostMapping("users/register")
    public String addUser(@ModelAttribute User user, BindingResult result) throws Exception {
        if(result.hasErrors()) {
            throw new Exception(result.getAllErrors().toString());
        }
        Long id = userDataAccess.add(user);
        return "redirect:/login";
    }
    
    @PostMapping("me/address")
    @ResponseBody
    public JSONResponse addUserAddress(@ModelAttribute Address address, BindingResult result) throws Exception {
        if(result.hasErrors()) {
            return new JSONResponse(false, result.getAllErrors().toString());
        }
        User user = CurrentUserService.getCurrentUser();
        userDataAccess.addAddress(user.getId(), address);
        return new JSONResponse(true, null);
    }
    
    @GetMapping("me/address")
    @ResponseBody
    public JSONResponse getUserAddress() throws Exception {
        User user = CurrentUserService.getCurrentUser();
        List<Address> address = userDataAccess.getAddress(user.getId());
        
        return new JSONResponse(true, address);
    }
    
    @GetMapping("me/payment")
    @ResponseBody
    public JSONResponse getUserPayment() throws Exception {
        User user = CurrentUserService.getCurrentUser();
        List<Payment> payment = userDataAccess.getPayments(user.getId());
        return new JSONResponse(true, payment);
    }
    
    @PostMapping("me/payment")
    @ResponseBody
    public JSONResponse addUserPayment(@ModelAttribute Payment payment, BindingResult result) throws Exception {
        if(result.hasErrors()) {
            return new JSONResponse(false, result.getAllErrors().toString());
        }
        User user = CurrentUserService.getCurrentUser();
        userDataAccess.addPayment(user.getId(), payment);
        return new JSONResponse(true, null);
    }
    
    @PostMapping("me/cart/add")
    @ResponseBody
    public JSONResponse addToCart(@RequestParam Long prodId, @RequestParam int quantity) throws Exception {
        User user = CurrentUserService.getCurrentUser();
        OrderItem orderItem = userDataAccess.getOrderItem(prodId, quantity);
        cartDataAccess.addOrderItem(user, orderItem);
        return new JSONResponse(true, orderItem);
    }
    
    @PostMapping("me/cart/remove")
    @ResponseBody
    public JSONResponse removeFromCart(@RequestParam int orderItem) throws Exception {
        User user = CurrentUserService.getCurrentUser();
        cartDataAccess.removeOrderItem(user, orderItem);
        return new JSONResponse(true, orderItem);
    }
    
    @PostMapping("me/cart/checkout")
    @ResponseBody
    public JSONResponse checkoutCart() throws Exception {
        User user = CurrentUserService.getCurrentUser();
        Long id = cartDataAccess.checkoutCart(user);
        return new JSONResponse(true, id.toString());
    }
    
    @PostMapping("me/order/pay")
    @ResponseBody
    public JSONResponse finishOrder(@RequestParam Long orderId, @RequestParam Long paymentId) throws Exception {
        User user = CurrentUserService.getCurrentUser();
        Orders order = orderDataAccess.payOrder(user, orderId, paymentId);
        return new JSONResponse(true, order);
    }
    
    @PostMapping("me/order/cancel")
    @ResponseBody
    public JSONResponse cancelOrder(@RequestParam Long orderId) throws Exception {
        orderDataAccess.delete(orderId);
        return new JSONResponse(true, null);
    }
    
    @GetMapping("me/orders/{orderId}")
    @ResponseBody
    public JSONResponse showOrder(@PathVariable Long orderId) throws Exception {
        Orders order = orderDataAccess.get(orderId);
        return new JSONResponse(true, order);
    }
    
    @GetMapping("me/orders")
    @ResponseBody
    public JSONResponse showAllOrder() throws Exception {
        User user = CurrentUserService.getCurrentUser();
        List<Orders> order = orderDataAccess.getUsersList(user);
        return new JSONResponse(true, order);
    }
    
    @GetMapping("me/cart")
    @ResponseBody
    public JSONResponse showCart() throws Exception {
        User user = CurrentUserService.getCurrentUser();
        Cart cart = cartDataAccess.get(user);
        if(cart == null)
            return new JSONResponse(true, new ArrayList<>());
        return new JSONResponse(true, cart.getItems());
    }
    
    @ExceptionHandler
    @ResponseBody
    public JSONResponse ShowFourOhFour(Exception e) {
        e.printStackTrace();
        return new JSONResponse(false, e.getMessage());
    }
    
}
