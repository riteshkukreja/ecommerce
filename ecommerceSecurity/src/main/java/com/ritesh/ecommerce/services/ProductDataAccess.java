/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.services;

import com.ritesh.ecommerce.dao.Category;
import com.ritesh.ecommerce.dao.Product;
import com.ritesh.ecommerce.interfaces.ProductDataAccessInterface;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Ritesh Kukreja
 */
@Component
@Transactional
@SessionAttributes("userObj")
public class ProductDataAccess implements ProductDataAccessInterface {
    
    @PersistenceContext
    EntityManager em;
    
    public Product get(Long id) throws Exception {
        return em.find(Product.class, id);
    }

    public List<Product> getAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

    public List<Category> getAllCategory() {
        return em.createQuery("from Category", Category.class).getResultList();
    }

    public void update(Long id, Product data) throws Exception {
        data.setId(id);
        em.merge(data);
    }

    public Long add(Product data, Long categoryId) throws Exception {
        Category category = getCategory(categoryId);
//        category.addProduct(data);
        data.setCategory(category);
        em.persist(data);
        return data.getId();
    }

    public void delete(int id) throws Exception {
        Product user = em.find(Product.class, id);
        em.remove(user);
    }
    
    public Category getCategory(Long id) {
        return em.find(Category.class, id);
    }
    
    public void addCategory(Category category) {
        em.persist(category);
    }

    public List<Product> getFiltered(Map<String, String> filters) {
        String query = "from Product";
        
        if(filters.size() > 0)
            query += " where ";
        
        for(Map.Entry<String, String> entry: filters.entrySet()) {
            query += entry.getKey() + " = :" + entry.getKey() + " and ";
        }
        
        query = query.substring(0, query.length() - 4);
        
        TypedQuery<Product> createQuery = em.createQuery(query, Product.class);
        for(Map.Entry<String, String> entry: filters.entrySet()) {
            createQuery.setParameter(entry.getKey(), entry.getValue());
        }
        
        return createQuery.getResultList();
    }
}
