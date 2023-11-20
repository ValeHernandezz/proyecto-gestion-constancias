package com.services.constancia.mail;

import javax.ejb.Remote;

import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.Usuario;

@Remote
public interface EmailBeanRemote {
	
	public boolean enviarMail(Constancia oConstancia, Estudiante oEstudiante);
	
	public void enviarMailCuentaRegistrada(Usuario oUsuario);
	
	public void enviarMailCuentaConfirmada(Usuario oUsuario);
}
