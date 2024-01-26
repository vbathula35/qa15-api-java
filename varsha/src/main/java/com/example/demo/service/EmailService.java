package com.example.demo.service;
import java.io.File;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.utils.GeneralUtilities;


@Service
public class EmailService {
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.username")
	private String fromEmail;
	GeneralUtilities generalUtilities;
	
	
	private JavaMailSender emailSender;
	
	private TemplateEngine templateEngine;
	
	public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine){
		this.templateEngine = templateEngine;
		this.emailSender = emailSender;
    }

	
	@Async("asyncTaskExecutor")
	public void sendSimpleMailMessage(String name, String to, String token) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setSubject("User Verification Email");
			message.setFrom(fromEmail);
			message.setTo(to);
			message.setText(generalUtilities.getEmailContent(name, token));	
			emailSender.send(message);
		}catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}
	@Async("asyncTaskExecutor")
	public void sendMimeMessageWithAttachments(String name, String to, String token) {
		try {
			MimeMessage message = getMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setPriority(1);
			helper.setSubject("User Verification Email");
			helper.setFrom(fromEmail);
			helper.setTo(to);
			helper.setText(generalUtilities.getEmailContent(name, token));
			FileSystemResource file = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/IMM5788.pdf"));
			helper.addAttachment(file.getFilename(), file);
			emailSender.send(message);
		}catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
		
	}
	@Async("asyncTaskExecutor")
	public void sendHtmlEmail(String name, String to, String token,  String fileSource) {
		
		try {
			Context context = new Context();
			context.setVariables(Map.of("name", name, "token", token));
			String text = templateEngine.process("emailtemplate", context);
			MimeMessage message = getMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setPriority(1);
			helper.setSubject("User Verification Email");
			helper.setFrom(fromEmail);
			helper.setTo(to);
			helper.setText(text, true);
			if (!fileSource.isEmpty()) {
				FileSystemResource file = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/IMM5788.pdf"));
				helper.addAttachment(file.getFilename(), file);
			}
			emailSender.send(message);
		}catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
		
	}
	@Async("asyncTaskExecutor")
	public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {
		try {
			MimeMessage message = getMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setPriority(1);
			helper.setSubject("User Verification Email");
			helper.setFrom(fromEmail);
			helper.setTo(to);
			
			
			Context context = new Context();
			context.setVariables(Map.of("name", name, "token", token));
			String text = templateEngine.process("emailtemplate", context);
			
			
			// Add HTML email body
			MimeMultipart mimeMultiPart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(text, "text/html");
			mimeMultiPart.addBodyPart(messageBodyPart);
			
			//Add images to the email body
			BodyPart imageBodyPart = new MimeBodyPart();
			DataSource dataSource = new FileDataSource(System.getProperty("user.home") + "/Downloads/test.png");
			imageBodyPart.setDataHandler(new DataHandler(dataSource));
			imageBodyPart.setHeader("Content-ID", "image");
			mimeMultiPart.addBodyPart(imageBodyPart);
			message.setContent(mimeMultiPart);
			
			FileSystemResource file = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/IMM5788.pdf"));
			helper.addAttachment(file.getFilename(), file);
			emailSender.send(message);
		}catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}

	}
	
	private MimeMessage getMimeMessage() {
		return emailSender.createMimeMessage();
	}

	
}