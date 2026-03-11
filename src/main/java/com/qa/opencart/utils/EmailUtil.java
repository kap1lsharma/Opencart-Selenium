package com.qa.opencart.utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtil {

	public static void sendTestReport(String reportPath) {

		final String senderEmail = "vatschetanya@gmail.com";
		final String password = "jfilvviynfvjxoan";
		final String recipient = "vatschetanya@gmail.com";

		// smtp server authentication
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		// create session with authentication
		Session session = Session.getInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(senderEmail, password);
			}

		});

		session.setDebug(true);

		// create email message
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("Test email from QA Automation");
//			message.setText("Hello \n this is a new line \n this is again a new line ");

//			Email Body Part
			MimeBodyPart attachmentPart = new MimeBodyPart();
//			String filePath = System.getProperty("user.dir") + "/test-output/emailable-report.html";
			System.out.println("Attchment part is : " + reportPath);
			attachmentPart.attachFile(new File(reportPath));

			// combine body and attachment parts
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(attachmentPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);

			// send Email
			Transport.send(message);
			System.out.println("Email sent successfully ");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
