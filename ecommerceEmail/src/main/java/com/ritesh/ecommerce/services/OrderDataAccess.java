/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.services;

import com.ritesh.ecommerce.dao.Cart;
import com.ritesh.ecommerce.dao.OrderItem;
import com.ritesh.ecommerce.dao.Orders;
import com.ritesh.ecommerce.dao.Payment;
import com.ritesh.ecommerce.dao.User;
import com.ritesh.ecommerce.interfaces.OrderDataAccessInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Ritesh Kukreja
 */
@Component
@Transactional
@SessionAttributes("userObj")
public class OrderDataAccess implements OrderDataAccessInterface{
    
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    EmailSender emailSender;
    
    public Orders get(Long id) throws Exception {
        return em.find(Orders.class, id);
    }
    
    public List<Orders> getUsersList(User user) throws Exception {
        return em.createQuery("from Orders where user = :user ", Orders.class)
               .setParameter("user", user)
               .getResultList();
    }

    public List<Orders> getAll() {
        return em.createQuery("from Orders", Orders.class).getResultList();
    }

    public void update(Long orderId, Orders data) throws Exception {
        data.setId(orderId);
        em.merge(data);
    }

    public Long add(Orders data) throws Exception {
        em.persist(data);
        return data.getId();
    }

    public void delete(Long id) throws Exception {
        Orders user = em.find(Orders.class, id);
        em.remove(user);
    }
    
    public Orders payOrder(User user, Long orderId, Long paymentId) throws Exception {
        Orders order = get(orderId);
        Payment payment = getPayment(paymentId);
        if(order == null || payment == null)
            throw new Exception("Invalid Order id or Payment id provided");
        
        order.setPayment(payment);
        em.persist(order);
        emailSender.sendMail("alert@test.com", user.getEmail(), "Payment details", order.toString());
        return order;
    }

    public Payment getPayment(Long paymentId) {
        return em.find(Payment.class, paymentId);
    }
}
