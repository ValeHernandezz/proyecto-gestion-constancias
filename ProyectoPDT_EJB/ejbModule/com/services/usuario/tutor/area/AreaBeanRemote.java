package com.services.usuario.tutor.area;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.Area;
import com.exception.ServiciosException;

@Remote
public interface AreaBeanRemote {

	Area crearArea(Area oArea) throws ServiciosException;

	boolean actualizarArea(Area oArea) throws ServiciosException;

	boolean eliminarArea(Long idArea) throws ServiciosException;

	ArrayList<Area> listarAreas() throws ServiciosException;

	ArrayList<Area> listarAreasFiltro(String filtro) throws ServiciosException;

}
