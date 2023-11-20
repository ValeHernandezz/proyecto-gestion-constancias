package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Evento;
import com.entities.Tutor;
import com.services.eventos.EventoBeanRemote;

public class ServiceEvento {

	private static EventoBeanRemote getService() {
		try {
			EventoBeanRemote constanciaBean = (EventoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/EventoBean!com.services.eventos.EventoBeanRemote");
			return constanciaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static Evento crearEvento(Evento oEvento) {
		try {
			var eventoBean = getService();
			return eventoBean.crearEvento(oEvento);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Evento> listarEventos() {
		try {
			var eventoBean = getService();
			return eventoBean.listarEventos();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Evento> listarEventosFiltro(String filtro) {
		try {
			var eventoBean = getService();
			return eventoBean.listarEventosFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static Evento buscarEventoPorId(Long id) {
		try {
			var eventoBean = getService();
			return eventoBean.buscarEventoPorId(id);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean asignarTutorAEvento(Evento oEvento, Tutor oTutor) {
		try {
			var eventoBean = getService();
			return eventoBean.asignarTutorAEvento(oEvento, oTutor);
		} catch (Exception e) {
			return false;
		}
	}
}
