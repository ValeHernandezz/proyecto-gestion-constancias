package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Tipo;
import com.services.constancia.tipo.TipoBeanRemote;

public class ServiceTipo {

	private static TipoBeanRemote getService() {
		try {
			TipoBeanRemote tipoBean = (TipoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/TipoBean!com.services.constancia.tipo.TipoBeanRemote");
			return tipoBean;

		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearTipoConstancia(Tipo tipoConstancia) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.crearTipo(tipoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizarTipoConstancia(Tipo tipoConstancia) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.actualizarTipo(tipoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean eliminarTipoConstancia(Long idTipoConstancia) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.eliminarTipo(idTipoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Tipo> listarTiposConstancias() {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.listarTipos();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Tipo> listarTiposConstanciasFiltro(String filtro) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.listarTipos(filtro);
		} catch (Exception e) {
			return null;
		}
	}

}
