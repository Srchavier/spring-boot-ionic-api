package com.cursomc.builder;

import com.cursomc.dto.CidadeDTO;
import com.cursomc.model.Cidade;

public class CidadeBuilder {

	public static CidadeDTO builder(Cidade cidade) {
		return new CidadeDTO(cidade.getId(), cidade.getNome());
	}

	public static Cidade builderEstado(CidadeDTO cidadeDto) {
		return new Cidade(cidadeDto.getId(), cidadeDto.getNome());
	}

}
