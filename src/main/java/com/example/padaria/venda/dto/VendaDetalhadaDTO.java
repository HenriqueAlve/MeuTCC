package com.example.padaria.venda.dto;

import com.example.padaria.item.venda.dto.ItemVendaDetalhadaDTO;
import com.example.padaria.venda.model.Venda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class VendaDetalhadaDTO {
    private Long id;
    private LocalDateTime dataVenda;
    private String nomeUsuario;
    private int quantidade;
    private double totalVenda;
    private List<ItemVendaDetalhadaDTO> itensVenda;

    // Getters, Setters e Construtores

    public static VendaDetalhadaDTO fromVenda(Venda venda) {
        VendaDetalhadaDTO dto = new VendaDetalhadaDTO();
        dto.setId(venda.getId());
        dto.setDataVenda(venda.getDataVenda());
        dto.setNomeUsuario(venda.getUsuario().getNome()); // Agora deve funcionar
        dto.setQuantidade(venda.getQuantidade());
        dto.setTotalVenda(venda.getTotalVenda());

        List<ItemVendaDetalhadaDTO> itensDTO = venda.getItensVenda().stream()
                .map(ItemVendaDetalhadaDTO::fromItemVenda)
                .collect(Collectors.toList());
        dto.setItensVenda(itensDTO);

        return dto;
    }

}


