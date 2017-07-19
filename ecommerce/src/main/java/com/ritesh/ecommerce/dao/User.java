/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Ritesh Kukreja
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue
    public Long id;
    
    public String name;
    
    @Email
    public String email;
    
    public String pass;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Embedded
    public List<Address> address = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Payment> payments = new ArrayList<>();

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

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
    
    public void addAddress(Address address) {
        this.address.add(address);
    }
    
    public void removeAddress(Address address) {
        this.address.remove(address);
    }
    
    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }
    
    public void removePayment(Payment payment) {
        this.payments.remove(payment);
    }
    
    public String toString() {
        return "[ name=" + name + ", email=" + email + ", password=" + pass + " ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
