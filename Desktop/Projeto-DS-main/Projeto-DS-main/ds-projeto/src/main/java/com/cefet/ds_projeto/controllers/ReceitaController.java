package com.cefet.ds_projeto.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cefet.ds_projeto.dto.ReceitaDTO;
import com.cefet.ds_projeto.services.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    
    @Autowired
    private ReceitaService receitaService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> findById(@PathVariable Long id) {
        ReceitaDTO receitaDTO = receitaService.findById(id);
        return ResponseEntity.ok(receitaDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> findAll() {
        List<ReceitaDTO> receitasDTO = receitaService.findAll();
        return ResponseEntity.ok(receitasDTO);
    }
    
    @PostMapping
    public ResponseEntity<ReceitaDTO> create(@RequestBody ReceitaDTO receitaDTO) {
        ReceitaDTO novaReceita = receitaService.create(receitaDTO);
        return ResponseEntity.status(201).body(novaReceita);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDTO> update(@PathVariable Long id, @RequestBody ReceitaDTO receitaDTO) {
        ReceitaDTO receitaAtualizada = receitaService.update(id, receitaDTO);
        return ResponseEntity.ok(receitaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        receitaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReceitaDTO>> findByUsuarioId(@PathVariable Long usuarioId) {
        List<ReceitaDTO> receitas = receitaService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(receitas);
    }
    
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ReceitaDTO>> findByCategoriaId(@PathVariable Long categoriaId) {
        List<ReceitaDTO> receitas = receitaService.findByCategoriaId(categoriaId);
        return ResponseEntity.ok(receitas);
    }
    
    @GetMapping("/periodo")
    public ResponseEntity<List<ReceitaDTO>> findByPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<ReceitaDTO> receitas = receitaService.findByDataEntradaBetween(dataInicio, dataFim);
        return ResponseEntity.ok(receitas);
    }
}