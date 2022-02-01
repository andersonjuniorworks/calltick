package com.andersonjunior.calltick.controllers;

import java.net.URI;

import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Comment;
import com.andersonjunior.calltick.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @CrossOrigin
    @ApiOperation(value = "Retorna os comentários por chamado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Comment>> findByTicket(@PathVariable Called id) {
        List<Comment> list = service.findByCalled(id);
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todos os comentários")
    @GetMapping()
    public ResponseEntity<List<Comment>> findAll() {
        return new ResponseEntity<List<Comment>>(service.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere um comentário")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Comment obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
}
