package com.cursomc;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.entity.Categoria;
import com.cursomc.entity.Cidade;
import com.cursomc.entity.Cliente;
import com.cursomc.entity.Endereco;
import com.cursomc.entity.Estado;
import com.cursomc.entity.Produto;
import com.cursomc.enums.TipoCliente;
import com.cursomc.repository.CategoriaRepository;
import com.cursomc.repository.CidadeRepository;
import com.cursomc.repository.ClienteRepository;
import com.cursomc.repository.EnderecoRepository;
import com.cursomc.repository.EstadoRepository;
import com.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", new BigDecimal(2500));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80));

		cat1.getProdutos().addAll(Arrays.asList(p2, p1, p3));

		cat2.getProdutos().addAll(Arrays.asList(p2));

		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		repository.saveAll(Arrays.asList(cat2, cat1));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlãndia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est1.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente clint1 = new Cliente(null, "Maria silva", "maria@hotmail.com", "90652158968", TipoCliente.PESSOAFISICA);
		clint1.getTelefones().addAll(Arrays.asList("985620178", "34675873"));

		Endereco e1 = new Endereco(null, "Rua flores", 300, "Apto 303", "Jardim", "3895546", clint1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", 105, "sala 800", "Centro", "389232", clint1, cid2);

		clint1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(clint1);
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}
}
