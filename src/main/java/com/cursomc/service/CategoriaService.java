package com.cursomc.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.entity.Categoria;
import com.cursomc.exception.ObjectNotFoundException;
import com.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria alterar(@Valid Categoria categoria, Long id) {
		categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Categoria.class.getName()));
		categoria.setId(id);
		return categoriaRepository.save(categoria);
	}
	

}
