package com.cefet.ds_projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.ds_projeto.dto.DespesaDTO;
import com.cefet.ds_projeto.services.DespesaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/despesas")

public class DespesaControlller {
    @Autowired
	private DespesaService despesaService;
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Buscar despesa por ID", description = "Retorna os dados de uma despesa específica com base no seu ID.")
	public ResponseEntity<DespesaDTO> findById(
		@Parameter(description = "ID da despesa", example = "2")
		@PathVariable Long id) {
	DespesaDTO despesaDTO = despesaService.findById(id);
	return ResponseEntity.ok(despesaDTO);
	}
	
	@GetMapping
	@Operation(summary = "Listar todas as despesas", description = "Retorna todas as despesas cadastradas no sistema.")
	public ResponseEntity<List<DespesaDTO>> findAll() {
	List<DespesaDTO> despesasDTOs = despesaService.findAll();
	return ResponseEntity.ok(despesasDTOs);
	}
	
	@PostMapping
	@Operation(summary = "Cadastrar nova despesa", description = "Cria uma nova despesa no sistema.")
	public ResponseEntity<DespesaDTO> create(@RequestBody DespesaDTO despesaDTO) {
	DespesaDTO novoDespesa = despesaService.insert(despesaDTO);
	return ResponseEntity.status(201).body(novoDespesa);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar despesa", description = "Atualiza os dados de uma despesa existente com base no ID.")
	public ResponseEntity<DespesaDTO> update(
		@Parameter(description = "ID da despesa a ser atualizada", example = "10")
		@PathVariable Long id,
		@Parameter(description = "Novos dados da despesa") 
		@RequestBody DespesaDTO despesaDTO) {
	DespesaDTO despesaAtualizado = despesaService.update(id, despesaDTO);
	return ResponseEntity.ok(despesaAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir despesa", description = "Remove uma despesa do sistema com base no ID.")
	public ResponseEntity<Void> delete(
		@Parameter(description = "ID da despesa a ser excluída", example = "10")
		@PathVariable Long id) {
	despesaService.delete(id);
	return ResponseEntity.noContent().build();
	}

	@GetMapping("/usuario/{usuarioId}")
	@Operation(summary = "Listar despesas por usuário", description = "Retorna todas as despesas associadas a um usuário específico.")
    public ResponseEntity<List<DespesaDTO>> findByUsuarioId(
		@Parameter(description = "ID do usuário", example = "3")
		@PathVariable Long usuarioId) {
        List<DespesaDTO> despesas = despesaService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(despesas);
    }
    
    @GetMapping("/categoria/{categoriaId}")
	@Operation(summary = "Listar despesas por categoria", description = "Retorna todas as despesas associadas a uma categoria específica.")
    public ResponseEntity<List<DespesaDTO>> findByCategoriaId(
		@Parameter(description = "ID do usuário", example = "5")
		@PathVariable Long categoriaId) {
        List<DespesaDTO> despesas = despesaService.findByCategoriaId(categoriaId);
        return ResponseEntity.ok(despesas);
    }
}
