package com.example.padaria.item.venda.dto;

import com.example.padaria.item.venda.model.ItemVenda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaDTO {
    private Long produtoId;
    private int quantidade;

    public ItemVendaDTO() {

    }

    public ItemVendaDTO(ItemVenda itemVenda) {
        this.produtoId = itemVenda.getProduto().getId();
        this.quantidade = itemVenda.getQuantidade();
    }

    public ItemVendaDTO(ItemVendaDTO itemVenda) {
        this.produtoId = itemVenda.getProdutoId();
        this.quantidade = itemVenda.getQuantidade();
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

