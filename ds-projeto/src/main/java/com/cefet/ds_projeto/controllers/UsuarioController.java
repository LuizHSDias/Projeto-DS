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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.ds_projeto.dto.UsuarioDTO;
import com.cefet.ds_projeto.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
	UsuarioDTO usuarioDTO = usuarioService.findById(id);
	return ResponseEntity.ok(usuarioDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
	List<UsuarioDTO> usuariosDTOs = usuarioService.findAll();
	return ResponseEntity.ok(usuariosDTOs);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
	UsuarioDTO novoUsuario = usuarioService.insert(usuarioDTO);
	return ResponseEntity.status(201).body(novoUsuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
	UsuarioDTO usuarioAtualizado = usuarioService.update(id, usuarioDTO);
	return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	usuarioService.delete(id);
	return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/existe")
	public ResponseEntity<Boolean> loginExiste(@RequestParam String login){
		Boolean resultado = usuarioService.loginExiste(login);
		return ResponseEntity.ok(resultado);
	}
}