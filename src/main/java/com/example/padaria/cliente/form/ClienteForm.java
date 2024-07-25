package com.example.padaria.cliente.form;

import com.example.padaria.endereco.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClienteForm {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O cpf é obrigatório")
    private String cpf;
    @NotBlank(message = "O email é obrigatório")
    private String email;
    @NotNull(message = "A data de nascimento é obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataDeNascismento;
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

}

