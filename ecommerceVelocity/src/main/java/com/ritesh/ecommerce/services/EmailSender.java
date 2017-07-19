/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.services;

import com.ritesh.ecommerce.dao.User;
import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Ritesh Kukreja
 */
@Service
public class EmailSender {
    
    @Autowired
    private MailSender mailSender;

    public void sendMail(String from, String to, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        
        // initialize content
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("firstName", "Ritesh");

        // turn to string
        StringWriter stringWriter = new StringWriter();
        
        message.setText(stringWriter.toString());
        
        // creating new template
        Velocity.evaluate(velocityContext, stringWriter, "foo", "Hello $firstName \nYou have been notified. \nThank you");
        message.setText(stringWriter.toString());
        
        mailSender.send(message);
    }
    
    public void sendRegistrationNotification(User user) {
        String message = "Hello, " + user.getName();
        sendMail(user.getEmail(), user.getEmail(), "Registration Complete", message);
                
    }
}
