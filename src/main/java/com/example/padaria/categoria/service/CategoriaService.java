package com.example.padaria.categoria.service;

import com.example.padaria.categoria.dto.CategoriaDTO;
import com.example.padaria.categoria.form.CategoriaForm;
import com.example.padaria.categoria.model.Categoria;
import com.example.padaria.categoria.repository.CategoriaRepository;
import com.example.padaria.exception.handler.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(CategoriaDTO.converter(categorias));
    }

    public ResponseEntity<CategoriaDTO> listarCategoria(Long idCategoria){
        Categoria categoria = this.buscarCategoria(idCategoria);
        return ResponseEntity.ok(new CategoriaDTO(categoria));
    }

    public ResponseEntity<CategoriaDTO> cadastrarCategoria(CategoriaForm formulario){
        Categoria categoria = new Categoria();
        categoria.setNome(formulario.getNome());
        categoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(new CategoriaDTO(categoria));
    }

    @Transactional
    public ResponseEntity<CategoriaDTO> atualizarCategoria(Long idCategoria, CategoriaForm formulario){
        Categoria categoria = this.buscarCategoria(idCategoria);
        categoria.setNome(formulario.getNome());
        return ResponseEntity.ok(new CategoriaDTO(categoria));
    }

    @Transactional
    public ResponseEntity<CategoriaDTO> deletarCategoria(Long idCategoria){
        Categoria categoria = buscarCategoria(idCategoria);
        this.categoriaRepository.deleteById(categoria.getId());
        return ResponseEntity.ok().build();
    }

    @Transactional
    public Categoria buscarCategoria(Long idCategoria){
        Optional<Categoria> optionalCategoria = this.categoriaRepository.findById(idCategoria);
        if(optionalCategoria.isEmpty()){
            throw new ObjetoNaoEncontradoException("Categoria com id " +idCategoria+" não encontrado!");
        }
        return optionalCategoria.get();
    }
}
