package com.cefet.ds_projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.ds_projeto.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
   
    List<Receita> findByCategoriaId(Long categoriaId);
    
    List<Receita> findByUsuarioId(Long usuarioId);
}