package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the EVENTOS_ESTUDIANTES database table.
 * 
 */
@Entity
@Table(name = "EVENTOS_ESTUDIANTES")
@NamedQuery(name = "EventoEstudiante.findAll", query = "SELECT e FROM EventoEstudiante e")
public class EventoEstudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventoEstudiantePK id;

	private String asistencia;

	private BigDecimal calificacion;

	public EventoEstudiante() {
	}

	public EventoEstudiante(EventoEstudiantePK id, String asistencia, BigDecimal calificacion) {
		super();
		this.id = id;
		this.asistencia = asistencia;
		this.calificacion = calificacion;
	}

	public EventoEstudiantePK getId() {
		return this.id;
	}

	public void setId(EventoEstudiantePK id) {
		this.id = id;
	}

	public String getAsistencia() {
		return this.asistencia;
	}

	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}

	public BigDecimal getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
	}

}