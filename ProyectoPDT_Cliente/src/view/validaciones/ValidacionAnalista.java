package view.validaciones;

import javax.swing.JOptionPane;

public class ValidacionAnalista {

	public static boolean validarComboBoxesAnalista(int comboIndexDepartamento, int comboIndexGenero,
			int comboIndexLocalidad, int comboIndexItr) {
		boolean comboGenero = true;
		boolean comboDepartamento = true;
		boolean comboLocalidad = true;
		boolean comboItr = true;

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

		if (comboItr && comboLocalidad && comboGenero && comboDepartamento) {
			return true;
		} else {
			return false;
		}
	}
}
