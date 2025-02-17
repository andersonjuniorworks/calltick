package com.andersonjunior.calltick.controllers;

import java.net.URI;

import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.dto.ClientDto;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Contract;
import com.andersonjunior.calltick.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Client obj = clientService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todos os clientes")
    @GetMapping()
    public ResponseEntity<List<Client>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        List<Client> list = clientService.findAll(page, size);
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna clientes por filtro")
    @GetMapping(value = "/filter")
    public ResponseEntity<List<Client>> findByFilter(
            @RequestParam(value = "document", required = false) String document,
            @RequestParam(value = "fullname", required = false) String fullname,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "contract", required = false) Contract contract) {

        List<Client> list = clientService.findByFilter(document, fullname, nickname, city, contract);
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna total de clientes por filtro")
    @GetMapping(value = "/countByFilter")
    public ResponseEntity<Integer> countByFilter(@RequestParam(value = "document", required = false) String document,
            @RequestParam(value = "fullname", required = false) String fullname,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "contract", required = false) Contract contract) {

        Integer count = clientService.countByFilter(document, fullname, nickname, city, contract);
        return ResponseEntity.ok().body(count);

    }

    @CrossOrigin
    @ApiOperation(value = "Retorna a quantidade de clientes no banco")
    @GetMapping(value = "/count")
    public ResponseEntity<Long> countRegisters() {
        Long obj = clientService.count();
        return ResponseEntity.ok().body(obj);
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
