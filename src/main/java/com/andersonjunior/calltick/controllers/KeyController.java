package com.andersonjunior.calltick.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.andersonjunior.calltick.models.Key;
import com.andersonjunior.calltick.services.KeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/key")
public class KeyController {

    @Autowired
    private KeyService keyService;

    @CrossOrigin
    @ApiOperation(value = "Retorna uma Key por código")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Key> findById(@PathVariable Long id) {
        Key obj = keyService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @CrossOrigin
    @ApiOperation(value = "Insere e retorna uma chave de liberação")
    @PostMapping()
    public ResponseEntity<Key> generateKey(@RequestParam() String cnpj, @RequestParam() String monthAndYear, @Valid @RequestBody Key key) {

        String completeDate = "";

        for(int i = 0; i < monthAndYear.length(); i++) {
            completeDate = monthAndYear.substring(3, 10);
        }

        String obj = keyService.gerarChave(cnpj, completeDate);
        key.setKey(obj);
        key.setCnpj(cnpj);

        key = keyService.insert(key);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(key.getId()).toUri();
        ResponseEntity.created(uri).build();

        return ResponseEntity.ok().body(key);

    }

}
