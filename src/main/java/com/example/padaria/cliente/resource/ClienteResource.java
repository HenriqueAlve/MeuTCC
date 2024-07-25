package com.example.padaria.cliente.resource;

import com.example.padaria.cliente.dto.ClienteDTO;
import com.example.padaria.cliente.form.ClienteForm;
import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> listarTodosClientes(){
        return this.clienteService.listarTodosClientes();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDTO> cadastrarCliente(@Valid @RequestBody ClienteForm formualario){
        return clienteService.cadastrarCliente(formualario);
    }


}
