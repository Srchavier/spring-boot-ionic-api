package com.cursomc.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"), QUITADO(2, "Quitando"), CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (EstadoPagamento c : EstadoPagamento.values()) {
			if (cod.equals(c.getCod())) {
				return c;
			}
		}
		throw new IllegalArgumentException("Codigo inválido: " + cod);
	}


}
