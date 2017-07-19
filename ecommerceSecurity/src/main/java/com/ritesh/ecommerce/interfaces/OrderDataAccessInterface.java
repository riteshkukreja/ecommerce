/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.interfaces;

import com.ritesh.ecommerce.dao.Cart;
import com.ritesh.ecommerce.dao.Orders;
import com.ritesh.ecommerce.dao.User;
import java.util.List;

/**
 *
 * @author Ritesh Kukreja
 */
public interface OrderDataAccessInterface {
    
    public Orders get(Long id) throws Exception;
    public List<Orders> getUsersList(User user) throws Exception;
    public List<Orders> getAll();
    public void update(Long orderId, Orders data) throws Exception;
    public Long add(Orders data) throws Exception;
    public void delete(Long id) throws Exception;
    public Orders payOrder(User user, Long orderId, Long paymentId) throws Exception;
    
}
