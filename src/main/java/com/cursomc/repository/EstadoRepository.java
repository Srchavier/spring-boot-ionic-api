package com.cursomc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Transactional(readOnly = true)
	List<Estado> findAllByOrderByNome();
}
