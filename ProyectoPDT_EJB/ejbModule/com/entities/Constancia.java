package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the CONSTANCIAS database table.
 * 
 */
@Entity
@Table(name = "CONSTANCIAS")
@NamedQuery(name = "Constancia.findAll", query = "SELECT c FROM Constancia c")
public class Constancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ID_CONSTANCIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_CONSTANCIA")
	@Column(name = "ID_CONSTANCIA")
	private long idConstancia;

	private String detalle;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	// bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO")
	private Estado estado;

	// bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name = "ID_ESTUDIANTE")
	private Estudiante estudiante;

	// bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name = "ID_EVENTO")
	private Evento evento;

	// bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name = "ID_TIPO")
	private Tipo tipo;

	public Constancia() {
	}

	public Constancia(String detalle, Date fechaHora, Estado estado, Estudiante estudiante, Evento evento, Tipo tipo) {
		super();
		this.detalle = detalle;
		this.fechaHora = fechaHora;
		this.estado = estado;
		this.estudiante = estudiante;
		this.evento = evento;
		this.tipo = tipo;
	}

	public long getIdConstancia() {
		return this.idConstancia;
	}

	public void setIdConstancia(long idConstancia) {
		this.idConstancia = idConstancia;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}