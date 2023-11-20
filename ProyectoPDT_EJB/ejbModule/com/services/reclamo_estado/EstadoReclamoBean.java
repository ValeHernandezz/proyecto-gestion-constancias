package com.services.reclamo_estado;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.EstadoReclamo;
import com.exception.ServiciosException;

@Stateless
public class EstadoReclamoBean implements EstadoReclamoBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public EstadoReclamoBean() {

	}

	@Override
	public boolean crearEstadoReclamo(EstadoReclamo oEstadoReclamo) throws ServiciosException {
		try {
			entityManager.persist(oEstadoReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el estado reclamo.");
		}
	}

	@Override
	public boolean actualizarEstadoReclamo(EstadoReclamo oEstadoReclamo) throws ServiciosException {
		try {
			entityManager.merge(oEstadoReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el estado reclamo.");
		}
	}

	@Override
	public boolean eliminarEstadoReclamo(Long idEstadoReclamo) throws ServiciosException {
		try {
			EstadoReclamo oEstadoReclamo = entityManager.find(EstadoReclamo.class, idEstadoReclamo);
			entityManager.remove(oEstadoReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el estado reclamo.");
		}
	}

	@Override
	public ArrayList<EstadoReclamo> listarEstadosReclamo() throws ServiciosException {
		try {
			TypedQuery<EstadoReclamo> query = entityManager.createQuery("SELECT er FROM EstadoReclamo er",
					EstadoReclamo.class);
			ArrayList<EstadoReclamo> listaEstadosReclamo = (ArrayList<EstadoReclamo>) query.getResultList();
			return listaEstadosReclamo;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estados reclamo.");
		}
	}

	@Override
	public ArrayList<EstadoReclamo> listarEstadosReclamo(String filtro) throws ServiciosException {
		try {
			TypedQuery<EstadoReclamo> query = entityManager
					.createQuery("SELECT er FROM EstadoReclamo er WHERE er.detalle LIKE :filtro", EstadoReclamo.class);
			ArrayList<EstadoReclamo> listaEstadosReclamo = (ArrayList<EstadoReclamo>) query.getResultList();
			return listaEstadosReclamo;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los estados reclamo según el filtro seleccionado.");
		}
	}

}
