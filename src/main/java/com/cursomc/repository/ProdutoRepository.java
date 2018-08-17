package com.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
