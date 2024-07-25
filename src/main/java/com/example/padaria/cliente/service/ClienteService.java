package com.example.padaria.cliente.service;

import com.example.padaria.cliente.dto.ClienteDTO;
import com.example.padaria.cliente.form.ClienteForm;
import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<List<ClienteDTO>> listarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(ClienteDTO.converter(clientes));
    }

    public ResponseEntity<ClienteDTO> cadastrarCliente(ClienteForm formualario) {
        Cliente cliente = new Cliente();
        cliente.setNome(formualario.getNome());
        cliente.setCpf(formualario.getCpf());
        cliente.setEmail(formualario.getEmail());
        cliente.setDataDeNascismento(formualario.getDataDeNascismento());
        cliente.setTelefone(formualario.getTelefone());
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(new ClienteDTO(cliente));
    }
}
