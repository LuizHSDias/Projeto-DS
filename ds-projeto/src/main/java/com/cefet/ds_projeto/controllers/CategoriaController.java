package com.cefet.ds_projeto.controllers;

import com.cefet.ds_projeto.dto.CategoriaDTO;
import com.cefet.ds_projeto.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias de itens ou serviços.") 
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @Operation(summary = "Cadastrar nova categoria",
               description = "Cria e insere uma nova categoria no sistema com base nos dados fornecidos.")
    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO novaCategoria = categoriaService.create(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }
    
    @Operation(summary = "Buscar categoria por ID",
               description = "Retorna os detalhes de uma categoria específica utilizando seu ID único.")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        CategoriaDTO categoriaDTO = categoriaService.findById(id);
        return ResponseEntity.ok(categoriaDTO);
    }
    
    @Operation(summary = "Listar todas as categorias",
               description = "Retorna uma lista de todas as categorias cadastradas no sistema.")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }
    
    @Operation(summary = "Atualizar categoria",
               description = "Atualiza os dados de uma categoria existente pelo seu ID.")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaAtualizada = categoriaService.update(id, categoriaDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }
    
    @Operation(summary = "Excluir categoria",
               description = "Remove uma categoria do sistema permanentemente pelo seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Buscar categorias por tipo",
               description = "Retorna uma lista de categorias filtradas por um tipo específico (ex: 'Produto', 'Serviço').")
    @GetMapping("/buscar-por-tipo")
    public ResponseEntity<List<CategoriaDTO>> findByTipo(@RequestParam String tipo) {
        List<CategoriaDTO> categorias = categoriaService.findByTipo(tipo);
        return ResponseEntity.ok(categorias);
    }
    
    @Operation(summary = "Verificar existência de descrição de categoria",
               description = "Verifica se uma descrição de categoria já existe no sistema, retornando verdadeiro ou falso.")
    @GetMapping("/verificar-descricao")
    public ResponseEntity<Boolean> verificarDescricao(@RequestParam String descricao) {
        boolean existe = categoriaService.existsByDescricao(descricao);
        return ResponseEntity.ok(existe);
    }
}