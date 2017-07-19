/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.dao;

import javax.persistence.Embeddable;

/**
 *
 * @author Ritesh Kukreja
 */
@Embeddable
public class Address {
    public String street;
    public String city;
    public String country;
    
    public String pincode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    public String toString() {
        return street + ", " + city + ", " + country.toUpperCase() + " - " + pincode;
    }
    
}
