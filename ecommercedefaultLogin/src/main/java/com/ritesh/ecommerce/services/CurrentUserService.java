/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.services;

import com.ritesh.ecommerce.dao.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Ritesh Kukreja
 */
public class CurrentUserService {
    
    public static User getCurrentUser() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof User)
            return (User) principal;
        
        throw new Exception("Not User logged in");
    }
    
}
