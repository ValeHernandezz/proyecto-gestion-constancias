package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ESTADOS_CONSTANCIAS database table.
 * 
 */
@Entity
@Table(name = "ESTADOS_CONSTANCIAS")
@NamedQuery(name = "EstadoConstancia.findAll", query = "SELECT e FROM EstadoConstancia e")
public class EstadoConstancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstadoConstanciaPK id;

	private String detalle;

	public EstadoConstancia() {
	}

	public EstadoConstancia(EstadoConstanciaPK id, String detalle) {
		super();
		this.id = id;
		this.detalle = detalle;
	}

	public EstadoConstanciaPK getId() {
		return this.id;
	}

	public void setId(EstadoConstanciaPK id) {
		this.id = id;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
}