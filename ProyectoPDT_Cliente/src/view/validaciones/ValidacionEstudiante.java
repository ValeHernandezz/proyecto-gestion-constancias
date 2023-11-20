package view.validaciones;

import javax.swing.JOptionPane;

public class ValidacionEstudiante {

	public static boolean validarComboBoxesEstudiante(int comboIndexDepartamento, int comboIndexGenero,
			int comboIndexLocalidad, int comboIndexItr, int comboIndexGeneracion, int comboIndexSemestre) {
		boolean comboGenero = true;
		boolean comboDepartamento = true;
		boolean comboLocalidad = true;
		boolean comboItr = true;
		boolean comboGeneracion = true;
		boolean comboSemestre = true;

		if (comboIndexGeneracion == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir una generación");
			comboGeneracion = false;
		}
		if (comboIndexSemestre == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir un semestre");
			comboSemestre = false;
		}
		if (comboIndexDepartamento == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir un deparatamento");
			comboDepartamento = false;
		}
		if (comboIndexGenero == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir un género");
			comboGenero = false;
		}
		if (comboIndexLocalidad == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir un localidad");
			comboLocalidad = false;
		}
		if (comboIndexItr == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir una itr");
			comboItr = false;
		}

		if (comboGeneracion && comboItr && comboLocalidad && comboGenero && comboDepartamento && comboSemestre) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
