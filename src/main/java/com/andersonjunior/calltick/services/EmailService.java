package com.andersonjunior.calltick.services;

import javax.mail.internet.MimeMessage;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.User;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

	void sendTicketConfirmationEmail(Called obj);
	void sendTicketFinishEmail(Called obj);
	void sendNewPasswordEmail(User user, String newPassword);
	
	void sendEmail(SimpleMailMessage msg);

	void sendTicketConfirmationHtmlEmail(Called obj);
	void sendHtmlEmail(MimeMessage msg);

}