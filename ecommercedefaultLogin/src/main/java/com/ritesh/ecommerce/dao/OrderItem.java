/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.dao;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ritesh Kukreja
 */
@Entity
public class OrderItem {  
    
    @Id
    @GeneratedValue
    public int id;
    
    @ManyToOne
    public Product product;  
    
    public int quantity;
    
    @Column(columnDefinition="timestamp")
    public Date dateAdded;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    } 
    
    public String toString() {
        return "[ product=" + product + ", qunatity=" + quantity + ", price=" + (product.getPrice() * quantity) + " ]";
    }
    
}
