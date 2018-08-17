package com.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomc.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
