package com.cefet.ds_projeto.services;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.ds_projeto.dto.UsuarioDTO;
import com.cefet.ds_projeto.entities.Usuario;
import com.cefet.ds_projeto.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Gerador de senha aleatória segura
	private String gerarSenha(int tamanho) {
		String alfabeto = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
		SecureRandom random = new SecureRandom();
		StringBuilder senha = new StringBuilder();
		for (int i = 0; i < tamanho; i++) {
			int index = random.nextInt(alfabeto.length());
			senha.append(alfabeto.charAt(index));
		}
		return senha.toString();
	}

	// Buscar todos
	public List<UsuarioDTO> findAll() {
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		return listaUsuarios.stream().map(UsuarioDTO::new).toList();
	}

	// Buscar por ID
	public UsuarioDTO findById(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
		return new UsuarioDTO(usuario);
	}

	// Inserir Usuário
	public UsuarioDTO insert(UsuarioDTO usuarioDTO) {

		// Verifica se já existe email 
		if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
			throw new IllegalArgumentException("Email já está em uso.");
		}

		// Verifica se já existe login
		if (usuarioRepository.existsByLogin(usuarioDTO.getLogin())) {
			throw new IllegalArgumentException("Login já está em uso.");
		}

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setLogin(usuarioDTO.getLogin());
		usuario.setSenha(gerarSenha(10));
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuarioSalvo);
	}

	// Atualizar Usuário
	public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        
		// Garante que o Email não seja alterado, caso não seja informado
		if (!usuario.getEmail().equals(usuarioDTO.getEmail())) {
			throw new IllegalArgumentException("Não é permitido alterar o email");
		}

		// Garante que o Login não seja alterado, caso não seja informado
		if (!usuario.getLogin().equals(usuarioDTO.getLogin())) {
			throw new IllegalArgumentException("Não é permitido alterar o login");
		}

		usuario.setNome(usuarioDTO.getNome());
		Usuario usuarioAtualizado = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuarioAtualizado);
	}

	// Remover por ID
	public void delete(Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new EntityNotFoundException("Usuário não encontrado com ID " + id);
		}
		usuarioRepository.deleteById(id);
	}

	// Verificar Email
	public boolean emailExiste(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	// Verificar Login
	public boolean loginExiste(String login) {
		return usuarioRepository.existsByLogin(login);
	}

}