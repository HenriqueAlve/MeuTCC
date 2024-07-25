package com.example.padaria.categoria.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaForm {
    @NotEmpty(message = "Esse campo não pode ser vazio")
    private String nome;
}
