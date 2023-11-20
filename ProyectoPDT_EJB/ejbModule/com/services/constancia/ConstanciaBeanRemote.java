package com.services.constancia;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.Constancia;
import com.exception.ServiciosException;

@Remote
public interface ConstanciaBeanRemote {

	boolean crearConstancia (Constancia oConstancia) throws ServiciosException;
	
	boolean actualizarConstancia (Constancia oConstancia) throws ServiciosException;

	boolean eliminarConstancia (Long idConstancia) throws ServiciosException;

	ArrayList<Constancia> listarConstancias() throws ServiciosException;
	
	ArrayList<Constancia> listarConstanciasFiltro(String filtro) throws ServiciosException;

}
