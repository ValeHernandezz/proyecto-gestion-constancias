package com.services.constancia.tipo;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.EstadoConstancia;
import com.entities.Tipo;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class TipoBean
 */
@Stateless
public class TipoBean implements TipoBeanRemote {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public TipoBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean crearTipo(Tipo oTipo) throws ServiciosException {
		try {
			entityManager.persist(oTipo);
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean actualizarTipo(Tipo oTipo) throws ServiciosException {
		try {
			entityManager.merge(oTipo);
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean eliminarTipo(Long idTipo) throws ServiciosException {
		try {
			Tipo oTipo = entityManager.find(Tipo.class, idTipo);
			entityManager.remove(oTipo);
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ArrayList<Tipo> listarTipos() throws ServiciosException {
		try {
			TypedQuery<Tipo> query = entityManager.createQuery("SELECT t FROM Tipo t", Tipo.class);
			ArrayList<Tipo> listaTipos = (ArrayList<Tipo>) query.getResultList();
			return listaTipos;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estados constancias.");
		}
	}

	@Override
	public ArrayList<Tipo> listarTipos(String filtro) throws ServiciosException {
		try {
			TypedQuery<Tipo> query = entityManager
					.createQuery("SELECT t FROM Tipo t WHERE t.nombre LIKE :filtro", Tipo.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Tipo> listaEstadosConstanciasFiltro = (ArrayList<Tipo>) query.getResultList();
			return listaEstadosConstanciasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los estados constancias según el filtro proporcionado.");
		}
	}

}
