package com.example.padaria.endereco.dto;

import com.example.padaria.cliente.dto.ClienteDTO;
import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.endereco.model.Endereco;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@Getter
@Setter
public class EnderecoDTO {

    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String numeroCasa;

    public EnderecoDTO(Endereco enderecos){
        this.rua = enderecos.getRua();
        this.bairro = enderecos.getBairro();
        this.cep = enderecos.getCep();
        this.cidade = enderecos.getCidade();
        this.estado = enderecos.getEstado();
        this.numeroCasa = enderecos.getNumeroCasa();
    }
    public static List<EnderecoDTO> converter(List<Endereco> enderecos) {
        return enderecos.stream().map(EnderecoDTO::new).collect(Collectors.toList());
    }

}
