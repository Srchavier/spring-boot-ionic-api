package com.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.entity.Categoria;
import com.cursomc.entity.ItemPedido;
import com.cursomc.entity.Pedido;
import com.cursomc.exception.ObjectNotFoundException;
import com.cursomc.repository.ItemPedidoRepository;
import com.cursomc.repository.PedidoRepository;

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
