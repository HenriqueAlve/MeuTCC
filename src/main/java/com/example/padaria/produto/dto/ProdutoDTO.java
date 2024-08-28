package com.example.padaria.produto.dto;

import com.example.padaria.categoria.dto.CategoriaDTO;
import com.example.padaria.produto.model.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private int quantidadeEmEstoque; // Corrigido o nome do campo
    @Lob
    private byte[] imagem; // Corrigido o tipo para byte[]
    private CategoriaDTO categoriaDTO;

    @JsonCreator
    public ProdutoDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("nome") String nome,
            @JsonProperty("preco") Float preco,
            @JsonProperty("quantidadeEmEstoque") Integer quantidadeEmEstoque, // Corrigido o nome do parâmetro
            @JsonProperty("imagem") byte[] imagem, // Corrigido o tipo para byte[]
            @JsonProperty("categoriaDTO") CategoriaDTO categoriaDTO
    ) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque; // Corrigido o nome do parâmetro
        this.imagem = imagem;
        this.categoriaDTO = categoriaDTO;
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidadeEmEstoque = produto.getQuantidadeEmEstoque(); // Corrigido o nome do método
        this.imagem = produto.getImagem();
        this.categoriaDTO = new CategoriaDTO(produto.getCategoria());
    }

    public static List<ProdutoDTO> converter(List<Produto> produtos){
        return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }


}
