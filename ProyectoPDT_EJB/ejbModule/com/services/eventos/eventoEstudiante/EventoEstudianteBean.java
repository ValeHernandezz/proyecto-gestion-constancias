package com.services.eventos.eventoEstudiante;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.EventoEstudiante;
import com.exception.ServiciosException;

@Stateless
public class EventoEstudianteBean implements EventoEstudianteBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public EventoEstudianteBean() {

	}

	@Override
	public boolean crearEventoEstudiante(EventoEstudiante oEventoEstudiante) throws ServiciosException {
		try {
			entityManager.persist(oEventoEstudiante);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el evento estudiante.");
		}
	}

	@Override
	public boolean actualizarEventoEstudiante(EventoEstudiante oEventoEstudiante) throws ServiciosException {
		try {
			entityManager.merge(oEventoEstudiante);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el evento estudiante.");
		}
	}

	@Override
	public boolean eliminarEventoEstudiante(Long idEventoEstudiante) throws ServiciosException {
		try {
			EventoEstudiante oEventoEstudiante = entityManager.find(EventoEstudiante.class, idEventoEstudiante);
			entityManager.remove(oEventoEstudiante);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el evento estudiante.");
		}
	}

	@Override
	public ArrayList<EventoEstudiante> listarEventosEstudiante() throws ServiciosException {
		try {
			TypedQuery<EventoEstudiante> query = entityManager.createQuery("SELECT e FROM EventoEstudiante e",
					EventoEstudiante.class);
			ArrayList<EventoEstudiante> listaEventosEstudiante = (ArrayList<EventoEstudiante>) query.getResultList();
			return listaEventosEstudiante;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los eventos estudiante");
		}
	}

	
	// Ver que poner en el filtro
	@Override
	public ArrayList<EventoEstudiante> listarEventosEstudiante(String filtro) throws ServiciosException {
		try {
			TypedQuery<EventoEstudiante> query = entityManager.createQuery(
					"SELECT e FROM EventoEstudiante e WHERE e.asistencia LIKE :filtro", EventoEstudiante.class);
			ArrayList<EventoEstudiante> listaEventosEstudiante = (ArrayList<EventoEstudiante>) query.getResultList();
			return listaEventosEstudiante;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los eventos estudiante según el filtro seleccionado");
		}
	}

}
