package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Tutor;
import com.services.usuario.tutor.TutorBeanRemote;

public class ServiceTutor {

	private static TutorBeanRemote getService() {
		try {
			TutorBeanRemote tutorBean = (TutorBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/TutorBean!com.services.usuario.tutor.TutorBeanRemote");
			return tutorBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearTutor(Tutor oTutor) {
		try {
			var tutorBean = getService();
			return tutorBean.crearTutor(oTutor);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Tutor> listarTutores() {
		try {
			var tutorBean = getService();
			return tutorBean.listarTutores();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean actualizarTutor(Tutor oTutor) {
		try {
			var tutorBean = getService();
			return tutorBean.actualizarTutor(oTutor);
		} catch (Exception e) {
			return false;
		}
	}
}
