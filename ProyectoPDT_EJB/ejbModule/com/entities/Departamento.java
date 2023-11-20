package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DEPARTAMENTOS database table.
 * 
 */
@Entity
@Table(name="DEPARTAMENTOS")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_ID_DEPARTAMENTO" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_DEPARTAMENTO")
	@Column(name="ID_DEPARTAMENTO")
	private long idDepartamento;

    private String nombre;

    //bi-directional many-to-one association to Itr
    @OneToMany(mappedBy="departamento")
    private List<Itr> itrs;

    //bi-directional many-to-one association to Localidad
    @OneToMany(mappedBy="departamento")
    private List<Localidad> localidades;

    //bi-directional many-to-one association to Usuario
    @OneToMany(mappedBy="departamento")
    private List<Usuario> usuarios;

	public Departamento() {
	}

	public Departamento(String nombre) {
		super();
		this.nombre = nombre;
	}

	public long getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Itr> getItrs() {
		return this.itrs;
	}

	public void setItrs(List<Itr> itrs) {
		this.itrs = itrs;
	}

	public Itr addItr(Itr itr) {
		getItrs().add(itr);
		itr.setDepartamento(this);

		return itr;
	}

	public Itr removeItr(Itr itr) {
		getItrs().remove(itr);
		itr.setDepartamento(null);

		return itr;
	}

	public List<Localidad> getLocalidades() {
		return this.localidades;
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}

	public Localidad addLocalidade(Localidad localidade) {
		getLocalidades().add(localidade);
		localidade.setDepartamento(this);

		return localidade;
	}

	public Localidad removeLocalidade(Localidad localidade) {
		getLocalidades().remove(localidade);
		localidade.setDepartamento(null);

		return localidade;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setDepartamento(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setDepartamento(null);

		return usuario;
	}

}