package com.services.constancia.estado;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.EstadoConstancia;
import com.exception.ServiciosException;

@Stateless
public class EstadoConstanciaBean implements EstadoConstanciaBeanRemote {
	
	@PersistenceContext
	private EntityManager entityManager;

	public EstadoConstanciaBean() {

	}

	public boolean crearEstadoConstancia(EstadoConstancia oEstadoConstancia) throws ServiciosException {
		try {
			entityManager.persist(oEstadoConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el estado constancia.");
		}
	}

	public boolean actualizarEstadoConstancia(EstadoConstancia oEstadoConstancia) throws ServiciosException {
		try {
			entityManager.merge(oEstadoConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el estado constancia.");
		}
	}

	public boolean eliminarEstadoConstancia(Long idEstadoConstancia) throws ServiciosException {
		try {
			EstadoConstancia oEstadoConstancia = entityManager.find(EstadoConstancia.class, idEstadoConstancia);
			entityManager.remove(oEstadoConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el estado constancia.");
		}
	}

	public ArrayList<EstadoConstancia> listarEstadosConstancias() throws ServiciosException {
		try {
			TypedQuery<EstadoConstancia> query = entityManager.createQuery("SELECT ec FROM EstadoConstancia ec", EstadoConstancia.class);
			ArrayList<EstadoConstancia> listaEstadosConstancias = (ArrayList<EstadoConstancia>) query.getResultList();
			return listaEstadosConstancias;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estados constancias.");
		}
	}

	public ArrayList<EstadoConstancia> listarEstadosConstanciasFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<EstadoConstancia> query = entityManager.createQuery("SELECT ec FROM EstadoConstancia ec WHERE ec.detalle LIKE :filtro", EstadoConstancia.class).setParameter("filtro", "%" + filtro + "%");
			ArrayList<EstadoConstancia> listaEstadosConstanciasFiltro = (ArrayList<EstadoConstancia>) query.getResultList();
			return listaEstadosConstanciasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estados constancias según el filtro proporcionado.");
		}
	}

}
