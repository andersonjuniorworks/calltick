package com.andersonjunior.calltick.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.andersonjunior.calltick.dto.EmailDto;
import com.andersonjunior.calltick.security.JwtUtil;
import com.andersonjunior.calltick.security.UserSpringSecurity;
import com.andersonjunior.calltick.services.AuthService;
import com.andersonjunior.calltick.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSpringSecurity user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto emailDto) {
        authService.sendNewPassword(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }

}
