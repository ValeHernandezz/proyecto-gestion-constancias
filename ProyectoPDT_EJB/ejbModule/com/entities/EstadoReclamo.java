package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the ESTADOS_RECLAMOS database table.
 * 
 */
@Entity
@Table(name = "ESTADOS_RECLAMOS")
@NamedQuery(name = "EstadoReclamo.findAll", query = "SELECT e FROM EstadoReclamo e")
public class EstadoReclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstadoReclamoPK id;

	private String detalle;

	public EstadoReclamo() {
	}

	public EstadoReclamo(EstadoReclamoPK id, String detalle) {
		super();
		this.id = id;
		this.detalle = detalle;
	}

	public EstadoReclamoPK getId() {
		return this.id;
	}

	public void setId(EstadoReclamoPK id) {
		this.id = id;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}