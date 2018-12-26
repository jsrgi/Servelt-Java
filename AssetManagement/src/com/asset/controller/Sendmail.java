package com.asset.controller;

//import java.net.PasswordAuthentication;
import java.util.Properties;

//import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.management.RuntimeErrorException;

public class Sendmail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final String senderEmail="gayu.deepi23@gmail.com ";
		final String password="deepika23";
		Properties pro=new Properties();
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", "587");
		Session session=Session.getDefaultInstance(pro,new javax.mail.Authenticator()
				{
			
			    protected 	javax.mail.PasswordAuthentication getPasswordAuthentication()
			    {	
			    	return new javax.mail.PasswordAuthentication(senderEmail, password);
			    }
			
		
				}
				);
		
		
		
		
		try {
			
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress("gayu.deepi23@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("gayu.psg24@gmail.com"));
			message.setSubject("Forget Password");
			message.setText("Your Password");
			Transport.send(message);
			System.out.println("done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
			// TODO: handle exception 
		}
		
	}

}
