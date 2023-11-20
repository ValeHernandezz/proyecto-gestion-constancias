package view.components.nav;

import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.entities.Usuario;

import context.Fabrica;
import context.helpers.Buscar;
import view.components.content.auth.LoginPanel;
import view.components.content.listadoReportes.estudiante.ReporteEstudiantePanel;
import view.components.content.listadoSolicitudes.estudiante.SolicitudDeConstanciaPanel;
import view.components.content.profile.MiPerfilEstudiantePanel;
import view.components.content.reclamo.ReclamoEstudiantePanel;
import view.components.utils.MostrarPanel;

public class MenuEstudiante extends JPanel {

	// JPanel
	private JPanel panelMenuOperador;

	// JLabel
	private JLabel labelMenuEstudiante;
	private JLabel labelNombreCompleto;

	// JButton
	private JButton btnSolicitudDeConstancia;
	private JButton btnMiPerfil;
	private JButton buttonCerrarSesion;
	private JButton btnReporte;

	public MenuEstudiante(MostrarPanel panel, Usuario oUsuario) {
		setLayout(null);

		panelMenuOperador = new JPanel();
		panelMenuOperador.setBounds(0, 0, 200, 550);
		panelMenuOperador.setBackground(Color.decode("#44bcf4"));
		add(panelMenuOperador);
		panelMenuOperador.setLayout(null);

		labelMenuEstudiante = new JLabel("Estudiante");
		labelMenuEstudiante.setHorizontalAlignment(SwingConstants.CENTER);
		labelMenuEstudiante.setFont(new Font("Cambria", Font.BOLD, 25));
		labelMenuEstudiante.setBounds(-19, 25, 238, 44);
		panelMenuOperador.add(labelMenuEstudiante);

		labelNombreCompleto = new JLabel(oUsuario.getNombreCompleto());
		labelNombreCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreCompleto.setFont(new Font("Cambria", Font.PLAIN, 13));
		labelNombreCompleto.setBounds(10, 80, 180, 13);
		panelMenuOperador.add(labelNombreCompleto);

		btnSolicitudDeConstancia = new JButton("Solicitud de Constancia");
		btnSolicitudDeConstancia.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnSolicitudDeConstancia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SolicitudDeConstanciaPanel gestionConstancia = new SolicitudDeConstanciaPanel(panel);
				gestionConstancia.setSize(798, 550);
				gestionConstancia.setLocation(0, 0);
				panel.mostrarPanelContent(gestionConstancia);
			}
		});
		btnSolicitudDeConstancia.setBounds(26, 200, 147, 25);
		panelMenuOperador.add(btnSolicitudDeConstancia);

		btnMiPerfil = new JButton("Mi Perfil");
		btnMiPerfil.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new MiPerfilEstudiantePanel(panel), 798, 550));

			}
		});
		btnMiPerfil.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnMiPerfil.setBounds(18, 463, 163, 25);
		panelMenuOperador.add(btnMiPerfil);

		buttonCerrarSesion = new JButton("Cerrar Sesión");
		buttonCerrarSesion.setFont(new Font("Cambria", Font.PLAIN, 12));
		buttonCerrarSesion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Fabrica.setoUsuarioLogueado(new Usuario());
				LoginPanel loginPanel = new LoginPanel(panel);
				loginPanel.setBounds(0, 0, 1028, 570);
				panel.mostrarPanelContent(loginPanel);
				panel.ocultarMenu();

			}
		});
		buttonCerrarSesion.setBounds(18, 500, 163, 25);
		panelMenuOperador.add(buttonCerrarSesion);

		btnReporte = new JButton("Reportes");
		btnReporte.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnReporte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.mostrarPanelContent(prepararPanel(new ReporteEstudiantePanel(panel, Buscar
						.estudianteFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento").get(0)),
						798, 550));
			}
		});
		btnReporte.setBounds(26, 297, 147, 25);
		panelMenuOperador.add(btnReporte);

		JButton btnReclamo = new JButton("Reclamos");
		btnReclamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Declarar una varibale de una clase
				// Tipo nombreDeVaribale = new Tipo();
				// Dato primitivo
				// tipo nombreDeVaribale = 23;
				ReclamoEstudiantePanel reclamoEstudiantePanel = new ReclamoEstudiantePanel();
				reclamoEstudiantePanel.setBounds(0, 0, 798, 550);
				panel.mostrarPanelContent(reclamoEstudiantePanel);

			}
		});
		btnReclamo.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnReclamo.setBounds(26, 249, 147, 25);
		panelMenuOperador.add(btnReclamo);

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
