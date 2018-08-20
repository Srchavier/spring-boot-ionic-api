package com.cursomc.resources;

import java.util.List;

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
	public ResponseEntity<Page<ProdutoDTO>> listarComPaginacao(
			@RequestParam(value = "nome", defaultValue = "0") String nome,
			@RequestParam(value = "categorias", defaultValue = "0") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "line", defaultValue = "24") Integer numeroLinhas,
			@RequestParam(value = "orderBy", defaultValue = "nome") String ordem,
			@RequestParam(value = "direction", defaultValue = "ASC") String direcao) {

		return ResponseEntity.ok().body(produtoService.buscar(URL.decodeParam(nome), URL.decodeIntList(categorias),
				pagina, numeroLinhas, ordem, direcao));
	}

	@GetMapping("/")
	public ResponseEntity<List<ProdutoDTO>> listar() {
		return ResponseEntity.ok().body(produtoService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPrId(@PathVariable Long id) {
		Produto pod = produtoService.buscarPorId(id);
		return ResponseEntity.ok().body(pod);

	}
}
