package com.cefet.ds_projeto.repositories;

import com.cefet.ds_projeto.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByTipoContainingIgnoreCase(String tipo);
    boolean existsByDescricao(String descricao);
}