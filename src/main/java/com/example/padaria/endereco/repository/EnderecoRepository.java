package com.example.padaria.endereco.repository;

import com.example.padaria.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco , Long> {
}
