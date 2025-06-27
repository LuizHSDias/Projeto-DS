package com.cefet.ds_projeto.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cefet.ds_projeto.dto.ReceitaDTO;
import com.cefet.ds_projeto.services.ReceitaService;

import io.swagger.v3.oas.annotations.Operation; 
import io.swagger.v3.oas.annotations.tags.Tag; 

@Tag(name = "Receitas", description = "Gerenciamento de receitas (entradas de valor)") 
@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    
    @Autowired
    private ReceitaService receitaService;
    
    @Operation(summary = "Buscar receita por ID", 
               description = "Retorna os detalhes de uma receita específica através do seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> findById(@PathVariable Long id) {
        ReceitaDTO receitaDTO = receitaService.findById(id);
        return ResponseEntity.ok(receitaDTO);
    }
    
    @Operation(summary = "Listar todas as receitas", 
               description = "Retorna uma lista de todas as receitas cadastradas.")
    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> findAll() {
        List<ReceitaDTO> receitasDTO = receitaService.findAll();
        return ResponseEntity.ok(receitasDTO);
    }
    
    @Operation(summary = "Cadastrar nova receita", 
               description = "Cria e insere uma nova receita no sistema.")
    @PostMapping
    public ResponseEntity<ReceitaDTO> create(@RequestBody ReceitaDTO receitaDTO) {
        ReceitaDTO novaReceita = receitaService.create(receitaDTO);
        return ResponseEntity.status(201).body(novaReceita);
    }
    
    @Operation(summary = "Atualizar receita", 
               description = "Atualiza os dados de uma receita existente pelo seu ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDTO> update(@PathVariable Long id, @RequestBody ReceitaDTO receitaDTO) {
        ReceitaDTO receitaAtualizada = receitaService.update(id, receitaDTO);
        return ResponseEntity.ok(receitaAtualizada);
    }
    
    @Operation(summary = "Excluir receita", 
               description = "Remove uma receita do sistema pelo seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        receitaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Listar receitas por ID de Usuário", 
               description = "Retorna uma lista de todas as receitas associadas a um ID de usuário específico.")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReceitaDTO>> findByUsuarioId(@PathVariable Long usuarioId) {
        List<ReceitaDTO> receitas = receitaService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(receitas);
    }
    
    @Operation(summary = "Listar receitas por ID de Categoria", 
               description = "Retorna uma lista de todas as receitas que pertencem a uma categoria específica.")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ReceitaDTO>> findByCategoriaId(@PathVariable Long categoriaId) {
        List<ReceitaDTO> receitas = receitaService.findByCategoriaId(categoriaId);
        return ResponseEntity.ok(receitas);
    }
    
    @Operation(summary = "Listar receitas por período de data", 
               description = "Retorna uma lista de receitas registradas entre uma data de início e uma data de fim.")
    @GetMapping("/periodo")
    public ResponseEntity<List<ReceitaDTO>> findByPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<ReceitaDTO> receitas = receitaService.findByDataEntradaBetween(dataInicio, dataFim);
        return ResponseEntity.ok(receitas);
    }
}