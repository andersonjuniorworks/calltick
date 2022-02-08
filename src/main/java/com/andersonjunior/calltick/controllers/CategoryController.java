package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.andersonjunior.calltick.models.Category;
import com.andersonjunior.calltick.services.CategoryService;

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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna uma categoria por código")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todas as categorias")
    @GetMapping
    public ResponseEntity<List<Category>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        List<Category> list = categoryService.findAll(page, size);
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere uma nova categoria")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Category category) {
        category = categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Edita uma categoria")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Category category, @PathVariable Long id) {
        category.setId(id);
        categoryService.update(category);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Exclui uma categoria")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
