/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.interfaces;

import com.ritesh.ecommerce.dao.Cart;
import com.ritesh.ecommerce.dao.OrderItem;
import com.ritesh.ecommerce.dao.User;
import java.util.List;

/**
 *
 * @author Ritesh Kukreja
 */
public interface CartDataAccessInterface {
    
    public Cart get(Long id) throws Exception;
    public Cart get(User user) throws Exception;
    public List<Cart> getAll();
    public void update(User user, Cart data) throws Exception;
    public Long add(Cart data) throws Exception;
    public void delete(int id) throws Exception;
    public void addOrderItem(User user, OrderItem orderItem) throws Exception;
    public void removeOrderItem(User user, int id) throws Exception;
    public OrderItem clone(OrderItem orderItem);
    public Long checkoutCart(User user) throws Exception;
    
    
}
