package com.example.padaria.venda.dto;

import com.example.padaria.item.venda.dto.ItemVendaDTO;

import java.time.LocalDateTime;
import java.util.List;

public record VendaDTODois(
        LocalDateTime dataVenda,
         int quantidade,
         int totalVenda,
         Long usuarioId,
         String nomeUsuario,
         List<ItemVendaDTO>itensVenda
) {
}
