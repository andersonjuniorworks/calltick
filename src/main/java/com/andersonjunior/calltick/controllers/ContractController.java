package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.dto.ContractDto;
import com.andersonjunior.calltick.models.Contract;
import com.andersonjunior.calltick.services.ContractService;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/contracts")
public class ContractController {

    @Autowired
    private ContractService service;

    @CrossOrigin
    @ApiOperation(value = "Retorna um contrato por código")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Contract> findById(@PathVariable Long id) {
        Contract obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todos os contratos")
    @GetMapping()
    public ResponseEntity<List<Contract>> findAll() {
        return new ResponseEntity<List<Contract>>(service.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna a quantidade de contratos no banco")
    @GetMapping(value = "/count")
    public ResponseEntity<Long> countRegisters() {
        Long obj = service.count();
        return ResponseEntity.ok().body(obj);
    }

/*     @CrossOrigin
    @ApiOperation(value = "Retorna um contrato por descrição")
    @GetMapping(value = "/description")
    public ResponseEntity<List<Contract>> findContractByDescription(@RequestParam(value = "value") String description) {
        return new ResponseEntity<List<Contract>>(service.findByDescription(description), HttpStatus.OK);
    } */

    @CrossOrigin
    @ApiOperation(value = "Insere um contrato")
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody ContractDto objDto) {
        Contract obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Edita um contrato")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Contract contract, @PathVariable Long id) {
        contract.setId(id);
        service.update(contract);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Exclui um contrato")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
