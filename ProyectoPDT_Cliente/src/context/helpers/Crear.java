package context.helpers;

import java.util.ArrayList;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Tutor;
import com.entities.Usuario;

import services.ServiceAnalista;
import services.ServiceEstudiante;
import services.ServiceEvento;
import services.ServiceTutor;
import services.ServiceUsuario;

public class Crear {

	// USUARIO-----------------------------
	public static boolean usuario(Usuario oUsuario, Object oTipoUsuario) {
		try {
			var oUsuarioCreado = ServiceUsuario.crearUsuario(oUsuario);

			if (oTipoUsuario instanceof Estudiante) {

				var oEstudiate = (Estudiante) oTipoUsuario;
				oEstudiate.setUsuario(oUsuarioCreado);

				var oEstudianteCreado = ServiceEstudiante.crearEstudiante(oEstudiate);
				return oEstudianteCreado;
			}

			if (oTipoUsuario instanceof Analista) {
				var oAnalista = (Analista) oTipoUsuario;
				oAnalista.setUsuario(oUsuarioCreado);
				var oAnalistaCreado = ServiceAnalista.crearAnalista(oAnalista);
				return oAnalistaCreado;
			}

			if (oTipoUsuario instanceof Tutor) {
				var oTutor = (Tutor) oTipoUsuario;
				oTutor.setUsuario(oUsuarioCreado);

				var oTutorCreado = ServiceTutor.crearTutor(oTutor);
				return oTutorCreado;
			}

			return false;
		} catch (Exception e) {
			return false;
		}
	}

	// USUARIO-----------------------------

	// EVENTO-----------------------------
	public static boolean crearUnEvento(Evento oEvento, ArrayList<Tutor> listaDeTutores) {
		try {
			var oEventoCreado = ServiceEvento.crearEvento(oEvento);

			if (oEventoCreado != null) {
				boolean valor = false;
				for (Tutor tutor : listaDeTutores) {
					valor = ServiceEvento.asignarTutorAEvento(oEventoCreado, tutor);
				}
				return valor;
			}

			return false;
		} catch (Exception e) {
			return false;
		}
	}
	// EVENTO-----------------------------
}
