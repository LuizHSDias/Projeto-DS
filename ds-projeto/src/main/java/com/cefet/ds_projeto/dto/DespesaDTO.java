package com.cefet.ds_projeto.dto;

import java.time.LocalDate;

import com.cefet.ds_projeto.entities.Despesa;

public class DespesaDTO {

    private Long id;
    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private String situacao;
    private Double valor;

    public DespesaDTO() {

    }

    public DespesaDTO(Despesa despesa){
        this.id = despesa.getId();
        this.descricao = despesa.getDescricao();
        this.dataVencimento = despesa.getDataVencimento();
        this.dataPagamento = despesa.getDataPagamento();
        this.situacao = despesa.getSituacao();
        this.valor = despesa.getValor();

    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public Double getValor() {
        return valor;
    }

    

    
}