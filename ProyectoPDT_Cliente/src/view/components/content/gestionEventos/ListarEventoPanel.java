package view.components.content.gestionEventos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.entities.Evento;

import services.ServiceEvento;
import view.components.content.section.GestionDeEventosAnalistaPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelEvento;

public class ListarEventoPanel extends JPanel {

	// JPanel
	private JPanel panelContent;

	// JButton
	private JButton buttonRegresar;

	// JLabel
	private JLabel labelListarEventos;

	// JTable y JScrollPane
	private JScrollPane scrollPaneTabla;
	private JTable tableEventos;

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public ListarEventoPanel(MostrarPanel panel) {
		setLayout(null);

		panelContent = new JPanel();
		panelContent.setBackground(new Color(192, 192, 192));
		panelContent.setBounds(0, 0, 798, 550);
		add(panelContent);
		panelContent.setLayout(null);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new GestionDeEventosAnalistaPanel(panel), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelContent.add(buttonRegresar);

		labelListarEventos = new JLabel("Listado de Eventos");
		labelListarEventos.setHorizontalAlignment(SwingConstants.CENTER);
		labelListarEventos.setForeground(new Color(0, 0, 0));
		labelListarEventos.setFont(new Font("Rockwell", Font.BOLD, 40));
		labelListarEventos.setBounds(156, 37, 486, 58);
		panelContent.add(labelListarEventos);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(54, 132, 690, 381);
		panelContent.add(scrollPaneTabla);

		tableEventos = new JTable();
		tableEventos.setModel(new TableModelEvento(
				ServiceEvento.listarEventos() == null ? new ArrayList<Evento>() : ServiceEvento.listarEventos()));
		scrollPaneTabla.setViewportView(tableEventos);

		panelContent.add(imagenFondoContent);

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
