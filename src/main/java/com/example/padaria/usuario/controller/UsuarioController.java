package com.example.padaria.usuario.controller;

import com.example.padaria.security.SecurityService;
import com.example.padaria.usuario.model.AuthenticationDto;
import com.example.padaria.usuario.model.LoginResponseDto;
import com.example.padaria.usuario.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    SecurityService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO data){
        System.out.println("chegou");
        return ResponseEntity.status(HttpStatus.OK).body(service.register(data));
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO data){
        System.out.println("chegou");
        return ResponseEntity.status(HttpStatus.OK).body(service.registerUser(data));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto data){
        return ResponseEntity.status(HttpStatus.OK).body(service.login(data));
    }

}
