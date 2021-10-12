package com.andersonjunior.calltick.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.andersonjunior.calltick.dto.UserDto;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @CrossOrigin
    @ApiOperation(value = "Retorna um usuário por código")
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = userService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna todos os usuários")
    @GetMapping()
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        return new ResponseEntity<List<User>>(userService.findAll(page, size), HttpStatus.OK);
    }

    
    @CrossOrigin
    @ApiOperation(value = "Retorna a quantidade de usuários no banco")
    @GetMapping(value = "/count")
    public ResponseEntity<Long> countRegisters() {
        Long obj = userService.count();
        return ResponseEntity.ok().body(obj);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna usuários por email")
    @GetMapping(value = "/email")
    public ResponseEntity<User> findByEmail(@RequestParam(value = "value") String email) {
        return new ResponseEntity<User>(userService.findByEmail(email), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Retorna usuários por nome completo")
    @GetMapping(value = "/fullname")
    public ResponseEntity<List<User>> findByFullname(@RequestParam(value = "value") String fullname) {
        return new ResponseEntity<List<User>>(userService.findByFullname(fullname), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere um novo usuário")
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody UserDto objDto) {
        User obj = userService.fromDTO(objDto);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String email, @RequestParam String password) {
        User optUser = userService.findByEmail(email);
        if (optUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        boolean valid = encoder.matches(password, optUser.getPassword());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

    @CrossOrigin
    @ApiOperation(value = "Edita um usuário")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Exclui um usuário")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
