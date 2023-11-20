package com.services.ubicacion.localidad;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Localidad;
import com.exception.ServiciosException;

@Stateless
public class LocalidadBean implements LocalidadBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public LocalidadBean() {

	}

	public Localidad crearLocalidad(Localidad oLocalidad) throws ServiciosException {
		try {
			entityManager.persist(oLocalidad);
			entityManager.flush();
			return oLocalidad;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear la localidad.");
		}
	}

	public Localidad actualizarLocalidad(Localidad oLocalidad) throws ServiciosException {
		try {
			entityManager.merge(oLocalidad);
			entityManager.flush();
			return oLocalidad;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar la localidad.");
		}
	}

	public boolean eliminarLocalidad(Long idLocalidad) throws ServiciosException {
		try {
			Localidad oLocalidad = entityManager.find(Localidad.class, idLocalidad);
			entityManager.remove(oLocalidad);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar la localidad.");
		}
	}

	public ArrayList<Localidad> listarLocalidades() throws ServiciosException {
		try {
			TypedQuery<Localidad> query = entityManager.createQuery("SELECT l FROM Localidad l", Localidad.class);
			ArrayList<Localidad> listaLocalidades = (ArrayList<Localidad>) query.getResultList();
			return listaLocalidades;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las localidades.");
		}
	}

	public ArrayList<Localidad> listarLocalidadesFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Localidad> query = entityManager
					.createQuery("SELECT l FROM Localidad l WHERE l.nombre LIKE :filtro", Localidad.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Localidad> listaLocalidadFiltro = (ArrayList<Localidad>) query.getResultList();
			return listaLocalidadFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las localidades.");
		}
	}

}
