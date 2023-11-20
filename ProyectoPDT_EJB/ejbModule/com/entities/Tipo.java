package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TIPOS database table.
 * 
 */
@Entity
@Table(name = "TIPOS")
@NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ID_TIPO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_TIPO")
	@Column(name = "ID_TIPO")
	private long idTipo;

	private String activo;

	@Lob
	@Column(name = "DESCRIPCION", columnDefinition = "CLOB")
	private String descripcion;
	
	@Column(unique = true)
	private String nombre;

	public Tipo() {
	}

	public Tipo(String nombre, String activo, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public long getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}