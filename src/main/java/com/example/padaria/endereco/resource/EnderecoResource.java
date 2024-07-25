package com.example.padaria.endereco.resource;

import com.example.padaria.endereco.dto.EnderecoDTO;
import com.example.padaria.endereco.form.EnderecoForm;
import com.example.padaria.endereco.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@Valid @RequestBody EnderecoForm formulario){
        return this.enderecoService.cadastrarEndereco(formulario);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long idEndereco , @Valid @RequestBody EnderecoForm formulario  ){
        return this.enderecoService.atualizarEndereco(idEndereco, formulario);
    }
}
