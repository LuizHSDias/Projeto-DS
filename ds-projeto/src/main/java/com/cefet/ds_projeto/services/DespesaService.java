package com.cefet.ds_projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.ds_projeto.dto.DespesaDTO;
import com.cefet.ds_projeto.entities.Despesa;
import com.cefet.ds_projeto.entities.Usuario;
import com.cefet.ds_projeto.repositories.DespesaRepository;
import com.cefet.ds_projeto.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DespesaService {
    @Autowired
	private DespesaRepository despesaRepository;

    @Autowired
	private UsuarioRepository usuarioRepository;

    // Buscar todos
	public List<DespesaDTO> findAll(){
	List<Despesa> listaDespesas = despesaRepository.findAll();
	return listaDespesas.stream().map(DespesaDTO::new).toList();
	}
	
	// Buscar por ID
	public DespesaDTO findById(Long id) {
		Despesa despesa = despesaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Despesa não encontrada com ID: " + id));
				return new DespesaDTO(despesa);
	}
	
	// Inserir Usuário
	public DespesaDTO insert(DespesaDTO despesaDTO) {
		
	Usuario usuario = usuarioRepository.findById(despesaDTO.getUsuarioId())
		.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrada com ID: " +
		 despesaDTO.getUsuarioId()));
	
	Despesa despesa = new Despesa();
	despesa.setDescricao(despesaDTO.getDescricao());
    despesa.setDataVencimento(despesaDTO.getDataVencimento());
    despesa.setDataPagamento(despesaDTO.getDataPagamento());
    despesa.setSituacao(despesaDTO.getSituacao());
    despesa.setValor(despesaDTO.getValor());
    despesa.setUsuario(usuario);
	Despesa despesaSalvo = despesaRepository.save(despesa);
	return new DespesaDTO(despesaSalvo);
	} 
	
	// Atualizar Usuário
	public DespesaDTO update(Long id, DespesaDTO despesaDTO) {
		Despesa despesa = despesaRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException("Despesa não encontrada com ID: " + id));
		
        Usuario usuario = usuarioRepository.findById(despesaDTO.getUsuarioId())
		.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " +
		despesaDTO.getUsuarioId()));
        despesa.setDescricao(despesaDTO.getDescricao());
        despesa.setDataVencimento(despesaDTO.getDataVencimento());
        despesa.setDataPagamento(despesaDTO.getDataPagamento());
        despesa.setSituacao(despesaDTO.getSituacao());
        despesa.setValor(despesaDTO.getValor());
        despesa.setUsuario(usuario);
		
		Despesa despesaAtualizado = despesaRepository.save(despesa);
		return new DespesaDTO(despesaAtualizado);
		}
	
	// Remover por ID 
	public void delete(Long id) {
		if(!despesaRepository.existsById(id)) {
			throw new EntityNotFoundException("Usuário não encontrado com ID " + id);
		}
		despesaRepository.deleteById(id);
	}

    // Buscar despesa usuário
		public List<DespesaDTO> buscarporUsuario(Long usuarioId){
			List<Despesa> listaDespesas = despesaRepository.findByUsuarioId(usuarioId);
			return listaDespesas.stream().map(DespesaDTO::new).toList();
		}
}
