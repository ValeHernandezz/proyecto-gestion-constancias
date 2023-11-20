package view.components.content.listadoSolicitudes.estudiante;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.entities.Constancia;
import com.entities.Estado;
import com.entities.EstadoConstancia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Tipo;

import context.Fabrica;
import context.helpers.Buscar;
import services.ServiceConstancia;
import services.ServiceEstado;
import services.ServiceEvento;
import services.ServiceTipo;
import view.components.content.listadoSolicitudes.FichaConstanciaEstudiante;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.components.content.home.BienvenidoPanel;
import view.components.content.listadoSolicitudes.FichaConstanciaAnalista;
import view.utils.TableModelConstancia;
import javax.swing.JSeparator;

public class SolicitudDeConstanciaPanel extends JPanel {

	// JPanel
	private JPanel panelForm;
	private JPanel panelPrincipal;

	// JTextField
	private JTextField textFieldDetalle;

	// JButton
	private JButton btnListarTodo;
	private JButton btnEditarConstancia;
	private JButton btnSolicitarConstancia;
	private JButton buttonRegresar;

	// JLabel
	private JLabel labelSolicitudDeConstancia;
	private JLabel lblDetalle;

	// JComboBox
	private JComboBox comboBoxTipoConstanciaBuscar;
	private JComboBox comboBoxTipoConstancia;
	private JComboBox comboBoxEstados;
	private JComboBox comboBoxEvento;

	// JTable y JScrollPane
	private JTable tableConstancias;
	private JScrollPane scrollPaneTabla;

	// JSeparator
	private JSeparator separator2;
	private JSeparator separator1;

	// Listas
	private ArrayList<Evento> listaDeEventos = ServiceEvento.listarEventos();
	private ArrayList<Estado> listaDeEstados = ServiceEstado.listarEstados();
	private ArrayList<Tipo> listaDeTipos = Buscar.tiposConstanciasActivos("S");
	private String[] tiposConstanciaStrings = new String[listaDeTipos.size() + 1];

	// Imágenes
	private ImagenesHelper imagenContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	// Extras
	private Long idConstancia = null;

	public SolicitudDeConstanciaPanel(MostrarPanel panel) {
		setLayout(null);

		String[] eventoStrings = new String[listaDeEventos.size() + 1];
		eventoStrings[0] = "Elige un evento";

		for (int i = 0; i < listaDeEventos.size(); i++) {
			eventoStrings[i + 1] = listaDeEventos.get(i).getTitulo();
		}
		DefaultComboBoxModel<String> comboBoxModelEvento = new DefaultComboBoxModel<>(eventoStrings);

		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 798, 550);
		add(panelPrincipal);
		panelPrincipal.setLayout(null);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new BienvenidoPanel(), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelPrincipal.add(buttonRegresar);

		labelSolicitudDeConstancia = new JLabel("Solicitud de Constancia");
		labelSolicitudDeConstancia.setHorizontalAlignment(SwingConstants.CENTER);
		labelSolicitudDeConstancia.setFont(new Font("Rockwell", Font.BOLD, 40));
		labelSolicitudDeConstancia.setBounds(144, 23, 510, 36);
		panelPrincipal.add(labelSolicitudDeConstancia);

		panelForm = new JPanel();
		panelForm.setBounds(10, 93, 778, 148);
		panelForm.setOpaque(false);
		panelPrincipal.add(panelForm);
		panelForm.setLayout(null);

		separator1 = new JSeparator();
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(0, 0, 778, 2);
		panelForm.add(separator1);

		lblDetalle = new JLabel("Detalle:");
		lblDetalle.setFont(new Font("Cambria", Font.BOLD, 15));
		lblDetalle.setBounds(50, 20, 175, 20);
		panelForm.add(lblDetalle);

		textFieldDetalle = new JTextField();
		textFieldDetalle.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldDetalle.setBounds(50, 49, 175, 19);
		panelForm.add(textFieldDetalle);
		textFieldDetalle.setColumns(10);

		comboBoxEvento = new JComboBox(comboBoxModelEvento);
		comboBoxEvento.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxEvento.setBounds(550, 48, 175, 21);
		panelForm.add(comboBoxEvento);

		tiposConstanciaStrings[0] = "Elige un Tipo de Constancia";
		for (int i = 0; i < listaDeTipos.size(); i++) {
			tiposConstanciaStrings[i + 1] = listaDeTipos.get(i).getNombre();
		}

		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(tiposConstanciaStrings);
		DefaultComboBoxModel<String> comboBoxModelBuscar = new DefaultComboBoxModel<>(tiposConstanciaStrings);

		comboBoxTipoConstancia = new JComboBox(comboBoxModel);
		comboBoxTipoConstancia.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxTipoConstancia.setBounds(275, 48, 225, 21);
		panelForm.add(comboBoxTipoConstancia);

		btnSolicitarConstancia = new JButton("Solicitar constancia");
		btnSolicitarConstancia.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnSolicitarConstancia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (textFieldDetalle.getText().trim().equals("") || comboBoxEvento.getSelectedIndex() == 0
						|| comboBoxTipoConstancia.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Debe completar los tres campos disponibles\npara solicitar una constancia", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Evento oEvento = ServiceEvento.listarEventosFiltro(comboBoxEvento.getSelectedItem().toString()).get(0);

				Estado oEstado = ServiceEstado.listarEstados().get(0);

				Estudiante oEstudiante = Buscar
						.estudianteFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento").get(0);

				LocalDateTime fechaHoraActual = LocalDateTime.now();
				Date fechaHoraFinal = Date.from(fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant());

				String detalle = textFieldDetalle.getText();

				Tipo tipoConstancia = ServiceTipo
						.listarTiposConstanciasFiltro(comboBoxTipoConstancia.getSelectedItem().toString()).get(0);

				Constancia oConstancia1 = new Constancia(detalle, fechaHoraFinal, oEstado, oEstudiante, oEvento,
						tipoConstancia);

				if (Buscar.existeUnaSolicitud(oEvento, tipoConstancia)) {
					JOptionPane.showMessageDialog(null,
							"No puede solicitar más de una constancia para el mismo evento y con el mismo tipo.",
							"Advertencia", JOptionPane.ERROR_MESSAGE);
					return;
				}

				var constanciaGuardada = ServiceConstancia.crearConstancia(oConstancia1);

				if (constanciaGuardada) {
					limpiarCampos();
					listarSolicitudes();
					JOptionPane.showMessageDialog(null, "La constancia se ha solicitado exitosamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				} else {
					JOptionPane.showMessageDialog(null,
							"No se pudo solicitar la constancia.\nPor favor, verifica los datos ingresados e inténtalo nuevamente.",
							"Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnSolicitarConstancia.setBounds(149, 96, 175, 25);
		panelForm.add(btnSolicitarConstancia);

		btnEditarConstancia = new JButton("Editar constancia");
		btnEditarConstancia.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (textFieldDetalle.getText().trim().equals("") || comboBoxEvento.getSelectedIndex() == 0
						|| comboBoxTipoConstancia.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Debe completar los tres campos disponibles\npara solicitar una constancia", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Evento oEventoCreado = ServiceEvento.listarEventosFiltro(comboBoxEvento.getSelectedItem().toString())
						.get(0);

				Estado oEstadoCreado = ServiceEstado.listarEstados().get(0);

				Estudiante oEstudiante = Buscar
						.estudianteFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento").get(0);

				LocalDateTime fechaHoraActual = LocalDateTime.now();
				Date fechaHoraFinal = Date.from(fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant());

				String detalle = textFieldDetalle.getText();

				Tipo tipoConstancia = ServiceTipo
						.listarTiposConstanciasFiltro(comboBoxTipoConstancia.getSelectedItem().toString()).get(0);

				int opcion = JOptionPane.showConfirmDialog(null,
						"Esta seguro que desea editar esta solicitud de constancia", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (opcion != 0) {
					return;
				}
				Constancia oConstanciaModificada = new Constancia(detalle, fechaHoraFinal, oEstadoCreado, oEstudiante,
						oEventoCreado, tipoConstancia);

				oConstanciaModificada.setIdConstancia(idConstancia);

				var constanciaEditada = ServiceConstancia.actualizarConstancia(oConstanciaModificada);

				if (constanciaEditada) {
					limpiarCampos();
					listarSolicitudes();
					btnEditarConstancia.setEnabled(false);
					JOptionPane.showMessageDialog(null, "La constancia se ha editado exitosamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				} else {
					JOptionPane.showMessageDialog(null,
							"No se pudo editar la constancia.\nPor favor, verifica los datos ingresados e inténtalo nuevamente.",
							"Error", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		btnEditarConstancia.setEnabled(false);
		btnEditarConstancia.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnEditarConstancia.setBounds(473, 96, 155, 25);
		panelForm.add(btnEditarConstancia);

		separator2 = new JSeparator();
		separator2.setForeground(new Color(68, 188, 244));
		separator2.setBounds(0, 146, 778, 2);
		panelForm.add(separator2);
		String[] estadosString = new String[listaDeEstados.size() + 1];
		estadosString[0] = "Elige un estado";

		for (int i = 0; i < listaDeEstados.size(); i++) {
			estadosString[i + 1] = listaDeEstados.get(i).getDescripcion();
		}
		DefaultComboBoxModel<String> comboBoxModelEstado = new DefaultComboBoxModel<>(estadosString);

		comboBoxEstados = new JComboBox(comboBoxModelEstado);
		comboBoxEstados.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxEstados.setBounds(68, 265, 175, 21);
		comboBoxEstados.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();

					if (selectedItem != "Elige un Estado") {
						TableModelConstancia tableModelConstancia = new TableModelConstancia(
								Buscar.misConstanciaPorEstado(selectedItem.toString()));
						tableConstancias.setModel(tableModelConstancia);
					} else {
						TableModelConstancia tableModelConstancia = new TableModelConstancia(Buscar.misConstancias());
						tableConstancias.setModel(tableModelConstancia);
					}
				}

			}
		});
		panelPrincipal.add(comboBoxEstados);

		comboBoxTipoConstanciaBuscar = new JComboBox(comboBoxModelBuscar);
		comboBoxTipoConstanciaBuscar.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxTipoConstanciaBuscar.setBounds(311, 265, 225, 21);
		comboBoxTipoConstanciaBuscar.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();

					if (selectedItem != "Elige un Tipo de Constancia") {
						TableModelConstancia tableModelConstancia = new TableModelConstancia(
								Buscar.misConstanciaPorTipo(selectedItem.toString()));
						tableConstancias.setModel(tableModelConstancia);
					} else {
						TableModelConstancia tableModelConstancia = new TableModelConstancia(Buscar.misConstancias());
						tableConstancias.setModel(tableModelConstancia);
					}
				}

			}
		});
		panelPrincipal.add(comboBoxTipoConstanciaBuscar);

		btnListarTodo = new JButton("Listar todo");
		btnListarTodo.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnListarTodo.setBounds(604, 263, 123, 25);
		btnListarTodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listarSolicitudes();
				limpiarCampos();
			}
		});
		panelPrincipal.add(btnListarTodo);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(10, 309, 778, 231);
		panelPrincipal.add(scrollPaneTabla);

		tableConstancias = new JTable();
		tableConstancias.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableConstancias.getSelectedRow();

				if (filaSeleccionada != -1) {

					String estado = (String) tableConstancias.getValueAt(filaSeleccionada, 6);
					String detalle = (String) tableConstancias.getValueAt(filaSeleccionada, 2);
					String evento = (String) tableConstancias.getValueAt(filaSeleccionada, 1);
					String tipo = (String) tableConstancias.getValueAt(filaSeleccionada, 0);
					idConstancia = Buscar.idConstanciaEstudiante(
							Fabrica.getoUsuarioLogueado().getDocumento().toString(), evento, tipo).getIdConstancia();

					if (estado.equals("Ingresado")) {
						Object[] options = { "Editar", "Eliminar" };
						int option = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?", "Acción",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

						if (option == 0) {
							textFieldDetalle.setText(detalle);
							comboBoxModelEvento.setSelectedItem(evento);
							comboBoxTipoConstancia.setSelectedItem(tipo);
							btnEditarConstancia.setEnabled(true);
						} else if (option == 1) {
							int optionEliminar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la solicitud?",
									"Confirmación", JOptionPane.YES_NO_OPTION);

							if (optionEliminar == 0) {
								boolean eliminado = ServiceConstancia.eliminarConstancia(idConstancia);
								if (eliminado) {
									limpiarCampos();
									listarSolicitudes();
									JOptionPane.showMessageDialog(null, "Eliminado");
									return;
								} else {
									limpiarCampos();
									listarSolicitudes();
									JOptionPane.showMessageDialog(null, "No eliminado");
									return;
								}
							} else {
								return;
							}
						}
					}

					if (!estado.equals("Ingresado")) {
						int opcion = JOptionPane.showConfirmDialog(null, "Ver ficha", "Confirmación",
								JOptionPane.YES_NO_OPTION);
						if (opcion != 0) {
							return;
						}
						String documentoEstudiante = Fabrica.getoUsuarioLogueado().getDocumento().toString();
						var constancia = Buscar.idConstanciaAnalista(documentoEstudiante, evento, tipo);
						var estudiante = Buscar.estudianteFiltro(documentoEstudiante, "Documento").get(0);
						FichaConstanciaEstudiante oActaConstancia = new FichaConstanciaEstudiante(constancia,
								estudiante);
						oActaConstancia.setLocationRelativeTo(null);
						oActaConstancia.setVisible(true);
						return;
					}
				}
			}
		});
		tableConstancias.setModel(new TableModelConstancia(new ArrayList<Constancia>()));
		scrollPaneTabla.setViewportView(tableConstancias);

		panelPrincipal.add(imagenContent);

		listarSolicitudes();

	}

	public void listarSolicitudes() {
		tableConstancias.setModel(new TableModelConstancia(Buscar.misConstancias()));
	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}

	public void limpiarCampos() {
		comboBoxTipoConstancia.setSelectedIndex(0);
		comboBoxEvento.setSelectedIndex(0);
		textFieldDetalle.setText(null);
		idConstancia = null;
		Fabrica.cargarListas();
		TableModelConstancia tableModelConstancia = new TableModelConstancia(Buscar.misConstancias());
		tableConstancias.setModel(tableModelConstancia);
	}
}
