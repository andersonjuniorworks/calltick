package com.andersonjunior.calltick.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.dto.ClientDto;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private ClientService clientService;

    @CrossOrigin
    @ApiOperation(value = "Retorna um cliente por código")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Client obj = clientService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todos os clientes")
    @GetMapping()
    public ResponseEntity<List<Client>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {

        Long count = clientService.count();
        Double total = count.doubleValue() / size;
        total = Math.ceil(total + 1) * 10;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", total.toString());

        List<Client> list = clientService.findAll(page, size);

        return ResponseEntity.ok().headers(responseHeaders).body(list);

    }

    @CrossOrigin
    @ApiOperation(value = "Retorna a quantidade de clientes no banco")
    @GetMapping(value = "/count")
    public ResponseEntity<Long> countRegisters() {
        Long obj = clientService.count();
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna um cliente por CPF ou CNPJ")
    @GetMapping(value = "/document")
    public ResponseEntity<List<Client>> findByDocument(@RequestParam(value = "value") String document) {
        return new ResponseEntity<List<Client>>(clientService.findByDocument(document), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna clientes por nome completo ou razão social")
    @GetMapping(value = "/fullname")
    public ResponseEntity<List<Client>> findByFullname(@RequestParam(value = "value") String fullname) {
        return new ResponseEntity<List<Client>>(clientService.findByFullname(fullname), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna clientes por apelido ou nome fantasia")
    @GetMapping(value = "/nickname")
    public ResponseEntity<List<Client>> findByNickname(@RequestParam(value = "value") String nickname) {
        return new ResponseEntity<List<Client>>(clientService.findByNickname(nickname), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna clientes por cidade")
    @GetMapping(value = "/city")
    public ResponseEntity<List<Client>> findByCity(@RequestParam(value = "value") String city) {
        return new ResponseEntity<List<Client>>(clientService.findByCity(city), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere um novo cliente")
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientDto objDto) {
        Client obj = clientService.fromDTO(objDto);
        obj = clientService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Edita um cliente")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Client client, @PathVariable Long id) {
        client.setId(id);
        clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Exclui um cliente")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

}
