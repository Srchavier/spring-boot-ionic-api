package com.cursomc.builder;

import com.cursomc.dto.ClienteDTO;
import com.cursomc.model.Cliente;

public class ClienteBuilder {

	public ClienteDTO builder(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
	}

	public Cliente builderCliente(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getEmail(), clienteDTO.getEmail(), null, null);
	}
}
