package com.example.padaria.usuario.model;


import java.util.Date;

public record UserDTO(String username, String password, UserRole role, String nome , String cpf,String telefone) {
}
