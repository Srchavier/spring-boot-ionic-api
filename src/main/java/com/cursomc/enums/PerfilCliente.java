package com.cursomc.enums;

public enum PerfilCliente {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private int cod;
	private String descricao;

	private PerfilCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static PerfilCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (PerfilCliente c : PerfilCliente.values()) {
			if (cod.equals(c.getCod())) {
				return c;
			}
		}
		throw new IllegalArgumentException("Codigo inválido: " + cod);
	}

}
