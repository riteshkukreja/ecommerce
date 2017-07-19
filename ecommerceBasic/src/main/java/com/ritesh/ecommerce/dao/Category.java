/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ritesh Kukreja
 */
@Entity
public class Category {
    
    @Id
    @GeneratedValue
    public Long id;
    
    public String name;
    
//    @OneToMany(mappedBy="category")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    public List<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
//    
//    public void addProduct(Product product) {
//        product.setCategory(this);
//        this.products.add(product);
//    }
//    
//    public void removeProduct(Product product) {
//        product.setCategory(null);
//        this.products.remove(product);
//    }
    
//    public String toString() {
//        return "[ name=" + name + ", numProducts=" + products.size() + " ]";
//    }
}
