package com.andersonjunior.calltick.controllers;

import java.util.HashMap;
import java.util.List;

import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.services.CalledService;
import com.andersonjunior.calltick.services.SectorService;
import com.andersonjunior.calltick.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/charts")
public class ChartController {

    private final CalledService calledService;
    private final UserService userService;
    private final SectorService sectorService;

    @Autowired
    public ChartController(CalledService calledService, UserService userService, SectorService sectorService) {
        this.calledService = calledService;
        this.userService = userService;
        this.sectorService = sectorService;
    }

    @CrossOrigin
    @GetMapping(value = "/ticketByUser")
    public ResponseEntity<Object> chartTicketByUser() {
        List<User> users = userService.findAll(0, 1000000);
        HashMap<String, Integer> list = new HashMap<String, Integer>();
        for (int i = 0; i < users.size(); i++) {
            list.put(users.get(i).getFullname(), calledService.countByFilter(null, users.get(i), null, null));
        }
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @GetMapping(value = "/ticketBySector")
    public ResponseEntity<Object> chartTicketBySector() {
        List<Sector> sectors = sectorService.findAll(0, 1000000);
        HashMap<String, Integer> list = new HashMap<String, Integer>();
        for (int i = 0; i < sectors.size(); i++) {
            list.put(sectors.get(i).getDescription(), calledService.countByFilter(null, null, sectors.get(i), null));
        }
        return ResponseEntity.ok().body(list);
    }

}
