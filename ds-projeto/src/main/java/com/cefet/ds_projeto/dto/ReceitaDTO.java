package com.cefet.ds_projeto.dto;

import java.time.LocalDate;
import com.cefet.ds_projeto.entities.Receita;

public class ReceitaDTO {
 
    private Long id;
    private LocalDate dataEntrada;
    private Double valor;
    private Long categoriaId;
    private Long usuarioId;

    public ReceitaDTO() {
    }  

    public ReceitaDTO(Receita receita) {
        this.id = receita.getId();
        this.dataEntrada = receita.getDataEntrada();
        this.valor = receita.getValor();
        this.categoriaId = receita.getCategoria().getId();
        this.usuarioId = receita.getUsuario().getId();
    }  

    public Long getId() {
        return id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public Double getValor() {
        return valor;
    }
    
    public Long getCategoriaId() {
        return categoriaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}