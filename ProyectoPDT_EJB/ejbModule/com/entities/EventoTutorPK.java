package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EVENTOS_TUTORES database table.
 * 
 */
@Embeddable
public class EventoTutorPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_EVENTO_TUTOR")
	private long idEventoTutor;

	@Column(name = "ID_EVENTO")
	private long idEvento;

	@Column(name = "ID_TUTOR")
	private long idTutor;

	public EventoTutorPK() {
	}

	public EventoTutorPK(long idEventoTutor, long idEvento, long idTutor) {
		super();
		this.idEventoTutor = idEventoTutor;
		this.idEvento = idEvento;
		this.idTutor = idTutor;
	}

	public long getIdEventoTutor() {
		return this.idEventoTutor;
	}

	public void setIdEventoTutor(long idEventoTutor) {
		this.idEventoTutor = idEventoTutor;
	}

	public long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public long getIdTutor() {
		return this.idTutor;
	}

	public void setIdTutor(long idTutor) {
		this.idTutor = idTutor;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EventoTutorPK)) {
			return false;
		}
		EventoTutorPK castOther = (EventoTutorPK) other;
		return (this.idEventoTutor == castOther.idEventoTutor) && (this.idEvento == castOther.idEvento)
				&& (this.idTutor == castOther.idTutor);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idEventoTutor ^ (this.idEventoTutor >>> 32)));
		hash = hash * prime + ((int) (this.idEvento ^ (this.idEvento >>> 32)));
		hash = hash * prime + ((int) (this.idTutor ^ (this.idTutor >>> 32)));

		return hash;
	}
}