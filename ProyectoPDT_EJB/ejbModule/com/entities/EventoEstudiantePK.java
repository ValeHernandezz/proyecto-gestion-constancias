package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EVENTOS_ESTUDIANTES database table.
 * 
 */
@Embeddable
public class EventoEstudiantePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_EVENTO")
	private long idEvento;

	@Column(name = "ID_ESTUDIANTE")
	private long idEstudiante;

	public EventoEstudiantePK() {
	}

	public EventoEstudiantePK(long idEvento, long idEstudiante) {
		super();
		this.idEvento = idEvento;
		this.idEstudiante = idEstudiante;
	}

	public long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public long getIdEstudiante() {
		return this.idEstudiante;
	}

	public void setIdEstudiante(long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EventoEstudiantePK)) {
			return false;
		}
		EventoEstudiantePK castOther = (EventoEstudiantePK) other;
		return (this.idEvento == castOther.idEvento) && (this.idEstudiante == castOther.idEstudiante);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idEvento ^ (this.idEvento >>> 32)));
		hash = hash * prime + ((int) (this.idEstudiante ^ (this.idEstudiante >>> 32)));

		return hash;
	}
}