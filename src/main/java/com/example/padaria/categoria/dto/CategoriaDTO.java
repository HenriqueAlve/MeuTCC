package com.example.padaria.categoria.dto;

import com.example.padaria.categoria.model.Categoria;
import com.example.padaria.produto.dto.ProdutoDTO;
import com.example.padaria.produto.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoriaDTO {
    private Long id;
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public static List<CategoriaDTO> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }
}
