package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TUTORES database table.
 * 
 */
@Entity
@Table(name = "TUTORES")
@NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t")
public class Tutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ID_TUTOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_TUTOR")
	@Column(name = "ID_TUTOR")
	private long idTutor;

	// bi-directional many-to-one association to Area
	@ManyToOne
	@JoinColumn(name = "ID_AREA")
	private Area area;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	public Tutor() {
	}

	public Tutor(Area area, Usuario usuario) {
		super();
		this.area = area;
		this.usuario = usuario;
	}

	public Tutor(Area area) {
		super();
		this.area = area;
	}

	public long getIdTutor() {
		return this.idTutor;
	}

	public void setIdTutor(long idTutor) {
		this.idTutor = idTutor;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}