package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.dto.ClientDto;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API Rest")
@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @ApiOperation(value = "Retorna um cliente por código")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        Client obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Retorna todos os clientes")
    @GetMapping()
    public ResponseEntity<List<Client>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        return new ResponseEntity<List<Client>>(service.findAll(page, size), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna clientes por nome completo")
    @GetMapping(value = "/fullname")
    public ResponseEntity<List<Client>> findByFullname(@RequestParam(value = "filter") String fullname) {
        return new ResponseEntity<List<Client>>(service.findByFullname(fullname), HttpStatus.OK);
    }

    @ApiOperation(value = "Insere um novo cliente")
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientDto objDto) {
        Client obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Edita um cliente")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Client client, @PathVariable Integer id) {
        client.setId(id);
        service.update(client);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Exclui um cliente")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
