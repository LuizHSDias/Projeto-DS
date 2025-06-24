package com.cefet.ds_projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ds_projeto.entities.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    boolean existsByLogin(String login);
}