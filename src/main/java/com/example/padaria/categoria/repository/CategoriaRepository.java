package com.example.padaria.categoria.repository;

import com.example.padaria.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria , Long> {

    Optional<Object> findByNome(String nome);
}
