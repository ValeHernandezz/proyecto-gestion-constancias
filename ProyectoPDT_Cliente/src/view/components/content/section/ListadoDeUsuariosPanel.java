package view.components.content.section;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.entities.Rol;

import services.ServiceRol;
import view.components.content.home.BienvenidoPanel;
import view.components.content.listadoUsuarios.ListadoDeAnalistasPanel;
import view.components.content.listadoUsuarios.ListadoDeEstudiantesPanel;
import view.components.content.listadoUsuarios.ListadoDeTutoresPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;

public class ListadoDeUsuariosPanel extends JPanel {

	// JPanel
	private JPanel panelPrincipal;
	private JPanel panelDatos;

	// JLabel
	private JLabel lblTiposDeUsuario;
	private JLabel labelListarUsuarios;

	// JButton
	private JButton btnEstudiante;
	private JButton btnTutor;
	private JButton btnAnalista;
	private JButton buttonRegresar;

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public ListadoDeUsuariosPanel(MostrarPanel panel) {

		setLayout(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 798, 550);
		add(panelPrincipal);
		panelPrincipal.setLayout(null);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new BienvenidoPanel(), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelPrincipal.add(buttonRegresar);

		labelListarUsuarios = new JLabel("Listado de Usuarios");
		labelListarUsuarios.setBounds(156, 41, 486, 36);
		labelListarUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		labelListarUsuarios.setFont(new Font("Rockwell", Font.BOLD, 40));
		panelPrincipal.add(labelListarUsuarios);

		panelDatos = new JPanel();
		panelDatos.setBorder(null);
		panelDatos.setBounds(123, 195, 551, 160);
		panelDatos.setOpaque(false);
		panelPrincipal.add(panelDatos);
		panelDatos.setLayout(null);

		lblTiposDeUsuario = new JLabel("Tipos de usuario a listar:");
		lblTiposDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiposDeUsuario.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblTiposDeUsuario.setBounds(63, 29, 425, 43);
		panelDatos.add(lblTiposDeUsuario);

		btnEstudiante = new JButton("Estudiante");
		btnEstudiante.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarPanelContent(prepararPanel(new ListadoDeEstudiantesPanel(panel), 798, 550));
			}
		});
		btnEstudiante.setFont(new Font("Cambria", Font.PLAIN, 20));
		btnEstudiante.setBounds(25, 101, 150, 30);
		panelDatos.add(btnEstudiante);

		btnTutor = new JButton("Tutor");
		btnTutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarPanelContent(prepararPanel(new ListadoDeTutoresPanel(panel), 798, 550));
			}
		});
		btnTutor.setFont(new Font("Cambria", Font.PLAIN, 20));
		btnTutor.setBounds(200, 101, 150, 30);
		panelDatos.add(btnTutor);

		btnAnalista = new JButton("Analista");
		btnAnalista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mostrarPanelContent(prepararPanel(new ListadoDeAnalistasPanel(panel), 798, 550));
			}
		});
		btnAnalista.setFont(new Font("Cambria", Font.PLAIN, 20));
		btnAnalista.setBounds(375, 101, 150, 30);
		panelDatos.add(btnAnalista);

		ArrayList<Rol> roles = ServiceRol.listarRoles();

		String[] rolesStrings = new String[roles.size() + 1];
		rolesStrings[0] = "Elige un rol";

		for (int i = 0; i < roles.size(); i++) {
			rolesStrings[i + 1] = roles.get(i).getDescripcion();
		}

		DefaultComboBoxModel<String> comboBoxModelRol = new DefaultComboBoxModel<String>(rolesStrings);

		panelPrincipal.add(imagenFondoContent);

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}

	public void mostrarPanelContent(JPanel panel) {
		panelPrincipal.removeAll();
		panelPrincipal.add(panel);
		panelPrincipal.revalidate();
		panelPrincipal.repaint();
	}
}
