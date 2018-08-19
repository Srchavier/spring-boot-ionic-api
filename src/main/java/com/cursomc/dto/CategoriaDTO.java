package com.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty()
	@Size(min = 5, max = 80)
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

}
