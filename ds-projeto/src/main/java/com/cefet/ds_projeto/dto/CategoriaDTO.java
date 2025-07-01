package com.cefet.ds_projeto.dto;

import com.cefet.ds_projeto.entities.Categoria;

import io.swagger.v3.oas.annotations.media.Schema; 

@Schema(description = "DTO para representar uma categoria de item ou serviço")

public class CategoriaDTO {

    @Schema(description = "ID único da categoria", example = "1")
    private Long id;

    @Schema(description = "Descrição da categoria (ex: 'Alimentos', 'Eletrônicos')", example = "Eletrônicos")
    private String descricao;

    @Schema(description = "Tipo da categoria (ex: 'Produto', 'Serviço')", example = "Produto")
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