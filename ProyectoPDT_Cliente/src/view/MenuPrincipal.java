package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import context.utils.CargarDatos;
import view.components.content.auth.LoginPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;

public class MenuPrincipal implements MostrarPanel {

	// JFrame
	private JFrame frmSistemaDeVentas;

	// JPanel
	private JPanel panelMenu = new JPanel();
	private JPanel panelContent = new JPanel();

	// Imágenes
	private ImagenesHelper imagenFondoLogin = new ImagenesHelper("/view/img/ImagenFondoLogin.png", 1028, 1028);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					CargarDatos.empezar();
					window.frmSistemaDeVentas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frmSistemaDeVentas = new JFrame();

		// Cargar la imagen del icono
		ImageIcon icono = new ImageIcon(MenuPrincipal.class.getResource("/view/img/ImagenLogoApp.png"));
		// Obtener la imagen del icono y ajustar su tamaño
		Image imagenIcono = icono.getImage();
		ImageIcon iconoEscalado = new ImageIcon(imagenIcono.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
		// Establecer el icono escalado en la ventana
		frmSistemaDeVentas.setIconImage(iconoEscalado.getImage());

		frmSistemaDeVentas.setTitle("Gestión de Constancias UTEC");
		frmSistemaDeVentas.setResizable(false);
		frmSistemaDeVentas.getContentPane().setBackground(new Color(172, 249, 214));
		frmSistemaDeVentas.setBounds(100, 100, 1042, 607);
		frmSistemaDeVentas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeVentas.getContentPane().setLayout(null);
		frmSistemaDeVentas.setLocationRelativeTo(null);

		mostrarPanelContent(prepararPanel(new LoginPanel(this), 1028, 570));
		panelContent.setBackground(new Color(192, 192, 192));
		panelContent.setBounds(0, 0, 1028, 570);
		frmSistemaDeVentas.getContentPane().add(panelContent);
		panelContent.setLayout(null);
		panelMenu.setBackground(new Color(117, 236, 164));
		panelMenu.setBounds(10, 10, 200, 550);

		frmSistemaDeVentas.getContentPane().add(panelMenu);
		frmSistemaDeVentas.getContentPane().add(panelMenu);
		panelMenu.setLayout(null);

		imagenFondoLogin.setBounds(0, 0, 1028, 1028);
		frmSistemaDeVentas.getContentPane().add(imagenFondoLogin);

	}

	public void mostrarPanelMenu(JPanel panel) {
		panelMenu.removeAll();
		panelMenu.add(panel, BorderLayout.CENTER);
		panelMenu.revalidate();
		panelMenu.repaint();
	}

	public void mostrarPanelContent(JPanel panel) {
		panelContent.removeAll();
		panelContent.add(panel, BorderLayout.CENTER);
		panelContent.revalidate();
		panelContent.repaint();

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}

	public void mostrarMenu() {
		panelMenu.setVisible(true);
		panelContent.setBounds(220, 10, 798, 550);
	}

	public void ocultarMenu() {
		panelContent.setBounds(0, 0, 1028, 570);
		panelContent.setBackground(new Color(145, 242, 189));
		panelMenu.setVisible(false);
	}

	public void inciarLogin() {
		ocultarMenu();
	}

	public MenuPrincipal() {
		initialize();
		inciarLogin();

	}

}