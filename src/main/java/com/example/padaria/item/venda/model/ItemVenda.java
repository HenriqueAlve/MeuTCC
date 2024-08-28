package com.example.padaria.item.venda.model;

import com.example.padaria.produto.model.Produto;
import com.example.padaria.venda.model.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itemVenda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    private Integer quantidade;
    private double precoTotal;

}
