package com.services.ubicacion.localidad;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.Localidad;
import com.exception.ServiciosException;

@Remote
public interface LocalidadBeanRemote {

	Localidad crearLocalidad(Localidad oLocalidad) throws ServiciosException;

	Localidad actualizarLocalidad(Localidad oLocalidad) throws ServiciosException;

	boolean eliminarLocalidad(Long idLocalidad) throws ServiciosException;

	ArrayList<Localidad> listarLocalidades() throws ServiciosException;

	ArrayList<Localidad> listarLocalidadesFiltro(String filtro) throws ServiciosException;

}
