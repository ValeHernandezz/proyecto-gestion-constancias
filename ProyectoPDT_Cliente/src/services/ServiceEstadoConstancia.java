package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.EstadoConstancia;
import com.services.constancia.estado.EstadoConstanciaBeanRemote;

public class ServiceEstadoConstancia {
	private static EstadoConstanciaBeanRemote getService() {
		try {
			EstadoConstanciaBeanRemote estadoConstanciaBean = (EstadoConstanciaBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_EJB/EstadoConstanciaBean!com.services.constancia.estado.EstadoConstanciaBeanRemote");
			return estadoConstanciaBean;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean crearEstadoConstancia(EstadoConstancia oEstadoConstancia) {
		try {
			var estadoConstanciaBean = getService();			
			return estadoConstanciaBean.crearEstadoConstancia(oEstadoConstancia);
		} catch (Exception e) {
			return false;
		}
	}
	
	public static ArrayList<EstadoConstancia> listarEstadosContancias(){
		try {
			var estadoConstanciaBean = getService();			
			return estadoConstanciaBean.listarEstadosConstancias();
		} catch (Exception e) {
			return new ArrayList<EstadoConstancia>();
		}
	}
}
