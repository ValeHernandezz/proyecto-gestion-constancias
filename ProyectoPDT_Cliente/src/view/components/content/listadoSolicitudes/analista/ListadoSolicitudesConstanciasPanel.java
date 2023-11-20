package view.components.content.listadoSolicitudes.analista;

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

import com.entities.Constancia;
import com.entities.Estado;
import com.entities.Tipo;

import context.helpers.Buscar;
import services.ServiceEstado;
import services.ServiceTipo;
import view.components.content.home.BienvenidoPanel;
import view.components.content.listadoSolicitudes.FichaConstanciaAnalista;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelConstancia;

public class ListadoSolicitudesConstanciasPanel extends JPanel {

	// JPanel
	private JPanel panelContent;
	private JPanel panelBusqueda;
	private JPanel panelFiltrar;

	// JButton
	private JButton buttonRegresar;
	private JButton btnListarPorFiltro;

	// JTextField
	private JTextField textFieldBuscarUsuario;

	// JLabel
	private JLabel labelTitulo;
	private JLabel lblBuscarNombreUsuario;
	private JLabel lblFiltrar;

	// JSeparator
	private JSeparator separator1;

	// JComboBox
	private JComboBox comboBoxEstados;
	private JComboBox comboBoxTipoConstancia;

	// JTable y JScrollPane
	private JTable tableConstancias;
	private JScrollPane scrollPaneTabla;

	// Listas
	private ArrayList<Estado> listaDeEstados = ServiceEstado.listarEstados();
	private ArrayList<Tipo> listaDeTipos = ServiceTipo.listarTiposConstancias();

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	// Extras
	private boolean filtroEstado = false;
	private boolean filtroTipo = false;

	public ListadoSolicitudesConstanciasPanel(MostrarPanel panel) {
		setLayout(null);

		panelContent = new JPanel();
		panelContent.setBackground(new Color(192, 192, 192));
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

		labelTitulo = new JLabel("Solicitudes de Constancia");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Rockwell", Font.BOLD, 40));
		labelTitulo.setBounds(19, 29, 759, 58);
		panelContent.add(labelTitulo);

		panelBusqueda = new JPanel();
		panelBusqueda.setOpaque(false);
		panelBusqueda.setBounds(19, 116, 369, 77);
		panelContent.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		lblBuscarNombreUsuario = new JLabel("Nombre de Usuario:");
		lblBuscarNombreUsuario.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblBuscarNombreUsuario.setBounds(10, 12, 293, 20);
		panelBusqueda.add(lblBuscarNombreUsuario);

		textFieldBuscarUsuario = new JTextField();
		textFieldBuscarUsuario.setBounds(10, 44, 225, 19);
		panelBusqueda.add(textFieldBuscarUsuario);
		textFieldBuscarUsuario.setColumns(10);

		String[] estadosString = new String[listaDeEstados.size() + 1];
		estadosString[0] = "Elige un estado";

		for (int i = 0; i < listaDeEstados.size(); i++) {
			estadosString[i + 1] = listaDeEstados.get(i).getDescripcion();
		}
		DefaultComboBoxModel<String> comboBoxModelEstado = new DefaultComboBoxModel<>(estadosString);

		btnListarPorFiltro = new JButton("Buscar");
		btnListarPorFiltro.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnListarPorFiltro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (textFieldBuscarUsuario.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Para realizar la búsqueda debe ingresar el nombre de usuario.",
							"Advertencia", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				String nombreUsuario = textFieldBuscarUsuario.getText();

				TableModelConstancia tableModelConstancia = new TableModelConstancia(
						Buscar.constanciaPorNombreUsuario(nombreUsuario));

				tableConstancias.setModel(tableModelConstancia);
			}
		});
		btnListarPorFiltro.setBounds(265, 43, 94, 21);
		panelBusqueda.add(btnListarPorFiltro);

		separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(398, 116, 2, 77);
		panelContent.add(separator1);

		panelFiltrar = new JPanel();
		panelFiltrar.setBounds(410, 116, 368, 77);
		panelFiltrar.setOpaque(false);
		panelContent.add(panelFiltrar);
		panelFiltrar.setLayout(null);

		lblFiltrar = new JLabel("Filtrar:");
		lblFiltrar.setBounds(10, 12, 170, 20);
		lblFiltrar.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblFiltrar.setHorizontalAlignment(SwingConstants.LEFT);
		panelFiltrar.add(lblFiltrar);

		String[] tiposConstanciaStrings = new String[listaDeTipos.size() + 1];
		tiposConstanciaStrings[0] = "Elige un tipo";
		for (int i = 0; i < listaDeTipos.size(); i++) {
			tiposConstanciaStrings[i + 1] = listaDeTipos.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelTipo = new DefaultComboBoxModel<>(tiposConstanciaStrings);

		comboBoxTipoConstancia = new JComboBox(comboBoxModelTipo);
		comboBoxTipoConstancia.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxTipoConstancia.setBounds(10, 44, 170, 21);
		panelFiltrar.add(comboBoxTipoConstancia);
		comboBoxTipoConstancia.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();

					if (selectedItem != "Elige un tipo") {
						filtroTipo = true;
						if (filtroTipo && filtroEstado) {
							TableModelConstancia tableModelConstancia = new TableModelConstancia(
									Buscar.constanciaPorTipoYEstado(selectedItem.toString(),
											comboBoxModelEstado.getSelectedItem().toString()));
							tableConstancias.setModel(tableModelConstancia);
						} else {
							TableModelConstancia tableModelConstancia = new TableModelConstancia(
									Buscar.constanciaPorTipo(selectedItem.toString()));
							tableConstancias.setModel(tableModelConstancia);
						}
					} else {
						filtroTipo = false;
						TableModelConstancia tableModelConstancia = new TableModelConstancia(
								Buscar.constanciaNoFinalizadas());
						tableConstancias.setModel(tableModelConstancia);
					}
				}

			}
		});

		comboBoxEstados = new JComboBox(comboBoxModelEstado);
		comboBoxEstados.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxEstados.setBounds(188, 44, 170, 21);
		panelFiltrar.add(comboBoxEstados);
		comboBoxEstados.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();

					if (selectedItem != "Elige un estado") {
						filtroEstado = true;
						if (filtroTipo && filtroEstado) {
							TableModelConstancia tableModelConstancia = new TableModelConstancia(
									Buscar.constanciaPorTipoYEstado(comboBoxTipoConstancia.getSelectedItem().toString(),
											selectedItem.toString()));
							tableConstancias.setModel(tableModelConstancia);
						} else {
							TableModelConstancia tableModelConstancia = new TableModelConstancia(
									Buscar.constanciaPorEstado(selectedItem.toString()));
							tableConstancias.setModel(tableModelConstancia);
						}
					} else {
						filtroEstado = false;
						TableModelConstancia tableModelConstancia = new TableModelConstancia(
								Buscar.constanciaNoFinalizadas());
						tableConstancias.setModel(tableModelConstancia);
					}
				}

			}
		});

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(30, 222, 738, 298);
		panelContent.add(scrollPaneTabla);

		tableConstancias = new JTable();
		tableConstancias.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableConstancias.getSelectedRow();

				if (filaSeleccionada != -1) {

					String tipo = (String) tableConstancias.getValueAt(filaSeleccionada, 0);
					String evento = (String) tableConstancias.getValueAt(filaSeleccionada, 1);
					String documentoEstudiante = (String) tableConstancias.getValueAt(filaSeleccionada, 5);

					var constancia = Buscar.idConstanciaAnalista(documentoEstudiante, evento, tipo);
					var estudiante = Buscar.estudianteFiltro(documentoEstudiante, "Documento").get(0);

					if (!constancia.getEstado().getDescripcion().equals("Finalizado")) {

						int opcion = JOptionPane.showConfirmDialog(null, "¿Desea modificar el estado de la constancia?",
								"Confirmar modificación", JOptionPane.YES_NO_OPTION);

						if (opcion == 0) {
							FichaConstanciaAnalista oGestionConstancia = new FichaConstanciaAnalista(constancia,
									estudiante, tableConstancias);
							oGestionConstancia.setLocationRelativeTo(null);
							oGestionConstancia.setVisible(true);
						}

					}

				}

			}
		});
		tableConstancias.setModel(new TableModelConstancia(new ArrayList<Constancia>()));
		scrollPaneTabla.setViewportView(tableConstancias);

		panelContent.add(imagenPanelContent);

		cargarListaConstancias();

	}

	public void cargarListaConstancias() {
		ArrayList<Constancia> lista = Buscar.constanciaNoFinalizadas();

		if (lista != null) {
			tableConstancias.setModel(new TableModelConstancia(lista));
		}
	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
