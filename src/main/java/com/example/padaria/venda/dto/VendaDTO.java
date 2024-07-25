package com.example.padaria.venda.dto;

import com.example.padaria.cliente.dto.ClienteDTO;
import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.produto.dto.ProdutoDTO;
import com.example.padaria.produto.model.Produto;
import com.example.padaria.venda.model.Venda;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class VendaDTO {
    private Long id;
    private Date dataVenda;
    private int quantidade;
    private double totalVenda;
    private Produto produto;
    private Cliente cliente;

    public VendaDTO(Venda venda) {
        this.id = venda.getId();
        this.dataVenda = venda.getDataVenda();
        this.quantidade = venda.getQuantidade();
        this.totalVenda = venda.getTotalVenda();
        this.produto = venda.getProduto();
        this.cliente = venda.getCliente();
    }

    public static List<VendaDTO> converter(List<Venda> vendas) {
        return vendas.stream().map(VendaDTO::new).collect(Collectors.toList());
    }
}
