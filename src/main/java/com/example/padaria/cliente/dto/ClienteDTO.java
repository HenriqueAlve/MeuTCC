package com.example.padaria.cliente.dto;


import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.endereco.model.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Date dataDeNascismento;
    private String telefone;
    private Endereco endereco;

    public ClienteDTO(Cliente clientes){
        this.id = clientes.getId();
        this.nome = clientes.getNome();
        this.cpf = clientes.getCpf();
        this.email = clientes.getEmail();
        this.dataDeNascismento = clientes.getDataDeNascismento();
        this.telefone = clientes.getTelefone();
        this.endereco = clientes.getEndereco();
    }

    public static List<ClienteDTO> converter(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }
}
