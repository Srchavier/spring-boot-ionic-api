package com.cursomc.builder;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cursomc.dto.ClienteDTO;
import com.cursomc.dto.ClienteNewDTO;
import com.cursomc.enums.TipoCliente;
import com.cursomc.model.Cidade;
import com.cursomc.model.Cliente;
import com.cursomc.model.Endereco;

public class ClienteBuilder {

	@Autowired
	private static BCryptPasswordEncoder bCryptPasswordEncoder;

	public static ClienteDTO builder(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getSenha());
	}

	public static Cliente builderCliente(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null,
				clienteDTO.getSenha());
	}

	public static Cliente builderClienteNewDto(@Valid ClienteNewDTO clienteNewDto) {
		Cliente cli = new Cliente(null, clienteNewDto.getNome(), clienteNewDto.getEmail(), clienteNewDto.getCpfOuCnpj(),
				TipoCliente.toEnum(clienteNewDto.getTipo()), bCryptPasswordEncoder.encode(clienteNewDto.getSenha()));
		Cidade cid = new Cidade(clienteNewDto.getIdCidade(), null, null);

		Endereco end = new Endereco(null, clienteNewDto.getLogradouro(), clienteNewDto.getNumero(),
				clienteNewDto.getComplemento(), clienteNewDto.getBairro(), clienteNewDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);

		cli.getTelefones().add(clienteNewDto.getTelefone1());
		if (clienteNewDto.getTelefone2() != null) {
			cli.getTelefones().add(clienteNewDto.getTelefone2());
		}
		if (clienteNewDto.getTelefone3() != null) {
			cli.getTelefones().add(clienteNewDto.getTelefone3());
		}
		return cli;
	}
}
