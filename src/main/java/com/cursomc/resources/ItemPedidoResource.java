package com.cursomc.resources;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.model.Categoria;
import com.cursomc.model.Pedido;
import com.cursomc.repository.PedidoRepository;
import com.cursomc.service.PedidoService;
import com.cursomc.service.exception.ObjectNotFoundException;

@RestController
@RequestMapping("/pedidos")
public class ItemPedidoResource {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPrId(@PathVariable Long id) {
		Optional<Pedido> itemPedido = pedidoRepository.findById(id);
		return ResponseEntity.ok().body(itemPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Categoria.class.getName())));

	}

	@GetMapping()
	public ResponseEntity<Page<Pedido>> listarComPaginacao(
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "line", defaultValue = "24") Integer numeroLinhas,
			@RequestParam(value = "orderBy", defaultValue = "instance") String ordem,
			@RequestParam(value = "direction", defaultValue = "DESC") String direcao) {

		return ResponseEntity.ok().body(pedidoService.listaComPaginacao(pagina, numeroLinhas, ordem, direcao));
	}

	@PostMapping
	public ResponseEntity<Pedido> salvar(@RequestBody @Valid Pedido pedido) {
		Pedido ped = pedidoService.salvar(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ped.getId()).toUri();
		return ResponseEntity.created(uri).body(ped);
	}

}
