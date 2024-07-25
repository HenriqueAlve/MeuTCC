package com.example.padaria.endereco.service;

import com.example.padaria.endereco.dto.EnderecoDTO;
import com.example.padaria.endereco.form.EnderecoForm;
import com.example.padaria.endereco.model.Endereco;
import com.example.padaria.endereco.repository.EnderecoRepository;
import com.example.padaria.exception.handler.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    public ResponseEntity<EnderecoDTO> cadastrarEndereco(EnderecoForm formulario) {
        Endereco endereco = new Endereco();
        endereco.setRua(formulario.getRua());
        endereco.setBairro(formulario.getBairro());
        endereco.setCep(formulario.getCep());
        endereco.setCidade(formulario.getCidade());
        endereco.setEstado(formulario.getEstado());
        endereco.setNumeroCasa(formulario.getNumeroCasa());
        endereco = enderecoRepository.save(endereco);
        return ResponseEntity.ok(new EnderecoDTO(endereco));

    }

    public ResponseEntity<EnderecoDTO> atualizarEndereco(Long idEndereco, EnderecoForm formulario) {
        Endereco endereco = this.buscarEndereco(idEndereco);
        endereco.setRua(formulario.getRua());
        endereco.setBairro(formulario.getBairro());
        endereco.setCep(formulario.getCep());
        endereco.setCidade(formulario.getCidade());
        endereco.setEstado(formulario.getEstado());
        endereco.setNumeroCasa(formulario.getNumeroCasa());
        endereco = enderecoRepository.save(endereco);
        return ResponseEntity.ok(new EnderecoDTO(endereco));
    }

    public Endereco buscarEndereco(Long idEndereco){
        Optional<Endereco> optionalEndereco = this.enderecoRepository.findById(idEndereco);
        if(optionalEndereco.isEmpty()){
            throw new ObjetoNaoEncontradoException("Endereço com id "+idEndereco+" não encontrado!");
        }
        return optionalEndereco.get();
    }
}
