package com.example.padaria.cliente.repository;

import com.example.padaria.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente , Long> {

}
