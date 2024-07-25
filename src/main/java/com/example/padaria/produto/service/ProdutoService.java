package com.example.padaria.produto.service;


import com.example.padaria.categoria.model.Categoria;
import com.example.padaria.categoria.repository.CategoriaRepository;
import com.example.padaria.categoria.service.CategoriaService;
import com.example.padaria.exception.handler.ObjetoNaoEncontradoException;
import com.example.padaria.produto.dto.ProdutoDTO;
import com.example.padaria.produto.form.ProdutoForm;
import com.example.padaria.produto.model.Produto;
import com.example.padaria.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return ResponseEntity.ok(ProdutoDTO.converter(produtos));
    }

    public ResponseEntity<ProdutoDTO> listarProduto(Long idProduto){
        Produto produto = this.buscarProduto(idProduto);
        return ResponseEntity.ok(new ProdutoDTO(produto));
    }

    public ResponseEntity<ProdutoDTO> cadastrarProduto(ProdutoForm formulario){
            Produto produto = new Produto();
            produto.setNome(formulario.getNome());
            produto.setPreco(formulario.getPreco());
            produto.setQuantidadeEmEstoque(formulario.getQuantidadeEmEstoque());
            produto.setImagem(formulario.getImagem());
            produto.setCategoria(formulario.getCategoria());
            produto = produtoRepository.save(produto);
        return ResponseEntity.ok(new ProdutoDTO(produto));
    }

    @Transactional
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não encontrado com id: " + id));

        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidadeEmEstoque(produtoDTO.getQuantiadeEmEstoque());

        if (produtoDTO.getCategoriaDTO() != null) {
            Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaDTO().getId())
                    .orElseThrow(() -> new ObjetoNaoEncontradoException("Categoria não encontrada com id: " + produtoDTO.getCategoriaDTO().getId()));
            produto.setCategoria(categoria);
        }

        Produto produtoAtualizado = produtoRepository.save(produto);
        return new ProdutoDTO(produtoAtualizado);
    }


    public ResponseEntity<ProdutoDTO> deletarProduto(Long idProduto){
        Produto produto = buscarProduto(idProduto);
        this.produtoRepository.deleteById(produto.getId());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<ProdutoDTO>> listarProdutosPeloIdDaCategoria(Long idCategoria){
        Categoria categoria = this.categoriaService.buscarCategoria(idCategoria);
        List<Produto> produtos = this.produtoRepository.findByCategoria_Id(categoria.getId());
        return ResponseEntity.ok(ProdutoDTO.converter(produtos));
    }

    @Transactional
    public ResponseEntity<List<ProdutoDTO>> deletarProdutosPeloIdDaCategoria(Long idCategoria) {
        Categoria categoria = this.categoriaService.buscarCategoria(idCategoria);
        this.produtoRepository.deleteByCategoria_Id(categoria.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Produto buscarProduto(Long idProduto){
        Optional<Produto> optionalProduto = this.produtoRepository.findById(idProduto);
        if(optionalProduto.isEmpty()){
            throw new ObjetoNaoEncontradoException("Produto com id "+idProduto+" não encontrado!");
        }
        return optionalProduto.get();
    }

    public Produto listarProdutosComEstoqueBaixo() {
        return produtoRepository.findByQuantidadeEmEstoqueLessThan(10);
    }



}

