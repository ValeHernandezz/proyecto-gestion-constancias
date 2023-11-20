package view.components.utils;

import javax.swing.JPanel;

public interface MostrarPanel {

	void mostrarPanelMenu(JPanel panel);

	void mostrarPanelContent(JPanel panel);

	JPanel prepararPanel(JPanel panel, int largo, int alto);

	void mostrarMenu();

	void ocultarMenu();

}
