/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.dao;

/**
 *
 * @author Ritesh Kukreja
 */
public class JSONResponse {
    
    public Boolean success = false;
    public Object message = null;
    public String error = null;
    
    public JSONResponse(Boolean status, Object message) {
        success = status;
        if(status)
            this.message = message;
        else
            this.error = (String)message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
