package com.cursomc.builder;

import com.cursomc.dto.EstadoDTO;
import com.cursomc.model.Estado;

public class EstadoBuilder {

	public static EstadoDTO builder(Estado estado) {
		return new EstadoDTO(estado.getId(), estado.getNome());
	}

	public static Estado builderEstado(EstadoDTO estadoDto) {
		return new Estado(estadoDto.getId(), estadoDto.getNome());
	}

}
