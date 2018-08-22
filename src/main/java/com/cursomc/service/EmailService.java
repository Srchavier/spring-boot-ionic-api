package com.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import com.cursomc.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);
}
