package context.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Test {

	public static void main(String[] args) {
		String mail = "gestiondeconstanciasutec@gmail.com";
		String password = "ztmeezskdyholoxk";

		for (int i = 0; i < 5; i++) {

			// Configuración de las propiedades del servidor de correo (Gmail)
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			// Autenticación del remitente
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mail, password);
				}
			});

			try {
				// Creación del mensaje de correo
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(mail));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse("nicolas.melendez@estudiantes.utec.edu.uy"));
				message.setSubject("Test");
				message.setText("puto el que lee");

				// Envío del mensaje
				Transport.send(message);

				System.out.println("Correo electrónico enviado exitosamente.");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

	}

}
