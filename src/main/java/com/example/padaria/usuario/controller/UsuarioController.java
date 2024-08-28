package com.example.padaria.usuario.controller;

import com.example.padaria.security.SecurityService;
import com.example.padaria.usuario.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/registerFuncionario")
    public ResponseEntity<String> registerFuncionario(@RequestBody UserDTO funcionario){
        return ResponseEntity.status(HttpStatus.OK).body(service.registerFuncionario(funcionario));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto data){
        return ResponseEntity.status(HttpStatus.OK).body(service.login(data));
    }

    @GetMapping("/lista/clientes")
    public ResponseEntity<List<Usuario>> listarClientes(){
        return this.service.listarClientes();
    }

}
