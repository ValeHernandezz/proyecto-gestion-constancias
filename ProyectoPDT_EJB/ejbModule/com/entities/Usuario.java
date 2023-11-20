package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name = "USUARIOS")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_USUARIO")
	@Column(name = "ID_USUARIO")
	private long idUsuario;

	private String clave;

	@Column(name = "DOCUMENTO")
	private BigDecimal documento;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	@Column(name = "MAIL_INSTITUCIONAL")
	private String mailInstitucional;

	@Column(name = "MAIL_PERSONAL")
	private String mailPersonal;

	@Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario;

	@Column(name = "PRIMER_APELLIDO")
	private String primerApellido;

	@Column(name = "PRIMER_NOMBRE")
	private String primerNombre;

	@Column(name = "SEGUNDO_APELLIDO")
	private String segundoApellido;

	@Column(name = "SEGUNDO_NOMBRE")
	private String segundoNombre;

	private String telefono;

	// bi-directional many-to-one association to Analista
	@OneToMany(mappedBy = "usuario")
	private List<Analista> analistas;

	// bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy = "usuario")
	private List<Estudiante> estudiantes;

	// bi-directional many-to-one association to Tutor
	@OneToMany(mappedBy = "usuario")
	private List<Tutor> tutores;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO")
	private Departamento departamento;

	// bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name = "ID_GENERO")
	private Genero genero;

	// bi-directional many-to-one association to Itr
	@ManyToOne
	@JoinColumn(name = "ID_ITR")
	private Itr itr;

	// bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name = "ID_LOCALIDAD")
	private Localidad localidad;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	private Rol rol;

	@Column(name = "ACTIVO")
	private String activo;

	@Column(name = "CONFIRMADO")
	private String confirmado;

	public Usuario() {
	}

	public Usuario(String clave, BigDecimal documento, Date fechaNacimiento, String mailInstitucional,
			String mailPersonal, String nombreUsuario, String primerApellido, String primerNombre,
			String segundoApellido, String segundoNombre, String telefono, String activo, String confirmado,
			Departamento departamento, Genero genero, Itr itr, Localidad localidad, Rol rol) {
		this.clave = clave;
		this.documento = documento;
		this.fechaNacimiento = fechaNacimiento;
		this.mailInstitucional = mailInstitucional;
		this.mailPersonal = mailPersonal;
		this.nombreUsuario = nombreUsuario;
		this.primerApellido = primerApellido;
		this.primerNombre = primerNombre;
		this.segundoApellido = segundoApellido;
		this.segundoNombre = segundoNombre;
		this.telefono = telefono;
		this.activo = activo;
		this.confirmado = confirmado;
		this.departamento = departamento;
		this.genero = genero;
		this.itr = itr;
		this.localidad = localidad;
		this.rol = rol;
	}

	public Usuario(String clave, BigDecimal documento, Date fechaNacimiento, String mailInstitucional,
			String mailPersonal, String nombreUsuario, String primerApellido, String primerNombre,
			String segundoApellido, String segundoNombre, String telefono, Departamento departamento, Genero genero,
			Itr itr, Localidad localidad, Rol rol) {
		super();
		this.clave = clave;
		this.documento = documento;
		this.fechaNacimiento = fechaNacimiento;
		this.mailInstitucional = mailInstitucional;
		this.mailPersonal = mailPersonal;
		this.nombreUsuario = nombreUsuario;
		this.primerApellido = primerApellido;
		this.primerNombre = primerNombre;
		this.segundoApellido = segundoApellido;
		this.segundoNombre = segundoNombre;
		this.telefono = telefono;
		this.departamento = departamento;
		this.genero = genero;
		this.itr = itr;
		this.localidad = localidad;
		this.rol = rol;
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public BigDecimal getDocumento() {
		return this.documento;
	}

	public void setDocumento(BigDecimal documento) {
		this.documento = documento;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public String getEdad() {
        Calendar calNacimiento = new GregorianCalendar();
        calNacimiento.setTime(this.fechaNacimiento);
        int yearNacimiento = calNacimiento.get(Calendar.YEAR);
        int monthNacimiento = calNacimiento.get(Calendar.MONTH);
        int dayNacimiento = calNacimiento.get(Calendar.DAY_OF_MONTH);

        LocalDate fechaNacimientoLocal = LocalDate.of(yearNacimiento, monthNacimiento + 1, dayNacimiento);
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimientoLocal, fechaActual);
        int edad = periodo.getYears();
        return edad + " años";
    }

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getMailInstitucional() {
		return this.mailInstitucional;
	}

	public void setMailInstitucional(String mailInstitucional) {
		this.mailInstitucional = mailInstitucional;
	}

	public String getMailPersonal() {
		return this.mailPersonal;
	}

	public void setMailPersonal(String mailPersonal) {
		this.mailPersonal = mailPersonal;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Analista> getAnalistas() {
		return this.analistas;
	}

	public void setAnalistas(List<Analista> analistas) {
		this.analistas = analistas;
	}

	public Analista addAnalista(Analista analista) {
		getAnalistas().add(analista);
		analista.setUsuario(this);

		return analista;
	}

	public Analista removeAnalista(Analista analista) {
		getAnalistas().remove(analista);
		analista.setUsuario(null);

		return analista;
	}

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Estudiante addEstudiante(Estudiante estudiante) {
		getEstudiantes().add(estudiante);
		estudiante.setUsuario(this);

		return estudiante;
	}

	public Estudiante removeEstudiante(Estudiante estudiante) {
		getEstudiantes().remove(estudiante);
		estudiante.setUsuario(null);

		return estudiante;
	}

	public List<Tutor> getTutores() {
		return this.tutores;
	}

	public void setTutores(List<Tutor> tutores) {
		this.tutores = tutores;
	}

	public Tutor addTutore(Tutor tutore) {
		getTutores().add(tutore);
		tutore.setUsuario(this);

		return tutore;
	}

	public Tutor removeTutore(Tutor tutore) {
		getTutores().remove(tutore);
		tutore.setUsuario(null);

		return tutore;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Itr getItr() {
		return this.itr;
	}

	public void setItr(Itr itr) {
		this.itr = itr;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(String confirmado) {
		this.confirmado = confirmado;
	}

	public String getNombreCompleto() {
		return this.primerNombre + " " + this.primerApellido;
	}

	public String getNombres() {
		return this.primerNombre + " " + (this.segundoNombre == null ? "" : this.segundoNombre);
	}

	public String getApellidos() {
		return this.primerApellido + " " + (this.segundoApellido == null ? "" : this.segundoApellido);
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", clave=" + clave + ", documento=" + documento
				+ ", fechaNacimiento=" + fechaNacimiento + ", mailInstitucional=" + mailInstitucional
				+ ", mailPersonal=" + mailPersonal + ", nombreUsuario=" + nombreUsuario + ", primerApellido="
				+ primerApellido + ", primerNombre=" + primerNombre + ", segundoApellido=" + segundoApellido
				+ ", segundoNombre=" + segundoNombre + ", telefono=" + telefono + ", analistas=" + analistas
				+ ", estudiantes=" + estudiantes + ", tutores=" + tutores + ", departamento=" + departamento
				+ ", genero=" + genero + ", itr=" + itr + ", localidad=" + localidad + ", rol=" + rol + ", activo="
				+ activo + ", confirmado=" + confirmado + "]";
	}

}