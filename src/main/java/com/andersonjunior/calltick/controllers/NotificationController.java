package com.andersonjunior.calltick.controllers;

import com.andersonjunior.calltick.models.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    private Notification notification = new Notification("");

    @CrossOrigin
    @GetMapping("/notify")
    public String getNotification() {

        template.convertAndSend("/topic/notification", notification);
        return "Notificar novo chamado!";
        
    }
    
}
