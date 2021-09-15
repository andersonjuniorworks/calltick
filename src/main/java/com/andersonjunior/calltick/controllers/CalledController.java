package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.dto.CalledDto;
import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.services.CalledService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "/api/calls")
public class CalledController {

    private CalledService service;

    @Autowired
    public CalledController(CalledService service) {
        this.service = service;
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna um chamado por código")
    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<Called> findById(@PathVariable Long id) {
        Called obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todos os chamados")
    @GetMapping(produces="application/json")
    public ResponseEntity<List<Called>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        return new ResponseEntity<List<Called>>(service.findAll(page, size), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna a quantidade de chamados no banco")
    @GetMapping(value = "/count")
    public ResponseEntity<Long> countRegisters() {
        Long obj = service.count();
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna uma lista de chamados pelo status e situação cadastral")
    @GetMapping(value = "/user", produces="application/json")
    public ResponseEntity<List<Called>> findByUser(
    @RequestParam(required = false) User user,
    @RequestParam(required = false, defaultValue = "1") Integer status,
    @RequestParam(required = false, defaultValue = "0") Integer active,
    @RequestParam(required = false, defaultValue = "0") Integer page,
    @RequestParam(required = false, defaultValue = "5") Integer size) {
        return new ResponseEntity<List<Called>>(service.findByUser(user, status, active, page, size), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna uma lista de chamados pelo status e situação cadastral")
    @GetMapping(value = "/all", produces="application/json")
    public ResponseEntity<List<Called>> findByStatus(
    @RequestParam(required = false, defaultValue = "1") Integer status,
    @RequestParam(required = false, defaultValue = "0") Integer active,
    @RequestParam(required = false, defaultValue = "0") Integer page,
    @RequestParam(required = false, defaultValue = "5") Integer size) {
        return new ResponseEntity<List<Called>>(service.findAllCalls(status, active, page, size), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna uma lista de chamados por periodo")
    @GetMapping(value = "/period", produces="application/json")
    public ResponseEntity<List<Called>> findByPeriod(
    @RequestParam(required = true) String startDate,
    @RequestParam(required = true) String endDate,
    @RequestParam(required = false, defaultValue = "0") Integer page,
    @RequestParam(required = false, defaultValue = "5") Integer size) {
        return new ResponseEntity<List<Called>>(service.findByPeriod(startDate, endDate, page, size), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna uma lista de chamados por cliente")
    @GetMapping(value = "/client", produces="application/json")
    public ResponseEntity<List<Called>> findByClient(
        @RequestParam(required = true) Client client,
        @RequestParam(required = false, defaultValue = "1") Integer status,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return new ResponseEntity<List<Called>>(service.findByClient(client, status, page, size), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Inserir chamado")
    @PostMapping(value = "/insert", produces="application/json")
    public ResponseEntity<Void> insert(@Valid @RequestBody CalledDto objDto) {
        Called obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Editar chamado")
    @PutMapping(value = "/update/{id}", produces="application/json")
    public ResponseEntity<Void> update(@Valid @RequestBody Called called, @PathVariable Long id) {
        called.setId(id);
        service.update(called);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Excluir chamado")
    @PutMapping(value = "/delete/{id}", produces="application/json")
    public ResponseEntity<Void> delete(@Valid @RequestBody Called called, @PathVariable Long id) {
        called.setId(id);
        service.delete(called);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Finalizar chamado")
    @PutMapping(value = "/finish/{id}", produces="application/json")
    public ResponseEntity<Void> finish(@Valid @RequestBody Called called, @PathVariable Long id) {
        called.setId(id);
        service.finishCalled(called);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Transferir chamado")
    @PutMapping(value = "/transfer/{id}", produces="application/json")
    public ResponseEntity<Void> transfer(@Valid @RequestBody Called called, @PathVariable Long id) {
        called.setId(id);
        service.transfer(called);
        return ResponseEntity.ok().build();
    }

}
