package context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.entities.Analista;
import com.entities.Constancia;
import com.entities.EstadoConstancia;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;

import services.ServiceAnalista;
import services.ServiceConstancia;
import services.ServiceEstadoConstancia;
import services.ServiceEstudiante;
import services.ServiceRol;
import services.ServiceTutor;
import services.ServiceUsuario;

public class Fabrica {

	// Atributos de apoyo para uso global de la app
	private static Usuario oUsuarioLogueado = new Usuario();
	private static Map<String, String> diccionarioCampo = new HashMap<>();

	private static ArrayList<Analista> listaDeAnalistas = new ArrayList<Analista>();
	private static ArrayList<Estudiante> listaDeEstudiantes = new ArrayList<Estudiante>();
	private static ArrayList<Tutor> listaDeTutores = new ArrayList<Tutor>();
	private static ArrayList<Constancia> listaDeConstancias = new ArrayList<Constancia>();
	private static ArrayList<EstadoConstancia> listaDeEstadosConstancias = new ArrayList<EstadoConstancia>();

	// Getters y setter de los atributos globales
	public static ArrayList<Analista> getListaDeAnalistas() {
		return listaDeAnalistas;
	}

	public static ArrayList<EstadoConstancia> getListaDeEstadosConstancias() {
		return listaDeEstadosConstancias;
	}

	public static void setListaDeEstadosConstancias(ArrayList<EstadoConstancia> listaDeEstadosConstancias) {
		Fabrica.listaDeEstadosConstancias = listaDeEstadosConstancias;
	}

	public static Usuario getoUsuarioLogueado() {
		return oUsuarioLogueado;
	}

	public static void setoUsuarioLogueado(Usuario oUsuarioLogueado) {
		Fabrica.oUsuarioLogueado = oUsuarioLogueado;
		
	}

	public static void setListaDeAnalistas(ArrayList<Analista> listaDeAnalistas) {
		Fabrica.listaDeAnalistas = listaDeAnalistas;
	}

	public static ArrayList<Estudiante> getListaDeEstudiantes() {
		return listaDeEstudiantes;
	}

	public static void setListaDeEstudiantes(ArrayList<Estudiante> listaDeEstudiantes) {
		Fabrica.listaDeEstudiantes = listaDeEstudiantes;
	}

	public static ArrayList<Tutor> getListaDeTutroes() {
		return listaDeTutores;
	}

	public static void setListaDeTutroes(ArrayList<Tutor> listaDeTutroes) {
		Fabrica.listaDeTutores = listaDeTutroes;
	}

	public static void setListaDeConstancias(ArrayList<Constancia> listaDeConstancias) {
		Fabrica.listaDeConstancias = listaDeConstancias;
	}

	public static Map<String, String> getDiccionarioCampo() {
		return diccionarioCampo;
	}

	public static void setDiccionarioCampo(Map<String, String> diccionarioCampo) {
		Fabrica.diccionarioCampo = diccionarioCampo;
	}

	public static ArrayList<Tutor> getListaDeTutores() {
		return listaDeTutores;
	}

	public static void setListaDeTutores(ArrayList<Tutor> listaDeTutores) {
		Fabrica.listaDeTutores = listaDeTutores;
	}

	public static ArrayList<Constancia> getListaDeConstancias() {
		return listaDeConstancias;
	}

	// Métodos de apoyo
	public static void cargarDiccionario() {
		diccionarioCampo.put("Nombre Completo", "primerNombre" + " " + "primerApellido");
		diccionarioCampo.put("Nombres", "u.primerNombre");
		diccionarioCampo.put("Apellidos", "u.primerApellido");
		diccionarioCampo.put("Documento", "CAST(u.documento AS string)");
		diccionarioCampo.put("Rol", "u.rol.idRol");
		diccionarioCampo.put("Confirmar", "u.confirmado");
		diccionarioCampo.put("Activo", "u.activo");
		diccionarioCampo.put("Nombre de Usuario", "u.nombreUsuario");
		diccionarioCampo.put("Mail Personal", "u.mailPersonal");
	}

	public static void limpiarListas() {
		listaDeAnalistas.clear();
		listaDeConstancias.clear();
		listaDeEstudiantes.clear();
		listaDeTutores.clear();
		listaDeEstadosConstancias.clear();
	}

	public static void cargarListas() {
		limpiarListas();
		setListaDeAnalistas(ServiceAnalista.listarAnalistas());
		setListaDeEstudiantes(ServiceEstudiante.listarEstudiantes());
		setListaDeTutroes(ServiceTutor.listarTutores());
		setListaDeConstancias(ServiceConstancia.listarConstancias());
		setListaDeEstadosConstancias(ServiceEstadoConstancia.listarEstadosContancias());
	}

	// Metodos para unir servicios o centralizar acciones que pueden escalar
	public static ArrayList<Usuario> buscarUsuarioPorCampoYFiltro(String filtro, String campo) {
		if (diccionarioCampo.size() == 0) {
			cargarDiccionario();
		}
		try {
			if (campo.equals("Nombre Completo")) {
				return buscarUsuarioPorNombreApellido(filtro, campo);
			}
			String valor = diccionarioCampo.get(campo);
			if (campo.equals("Rol")) {
				filtro = String.valueOf(ServiceRol.listarRolesFiltro(filtro).get(0).getIdRol());
			}
			return ServiceUsuario.buscarUsuarioPorCampoYFiltro(filtro, valor);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Usuario> buscarUsuarioPorNombreApellido(String filtro, String campo) {
		if (diccionarioCampo.size() == 0) {
			cargarDiccionario();
		}

		String filtro1 = null;
		String filtro2 = null;

		try {
			String valor = diccionarioCampo.get(campo);

			String[] partes = filtro.split(" ");
			filtro1 = partes[0]; // Nombre
			filtro2 = partes[1]; // Apellido
			return ServiceUsuario.listarUsuariosPorNombreApellido(filtro1, filtro2, valor);
		} catch (Exception e) {
			return null;
		}

	}

	public static String generarNombreUsuario(String emailInstitucional) {

		String[] partes = emailInstitucional.split("@");
		String nombreDeUsuario = partes[0];
		return nombreDeUsuario;

	}

}
