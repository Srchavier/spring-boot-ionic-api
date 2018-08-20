package com.cursomc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.cursomc.model.PagamentoComBoleto;

@Service
public class boletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pag, LocalDateTime instancePedido) {

		LocalDate local = instancePedido.toLocalDate();
		LocalDate LocalDatelocal1 = local.plusDays(7);
		if (LocalDatelocal1.getDayOfWeek().ordinal() == 7) {
			LocalDatelocal1.plusDays(1);
		}
		pag.setDataVencimento(LocalDatelocal1);
	}
}
