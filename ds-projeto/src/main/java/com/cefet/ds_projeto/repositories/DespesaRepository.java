package com.cefet.ds_projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ds_projeto.entities.Despesa;

public interface DespesaRepository extends JpaRepository <Despesa, Long>  {

}