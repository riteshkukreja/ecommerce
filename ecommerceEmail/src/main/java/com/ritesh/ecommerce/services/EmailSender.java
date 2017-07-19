/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritesh.ecommerce.services;

import com.ritesh.ecommerce.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ritesh Kukreja
 */
@Service
public class EmailSender {
    
    @Autowired
    private MailSender mailSender;

    public void sendMail(String from, String to, String subject, String msg) {
        System.out.println("sending mail");
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(msg);
            mailSender.send(message);
    }
    
    public void sendRegistrationNotification(User user) {
        String message = "Hello, " + user.getName();
        sendMail(user.getEmail(), user.getEmail(), "Registration Complete", message);
                
    }
}
