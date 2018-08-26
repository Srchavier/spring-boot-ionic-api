package com.cursomc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.builder.EstadoBuilder;
import com.cursomc.dto.EstadoDTO;
import com.cursomc.model.Estado;
import com.cursomc.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<EstadoDTO> buscarTodosEstados() {
		List<Estado> estado = estadoRepository.findAllByOrderByNome();
		return estado.stream().map(x -> EstadoBuilder.builder(x)).collect(Collectors.toList());
	}

}
