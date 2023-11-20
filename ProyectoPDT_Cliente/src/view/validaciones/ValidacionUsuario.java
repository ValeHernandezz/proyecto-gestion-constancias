package view.validaciones;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import context.Fabrica;

public class ValidacionUsuario {

	public static boolean validarUnUsuario(String clave, String cedula, Date fechaNacimiento, String mailInstitucional,
			String mailPersonal, String primerApellido, String primerNombre, String segundoApellido,
			String segundoNombre, String telefono, String rol) {

		var validarNombres = validarNombres(primerNombre, segundoNombre);
		var validarApellidos = validarApellidos(primerApellido, segundoApellido);
		var validarMailPersonal = validarMailPersonal(mailPersonal, cedula);
		var validarMailInstitucional = false;

		if (rol.equals("estudiante")) {
			validarMailInstitucional = validarMailInstitucionalEstudiante(mailInstitucional);
		}
		if (rol.equals("funcionario")) {
			validarMailInstitucional = validarMailInstitucionalFuncionario(mailInstitucional);
		}

		var validarTelefono = validarTelefono(telefono);
		var validarCedula = validarCedulaUruguaya(cedula);
		var validarFechaNacimiento = validarFechaDeNacimiento(fechaNacimiento);
		var validarClave = validarClave(clave);

		if (validarNombres && validarApellidos && validarMailPersonal && validarMailInstitucional && validarTelefono
				&& validarCedula && validarFechaNacimiento && validarClave) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean validarNombres(String primerNombre, String segundoNombre) {
		boolean resultado = true;

		if (segundoNombre.equals("")) {

			if (primerNombre.length() > 20 || primerNombre.length() < 3) {
				JOptionPane.showMessageDialog(null, "El primer nombre debe tener una longitud entre 3 y 20 caracteres.",
						"Error", JOptionPane.ERROR_MESSAGE);
				resultado = false;
			}

		} else {
			if ((primerNombre.length() > 20 || primerNombre.length() < 3)
					&& (segundoNombre.length() > 20 || segundoNombre.length() < 3)) {
				JOptionPane.showMessageDialog(null, "Los nombres deben tener una longitud entre 3 y 20 caracteres.",
						"Error", JOptionPane.ERROR_MESSAGE);
				resultado = false;
			}
		}

		return resultado;
	}

	private static boolean validarApellidos(String primerApellido, String segundoApellido) {
		boolean resultado = true;

		if (segundoApellido.equals("")) {

			if (primerApellido.length() > 20 || primerApellido.length() < 3) {
				JOptionPane.showMessageDialog(null,
						"El primer apellido debe tener una longitud entre 3 y 20 caracteres.", "Error",
						JOptionPane.ERROR_MESSAGE);
				resultado = false;
			}

		} else {
			if ((primerApellido.length() > 20 || primerApellido.length() < 3)
					&& (segundoApellido.length() > 20 || segundoApellido.length() < 3)) {
				JOptionPane.showMessageDialog(null,
						"Los apellidos deben tener una longitud entre 3 y 20 caracteres cada uno.", "Error",
						JOptionPane.ERROR_MESSAGE);
				resultado = false;
			}
		}
		return resultado;
	}

	private static boolean validarCedulaUruguaya(String cedula) {

		// Para evitar cualquier ingreso mayor o menor a 8 dígitos
		if (cedula.isEmpty() || cedula.length() != 8 || cedula.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "La cédula debe tener 8 dígitos numéricos sin puntos ni guiones.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Para evitar que todos los digitos de la cédula sean iguales
		if (cedula.matches("(\\d)[-]*[$1]+")) {
			JOptionPane.showMessageDialog(null, "La cédula no es válida en Uruguay.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		int suma = 0;
		for (int i = 0; i < 7; i++) {
			int digito = Integer.parseInt(String.valueOf(cedula.charAt(i)));
			int factor = Integer.parseInt(String.valueOf("2987634".charAt(i)));
			suma += digito * factor;
		}

		int verificador = 10 - (suma % 10);
		if (verificador > 9) {
			verificador = 0;
		}

		int ultimoDigito = Integer.parseInt(String.valueOf(cedula.charAt(7)));
		if (verificador != ultimoDigito) {
			JOptionPane.showMessageDialog(null, "La cédula no es válida en Uruguay.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private static boolean validarMailPersonal(String mailPersonal, String documento) {

		// Verificar que el mail sea el de la persona que lo ingresa, lo sabemos por su documento
		var existe = Fabrica.buscarUsuarioPorCampoYFiltro(mailPersonal, "Mail Personal");

		if (existe != null && existe.size() >= 1) {
			// Si existe un registro con el documento que llega y ademas el mail le pertenece a esa persona devolvemos true
			if (existe != null && existe.get(0).getDocumento().toString().equals(documento)
					&& existe.get(0).getMailPersonal().equals(mailPersonal)) {
				return true;
			}
			JOptionPane.showMessageDialog(null, "El mail personal ingresado ya existe. Por favor, ingrese otro.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Verificar que el correo contenga "@" y ".com" luego de "@"
		if (mailPersonal.contains("@") && mailPersonal.contains(".com")
				&& mailPersonal.indexOf("@") < mailPersonal.lastIndexOf(".com")) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "El mail personal ingresado no es válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private static boolean validarMailInstitucionalEstudiante(String correo) {
		// Verificar que el correo tenga el formato especificado
		if (correo.matches("^[A-Za-z]+(\\.[A-Za-z]+)*@estudiantes\\.utec\\.edu\\.uy$") && contarPuntos(correo) == 4) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "El mail institucional ingresado no es válido.\\nIngrese un mail con el siguiente formato: nombre.apellido@estudiantes.utec.edu.uy", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private static boolean validarMailInstitucionalFuncionario(String correo) {

		if (correo.trim().length() < 8) {
			JOptionPane.showMessageDialog(null,
					"El mail institucional ingresado no es válido.\nIngrese un mail con el siguiente formato: nombre.apellido@utec.edu.uy",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Verificar que el correo tenga el formato especificado
		if (correo.matches("^[A-Za-z]+(\\.[A-Za-z]+)*@utec\\.edu\\.uy$") && contarPuntos(correo) == 3) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "El mail institucional ingresado no es válido.\\nIngrese un mail con el siguiente formato: nombre.apellido@utec.edu.uy", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private static int contarPuntos(String correo) {
		// Contar la cantidad de puntos en el correo
		int contador = 0;
		for (int i = 0; i < correo.length(); i++) {
			if (correo.charAt(i) == '.') {
				contador++;
			}
		}
		return contador;
	}

	private static boolean validarTelefono(String telefono) {
		// Verificar que la cadena contenga solo dígitos
		if (!telefono.matches("\\d+")) {
			JOptionPane.showMessageDialog(null,
					"El número de teléfono ingresado es inválido.\nPor favor, asegúrese de ingresar sólo dígitos numéricos.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Verificar que la longitud sea la correcta
		if (telefono.length() != 9) {
			JOptionPane.showMessageDialog(null,
					"El número de teléfono ingresado es inválido.\nPor favor, asegúrese de que la longitud sea de 9 dígitos.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private static boolean validarClave(String clave) {
		if (clave.length() < 7) {
			JOptionPane.showMessageDialog(null,
					"La contraseña ingresada es inválida.\nPor favor, asegúrese de que la contraseña tenga al menos 8 caracteres.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Verificar si la clave contiene al menos una letra y un número
		boolean contieneLetra = false;
		boolean contieneNumero = false;

		for (char c : clave.toCharArray()) {
			if (Character.isLetter(c)) {
				contieneLetra = true;
			} else if (Character.isDigit(c)) {
				contieneNumero = true;
			}

			if (contieneLetra && contieneNumero) {
				return true;
			}
		}

		JOptionPane.showMessageDialog(null,
				"La contraseña ingresada es inválida.\nPor favor, asegúrese de que la contraseña contenga\nal menos 8 dígitos, y solo letras y números.",
				"Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private static boolean validarFechaDeNacimiento(Date fechaNacimiento) {
		Calendar calendarNacimiento = Calendar.getInstance();
		calendarNacimiento.setTime(fechaNacimiento);

		Calendar calendarHoy = Calendar.getInstance();

		// Restamos 18 años a la fecha actual
		calendarHoy.add(Calendar.YEAR, -18);

		if (calendarNacimiento.before(calendarHoy) || calendarNacimiento.equals(calendarHoy)) {
			return true;
		}

		JOptionPane.showMessageDialog(null, "Debe ser mayor de edad para solicitar el registro.", "Error",
				JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public static boolean esUnNumero(String documento) {

		boolean esUnNumero;

		try {

			int numero = Integer.parseInt(documento);
			return esUnNumero = true;

		} catch (NumberFormatException e) {
			return esUnNumero = false;

		}
	}

	public static boolean existeLaCedula(String documento) {
		try {

			var oExisteUsuario = Fabrica.buscarUsuarioPorCampoYFiltro(documento, "Documento").get(0);
			if (oExisteUsuario != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
