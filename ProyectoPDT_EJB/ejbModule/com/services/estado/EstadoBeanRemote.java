package com.services.estado;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.Estado;
import com.exception.ServiciosException;

@Remote
public interface EstadoBeanRemote {

	boolean crearEstado(Estado oEstado) throws ServiciosException;

	boolean actualizarEstado(Estado oEstado) throws ServiciosException;

	boolean eliminarEstado(Long idEstado) throws ServiciosException;

	ArrayList<Estado> listarEstados() throws ServiciosException;

	ArrayList<Estado> listarEstadosFiltro(String filtro) throws ServiciosException;

}
