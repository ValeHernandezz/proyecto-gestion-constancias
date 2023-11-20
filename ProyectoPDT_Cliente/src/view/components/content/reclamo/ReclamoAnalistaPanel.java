package view.components.content.reclamo;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.Estado;
import com.entities.EstadoReclamo;
import com.entities.EstadoReclamoPK;
import com.entities.Reclamo;

import context.Fabrica;
import context.helpers.Buscar;
import services.ServiceEstado;
import services.ServiceReclamo;
import view.img.ImagenesHelper;
import view.utils.TableModelReclamo;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ReclamoAnalistaPanel extends JPanel {
	private JTable tableReportes;
	private JTextField textFieldDetalle;
	private JComboBox comboBoxEstado;
	private ArrayList<Estado> listaDeEstados = ServiceEstado.listarEstados();
	private ArrayList<Reclamo> listaDeReclamos = ServiceReclamo.listarReclamos();
	private String idReclamo = null;
	private JPanel panelFormulario;
	private JLabel lblListadoDeReclamos;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public void cargarLista() {
		TableModelReclamo oModelTable = new TableModelReclamo(ServiceReclamo.listarReclamos());
		tableReportes.setModel(oModelTable);
	}

	public Reclamo buscarReclamoPorId(long id) {
		Reclamo oReclamo = new Reclamo();
		for (var aux : ServiceReclamo.listarReclamos()) {
			if (aux.getIdReclamo() == id) {
				oReclamo = aux;
				break;
			}
		}
		return oReclamo;
	}

	public long buscaEstadoPorDescripcion(String descripcion) {
		long id = 1;
		for (var aux : ServiceEstado.listarEstados()) {
			if (aux.getDescripcion().equals(descripcion)) {
				id = aux.getIdEstado();
				break;
			}
		}
		return id;
	}

	public void limpiarCampo() {
		panelFormulario.setVisible(false);
	}

	public ReclamoAnalistaPanel() {
		setLayout(null);
		String[] estadoString = new String[listaDeEstados.size() + 1];
		estadoString[0] = "Elige un estado";

		for (int i = 0; i < listaDeEstados.size(); i++) {
			estadoString[i + 1] = listaDeEstados.get(i).getDescripcion();
		}
		DefaultComboBoxModel<String> comboBoxModelEstado = new DefaultComboBoxModel<>(estadoString);
		JPanel panelContent = new JPanel();

		panelContent.setBounds(0, 0, 798, 550);
		add(panelContent);
		panelContent.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 172, 546, 304);
		panelContent.add(scrollPane);

		tableReportes = new JTable();
		tableReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableReportes.getSelectedRow();

				if (filaSeleccionada != -1) {
					idReclamo = (String) tableReportes.getValueAt(filaSeleccionada, 0);

					String estado = (String) tableReportes.getValueAt(filaSeleccionada, 5);
					panelFormulario.removeAll();

					JLabel lblNewLabel = new JLabel("ID: " + idReclamo);
					lblNewLabel.setBounds(10, 39, 46, 14);
					panelFormulario.add(lblNewLabel);

					textFieldDetalle = new JTextField();
					textFieldDetalle.setBounds(10, 88, 201, 26);
					panelFormulario.add(textFieldDetalle);
					textFieldDetalle.setColumns(10);

					comboBoxEstado = new JComboBox<>(comboBoxModelEstado);
					comboBoxEstado.setBounds(10, 146, 201, 26);
					comboBoxEstado.setSelectedItem(estado);
					panelFormulario.add(comboBoxEstado);

					JButton btnNewButton = new JButton("Cambiar estado");
					btnNewButton.setBounds(40, 200, 141, 23);
					btnNewButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							long idReclamolong = Long.parseLong(idReclamo);
							long idAnalista = Buscar
									.analistaFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(),
											"Documento")
									.get(0).getIdAnalista();
							Date fechaActual = new Date();
							EstadoReclamoPK oEstadoReclamoPk = new EstadoReclamoPK(idReclamolong, idAnalista,
									fechaActual);

							String detalle = textFieldDetalle.getText();

							Reclamo oReclamo = buscarReclamoPorId(idReclamolong);
							oReclamo.setIdEstado(
									buscaEstadoPorDescripcion(comboBoxEstado.getSelectedItem().toString()));

							boolean oReclamoCambiado = ServiceReclamo.actualizarReclamo(oReclamo);

							EstadoReclamo oEstadoReclamo = new EstadoReclamo(oEstadoReclamoPk, detalle);

							boolean estadoCambiado = ServiceReclamo.cambiarEstadoReclamo(oEstadoReclamo);
							if (estadoCambiado && oReclamoCambiado) {
								limpiarCampo();
								cargarLista();
								JOptionPane.showMessageDialog(null, "Estado cambiado");
								return;
							} else {
								limpiarCampo();
								cargarLista();
								JOptionPane.showMessageDialog(null, "No se pudo cambiar el estado");
							}
						}
					});
					panelFormulario.add(btnNewButton);

					// Actualizar el diseño y repintar el panel
					panelFormulario.revalidate();
					panelFormulario.repaint();

					panelFormulario.setVisible(true);
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

		panelFormulario = new JPanel();
		panelFormulario.setBackground(new Color(255, 255, 255));
		panelFormulario.setVisible(false);
		panelFormulario.setBounds(10, 172, 221, 304);
		panelFormulario.setOpaque(false);
		panelContent.add(panelFormulario);
		panelFormulario.setLayout(null);

		JButton btnNewButton = new JButton("Cambiar estado");
		btnNewButton.setBounds(40, 235, 141, 23);
		panelFormulario.add(btnNewButton);

		textFieldDetalle = new JTextField();
		textFieldDetalle.setBackground(new Color(255, 255, 255));
		textFieldDetalle.setBounds(10, 56, 201, 26);
		panelFormulario.add(textFieldDetalle);
		textFieldDetalle.setColumns(10);

		comboBoxEstado = new JComboBox(comboBoxModelEstado);
		comboBoxEstado.setBackground(new Color(192, 192, 192));
		comboBoxEstado.setBounds(10, 158, 201, 26);
		panelFormulario.add(comboBoxEstado);

		lblNewLabel_2 = new JLabel("Detalle");
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(10, 31, 46, 14);
		panelFormulario.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Estado");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_3.setBounds(10, 133, 46, 14);
		panelFormulario.add(lblNewLabel_3);

		lblListadoDeReclamos = new JLabel("LISTADO DE RECLAMOS");
		lblListadoDeReclamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeReclamos.setFont(new Font("Rockwell", Font.BOLD, 35));
		lblListadoDeReclamos.setBounds(211, 32, 433, 74);
		panelContent.add(lblListadoDeReclamos);

		lblNewLabel_1 = new JLabel("-Seleccione un reporte de la tabla para cambiar su estado-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(241, 147, 546, 14);
		panelContent.add(lblNewLabel_1);

		if (listaDeReclamos != null) {
			TableModelReclamo oModelTable = new TableModelReclamo(listaDeReclamos);
			tableReportes.setModel(oModelTable);
		}

		panelContent.add(imagenFondoContent);
	}

}
