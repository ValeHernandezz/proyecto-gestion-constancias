package view.components.nav;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

import com.entities.Usuario;

import context.Fabrica;
import view.components.content.auth.LoginPanel;
import view.components.content.listadoItr.ListadoItrPanel;
import view.components.content.listadoReportes.funcionario.ReporteFuncionarioPanel;
import view.components.content.listadoSolicitudes.analista.ListadoSolicitudesConstanciasPanel;
import view.components.content.listadoTipoConstancia.TiposConstanciasPanel;
import view.components.content.listadoUsuarios.ConfirmarUsuarioPanel;
import view.components.content.profile.MiPerfilAnalistaPanel;
import view.components.content.reclamo.ReclamoAnalistaPanel;
import view.components.content.section.GestionDeEventosAnalistaPanel;
import view.components.content.section.ListadoDeUsuariosPanel;
import view.components.utils.MostrarPanel;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MenuAnalista extends JPanel {

	// JPanel
	private JPanel panelMenuAdministrador;
	private JPanel panelBotones;

	// JLabel
	private JLabel labelMenuAnalista;
	private JLabel labelNombreCompleto;

	// JButton
	private JButton buttonUsuarioAConfirmar;
	private JButton buttonListarUsuarios;
	private JButton buttonCerrarSesión;
	private JButton btnListarSolicitudes;
	private JButton btnGestionDeEventos;
	private JButton btnItrs;
	private JButton btnReporte;
	private JButton btnTiposDeConstancia;
	private JButton btnMiPerfil;
	private JButton btnReclamos;

	public MenuAnalista(MostrarPanel panel, Usuario oUsuario) {
		setLayout(null);

		panelMenuAdministrador = new JPanel();
		panelMenuAdministrador.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panelMenuAdministrador.setBackground(Color.decode("#44bcf4"));
		panelMenuAdministrador.setBounds(0, 0, 200, 550);
		add(panelMenuAdministrador);
		panelMenuAdministrador.setLayout(null);

		labelMenuAnalista = new JLabel("Analista");
		labelMenuAnalista.setHorizontalAlignment(SwingConstants.CENTER);
		labelMenuAnalista.setFont(new Font("Rockwell", Font.BOLD, 25));
		labelMenuAnalista.setBounds(-19, 7, 238, 44);
		panelMenuAdministrador.add(labelMenuAnalista);

		labelNombreCompleto = new JLabel(oUsuario.getNombreCompleto());
		labelNombreCompleto.setFont(new Font("Cambria", Font.PLAIN, 13));
		labelNombreCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreCompleto.setBounds(10, 58, 180, 13);
		panelMenuAdministrador.add(labelNombreCompleto);

		panelBotones = new JPanel();
		panelBotones.setBounds(10, 78, 180, 463);
		panelBotones.setOpaque(false);
		panelMenuAdministrador.add(panelBotones);
		panelBotones.setLayout(null);

		buttonListarUsuarios = new JButton("Listar Usuarios");
		buttonListarUsuarios.setBounds(8, 19, 163, 25);
		panelBotones.add(buttonListarUsuarios);
		buttonListarUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ListadoDeUsuariosPanel listadoDeUsuariosPanel = new ListadoDeUsuariosPanel(panel);
				listadoDeUsuariosPanel.setSize(798, 550);
				listadoDeUsuariosPanel.setLocation(0, 0);
				panel.mostrarPanelContent(listadoDeUsuariosPanel);

			}
		});
		buttonListarUsuarios.setFont(new Font("Cambria", Font.PLAIN, 13));

		buttonUsuarioAConfirmar = new JButton("Usuarios a confirmar");
		buttonUsuarioAConfirmar.setBounds(8, 63, 163, 25);
		panelBotones.add(buttonUsuarioAConfirmar);
		buttonUsuarioAConfirmar.setFont(new Font("Cambria", Font.PLAIN, 13));

		btnListarSolicitudes = new JButton("Listar Solicitudes");
		btnListarSolicitudes.setBounds(8, 107, 163, 25);
		panelBotones.add(btnListarSolicitudes);
		btnListarSolicitudes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListadoSolicitudesConstanciasPanel constanciasPanel = new ListadoSolicitudesConstanciasPanel(panel);
				constanciasPanel.setBounds(0, 0, 798, 550);
				panel.mostrarPanelContent(constanciasPanel);
			}
		});
		btnListarSolicitudes.setFont(new Font("Cambria", Font.PLAIN, 13));

		btnGestionDeEventos = new JButton("Gestión de Eventos");
		btnGestionDeEventos.setBounds(8, 151, 163, 25);
		panelBotones.add(btnGestionDeEventos);
		btnGestionDeEventos.setFont(new Font("Cambria", Font.PLAIN, 13));

		btnItrs = new JButton("ITRs");
		btnItrs.setBounds(5, 195, 169, 25);
		panelBotones.add(btnItrs);
		btnItrs.setFont(new Font("Cambria", Font.PLAIN, 13));

		btnTiposDeConstancia = new JButton("Tipos de Constancias");
		btnTiposDeConstancia.setBounds(8, 239, 163, 25);
		panelBotones.add(btnTiposDeConstancia);
		btnTiposDeConstancia.setFont(new Font("Cambria", Font.PLAIN, 13));

		btnReporte = new JButton("Reportes");
		btnReporte.setBounds(8, 283, 163, 25);
		panelBotones.add(btnReporte);
		btnReporte.setFont(new Font("Cambria", Font.PLAIN, 13));

		btnMiPerfil = new JButton("Mi Perfil");
		btnMiPerfil.setBounds(8, 371, 163, 25);
		panelBotones.add(btnMiPerfil);
		btnMiPerfil.setFont(new Font("Cambria", Font.PLAIN, 13));

		buttonCerrarSesión = new JButton("Cerrar Sesión");
		buttonCerrarSesión.setBounds(8, 415, 163, 25);
		panelBotones.add(buttonCerrarSesión);
		buttonCerrarSesión.setFont(new Font("Cambria", Font.PLAIN, 13));

		btnReclamos = new JButton("Reclamos");
		btnReclamos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReclamoAnalistaPanel panelReclamoAnalista = new ReclamoAnalistaPanel();
				panelReclamoAnalista.setBounds(0, 0, 798, 550);
				panel.mostrarPanelContent(panelReclamoAnalista);
			}
		});
		btnReclamos.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnReclamos.setBounds(8, 327, 163, 25);
		panelBotones.add(btnReclamos);
		buttonCerrarSesión.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Fabrica.setoUsuarioLogueado(new Usuario());
				LoginPanel loginPanel = new LoginPanel(panel);
				loginPanel.setBounds(0, 0, 1028, 570);
				panel.mostrarPanelContent(loginPanel);
				panel.ocultarMenu();

			}
		});
		btnMiPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				MiPerfilAnalistaPanel miPerfilAnalistaPanel = new MiPerfilAnalistaPanel(panel);
				miPerfilAnalistaPanel.setBounds(0, 0, 798, 550);
				panel.mostrarPanelContent(miPerfilAnalistaPanel);

			}
		});
		btnReporte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReporteFuncionarioPanel gestionDeReporteFuncionario = new ReporteFuncionarioPanel(panel);
				gestionDeReporteFuncionario.setBounds(0, 0, 798, 550);
				panel.mostrarPanelContent(gestionDeReporteFuncionario);
			}
		});
		btnTiposDeConstancia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TiposConstanciasPanel listadoTiposConstanciasPanel = new TiposConstanciasPanel(panel);
				listadoTiposConstanciasPanel.setBounds(0, 0, 798, 550);
				panel.mostrarPanelContent(listadoTiposConstanciasPanel);
			}
		});
		btnItrs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListadoItrPanel listadoPanel = new ListadoItrPanel(panel);
				listadoPanel.setBounds(0, 0, 798, 550);
				panel.mostrarPanelContent(listadoPanel);
			}
		});
		btnGestionDeEventos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestionDeEventosAnalistaPanel gestionDeEventos = new GestionDeEventosAnalistaPanel(panel);
				gestionDeEventos.setBounds(0, 0, 798, 550);
				panel.mostrarPanelContent(gestionDeEventos);
			}
		});
		buttonUsuarioAConfirmar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ConfirmarUsuarioPanel usuarioConfirmarPanel = new ConfirmarUsuarioPanel(panel);
				usuarioConfirmarPanel.setSize(798, 550);
				usuarioConfirmarPanel.setLocation(0, 0);
				panel.mostrarPanelContent(usuarioConfirmarPanel);

			}
		});

	}

	public void cambiarLabel(String nombreNuevo) {
		labelNombreCompleto.setText(nombreNuevo);
	}
}
