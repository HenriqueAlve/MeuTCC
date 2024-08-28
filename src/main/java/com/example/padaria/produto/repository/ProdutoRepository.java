package com.example.padaria.produto.repository;

import com.example.padaria.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria_Id(Long idCategoria);

    void deleteByCategoria_Id(Long idCategoria);

    List<Produto> findAllByQuantidadeEmEstoqueLessThan(int quantidade);

    Produto findByNome(String nome);





}
