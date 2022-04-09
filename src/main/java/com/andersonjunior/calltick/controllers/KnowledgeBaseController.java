package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.models.KnowledgeBase;
import com.andersonjunior.calltick.services.KnowledgeBaseService;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/knowledges")
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    @Autowired
    public KnowledgeBaseController(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna um conhecimento por código")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<KnowledgeBase> findById(@PathVariable Long id) {
        KnowledgeBase obj = knowledgeBaseService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna um registro por descrição")
    @GetMapping(value = "/description")
    public ResponseEntity<List<KnowledgeBase>> findByDescription(@RequestParam(name = "value", required = true) String description) {
        List<KnowledgeBase> list = knowledgeBaseService.findByDescription(description);
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todos os registros na base de conhecimento")
    @GetMapping
    public ResponseEntity<List<KnowledgeBase>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        List<KnowledgeBase> list = knowledgeBaseService.findAll(page, size);
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere um novo registro na base de conhecimento")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody KnowledgeBase knowledgeBase) {
        knowledgeBase = knowledgeBaseService.insert(knowledgeBase);
        System.out.println("AQUI: "+knowledgeBase);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(knowledgeBase.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Edita um registro na base de conhecimento")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody KnowledgeBase knowledgeBase, @PathVariable Long id) {
        knowledgeBase.setId(id);
        knowledgeBaseService.update(knowledgeBase);
        return ResponseEntity.noContent().build();
    }

    
}
