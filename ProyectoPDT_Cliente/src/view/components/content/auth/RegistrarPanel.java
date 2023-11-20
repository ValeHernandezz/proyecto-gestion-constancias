package view.components.content.auth;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.components.content.auth.users.AnalistaRegistroPanel;
import view.components.content.auth.users.EstudianteRegistroPanel;
import view.components.content.auth.users.TutorRegistroPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;

public class RegistrarPanel extends JPanel {

	// JPanel
	private JPanel panelMain;
	private JPanel panelTitulo;
	private JPanel panelContent;

	// JLabel
	private JLabel lblTitulo;
	private JLabel lblTextoEleccion;
	private JLabel lblYaTienesUnaCuenta;

	// JButton
	private JButton btnAnalista;
	private JButton btnTutor;
	private JButton btnEstudiante;
	private JButton btnIniciarSesión;

	// Imágenes
	private ImagenesHelper imagenFondoLogin = new ImagenesHelper("/view/img/ImagenFondoLogin.png", 1028, 1028);
	private ImagenesHelper imagenContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 1008, 468);

	public RegistrarPanel(MostrarPanel panel) {
		setLayout(null);

		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 1028, 570);
		add(panelMain);
		panelMain.setLayout(null);

		panelTitulo = new JPanel();
		panelTitulo.setBounds(10, 10, 1008, 72);
		panelTitulo.setBackground(Color.decode("#44bcf4"));
		panelMain.add(panelTitulo);
		panelTitulo.setLayout(null);

		lblTitulo = new JLabel("Solicitud de Registro");
		lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 40));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(206, 7, 595, 57);
		panelTitulo.add(lblTitulo);

		panelContent = new JPanel();
		panelContent.setBounds(10, 92, 1008, 468);
		panelContent.setBackground(new Color(255, 255, 255));
		panelMain.add(panelContent);
		panelContent.setLayout(null);

		lblTextoEleccion = new JLabel("Tipos de usuarios para solicitar el registro:");
		lblTextoEleccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextoEleccion.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblTextoEleccion.setBounds(112, 112, 783, 43);
		panelContent.add(lblTextoEleccion);

		btnAnalista = new JButton("Analista");
		btnAnalista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mostrarPanelMenu(prepararPanel(new AnalistaRegistroPanel(panel), 1028, 434));

			}
		});
		btnAnalista.setBounds(290, 223, 115, 25);
		panelContent.add(btnAnalista);
		btnAnalista.setFont(new Font("Cambria", Font.PLAIN, 17));

		btnTutor = new JButton("Tutor");
		btnTutor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				mostrarPanelMenu(prepararPanel(new TutorRegistroPanel(panel), 1028, 434));

			}
		});
		btnTutor.setBounds(445, 223, 115, 25);
		panelContent.add(btnTutor);
		btnTutor.setFont(new Font("Cambria", Font.PLAIN, 17));

		btnEstudiante = new JButton("Estudiante");
		btnEstudiante.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarPanelMenu(prepararPanel(new EstudianteRegistroPanel(panel), 1028, 434));
			}
		});
		btnEstudiante.setBounds(600, 223, 115, 25);
		panelContent.add(btnEstudiante);
		btnEstudiante.setFont(new Font("Cambria", Font.PLAIN, 17));

		lblYaTienesUnaCuenta = new JLabel("¿Ya tienes una cuenta?");
		lblYaTienesUnaCuenta.setForeground(Color.decode("#828588"));
		lblYaTienesUnaCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblYaTienesUnaCuenta.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblYaTienesUnaCuenta.setBounds(219, 372, 570, 20);
		panelContent.add(lblYaTienesUnaCuenta);

		btnIniciarSesión = new JButton("Iniciar Sesión");
		btnIniciarSesión.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new LoginPanel(panel), 1028, 570));

			}
		});
		btnIniciarSesión.setBounds(433, 408, 141, 25);
		panelContent.add(btnIniciarSesión);
		btnIniciarSesión.setFont(new Font("Cambria", Font.PLAIN, 17));

		// Agrega la imagen al panel indicado
		panelMain.add(imagenFondoLogin);
		panelContent.add(imagenContent);

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}

	public void mostrarPanelMenu(JPanel panel) {
		panelContent.removeAll();
		panel.setBounds(0, 0, panelContent.getWidth(), panelContent.getHeight());
		panelContent.add(panel);
		panelContent.revalidate();
		panelContent.repaint();
	}

}
