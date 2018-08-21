package com.cursomc.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.enums.EstadoPagamento;
import com.cursomc.enums.TipoCliente;
import com.cursomc.model.Categoria;
import com.cursomc.model.Cidade;
import com.cursomc.model.Cliente;
import com.cursomc.model.Endereco;
import com.cursomc.model.Estado;
import com.cursomc.model.ItemPedido;
import com.cursomc.model.Pagamento;
import com.cursomc.model.PagamentoComBoleto;
import com.cursomc.model.PagamentoComCartao;
import com.cursomc.model.Pedido;
import com.cursomc.model.Produto;
import com.cursomc.repository.CategoriaRepository;
import com.cursomc.repository.CidadeRepository;
import com.cursomc.repository.ClienteRepository;
import com.cursomc.repository.EnderecoRepository;
import com.cursomc.repository.EstadoRepository;
import com.cursomc.repository.ItemPedidoRepository;
import com.cursomc.repository.PagamentoRepository;
import com.cursomc.repository.PedidoRepository;
import com.cursomc.repository.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() {
		DateTimeFormatter aFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80));
		Produto p4 = new Produto(null, "Mesa de escritório", new BigDecimal(300));
		Produto p5 = new Produto(null, "Toalha", new BigDecimal(50));
		Produto p6 = new Produto(null, "Colcha", new BigDecimal(200));
		Produto p7 = new Produto(null, "TV true color", new BigDecimal(1200));
		Produto p8 = new Produto(null, "Roçadeira", new BigDecimal(800));
		Produto p9 = new Produto(null, "Abajour", new BigDecimal(100));
		Produto p10 = new Produto(null, "Pendente", new BigDecimal(180));
		Produto p11 = new Produto(null, "Shampoo", new BigDecimal(90));

		cat1.getProdutos().addAll(Arrays.asList(p2, p1, p3));

		cat2.getProdutos().addAll(Arrays.asList(p2, p4, p1));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		repository.saveAll(Arrays.asList(cat2, cat1, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlãndia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est1.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente clint1 = new Cliente(null, "Maria silva", "maria@hotmail.com", "90652158968",
				TipoCliente.PESSOA_FISICA);
		clint1.getTelefones().addAll(Arrays.asList("985620178", "34675873"));

		Endereco e1 = new Endereco(null, "Rua flores", 300, "Apto 303", "Jardim", "3895546", clint1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", 105, "sala 800", "Centro", "389232", clint1, cid2);

		clint1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(clint1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		Pedido ped1 = new Pedido(null, LocalDateTime.parse("01-08-2018 04:24", aFormatter), e1, clint1);
		Pedido ped2 = new Pedido(null, LocalDateTime.parse("10-10-2018 04:24", aFormatter), e2, clint1);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null,
				LocalDate.parse("2019-01-01"));
		ped2.setPagamento(pag2);

		clint1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido item1 = new ItemPedido(ped1, p1, new BigDecimal(0), 1, new BigDecimal(2000));
		ItemPedido item2 = new ItemPedido(ped1, p3, new BigDecimal(0), 2, new BigDecimal(80));
		ItemPedido item3 = new ItemPedido(ped2, p2, new BigDecimal(100), 1, new BigDecimal(800));

		ped1.getItens().addAll(Arrays.asList(item1, item2));
		ped2.getItens().addAll(Arrays.asList(item3));

		p1.getItens().addAll(Arrays.asList(item1));
		p2.getItens().addAll(Arrays.asList(item3));
		p3.getItens().addAll(Arrays.asList(item2));

		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
	}

}
