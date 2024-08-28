package com.example.padaria.venda.dto;

import com.example.padaria.item.venda.dto.ItemVendaDTO;
import com.example.padaria.venda.model.Venda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class VendaDTO {

    private LocalDateTime dataVenda;
    private int quantidade;
    private double totalVenda;
    private Long usuarioId;
    private String nomeUsuario;
    private List<ItemVendaDTO> itensVenda;

    public VendaDTO() {
        // Construtor padrão
    }

    public VendaDTO(Venda venda) {
        this.dataVenda = venda.getDataVenda();
        this.quantidade = venda.getQuantidade();
        this.totalVenda = venda.getTotalVenda();
        this.usuarioId = venda.getUsuario().getId();
        this.nomeUsuario = venda.getUsuario().getNome();
        this.itensVenda = venda.getItensVenda().stream()
                .map(ItemVendaDTO::new)
                .collect(Collectors.toList());
    }

    public VendaDTO(VendaDTODois venda, Long id, String nome) {
        this.dataVenda = venda.dataVenda();
        this.quantidade = venda.quantidade();
        this.totalVenda = venda.totalVenda();
        this.usuarioId = id;
        this.nomeUsuario = nome;
        this.itensVenda = venda.itensVenda().stream()
                .map(ItemVendaDTO::new)
                .collect(Collectors.toList());
    }

    public static List<VendaDTO> converter(List<Venda> vendas) {
        return vendas.stream().map(VendaDTO::new).collect(Collectors.toList());
    }
}
