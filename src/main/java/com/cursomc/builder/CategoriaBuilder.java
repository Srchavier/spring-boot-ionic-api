package com.cursomc.builder;

import com.cursomc.dto.CategoriaDTO;
import com.cursomc.model.Categoria;

public class CategoriaBuilder {
	
	public CategoriaDTO builder(Categoria categoria) {
		return new CategoriaDTO(categoria.getId(), categoria.getNome());
	}

}
