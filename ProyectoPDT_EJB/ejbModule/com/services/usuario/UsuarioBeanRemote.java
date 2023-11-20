package com.services.usuario;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.Usuario;
import com.exception.ServiciosException;
import com.utils.Respuesta;

@Remote
public interface UsuarioBeanRemote {

	Respuesta login(String nickname, String clave) throws ServiciosException;

	Usuario crearUsuario(Usuario oUsuario) throws ServiciosException;

	Usuario actualizarUsuario(Usuario oUsuario) throws ServiciosException;

	boolean eliminarUsuario(Long idUsuario) throws ServiciosException;

	ArrayList<Usuario> listarUsuarios() throws ServiciosException;

	ArrayList<Usuario> listarUsuariosFiltro(String filtro) throws ServiciosException;

	ArrayList<Usuario> listarUsuariosFiltroRol(String filtro) throws ServiciosException;

	ArrayList<Usuario> listarUsuariosFiltroPersonalizado(String filtro, String campo) throws ServiciosException;

	ArrayList<Usuario> listarUsuariosPorNombreApellido(String filtro1, String filtro2, String campo)
			throws ServiciosException;

	boolean tienePermiso(Long idRol, Long idFuncionalidad) throws ServiciosException;

}
