package com.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.model.Categoria;
import com.cursomc.model.Cliente;
import com.cursomc.repository.ClienteRepository;
import com.cursomc.service.exception.ObjectNotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(clienteRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPrId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return ResponseEntity.ok().body(cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Categoria.class.getName())));

	}

}
