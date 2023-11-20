package com.services.reclamo_estado;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.EstadoReclamo;
import com.exception.ServiciosException;

@Remote
public interface EstadoReclamoBeanRemote {

	boolean crearEstadoReclamo(EstadoReclamo oEstadoReclamo) throws ServiciosException;

	boolean actualizarEstadoReclamo(EstadoReclamo oEstadoReclamo) throws ServiciosException;

	boolean eliminarEstadoReclamo(Long idEstadoReclamo) throws ServiciosException;

	ArrayList<EstadoReclamo> listarEstadosReclamo() throws ServiciosException;

	ArrayList<EstadoReclamo> listarEstadosReclamo(String filtro) throws ServiciosException;

}
