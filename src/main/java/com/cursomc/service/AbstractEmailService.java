package com.cursomc.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cursomc.model.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String emailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleEmailMessageFromPedido(pedido);
		sendEmail(sm);
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

	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);

	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		MimeMessage sm;
		try {
			sm = prepareMimeMessageFromPedido(pedido);
			sendHtmlEmail(sm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(pedido);

		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(pedido.getCliente().getEmail());
		mmh.setFrom(emailSender);
		mmh.setSubject("Pedido confirmado código:" + pedido.getId() + " !");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(pedido), true);
		return mimeMessage;
	}
}
