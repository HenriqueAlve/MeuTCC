package com.example.padaria.produto.model;

import com.example.padaria.categoria.model.Categoria;
import com.example.padaria.produto.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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



    public static List<Produto> converterDTO(List<ProdutoDTO> produtosDTO){
        return produtosDTO.stream().map(produtosDTO1 -> new Produto()).collect(Collectors.toList());
    }

}
