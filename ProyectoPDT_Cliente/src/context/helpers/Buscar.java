package context.helpers;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.entities.Analista;
import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.EventoEstudiante;
import com.entities.Itr;
import com.entities.Localidad;
import com.entities.Tipo;
import com.entities.Tutor;
import com.entities.Usuario;

import context.Fabrica;
import services.ServiceAnalista;
import services.ServiceConstancia;
import services.ServiceEstado;
import services.ServiceEstadoConstancia;
import services.ServiceEvento;
import services.ServiceEventoEstudiante;
import services.ServiceItr;
import services.ServiceRol;
import services.ServiceTipo;
import services.ServiceUbicacion;
import services.ServiceUsuario;

public class Buscar {

	public static String estadoPorId(long id) {
		String nombre = "";
		for (var aux : ServiceEstado.listarEstados()) {
			if (aux.getIdEstado() == id) {
				nombre = aux.getDescripcion();
				break;
			}
		}
		return nombre;
	}

	// USUARIO-------------------------------------------
	private static void buscarEstudianteLogueado(ArrayList<Constancia> listaDeRespuesta, Constancia aux) {
		if (aux.getEstudiante().getUsuario().getDocumento().equals(Fabrica.getoUsuarioLogueado().getDocumento())) {
			listaDeRespuesta.add(aux);
		}
	}

	private static ArrayList<Usuario> buscarUsuarioPorNombreApellido(String filtro, String campo) {
		if (Fabrica.getDiccionarioCampo().size() == 0) {
			Fabrica.cargarDiccionario();
		}

		String filtro1 = null;
		String filtro2 = null;

		try {
			String valor = Fabrica.getDiccionarioCampo().get(campo);

			String[] partes = filtro.split(" ");
			filtro1 = partes[0]; // Nombre
			filtro2 = partes[1]; // Apellido
			return ServiceUsuario.listarUsuariosPorNombreApellido(filtro1, filtro2, valor);
		} catch (Exception e) {
			return null;
		}

	}

	private static ArrayList<Usuario> buscarUsuarioPorCampoYFiltro(String filtro, String campo) {
		if (Fabrica.getDiccionarioCampo().size() == 0) {
			Fabrica.cargarDiccionario();
		}
		try {
			if (campo.equals("Nombre Completo")) {
				return buscarUsuarioPorNombreApellido(filtro, campo);
			}

			String valor = Fabrica.getDiccionarioCampo().get(campo);

			if (campo.equals("Rol")) {
				filtro = String.valueOf(ServiceRol.listarRolesFiltro(filtro).get(0).getIdRol());
			}
			return ServiceUsuario.buscarUsuarioPorCampoYFiltro(filtro, valor);
		} catch (Exception e) {
			return null;
		}
	}

	private static ArrayList<Usuario> usuariosConfirmar(String filtro) {
		var lista = buscarUsuarioPorCampoYFiltro(filtro, "Confirmar");
		return lista;
	}

	private static ArrayList<Usuario> usuariosActivo(String filtro) {
		var lista = buscarUsuarioPorCampoYFiltro(filtro, "Activo");
		return lista;
	}
	// USUARIO-------------------------------------------

	// TUTOR-------------------------------------------
	public static ArrayList<Tutor> tutorPorRol(String filtro) {

		if (Fabrica.getListaDeTutores().size() == 0) {
			Fabrica.cargarListas();
		}

		try {
			ArrayList<Tutor> listaDeResultados = new ArrayList<Tutor>();

			for (Tutor aux : Fabrica.getListaDeTutores()) {
				if (aux.getUsuario().getRol().getDescripcion().equals(filtro)) {
					listaDeResultados.add(aux);
				}
			}

			return listaDeResultados;
		} catch (Exception e) {
			return new ArrayList<Tutor>();
		}
	}

	public static ArrayList<Tutor> tutoresSinConfirmar() {

		if (Fabrica.getListaDeTutores().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Tutor> listaDeRespuesta = new ArrayList<Tutor>();

		for (var aux : Fabrica.getListaDeTutores()) {
			for (var aux2 : usuariosConfirmar("N")) {
				if (aux2.getDocumento().equals(aux.getUsuario().getDocumento())) {
					listaDeRespuesta.add(aux);
				}
			}

		}
		return listaDeRespuesta;
	}

	public static ArrayList<Tutor> tutorFiltro(String filtro, String campo) {

		if (Fabrica.getListaDeTutores().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Tutor> listaDeRespuesta = new ArrayList<Tutor>();

		for (var aux : Fabrica.getListaDeTutores()) {
			for (var aux2 : buscarUsuarioPorCampoYFiltro(filtro, campo)) {
				if (aux2.getDocumento().equals(aux.getUsuario().getDocumento())) {
					listaDeRespuesta.add(aux);
				}
			}
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Tutor> listarTutoresActivos(String filtro) {
		if (Fabrica.getListaDeTutores().size() == 0) {
			Fabrica.cargarListas();
		}
		try {

			ArrayList<Tutor> listaDeRespuesta = new ArrayList<Tutor>();

			for (var tutorConDatos : Fabrica.getListaDeTutores()) {

				for (var usuarioActivo : buscarUsuarioPorCampoYFiltro(filtro, "Activo")) {
					if (usuarioActivo.getDocumento().equals(tutorConDatos.getUsuario().getDocumento())) {
						listaDeRespuesta.add(tutorConDatos);
					}
				}

			}
			return listaDeRespuesta;
		} catch (Exception e) {
			return new ArrayList<Tutor>();
		}
	}

	public static ArrayList<Tutor> tutoresITR(String itr) {
		if (Fabrica.getListaDeTutores().size() == 0) {
			Fabrica.cargarListas();
		}
		ArrayList<Tutor> listaDeRespuesta = new ArrayList<Tutor>();

		for (var tutorConDatos : Fabrica.getListaDeTutores()) {

			if (tutorConDatos.getUsuario().getItr().getNombre().toUpperCase().contains(itr.toUpperCase())) {
				listaDeRespuesta.add(tutorConDatos);
			}

		}

		return listaDeRespuesta;
	}
	// TUTOR-------------------------------------------

	// ESTUDIANTE----------------------------------------
	public static ArrayList<Estudiante> estudiantesSinConfirmar() {

		if (Fabrica.getListaDeEstudiantes().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Estudiante> listaDeRespuesta = new ArrayList<Estudiante>();

		for (var aux : Fabrica.getListaDeEstudiantes()) {

			for (var aux2 : usuariosConfirmar("N")) {
				if (aux2.getDocumento().equals(aux.getUsuario().getDocumento())) {
					listaDeRespuesta.add(aux);
				}
			}

		}
		return listaDeRespuesta;
	}

	public static ArrayList<Estudiante> estudianteFiltro(String filtro, String campo) {

		if (Fabrica.getListaDeEstudiantes().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Estudiante> listaDeRespuesta = new ArrayList<Estudiante>();

		for (var aux : Fabrica.getListaDeEstudiantes()) {

			for (var aux2 : buscarUsuarioPorCampoYFiltro(filtro, campo)) {
				if (aux2.getDocumento().equals(aux.getUsuario().getDocumento())) {
					listaDeRespuesta.add(aux);
				}
			}
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Estudiante> estudiantesActivos(String filtro) {

		if (Fabrica.getListaDeEstudiantes().size() == 0) {
			Fabrica.cargarListas();
		}

		try {
			ArrayList<Estudiante> listaDeRespuesta = new ArrayList<Estudiante>();

			for (var tutorConDatos : Fabrica.getListaDeEstudiantes()) {

				for (var usuarioActivo : usuariosActivo(filtro)) {
					if (usuarioActivo.getDocumento().equals(tutorConDatos.getUsuario().getDocumento())) {
						listaDeRespuesta.add(tutorConDatos);
					}
				}

			}
			return listaDeRespuesta;
		} catch (Exception e) {
			return new ArrayList<Estudiante>();
		}
	}

	public static ArrayList<Estudiante> estudianteITR(String itr) {
		if (Fabrica.getListaDeEstudiantes().size() == 0) {
			Fabrica.cargarListas();
		}
		ArrayList<Estudiante> listaDeRespuesta = new ArrayList<Estudiante>();

		for (var estudianteConDatos : Fabrica.getListaDeEstudiantes()) {

			if (estudianteConDatos.getUsuario().getItr().getNombre().toUpperCase().contains(itr.toUpperCase())) {
				listaDeRespuesta.add(estudianteConDatos);
			}

		}

		return listaDeRespuesta;
	}

	public static ArrayList<Estudiante> estudianteGeneracion(String filtro) {
		if (Fabrica.getListaDeEstudiantes().size() == 0) {
			Fabrica.cargarListas();
		}
		ArrayList<Estudiante> listaDeRespuesta = new ArrayList<Estudiante>();

		for (var estudianteConDatos : Fabrica.getListaDeEstudiantes()) {

			if (estudianteConDatos.getGeneracion().toUpperCase().contains(filtro.toUpperCase())) {
				listaDeRespuesta.add(estudianteConDatos);
			}

		}

		return listaDeRespuesta;
	}

	public static ArrayList<Estudiante> estudianteSemestre(String filtro) {
		if (Fabrica.getListaDeEstudiantes().size() == 0) {
			Fabrica.cargarListas();
		}
		ArrayList<Estudiante> listaDeRespuesta = new ArrayList<Estudiante>();

		for (var estudianteConDatos : Fabrica.getListaDeEstudiantes()) {

			if (String.valueOf(estudianteConDatos.getSemestre()).contains(filtro)) {
				listaDeRespuesta.add(estudianteConDatos);
			}

		}

		return listaDeRespuesta;
	}
	// ESTUDIANTE----------------------------------------

	// CONSTANCIA----------------------------------------
	public static ArrayList<Constancia> misConstancias() {
		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Constancia> listaDeRespuesta = new ArrayList<Constancia>();

		for (var aux : Fabrica.getListaDeConstancias()) {
			buscarEstudianteLogueado(listaDeRespuesta, aux);
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Constancia> constanciaPorNombreUsuario(String nombreUsuario) {
		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Constancia> listaDeRespuesta = new ArrayList<Constancia>();

		for (var constancia : Fabrica.getListaDeConstancias()) {

			if (constancia.getEstudiante().getUsuario().getNombreUsuario().equals(nombreUsuario)) {
				listaDeRespuesta.add(constancia);
			}
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Constancia> constanciaPorEstado(String estado) {
		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Constancia> listaDeRespuesta = new ArrayList<Constancia>();

		for (var constancia : Fabrica.getListaDeConstancias()) {
			if (constancia.getEstado().getDescripcion().equals(estado)) {
				listaDeRespuesta.add(constancia);
			}
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Constancia> misConstanciaPorEstado(String estado) {
		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Constancia> listaDeRespuesta = new ArrayList<Constancia>();

		for (var constancia : misConstancias()) {
			if (constancia.getEstado().getDescripcion().equals(estado)) {
				listaDeRespuesta.add(constancia);
			}
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Constancia> constanciaNoFinalizadas() {

		ArrayList<Constancia> listaDeRespuesta = new ArrayList<Constancia>();

		for (var constancia : ServiceConstancia.listarConstancias()) {
			if (!constancia.getEstado().getDescripcion().equals("Finalizado")) {
				listaDeRespuesta.add(constancia);
			}
		}

		return listaDeRespuesta;
	}

	public static boolean existeUnaSolicitud(Evento oEvento, Tipo oTipo) {
		try {
			boolean respuesta = false;

			for (var aux : misConstancias()) {
				if (aux.getEvento().getIdEvento() == oEvento.getIdEvento()
						&& aux.getTipo().getIdTipo() == oTipo.getIdTipo()) {
					respuesta = true;
				}
			}

			return respuesta;
		} catch (Exception e) {
			return true;
		}
	}

	public static ArrayList<Constancia> misConstanciaPorTipo(String tipo) {
		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Constancia> listaDeRespuesta = new ArrayList<Constancia>();

		for (var constancia : misConstancias()) {
			if (constancia.getTipo().getNombre().toString().equals(tipo)) {
				listaDeRespuesta.add(constancia);
			}
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Constancia> constanciaPorTipo(String tipo) {
		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Constancia> listaDeRespuesta = new ArrayList<Constancia>();

		for (var constancia : Fabrica.getListaDeConstancias()) {
			if (constancia.getTipo().getNombre().equals(tipo)) {
				listaDeRespuesta.add(constancia);
			}
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Constancia> constanciaPorTipoYEstado(String tipo, String estado) {
		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Constancia> listaDeRespuesta = new ArrayList<Constancia>();

		for (var constancia : Fabrica.getListaDeConstancias()) {
			if (constancia.getTipo().getNombre().equals(tipo)
					&& constancia.getEstado().getDescripcion().equals(estado)) {
				listaDeRespuesta.add(constancia);
			}
		}

		return listaDeRespuesta;
	}

	public static Constancia idConstanciaEstudiante(String cedula, String evento, String tipoConstancia) {

		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		Constancia oConstanciaRespuesta = null;
		for (var constancia : misConstancias()) {
			if (constancia.getEstudiante().getUsuario().getDocumento().toString().equals(cedula)
					&& constancia.getEvento().getTitulo().equals(evento)
					&& constancia.getTipo().getNombre().equals(tipoConstancia)) {
				oConstanciaRespuesta = constancia;
				break;
			}
		}
		return oConstanciaRespuesta;

	}

	public static Constancia idConstanciaAnalista(String cedula, String evento, String tipoConstancia) {

		if (Fabrica.getListaDeConstancias().size() == 0) {
			Fabrica.cargarListas();
		}

		Constancia oConstanciaRespuesta = null;
		for (var constancia : ServiceConstancia.listarConstancias()) {
			if (constancia.getEstudiante().getUsuario().getDocumento().toString().equals(cedula)
					&& constancia.getEvento().getTitulo().equals(evento)
					&& constancia.getTipo().getNombre().equals(tipoConstancia)) {
				oConstanciaRespuesta = constancia;
				break;
			}
		}
		return oConstanciaRespuesta;

	}

	public static Analista analistaDeConstancia(Long idConstancia, Long idEstado) {
		try {
			var lista = ServiceEstadoConstancia.listarEstadosContancias();
			Analista oAnalista = null;
			for (var aux : lista) {
				if (aux.getId().getEstadoId() == idEstado && aux.getId().getIdConstancia() == idConstancia) {
					oAnalista = analistaPorId(aux.getId().getIdAnalista());
					break;
				}
			}
			return oAnalista;
		} catch (Exception e) {
			return null;
		}
	}

	public static String detalleEstadoDeConstancia(Long idConstancia, Long idEstado) {
		try {
			var lista = ServiceEstadoConstancia.listarEstadosContancias();
			var idAnalista = analistaDeConstancia(idConstancia, idEstado).getIdAnalista();

			String detalle = null;
			for (var aux : lista) {
				if (aux.getId().getEstadoId() == idEstado && aux.getId().getIdConstancia() == idConstancia
						&& aux.getId().getIdAnalista() == idAnalista) {
					detalle = aux.getDetalle();
					break;
				}
			}
			return detalle;

		} catch (Exception e) {
			return "-";
		}

	}
	// CONSTANCIA----------------------------------------

	// ANALISTA----------------------------------------
	public static ArrayList<Analista> analistasSinCsonfirmar() {

		if (Fabrica.getListaDeAnalistas().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Analista> listaDeRespuesta = new ArrayList<Analista>();

		var lista = usuariosConfirmar("N");

		for (var aux : Fabrica.getListaDeAnalistas()) {

			for (var aux2 : lista) {
				if (aux2.getDocumento().equals(aux.getUsuario().getDocumento())) {
					listaDeRespuesta.add(aux);
				}
			}

		}
		return listaDeRespuesta;
	}

	public static ArrayList<Analista> analistaFiltro(String filtro, String campo) {

		if (Fabrica.getListaDeAnalistas().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Analista> listaDeRespuesta = new ArrayList<Analista>();

		var lista = buscarUsuarioPorCampoYFiltro(filtro, campo);

		for (var aux : Fabrica.getListaDeAnalistas()) {

			for (var aux2 : lista) {
				if (aux2.getDocumento().equals(aux.getUsuario().getDocumento())) {
					listaDeRespuesta.add(aux);
				}
			}
		}

		return listaDeRespuesta;
	}

	public static ArrayList<Analista> analistasSinConfirmar() {

		if (Fabrica.getListaDeAnalistas().size() == 0) {
			Fabrica.cargarListas();
		}

		ArrayList<Analista> listaDeRespuesta = new ArrayList<Analista>();

		for (var aux : Fabrica.getListaDeAnalistas()) {
			for (var aux2 : usuariosConfirmar("N")) {
				if (aux2.getDocumento().equals(aux.getUsuario().getDocumento())) {
					listaDeRespuesta.add(aux);
				}
			}

		}
		return listaDeRespuesta;
	}

	public static ArrayList<Analista> analistasActivos(String filtro) {
		if (Fabrica.getListaDeAnalistas().size() == 0) {
			Fabrica.cargarListas();
		}
		try {

			ArrayList<Analista> listaDeRespuesta = new ArrayList<Analista>();

			for (var analistaConDatos : Fabrica.getListaDeAnalistas()) {

				for (var usuarioActivo : usuariosActivo(filtro)) {
					if (usuarioActivo.getDocumento().equals(analistaConDatos.getUsuario().getDocumento())) {
						listaDeRespuesta.add(analistaConDatos);
					}
				}

			}
			return listaDeRespuesta;
		} catch (Exception e) {
			return new ArrayList<Analista>();
		}
	}

	public static ArrayList<Analista> analistaITR(String itr) {
		if (Fabrica.getListaDeAnalistas().size() == 0) {
			Fabrica.cargarListas();
		}
		ArrayList<Analista> listaDeRespuesta = new ArrayList<Analista>();

		for (var analistaConDatos : Fabrica.getListaDeAnalistas()) {

			if (analistaConDatos.getUsuario().getItr().getNombre().toUpperCase().contains(itr.toUpperCase())) {
				listaDeRespuesta.add(analistaConDatos);
			}

		}

		return listaDeRespuesta;
	}

	public static Analista analistaPorId(Long id) {

		ArrayList<Analista> listaDeRespuesta = new ArrayList<Analista>();

		for (var analistaConDatos : ServiceAnalista.listarAnalistas()) {

			if (analistaConDatos.getIdAnalista() == id) {
				listaDeRespuesta.add(analistaConDatos);
				break;
			}

		}

		return listaDeRespuesta.get(0);
	}
	// ANALISTA----------------------------------------

	// RECLAMO----------------------------------------
	public static ArrayList<EventoEstudiante> misReportes() {

		ArrayList<EventoEstudiante> listaDeRespuesta = new ArrayList<EventoEstudiante>();

		for (var aux : ServiceEventoEstudiante.listarEventoEstudiante()) {
			if (aux.getId().getIdEstudiante() == estudianteFiltro(
					Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento").get(0).getIdEstudiante()) {
				listaDeRespuesta.add(aux);
			}
		}

		return listaDeRespuesta;

	}

	public static ArrayList<EventoEstudiante> reportesEstudiante(Estudiante oEstudiante) {

		ArrayList<EventoEstudiante> listaDeRespuesta = new ArrayList<EventoEstudiante>();

		for (var aux : ServiceEventoEstudiante.listarEventoEstudiante()) {
			if (aux.getId().getIdEstudiante() == estudianteFiltro(oEstudiante.getUsuario().getDocumento().toString(),
					"Documento").get(0).getIdEstudiante()) {
				listaDeRespuesta.add(aux);
			}
		}

		return listaDeRespuesta;

	}

	public static int reportesEstudiantePorAsistencia(Estudiante oEstudiante, String asistio) {

		int respuesta = 0;

		for (var aux : reportesEstudiante(oEstudiante)) {
			if (aux.getAsistencia().equals(asistio)) {
				respuesta++;
			}
		}

		return respuesta;

	}

	public static String reportesEstudiantePorAsistenciaPorcentaje(Estudiante oEstudiante, String asistio) {

		int totalAsistencias = 0;
		int asistenciasContadas = 0;

		for (var aux : reportesEstudiante(oEstudiante)) {
			if (aux.getAsistencia().equals(asistio)) {
				asistenciasContadas++;
			}
			totalAsistencias++;
		}

		double porcentaje = (double) asistenciasContadas / totalAsistencias * 100;

		DecimalFormat formato = new DecimalFormat("#0.00"); // Define el formato con dos decimales
		String porcentajeFormateado = formato.format(porcentaje); // Formatea el número

		return porcentajeFormateado + "%";
	}

	// EVENTOS ESTUDIANTE----------------------------------------

	public static Evento eventoPorId(long idEvento) {
		Evento respuesta = null;

		for (var evento : ServiceEvento.listarEventos()) {
			if (evento.getIdEvento() == idEvento) {
				respuesta = evento;
				break;
			}
		}

		return respuesta;
	}

	public static Evento eventoPorTitulo(String titulo) {
		Evento respuesta = null;

		for (var evento : ServiceEvento.listarEventos()) {
			if (evento.getTitulo().equals(titulo)) {
				respuesta = evento;
				break;
			}
		}

		return respuesta;
	}

	// ITR ----------------------------------------

	public static Itr itrPorId(Long idItr) {

		Itr respuesta = null;

		for (var itr : ServiceItr.listarItrs()) {
			if (itr.getIdItr() == idItr) {
				respuesta = itr;
				break;
			}
		}

		return respuesta;

	}

	public static Itr itrPorNombre(String nombre) {

		Itr respuesta = null;

		for (var itr : ServiceItr.listarItrs()) {
			if (itr.getNombre().equals(nombre)) {
				respuesta = itr;
				break;
			}
		}

		return respuesta;

	}

	public static ArrayList<Itr> itrsActivos(String actividad) {

		ArrayList<Itr> respuesta = new ArrayList<Itr>();

		for (var itr : ServiceItr.listarItrs()) {
			if (itr.getActivo().equals(actividad)) {
				respuesta.add(itr);
			}
		}

		return respuesta;

	}

	// TIPO CONSTANCIA ----------------------------------------

	public static Tipo tipoConstanciaPorId(Long idTipo) {

		Tipo respuesta = null;

		for (var tipo : ServiceTipo.listarTiposConstancias()) {
			if (tipo.getIdTipo() == idTipo) {
				respuesta = tipo;
				break;
			}
		}

		return respuesta;

	}

	public static Tipo tipoConstanciaPorNombre(String nombre) {

		Tipo respuesta = null;

		for (var tipo : ServiceTipo.listarTiposConstancias()) {
			if (tipo.getNombre().equals(nombre)) {
				respuesta = tipo;
				break;
			}
		}

		return respuesta;

	}

	public static ArrayList<Tipo> tiposConstanciasActivos(String actividad) {

		ArrayList<Tipo> respuesta = new ArrayList<Tipo>();

		for (var tipo : ServiceTipo.listarTiposConstancias()) {
			if (tipo.getActivo().equals(actividad)) {
				respuesta.add(tipo);
			}
		}

		return respuesta;

	}

	// LOCALIDAD -------------------
	public static ArrayList<Localidad> localidadPorDepartamento(String filtro) {
		ArrayList<Localidad> listaDeRespuesta = new ArrayList<Localidad>();

		for (var aux : ServiceUbicacion.listarLocalidades()) {
			if (aux.getDepartamento().getNombre().equals(filtro)) {
				listaDeRespuesta.add(aux);
			}
		}

		return listaDeRespuesta;
	}
	// LOCALIDAD -------------------
}
