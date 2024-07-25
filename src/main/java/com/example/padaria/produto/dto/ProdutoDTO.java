package com.example.padaria.produto.dto;

import com.example.padaria.categoria.dto.CategoriaDTO;
import com.example.padaria.produto.model.Produto;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Float preco;
    private int quantiadeEmEstoque;
    @Lob
    private byte[] imagem;
    private CategoriaDTO categoriaDTO;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantiadeEmEstoque = produto.getQuantidadeEmEstoque();
        this.imagem = produto.getImagem();
        this.categoriaDTO = new CategoriaDTO(produto.getCategoria());
    }

    public static List<ProdutoDTO> converter(List<Produto> produtos){
        return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

}
