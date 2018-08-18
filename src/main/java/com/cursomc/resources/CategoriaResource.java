package com.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.entity.Categoria;
import com.cursomc.repository.CategoriaRepository;
import com.cursomc.service.CategoriaService;
import com.cursomc.service.exception.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/")
	public ResponseEntity<List<Categoria>> listar() {
		return ResponseEntity.ok().body(categoriaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPrId(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return ResponseEntity.ok().body(categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Categoria.class.getName())));

	}

	@PostMapping
	public ResponseEntity<Categoria> salvar(@RequestBody @Valid Categoria categoria) {
		Categoria cat = categoriaService.salvar(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cat);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> alterar(@RequestBody @Valid Categoria categoria, @PathVariable Long id) {
		Categoria cat = categoriaService.alterar(categoria, id);
		return ResponseEntity.ok(cat);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleta(@PathVariable Long id) {
		categoriaService.deleta(id);
		return ResponseEntity.noContent().build();
	}

}
