/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.controllers;

import com.ritesh.ecommerce.dao.Category;
import com.ritesh.ecommerce.dao.JSONResponse;
import com.ritesh.ecommerce.dao.Product;
import com.ritesh.ecommerce.dao.User;
import com.ritesh.ecommerce.interfaces.ProductDataAccessInterface;
import com.ritesh.ecommerce.services.ProductDataAccess;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Ritesh Kukreja
 */
@Controller
@SessionAttributes("userObj")
public class ProductController {
    
    @Autowired
    ProductDataAccessInterface productDataAccess;
    
    @GetMapping("/products/{prodId}")
    @ResponseBody
    public JSONResponse viewProduct(@PathVariable Long prodId) throws Exception {
        return new JSONResponse(true, productDataAccess.get(prodId));
    }
    
    @GetMapping(path = "/products")
    @ResponseBody
    public JSONResponse viewAllProduct() throws Exception {
        return new JSONResponse(true, productDataAccess.getAll());
    }
    
    @GetMapping(path = "/category")
    @ResponseBody
    public JSONResponse viewAllCatgeory() throws Exception {
        return new JSONResponse(true, productDataAccess.getAllCategory());
    }
    
    @PostMapping("/products")
    @ResponseBody
    public JSONResponse addProduct(@ModelAttribute Product product, BindingResult result, @RequestParam Long categoryId) throws Exception {
        if(result.hasErrors()) {
            throw new Exception("Binding Result error");
        }        
        productDataAccess.add(product,categoryId);
        return new JSONResponse(true, product);
    }
    
    @PostMapping("/category")
    @ResponseBody
    public JSONResponse addCategory(@ModelAttribute Category category, BindingResult result) throws Exception {
        if(result.hasErrors()) {
            throw new Exception("Binding Result error");
        }
        
        productDataAccess.addCategory(category);
        return new JSONResponse(true, category);
    }
    
    @ExceptionHandler
    @ResponseBody
    public JSONResponse ShowFourOhFour(Exception e) {
        e.printStackTrace();
        return new JSONResponse(false, e.getMessage());
    }
    
}
