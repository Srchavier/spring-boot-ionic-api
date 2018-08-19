package com.cursomc.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cursomc.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento;

	public PagamentoComBoleto() {

	}

	public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, LocalDate dataPagamento,
			LocalDate dataVencimento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
