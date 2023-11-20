package com.services.reclamo;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.Reclamo;
import com.exception.ServiciosException;

@Remote
public interface ReclamoBeanRemote {

	boolean crearReclamo(Reclamo oDepartamento) throws ServiciosException;

	boolean actualizarReclamo(Reclamo oReclamo) throws ServiciosException;

	boolean eliminarReclamo(Long idReclamo) throws ServiciosException;

	ArrayList<Reclamo> listarReclamos() throws ServiciosException;

	ArrayList<Reclamo> listarReclamoFiltro(String filtro) throws ServiciosException;

	

}
