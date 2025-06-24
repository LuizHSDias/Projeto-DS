package com.cefet.ds_projeto.repositories;

import com.cefet.ds_projeto.entities.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByUsuarioId(Long usuarioId);
    List<Receita> findByCategoriaId(Long categoriaId);
    List<Receita> findByDataEntradaBetween(LocalDate dataInicio, LocalDate dataFim);
}