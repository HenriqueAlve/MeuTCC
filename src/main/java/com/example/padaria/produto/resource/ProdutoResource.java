package com.example.padaria.produto.resource;

import com.example.padaria.produto.dto.ProdutoDTO;
import com.example.padaria.produto.dto.ProdutoEstoqueDTO;
import com.example.padaria.produto.form.ProdutoForm;
import com.example.padaria.produto.model.Produto;
import com.example.padaria.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){

        return this.produtoService.listarProdutos();
    }

    @GetMapping("/estoque")
    public ResponseEntity<List<ProdutoEstoqueDTO>> listarEstoque( ){
        return this.produtoService.listarEstoque();
    }


    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> listarProduto(@PathVariable Long idProduto){
        return this.produtoService.listarProduto(idProduto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@Valid @RequestBody ProdutoForm formulario){
        return this.produtoService.cadastrarProduto(formulario);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO atualizadoProduto = produtoService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.ok(atualizadoProduto);
    }
    @DeleteMapping("/deletar/{idProduto}")
    public ResponseEntity<ProdutoDTO> deletarProduto(@PathVariable Long idProduto){
        return this.produtoService.deletarProduto(idProduto);
    }


    @DeleteMapping("/produtos_categoria/{idCategoria}")
    public ResponseEntity<List<ProdutoDTO>> deletarProdutosPeloIdDaCategoria(@PathVariable Long idCategoria){
        return this.produtoService.deletarProdutosPeloIdDaCategoria(idCategoria);
    }

    @GetMapping("/produtos_categoria/{idCategoria}")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPeloIdDaCategoria(@PathVariable Long idCategoria){
        return this.produtoService.listarProdutosPeloIdDaCategoria(idCategoria);
    }

    @GetMapping("/estoqueBaixo")
    @Transactional
    public ResponseEntity<List<ProdutoEstoqueDTO>> listarProdutosComEstoqueBaixo(){
        return produtoService.listarProdutosComEstoqueBaixo();
    }
}

