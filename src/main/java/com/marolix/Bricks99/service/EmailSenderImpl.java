package com.marolix.Bricks99.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderImpl implements EmailSender {



@Autowired
	    private JavaMailSender mailSender;

	
	    public void sendEmail(String to, String subject, String body) {
	    	System.out.println("send email invoked");
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);
	        System.out.println("before send");
	        mailSender.send(message);
	        System.out.println("after send");
	    }
	}


