package com.services.eventos;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.exception.ServiciosException;
import com.entities.Evento;
import com.entities.EventoTutor;
import com.entities.EventoTutorPK;
import com.entities.Tutor;

@Stateless
public class EventoBean implements EventoBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

	public EventoBean() {

	}

	@Override
	public Evento crearEvento(Evento evento) throws ServiciosException {

		try {
			entityManager.persist(evento);
			entityManager.flush();
			return evento;

		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el evento.");
		}
	}

	@Override
	public boolean actualizarEvento(Evento evento) throws ServiciosException {
		try {
			entityManager.merge(evento);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el evento.");
		}
	}

	@Override
	public boolean borrarEvento(Long idEvento) throws ServiciosException {
		try {
			Evento evento = entityManager.find(Evento.class, idEvento);
			entityManager.remove(evento);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el evento.");
		}

	}

	@Override
	public ArrayList<Evento> listarEventos() throws ServiciosException {
		try {
			TypedQuery<Evento> query = entityManager.createQuery("SELECT e FROM Evento e ", Evento.class);
			ArrayList<Evento> listaEvento = (ArrayList<Evento>) query.getResultList();
			return listaEvento;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los eventos.");
		}
	}

	@Override
	public ArrayList<Evento> listarEventosFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Evento> query = entityManager
					.createQuery("SELECT e FROM Evento e WHERE e.titulo LIKE :filtro", Evento.class)
					.setParameter("filtro", "%" + filtro + "%");
			return (ArrayList<Evento>) query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo listar los estudiantes con el filtro proporcionado.");
		}
	}

	@Override
	public boolean asignarTutorAEvento(Evento oEvento, Tutor oTutor) {
		try {

			EventoTutorPK oEventoTutorPK = new EventoTutorPK(2L, oEvento.getIdEvento(), oTutor.getIdTutor());

			EventoTutor oEventoTutor = new EventoTutor(oEventoTutorPK);

			entityManager.persist(oEventoTutor);
			entityManager.flush();

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public Evento buscarEventoPorId(Long id) throws ServiciosException {
		try {
			TypedQuery<Evento> query = entityManager
					.createQuery("SELECT e FROM Evento e WHERE e.idEvento = :id", Evento.class).setParameter("id", id);
			var evento = (ArrayList<Evento>) query.getResultList();
			return evento.get(0);
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo listar los estudiantes con el filtro proporcionado.");
		}
	}

}
