package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADOS database table.
 * 
 */
@Entity
@Table(name="ESTADOS")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_ID_ESTADO" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_ESTADO")
	@Column(name="ID_ESTADO")
	private long idEstado;

	private String descripcion;

	//bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy="estado")
	private List<Constancia> constancias;
//
//	//bi-directional many-to-one association to Justificacione
//	@OneToMany(mappedBy="estado")
//	private List<Justificacion> justificaciones;
//
//	//bi-directional many-to-one association to Reclamo
//	@OneToMany(mappedBy="estado")
//	private List<Reclamo> reclamos;

	public Estado() {
	}
	
	
	
	public Estado(String descripcion) {
		super();
		this.descripcion = descripcion;
	}



	public long getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//	public List<Constancia> getConstancias() {
//		return this.constancias;
//	}
//
//	public void setConstancias(List<Constancia> constancias) {
//		this.constancias = constancias;
//	}
//
//	public Constancia addConstancia(Constancia constancia) {
//		getConstancias().add(constancia);
//		constancia.setEstado(this);
//
//		return constancia;
//	}
//
//	public Constancia removeConstancia(Constancia constancia) {
//		getConstancias().remove(constancia);
//		constancia.setEstado(null);
//
//		return constancia;
//	}
//
//	public List<Justificacion> getJustificaciones() {
//		return this.justificaciones;
//	}
//
//	public void setJustificaciones(List<Justificacion> justificaciones) {
//		this.justificaciones = justificaciones;
//	}
//
//	public Justificacion addJustificacione(Justificacion justificacione) {
//		getJustificaciones().add(justificacione);
//		justificacione.setEstado(this);
//
//		return justificacione;
//	}
//
//	public Justificacion removeJustificacione(Justificacion justificacione) {
//		getJustificaciones().remove(justificacione);
//		justificacione.setEstado(null);
//
//		return justificacione;
//	}
//
//	public List<Reclamo> getReclamos() {
//		return this.reclamos;
//	}
//
//	public void setReclamos(List<Reclamo> reclamos) {
//		this.reclamos = reclamos;
//	}
//
//	public Reclamo addReclamo(Reclamo reclamo) {
//		getReclamos().add(reclamo);
//		reclamo.setEstado(this);
//
//		return reclamo;
//	}
//
//	public Reclamo removeReclamo(Reclamo reclamo) {
//		getReclamos().remove(reclamo);
//		reclamo.setEstado(null);
//
//		return reclamo;
//	}

}