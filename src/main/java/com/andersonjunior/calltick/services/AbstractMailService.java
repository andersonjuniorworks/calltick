package com.andersonjunior.calltick.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractMailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendTicketConfirmationEmail(Called obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromTicket(obj);
		sendEmail(sm);
	}

	@Override
	public void sendNewPasswordEmail(User user, String newPassword) {
		SimpleMailMessage sm = prepareNewPasswordEmail(user, newPassword);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPassword) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Recuperação de Senha - Calltick");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPassword);
		return sm;
	}

	@Override
	public void sendTicketFinishEmail(Called obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromFinishTicket(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromTicket(Called obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Chamado Aberto - Calltick");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromFinishTicket(Called obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Chamado Finalizado - Calltick");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	protected String htmlFromTemplateTicket(Called called) {
		Context context = new Context();
		context.setVariable("ticket", called);
		return templateEngine.process("email/newTicket", context);
	}

	@Override
	public void sendTicketConfirmationHtmlEmail(Called obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromTicket(obj);
			sendHtmlEmail(mm);
		} catch(MessagingException ex) {
			sendTicketConfirmationEmail(obj);
		}	
	}

	protected MimeMessage prepareMimeMessageFromTicket(Called called) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(called.getUser().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Chamado Aberto - Calltick");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateTicket(called), true);
		return mimeMessage;
	}
	
}
