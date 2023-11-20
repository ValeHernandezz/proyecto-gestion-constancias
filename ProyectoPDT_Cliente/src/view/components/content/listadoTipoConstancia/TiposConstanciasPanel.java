package view.components.content.listadoTipoConstancia;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import com.entities.Itr;
import com.entities.Tipo;

import context.helpers.Actualizar;
import context.helpers.Borrar;
import context.helpers.Buscar;
import services.ServiceItr;
import services.ServiceTipo;
import view.components.content.home.BienvenidoPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelItr;
import view.utils.TableModelTipoConstancia;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import java.awt.Color;

public class TiposConstanciasPanel extends JPanel {

	// JPanel
	private JPanel panelContent;
	private JPanel panelDatos;
	private JPanel panelTabla;

	// JButton
	private JButton buttonRegresar;
	private JButton btnCrear;
	private JButton btnEditar;
	private JButton btnEliminar;

	// JLabel
	private JLabel lblTiposConstancias;
	private JLabel lblNombre;
	private JLabel lblFiltroDescripcion;

	// JTextField
	private JTextField textFieldNombre;

	// JSeparator
	private JSeparator separator1;

	// JComboBox
	private JComboBox comboBoxFiltro;

	// JTable y JScrollPane
	private JTable tableTiposConstancias;
	private JScrollPane scrollPane;

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	// Extras
	private Long idTipo = null;
	private boolean paraActivar = false;

	public TiposConstanciasPanel(MostrarPanel panel) {
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
		buttonRegresar.setBounds(10, 10, 45, 25);
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		panelContent.add(buttonRegresar);

		lblTiposConstancias = new JLabel("Tipos de Constancias");
		lblTiposConstancias.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiposConstancias.setFont(new Font("Rockwell", Font.BOLD, 40));
		lblTiposConstancias.setBounds(38, 14, 721, 58);
		panelContent.add(lblTiposConstancias);

		panelDatos = new JPanel();
		panelDatos.setBounds(10, 148, 378, 325);
		panelDatos.setOpaque(false);
		panelContent.add(panelDatos);
		panelDatos.setLayout(null);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblNombre.setBounds(94, 33, 190, 25);
		panelDatos.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(61, 91, 256, 25);
		panelDatos.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnCrear.setBounds(139, 149, 100, 25);
		panelDatos.add(btnCrear);

		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnEditar.setBounds(139, 207, 100, 25);
		panelDatos.add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnEliminar.setBounds(139, 265, 100, 25);
		panelDatos.add(btnEliminar);

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

		lblFiltroDescripcion = new JLabel(
				"<html><div style='text-align: center;'>Seleccione un Tipo de Constancia para<br/>editar sus datos o modificar su plantilla.</div></html>");
		lblFiltroDescripcion.setBounds(0, 0, 378, 57);
		panelTabla.add(lblFiltroDescripcion);
		lblFiltroDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltroDescripcion.setFont(new Font("Cambria", Font.PLAIN, 15));

		comboBoxFiltro = new JComboBox();
		comboBoxFiltro.setBounds(61, 67, 256, 25);
		panelTabla.add(comboBoxFiltro);
		comboBoxFiltro.setModel(new DefaultComboBoxModel(new String[] { "Filtro", "Activos", "Eliminados" }));
		comboBoxFiltro.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxFiltro.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();
					lblFiltroDescripcion.setText("");
					paraActivar = false;
					if (!selectedItem.equals("Eliminados")) {
						lblFiltroDescripcion.setText(
								"<html><div style='text-align: center;'>Seleccione un Tipo de Constancia para<br/>editar sus datos o modificar su plantilla.</div></html>");
						tableTiposConstancias
								.setModel(new TableModelTipoConstancia(Buscar.tiposConstanciasActivos("S")));
						return;
					}

					if (selectedItem.equals("Eliminados")) {
						paraActivar = true;
						lblFiltroDescripcion.setText(
								"<html><div style='text-align: center;'>Seleccione un Tipo de Constancia para activarlo</div></html>");
						tableTiposConstancias
								.setModel(new TableModelTipoConstancia(Buscar.tiposConstanciasActivos("N")));
						return;
					}
				}
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 113, 256, 289);
		panelTabla.add(scrollPane);

		tableTiposConstancias = new JTable();
		tableTiposConstancias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableTiposConstancias.getSelectedRow();

				if (paraActivar) {
					String nombre = (String) tableTiposConstancias.getValueAt(filaSeleccionada, 0);

					Tipo oTipoConstanciaActivado = Buscar.tipoConstanciaPorNombre(nombre);

					int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea activar este Tipo de Constancia?",
							"Confirmar activación", JOptionPane.YES_NO_OPTION);

					if (aceptar != 0) {
						return;
					}

					oTipoConstanciaActivado.setActivo("S");

					boolean tipoConstanciaActualizado = Actualizar.tipoConstancia(oTipoConstanciaActivado);

					if (tipoConstanciaActualizado) {
						tableTiposConstancias
								.setModel(new TableModelTipoConstancia(Buscar.tiposConstanciasActivos("N")));
						JOptionPane.showMessageDialog(null, "Tipo de Constancia activado exitosamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					JOptionPane.showMessageDialog(null, "No se pudo activar el Tipo de Constancia", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

				if (filaSeleccionada != -1) {

					Object[] options = { "Editar datos", "Modificar plantilla" };
					int option = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?", "Acción",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					if (option == 0) {

						String nombre = (String) tableTiposConstancias.getValueAt(filaSeleccionada, 0);
						idTipo = ServiceTipo.listarTiposConstanciasFiltro(nombre).get(0).getIdTipo();

						textFieldNombre.setText(nombre);
					} else if (option == 1) {
						String nombre = (String) tableTiposConstancias.getValueAt(filaSeleccionada, 0);
						ModificacionPlantillaFrame modificarPlantillaFrame = new ModificacionPlantillaFrame(
								ServiceTipo.listarTiposConstanciasFiltro(nombre).get(0));
						modificarPlantillaFrame.setLocationRelativeTo(null);
						modificarPlantillaFrame.setVisible(true);
					}

				}
			}
		});
		scrollPane.setViewportView(tableTiposConstancias);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (idTipo == null) {
					JOptionPane.showMessageDialog(null,
							"Debe seleccionar un tipo de constancia de la tabla para eliminarlo.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {

					int confirmacion = JOptionPane.showConfirmDialog(null,
							"¿Está seguro que desea eliminar este tipo de constancia?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirmacion != 0) {
						return;
					}

					boolean eliminarTipoContancia = Borrar.darBajaLogica(Buscar.tipoConstanciaPorId(idTipo));

					if (eliminarTipoContancia) {

						listarTiposConstancias();
						textFieldNombre.setText(null);
						idTipo = null;
						JOptionPane.showMessageDialog(null, "El tipo de constancia ha sido eliminado con éxito.",
								"Éxito", JOptionPane.INFORMATION_MESSAGE);
						return;

					} else {
						textFieldNombre.setText(null);
						idTipo = null;
						JOptionPane.showMessageDialog(null, "El tipo de constancia no se ha podido eliminar con éxito.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Inténtelo de nuevo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (idTipo == null) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de constancia de la tabla.",
							"Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				String nombre = textFieldNombre.getText();
				var existeConstancia = Buscar.tipoConstanciaPorNombre(nombre);

				if (existeConstancia != null) {
					JOptionPane.showMessageDialog(null, "Ya existe un tipo de constancia con ese nombre.",
							"Advertencia", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (nombre.trim().equals("") || nombre.trim().length() < 5) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				String descripcion = Buscar.tipoConstanciaPorId(idTipo).getDescripcion();

				Tipo tipoConstanciaNuevo = new Tipo(nombre, "S", descripcion);
				tipoConstanciaNuevo.setIdTipo(idTipo);

				try {

					int confirmacion = JOptionPane.showConfirmDialog(null,
							"¿Está seguro que desea editar este tipo de constancia?", "Confirmar edición",
							JOptionPane.YES_NO_OPTION);

					if (confirmacion != 0) {
						return;
					}

					boolean actualizarTipoContancia = ServiceTipo.actualizarTipoConstancia(tipoConstanciaNuevo);
					if (actualizarTipoContancia) {

						listarTiposConstancias();
						textFieldNombre.setText(null);
						idTipo = null;
						JOptionPane.showMessageDialog(null, "El tipo de constancia " + tipoConstanciaNuevo.getNombre()
								+ " se ha editado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						return;

					} else {
						textFieldNombre.setText(null);
						idTipo = null;
						JOptionPane.showMessageDialog(null, "El tipo de constancia " + tipoConstanciaNuevo.getNombre()
								+ " no se ha podido editar con éxito.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Inténtelo de nuevo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String nombre = textFieldNombre.getText();

				var existeConstancia = Buscar.tipoConstanciaPorNombre(nombre);

				if (existeConstancia != null) {
					JOptionPane.showMessageDialog(null, "Ya existe un tipo de constancia con ese nombre.",
							"Advertencia", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (nombre.trim().equals("") || nombre.trim().length() < 5) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Tipo tipoConstanciaNuevo = new Tipo(nombre, "S", "");

				try {
					boolean crearTipoContancia = ServiceTipo.crearTipoConstancia(tipoConstanciaNuevo);
					if (crearTipoContancia) {
						listarTiposConstancias();
						textFieldNombre.setText(null);
						JOptionPane.showMessageDialog(null, "El tipo de constancia " + tipoConstanciaNuevo.getNombre()
								+ " se ha creado exitosamente, para definir su plantilla PDF seleccione la misma desde la tabla.",
								"Éxito", JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						textFieldNombre.setText(null);
						JOptionPane.showMessageDialog(null, "No se ha podido crear con éxito el tipo de constancia "
								+ tipoConstanciaNuevo.getNombre() + " .", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Inténtelo de nuevo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});

		panelContent.add(imagenPanelContent);

		listarTiposConstancias();

	}

	public void listarTiposConstancias() {
		var lista = Buscar.tiposConstanciasActivos("S");
		tableTiposConstancias.setModel(new TableModelTipoConstancia(lista != null ? lista : new ArrayList<Tipo>()));
	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
