package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the ESTUDIANTES database table.
 * 
 */
@Entity
@Table(name = "ESTUDIANTES")
@NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ID_ESTUDIANTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ESTUDIANTE")
	@Column(name = "ID_ESTUDIANTE")
	private long idEstudiante;

	private String generacion;

	private BigDecimal semestre;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	public Estudiante() {
	}

	public Estudiante(String generacion, BigDecimal semestre) {
		super();
		this.generacion = generacion;
		this.semestre = semestre;
	}

	public Estudiante(String generacion, BigDecimal semestre, Usuario usuario) {
		super();
		this.generacion = generacion;
		this.semestre = semestre;
		this.usuario = usuario;
	}

	public long getIdEstudiante() {
		return this.idEstudiante;
	}

	public void setIdEstudiante(long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getGeneracion() {
		return this.generacion;
	}

	public void setGeneracion(String generacion) {
		this.generacion = generacion;
	}

	public BigDecimal getSemestre() {
		return this.semestre;
	}

	public void setSemestre(BigDecimal semestre) {
		this.semestre = semestre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Estudiante [idEstudiante=" + idEstudiante + ", generacion=" + generacion + ", semestre=" + semestre
				+ ", usuario=" + usuario + "]";
	}
	
	

}