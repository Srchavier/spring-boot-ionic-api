package com.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.model.Categoria;
import com.cursomc.model.Pedido;
import com.cursomc.repository.PedidoRepository;
import com.cursomc.service.exception.ObjectNotFoundException;

@RestController
@RequestMapping("/pedidos")
public class ItemPedidoResource {

	@Autowired
	private PedidoRepository pedidoRepository;

	// @Autowired
	// private CategoriaService CategoriaService;

	@GetMapping("/")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(pedidoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPrId(@PathVariable Long id) {
		Optional<Pedido> itemPedido = pedidoRepository.findById(id);
		return ResponseEntity.ok().body(itemPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Categoria.class.getName())));

	}

}
