package com.cursomc.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.enums.EstadoPagamento;
import com.cursomc.model.ItemPedido;
import com.cursomc.model.PagamentoComBoleto;
import com.cursomc.model.Pedido;
import com.cursomc.repository.ItemPedidoRepository;
import com.cursomc.repository.PagamentoRepository;
import com.cursomc.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private boletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

	public Pedido salvar(@Valid Pedido pedido) {
		pedido.setId(null);
		pedido.setInstance(LocalDateTime.now());
		pedido.setCliente(clienteService.buscarPorId(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pag = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pag, pedido.getInstance());
		}

		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());

		for (ItemPedido i : pedido.getItens()) {
			i.setDesconto(new BigDecimal(0));
			i.setProduto(produtoService.buscarPorId(i.getProduto().getId()));
			i.setValor(i.getProduto().getValor());
			i.setPedido(pedido);
		}

		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfirmationEmail(pedido);
		return pedido;
	}

}
