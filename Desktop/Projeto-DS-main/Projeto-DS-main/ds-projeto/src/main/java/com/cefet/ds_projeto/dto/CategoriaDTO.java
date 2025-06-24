package com.cefet.ds_projeto.dto;

import com.cefet.ds_projeto.entities.Categoria;

public class CategoriaDTO {
 
    private Long id;
    private String descricao;
    private String tipo;

    public CategoriaDTO() {
    }  

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.descricao = categoria.getDescricao();
        this.tipo = categoria.getTipo();
        
    }  

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }
    
}