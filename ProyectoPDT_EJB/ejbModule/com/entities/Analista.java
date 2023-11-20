package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ANALISTAS database table.
 * 
 */
@Entity
@Table(name="ANALISTAS")
@NamedQuery(name="Analista.findAll", query="SELECT a FROM Analista a")
public class Analista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_ID_ANALISTA" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_ANALISTA")
	@Column(name="ID_ANALISTA")
	private long idAnalista;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;
	
	@Lob
	@Column(name="FIRMA")
	private byte[] firma;
	
	public Analista() {
	}

	public Analista (Usuario usuario) {
		super();
		this.usuario = usuario;
	}
	
	
	
	public Analista(long idAnalista) {
		super();
		this.idAnalista = idAnalista;
	}

	public long getIdAnalista() {
		return this.idAnalista;
	}

	public void setIdAnalista(long idAnalista) {
		this.idAnalista = idAnalista;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public byte[] getFirma() {
		return firma;
	}

	public void setFirma(byte[] firma) {
		this.firma = firma;
	}
	
	

}