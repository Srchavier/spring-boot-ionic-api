package com.cursomc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.builder.ProdutoBuilder;
import com.cursomc.dto.ProdutoDTO;
import com.cursomc.model.Categoria;
import com.cursomc.model.Produto;
import com.cursomc.repository.CategoriaRepository;
import com.cursomc.repository.ProdutoRepository;
import com.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Produto.class.getName()));
	}

	public List<ProdutoDTO> buscarTodos() {
		List<Produto> pod = produtoRepository.findAll();
		return pod.stream().map(obj -> ProdutoBuilder.builder(obj)).collect(Collectors.toList());
	}

	public Page<ProdutoDTO> buscar(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);

		Page<Produto> pod = produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias,
				pageRequest);

		return pod.map(obj -> ProdutoBuilder.builder(obj));

	}

}
