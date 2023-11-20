package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Estado;
import com.services.estado.EstadoBeanRemote;

public class ServiceEstado {
	private static EstadoBeanRemote getService() {
		try {
			EstadoBeanRemote constanciaBean = (EstadoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/EstadoBean!com.services.estado.EstadoBeanRemote");
			return constanciaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearEstado(Estado oEstado) {
		try {
			var estadoBean = getService();
			return estadoBean.crearEstado(oEstado);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Estado> listarEstados() {
		try {
			var estadoBean = getService();
			return estadoBean.listarEstados();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<Estado> listarEstadosFiltro(String filtro) {
		try {
			var estadoBean = getService();
			return estadoBean.listarEstadosFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}
}
