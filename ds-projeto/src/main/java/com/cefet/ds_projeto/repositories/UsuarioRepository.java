package com.cefet.ds_projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ds_projeto.entities.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
    // Método Para Verificar a Existência de Email
    boolean existsByEmail(String email);

    // Método Para Verificar a Existência de Login
    boolean existsByLogin(String login);
}