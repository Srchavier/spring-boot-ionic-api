package com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.dto.ProdutoDTO;
import com.cursomc.model.Produto;
import com.cursomc.resources.utils.URL;
import com.cursomc.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping()
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		return ResponseEntity.ok().body(produtoService.buscar(URL.decodeParam(nome), URL.decodeIntList(categorias),
				page, linesPerPage, orderBy, direction));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPrId(@PathVariable Long id) {
		Produto pod = produtoService.buscarPorId(id);
		return ResponseEntity.ok().body(pod);

	}
}
