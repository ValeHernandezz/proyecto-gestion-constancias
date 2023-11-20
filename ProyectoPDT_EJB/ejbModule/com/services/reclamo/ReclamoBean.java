package com.services.reclamo;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Reclamo;
import com.exception.ServiciosException;

@Stateless
public class ReclamoBean implements ReclamoBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public ReclamoBean() {

	}

	public boolean crearReclamo(Reclamo oReclamo) throws ServiciosException {
		try {
			entityManager.persist(oReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el reclamo.");
		}
	}

	public boolean actualizarReclamo(Reclamo oReclamo) throws ServiciosException {
		try {
			entityManager.merge(oReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el reclamo.");
		}
	}

	public boolean eliminarReclamo(Long idReclamo) throws ServiciosException {
		try {
			Reclamo oReclamo = entityManager.find(Reclamo.class, idReclamo);
			entityManager.remove(oReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el reclamo.");
		}
	}

	public ArrayList<Reclamo> listarReclamos() throws ServiciosException {
		try {
			TypedQuery<Reclamo> query = entityManager.createQuery("SELECT r FROM Reclamo r", Reclamo.class);
			ArrayList<Reclamo> listaReclamos = (ArrayList<Reclamo>) query.getResultList();
			return listaReclamos;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los reclamos.");
		}
	}

	public ArrayList<Reclamo> listarReclamoFiltro(String filtro) throws ServiciosException {
		try {
			Long filtroId = Long.parseLong(filtro); // Convertir el filtro String a Long
			TypedQuery<Reclamo> query = entityManager
					.createQuery("SELECT r FROM Reclamo r WHERE r.idEstudiante = :filtro", Reclamo.class)
					.setParameter("filtro", filtroId); // Usar el Long convertido como parámetro
			ArrayList<Reclamo> listaReclamoFiltro = (ArrayList<Reclamo>) query.getResultList();
			return listaReclamoFiltro;
		} catch (NumberFormatException e) {
			// Manejar la excepción si el filtro no es un número válido
			throw new ServiciosException("ERROR - El filtro debe ser un número válido.");
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los reclamos.");
		}
	}

}
