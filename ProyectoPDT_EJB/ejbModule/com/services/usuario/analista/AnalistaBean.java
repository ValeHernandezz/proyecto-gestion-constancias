package com.services.usuario.analista;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.exception.ServiciosException;

import com.entities.Analista;

@Stateless
public class AnalistaBean implements AnalistaBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

	public AnalistaBean() {

	}

	@Override
	public boolean crearAnalista(Analista analista) throws ServiciosException {
		try {
			entityManager.persist(analista);
			entityManager.flush();
			return true;

		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el analista.");
		}

	}

	@Override
	public boolean actualizarAnalista(Analista analista) throws ServiciosException {
		try {
			entityManager.merge(analista);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el analista.");
		}
	}

	@Override
	public boolean borrarAnalista(Long idAnalista) throws ServiciosException {
		try {
			Analista analista = entityManager.find(Analista.class, idAnalista);
			entityManager.remove(analista);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el analista.");
		}

	}

	@Override
	public ArrayList<Analista> listarAnalistas() throws ServiciosException {
		try {
			TypedQuery<Analista> query = entityManager.createQuery("SELECT a FROM Analista a JOIN FETCH a.usuario",
					Analista.class);
			ArrayList<Analista> listaAnalistas = (ArrayList<Analista>) query.getResultList();
			return listaAnalistas;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los analistas con usuarios.");
		}
	}

	@Override
	public ArrayList<Analista> listarAnalistasFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Analista> query = entityManager
					.createQuery("SELECT e FROM Analista e WHERE e.descripcion LIKE :filtro", Analista.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Analista> listaAnalistasFiltro = (ArrayList<Analista>) query.getResultList();
			return listaAnalistasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los analistas según el filtro proporcionado.");
		}
	}
}
