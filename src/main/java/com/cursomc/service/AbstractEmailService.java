package com.cursomc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.cursomc.model.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String emailSender;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleEmailMessageFromPedido(pedido);
		sendEmail(sm);
	}

	@Override
	public void sendEmail(SimpleMailMessage msg) {

	}

	protected SimpleMailMessage prepareSimpleEmailMessageFromPedido(Pedido pedido) {

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(emailSender);
		sm.setSubject("Pedido confirmado código:" + pedido.getId() + " !");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		return sm;
	}

}
