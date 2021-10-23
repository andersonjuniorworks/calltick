package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.dto.CompanyDto;
import com.andersonjunior.calltick.models.Company;
import com.andersonjunior.calltick.services.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

/*     @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    } */

    @CrossOrigin
    @ApiOperation(value = "Retorna uma empresa por código")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Company obj = companyService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todas as empresas")
    @GetMapping()
    public ResponseEntity<List<Company>> findAll() {

        List<Company> list = companyService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @CrossOrigin
    @ApiOperation(value = "Insere uma nova empresa")
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody CompanyDto objDto) {
        Company obj = companyService.fromDTO(objDto);
        obj = companyService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Edita uma empresa")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Company company, @PathVariable Long id) {
        company.setId(id);
        companyService.update(company);
        return ResponseEntity.noContent().build();
    }
    
}
