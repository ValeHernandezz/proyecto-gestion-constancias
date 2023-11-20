package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the ESTADOS_RECLAMOS database table.
 * 
 */
@Embeddable
public class EstadoReclamoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_RECLAMO", insertable = false, updatable = false)
	private long idReclamo;

	@Column(name = "ID_ANALISTA")
	private long idAnalista;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_HORA")
	private java.util.Date fechaHora;

	public EstadoReclamoPK() {
	}

	public EstadoReclamoPK(long idReclamo, long idAnalista, Date fechaHora) {
		super();
		this.idReclamo = idReclamo;
		this.idAnalista = idAnalista;
		this.fechaHora = fechaHora;
	}

	public long getIdReclamo() {
		return this.idReclamo;
	}

	public void setIdReclamo(long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public long getIdAnalista() {
		return this.idAnalista;
	}

	public void setIdAnalista(long idAnalista) {
		this.idAnalista = idAnalista;
	}

	public java.util.Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(java.util.Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EstadoReclamoPK)) {
			return false;
		}
		EstadoReclamoPK castOther = (EstadoReclamoPK) other;
		return (this.idReclamo == castOther.idReclamo) && (this.idAnalista == castOther.idAnalista)
				&& this.fechaHora.equals(castOther.fechaHora);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idReclamo ^ (this.idReclamo >>> 32)));
		hash = hash * prime + ((int) (this.idAnalista ^ (this.idAnalista >>> 32)));
		hash = hash * prime + this.fechaHora.hashCode();

		return hash;
	}
}