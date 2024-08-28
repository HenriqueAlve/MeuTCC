package com.example.padaria.usuario.repository;

import com.example.padaria.usuario.model.Usuario;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByUsername(String username);

    @Query("select u from Usuario u where u.username = :username")
    Usuario getByUsername(@Param("username") String username);


}