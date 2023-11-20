package services;

import java.util.ArrayList;
import javax.naming.InitialContext;
import com.entities.EventoEstudiante;
import com.services.eventos.eventoEstudiante.EventoEstudianteBeanRemote;

public class ServiceEventoEstudiante {

	private static EventoEstudianteBeanRemote getService() {
		try {
			EventoEstudianteBeanRemote eventoEstudianteBean = (EventoEstudianteBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_EJB/EventoEstudianteBean!com.services.eventos.eventoEstudiante.EventoEstudianteBeanRemote");
			return eventoEstudianteBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearEventoEstudiante(EventoEstudiante oEventoEstudiante) {
		try {
			var eventoEstudianteBean = getService();
			return eventoEstudianteBean.crearEventoEstudiante(oEventoEstudiante);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizarEventoEstudiante(EventoEstudiante oEventoEstudiante) {
		try {
			var eventoEstudianteBean = getService();
			return eventoEstudianteBean.actualizarEventoEstudiante(oEventoEstudiante);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean eliminarEventoEstudiante(Long id) {
		try {
			var eventoEstudianteBean = getService();
			return eventoEstudianteBean.eliminarEventoEstudiante(id);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<EventoEstudiante> listarEventoEstudiante() {
		try {
			var eventoEstudianteBean = getService();
			return eventoEstudianteBean.listarEventosEstudiante();
		} catch (Exception e) {
			return null;
		}
	}

}
