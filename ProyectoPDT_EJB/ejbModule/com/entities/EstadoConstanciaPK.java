package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the ESTADOS_CONSTANCIAS database table.
 * 
 */
@Embeddable
public class EstadoConstanciaPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_CONSTANCIA")
	private long idConstancia;

	@Column(name = "ID_ANALISTA")
	private long idAnalista;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_HORA")
	private java.util.Date fechaHora;

	@Column(name = "ID_ESTADO")
	private Long idEstado;

	public EstadoConstanciaPK() {
	}
	
	public EstadoConstanciaPK(long idConstancia, long idAnalista, Date fechaHora, Long idEstado) {
		super();
		this.idConstancia = idConstancia;
		this.idAnalista = idAnalista;
		this.fechaHora = fechaHora;
		this.idEstado = idEstado;
	}

	public long getIdConstancia() {
		return this.idConstancia;
	}

	public void setIdConstancia(long idConstancia) {
		this.idConstancia = idConstancia;
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

	public Long getEstadoId() {
		return this.idEstado;
	}

	public void setEstadoId(Long idEstado) {
		this.idEstado = idEstado;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EstadoConstanciaPK)) {
			return false;
		}
		EstadoConstanciaPK castOther = (EstadoConstanciaPK) other;
		return (this.idConstancia == castOther.idConstancia) && (this.idAnalista == castOther.idAnalista)
				&& this.fechaHora.equals(castOther.fechaHora) && (this.idEstado == castOther.idEstado);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idConstancia ^ (this.idConstancia >>> 32)));
		hash = hash * prime + ((int) (this.idAnalista ^ (this.idAnalista >>> 32)));
		hash = hash * prime + this.fechaHora.hashCode();
		hash = (int) (hash * prime + this.idEstado);
		return hash;
	}
}