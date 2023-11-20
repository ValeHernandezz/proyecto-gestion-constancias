package com.services.constancia.estado;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.EstadoConstancia;
import com.exception.ServiciosException;

@Remote
public interface EstadoConstanciaBeanRemote {
	
	boolean crearEstadoConstancia(EstadoConstancia oEstadoConstancia) throws ServiciosException;

	boolean actualizarEstadoConstancia(EstadoConstancia oEstadoConstancia) throws ServiciosException;

	boolean eliminarEstadoConstancia(Long idEstadoConstancia) throws ServiciosException;

	ArrayList<EstadoConstancia> listarEstadosConstancias() throws ServiciosException;

	ArrayList<EstadoConstancia> listarEstadosConstanciasFiltro(String filtro) throws ServiciosException;

}
