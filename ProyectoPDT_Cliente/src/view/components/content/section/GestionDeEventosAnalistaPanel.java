package view.components.content.section;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.components.content.gestionEventos.CrearEventoPanel;
import view.components.content.gestionEventos.ListarEventoPanel;
import view.components.content.home.BienvenidoPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;

public class GestionDeEventosAnalistaPanel extends JPanel {

	// JPanel
	private JPanel panelPrincipal;
	private JPanel panelDatos;

	// JButton
	private JButton buttonRegresar;
	private JButton btnCrear;
	private JButton btnListar;

	// JLabel
	private JLabel labelGestiónDeEventos;
	private JLabel lblOpcionesDeConstancias;

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public GestionDeEventosAnalistaPanel(MostrarPanel panel) {
		setLayout(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setBounds(0, 0, 798, 550);
		add(panelPrincipal);
		panelPrincipal.setLayout(null);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				mostrarPanelContent(prepararPanel(new BienvenidoPanel(), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelPrincipal.add(buttonRegresar);

		labelGestiónDeEventos = new JLabel("Gestión de Eventos");
		labelGestiónDeEventos.setHorizontalAlignment(SwingConstants.CENTER);
		labelGestiónDeEventos.setForeground(new Color(0, 0, 0));
		labelGestiónDeEventos.setFont(new Font("Rockwell", Font.BOLD, 40));
		labelGestiónDeEventos.setBounds(156, 28, 486, 58);
		panelPrincipal.add(labelGestiónDeEventos);

		panelDatos = new JPanel();
		panelDatos.setLayout(null);
		panelDatos.setOpaque(false);
		panelDatos.setBackground(new Color(188, 190, 196));
		panelDatos.setBounds(162, 195, 474, 160);
		panelPrincipal.add(panelDatos);

		lblOpcionesDeConstancias = new JLabel("Eliga una opción:");
		lblOpcionesDeConstancias.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpcionesDeConstancias.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblOpcionesDeConstancias.setBounds(24, 29, 425, 43);
		panelDatos.add(lblOpcionesDeConstancias);

		btnCrear = new JButton("Crear");
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarPanelContent(prepararPanel(new CrearEventoPanel(panel), 798, 550));
			}
		});
		btnCrear.setFont(new Font("Cambria", Font.PLAIN, 20));
		btnCrear.setBounds(81, 101, 115, 30);
		panelDatos.add(btnCrear);

		btnListar = new JButton("Listar");
		btnListar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarPanelContent(prepararPanel(new ListarEventoPanel(panel), 798, 550));
			}
		});

		btnListar.setFont(new Font("Cambria", Font.PLAIN, 20));
		btnListar.setBounds(277, 101, 115, 30);
		panelDatos.add(btnListar);

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
