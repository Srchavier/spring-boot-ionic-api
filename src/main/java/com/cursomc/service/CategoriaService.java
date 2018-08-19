package com.cursomc.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.builder.CategoriaBuilder;
import com.cursomc.dto.CategoriaDTO;
import com.cursomc.model.Categoria;
import com.cursomc.repository.CategoriaRepository;
import com.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria alterar(@Valid Categoria categoria, Long id) {
		buscarPorId(id);
		categoria.setId(id);
		return categoriaRepository.save(categoria);
	}

	public void deleta(Long id) {
		buscarPorId(id);
		categoriaRepository.deleteById(id);
	}

	public Categoria buscarPorId(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Categoria.class.getName()));
	}

	public List<CategoriaDTO> buscarTodos() {
		List<Categoria> cat = categoriaRepository.findAll();
		return cat.stream().map(obj -> new CategoriaBuilder().builder(obj)).collect(Collectors.toList());
	}
	
	
	

}
