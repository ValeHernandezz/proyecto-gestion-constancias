package com.services.constancia.mail;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.Usuario;

/**
 * Session Bean implementation class EmailBean
 */
@Stateless
public class EmailBean implements EmailBeanRemote {

	private String mail = "gestiondeconstanciasutec@gmail.com";
	private String password = "ztmeezskdyholoxk";

	/**
	 * Default constructor.
	 */
	public EmailBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean enviarMail(Constancia oConstancia, Estudiante oEstudiante) {

		String descripcion = null;

		if (oConstancia.getEstado().getDescripcion().equals("En proceso")) {
			descripcion = "El siguiente correo es para notificar al estudiante "
					+ oEstudiante.getUsuario().getNombreCompleto() + " - "
					+ oEstudiante.getUsuario().getDocumento().toString()
					+ " acerca del cambio de estado en su solicitud de constancia.\nSu solicitud se encuentra en proceso, cuando esté finalizada volverá a recibir una notificación.\nEste es un mail generado automáticamente.\nSaludos cordiales,\nSecretaria de UTEC.";
		}
		if (oConstancia.getEstado().getDescripcion().equals("Finalizado")) {
			descripcion = "El siguiente correo es para notificar al estudiante "
					+ oEstudiante.getUsuario().getNombreCompleto() + " - "
					+ oEstudiante.getUsuario().getDocumento().toString()
					+ " acerca del cambio de estado en su solicitud de constancia.\nSu solicitud se encuentra finalizada, puede descargar su constancia desde la plataforma.\nEste es un mail generado automáticamente.\nSaludos cordiales,\nSecretaria de UTEC.";
		}
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
					InternetAddress.parse(oEstudiante.getUsuario().getMailInstitucional()));
			message.setSubject("Solicitud de Constancia de " + oConstancia.getTipo().getNombre());
			message.setText(descripcion);

			// Envío del mensaje
			Transport.send(message);

			System.out.println("Correo electrónico enviado exitosamente.");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println(e);
			return false;
		}

	}

	@Override
	public void enviarMailCuentaRegistrada(Usuario oUsuario) {
		// Configuración de las propiedades del servidor de correo (Gmail)
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.portF", "587");
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(oUsuario.getMailInstitucional()));
			message.setSubject("Solicitud de registro");
			message.setText("Hola " + oUsuario.getNombreCompleto()
					+ ".\nSu cuenta está siendo validada, cuando un analista confirme su registro le llegará un correo.\nEste es un mail generado automáticamente.\nSaludos cordiales,\nSecretaria de UTEC.");

			// Envío del mensaje
			Transport.send(message);

			System.out.println("Correo electrónico enviado exitosamente.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enviarMailCuentaConfirmada(Usuario oUsuario) {
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(oUsuario.getMailInstitucional()));
			message.setSubject("Cuenta confirmada");
			message.setText("Hola " + oUsuario.getNombreCompleto()
					+ ".\nSu cuenta ha sido confirmada, ya puede acceder a la plataforma de Gestión de Constancias.\nEste es un mail generado automáticamente.\nSaludos cordiales,\nSecretaria de UTEC.");

			// Envío del mensaje
			Transport.send(message);

			System.out.println("Correo electrónico enviado exitosamente.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
