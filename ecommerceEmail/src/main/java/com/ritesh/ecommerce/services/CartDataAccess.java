/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.services;

import com.ritesh.ecommerce.dao.Cart;
import com.ritesh.ecommerce.dao.Category;
import com.ritesh.ecommerce.dao.OrderItem;
import com.ritesh.ecommerce.dao.Orders;
import com.ritesh.ecommerce.dao.Payment;
import com.ritesh.ecommerce.dao.Product;
import com.ritesh.ecommerce.dao.User;
import com.ritesh.ecommerce.interfaces.CartDataAccessInterface;
import com.ritesh.ecommerce.interfaces.OrderDataAccessInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class CartDataAccess implements CartDataAccessInterface{
    
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    OrderDataAccessInterface orderDataAccess;
    
    @Autowired
    EmailSender emailSender;
    
    public Cart get(Long id) throws Exception {
        return em.find(Cart.class, id);
    }
    
    public Cart get(User user) throws Exception {
        try {
            return em.createQuery("from Cart where user = :user ", Cart.class)
                   .setParameter("user", user)
                   .getSingleResult();
        } catch(NoResultException  e) {
            
        }
        return null;
    }

    public List<Cart> getAll() {
        return em.createQuery("from Cart", Cart.class).getResultList();
    }

    public void update(User user, Cart data) throws Exception {
        data.setId(user.getId());
        em.merge(data);
    }

    public Long add(Cart data) throws Exception {
        em.persist(data);
        return data.getId();
    }

    public void delete(int id) throws Exception {
        Cart user = em.find(Cart.class, id);
        em.remove(user);
    }
    
    public void addOrderItem(User user, OrderItem orderItem) throws Exception {
        Cart cart = get(user);
        if(cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }
        cart.addItems(orderItem);
        em.persist(cart);
    }
    
    public void removeOrderItem(User user, int id) throws Exception {
        Cart cart = get(user);
        List<OrderItem> orderItems = cart.getItems();
        for(int i = orderItems.size()-1; i >= 0 ; i--) {
            if(orderItems.get(i).id == id) {
                cart.removeItems(orderItems.get(i));
                break;
            }
        }
        em.persist(cart);
    }
    
    public OrderItem clone(OrderItem orderItem) {
        OrderItem newOrder = new OrderItem();
        newOrder.setDateAdded(orderItem.getDateAdded());
        newOrder.setProduct(orderItem.getProduct());
        newOrder.setQuantity(orderItem.getQuantity());
        return newOrder;
    }
    
    public Long checkoutCart(User user) throws Exception {
        Orders order = new Orders();
        order.setUser(user);
        Cart cart = get(user);
        
        List<OrderItem> ll = order.getItems();
        for(OrderItem orderItem: cart.getItems()) {
            ll.add(clone(orderItem));
        }
        cart.clear();
        
        int price = 0;        
        for(OrderItem orderItem: ll) {
            price += (orderItem.product.getPrice() * orderItem.getQuantity());
        }
        
        order.setPrice(price);    
        orderDataAccess.add(order);
        emailSender.sendMail("alert@test.com", user.getEmail(), "Order details", order.toString());
        
        em.persist(cart);
        
        return order.getId();
    }
}
