package com.example.padaria.venda.resource;

import com.example.padaria.venda.dto.VendaDTO;
import com.example.padaria.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaResource {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/vender/{produtoId}/{quantidade}")
    public ResponseEntity<VendaDTO> vender(
            @PathVariable Long produtoId,
            @PathVariable int quantidade
            ) {
        return vendaService.vender(produtoId, quantidade);
    }

    @GetMapping("/ver/vendas")
    public ResponseEntity<List<VendaDTO>> listarVendas(){
        return this.vendaService.listarVendas();
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
