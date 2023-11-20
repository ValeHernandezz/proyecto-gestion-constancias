package view.validaciones;

import javax.swing.JOptionPane;

public class ValidacionTutor {
	public static boolean validarComboBoxesTutor(int comboIndexDepartamento, int comboIndexGenero,
			int comboIndexLocalidad, int comboIndexItr, int comboIndexRol, int comboIndexArea) {
		boolean comboGenero = true;
		boolean comboDepartamento = true;
		boolean comboLocalidad = true;
		boolean comboItr = true;
		boolean comboArea = true;
		boolean comboRol = true;

		if (comboIndexArea == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir una área");
			comboArea = false;
		}
		if (comboIndexRol == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir un rol");
			comboRol = false;
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

		if (comboArea && comboRol && comboItr && comboLocalidad && comboGenero && comboDepartamento) {
			return true;
		} else {
			return false;
		}
	}
}
