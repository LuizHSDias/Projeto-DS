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

@RestController
@RequestMapping("/despesas")

public class DespesaControlller {
    @Autowired
	private DespesaService despesaService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesaDTO> findById(@PathVariable Long id) {
	DespesaDTO despesaDTO = despesaService.findById(id);
	return ResponseEntity.ok(despesaDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<DespesaDTO>> findAll() {
	List<DespesaDTO> despesasDTOs = despesaService.findAll();
	return ResponseEntity.ok(despesasDTOs);
	}
	
	@PostMapping
	public ResponseEntity<DespesaDTO> create(@RequestBody DespesaDTO despesaDTO) {
	DespesaDTO novoDespesa = despesaService.insert(despesaDTO);
	return ResponseEntity.status(201).body(novoDespesa);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DespesaDTO> update(@PathVariable Long id, @RequestBody DespesaDTO despesaDTO) {
	DespesaDTO despesaAtualizado = despesaService.update(id, despesaDTO);
	return ResponseEntity.ok(despesaAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	despesaService.delete(id);
	return ResponseEntity.noContent().build();
	}
}
