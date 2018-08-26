package com.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.builder.CategoriaBuilder;
import com.cursomc.dto.CategoriaDTO;
import com.cursomc.model.Categoria;
import com.cursomc.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listar() {
		return ResponseEntity.ok().body(categoriaService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPrId(@PathVariable Long id) {
		Categoria categoria = categoriaService.buscarPorId(id);
		return ResponseEntity.ok().body(categoria);

	}

	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> listarComPaginacao(
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "line", defaultValue = "24") Integer numeroLinhas,
			@RequestParam(value = "orderBy", defaultValue = "nome") String ordem,
			@RequestParam(value = "direction", defaultValue = "ASC") String direcao) {

		return ResponseEntity.ok().body(categoriaService.listaComPaginacao(pagina, numeroLinhas, ordem, direcao));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Categoria> salvar(@RequestBody @Valid CategoriaDTO categoriaDto) {
		Categoria cat = categoriaService.salvar(CategoriaBuilder.builderCategoria(categoriaDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).body(cat);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> alterar(@RequestBody @Valid CategoriaDTO categoriaDto, @PathVariable Long id) {
		Categoria cat = categoriaService.alterar(CategoriaBuilder.builderCategoria(categoriaDto), id);
		return ResponseEntity.ok(cat);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleta(@PathVariable Long id) {
		categoriaService.deleta(id);
		return ResponseEntity.noContent().build();
	}

}
