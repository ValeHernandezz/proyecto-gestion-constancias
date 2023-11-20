package view.components.content.reclamo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.entities.Estado;
import com.entities.Evento;
import com.entities.Reclamo;

import context.Fabrica;
import context.helpers.Buscar;
import services.ServiceEstado;
import services.ServiceEvento;
import services.ServiceReclamo;
import view.img.ImagenesHelper;
import view.utils.TableModelReclamo;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;

public class ReclamoEstudiantePanel extends JPanel {
	private JTextField textFieldTitulo;
	private JTextField textFieldDetalle;
	private JComboBox comboBoxEventos;
	private ArrayList<Evento> listaDeEventos = ServiceEvento.listarEventos();
	private ArrayList<Estado> listaDeEstados = ServiceEstado.listarEstados();
	private ArrayList<Reclamo> listaDeReclamos = ServiceReclamo.listarReclamosFiltro(
			String.valueOf(Buscar.estudianteFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento")
					.get(0).getIdEstudiante()));
	private JTable tableReportes;
	private String idReclamo = null;

	public Estado getEstadoInicial() {
		Estado estadoInicial = new Estado();
		for (var aux : listaDeEstados) {
			if (aux.getDescripcion().equals("Ingresado")) {
				estadoInicial = aux;
				break;
			}
		}
		return estadoInicial;
	}

	public void limpiarCampos() {
		textFieldTitulo.setText(null);
		textFieldDetalle.setText(null);
		comboBoxEventos.setSelectedIndex(0);
		idReclamo = null;
	}

	public void cargarLista() {
		TableModelReclamo oModelTable = new TableModelReclamo(ServiceReclamo.listarReclamosFiltro(String
				.valueOf(Buscar.estudianteFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento")
						.get(0).getIdEstudiante())));
		tableReportes.setModel(oModelTable);
	}

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public ReclamoEstudiantePanel() {
		setLayout(null);

		JPanel panelContent = new JPanel();

		panelContent.setBounds(0, 0, 798, 550);
		add(panelContent);
		panelContent.setLayout(null);

		JLabel lblTitulo = new JLabel("Solicitar Reclamo");
		lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(219, 27, 359, 51);
		panelContent.add(lblTitulo);

		String[] eventoString = new String[listaDeEventos.size() + 1];
		eventoString[0] = "Elige un evento";

		for (int i = 0; i < listaDeEventos.size(); i++) {
			eventoString[i + 1] = listaDeEventos.get(i).getTitulo();
		}
		DefaultComboBoxModel<String> comboBoxModelEstado = new DefaultComboBoxModel<>(eventoString);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(252, 159, 531, 293);
		panelContent.add(scrollPane);

		tableReportes = new JTable();
		tableReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableReportes.getSelectedRow();
				String estado = (String) tableReportes.getValueAt(filaSeleccionada, 5);

				if (estado.equals("Ingresado")) {
					if (filaSeleccionada != -1) {
						Object[] options = { "Editar", "Eliminar" };
						int opcion = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?", "Acción",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

						if (opcion == 0) {
							idReclamo = (String) tableReportes.getValueAt(filaSeleccionada, 0);
							String titulo = (String) tableReportes.getValueAt(filaSeleccionada, 1);
							String detalle = (String) tableReportes.getValueAt(filaSeleccionada, 2);
							String evento = (String) tableReportes.getValueAt(filaSeleccionada, 4);

							textFieldTitulo.setText(titulo);
							textFieldDetalle.setText(detalle);
							comboBoxEventos.setSelectedItem(evento);
							return;
						}
						if (opcion == 1) {
							String id = (String) tableReportes.getValueAt(filaSeleccionada, 0);
							idReclamo = id;
							Reclamo oReclamo = new Reclamo();

							for (var aux : ServiceReclamo.listarReclamos()) {
								if (String.valueOf(aux.getIdReclamo()).equals(id)) {
									oReclamo = aux;
								}
							}

							boolean seBorro = ServiceReclamo.borrarReclamo(oReclamo);

							if (seBorro) {
								cargarLista();
								limpiarCampos();
								JOptionPane.showMessageDialog(null, "Se ha borrado correctamente");
								return;
							} else {
								JOptionPane.showMessageDialog(null, "No se ha podido borrar");
							}
						}
					}
				} else {

				}
			}
		});
		tableReportes.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Titulo", "Detalle", "Fecha", "Evento", "Estado" }) {
			Class[] columnTypes = new Class[] { String.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tableReportes);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setOpaque(false);
		panelFormulario.setBounds(15, 159, 222, 294);
		panelContent.add(panelFormulario);
		panelFormulario.setLayout(null);

		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(20, 43, 182, 26);
		panelFormulario.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Detalle:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(20, 94, 182, 14);
		panelFormulario.add(lblNewLabel_1);

		textFieldDetalle = new JTextField();
		textFieldDetalle.setBounds(20, 119, 182, 26);
		panelFormulario.add(textFieldDetalle);
		textFieldDetalle.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Evento:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(20, 170, 182, 14);
		panelFormulario.add(lblNewLabel_2);

		comboBoxEventos = new JComboBox(comboBoxModelEstado);
		comboBoxEventos.setBounds(20, 195, 182, 26);
		panelFormulario.add(comboBoxEventos);

		JLabel lblNewLabel = new JLabel("Título:");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel.setBounds(20, 18, 182, 14);
		panelFormulario.add(lblNewLabel);

		JButton btnEnviarFormulario = new JButton("Enviar ");
		btnEnviarFormulario.setBounds(14, 246, 89, 23);
		panelFormulario.add(btnEnviarFormulario);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(117, 246, 89, 23);
		panelFormulario.add(btnEditar);

		JLabel lblNewLabel_3 = new JLabel("-Seleccione un reporte de la tabla para editarlo o eliminarlo-");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(252, 134, 531, 14);
		panelContent.add(lblNewLabel_3);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String titulo = textFieldTitulo.getText();
				String detalle = textFieldDetalle.getText();
				long idEvento = Buscar.eventoPorTitulo(comboBoxEventos.getSelectedItem().toString()).getIdEvento();
				long idEstudiante = Buscar
						.estudianteFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento").get(0)
						.getIdEstudiante();
				Date fechaActual = new Date();
				long idEstado = getEstadoInicial().getIdEstado();

				Reclamo oReclamoEditado = new Reclamo(detalle, fechaActual, idEstado, idEstudiante, idEvento, titulo);
				oReclamoEditado.setIdReclamo(Long.parseLong(idReclamo));

				boolean editarReclamo = ServiceReclamo.actualizarReclamo(oReclamoEditado);

				if (editarReclamo) {
					cargarLista();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "Se ha editado correctamente");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "No se ha podido editar");
				}

			}
		});
		btnEnviarFormulario.addMouseListener(new MouseAdapter() {
			@Override
			// Esto se ejecuta cuando das click en el evento
			public void mouseClicked(MouseEvent e) {

				// String nombreDelContendioTF = textFiled.getText();
				String titulo = textFieldTitulo.getText();
				String detalle = textFieldDetalle.getText();
				long idEvento = Buscar.eventoPorTitulo(comboBoxEventos.getSelectedItem().toString()).getIdEvento();
				long idEstudiante = Buscar
						.estudianteFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento").get(0)
						.getIdEstudiante();
				Date fechaActual = new Date();
				long idEstado = getEstadoInicial().getIdEstado();

				Reclamo oNuevoReclamo = new Reclamo(detalle, fechaActual, idEstado, idEstudiante, idEvento, titulo);

				boolean reclamoGuardado = ServiceReclamo.crearReclamo(oNuevoReclamo);
				if (reclamoGuardado) {
					cargarLista();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "Se ha solicitado un reclamo");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "No se ha podido solicitar el reclamo");
				}

			}
		});
		if (listaDeReclamos != null) {
			TableModelReclamo oModelTable = new TableModelReclamo(listaDeReclamos);
			tableReportes.setModel(oModelTable);
		}

		panelContent.add(imagenFondoContent);
	}

}
