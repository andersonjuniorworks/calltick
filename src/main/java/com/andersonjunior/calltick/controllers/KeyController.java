package com.andersonjunior.calltick.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Key;
import com.andersonjunior.calltick.services.KeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/key")
public class KeyController {

    @Autowired
    private KeyService keyService;
    
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Key> generateKey(@RequestParam() String cnpj, @RequestParam() String monthAndYear) {

        String completeDate = "";

        for(int i = 0; i < monthAndYear.length(); i++) {
            completeDate = monthAndYear.substring(3, 10);
        }

        String obj = keyService.gerarChave(cnpj, completeDate);
        Key key = new Key();
        key.setKey(obj);
        return ResponseEntity.ok().body(key);

    }

}
