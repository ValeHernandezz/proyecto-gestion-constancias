package com.services.constancia;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Constancia;
import com.exception.ServiciosException;

@Stateless
public class ConstanciaBean implements ConstanciaBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public ConstanciaBean() {

	}

	public boolean crearConstancia(Constancia oConstancia) throws ServiciosException {
		try {
			entityManager.persist(oConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear la constancia.");
		}
	}

	public boolean actualizarConstancia(Constancia oConstancia) throws ServiciosException {
		try {
			entityManager.merge(oConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar la constancia.");
		}
	}

	public boolean eliminarConstancia(Long idConstancia) throws ServiciosException {
		try {
			Constancia oConstancia = entityManager.find(Constancia.class, idConstancia);
			entityManager.remove(oConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar la constancia.");
		}
	}

	public ArrayList<Constancia> listarConstancias() throws ServiciosException {
		try {
			TypedQuery<Constancia> query = entityManager.createQuery(
					"SELECT c FROM Constancia c JOIN FETCH c.estado JOIN FETCH c.estudiante JOIN FETCH c.evento",
					Constancia.class);
			ArrayList<Constancia> listaConstancias = (ArrayList<Constancia>) query.getResultList();
			return listaConstancias;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las constancias.");
		}
	}

	public ArrayList<Constancia> listarConstanciasFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Constancia> query = entityManager
					.createQuery("SELECT c FROM Constancia c WHERE c.detalle LIKE :filtro", Constancia.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Constancia> listaConstanciasFiltro = (ArrayList<Constancia>) query.getResultList();
			return listaConstanciasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las constancias según el filtro seleccionado.");
		}
	}

}
