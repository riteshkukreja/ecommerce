/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.services;

import com.ritesh.ecommerce.dao.Address;
import com.ritesh.ecommerce.dao.Cart;
import com.ritesh.ecommerce.dao.Orders;
import com.ritesh.ecommerce.dao.OrderItem;
import com.ritesh.ecommerce.dao.Payment;
import com.ritesh.ecommerce.dao.Product;
import com.ritesh.ecommerce.dao.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.ritesh.ecommerce.interfaces.CartDataAccessInterface;
import com.ritesh.ecommerce.interfaces.UserDataAccessInterface;

/**
 *
 * @author Ritesh Kukreja
 */
@Component
@Transactional
public class UserDataAccess implements UserDataAccessInterface {

    @PersistenceContext
    EntityManager em;
    
    public User get(Long id) throws Exception {
        return em.find(User.class, id);
    }

    public List<User> getAll() {
        return em.createQuery("from User", User.class).getResultList();
    }

    public void update(Long id, User data) throws Exception {
        data.setId(id);
        em.merge(data);
    }

    public Long add(User data) throws Exception {
        em.persist(data);
        return data.getId();
    }

    public void delete(int id) throws Exception {
        User user = em.find(User.class, id);
        em.remove(user);
    }   
    
    public void addAddress(Long id, Address address) throws Exception {
        User user = get(id);
        user.addAddress(address);
    }

    public void addPayment(Long id, Payment payment) throws Exception {
        User user = get(id);
        user.addPayment(payment);
    }

    public List<Payment> getPayments(Long userId) throws Exception {
        User user = get(userId);
        return user.getPayments();
    }

    public List<Address> getAddress(Long userId) throws Exception {
        User user = get(userId);
        return user.getAddress();
    }
    
    public User login(String email, String pass) {
        User user = em.createQuery("from User where email = :email and pass = :pass", User.class)
                    .setParameter("pass", pass)
                    .setParameter("email", email)
                    .getSingleResult();
        return user;
    }

    public OrderItem getOrderItem(Long prodId, int quantity) {
        Product product = em.find(Product.class, prodId);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setDateAdded(new Date());
        
        return orderItem;
    }
    
}
