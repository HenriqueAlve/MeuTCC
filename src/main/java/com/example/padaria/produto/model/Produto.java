package com.example.padaria.produto.model;

import com.example.padaria.categoria.model.Categoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;
    private String nome;
    private Float preco;
    private int quantidadeEmEstoque;

    @Lob
    private byte[] imagem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;


}
