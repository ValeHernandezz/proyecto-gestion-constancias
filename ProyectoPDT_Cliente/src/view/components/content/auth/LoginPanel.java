package view.components.content.auth;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.entities.Usuario;
import com.utils.Respuesta;

import context.Fabrica;
import context.helpers.Buscar;
import services.ServiceUsuario;
import view.components.content.firmaElectronica.CargarFirmaElectronicaFrame;
import view.components.content.home.BienvenidoPanel;
import view.components.nav.MenuAnalista;
import view.components.nav.MenuEstudiante;
import view.components.nav.MenuTutor;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;

public class LoginPanel extends JPanel {

	// JPanel
	private JPanel panelLogin;
	private JPanel panelDatos;
	private JPanel panelLogoTipo;
	// JLabel
	private JLabel labelInicioDeSesion;
	private JLabel lblNewLabel;
	private JLabel labelMail;
	private JLabel labelClave;

	// JPasswordField
	private JPasswordField passwordFieldClave;

	// JTextField
	private JTextField textFieldMail;

	// JButton
	private JButton buttonIngresar;
	private JButton buttonSolicitarRegistro;

	// Imágenes
	private ImagenesHelper imagenFondoLogin = new ImagenesHelper("/view/img/ImagenFondoLogin.png", 1028, 1028);
	private ImagenesHelper imagenApp = new ImagenesHelper("/view/img/ImagenLogoApp.png", 100, 100);

	// Extras
	private BienvenidoPanel panelBienvenido;

	public LoginPanel(MostrarPanel panel) {
		setLayout(null);

		panelLogin = new JPanel();
		panelLogin.setBackground(Color.decode("#d8d8d8"));
		panelLogin.setBounds(0, 0, 1028, 570);
		add(panelLogin);
		panelLogin.setLayout(null);

		panelDatos = new JPanel();
		panelDatos.setBounds(0, 0, 384, 570);
		panelDatos.setBackground(new Color(255, 255, 255));
		panelLogin.add(panelDatos);
		panelDatos.setLayout(null);

		panelLogoTipo = new JPanel();
		panelLogoTipo.setBounds(142, 37, 100, 100);
		panelLogoTipo.setOpaque(false); // Establecer el panel como transparente
		panelDatos.add(panelLogoTipo);
		panelLogoTipo.setLayout(null);

		panelLogoTipo.add(imagenApp);

		labelInicioDeSesion = new JLabel("Inicio de Sesión");
		labelInicioDeSesion.setBounds(28, 164, 327, 47);
		panelDatos.add(labelInicioDeSesion);
		labelInicioDeSesion.setHorizontalAlignment(SwingConstants.CENTER);
		labelInicioDeSesion.setFont(new Font("Rockwell", Font.BOLD, 40));

		lblNewLabel = new JLabel("Ingresa tus credenciales para acceder a tu cuenta.");
		lblNewLabel.setForeground(Color.decode("#828588"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNewLabel.setBounds(21, 238, 341, 30);
		panelDatos.add(lblNewLabel);

		labelMail = new JLabel("Nombre de usuario");
		labelMail.setBounds(114, 295, 219, 13);
		panelDatos.add(labelMail);
		labelMail.setFont(new Font("Cambria", Font.PLAIN, 15));

		textFieldMail = new JTextField();
		textFieldMail.setBounds(114, 318, 156, 19);
		panelDatos.add(textFieldMail);
		textFieldMail.setFont(new Font("Cambria", Font.PLAIN, 13));
		textFieldMail.setColumns(10);

		labelClave = new JLabel("Contraseña");
		labelClave.setBounds(114, 362, 74, 13);
		panelDatos.add(labelClave);
		labelClave.setFont(new Font("Cambria", Font.PLAIN, 15));

		passwordFieldClave = new JPasswordField();
		passwordFieldClave.setBounds(114, 385, 156, 19);
		panelDatos.add(passwordFieldClave);
		passwordFieldClave.setFont(new Font("Cambria", Font.PLAIN, 13));

		buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setBounds(144, 434, 95, 25);
		panelDatos.add(buttonIngresar);
		buttonIngresar.setFont(new Font("Cambria", Font.PLAIN, 15));
		buttonIngresar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String nombreUsuario = textFieldMail.getText();
				String clave = passwordFieldClave.getText();

				if (nombreUsuario.length() < 7 || clave.length() < 3) {
					JOptionPane.showMessageDialog(null, "Campos incompletos", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Respuesta oRespuesta = ServiceUsuario.login(nombreUsuario, clave);

					if (oRespuesta.getData() == null || oRespuesta.getStatus().equals("error")) {
						JOptionPane.showMessageDialog(null, oRespuesta.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (oRespuesta.getData() != null && oRespuesta.getStatus().equals("success")) {

						Usuario existeUsuario = (Usuario) oRespuesta.getData();

						panelBienvenido = new BienvenidoPanel();
						panelBienvenido.setSize(798, 550);
						panelBienvenido.setLocation(0, 0);
						panel.mostrarPanelContent(panelBienvenido);
						panel.mostrarMenu();
						Fabrica.setoUsuarioLogueado(existeUsuario);

						if (existeUsuario.getRol().getDescripcion().equals("Analista")) {
							panel.mostrarPanelMenu(prepararPanel(new MenuAnalista(panel, existeUsuario), 200, 550));

							if (Buscar.analistaFiltro(existeUsuario.getDocumento().toString(), "Documento").get(0)
									.getFirma() == null) {

								Object[] options = { "Cargar Firma" };
								int option = JOptionPane.showOptionDialog(null, "Debe cargar su firma electrónica",
										"Carga Firma Electrónica", JOptionPane.DEFAULT_OPTION,
										JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

								if (option == 0) {
									CargarFirmaElectronicaFrame cargarFirmaElectronicaFrame = new CargarFirmaElectronicaFrame(
											Buscar.analistaFiltro(existeUsuario.getDocumento().toString(), "Documento")
													.get(0),
											false);
									cargarFirmaElectronicaFrame.setLocationRelativeTo(null);
									cargarFirmaElectronicaFrame.setVisible(true);
								} else {

									JOptionPane.showMessageDialog(null,
											"Para iniciar sesión debe cargar su firma electrónica en formato de imagen.",
											"Advertencia", JOptionPane.INFORMATION_MESSAGE);

									Fabrica.setoUsuarioLogueado(new Usuario());
									LoginPanel loginPanel = new LoginPanel(panel);
									loginPanel.setBounds(0, 0, 1028, 570);
									panel.mostrarPanelContent(loginPanel);
									panel.ocultarMenu();
								}
							}
							return;
						}
						if (existeUsuario.getRol().getDescripcion().equals("Tutor")
								|| existeUsuario.getRol().getDescripcion().equals("Encargado")) {
							panel.mostrarPanelMenu(prepararPanel(new MenuTutor(panel, existeUsuario), 200, 550));
							return;
						}
						if (existeUsuario.getRol().getDescripcion().equals("Estudiante")) {
							panel.mostrarPanelMenu(prepararPanel(new MenuEstudiante(panel, existeUsuario), 200, 550));
							return;
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en el sistema");
				}
			}
		});

		buttonSolicitarRegistro = new JButton("Solicitar Registro");
		buttonSolicitarRegistro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new RegistrarPanel(panel), 1028, 570));

			}
		});
		buttonSolicitarRegistro.setFont(new Font("Cambria", Font.PLAIN, 15));
		buttonSolicitarRegistro.setBounds(114, 493, 156, 25);
		panelDatos.add(buttonSolicitarRegistro);

		panelLogin.add(imagenFondoLogin);

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
