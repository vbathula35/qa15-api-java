package com.example.demo.service;

public interface EmailServiceInterface {
	void sendSimpleMailMessage(String name, String to, String token);
	void sendMimeMessageWithAttachments(String name, String to, String token);
	void sendHtmlEmail(String name, String to, String token);
	void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
}
