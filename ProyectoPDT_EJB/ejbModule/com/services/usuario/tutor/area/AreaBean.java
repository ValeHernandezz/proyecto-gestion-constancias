package com.services.usuario.tutor.area;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Area;
import com.exception.ServiciosException;

@Stateless
public class AreaBean implements AreaBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public AreaBean() {

	}

	public Area crearArea(Area oArea) throws ServiciosException {
		try {
			entityManager.persist(oArea);
			entityManager.flush();
			return oArea;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public boolean actualizarArea(Area oArea) throws ServiciosException {
		try {
			entityManager.merge(oArea);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el área.");
		}
	}

	public boolean eliminarArea(Long idArea) throws ServiciosException {
		try {
			Area oArea = entityManager.find(Area.class, idArea);
			entityManager.remove(oArea);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el área.");
		}
	}

	public ArrayList<Area> listarAreas() throws ServiciosException {
		try {
			TypedQuery<Area> query = entityManager.createQuery("SELECT a FROM Area a", Area.class);
			ArrayList<Area> listaAreas = (ArrayList<Area>) query.getResultList();
			return listaAreas;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las áreas.");
		}
	}

	public ArrayList<Area> listarAreasFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Area> query = entityManager
					.createQuery("SELECT a FROM Area a WHERE a.descripcion LIKE :filtro", Area.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Area> listaAreasFiltro = (ArrayList<Area>) query.getResultList();
			return listaAreasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las áreas según el filtro proporcionado.");
		}
	}

}
