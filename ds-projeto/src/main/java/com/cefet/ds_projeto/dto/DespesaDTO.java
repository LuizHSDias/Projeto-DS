package com.cefet.ds_projeto.dto;

import java.time.LocalDate;

import com.cefet.ds_projeto.entities.Despesa;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa uma despesa pessoal")

public class DespesaDTO {

    @Schema(description = "Identificador único da despesa", example = "1")
    private Long id;

    @Schema(description = "Descrição da despesa", example = "Pagamento da conta de luz")
    private String descricao;

    @Schema(description = "Data de vencimento da despesa (formato YYYY-MM-DD)", example = "2025-06-30")
    private LocalDate dataVencimento;

    @Schema(description = "Data em que a despesa foi paga (formato YYYY-MM-DD)", example = "2025-06-28")
    private LocalDate dataPagamento;

    @Schema(description = "Situação da despesa (ex: 'PENDENTE', 'PAGA')", example = "PAGA")
    private String situacao;

    @Schema(description = "Valor monetário da despesa", example = "250.75")
    private Double valor;

    @Schema(description = "ID da categoria associada à despesa", example = "3")
    private Long categoriaId;

    @Schema(description = "ID do usuário responsável pela despesa", example = "1")
    private Long usuarioId;

    public DespesaDTO() {

    }

    public DespesaDTO(Despesa despesa){
        this.id = despesa.getId();
        this.descricao = despesa.getDescricao();
        this.dataVencimento = despesa.getDataVencimento();
        this.dataPagamento = despesa.getDataPagamento();
        this.situacao = despesa.getSituacao();
        this.valor = despesa.getValor();
        this.categoriaId = despesa.getCategoria().getId();
        this.usuarioId = despesa.getUsuario().getId();

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

    public Long getCategoriaId(){
        return categoriaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    
}