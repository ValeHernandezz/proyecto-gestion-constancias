package view.components.content.listadoReportes.estudiante;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import com.entities.Estudiante;

import context.helpers.Buscar;
import context.helpers.Generar;
import view.components.content.home.BienvenidoPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelEventoEstudiante;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import java.awt.Color;

public class ReporteEstudiantePanel extends JPanel {

	// JPanel
	private JPanel panelContent;

	// JButton
	private JButton buttonRegresar;
	private JButton btnDescargarEscolaridad;

	// JLabel
	private JLabel lblReportes;
	private JLabel lblNEventos;
	private JLabel lblNEventosAsistidos;
	private JLabel lblNDeEventosNoAsistidos;

	// JTable y JScrollPane
	private JTable tableReportes;
	private JScrollPane scrollPaneTabla;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public ReporteEstudiantePanel(MostrarPanel panel, Estudiante oEstudiante) {
		setLayout(null);

		panelContent = new JPanel();
		panelContent.setBounds(0, 0, 798, 550);
		add(panelContent);
		panelContent.setLayout(null);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new BienvenidoPanel(), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 13, 45, 25);
		panelContent.add(buttonRegresar);

		lblReportes = new JLabel("Reportes");
		lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportes.setFont(new Font("Rockwell", Font.BOLD, 40));
		lblReportes.setBounds(156, 18, 486, 58);
		panelContent.add(lblReportes);

		separator1 = new JSeparator();
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(30, 94, 738, 2);
		panelContent.add(separator1);

		lblNEventos = new JLabel("N.º de Eventos: " + Buscar.reportesEstudiante(oEstudiante).size());
		lblNEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNEventos.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNEventos.setBounds(327, 114, 143, 25);
		panelContent.add(lblNEventos);

		lblNEventosAsistidos = new JLabel(
				"N.º de Eventos asistidos: " + Buscar.reportesEstudiantePorAsistencia(oEstudiante, "S") + "  |  "
						+ Buscar.reportesEstudiantePorAsistenciaPorcentaje(oEstudiante, "S"));
		lblNEventosAsistidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNEventosAsistidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNEventosAsistidos.setBounds(46, 157, 330, 25);
		panelContent.add(lblNEventosAsistidos);

		lblNDeEventosNoAsistidos = new JLabel(
				"N.º de Eventos no asistidos: " + Buscar.reportesEstudiantePorAsistencia(oEstudiante, "N") + "  |  "
						+ Buscar.reportesEstudiantePorAsistenciaPorcentaje(oEstudiante, "N"));
		lblNDeEventosNoAsistidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNDeEventosNoAsistidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNDeEventosNoAsistidos.setBounds(422, 157, 330, 25);
		panelContent.add(lblNDeEventosNoAsistidos);

		btnDescargarEscolaridad = new JButton("Descargar Escolaridad");
		btnDescargarEscolaridad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Generar oGenerar = new Generar();
				oGenerar.escolaridad(Buscar.misReportes(), oEstudiante);
			}
		});

		separator2 = new JSeparator();
		separator2.setForeground(new Color(68, 188, 244));
		separator2.setBounds(30, 200, 738, 2);
		panelContent.add(separator2);
		btnDescargarEscolaridad.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnDescargarEscolaridad.setBounds(302, 220, 194, 25);
		panelContent.add(btnDescargarEscolaridad);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(30, 263, 738, 262);
		panelContent.add(scrollPaneTabla);

		tableReportes = new JTable();
		scrollPaneTabla.setViewportView(tableReportes);

		panelContent.add(imagenPanelContent);

		cargarMisReportes();
	}

	public void cargarMisReportes() {
		var lista = Buscar.misReportes();
		tableReportes.setModel(new TableModelEventoEstudiante(lista));
	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
