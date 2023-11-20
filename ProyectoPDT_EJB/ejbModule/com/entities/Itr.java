package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the ITRS database table.
 * 
 */
@Entity
@Table(name = "ITRS")
@NamedQuery(name = "Itr.findAll", query = "SELECT i FROM Itr i")
public class Itr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ID_ITR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ITR")
	@Column(name = "ID_ITR")
	private long idItr;

	@Column(unique = true)
	private String nombre;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO")
	private Departamento departamento;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "itr")
	private List<Usuario> usuarios;

	private String activo;

	public Itr() {
	}

	public Itr(String nombre, Departamento departamento, String activo) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
		this.activo = activo;
	}

	public long getIdItr() {
		return this.idItr;
	}

	public void setIdItr(long idItr) {
		this.idItr = idItr;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}