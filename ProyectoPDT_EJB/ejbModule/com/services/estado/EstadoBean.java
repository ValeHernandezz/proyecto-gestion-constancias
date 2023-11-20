package com.services.estado;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Estado;
import com.exception.ServiciosException;

@Stateless
public class EstadoBean implements EstadoBeanRemote {
	
	@PersistenceContext
	private EntityManager entityManager;

	public EstadoBean() {

	}

	public boolean crearEstado(Estado oEstado) throws ServiciosException {
		try {
			entityManager.persist(oEstado);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el estado.");
		}
	}

	public boolean actualizarEstado(Estado oEstado) throws ServiciosException {
		try {
			entityManager.merge(oEstado);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el estado.");
		}
	}

	public boolean eliminarEstado(Long idEstado) throws ServiciosException {
		try {
			Estado oEstado = entityManager.find(Estado.class, idEstado);
			entityManager.remove(oEstado);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el estado.");
		}
	}

	public ArrayList<Estado> listarEstados() throws ServiciosException {
		try {
			TypedQuery<Estado> query = entityManager.createQuery("SELECT e FROM Estado e", Estado.class);
			ArrayList<Estado> listaEstados = (ArrayList<Estado>) query.getResultList();
			return listaEstados;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estados.");
		}
	}

	public ArrayList<Estado> listarEstadosFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Estado> query = entityManager.createQuery("SELECT e FROM Estado e WHERE e.descripcion LIKE :filtro", Estado.class).setParameter("filtro", "%" + filtro + "%");
			ArrayList<Estado> listaEstadosFiltro = (ArrayList<Estado>) query.getResultList();
			return listaEstadosFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estados según el filtro proporcionado.");
		}
	}

}
