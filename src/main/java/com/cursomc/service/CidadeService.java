package com.cursomc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.builder.CidadeBuilder;
import com.cursomc.dto.CidadeDTO;
import com.cursomc.model.Cidade;
import com.cursomc.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<CidadeDTO> buscaCidPorId(Long id) {
		List<Cidade> cid = cidadeRepository.findCidades(id);
		return cid.stream().map(x -> CidadeBuilder.builder(x)).collect(Collectors.toList());
	}

}
