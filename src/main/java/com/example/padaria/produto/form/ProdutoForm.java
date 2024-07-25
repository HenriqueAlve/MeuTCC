package com.example.padaria.produto.form;

import com.example.padaria.categoria.model.Categoria;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoForm {

    @NotNull
    private String nome;
    @NotNull
    private Float preco;
    @NotNull
    private int quantidadeEmEstoque;
    @Lob
    private byte[] imagem;
    @NotNull
    private Categoria categoria;
}
