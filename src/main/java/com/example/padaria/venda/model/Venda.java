package com.example.padaria.venda.model;

import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.produto.model.Produto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "venda")
@Getter
@Setter
public class Venda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda")
    private Long id;
    private Date dataVenda;
    private int quantidade;
    private double totalVenda;
    @ManyToOne
    private Produto produto;

}
