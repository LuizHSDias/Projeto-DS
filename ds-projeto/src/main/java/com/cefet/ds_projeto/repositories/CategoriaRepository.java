package com.cefet.ds_projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.ds_projeto.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByTipo(String tipo);
    
    List<Categoria> findByDescricaoContaining(String descricao);
}