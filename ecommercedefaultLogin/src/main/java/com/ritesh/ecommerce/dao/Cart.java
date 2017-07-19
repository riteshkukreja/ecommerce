/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ritesh Kukreja
 */
@Entity
public class Cart {
    
    @Id
    @GeneratedValue
    public Long id;
    
    @ManyToOne
    public User user;
   
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<OrderItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public void addItems(OrderItem item) {
        this.items.add(item);
    }
        
    public void removeItems(OrderItem item) {
        this.items.remove(item);
    }

    public void clear() {
        this.items.clear();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public int calculatePrice() {
        int cost = 0;
        for(OrderItem orderItem: items) {
            cost += (orderItem.getProduct().getPrice() * orderItem.getQuantity());
        }
        return cost;
    }
    
    public String toString() {
        return "[ products=" + items.toString() + ", price=" + calculatePrice() + " ]";
    }
    
}
