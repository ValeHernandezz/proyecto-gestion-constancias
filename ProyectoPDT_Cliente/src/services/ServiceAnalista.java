package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Analista;
import com.services.usuario.analista.AnalistaBeanRemote;

public class ServiceAnalista {

	private static AnalistaBeanRemote getService() {
		try {
			AnalistaBeanRemote analistaBean = (AnalistaBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/AnalistaBean!com.services.usuario.analista.AnalistaBeanRemote");
			return analistaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearAnalista(Analista oAnalista) {
		try {
			var analistaBean = getService();
			return analistaBean.crearAnalista(oAnalista);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizarAnalista(Analista oAnalista) {
		try {
			var analistaBean = getService();
			return analistaBean.actualizarAnalista(oAnalista);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Analista> listarAnalistas() {
		try {
			var analistaBean = getService();
			return analistaBean.listarAnalistas();
		} catch (Exception e) {
			return null;
		}
	}

}
