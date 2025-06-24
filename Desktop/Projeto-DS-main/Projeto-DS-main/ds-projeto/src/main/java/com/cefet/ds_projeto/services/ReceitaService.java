package com.cefet.ds_projeto.services;

import com.cefet.ds_projeto.dto.ReceitaDTO;
import com.cefet.ds_projeto.entities.*;
import com.cefet.ds_projeto.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaService {
    
    @Autowired
    private ReceitaRepository receitaRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public ReceitaDTO findById(Long id) {
        Receita receita = receitaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Receita não encontrada com ID: " + id));
        return new ReceitaDTO(receita);
    }
    
    public List<ReceitaDTO> findAll() {
        return receitaRepository.findAll().stream()
            .map(ReceitaDTO::new)
            .collect(Collectors.toList());
    }
    
    public ReceitaDTO create(ReceitaDTO receitaDTO) {
        Categoria categoria = categoriaRepository.findById(receitaDTO.getCategoriaId())
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        
        Usuario usuario = usuarioRepository.findById(receitaDTO.getUsuarioId())
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Receita receita = new Receita();
        receita.setDataEntrada(receitaDTO.getDataEntrada());
        receita.setValor(receitaDTO.getValor());
        receita.setCategoria(categoria);
        receita.setUsuario(usuario);

        Receita receitaSalva = receitaRepository.save(receita);
        return new ReceitaDTO(receitaSalva);
    }
    
    public ReceitaDTO update(Long id, ReceitaDTO receitaDTO) {
        Receita receita = receitaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Receita não encontrada"));

        if (!receita.getUsuario().getId().equals(receitaDTO.getUsuarioId())) {
            throw new IllegalArgumentException("Não é permitido alterar o usuário da receita");
        }

        Categoria categoria = categoriaRepository.findById(receitaDTO.getCategoriaId())
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        receita.setDataEntrada(receitaDTO.getDataEntrada());
        receita.setValor(receitaDTO.getValor());
        receita.setCategoria(categoria);

        return new ReceitaDTO(receitaRepository.save(receita));
    }
    
    public void delete(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new EntityNotFoundException("Receita não encontrada com ID: " + id);
        }
        receitaRepository.deleteById(id);
    }
    
    public List<ReceitaDTO> findByUsuarioId(Long usuarioId) {
        return receitaRepository.findByUsuarioId(usuarioId).stream()
            .map(ReceitaDTO::new)
            .collect(Collectors.toList());
    }
    
    public List<ReceitaDTO> findByCategoriaId(Long categoriaId) {
        return receitaRepository.findByCategoriaId(categoriaId).stream()
            .map(ReceitaDTO::new)
            .collect(Collectors.toList());
    }
    
    public List<ReceitaDTO> findByDataEntradaBetween(LocalDate dataInicio, LocalDate dataFim) {
        return receitaRepository.findByDataEntradaBetween(dataInicio, dataFim).stream()
            .map(ReceitaDTO::new)
            .collect(Collectors.toList());
    }
}