package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the RECLAMOS database table.
 * 
 */
@Entity
@Table(name = "RECLAMOS")
@NamedQuery(name = "Reclamo.findAll", query = "SELECT r FROM Reclamo r")
public class Reclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_ID_RECLAMO" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_RECLAMO")
	@Column(name = "ID_RECLAMO")
	private long idReclamo;

	private String detalle;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	@Column(name = "ID_ESTADO")
	private long idEstado;

	@Column(name = "ID_ESTUDIANTE")
	private long idEstudiante;

	@Column(name = "ID_EVENTO")
	private long idEvento;

	private String titulo;

	public Reclamo() {
	}

	public Reclamo(String detalle, Date fechaHora, long idEstado, long idEstudiante, long idEvento, String titulo) {
		super();
		this.detalle = detalle;
		this.fechaHora = fechaHora;
		this.idEstado = idEstado;
		this.idEstudiante = idEstudiante;
		this.idEvento = idEvento;
		this.titulo = titulo;
	}

	public long getIdReclamo() {
		return this.idReclamo;
	}

	public void setIdReclamo(long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public long getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public long getIdEstudiante() {
		return this.idEstudiante;
	}

	public void setIdEstudiante(long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}