package com.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
