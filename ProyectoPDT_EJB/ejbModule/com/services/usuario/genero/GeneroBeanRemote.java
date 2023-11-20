package com.services.usuario.genero;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.Genero;
import com.exception.ServiciosException;

@Remote
public interface GeneroBeanRemote {

	Genero crearGenero(Genero oGenero) throws ServiciosException;

	Genero actualizarGenero(Genero oGenero) throws ServiciosException;

	boolean eliminarGenero(Long idGenero) throws ServiciosException;

	ArrayList<Genero> listarGeneros() throws ServiciosException;

	ArrayList<Genero> listarGenerosFiltro(String filtro) throws ServiciosException;

}
