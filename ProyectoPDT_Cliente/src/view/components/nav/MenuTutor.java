package view.components.nav;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.entities.Usuario;

import context.Fabrica;
import view.components.content.auth.LoginPanel;
import view.components.content.listadoReportes.funcionario.ReporteFuncionarioPanel;
import view.components.content.profile.MiPerfilTutorPanel;
import view.components.utils.MostrarPanel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuTutor extends JPanel {

	// JPanel
	private JPanel panelMenuJefe;

	// JLabel
	private JLabel labelMenuTutor;
	private JLabel labelNombreCompleto;

	// JButton
	private JButton buttonCerrarSesion;
	private JButton btnMiPerfil;
	private JButton btnReporte;

	public MenuTutor(MostrarPanel panel, Usuario oUsuario) {
		setLayout(null);

		panelMenuJefe = new JPanel();
		panelMenuJefe.setBounds(0, 0, 200, 550);
		panelMenuJefe.setBackground(Color.decode("#44bcf4"));
		add(panelMenuJefe);
		panelMenuJefe.setLayout(null);

		labelMenuTutor = new JLabel(oUsuario.getRol().getDescripcion());
		labelMenuTutor.setFont(new Font("Cambria", Font.BOLD, 25));
		labelMenuTutor.setHorizontalAlignment(SwingConstants.CENTER);
		labelMenuTutor.setBounds(-17, 25, 234, 44);
		panelMenuJefe.add(labelMenuTutor);

		labelNombreCompleto = new JLabel(oUsuario.getNombreCompleto());
		labelNombreCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreCompleto.setFont(new Font("Cambria", Font.PLAIN, 13));
		labelNombreCompleto.setBounds(10, 80, 180, 13);
		panelMenuJefe.add(labelNombreCompleto);

		buttonCerrarSesion = new JButton("Cerrar Sesión");
		buttonCerrarSesion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Fabrica.setoUsuarioLogueado(new Usuario());
				LoginPanel loginPanel = new LoginPanel(panel);
				loginPanel.setBounds(0, 0, 1028, 570);
				panel.mostrarPanelContent(loginPanel);
				panel.ocultarMenu();

			}
		});
		buttonCerrarSesion.setFont(new Font("Cambria", Font.PLAIN, 12));
		buttonCerrarSesion.setBounds(18, 500, 163, 25);
		panelMenuJefe.add(buttonCerrarSesion);

		btnMiPerfil = new JButton("Mi Perfil");
		btnMiPerfil.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new MiPerfilTutorPanel(panel), 798, 550));

			}
		});
		btnMiPerfil.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnMiPerfil.setBounds(18, 462, 163, 25);
		panelMenuJefe.add(btnMiPerfil);

		btnReporte = new JButton("Reportes");
		btnReporte.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnReporte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.mostrarPanelContent(prepararPanel(new ReporteFuncionarioPanel(panel), 798, 550));
			}
		});
		btnReporte.setBounds(18, 193, 163, 25);
		panelMenuJefe.add(btnReporte);

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}

}
