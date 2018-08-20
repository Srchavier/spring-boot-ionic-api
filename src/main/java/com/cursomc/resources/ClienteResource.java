package com.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.builder.ClienteBuilder;
import com.cursomc.dto.ClienteDTO;
import com.cursomc.dto.ClienteNewDTO;
import com.cursomc.model.Cliente;
import com.cursomc.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() {
		return ResponseEntity.ok().body(clienteService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		return ResponseEntity.ok().body(cliente);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> listarComPaginacao(
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "line", defaultValue = "24") Integer numeroLinhas,
			@RequestParam(value = "orderBy", defaultValue = "nome") String ordem,
			@RequestParam(value = "direction", defaultValue = "ASC") String direcao) {

		return ResponseEntity.ok().body(clienteService.listaComPaginacao(pagina, numeroLinhas, ordem, direcao));
	}

	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody @Valid ClienteNewDTO ClienteNewDto) {
		Cliente cat = clienteService.salvar(ClienteBuilder.builderClienteNewDto(ClienteNewDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).body(cat);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@RequestBody @Valid ClienteDTO ClienteDto, @PathVariable Long id) {
		Cliente cat = clienteService.alterar(ClienteBuilder.builderCliente(ClienteDto), id);
		return ResponseEntity.ok(cat);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleta(@PathVariable Long id) {
		clienteService.deleta(id);
		return ResponseEntity.noContent().build();
	}

}
