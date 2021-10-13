package com.andersonjunior.calltick.controllers;

import com.andersonjunior.calltick.models.Key;
import com.andersonjunior.calltick.services.KeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/key")
public class KeyController {

    @Autowired
    private KeyService keyService;

    @CrossOrigin
    @ApiOperation(value = "Retorna uma chave de liberação")
    @GetMapping()
    public ResponseEntity<Key> generateKey(@RequestParam() String cnpj, @RequestParam() String monthAndYear) {

        String completeDate = "";

        for(int i = 0; i < monthAndYear.length(); i++) {
            completeDate = monthAndYear.substring(3, 10);
        }

        Key key = new Key();

        key.setKey(keyService.gerarChave(cnpj, completeDate));
 
        return ResponseEntity.ok().body(key);

    }

}
