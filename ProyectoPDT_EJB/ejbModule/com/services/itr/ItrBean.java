package com.services.itr;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Itr;
import com.exception.ServiciosException;

@Stateless
public class ItrBean implements ItrBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

	public ItrBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Itr crearITR(Itr oITR) throws ServiciosException {
		try {
			entityManager.persist(oITR);
			entityManager.flush();
			return oITR;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Itr actualizarITR(Itr oITR) throws ServiciosException {
		try {
			entityManager.merge(oITR);
			entityManager.flush();
			return oITR;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean eliminarITR(Long idITR) throws ServiciosException {
		try {
			Itr oITR = entityManager.find(Itr.class, idITR);
			entityManager.remove(oITR);
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ArrayList<Itr> listarITR() throws ServiciosException {
		try {
			TypedQuery<Itr> query = entityManager.createQuery("SELECT i FROM Itr i", Itr.class);
			ArrayList<Itr> lista = (ArrayList<Itr>) query.getResultList();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Itr> listarITRFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Itr> query = entityManager
					.createQuery("SELECT i FROM Itr i WHERE i.nombre LIKE :filtro", Itr.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Itr> lista = (ArrayList<Itr>) query.getResultList();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}

}
