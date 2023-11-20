package com.services.usuario.tutor;

import java.util.ArrayList;

import javax.ejb.Remote;
import com.exception.ServiciosException;

import com.entities.Tutor;

@Remote
public interface TutorBeanRemote {

	boolean crearTutor(Tutor tutor) throws ServiciosException;

	boolean actualizarTutor(Tutor tutor) throws ServiciosException;

	boolean borrarTutor(Long idTutor) throws ServiciosException;

	ArrayList<Tutor> listarTutores() throws ServiciosException;

	ArrayList<Tutor> listarTutoresFiltro(String filtro) throws ServiciosException;
}
