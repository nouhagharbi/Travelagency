package com.ditraacademy.travelagency.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

    @Autowired
    JavaMailSender javaMailSender;

    public void senEmail(String destination, String subject, String text){

        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(destination);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
