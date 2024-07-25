package com.example.padaria.endereco.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoForm {

    @NotEmpty(message = "Esse campo não pode estar vazio")
    private String rua;
    @NotEmpty(message = "Esse campo não pode estar vazio")
    private String bairro;
    @NotEmpty(message = "Esse campo não pode estar vazio")
    private String cep;
    @NotEmpty(message = "Esse campo não pode estar vazio")
    private String cidade;
    @NotEmpty(message = "Esse campo não pode estar vazio")
    private String estado;
    @NotEmpty(message = "Esse campo não pode estar vazio")
    private String numeroCasa;

}
