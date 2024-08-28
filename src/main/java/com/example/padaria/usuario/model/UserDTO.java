package com.example.padaria.usuario.model;


import com.example.padaria.endereco.model.Endereco;

import java.util.Date;

public record UserDTO(String username, String password, UserRole role, String nome , String cpf, String telefone, Endereco endereco) {
}
