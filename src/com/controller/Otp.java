package com.controller;

import java.util.Random;

import com.mailing.Mailer;

public class Otp {
	public static String generateOTP() {
		Random rand = new Random();
		String otp = "";
		for(int i=0;i<4;i++) {
			otp = otp+rand.nextInt(10);
		}
		return otp;
	}
	public static boolean mailOTP(String otp,String reciptant) {
		// String host = "smtp.gmail.com";
		// Properties prop = System.getProperties();
		// prop.put("mail.smtp.host", host);
		// prop.put("mail.smtp.port", "465");
		// prop.put("mail.smtp.ssl.enable", "true");
		// prop.put("mail.smtp.auth", "true");
		// prop.put("mail.smtp.starttls.required", "true");
		// prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		// prop.put("mail.smtp.socketFactory.port", "465");
		// prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		// Session session = Session.getInstance(prop, new Authenticator() {
		// 	@Override
		// 	protected PasswordAuthentication getPasswordAuthentication() {
		// 		return new PasswordAuthentication("harrishdhaithya@gmail.com", "cgnhdntrxfakuycp");
		// 	}
		// });
		// try {
		// 	MimeMessage message = new MimeMessage(session);
		// 	message.setFrom(new InternetAddress("harrishdhaithya@gmail.com"));
		// 	message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciptant));
		// 	message.setSubject("Your One Time Password");
		// 	message.setText("OTP: "+otp);
		// 	Transport.send(message);
		// 	return true;
		// }catch(MessagingException e) {
		// 	System.out.println("Could not Send OTP");
		// 	System.out.println(e);
		// 	return false;
		// }
		return Mailer.sendMail(reciptant, "Your One Time Password", "OTP: "+otp);
	}
}
