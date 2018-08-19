package com.cursomc.service.exception;

import java.io.Serializable;

public class Erro implements Serializable {

	private static final long serialVersionUID = 450215305860438421L;
	private String mensagemUsuario;
	private String mensagemDesenvolvedor;

	public Erro() {
	}

	public Erro(String mensagemUsuario, String mensagemDesenvolvimento) {
		super();
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvimento;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}

}