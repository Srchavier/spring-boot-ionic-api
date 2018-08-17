package com.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.entity.Categoria;
import com.cursomc.exception.ObjectNotFoundException;
import com.cursomc.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository CategoriaRepository;
	
	//@Autowired
	//private CategoriaService CategoriaService;
	
	@GetMapping("/")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(CategoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPrId(@PathVariable Long id) {
		Optional<Categoria> categoria = CategoriaRepository.findById(id);
		return ResponseEntity.ok().body(categoria.orElseThrow(() ->new ObjectNotFoundException("Objecto não encontrado id:" +id + " type:" + Categoria.class.getName())));
		
	}

}
