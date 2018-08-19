package com.cursomc.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.builder.ClienteBuilder;
import com.cursomc.dto.ClienteDTO;
import com.cursomc.model.Cliente;
import com.cursomc.repository.ClienteRepository;
import com.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente alterar(@Valid Cliente cliente, Long id) {
		Cliente clienteSalvo = buscarPorId(id);
		//BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		alterarCliente(cliente, clienteSalvo);
		return clienteRepository.save(clienteSalvo);
	}

	

	private void alterarCliente(@Valid Cliente cliente, Cliente clienteSalvo) {
		clienteSalvo.setNome(cliente.getNome());
		clienteSalvo.setEmail(cliente.getEmail());
	}

	public void deleta(Long id) {
		buscarPorId(id);
		clienteRepository.deleteById(id);
	}

	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Cliente.class.getName()));
	}

	public List<ClienteDTO> buscarTodos() {
		List<Cliente> cat = clienteRepository.findAll();
		return cat.stream().map(obj -> new ClienteBuilder().builder(obj)).collect(Collectors.toList());
	}
	
	public Page<ClienteDTO> listaComPaginacao(Integer pagina, Integer numeroLinhas, String ordem, String direcao) {

		PageRequest pageRequest = PageRequest.of(pagina, numeroLinhas, Direction.valueOf(direcao), ordem);

		Page<Cliente> cliente = clienteRepository.findAll(pageRequest);

		return (Page<ClienteDTO>) cliente.map(obj -> new ClienteBuilder().builder(obj));

	}
	
	

}
