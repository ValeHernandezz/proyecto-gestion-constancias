package context.helpers;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Tipo;
import com.entities.Tutor;
import com.entities.Usuario;

import services.ServiceAnalista;
import services.ServiceArea;
import services.ServiceEstudiante;
import services.ServiceItr;
import services.ServiceTipo;
import services.ServiceTutor;
import services.ServiceUsuario;

public class Actualizar {

	public static boolean usuario(Usuario oUsuario, Object oTipoUsuario) {
		try {
			var usuarioEditado = ServiceUsuario.actualizarUsuario(oUsuario);

			if (oTipoUsuario instanceof Estudiante) {

				var oEstudiate = (Estudiante) oTipoUsuario;
				oEstudiate.setUsuario(usuarioEditado);

				var oEstudianteCreado = ServiceEstudiante.actualizarEstudiante(oEstudiate);
				return oEstudianteCreado;
			}

			if (oTipoUsuario instanceof Analista) {
				var oAnalista = (Analista) oTipoUsuario;
				oAnalista.setUsuario(usuarioEditado);

				var oAnalistaCreado = ServiceAnalista.actualizarAnalista(oAnalista);
				return oAnalistaCreado;
			}

			if (oTipoUsuario instanceof Tutor) {
				var oTutor = (Tutor) oTipoUsuario;
				oTutor.setUsuario(usuarioEditado);

				var oTutorCreado = ServiceTutor.actualizarTutor(oTutor);
				return oTutorCreado;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean itr(Itr oItr) {
		try {

			return ServiceItr.actualizarItr(oItr) != null ? true : false;
			
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean tipoConstancia(Tipo oTipoConstancia) {
		try {

			return ServiceTipo.actualizarTipoConstancia(oTipoConstancia);
			
		} catch (Exception e) {
			return false;
		}
	}

}
