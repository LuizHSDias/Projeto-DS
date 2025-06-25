package com.cefet.ds_projeto.dto;

import com.cefet.ds_projeto.entities.Usuario;

public class UsuarioDTO {

    private Long id;
    private String nome; 
    private String email;
    private String login;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.login = usuario.getLogin();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }
}