package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Constancia;
import com.services.constancia.ConstanciaBeanRemote;

public class ServiceConstancia {

	private static ConstanciaBeanRemote getService() {
		try {
			ConstanciaBeanRemote constanciaBean = (ConstanciaBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/ConstanciaBean!com.services.constancia.ConstanciaBeanRemote");
			return constanciaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearConstancia(Constancia oConstancia) {
		try {
			var constanciaBean = getService();
			return constanciaBean.crearConstancia(oConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizarConstancia(Constancia oConstancia) {
		try {
			var constanciaBean = getService();
			return constanciaBean.actualizarConstancia(oConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean eliminarConstancia(Long id) {
		try {
			var constanciaBean = getService();
			return constanciaBean.eliminarConstancia(id);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Constancia> listarConstancias() {
		try {
			var constanciaBean = getService();
			return constanciaBean.listarConstancias();
		} catch (Exception e) {
			return null;
		}
	}
}
