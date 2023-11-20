package com.services.ubicacion.departamento;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Departamento;
import com.exception.ServiciosException;

@Stateless
public class DepartamentoBean implements DepartamentoBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public DepartamentoBean() {

	}

	public Departamento crearDepartamento(Departamento oDepartamento) throws ServiciosException {
		try {
			entityManager.persist(oDepartamento);
			entityManager.flush();
			return oDepartamento;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el departamento.");
		}
	}

	public Departamento actualizarDepartamento(Departamento oDepartamento) throws ServiciosException {
		try {
			entityManager.merge(oDepartamento);
			entityManager.flush();
			return oDepartamento;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el departamento.");
		}
	}

	public boolean eliminarDepartamento(Long idDepartamento) throws ServiciosException {
		try {
			Departamento oDepartamento = entityManager.find(Departamento.class, idDepartamento);
			entityManager.remove(oDepartamento);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el departamento.");
		}
	}

	public ArrayList<Departamento> listarDepartamentos() throws ServiciosException {
		try {
			TypedQuery<Departamento> query = entityManager.createQuery("SELECT d FROM Departamento d", Departamento.class);
			ArrayList<Departamento> listaDepartamentos = (ArrayList<Departamento>) query.getResultList();
			return listaDepartamentos;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los departamentos.");
		}
	}

	public ArrayList<Departamento> listarDepartamentosFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Departamento> query = entityManager.createQuery("SELECT d FROM Departamento d WHERE d.nombre LIKE :filtro", Departamento.class).setParameter("filtro", "%" + filtro + "%");
			ArrayList<Departamento> listaDepartamentosFiltro = (ArrayList<Departamento>) query.getResultList();
			return listaDepartamentosFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los departamentos según el filtro proporcionado.");
		}
	}

}
