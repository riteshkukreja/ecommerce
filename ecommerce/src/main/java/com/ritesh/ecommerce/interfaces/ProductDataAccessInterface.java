/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.interfaces;

import com.ritesh.ecommerce.dao.Category;
import com.ritesh.ecommerce.dao.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ritesh Kukreja
 */
public interface ProductDataAccessInterface {
    
    public Product get(Long id) throws Exception;
    public List<Product> getAll();
    public List<Category> getAllCategory();
    public void update(Long id, Product data) throws Exception;
    public Long add(Product data, Long categoryId) throws Exception;
    public void delete(int id) throws Exception;
    public Category getCategory(Long id);
    public void addCategory(Category category);
    public List<Product> getFiltered(Map<String, String> filters);    
    
}
