package com.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.dto.CidadeDTO;
import com.cursomc.dto.EstadoDTO;
import com.cursomc.service.CidadeService;
import com.cursomc.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;

	@GetMapping()
	public ResponseEntity<List<EstadoDTO>> listarTodosOrdenadoPorNome() {
		return ResponseEntity.ok().body(estadoService.buscarTodosEstados());
	}

	@GetMapping("/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> buscarCidadePorId(@PathVariable Integer estadoId) {
		return ResponseEntity.ok().body(cidadeService.buscaCidPorId(estadoId.longValue()));
	}

}
