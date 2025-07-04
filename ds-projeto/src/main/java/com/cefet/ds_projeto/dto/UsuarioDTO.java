package com.cefet.ds_projeto.dto;

import com.cefet.ds_projeto.entities.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa os dados de um usuário")

public class UsuarioDTO {

    @Schema(description = "Identificador único de usuário", example = "1")
    private Long id;

    @Schema(description = "Nome completo do usuário", example = "Marcela Freitas Mariano")
    private String nome; 

    @Schema(description = "Email do usuário", example = "Marcela@gmail.com")
    private String email;

    @Schema(description = "Login do usuário", example = "Marcela")
    private String login;

    private String senha;
    
    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
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

    public String getSenha() {
        return senha;
    }
}