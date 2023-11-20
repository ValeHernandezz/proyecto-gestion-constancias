package com.services.usuario.genero;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Genero;
import com.exception.ServiciosException;

@Stateless
public class GeneroBean implements GeneroBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public GeneroBean() {

	}

	public Genero crearGenero(Genero oGenero) throws ServiciosException {
		try {
			entityManager.persist(oGenero);
			entityManager.flush();
			return oGenero;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el genero.");
		}
	}

	public Genero actualizarGenero(Genero oGenero) throws ServiciosException {
		try {
			entityManager.merge(oGenero);
			entityManager.flush();
			return oGenero;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el genero.");
		}
	}

	public boolean eliminarGenero(Long idGenero) throws ServiciosException {
		try {
			Genero oGenero = entityManager.find(Genero.class, idGenero);
			entityManager.remove(oGenero);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el genero.");
		}
	}

	public ArrayList<Genero> listarGeneros() throws ServiciosException {
		try {
			TypedQuery<Genero> query = entityManager.createQuery("SELECT g FROM Genero g", Genero.class);
			ArrayList<Genero> listaGeneros = (ArrayList<Genero>) query.getResultList();
			return listaGeneros;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los generos.");
		}
	}

	public ArrayList<Genero> listarGenerosFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Genero> query = entityManager
					.createQuery("SELECT g FROM Genero g WHERE g.nombre LIKE :filtro", Genero.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Genero> listaGeneroFiltro = (ArrayList<Genero>) query.getResultList();
			return listaGeneroFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los generos segun el filtro.");
		}
	}

}
