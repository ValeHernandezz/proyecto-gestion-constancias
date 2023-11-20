package services;

import javax.naming.InitialContext;

import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.Usuario;
import com.services.constancia.mail.EmailBeanRemote;

public class ServiceEmail {
	private static EmailBeanRemote getService() {
		try {
			EmailBeanRemote emailBean = (EmailBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/EmailBean!com.services.constancia.mail.EmailBeanRemote");
			return emailBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean mandarMail(Constancia oConstancia, Estudiante oEstudiante) {
		try {
			var emailBean = getService();
			return emailBean.enviarMail(oConstancia, oEstudiante);
		} catch (Exception e) {
			return false;
		}
	}

	public static void mandarMailRegistro(Usuario oEstudiante) {
		try {
			var emailBean = getService();
			emailBean.enviarMailCuentaRegistrada(oEstudiante);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void mandarMailConfirmado(Usuario oEstudiante) {
		try {
			var emailBean = getService();
			emailBean.enviarMailCuentaConfirmada(oEstudiante);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
