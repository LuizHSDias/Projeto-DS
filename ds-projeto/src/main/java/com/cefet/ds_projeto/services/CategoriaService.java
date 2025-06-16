package com.cefet.ds_projeto.services;

import com.cefet.ds_projeto.dto.CategoriaDTO;
import com.cefet.ds_projeto.entities.Categoria;
import com.cefet.ds_projeto.repositories.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + id));
        return new CategoriaDTO(categoria);
    }
    
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream()
            .map(CategoriaDTO::new)
            .collect(Collectors.toList());
    }
    
    public CategoriaDTO create(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(categoriaDTO.getDescricao());
        categoria.setTipo(categoriaDTO.getTipo());
        
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoriaSalva);
    }
    
    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        
        categoria.setDescricao(categoriaDTO.getDescricao());
        categoria.setTipo(categoriaDTO.getTipo());
        
        return new CategoriaDTO(categoriaRepository.save(categoria));
    }
    
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada com ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
    
    public List<CategoriaDTO> findByTipo(String tipo) {
        return categoriaRepository.findByTipoContainingIgnoreCase(tipo).stream()
            .map(CategoriaDTO::new)
            .collect(Collectors.toList());
    }
    
    public boolean existsByDescricao(String descricao) {
        return categoriaRepository.existsByDescricao(descricao);
    }
}