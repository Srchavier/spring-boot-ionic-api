package com.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.model.Cliente;
import com.cursomc.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@SuppressWarnings("unused")
	private Cliente salvar(Cliente cliente) {
		return  clienteRepository.save(cliente);
	}
	
	

}
