package com.example.padaria.item.venda.dto;

import com.example.padaria.item.venda.model.ItemVenda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaDetalhadaDTO {
    private String nomeProduto;
    private int quantidade;
    private double precoTotal;

    // Getters, Setters e Construtores

    public static ItemVendaDetalhadaDTO fromItemVenda(ItemVenda itemVenda) {
        ItemVendaDetalhadaDTO dto = new ItemVendaDetalhadaDTO();
        dto.setNomeProduto(itemVenda.getProduto().getNome());
        dto.setQuantidade(itemVenda.getQuantidade());
        dto.setPrecoTotal(itemVenda.getPrecoTotal());
        return dto;
    }
}
