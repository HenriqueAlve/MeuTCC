package com.example.padaria.venda.model;

import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.item.venda.model.ItemVenda;
import com.example.padaria.produto.model.Produto;
import com.example.padaria.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venda")
@Getter
@Setter
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda")
    private Long id;
    private LocalDateTime dataVenda;
    private int quantidade;
    private double totalVenda;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itensVenda;

    @ManyToOne
    @JoinColumn(name = "usuario_id")  // Defina a chave estrangeira
    private Usuario usuario;

}
