package com.cefet.ds_projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.ds_projeto.dto.DespesaDTO;
import com.cefet.ds_projeto.entities.Categoria;
import com.cefet.ds_projeto.entities.Despesa;
import com.cefet.ds_projeto.entities.Usuario;
import com.cefet.ds_projeto.repositories.CategoriaRepository;
import com.cefet.ds_projeto.repositories.DespesaRepository;
import com.cefet.ds_projeto.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DespesaService {
    @Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

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
	
	// Inserir Despesa
	public DespesaDTO insert(DespesaDTO despesaDTO) {
		
	Usuario usuario = usuarioRepository.findById(despesaDTO.getUsuarioId())
		.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrada com ID: " +
		 despesaDTO.getUsuarioId()));
	
	Categoria categoria = categoriaRepository.findById(despesaDTO.getCategoriaId())
		.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " +
		 despesaDTO.getCategoriaId()));
	
	Despesa despesa = new Despesa();
	despesa.setDescricao(despesaDTO.getDescricao());
    despesa.setDataVencimento(despesaDTO.getDataVencimento());
    despesa.setDataPagamento(despesaDTO.getDataPagamento());
    despesa.setSituacao(despesaDTO.getSituacao());
    despesa.setValor(despesaDTO.getValor());
	despesa.setCategoria(categoria);
    despesa.setUsuario(usuario);
	Despesa despesaSalvo = despesaRepository.save(despesa);
	return new DespesaDTO(despesaSalvo);
	} 
	
	// Atualizar Despesa
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
	  public List<DespesaDTO> findByUsuarioId(Long usuarioId) {
        return despesaRepository.findByUsuarioId(usuarioId).stream()
            .map(DespesaDTO::new).toList();
}


    public List<DespesaDTO> findByCategoriaId(Long categoriaId) {
        return despesaRepository.findByCategoriaId(categoriaId).stream()
            .map(DespesaDTO::new).toList();
		}
	}