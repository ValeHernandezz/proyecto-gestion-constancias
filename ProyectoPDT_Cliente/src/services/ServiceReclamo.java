package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.EstadoReclamo;
import com.entities.Reclamo;
import com.services.reclamo.ReclamoBeanRemote;
import com.services.reclamo_estado.EstadoReclamoBeanRemote;

public class ServiceReclamo {

	private static ReclamoBeanRemote getService() {
		try {
			ReclamoBeanRemote reclamoBean = (ReclamoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/ReclamoBean!com.services.reclamo.ReclamoBeanRemote");
			return reclamoBean;

		} catch (Exception e) {
			return null;
		}

	}

	private static EstadoReclamoBeanRemote getServiceEstadoReclamo() {
		try {
			EstadoReclamoBeanRemote reclamoBean = (EstadoReclamoBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_EJB/EstadoReclamoBean!com.services.reclamo_estado.EstadoReclamoBeanRemote");
			return reclamoBean;

		} catch (Exception e) {
			return null;
		}

	}

	public static boolean crearReclamo(Reclamo oReclamo) {
		try {
			var reclamoBean = getService();
			return reclamoBean.crearReclamo(oReclamo);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean actualizarReclamo(Reclamo oReclamo) {
		try {
			var reclamoBean = getService();
			return reclamoBean.actualizarReclamo(oReclamo);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Reclamo> listarReclamos() {
		try {
			var reclamoBean = getService();
			return reclamoBean.listarReclamos();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Reclamo> listarReclamosFiltro(String filtro) {
		try {
			var reclamoBean = getService();
			return reclamoBean.listarReclamoFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean borrarReclamo(Reclamo oReclamo) {
		try {
			var reclamoBean = getService();
			return reclamoBean.eliminarReclamo(oReclamo.getIdReclamo());
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean cambiarEstadoReclamo(EstadoReclamo oEstadoReclamo) {
		try {
			var reclamoEstadoBean = getServiceEstadoReclamo();
			return reclamoEstadoBean.crearEstadoReclamo(oEstadoReclamo);
		} catch (Exception e) {
			return false;
		}
	}

	public static String getDetalleDelEstado(long idReclamo) {
		return "";
	}

}