package com.cursomc.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
		Categoria categoriaSalva = buscarPorId(id);
		alterarCategoria(categoria, categoriaSalva);
		return categoriaRepository.save(categoriaSalva);
	}

	private void alterarCategoria(@Valid Categoria categoria, Categoria categoriaSalva) {
		categoriaSalva.setNome(categoria.getNome());
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
		return cat.stream().map(obj -> CategoriaBuilder.builder(obj)).collect(Collectors.toList());
	}

	public Page<CategoriaDTO> listaComPaginacao(Integer pagina, Integer numeroLinhas, String ordem, String direcao) {

		Pageable pageRequest = PageRequest.of(pagina, numeroLinhas, Direction.valueOf(direcao), ordem);

		Page<Categoria> cat = categoriaRepository.findAll(pageRequest);

		return cat.map(obj -> CategoriaBuilder.builder(obj));

	}

}
