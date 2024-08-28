package com.example.padaria.produto.dto;

import com.example.padaria.produto.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProdutoEstoqueDTO {
    private Long id;
    private String nome;
    private int quantidadeEmEstoque;

    public ProdutoEstoqueDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.quantidadeEmEstoque = produto.getQuantidadeEmEstoque();
    }




    public static List<ProdutoEstoqueDTO> converter(List<Produto> produtos){
        return produtos.stream().map(ProdutoEstoqueDTO::new).collect(Collectors.toList());
    }
}
