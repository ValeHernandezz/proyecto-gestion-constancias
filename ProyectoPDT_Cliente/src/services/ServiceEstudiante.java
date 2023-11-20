package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Estudiante;
import com.services.usuario.estudiante.EstudianteBeanRemote;

public class ServiceEstudiante {

	private static EstudianteBeanRemote getService() {
		try {
			EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_EJB/EstudianteBean!com.services.usuario.estudiante.EstudianteBeanRemote");
			return estudianteBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearEstudiante(Estudiante oEstudiante) {
		try {
			var estudianteBean = getService();
			return estudianteBean.crearEstudiante(oEstudiante);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Estudiante> listarEstudiantes(){
		try {
			var estudianteBean = getService();
			return estudianteBean.listarEstudiantes();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean actualizarEstudiante(Estudiante oEstudiante) {
		try {
			var estudianteBean = getService();
			return estudianteBean.actualizarEstudiante(oEstudiante);
		} catch (Exception e) {
			return false;
		}
	}
}
