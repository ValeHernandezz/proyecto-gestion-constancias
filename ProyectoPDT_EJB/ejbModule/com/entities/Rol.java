package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Table(name = "\"ROLES\"")
@NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ID_ROL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ROL")
	@Column(name = "ID_ROL")
	private long idRol;

	private String descripcion;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "rol")
	private List<Usuario> usuarios;

	public Rol() {
	}

	public Rol(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRol(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRol(null);

		return usuario;
	}

}