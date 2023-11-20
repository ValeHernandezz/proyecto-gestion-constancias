package view.components.content.listadoItr;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.entities.Departamento;
import com.entities.Itr;

import context.Fabrica;
import context.helpers.Actualizar;
import context.helpers.Borrar;
import context.helpers.Buscar;
import services.ServiceItr;
import services.ServiceUbicacion;
import view.components.content.home.BienvenidoPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelItr;

public class ListadoItrPanel extends JPanel {

	// JPanel
	private JPanel panelContent;
	private JPanel panelDatos;
	private JPanel panelTabla;

	// JLabel
	private JLabel lblTitulo;
	private JLabel lblFiltroDescripcion;
	private JLabel lblNombre;

	// JButton
	private JButton buttonRegresar;
	private JButton btnCrear;
	private JButton btnEliminar;
	private JButton btnEditar;

	// JTextField
	private JTextField textFieldNombreItr;

	// JTable y JScrollPane
	private JTable tableItrs;
	private JScrollPane scrollPaneTabla;

	// JComboBox
	private JComboBox comboBoxBusqueda;
	private JComboBox comboBoxDepartamento;

	// JSeparator
	private JSeparator separator1;

	// Listas
	private ArrayList<Departamento> listaDeDepartamentos = ServiceUbicacion.listarDepartamentos();

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	// Extras
	private Departamento departamentoViejo = null;
	private boolean paraActivar = false;
	private Long idItr = null;

	public ListadoItrPanel(MostrarPanel panel) {
		setLayout(null);

		panelContent = new JPanel();
		panelContent.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panelContent.setOpaque(false);
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
		buttonRegresar.setBounds(10, 10, 45, 25);
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		panelContent.add(buttonRegresar);

		lblTitulo = new JLabel("ITRs");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 40));
		lblTitulo.setBounds(189, 14, 420, 58);
		panelContent.add(lblTitulo);

		panelDatos = new JPanel();
		panelDatos.setBounds(10, 148, 378, 325);
		panelDatos.setOpaque(false);
		panelDatos.setLayout(null);
		panelContent.add(panelDatos);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(61, 25, 256, 25);
		panelDatos.add(lblNombre);
		lblNombre.setFont(new Font("Cambria", Font.PLAIN, 20));

		textFieldNombreItr = new JTextField();
		textFieldNombreItr.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldNombreItr.setBounds(61, 75, 256, 25);
		panelDatos.add(textFieldNombreItr);
		textFieldNombreItr.setColumns(10);

		String[] departamentoStrings = new String[listaDeDepartamentos.size() + 1];
		departamentoStrings[0] = "Elige un departamento";

		for (int i = 0; i < listaDeDepartamentos.size(); i++) {
			departamentoStrings[i + 1] = listaDeDepartamentos.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelDepartamento = new DefaultComboBoxModel<>(departamentoStrings);

		comboBoxDepartamento = new JComboBox(comboBoxModelDepartamento);
		comboBoxDepartamento.setBounds(61, 125, 256, 25);
		panelDatos.add(comboBoxDepartamento);
		comboBoxDepartamento.setFont(new Font("Cambria", Font.PLAIN, 15));

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(139, 275, 100, 25);
		btnEliminar.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (idItr == null) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un ITR de la tabla para eliminarlo.",
							"Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este ITR?",
							"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

					if (opcion == 0) {

						boolean bajaLogica = Borrar.darBajaLogica(Buscar.itrPorId(idItr));

						if (bajaLogica) {
							JOptionPane.showMessageDialog(null, "El ITR ha sido eliminado con éxito.", "Éxito",
									JOptionPane.INFORMATION_MESSAGE);
							limpiarCampos();
							cargarLista();
							return;
						} else {
							JOptionPane.showMessageDialog(null, "El ITR no se ha podido eliminar con éxito.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Inténtelo de nuevo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		panelDatos.add(btnEliminar);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(139, 175, 100, 25);
		btnCrear.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (idItr != null) {
					idItr = null;
				}

				if (textFieldNombreItr.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de ITR para crearlo.",
							"Advertencia", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (comboBoxDepartamento.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Por favor, elija un departamento.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {

					String nombre = textFieldNombreItr.getText();

					var existeItr = Buscar.itrPorNombre(nombre);

					if (existeItr != null) {
						JOptionPane.showMessageDialog(null, "Ya existe un ITR con ese nombre.", "Advertencia",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Departamento departamento = ServiceUbicacion
							.listarDepartamentosFiltro(comboBoxDepartamento.getSelectedItem().toString()).get(0);
					Itr oItrNueva = new Itr(nombre, departamento, "S");
					System.out.println(oItrNueva.getNombre());

					Itr oItrGuardada = ServiceItr.crearItr(oItrNueva);

					if (oItrGuardada != null) {
						comboBoxDepartamento.setEnabled(true);
						JOptionPane.showMessageDialog(null,
								"El " + oItrNueva.getNombre() + " se ha creado exitosamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarCampos();
						cargarLista();
						return;
					} else {
						comboBoxDepartamento.setEnabled(true);
						limpiarCampos();
						JOptionPane.showMessageDialog(null,
								"No se ha podido crear con éxito el " + oItrNueva.getNombre(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Inténtelo de nuevo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.println(e2);
					return;
				}
			}
		});

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(139, 225, 100, 25);
		btnEditar.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (idItr == null) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un ITR de la tabla.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					String nombre = textFieldNombreItr.getText();

					var existeItr = Buscar.itrPorNombre(nombre);

					if (existeItr != null) {
						JOptionPane.showMessageDialog(null, "Ya existe un ITR con ese nombre.", "Advertencia",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Itr oItrEditada = new Itr(nombre, departamentoViejo, "S");

					oItrEditada.setIdItr(idItr);

					int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea editar este ITR?",
							"Confirmar edición", JOptionPane.YES_NO_OPTION);

					if (confirmacion == 0) {
						Itr oItrModificada = ServiceItr.actualizarItr(oItrEditada);

						if (oItrModificada != null) {
							comboBoxDepartamento.setEnabled(true);
							JOptionPane.showMessageDialog(null,
									"El " + oItrEditada.getNombre() + " se ha editado exitosamente.", "Éxito",
									JOptionPane.INFORMATION_MESSAGE);
							limpiarCampos();
							cargarLista();
							return;
						} else {
							comboBoxDepartamento.setEnabled(true);
							limpiarCampos();
							JOptionPane.showMessageDialog(null,
									"No se ha podido editar con éxito el " + oItrEditada.getNombre(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error base de datos");
				}
			}
		});
		panelDatos.add(btnEditar);
		panelDatos.add(btnCrear);

		separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);

		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(398, 86, 2, 449);
		panelContent.add(separator1);

		panelTabla = new JPanel();
		panelTabla.setBounds(410, 98, 378, 424);
		panelTabla.setOpaque(false);
		panelContent.add(panelTabla);
		panelTabla.setLayout(null);

		lblFiltroDescripcion = new JLabel("");
		lblFiltroDescripcion.setBounds(0, 21, 378, 25);
		panelTabla.add(lblFiltroDescripcion);
		lblFiltroDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltroDescripcion.setFont(new Font("Cambria", Font.PLAIN, 15));

		comboBoxBusqueda = new JComboBox();
		comboBoxBusqueda.setBounds(61, 67, 256, 25);
		panelTabla.add(comboBoxBusqueda);
		comboBoxBusqueda.setModel(new DefaultComboBoxModel(new String[] { "Filtro", "Activos", "Eliminados" }));
		comboBoxBusqueda.setFont(new Font("Cambria", Font.PLAIN, 15));

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(61, 113, 256, 289);
		panelTabla.add(scrollPaneTabla);

		tableItrs = new JTable();
		tableItrs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableItrs.getSelectedRow();

				if (paraActivar) {
					String nombre = (String) tableItrs.getValueAt(filaSeleccionada, 0);

					Itr oItrActivado = Buscar.itrPorNombre(nombre);

					int aceptar = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea activar este ITR?",
							"Confirmar activación", JOptionPane.YES_NO_OPTION);

					if (aceptar != 0) {
						return;
					}

					oItrActivado.setActivo("S");

					boolean itrActualizado = Actualizar.itr(oItrActivado);

					if (itrActualizado) {
						tableItrs.setModel(new TableModelItr(Buscar.itrsActivos("N")));
						JOptionPane.showMessageDialog(null, "ITR activado exitosamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					JOptionPane.showMessageDialog(null, "No se pudo activar el ITR.", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

				if (filaSeleccionada != -1) {
					String nombre = (String) tableItrs.getValueAt(filaSeleccionada, 0);
					idItr = ServiceItr.listarItrsFiltro(nombre).get(0).getIdItr();
					departamentoViejo = ServiceItr.listarItrsFiltro(nombre).get(0).getDepartamento();
					textFieldNombreItr.setText(nombre);
					comboBoxDepartamento.setSelectedIndex(0);
					comboBoxDepartamento.setEnabled(false);
				}
			}
		});
		tableItrs.setModel(new TableModelItr(new ArrayList<Itr>()));
		scrollPaneTabla.setViewportView(tableItrs);
		comboBoxBusqueda.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();
					lblFiltroDescripcion.setText("");
					paraActivar = false;
					if (!selectedItem.equals("Eliminados")) {
						tableItrs.setModel(new TableModelItr(Buscar.itrsActivos("S")));
						return;
					}

					if (selectedItem.equals("Eliminados")) {
						paraActivar = true;
						lblFiltroDescripcion.setText("Seleccione un ITR para activarlo");
						tableItrs.setModel(new TableModelItr(Buscar.itrsActivos("N")));
						return;
					}
				}
			}
		});

		panelContent.add(imagenPanelContent);
		imagenPanelContent.setLayout(null);

		cargarLista();
	}

	public void cargarLista() {
		Fabrica.cargarListas();
		TableModelItr oModel = new TableModelItr(Buscar.itrsActivos("S"));
		if (oModel != null) {
			tableItrs.setModel(oModel);
		}
	}

	public void limpiarCampos() {
		idItr = null;
		departamentoViejo = null;
		textFieldNombreItr.setText(null);
		comboBoxDepartamento.setSelectedIndex(0);
		comboBoxDepartamento.setEnabled(true);
	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
