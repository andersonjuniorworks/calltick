package com.andersonjunior.calltick.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SenderEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviar() {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("andersonjunior.tech@gmail.com \n");
        email.setSubject("Teste envio de e-mail \n");
        email.setText("Enviei este e-mail usando Spring Boot.");
        mailSender.send(email);
    }
    
}
