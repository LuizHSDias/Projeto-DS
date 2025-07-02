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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "Operações relacionadas a usuarios")

public class UsuarioController {
    
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Buscar Usuário por ID", description = "Retorna os dados de um usuário específico.")
	public ResponseEntity<UsuarioDTO> findById(
		@Parameter(description = "ID do usuário a ser buscado", example = "1")
		@PathVariable Long id) {
	UsuarioDTO usuarioDTO = usuarioService.findById(id);
	return ResponseEntity.ok(usuarioDTO);
	}
	
	@GetMapping
	@Operation(summary = "Listar todos os usuários", description = "Retorna a lista de todos os usuários cadastrados.")
	public ResponseEntity<List<UsuarioDTO>> findAll() {
	List<UsuarioDTO> usuariosDTOs = usuarioService.findAll();
	return ResponseEntity.ok(usuariosDTOs);
	}
	
	@PostMapping
	@Operation(summary = "Criar um novo usuário", description = "Adiciona um novo usuário ao sistema.")
	public ResponseEntity<UsuarioDTO> create(
		@Parameter(description = "ID do usuário a ser atualizado", example = "1") 
        @PathVariable Long id,
        @Parameter(description = "Dados atualizados do usuário")
		@RequestBody UsuarioDTO usuarioDTO) {
	UsuarioDTO novoUsuario = usuarioService.insert(usuarioDTO);
	return ResponseEntity.status(201).body(novoUsuario);
	}
	
	@PutMapping("/{id}")
	 @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente com base no ID.")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
	UsuarioDTO usuarioAtualizado = usuarioService.update(id, usuarioDTO);
	return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir usuário", description = "Remove um usuário do sistema com base no ID.")
	public ResponseEntity<Void> delete(
		@Parameter(description = "ID do usuário a ser excluído", example = "1")
		@PathVariable Long id) {
	usuarioService.delete(id);
	return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/existe-email")
	@Operation(summary = "Verificar existência de e-mail", description = "Verifica se um determinado e-mail já está cadastrado no sistema.")
	public ResponseEntity<Boolean> emailExiste(
		@Parameter(description = "E-mail a ser verificado", example = "usuario@email.com")
		@RequestParam String email){
		Boolean resultado = usuarioService.emailExiste(email);
		return ResponseEntity.ok(resultado);
	}

	@GetMapping("/existe-login")
	@Operation(summary = "Verificar existência de login", description = "Verifica se um determinado login já está cadastrado no sistema.")
	public ResponseEntity<Boolean> loginExiste(
		@Parameter(description = "Login a ser verificado", example = "usuario123")
		@RequestParam String login){
		Boolean resultado = usuarioService.loginExiste(login);
		return ResponseEntity.ok(resultado);
	}
}