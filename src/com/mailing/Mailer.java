package com.mailing;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
    private static Session session = null;
    private static Session getSession(){
        if(session==null){
            String host = "smtp.gmail.com";
            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.required", "true");
            prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(System.getProperty("email"), System.getProperty("password"));
                }
            });
        }
        return session;
    }
    public static boolean sendMail(String to,String subject,String text){
        try {
			MimeMessage message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(System.getProperty("email")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			return true;
		}catch(MessagingException e) {
			System.out.println("Could not Send Mail");
			e.printStackTrace();
		}
        return false;
    }
}
