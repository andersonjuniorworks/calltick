package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.models.Paid;
import com.andersonjunior.calltick.services.PaidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/api/paids")
public class PaidController {

    @Autowired
    private PaidService paidService;

    @ApiOperation(value = "Retorna todos os pagamentos")
    @GetMapping(produces="application/json")
    public ResponseEntity<List<Paid>> findAll(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "5") Integer size) {
        return new ResponseEntity<List<Paid>>(paidService.findAll(page, size), HttpStatus.OK);
    }

    @ApiOperation(value = "Insere um novo pagamento")
    @PostMapping(produces="application/json")
    public ResponseEntity<Void> insert(@Valid @RequestBody Paid obj) {
        obj = paidService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Exclui um pagamento")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paidService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
