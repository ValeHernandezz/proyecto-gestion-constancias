package view.components.content.listadoReportes.funcionario;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import com.entities.Estudiante;

import context.helpers.Buscar;
import services.ServiceEstudiante;
import view.components.content.home.BienvenidoPanel;
import view.components.content.listadoReportes.ReportesDeEstudiantesPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelEstudiante;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class ReporteFuncionarioPanel extends JPanel {

	// JPanel
	private JPanel panelContent;
	private JPanel panelBusqueda;

	// JButton
	private JButton buttonRegresar;
	private JButton btnBuscar;

	// JLabel
	private JLabel lblReportesDeEstudiantes;
	private JLabel lblInformacion;
	private JLabel lblBuscar;

	// JTextField
	private JTextField textField;

	// JTable y JScrollPane
	private JTable tableReportes;
	private JScrollPane scrollPaneTabla;

	// JComboBox
	private JComboBox comboBox;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public ReporteFuncionarioPanel(MostrarPanel panel) {
		setLayout(null);

		panelContent = new JPanel();
		panelContent.setBounds(0, 0, 798, 550);
		add(panelContent);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.setBounds(10, 10, 45, 25);
		buttonRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new BienvenidoPanel(), 798, 550));

			}
		});
		panelContent.setLayout(null);
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		panelContent.add(buttonRegresar);

		lblReportesDeEstudiantes = new JLabel("Reportes de Estudiantes");
		lblReportesDeEstudiantes.setBounds(113, 17, 572, 58);
		lblReportesDeEstudiantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportesDeEstudiantes.setFont(new Font("Rockwell", Font.BOLD, 40));
		panelContent.add(lblReportesDeEstudiantes);

		separator1 = new JSeparator();
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(30, 92, 738, 2);
		panelContent.add(separator1);

		panelBusqueda = new JPanel();
		panelBusqueda.setBounds(74, 111, 674, 50);
		panelBusqueda.setOpaque(false);
		panelContent.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(26, 12, 85, 25);
		panelBusqueda.add(lblBuscar);
		lblBuscar.setFont(new Font("Cambria", Font.BOLD, 20));

		textField = new JTextField();
		textField.setBounds(137, 12, 215, 25);
		panelBusqueda.add(textField);
		textField.setFont(new Font("Cambria", Font.PLAIN, 15));
		textField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Elige un filtro", "Documento", "Nombre de Usuario" }));
		comboBox.setBounds(378, 12, 150, 25);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();

					if (selectedItem.equals("Elige un filtro")) {
						cargarReportes();
					}
				}
			}
		});
		panelBusqueda.add(comboBox);
		comboBox.setFont(new Font("Cambria", Font.PLAIN, 15));

		btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indexComboBox = comboBox.getSelectedIndex();

				if (indexComboBox == 0) {
					JOptionPane.showMessageDialog(null, "Debe elegir un filtro para realizar la busqueda.",
							"Advertencia", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String filtro = textField.getText();
				String campo = comboBox.getSelectedItem().toString();

				if (filtro.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Debe completar los campos para realizar la busqueda.",
							"Advertencia", JOptionPane.ERROR_MESSAGE);
					return;
				}

				ArrayList<Estudiante> listaDeEstudiantes = Buscar.estudianteFiltro(filtro, campo);

				if (listaDeEstudiantes == null || listaDeEstudiantes.size() == 0) {
					JOptionPane.showMessageDialog(null, "No existe un estudiante con ese dato.", "Advertencia",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				tableReportes.setModel(new TableModelEstudiante(listaDeEstudiantes));
			}
		});
		btnBuscar.setBounds(554, 12, 91, 25);
		panelBusqueda.add(btnBuscar);
		btnBuscar.setFont(new Font("Cambria", Font.PLAIN, 15));

		separator2 = new JSeparator();
		separator2.setForeground(new Color(68, 188, 244));
		separator2.setBounds(30, 178, 738, 2);
		panelContent.add(separator2);

		lblInformacion = new JLabel("Seleccione un estudiante para ver su reporte.");
		lblInformacion.setBounds(141, 197, 515, 25);
		lblInformacion.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		panelContent.add(lblInformacion);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(30, 239, 738, 288);
		panelContent.add(scrollPaneTabla);

		tableReportes = new JTable();
		tableReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableReportes.getSelectedRow();

				if (filaSeleccionada != -1) {
					String documento = (String) tableReportes.getValueAt(filaSeleccionada, 0);

					Estudiante oEstudiante = Buscar.estudianteFiltro(documento, "Documento").get(0);
					ReportesDeEstudiantesPanel reporteFrame = new ReportesDeEstudiantesPanel(oEstudiante);
					reporteFrame.setLocationRelativeTo(null);
					reporteFrame.setVisible(true);
				}
			}
		});
		scrollPaneTabla.setViewportView(tableReportes);

		panelContent.add(imagenPanelContent);

		cargarReportes();
	}

	public void cargarReportes() {
		var lista = ServiceEstudiante.listarEstudiantes();
		tableReportes.setModel(new TableModelEstudiante(lista != null ? lista : new ArrayList<Estudiante>()));
	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
