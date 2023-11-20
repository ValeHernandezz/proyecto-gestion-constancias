package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EVENTOS_TUTORES database table.
 * 
 */
@Entity
@Table(name="EVENTOS_TUTORES")
@NamedQuery(name="EventoTutor.findAll", query="SELECT e FROM EventoTutor e")
public class EventoTutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventoTutorPK id;

	public EventoTutor() {
	}

	public EventoTutor(EventoTutorPK id) {
		super();
		this.id = id;
	}

	public EventoTutorPK getId() {
		return this.id;
	}

	public void setId(EventoTutorPK id) {
		this.id = id;
	}

}