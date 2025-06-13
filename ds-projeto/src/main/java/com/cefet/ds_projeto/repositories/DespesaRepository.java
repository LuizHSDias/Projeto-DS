package com.cefet.ds_projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.ds_projeto.entities.Despesa;

public interface DespesaRepository extends JpaRepository <Despesa, Long>  {
    List<Despesa> findByUsuarioId(Long usuarioId);
}