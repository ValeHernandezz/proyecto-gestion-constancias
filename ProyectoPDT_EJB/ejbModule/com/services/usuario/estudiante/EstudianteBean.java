package com.services.usuario.estudiante;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.exception.ServiciosException;

import com.entities.Estudiante;

@Stateless
public class EstudianteBean implements EstudianteBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

	public EstudianteBean() {

	}

	@Override
	public boolean crearEstudiante(Estudiante estudiante) throws ServiciosException {
		try {
			entityManager.persist(estudiante);
			entityManager.flush();
			return true;

		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el estudiante.");
		}
	}

	@Override
	public boolean actualizarEstudiante(Estudiante estudiante) throws ServiciosException {
		try {
			entityManager.merge(estudiante);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el estudiante.");
		}
	}

	@Override
	public boolean borrarEstudiante(Long idEstudiante) throws ServiciosException {
		try {
			Estudiante estudiante = entityManager.find(Estudiante.class, idEstudiante);
			entityManager.remove(estudiante);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el e.");
		}

	}

	@Override
	public ArrayList<Estudiante> listarEstudiantes() throws ServiciosException {
		try {
			TypedQuery<Estudiante> query = entityManager.createQuery("SELECT e FROM Estudiante e JOIN FETCH e.usuario",
					Estudiante.class);
			ArrayList<Estudiante> listaEstudiante = (ArrayList<Estudiante>) query.getResultList();
			return listaEstudiante;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estudiantes.");
		}
	}

	@Override
	public ArrayList<Estudiante> listarEstudiantesFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Estudiante> query = entityManager
					.createQuery("SELECT e FROM Estudiante e WHERE e.descripcion LIKE :filtro", Estudiante.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Estudiante> listaEstudiantesFiltro = (ArrayList<Estudiante>) query.getResultList();
			return listaEstudiantesFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los estudiantes según el filtro proporcionado.");
		}
	}

}
