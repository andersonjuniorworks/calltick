package com.andersonjunior.calltick.controllers;

import java.util.ArrayList;
import java.util.List;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Notification;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.services.CalledService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class NotificationController {

    private final List<User> users = new ArrayList<>();

    @Autowired
    private SimpMessagingTemplate template;

    private Notification notification = new Notification("");

    @Autowired
    private CalledService calledService;

    @GetMapping("/notify")
    public String getNotification() {
        notification.setMessage("Chamado aberto com sucesso!");
        template.convertAndSend("/topic/notification", notification);
        return notification.getMessage();
    }

    @GetMapping("/connected")
    public List<User> getConnected(@RequestParam User user) {
        template.convertAndSend("/topic/connected", notification);
        if(!users.contains(user)) {
            users.add(user);
        }
        return users;
    }

    @GetMapping("/disconnect")
    public List<User> getDisconnect(@RequestParam User user) {
        template.convertAndSend("/topic/disconnected", notification);
        users.remove(user);
        return users;
    }

    @GetMapping("/usersConnected")
    public List<User> getUserConnected() {
        template.convertAndSend("/topic/users", notification);
        return users;
    }

    @GetMapping("/getTickets")
    public ResponseEntity<List<Called>> getTickets() {
        List<Called> list = calledService.findAll(0, 1000000);
        template.convertAndSend("/topic/tickets", list);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        template.convertAndSend("/topic/tickets", users);
        return ResponseEntity.ok().body(users);
    }
    
}
