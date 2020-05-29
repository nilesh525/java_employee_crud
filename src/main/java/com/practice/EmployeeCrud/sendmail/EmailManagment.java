package com.practice.EmployeeCrud.sendmail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.practice.EmployeeCrud.Model.MailSignature;

@Component
public class EmailManagment {	
	public void sendmail(String mail) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("premmohanlal525@gmail.com", "Intelizign@11022");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("premmohanlal525@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
		   msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("premmohanlal525@gmail.com"));
		   msg.setSubject("Registration confirmation!");
		   msg.setContent("PFA", "text/html");
		   msg.setSentDate(new Date());
		   
		   String user = mail.replaceAll("@gmail.com", "");

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Dear "+user+" <br> Thanks for registering with us. <br><br> "
		   		+ "Regards,<br>Abc", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("D:\\Personal\\pp.JPEG");
		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}
	
	public void sendmailcostom(MailSignature mailSig,String pass) throws AddressException, MessagingException, IOException {
		String from=mailSig.getFromMail();String tomail=mailSig.getToMail();String message =mailSig.getMessage();
		String senderpass=pass;
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(from, senderpass);
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("premmohanlal525@gmail.com", false));
		   
		   String tomailuser = tomail.replaceAll("@gmail.com", "");
		   String fromuser = from.replaceAll("@gmail.com", "");
		   
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tomail));
		   msg.setSubject("Message from "+fromuser);
		   msg.setContent("PFA", "text/html");
		   msg.setSentDate(new Date());
		   
		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Dear "+tomailuser+" <br> "+fromuser +" has sent you some message,"
		   		+ " please find below : <br> "+message
		   		+ "<br><br>Regards,<br>Abc", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		/*
		 * MimeBodyPart attachPart = new MimeBodyPart();
		 * 
		 * attachPart.attachFile("D:\\Personal\\pp.JPEG");
		 * multipart.addBodyPart(attachPart);
		 */
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}

}
