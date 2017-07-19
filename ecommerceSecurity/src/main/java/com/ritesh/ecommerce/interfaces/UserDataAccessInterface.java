/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.interfaces;

import com.ritesh.ecommerce.dao.Address;
import com.ritesh.ecommerce.dao.OrderItem;
import com.ritesh.ecommerce.dao.Payment;
import com.ritesh.ecommerce.dao.User;
import java.util.List;

/**
 *
 * @author Ritesh Kukreja
 */
public interface UserDataAccessInterface {
    
    public User get(Long id) throws Exception;
    public List<User> getAll();
    public void update(Long id, User data) throws Exception;
    public Long add(User data) throws Exception;
    public void delete(int id) throws Exception;
    public void addAddress(Long id, Address address) throws Exception;
    public void addPayment(Long id, Payment payment) throws Exception;
    public List<Payment> getPayments(Long userId) throws Exception;
    public List<Address> getAddress(Long userId) throws Exception;
    public User login(String email, String pass);
    public OrderItem getOrderItem(Long prodId, int quantity);
    
}
