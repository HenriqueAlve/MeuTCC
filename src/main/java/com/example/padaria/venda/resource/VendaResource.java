package com.example.padaria.venda.resource;

import com.example.padaria.usuario.model.Usuario;
import com.example.padaria.usuario.repository.UserRepository;
import com.example.padaria.venda.dto.VendaDTO;
import com.example.padaria.venda.dto.VendaDTODois;
import com.example.padaria.venda.dto.VendaDetalhadaDTO;
import com.example.padaria.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaResource {

    @Autowired
    private VendaService vendaService;
    @Autowired
    UserRepository userRepository;

//    @PostMapping("/registrarVenda")
//    public ResponseEntity<VendaDTO> registrarVenda(@RequestBody List<ItemVenda> itemVendas) {
//        return this.vendaService.registrarVenda(itemVendas);tttttttttttttttttttttt
//    }

    @PostMapping("/registrarVendas")
    public ResponseEntity<VendaDetalhadaDTO> registrarVenda(@RequestBody VendaDTODois param) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nome;

        if (principal instanceof UserDetails){
            nome = ((UserDetails)principal).getUsername();
        } else {
            nome = principal.toString();
        }
        Usuario usuario = userRepository.getByUsername(nome);
        System.out.println(nome);
        System.out.println(usuario.getId());

        VendaDTO newVenda = new VendaDTO(param, usuario.getId(), nome);
        System.out.println(param);
        System.out.println("Nova venda: " + newVenda);
        VendaDetalhadaDTO vendaDetalhadaDTO = vendaService.registrarVenda(newVenda);
        return new ResponseEntity<>(vendaDetalhadaDTO, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    @Transactional
    public ResponseEntity<List<VendaDTO>> listarVendas(){
        return this.vendaService.listarVendas();
    }

    @GetMapping("/mes/{mes}")
    @Transactional
    public ResponseEntity<List<VendaDTO>> listarVendasMensais(@PathVariable int mes) {
        return this.vendaService.listarVendasMensais(mes);
    }
    @GetMapping("/total-vendas")
    public ResponseEntity<Double> getTotalVendas() {
        Double totalVendas = vendaService.getTotalVendas();
        return ResponseEntity.ok(totalVendas);
    }

    @GetMapping("/total-pedidos")
    public ResponseEntity<Long> getTotalPedidos() {
        Long totalPedidos = vendaService.getTotalPedidos();
        return ResponseEntity.ok(totalPedidos);
    }
    @GetMapping("/total-vendas-diarias")
    public ResponseEntity<Double> getTotalVendasDiarias() {
        Double totalVendasDiarias = vendaService.getTotalVendasDiarias();
        return ResponseEntity.ok(totalVendasDiarias);
    }

    @GetMapping("/total-vendas-mensais")
    public ResponseEntity<Double> getTotalVendasMensais() {
        try {
            Double totalVendasMensais = vendaService.getTotalVendasMensais();
            return ResponseEntity.ok(totalVendasMensais);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(500).body(null);
        }
    }
}
