package com.example.padaria.cliente.model;

import com.example.padaria.endereco.model.Endereco;
import com.example.padaria.venda.model.Venda;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Date dataDeNascismento;
    private String telefone;
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;


}
