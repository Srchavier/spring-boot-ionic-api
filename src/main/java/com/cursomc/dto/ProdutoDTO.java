package com.cursomc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private BigDecimal valor;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Long id, String nome, BigDecimal valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
