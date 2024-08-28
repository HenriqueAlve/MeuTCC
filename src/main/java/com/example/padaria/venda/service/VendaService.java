package com.example.padaria.venda.service;

import com.example.padaria.cliente.model.Cliente;
import com.example.padaria.cliente.repository.ClienteRepository;
import com.example.padaria.exception.handler.ObjetoNaoEncontradoException;
import com.example.padaria.item.venda.dto.ItemVendaDTO;
import com.example.padaria.item.venda.model.ItemVenda;
import com.example.padaria.item.venda.repository.ItemVendaRepository;
import com.example.padaria.produto.dto.ProdutoDTO;
import com.example.padaria.produto.model.Produto;
import com.example.padaria.produto.repository.ProdutoRepository;
import com.example.padaria.usuario.model.AuthenticationDto;
import com.example.padaria.usuario.model.UserDTO;
import com.example.padaria.usuario.model.Usuario;
import com.example.padaria.usuario.repository.UserRepository;
import com.example.padaria.venda.dto.VendaDTO;
import com.example.padaria.venda.dto.VendaDetalhadaDTO;
import com.example.padaria.venda.model.Venda;
import com.example.padaria.venda.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;





import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private UserRepository usuarioRepository;

//    public ResponseEntity<VendaDTO> vender(Long produtoId, int quantidade) {
//        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
//        if (!optionalProduto.isPresent()) {
//            throw new RuntimeException("Produto não encontrado");
//        }
//
//        Produto produto = optionalProduto.get();
//        if (quantidade > produto.getQuantidadeEmEstoque()) {
//            throw new RuntimeException("Quantidade em estoque insuficiente");
//        }
//
//        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);
//        produtoRepository.save(produto);
//
//        Venda venda = new Venda();
//        venda.setProduto(produto);
//        venda.setQuantidade(quantidade);
//        venda.setDataVenda(Date.valueOf(LocalDate.now()));
//        venda.setTotalVenda(produto.getPreco() * quantidade);
//
//        venda = vendaRepository.save(venda);
//
//        return ResponseEntity.ok(new VendaDTO(venda));
//
//    }




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

//    public ResponseEntity<VendaDTO> registrarVenda(List<ItemVenda> itemVendas) {
//        Venda vendas = new Venda();
//        vendas.setItens(itemVendas);
//        return ResponseEntity.ok(VendaDTO.converter(vendas));
//
//    }

//    public Venda registrarVenda(Venda venda) {
//        return null;
//    }


    @Transactional
    public VendaDetalhadaDTO registrarVenda(VendaDTO vendaDTO) {
        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());

        Usuario usuario = usuarioRepository.findById(vendaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        venda.setUsuario(usuario);

        List<ItemVenda> itensVenda = new ArrayList<>();
        venda.setQuantidade(0);
        venda.setTotalVenda(0.0);

        for (ItemVendaDTO itemDTO : vendaDTO.getItensVenda()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getQuantidadeEmEstoque() < itemDTO.getQuantidade()) {
                throw new RuntimeException("Quantidade em estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - itemDTO.getQuantidade());
            produtoRepository.save(produto);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemDTO.getQuantidade());
            itemVenda.setPrecoTotal(produto.getPreco() * itemDTO.getQuantidade());
            itemVenda.setVenda(venda);
            itensVenda.add(itemVenda);

            venda.setQuantidade(venda.getQuantidade() + itemDTO.getQuantidade());
            venda.setTotalVenda(venda.getTotalVenda() + itemVenda.getPrecoTotal());
        }

        venda.setItensVenda(itensVenda);
        vendaRepository.save(venda);


        return VendaDetalhadaDTO.fromVenda(venda);
    }



    public ResponseEntity<List<VendaDTO>> listarVendasMensais(int mes) {
        // Busque as vendas filtradas pelo mês
        List<Venda> vendas = vendaRepository.findVendasByMes(mes);

        // Mapeie as vendas para VendaDTO
        List<VendaDTO> vendasDTO = vendas.stream().map(VendaDTO::new).collect(Collectors.toList());

        // Retorne a lista de VendaDTOs como resposta
        return ResponseEntity.ok(vendasDTO);
    }
}
