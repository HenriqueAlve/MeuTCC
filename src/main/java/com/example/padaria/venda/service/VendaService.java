package com.example.padaria.venda.service;

import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.cliente.repository.ClienteRepository;
import com.example.padaria.produto.dto.ProdutoDTO;
import com.example.padaria.produto.model.Produto;
import com.example.padaria.produto.repository.ProdutoRepository;
import com.example.padaria.venda.dto.VendaDTO;
import com.example.padaria.venda.model.Venda;
import com.example.padaria.venda.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;





import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<VendaDTO> vender(Long produtoId, int quantidade) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        if (!optionalProduto.isPresent()) {
            throw new RuntimeException("Produto não encontrado");
        }

        Produto produto = optionalProduto.get();
        if (quantidade > produto.getQuantidadeEmEstoque()) {
            throw new RuntimeException("Quantidade em estoque insuficiente");
        }

        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);
        produtoRepository.save(produto);

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setQuantidade(quantidade);
        venda.setDataVenda(Date.valueOf(LocalDate.now()));
        venda.setTotalVenda(produto.getPreco() * quantidade);

        venda = vendaRepository.save(venda);

        return ResponseEntity.ok(new VendaDTO(venda));

    }



    public ResponseEntity<List<VendaDTO>> listarVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        return ResponseEntity.ok(VendaDTO.converter(vendas));
    }

    public Double getTotalVendas() {
        return vendaRepository.findTotalVendas();
    }

    public Long getTotalPedidos() {
        return vendaRepository.findTotalPedidos();
    }
    public Double getTotalVendasDiarias() {
        LocalDate today = LocalDate.now();
        Date sqlDate = Date.valueOf(today);
        List<Venda> vendas = vendaRepository.findByDataVenda(sqlDate);
        return vendas.stream().mapToDouble(Venda::getTotalVenda).sum();
    }

    public Double getTotalVendasMensais() {
        YearMonth thisMonth = YearMonth.now();
        int year = thisMonth.getYear();
        int month = thisMonth.getMonthValue();
        List<Venda> vendas = vendaRepository.findByDataVendaMes(year, month);
        return vendas.stream().mapToDouble(Venda::getTotalVenda).sum();
    }
}
