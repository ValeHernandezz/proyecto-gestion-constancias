package view.components.content.listadoUsuarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import com.entities.Genero;
import com.entities.Itr;
import com.entities.Localidad;
import com.entities.Rol;
import com.entities.Usuario;
import com.toedter.calendar.JDateChooser;

import context.Fabrica;
import context.helpers.Actualizar;
import context.helpers.Borrar;
import context.helpers.Buscar;
import services.ServiceAnalista;
import services.ServiceEmail;
import services.ServiceGenero;
import services.ServiceItr;
import services.ServiceRol;
import services.ServiceUbicacion;
import services.ServiceUsuario;
import view.components.content.section.ListadoDeUsuariosPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelAnalista;
import view.validaciones.ValidacionAnalista;
import view.validaciones.ValidacionUsuario;

public class ListadoDeAnalistasPanel extends JPanel {

	// JPanel
	private JPanel panelPrincipal;
	private JPanel panelForm;
	private JPanel panelBuscador;

	// JLabel
	private JLabel labelListadoDeAnalistas;
	private JLabel labelNombres;
	private JLabel labelApellidos;
	private JLabel labelCedula;
	private JLabel lblTelefono;
	private JLabel labelMailPersonal;
	private JLabel labelMailInstitucional;
	private JLabel lblFechaNacimiento;
	private JLabel lblTituloBuscar;
	private JLabel lblFiltroDescripcion = new JLabel("");

	// JButton
	private JButton buttonRegresar;
	private JButton buttonEditar;
	private JButton buttonEliminar;
	private JButton buttonLimpiarCampos;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JButton btnListarTodo;

	// JTextField
	private JTextField textFieldCedula;
	private JTextField textFieldMailPersonal;
	private JTextField textFieldNombres;
	private JTextField textFieldApellidos;
	private JTextField textFieldTelefono;
	private JTextField textFieldMailInstitucional;
	private JTextField textFieldBuscador;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// JTable y JScollPane
	private JTable tableAnalista;
	private JScrollPane scrollPaneTabla;

	// JComboBox
	private JComboBox comboBoxGenero;
	private JComboBox comboBoxITR;
	private JComboBox comboBoxDepartamento;
	private JComboBox comboBoxLocalidad;
	private JComboBox comboBoxFiltro;

	// JDateChooser
	private JDateChooser dateChooserFechaNacimiento;

	// Listas
	private ArrayList<Genero> listaDeGeneros = ServiceGenero.listarGeneros();
	private ArrayList<Itr> listaDeItrs = Buscar.itrsActivos("S");
	private ArrayList<Departamento> listaDeDepartamentos = ServiceUbicacion.listarDepartamentos();

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	// Extras
	private boolean paraValidar = false;
	private boolean paraActivar = false;
	private String departamento = null;

	public ListadoDeAnalistasPanel(MostrarPanel panel) {
		setLayout(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panelPrincipal.setBounds(0, 0, 798, 550);
		add(panelPrincipal);
		panelPrincipal.setLayout(null);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.mostrarPanelContent(prepararPanel(new ListadoDeUsuariosPanel(panel), 798, 550));
			}
		});
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelPrincipal.add(buttonRegresar);
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));

		String[] generoStrings = new String[listaDeGeneros.size() + 1];
		generoStrings[0] = "Elige un género";

		for (int i = 0; i < listaDeGeneros.size(); i++) {
			generoStrings[i + 1] = listaDeGeneros.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelGenero = new DefaultComboBoxModel<>(generoStrings);

		String[] itrsStrings = new String[listaDeItrs.size() + 1];
		itrsStrings[0] = "Elige un ITR";

		for (int i = 0; i < listaDeItrs.size(); i++) {
			itrsStrings[i + 1] = listaDeItrs.get(i).getNombre();
		}

		DefaultComboBoxModel<String> comboBoxModelItr = new DefaultComboBoxModel<>(itrsStrings);

		labelListadoDeAnalistas = new JLabel("Listado de Analistas");
		labelListadoDeAnalistas.setHorizontalAlignment(SwingConstants.CENTER);
		labelListadoDeAnalistas.setFont(new Font("Rockwell", Font.BOLD, 40));
		labelListadoDeAnalistas.setBounds(156, 23, 486, 36);
		panelPrincipal.add(labelListadoDeAnalistas);

		panelForm = new JPanel();
		panelForm.setOpaque(false);
		panelForm.setBounds(10, 53, 778, 251);
		panelPrincipal.add(panelForm);
		panelForm.setLayout(null);

		labelNombres = new JLabel("Nombres");
		labelNombres.setBounds(34, 23, 151, 20);
		panelForm.add(labelNombres);
		labelNombres.setFont(new Font("Cambria", Font.PLAIN, 15));

		textFieldNombres = new JTextField();
		textFieldNombres.setBounds(34, 48, 151, 19);
		panelForm.add(textFieldNombres);
		textFieldNombres.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldNombres.setColumns(10);

		labelApellidos = new JLabel("Apellidos");
		labelApellidos.setBounds(219, 23, 151, 20);
		panelForm.add(labelApellidos);
		labelApellidos.setFont(new Font("Cambria", Font.PLAIN, 15));

		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(219, 48, 151, 19);
		panelForm.add(textFieldApellidos);
		textFieldApellidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldApellidos.setColumns(10);

		labelCedula = new JLabel("Cédula");
		labelCedula.setBounds(404, 23, 151, 20);
		panelForm.add(labelCedula);
		labelCedula.setFont(new Font("Cambria", Font.PLAIN, 15));

		textFieldCedula = new JTextField();
		textFieldCedula.setBounds(404, 48, 151, 19);
		panelForm.add(textFieldCedula);
		textFieldCedula.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldCedula.setColumns(10);

		lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(589, 23, 151, 20);
		panelForm.add(lblTelefono);
		lblTelefono.setFont(new Font("Cambria", Font.PLAIN, 15));

		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldTelefono.setBounds(589, 48, 151, 19);
		panelForm.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		labelMailPersonal = new JLabel("Mail Personal");
		labelMailPersonal.setBounds(34, 90, 151, 20);
		panelForm.add(labelMailPersonal);
		labelMailPersonal.setFont(new Font("Cambria", Font.PLAIN, 15));

		textFieldMailPersonal = new JTextField();
		textFieldMailPersonal.setBounds(34, 115, 151, 19);
		panelForm.add(textFieldMailPersonal);
		textFieldMailPersonal.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldMailPersonal.setColumns(10);

		labelMailInstitucional = new JLabel("Mail Institucional");
		labelMailInstitucional.setBounds(219, 90, 151, 20);
		panelForm.add(labelMailInstitucional);
		labelMailInstitucional.setFont(new Font("Cambria", Font.PLAIN, 15));

		textFieldMailInstitucional = new JTextField();
		textFieldMailInstitucional.setBounds(219, 115, 151, 19);
		panelForm.add(textFieldMailInstitucional);
		textFieldMailInstitucional.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldMailInstitucional.setColumns(10);

		lblFechaNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaNacimiento.setBounds(404, 90, 151, 20);
		panelForm.add(lblFechaNacimiento);
		lblFechaNacimiento.setFont(new Font("Cambria", Font.PLAIN, 15));

		dateChooserFechaNacimiento = new JDateChooser();
		dateChooserFechaNacimiento.setBounds(404, 115, 151, 19);
		dateChooserFechaNacimiento.setFont(new Font("Cambria", Font.PLAIN, 15));
		panelForm.add(dateChooserFechaNacimiento);

		comboBoxGenero = new JComboBox(comboBoxModelGenero);
		comboBoxGenero.setBounds(589, 114, 151, 21);
		panelForm.add(comboBoxGenero);
		comboBoxGenero.setFont(new Font("Cambria", Font.PLAIN, 15));

		comboBoxITR = new JComboBox(comboBoxModelItr);
		comboBoxITR.setBounds(81, 157, 151, 21);
		panelForm.add(comboBoxITR);
		comboBoxITR.setFont(new Font("Cambria", Font.PLAIN, 15));

		String[] departamentoStrings = new String[listaDeDepartamentos.size() + 1];
		departamentoStrings[0] = "Elige un departamento";

		for (int i = 0; i < listaDeDepartamentos.size(); i++) {
			departamentoStrings[i + 1] = listaDeDepartamentos.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelDepartamento = new DefaultComboBoxModel<>(departamentoStrings);

		comboBoxDepartamento = new JComboBox(comboBoxModelDepartamento);
		comboBoxDepartamento.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();
					if (!selectedItem.equals("Elige un departamento")) {
						departamento = selectedItem.toString();

						var lista = Buscar.localidadPorDepartamento(departamento);
						String[] localidadStrings = new String[lista.size() + 1];
						localidadStrings[0] = "Elige una localidad";
						for (int i = 0; i < lista.size(); i++) {
							localidadStrings[i + 1] = lista.get(i).getNombre();
						}
						DefaultComboBoxModel<String> comboBoxModelLocalidad = new DefaultComboBoxModel<>(
								localidadStrings);
						comboBoxLocalidad.setModel(comboBoxModelLocalidad);

						comboBoxLocalidad.setEnabled(true);
					} else {
						departamento = null;
						comboBoxLocalidad.setEnabled(false);
					}
				}
			}
		});
		comboBoxDepartamento.setBounds(313, 157, 151, 21);
		comboBoxDepartamento.setFont(new Font("Cambria", Font.PLAIN, 15));
		panelForm.add(comboBoxDepartamento);

		String[] localidadStrings = new String[1];
		localidadStrings[0] = "Elige un localidad";
		DefaultComboBoxModel<String> comboBoxModelLocalidad = new DefaultComboBoxModel<>(localidadStrings);

		comboBoxLocalidad = new JComboBox(comboBoxModelLocalidad);
		comboBoxLocalidad.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxLocalidad.setBounds(545, 157, 151, 21);
		comboBoxLocalidad.setEnabled(false);
		panelForm.add(comboBoxLocalidad);

		buttonEditar = new JButton("Editar");
		buttonEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// Obtengo los indices de los comboBox
				int comboIndexDepartamento = comboBoxDepartamento.getSelectedIndex();
				int comboIndexGenero = comboBoxGenero.getSelectedIndex();
				int comboIndexLocalidad = comboBoxLocalidad.getSelectedIndex();
				int comboIndexItr = comboBoxITR.getSelectedIndex();

				// Valido si son !=0
				boolean comboBoxesCompletos = ValidacionAnalista.validarComboBoxesAnalista(comboIndexDepartamento,
						comboIndexGenero, comboIndexLocalidad, comboIndexItr);

				if (!comboBoxesCompletos) {
					return;
				}

				// Obtengo los datos del interfaz y edito un objeto usuario
				try {
					String nombres[] = textFieldNombres.getText().split(" ");
					String apellidos[] = textFieldApellidos.getText().split(" ");

					String primerNombre = nombres.length > 0 ? nombres[0] : "";
					String segundoNombre = nombres.length > 1 ? nombres[1] : "";

					String primerApellido = apellidos.length > 0 ? apellidos[0] : "";
					String segundoApellido = apellidos.length > 1 ? apellidos[1] : "";

					BigDecimal cedula = new BigDecimal(textFieldCedula.getText());
					String mailPersonal = textFieldMailPersonal.getText();
					String mailInstitucional = textFieldMailInstitucional.getText();
					String telefono = textFieldTelefono.getText();
					Rol rol = ServiceRol.listarRolesFiltro("Analista").get(0);
					Genero genero = ServiceGenero.listarGenerosFiltro(comboBoxGenero.getSelectedItem().toString())
							.get(0);
					Departamento departamento = ServiceUbicacion
							.listarDepartamentosFiltro(comboBoxDepartamento.getSelectedItem().toString()).get(0);
					Localidad localidad = ServiceUbicacion
							.listarLocalidadesFiltro(comboBoxLocalidad.getSelectedItem().toString()).get(0);
					Itr itr = ServiceItr.listarItrsFiltro(comboBoxITR.getSelectedItem().toString()).get(0);

					Date selectedDate = dateChooserFechaNacimiento.getDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(selectedDate);

					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DAY_OF_MONTH);

					// Crea una instancia de Calendar y establece los valores obtenidos
					Calendar newCal = Calendar.getInstance();
					newCal.set(year, month, day);

					// Obtiene la instancia de Date a partir de Calendar
					Date fechaNacimiento = newCal.getTime();

					// Obtengo los datos que no estan en la tabla
					var analistaAntiguo = Buscar.analistaFiltro(String.valueOf(cedula), "Documento").get(0);

					// Valido los datos
					boolean datosValidos = ValidacionUsuario.validarUnUsuario(analistaAntiguo.getUsuario().getClave(),
							String.valueOf(cedula), fechaNacimiento, mailInstitucional, mailPersonal, primerApellido,
							primerNombre, segundoApellido, segundoNombre, telefono, "funcionario");

					if (!datosValidos) {
						return;
					}
					// Luego de saber que los datos son validos pregunto si esta seguro
					// Si le da si devuelve 0
					int confirmar = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de que desea editar los datos de este usuario?", "Confirmar edición",
							JOptionPane.YES_NO_OPTION);

					if (confirmar != 0) {
						return;
					}

					// NOTA IMPORTANTE: Al ser solo el evento de actualizar debemos setearle los
					// valores antiguos de activo y confirmado.
					// (el activo solo cambia con la accion de borrado y confirmado solo lo hace un
					// analista pero no puede desconfirmar)
					Usuario oUsuarioActualizado = new Usuario(analistaAntiguo.getUsuario().getClave(), cedula,
							fechaNacimiento, mailInstitucional, mailPersonal,
							analistaAntiguo.getUsuario().getNombreUsuario(), primerApellido, primerNombre,
							segundoApellido, segundoNombre, telefono, analistaAntiguo.getUsuario().getActivo(),
							analistaAntiguo.getUsuario().getConfirmado(), departamento, genero, itr, localidad, rol);

					// Le pongo el id que le corresponde al usuario
					oUsuarioActualizado.setIdUsuario(analistaAntiguo.getUsuario().getIdUsuario());

					// Actualizo los datos
					// Nota: como analista no tiene datos adiccionales a los que "hereda" de Usuario
					// solamente debemos actualizar el usuario

					var oUsuarioEditado = Actualizar.usuario(oUsuarioActualizado, analistaAntiguo);

					if (oUsuarioEditado) {
						// Si todo sale bien limpiamos los campos y actualizamos las listas
						limpiarCampos();
						Fabrica.cargarListas();
						listarAnalistas();
						JOptionPane.showMessageDialog(null, "El usuario ha sido editado correctamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						return;

					} else {
						// Vino null del servidor
						JOptionPane.showMessageDialog(null,
								"No se pudo editar el usuario.\nPor favor, inténtelo de nuevo más tarde.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					// Algo se rompio al tomar los valores del los inputs o mediante la peticion al
					// servidor
					JOptionPane.showMessageDialog(null, "Se ha producido un error en la Base de Datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		buttonEditar.setBounds(285, 202, 85, 21);
		panelForm.add(buttonEditar);
		buttonEditar.setFont(new Font("Cambria", Font.PLAIN, 13));

		buttonEliminar = new JButton("Eliminar");
		buttonEliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (textFieldCedula.getText().length() <= 5) {
					JOptionPane.showMessageDialog(null, "Seleccione un analista de la lista");
					return;
				}

				try {
					int confirmar = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de que desea eliminar este usuario?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirmar == 0) {
						var usuarioAEliminar = Buscar.analistaFiltro(textFieldCedula.getText(), "Documento").get(0);
						boolean respuesta = Borrar.darBajaLogica(usuarioAEliminar.getUsuario());
						if (respuesta) {
							limpiarCampos();
							Fabrica.cargarListas();
							listarAnalistas();
							JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado correctamente", "Éxito",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						} else {
							JOptionPane.showMessageDialog(null,
									"No se pudo eliminar el usuario.\nPor favor, inténtelo de nuevo más tarde.",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					return;
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error en la Base de Datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonEliminar.setBounds(404, 202, 85, 21);
		panelForm.add(buttonEliminar);
		buttonEliminar.setFont(new Font("Cambria", Font.PLAIN, 13));

		buttonLimpiarCampos = new JButton("Limpiar Campos");
		buttonLimpiarCampos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
			}
		});
		buttonLimpiarCampos.setBounds(604, 202, 136, 21);
		panelForm.add(buttonLimpiarCampos);
		buttonLimpiarCampos.setFont(new Font("Cambria", Font.PLAIN, 13));

		separator1 = new JSeparator();
		separator1.setForeground(Color.decode("#44bcf4"));
		separator1.setBounds(10, 303, 778, 2);
		panelPrincipal.add(separator1);

		panelBuscador = new JPanel();
		panelBuscador.setBounds(10, 315, 778, 67);
		panelBuscador.setOpaque(false);
		panelPrincipal.add(panelBuscador);
		panelBuscador.setLayout(null);

		lblTituloBuscar = new JLabel("Buscar:");
		lblTituloBuscar.setFont(new Font("Cambria", Font.BOLD, 15));
		lblTituloBuscar.setBounds(24, 11, 61, 20);
		panelBuscador.add(lblTituloBuscar);

		textFieldBuscador = new JTextField();
		textFieldBuscador.setFont(new Font("Cambria", Font.PLAIN, 12));
		textFieldBuscador.setBounds(109, 12, 145, 19);
		panelBuscador.add(textFieldBuscador);
		textFieldBuscador.setColumns(10);

		comboBoxFiltro = new JComboBox();
		comboBoxFiltro.setModel(new DefaultComboBoxModel(new String[] { "Filtro", "Nombre Completo", "Nombres",
				"Apellidos", "Documento", "Sin validar", "Itr", "Eliminados", "Activos" }));
		comboBoxFiltro.setFont(new Font("Cambria", Font.PLAIN, 12));
		comboBoxFiltro.setBounds(278, 11, 130, 21);
		comboBoxFiltro.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();
					lblFiltroDescripcion.setText("");
					paraValidar = false;
					paraActivar = false;
					if (selectedItem.equals("Activos")) {
						tableAnalista.setModel(new TableModelAnalista(Buscar.analistasActivos("S")));
						return;
					}

					if (selectedItem.equals("Eliminados")) {
						paraActivar = true;
						lblFiltroDescripcion.setText("Seleccione un analista para activar su cuenta.");
						tableAnalista.setModel(new TableModelAnalista(Buscar.analistasActivos("N")));
						return;
					}

					if (selectedItem.equals("Sin validar")) {
						paraValidar = true;
						lblFiltroDescripcion.setText("Seleccione un analista para validar su cuenta.");
						tableAnalista.setModel(new TableModelAnalista(Buscar.analistasSinConfirmar()));
						return;
					}

				}
			}
		});
		panelBuscador.add(comboBoxFiltro);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indexComboFiltro = comboBoxFiltro.getSelectedIndex();
				if (indexComboFiltro == 0) {
					JOptionPane.showMessageDialog(null, "Por favor, elija un filtro.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					String filtro = textFieldBuscador.getText();
					String campo = comboBoxFiltro.getSelectedItem().toString();

					if (campo.equals("Itr")) {
						var lista = Buscar.analistaITR(filtro);

						if (lista.size() == 0) {
							JOptionPane.showMessageDialog(null, "No se encontraron analistas para ese Itr",
									"Información", JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						TableModelAnalista oModelAnalista = new TableModelAnalista(lista);
						tableAnalista.setModel(oModelAnalista);
						return;
					}

					var lista = Buscar.analistaFiltro(filtro, campo);

					if (lista.size() == 0) {
						JOptionPane.showMessageDialog(null, "No se encontraron resultados.", "Información",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					TableModelAnalista oModelAnalista = new TableModelAnalista(lista);
					tableAnalista.setModel(oModelAnalista);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se produjo un error al buscar el usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnBuscar.setBounds(432, 11, 85, 21);
		panelBuscador.add(btnBuscar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarCamposBuscador();
			}
		});
		btnLimpiar.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnLimpiar.setBounds(541, 11, 85, 21);
		panelBuscador.add(btnLimpiar);

		btnListarTodo = new JButton("Listar todo");
		btnListarTodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listarAnalistas();
			}
		});
		btnListarTodo.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnListarTodo.setBounds(650, 11, 104, 21);
		panelBuscador.add(btnListarTodo);

		separator2 = new JSeparator();
		separator2.setForeground(Color.decode("#44bcf4"));
		separator2.setBounds(10, 368, 778, 2);
		panelPrincipal.add(separator2);

		lblFiltroDescripcion.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		lblFiltroDescripcion.setBounds(156, 376, 486, 25);
		lblFiltroDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		panelPrincipal.add(lblFiltroDescripcion);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(10, 407, 778, 133);
		panelPrincipal.add(scrollPaneTabla);

		tableAnalista = new JTable();
		tableAnalista.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		tableAnalista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableAnalista.getSelectedRow();

				if (paraValidar) {

					if (filaSeleccionada != -1) {
						String cedula = (String) tableAnalista.getValueAt(filaSeleccionada, 0);

						Usuario oUsuarioValidado = Buscar.analistaFiltro(cedula, "Documento").get(0).getUsuario();

						int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea validar el usuario?",
								"Validar usuario", JOptionPane.YES_NO_OPTION);

						if (aceptar == 0) {
							oUsuarioValidado.setConfirmado("S");
							var usuarioActualizado = ServiceUsuario.actualizarUsuario(oUsuarioValidado);
							if (usuarioActualizado != null) {
								tableAnalista.setModel(new TableModelAnalista(Buscar.analistasSinConfirmar()));
								ServiceEmail.mandarMailConfirmado(oUsuarioValidado);
								JOptionPane.showMessageDialog(null, "Usuario validado exitosamente", "Éxito",
										JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							JOptionPane.showMessageDialog(null, "No se pudo validar el usuario", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}

				if (paraActivar) {

					if (filaSeleccionada != -1) {
						String cedula = (String) tableAnalista.getValueAt(filaSeleccionada, 0);

						Usuario oUsuarioActivado = Buscar.analistaFiltro(cedula, "Documento").get(0).getUsuario();

						int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea activar el usuario?",
								"Activar usuario", JOptionPane.YES_NO_OPTION);

						if (aceptar == 0) {
							oUsuarioActivado.setActivo("S");

							Usuario usuarioActualizado = ServiceUsuario.actualizarUsuario(oUsuarioActivado);

							if (usuarioActualizado != null) {
								tableAnalista.setModel(new TableModelAnalista(Buscar.analistasActivos("N")));
								JOptionPane.showMessageDialog(null, "Usuario activado exitosamente", "Éxito",
										JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							JOptionPane.showMessageDialog(null, "No se pudo activar el usuario", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				if (filaSeleccionada != -1) {

					String documento = (String) tableAnalista.getValueAt(filaSeleccionada, 0);
					String nombres = (String) tableAnalista.getValueAt(filaSeleccionada, 1);
					String apellidos = (String) tableAnalista.getValueAt(filaSeleccionada, 2);
					String mailPersonal = (String) tableAnalista.getValueAt(filaSeleccionada, 3);
					String mailInstitucional = (String) tableAnalista.getValueAt(filaSeleccionada, 4);
					String genero = (String) tableAnalista.getValueAt(filaSeleccionada, 5);
					String departamento = (String) tableAnalista.getValueAt(filaSeleccionada, 6);
					String localidad = (String) tableAnalista.getValueAt(filaSeleccionada, 7);
					String itr = (String) tableAnalista.getValueAt(filaSeleccionada, 8);
					String telefono = (String) tableAnalista.getValueAt(filaSeleccionada, 9);
					String fechaSeleccionada = (String) tableAnalista.getValueAt(filaSeleccionada, 10);

					// Define el formato de fecha esperado
					SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaNacimiento = null;
					try {
						// Analiza la cadena en un objeto Date
						fechaNacimiento = formatoFecha.parse(fechaSeleccionada);

						// Establece la fecha en el JDateChooser
						dateChooserFechaNacimiento.setDate(fechaNacimiento);
					} catch (ParseException e2) {
						// Manejo de la excepción en caso de que el formato no sea válido
						e2.printStackTrace();
					}

					textFieldCedula.setText(documento);
					textFieldNombres.setText(nombres);
					textFieldApellidos.setText(apellidos);
					textFieldMailPersonal.setText(mailPersonal);
					textFieldMailInstitucional.setText(mailInstitucional);
					comboBoxGenero.setSelectedItem(genero);
					comboBoxDepartamento.setSelectedItem(departamento);
					comboBoxLocalidad.setSelectedItem(localidad);
					comboBoxITR.setSelectedItem(itr);
					textFieldTelefono.setText(telefono);
					dateChooserFechaNacimiento.setDate(fechaNacimiento);
					textFieldMailInstitucional.setEnabled(false);
					textFieldCedula.setEnabled(false);

				}

			}
		});
		tableAnalista.setModel(new TableModelAnalista(Buscar.analistasActivos("S")));
		scrollPaneTabla.setViewportView(tableAnalista);
		;
		;

		panelPrincipal.add(imagenFondoContent);

	}

	public void limpiarCampos() {
		comboBoxDepartamento.setSelectedIndex(0);
		comboBoxGenero.setSelectedIndex(0);
		comboBoxITR.setSelectedIndex(0);
		comboBoxLocalidad.setSelectedIndex(0);
		textFieldApellidos.setText(null);
		textFieldCedula.setText(null);
		textFieldMailInstitucional.setText(null);
		textFieldMailPersonal.setText(null);
		textFieldNombres.setText(null);
		textFieldTelefono.setText(null);
		textFieldMailInstitucional.setEnabled(true);
		textFieldCedula.setEnabled(true);
		dateChooserFechaNacimiento.setDate(null);
	}

	public void limpiarCamposBuscador() {
		comboBoxFiltro.setSelectedIndex(0);
		textFieldBuscador.setText(null);
		lblFiltroDescripcion.setText(null);
		paraValidar = false;
	}

	public void listarAnalistas() {
		tableAnalista.setModel(new TableModelAnalista(ServiceAnalista.listarAnalistas()));
	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
