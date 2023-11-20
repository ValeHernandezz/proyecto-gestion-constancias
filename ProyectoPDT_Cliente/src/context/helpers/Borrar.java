package context.helpers;

import com.entities.Itr;
import com.entities.Tipo;
import com.entities.Usuario;

import services.ServiceItr;
import services.ServiceTipo;
import services.ServiceUsuario;

public class Borrar {
	
	// USUARIO -----------------------------
	public static boolean darBajaLogica(Usuario oUsuario) {
		try {
			oUsuario.setActivo("N");
			var oUsuarioActualizado = ServiceUsuario.actualizarUsuario(oUsuario);
			if (oUsuarioActualizado == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// ITR -----------------------------
	public static boolean darBajaLogica(Itr oItr) {
		try {
			oItr.setActivo("N");
			var oItrActualizado = ServiceItr.actualizarItr(oItr);
			if (oItrActualizado == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// TIPO CONSTANCIA -----------------------------
		public static boolean darBajaLogica(Tipo oTipo) {
			try {
				oTipo.setActivo("N");
				var oTipoActualizado = ServiceTipo.actualizarTipoConstancia(oTipo);
				if (oTipoActualizado == false) {
					return false;
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}
}
