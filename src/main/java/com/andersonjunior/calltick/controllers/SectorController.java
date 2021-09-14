package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.dto.SectorDto;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.services.SectorService;

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
@RequestMapping(value = "/api/sectors")
public class SectorController {

    @Autowired
    private SectorService service;

    @CrossOrigin
    @ApiOperation(value = "Retorna um setor por código")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Sector> findById(@PathVariable Integer id) {
        Sector obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todos os setores")
    @GetMapping()
    public ResponseEntity<List<Sector>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        return new ResponseEntity<List<Sector>>(service.findAll(page, size), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna a quantidade de setores no banco")
    @GetMapping(value = "/count")
    public ResponseEntity<Long> countRegisters() {
        Long obj = service.count();
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna um setor por descrição")
    @GetMapping(value = "/description")
    public ResponseEntity<List<Sector>> findSectorByDescription(@RequestParam(value = "value") String description) {
        return new ResponseEntity<List<Sector>>(service.findByDescription(description), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere um setor")
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody SectorDto objDto) {
        Sector obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Editar um setor")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Sector sector, @PathVariable Integer id) {
        sector.setId(id);
        service.update(sector);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Exclui um setor")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
