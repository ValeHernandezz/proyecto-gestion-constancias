package com.services.constancia.tipo;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.Tipo;
import com.exception.ServiciosException;

@Remote
public interface TipoBeanRemote {
	boolean crearTipo(Tipo oTipo) throws ServiciosException;

	boolean actualizarTipo(Tipo oTipo) throws ServiciosException;

	boolean eliminarTipo(Long idTipo) throws ServiciosException;

	ArrayList<Tipo> listarTipos() throws ServiciosException;

	ArrayList<Tipo> listarTipos(String filtro) throws ServiciosException;
}
