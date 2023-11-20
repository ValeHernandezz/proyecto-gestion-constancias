package com.services.usuario.tutor;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.exception.ServiciosException;

import com.entities.Tutor;

@Stateless
public class TutorBean implements TutorBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

	public TutorBean() {

	}

	@Override
	public boolean crearTutor(Tutor tutor) throws ServiciosException {
		try {
			entityManager.persist(tutor);
			entityManager.flush();
			return true;

		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el tutor.");

		}
	}

	@Override
	public boolean actualizarTutor(Tutor tutor) throws ServiciosException {
		try {
			entityManager.merge(tutor);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el tutor.");
		}

	}

	@Override
	public boolean borrarTutor(Long idTutor) throws ServiciosException {
		try {
			Tutor tutor = entityManager.find(Tutor.class, idTutor);
			entityManager.remove(tutor);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el tutor.");
		}

	}

	@Override
	public ArrayList<Tutor> listarTutores() throws ServiciosException {
		try {

			TypedQuery<Tutor> query = entityManager.createQuery("SELECT t FROM Tutor t JOIN FETCH t.usuario",
				    Tutor.class);
			ArrayList<Tutor> listaTutor = (ArrayList<Tutor>) query.getResultList();
			return listaTutor;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los tutores.");
		}
	}

	@Override
	public ArrayList<Tutor> listarTutoresFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Tutor> query = entityManager
					.createQuery("SELECT e FROM Tutor e WHERE e.descripcion LIKE :filtro", Tutor.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Tutor> listaTutoresFiltro = (ArrayList<Tutor>) query.getResultList();
			return listaTutoresFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los tutores según el filtro proporcionado.");
		}
	}
}
