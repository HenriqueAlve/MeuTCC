package com.example.padaria.endereco.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String numeroCasa;

}
