package com.services.rol;

import java.util.ArrayList;

import javax.ejb.Remote;
import com.entities.Rol;
import com.exception.ServiciosException;

@Remote
public interface RolBeanRemote {
	
	Rol crearRol(Rol oRol) throws ServiciosException;

	Rol actualizarRol(Rol oRol) throws ServiciosException;

	boolean eliminarRol(Long idRol) throws ServiciosException;

	ArrayList<Rol> listarRoles() throws ServiciosException;

	ArrayList<Rol> listarRolesFiltro(String filtro) throws ServiciosException; 

}
