package com.example.padaria.categoria.resource;

import com.example.padaria.categoria.dto.CategoriaDTO;
import com.example.padaria.categoria.form.CategoriaForm;
import com.example.padaria.categoria.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        return this.categoriaService.listarCategorias();
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> listarCategoria(@PathVariable Long idCategoria){
        return this.categoriaService.listarCategoria(idCategoria);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CategoriaDTO> cadastrarCategoria(@RequestBody CategoriaForm formulario){
        return this.categoriaService.cadastrarCategoria(formulario);
    }

    @PutMapping("/atualizar/{idCategoria}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long idCategoria, @RequestBody CategoriaForm formulario){
        return this.categoriaService.atualizarCategoria(idCategoria, formulario);
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> deletarCategoria(@PathVariable Long idCategoria){
        return this.categoriaService.deletarCategoria(idCategoria);
    }
}

