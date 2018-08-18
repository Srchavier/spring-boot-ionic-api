package com.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.entity.Categoria;
import com.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	

}
