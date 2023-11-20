package view.components.content.listadoReportes;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Estudiante;
import com.entities.EventoEstudiante;

import context.helpers.Buscar;
import context.helpers.Generar;
import view.img.ImagenesHelper;
import view.utils.TableModelEventoEstudiante;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import java.awt.Color;

public class ReportesDeEstudiantesPanel extends JFrame {

	// JPanel
	private JPanel contentPane;
	private JPanel panelContent;

	// JButton
	private JButton btnDescargarEscolaridad;

	// JLabel
	private JLabel labelTitulo;
	private JLabel lblNEventos;
	private JLabel lblNEventosAsistidos;
	private JLabel lblNDeEventos;

	// JTable y JScrollPane
	private JTable tableReporte;
	private JScrollPane scrollPaneTabla;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 722, 412);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportesDeEstudiantesPanel frame = new ReportesDeEstudiantesPanel(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReportesDeEstudiantesPanel(Estudiante oEstudiante) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ReportesDeEstudiantesPanel.class.getResource("/view/img/ImagenLogoApp.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 736, 496);
		setTitle("Reporte de " + oEstudiante.getUsuario().getNombreCompleto());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelContent = new JPanel();
		panelContent.setBounds(0, 0, 722, 459);
		contentPane.add(panelContent);
		panelContent.setLayout(null);

		btnDescargarEscolaridad = new JButton("Descargar Escolaridad");
		btnDescargarEscolaridad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Generar oGenerar = new Generar();
				oGenerar.escolaridad(Buscar.reportesEstudiante(oEstudiante), oEstudiante);
			}
		});

		labelTitulo = new JLabel("Reporte de " + oEstudiante.getUsuario().getNombreCompleto());
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
		labelTitulo.setBounds(0, 13, 712, 58);
		panelContent.add(labelTitulo);

		separator1 = new JSeparator();
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(10, 84, 702, 2);
		panelContent.add(separator1);

		lblNEventos = new JLabel("N.º de Eventos: " + Buscar.reportesEstudiante(oEstudiante).size());
		lblNEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNEventos.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNEventos.setBounds(284, 99, 143, 25);
		panelContent.add(lblNEventos);

		lblNEventosAsistidos = new JLabel(
				"N.º de Eventos asistidos: " + Buscar.reportesEstudiantePorAsistencia(oEstudiante, "S") + "  |  "
						+ Buscar.reportesEstudiantePorAsistenciaPorcentaje(oEstudiante, "S"));
		lblNEventosAsistidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNEventosAsistidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNEventosAsistidos.setBounds(10, 137, 330, 25);
		panelContent.add(lblNEventosAsistidos);

		lblNDeEventos = new JLabel(
				"N.º de Eventos no asistidos: " + Buscar.reportesEstudiantePorAsistencia(oEstudiante, "N") + "  |  "
						+ Buscar.reportesEstudiantePorAsistenciaPorcentaje(oEstudiante, "N"));
		lblNDeEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNDeEventos.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNDeEventos.setBounds(372, 137, 330, 25);
		panelContent.add(lblNDeEventos);

		separator2 = new JSeparator();
		separator2.setForeground(new Color(68, 188, 244));
		separator2.setBounds(10, 175, 702, 2);
		panelContent.add(separator2);
		btnDescargarEscolaridad.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnDescargarEscolaridad.setBounds(264, 190, 194, 25);
		panelContent.add(btnDescargarEscolaridad);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(10, 228, 702, 221);
		panelContent.add(scrollPaneTabla);

		tableReporte = new JTable();
		scrollPaneTabla.setViewportView(tableReporte);
		imagenPanelContent.setBounds(0, 0, 722, 459);

		panelContent.add(imagenPanelContent);

		cargarLista(oEstudiante);
	}

	public void cargarLista(Estudiante estudiante) {
		var lista = Buscar.reportesEstudiante(estudiante);
		tableReporte
				.setModel(new TableModelEventoEstudiante(lista != null ? lista : new ArrayList<EventoEstudiante>()));
	}
}
