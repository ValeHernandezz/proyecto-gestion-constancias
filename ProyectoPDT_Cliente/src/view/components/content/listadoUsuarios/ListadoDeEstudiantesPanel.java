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

import com.entities.Departamento;
import com.entities.Estudiante;
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
import services.ServiceEmail;
import services.ServiceEstudiante;
import services.ServiceGenero;
import services.ServiceItr;
import services.ServiceRol;
import services.ServiceUbicacion;
import services.ServiceUsuario;
import view.components.content.listadoSolicitudes.ConstanciasEstudiante;
import view.components.content.section.ListadoDeUsuariosPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelEstudiante;
import view.validaciones.ValidacionEstudiante;
import view.validaciones.ValidacionUsuario;

public class ListadoDeEstudiantesPanel extends JPanel {

	// JPanel
	private JPanel panelPrincipal;
	private JPanel panelForm;
	private JPanel panelBuscador;

	// JButton
	private JButton buttonRegresar;
	private JButton buttonModificar;
	private JButton buttonEliminar;
	private JButton buttonLimpiarCampos;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JButton btnListarTodo;

	// JLabel
	private JLabel lblListadoDeEstudiantes;
	private JLabel labelNombres;
	private JLabel labelApellidos;
	private JLabel labelCedula;
	private JLabel lblTelefono;
	private JLabel labelMailPersonal;
	private JLabel labelMailInstitucional;
	private JLabel lblFechaNacimiento;
	private JLabel lblTituloBuscar;
	private JLabel lblFiltroDescripcion = new JLabel("");

	// JTextField
	private JTextField textFieldNombres;
	private JTextField textFieldApellidos;
	private JTextField textFieldDocumento;
	private JTextField textFieldTelefono;
	private JTextField textFieldMailPersonal;
	private JTextField textFieldMailInstitucional;
	private JTextField textFieldBuscador;

	// JComboBox
	private JComboBox comboBoxGeneracion;
	private JComboBox comboBoxSemestre;
	private JComboBox comboBoxLocalidad;
	private JComboBox comboBoxGenero;
	private JComboBox comboBoxITR;
	private JComboBox comboBoxDepartamento;
	private JComboBox comboBoxFiltro;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// JTable y JScrollPane
	private JTable tableEstudiantes;
	private JScrollPane scrollPane;

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

	public ListadoDeEstudiantesPanel(MostrarPanel panel) {
		setLayout(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		panelPrincipal.setBounds(0, 0, 798, 550);
		add(panelPrincipal);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(panel.prepararPanel(new ListadoDeUsuariosPanel(panel), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelPrincipal.add(buttonRegresar);

		lblListadoDeEstudiantes = new JLabel("Listado de Estudiantes");
		lblListadoDeEstudiantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeEstudiantes.setFont(new Font("Rockwell", Font.BOLD, 40));
		lblListadoDeEstudiantes.setBounds(156, 23, 486, 36);
		panelPrincipal.add(lblListadoDeEstudiantes);

		panelForm = new JPanel();
		panelForm.setLayout(null);
		panelForm.setBounds(10, 53, 778, 280);
		panelForm.setOpaque(false);
		panelPrincipal.add(panelForm);

		labelNombres = new JLabel("Nombres");
		labelNombres.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelNombres.setBounds(34, 23, 151, 20);
		panelForm.add(labelNombres);

		textFieldNombres = new JTextField();
		textFieldNombres.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldNombres.setColumns(10);
		textFieldNombres.setBounds(34, 48, 151, 19);
		panelForm.add(textFieldNombres);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(219, 48, 151, 19);
		panelForm.add(textFieldApellidos);

		labelApellidos = new JLabel("Apellidos");
		labelApellidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelApellidos.setBounds(219, 23, 151, 20);
		panelForm.add(labelApellidos);

		textFieldDocumento = new JTextField();
		textFieldDocumento.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldDocumento.setColumns(10);
		textFieldDocumento.setBounds(404, 48, 151, 19);
		panelForm.add(textFieldDocumento);

		labelCedula = new JLabel("Cédula");
		labelCedula.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelCedula.setBounds(404, 23, 151, 20);
		panelForm.add(labelCedula);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(589, 48, 151, 19);
		panelForm.add(textFieldTelefono);

		lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblTelefono.setBounds(589, 23, 151, 20);
		panelForm.add(lblTelefono);

		textFieldMailPersonal = new JTextField();
		textFieldMailPersonal.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldMailPersonal.setColumns(10);
		textFieldMailPersonal.setBounds(81, 115, 151, 19);
		panelForm.add(textFieldMailPersonal);

		labelMailPersonal = new JLabel("Mail Personal");
		labelMailPersonal.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelMailPersonal.setBounds(81, 90, 151, 20);
		panelForm.add(labelMailPersonal);

		textFieldMailInstitucional = new JTextField();
		textFieldMailInstitucional.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldMailInstitucional.setColumns(10);
		textFieldMailInstitucional.setBounds(313, 115, 151, 19);
		panelForm.add(textFieldMailInstitucional);

		labelMailInstitucional = new JLabel("Mail Institucional");
		labelMailInstitucional.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelMailInstitucional.setBounds(313, 90, 151, 20);
		panelForm.add(labelMailInstitucional);

		lblFechaNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaNacimiento.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblFechaNacimiento.setBounds(545, 90, 151, 20);
		panelForm.add(lblFechaNacimiento);

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

		comboBoxGenero = new JComboBox(comboBoxModelGenero);
		comboBoxGenero.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxGenero.setBounds(81, 157, 151, 21);
		panelForm.add(comboBoxGenero);

		comboBoxITR = new JComboBox(comboBoxModelItr);
		comboBoxITR.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxITR.setBounds(545, 199, 151, 21);
		panelForm.add(comboBoxITR);

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

		buttonModificar = new JButton("Editar");
		buttonModificar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// Obtengo los index de los comboBox
				int comboIndexDepartamento = comboBoxDepartamento.getSelectedIndex();
				int comboIndexGenero = comboBoxGenero.getSelectedIndex();
				int comboIndexLocalidad = comboBoxLocalidad.getSelectedIndex();
				int comboIndexItr = comboBoxITR.getSelectedIndex();
				int comboIndexGeneracion = comboBoxGeneracion.getSelectedIndex();
				int comboIndexSemestre = comboBoxSemestre.getSelectedIndex();

				// Verifico que sean != 0
				boolean comboBoxesCompletos = ValidacionEstudiante.validarComboBoxesEstudiante(comboIndexDepartamento,
						comboIndexGenero, comboIndexLocalidad, comboIndexItr, comboIndexGeneracion, comboIndexSemestre);

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

					BigDecimal cedula = new BigDecimal(textFieldDocumento.getText());
					String mailPersonal = textFieldMailPersonal.getText();
					String mailInstitucional = textFieldMailInstitucional.getText();
					String telefono = textFieldTelefono.getText();
					Rol rol = ServiceRol.listarRolesFiltro("Estudiante").get(0);
					Genero genero = ServiceGenero.listarGenerosFiltro(comboBoxGenero.getSelectedItem().toString())
							.get(0);
					Departamento departamento = ServiceUbicacion
							.listarDepartamentosFiltro(comboBoxDepartamento.getSelectedItem().toString()).get(0);
					Localidad localidad = ServiceUbicacion
							.listarLocalidadesFiltro(comboBoxLocalidad.getSelectedItem().toString()).get(0);
					Itr itr = ServiceItr.listarItrsFiltro(comboBoxITR.getSelectedItem().toString()).get(0);

					var estudianteAntiguo = Buscar.estudianteFiltro(String.valueOf(cedula), "Documento").get(0);

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
					boolean datosValidos = ValidacionUsuario.validarUnUsuario(estudianteAntiguo.getUsuario().getClave(),
							String.valueOf(cedula), fechaNacimiento, mailInstitucional, mailPersonal, primerApellido,
							primerNombre, segundoApellido, segundoNombre, telefono, "estudiante");

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

					Usuario oUsuarioNuevo = new Usuario(estudianteAntiguo.getUsuario().getClave(), cedula,
							fechaNacimiento, mailInstitucional, mailPersonal,
							estudianteAntiguo.getUsuario().getNombreUsuario(), primerApellido, primerNombre,
							segundoApellido, segundoNombre, telefono, estudianteAntiguo.getUsuario().getActivo(),
							estudianteAntiguo.getUsuario().getConfirmado(), departamento, genero, itr, localidad, rol);

					oUsuarioNuevo.setIdUsuario(estudianteAntiguo.getUsuario().getIdUsuario());

					Estudiante oEstudianteActualizado = new Estudiante(comboBoxGeneracion.getSelectedItem().toString(),
							new BigDecimal(comboBoxSemestre.getSelectedItem().toString()), oUsuarioNuevo);

					oEstudianteActualizado.setIdEstudiante(estudianteAntiguo.getIdEstudiante());

					boolean oEstudianteEditado = Actualizar.usuario(oUsuarioNuevo, oEstudianteActualizado);

					if (oEstudianteEditado) {
						limpiarCampos();
						Fabrica.cargarListas();
						listarEstudiantes();
						JOptionPane.showMessageDialog(null, "El usuario ha sido editado correctamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						JOptionPane.showMessageDialog(null,
								"No se pudo editar el usuario.\nPor favor, inténtelo de nuevo más tarde.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error en la Base de Datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		buttonModificar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonModificar.setBounds(285, 242, 85, 21);
		panelForm.add(buttonModificar);

		buttonEliminar = new JButton("Eliminar");
		buttonEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldDocumento.getText().length() <= 5) {
					JOptionPane.showMessageDialog(null, "Seleccione un estudiante de la lista");
					return;
				}
				try {
					int confirmar = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de que desea eliminar este usuario?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);
					if (confirmar == 0) {
						boolean respuesta = Borrar.darBajaLogica(
								Buscar.estudianteFiltro(textFieldDocumento.getText(), "Documento").get(0).getUsuario());
						if (respuesta) {
							limpiarCampos();
							Fabrica.cargarListas();
							listarEstudiantes();
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
		buttonEliminar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonEliminar.setBounds(404, 242, 85, 21);
		panelForm.add(buttonEliminar);

		buttonLimpiarCampos = new JButton("Limpiar Campos");
		buttonLimpiarCampos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
			}
		});
		buttonLimpiarCampos.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonLimpiarCampos.setBounds(604, 242, 136, 21);
		panelForm.add(buttonLimpiarCampos);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 407, 778, 133);
		panelPrincipal.add(scrollPane);

		tableEstudiantes = new JTable();
		tableEstudiantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableEstudiantes.getSelectedRow();

				if (paraValidar) {

					if (filaSeleccionada != -1) {
						String documento = (String) tableEstudiantes.getValueAt(filaSeleccionada, 0);

						Usuario oUsuarioConfirmado = Buscar.estudianteFiltro(documento, "Documento").get(0)
								.getUsuario();

						int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea confirmar el usuario?");

						if (aceptar == 0) {
							oUsuarioConfirmado.setConfirmado("S");
							var usuarioActualizado = ServiceUsuario.actualizarUsuario(oUsuarioConfirmado);
							if (usuarioActualizado != null) {
								tableEstudiantes.setModel(new TableModelEstudiante(Buscar.estudiantesSinConfirmar()));
								ServiceEmail.mandarMailConfirmado(oUsuarioConfirmado);
								JOptionPane.showMessageDialog(null, "Usuario confirmado con éxito");
								return;
							}
							JOptionPane.showMessageDialog(null, "No se pudo confirmar el usuario");
						}
					}
				}
				if (paraActivar) {

					if (filaSeleccionada != -1) {
						String documento = (String) tableEstudiantes.getValueAt(filaSeleccionada, 0);

						Usuario oUsuarioActivado = Buscar.estudianteFiltro(documento, "Documento").get(0).getUsuario();

						int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea activar el usuario?");

						if (aceptar == 0) {
							oUsuarioActivado.setActivo("S");
							var usuarioActualizado = ServiceUsuario.actualizarUsuario(oUsuarioActivado);
							if (usuarioActualizado != null) {
								tableEstudiantes.setModel(new TableModelEstudiante(Buscar.estudiantesActivos("N")));
								JOptionPane.showMessageDialog(null, "Usuario activado con éxito");
								return;
							}
							JOptionPane.showMessageDialog(null, "No se pudo activar el usuario");
						}
					}
				}

				Object[] options = { "Editar", "Ver constancias" };
				int option = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?", "Acción",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				// Handle the selected option
				if (option == 0) {
					if (filaSeleccionada != -1) {

						String documento = (String) tableEstudiantes.getValueAt(filaSeleccionada, 0);
						String nombres = (String) tableEstudiantes.getValueAt(filaSeleccionada, 1);
						String apellidos = (String) tableEstudiantes.getValueAt(filaSeleccionada, 2);
						String mailPersonal = (String) tableEstudiantes.getValueAt(filaSeleccionada, 3);
						String mailInstitucional = (String) tableEstudiantes.getValueAt(filaSeleccionada, 4);
						String genero = (String) tableEstudiantes.getValueAt(filaSeleccionada, 5);
						String departamento = (String) tableEstudiantes.getValueAt(filaSeleccionada, 6);
						String localidad = (String) tableEstudiantes.getValueAt(filaSeleccionada, 7);
						String itr = (String) tableEstudiantes.getValueAt(filaSeleccionada, 8);
						String telefono = (String) tableEstudiantes.getValueAt(filaSeleccionada, 9);
						String generacion = (String) tableEstudiantes.getValueAt(filaSeleccionada, 10);
						String semestre = (String) tableEstudiantes.getValueAt(filaSeleccionada, 11);
						String fechaSeleccionada = (String) tableEstudiantes.getValueAt(filaSeleccionada, 12);

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

						textFieldDocumento.setText(documento);
						textFieldNombres.setText(nombres);
						textFieldApellidos.setText(apellidos);
						textFieldMailPersonal.setText(mailPersonal);
						textFieldMailInstitucional.setText(mailInstitucional);
						comboBoxGenero.setSelectedItem(genero);
						comboBoxDepartamento.setSelectedItem(departamento);
						comboBoxLocalidad.setSelectedItem(localidad);
						comboBoxITR.setSelectedItem(itr);
						textFieldTelefono.setText(telefono);
						comboBoxGeneracion.setSelectedItem(generacion);
						comboBoxSemestre.setSelectedItem(semestre);
						dateChooserFechaNacimiento.setDate(fechaNacimiento);
						textFieldMailInstitucional.setEnabled(false);
						textFieldDocumento.setEnabled(false);

					}
				} else if (option == 1) {
					String documento = (String) tableEstudiantes.getValueAt(filaSeleccionada, 0);
					ConstanciasEstudiante oConstanciaEstudiante = new ConstanciasEstudiante(
							Buscar.estudianteFiltro(documento, "Documento").get(0));
					oConstanciaEstudiante.setLocationRelativeTo(null);
					oConstanciaEstudiante.setVisible(true);
				}

			}
		});
		tableEstudiantes.setModel(new TableModelEstudiante(Buscar.estudiantesActivos("S")));
		scrollPane.setViewportView(tableEstudiantes);

		panelBuscador = new JPanel();
		panelBuscador.setLayout(null);
		panelBuscador.setBounds(10, 332, 778, 75);
		panelBuscador.setOpaque(false);
		panelPrincipal.add(panelBuscador);

		lblTituloBuscar = new JLabel("Buscar:");
		lblTituloBuscar.setFont(new Font("Cambria", Font.BOLD, 15));
		lblTituloBuscar.setBounds(24, 11, 61, 20);
		panelBuscador.add(lblTituloBuscar);

		textFieldBuscador = new JTextField();
		textFieldBuscador.setFont(new Font("Cambria", Font.PLAIN, 12));
		textFieldBuscador.setColumns(10);
		textFieldBuscador.setBounds(109, 12, 145, 19);
		panelBuscador.add(textFieldBuscador);

		comboBoxFiltro = new JComboBox();
		comboBoxFiltro.setModel(new DefaultComboBoxModel(new String[] { "Filtro", "Nombre Completo", "Nombres",
				"Apellidos", "Documento", "Sin validar", "Itr", "Generación", "Semestre", "Eliminados", "Activos" }));
		comboBoxFiltro.setFont(new Font("Cambria", Font.PLAIN, 12));
		comboBoxFiltro.setBounds(278, 11, 130, 21);
		comboBoxFiltro.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object selectedItem = e.getItem();
					lblFiltroDescripcion.setText("");
					paraValidar = false;
					paraActivar = false;
					if (selectedItem.equals("Activos")) {
						tableEstudiantes.setModel(new TableModelEstudiante(Buscar.estudiantesActivos("S")));
						return;
					}

					if (selectedItem.equals("Eliminados")) {
						paraActivar = true;
						lblFiltroDescripcion.setText("Seleccione un estudiante para activar su cuenta.");
						tableEstudiantes.setModel(new TableModelEstudiante(Buscar.estudiantesActivos("N")));
						return;
					}

					if (selectedItem.equals("Sin validar")) {
						paraValidar = true;
						lblFiltroDescripcion.setText("Seleccione un estudiante para validar su cuenta.");
						tableEstudiantes.setModel(new TableModelEstudiante(Buscar.estudiantesSinConfirmar()));
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
						var lista = Buscar.estudianteITR(filtro);

						if (lista.size() == 0) {
							JOptionPane.showMessageDialog(null, "No se encontraron estudiantes para ese Itr",
									"Información", JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						TableModelEstudiante oModelEstudiante = new TableModelEstudiante(lista);
						tableEstudiantes.setModel(oModelEstudiante);
						return;
					}

					if (campo.equals("Generación")) {
						var lista = Buscar.estudianteGeneracion(filtro);

						if (lista.size() == 0) {
							JOptionPane.showMessageDialog(null, "No se encontraron estudiantes para esa generación",
									"Información", JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						TableModelEstudiante oModelEstudiante = new TableModelEstudiante(lista);
						tableEstudiantes.setModel(oModelEstudiante);
						return;
					}

					if (campo.equals("Semestre")) {
						var lista = Buscar.estudianteSemestre(filtro);

						if (lista.size() == 0) {
							JOptionPane.showMessageDialog(null, "No se encontraron estudiantes para ese semestre",
									"Información", JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						TableModelEstudiante oModelEstudiante = new TableModelEstudiante(lista);
						tableEstudiantes.setModel(oModelEstudiante);
						return;
					}

					var lista = Buscar.estudianteFiltro(filtro, campo);
					if (lista.size() == 0) {
						JOptionPane.showMessageDialog(null, "No se encontraron resultados.", "Información",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					TableModelEstudiante oModelEstudiante = new TableModelEstudiante(lista);
					tableEstudiantes.setModel(oModelEstudiante);
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
				listarEstudiantes();
			}
		});
		btnListarTodo.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnListarTodo.setBounds(650, 11, 104, 21);
		panelBuscador.add(btnListarTodo);

		lblFiltroDescripcion.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		lblFiltroDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltroDescripcion.setBounds(146, 46, 486, 25);
		panelBuscador.add(lblFiltroDescripcion);

		separator1 = new JSeparator();
		separator1.setForeground(Color.decode("#44bcf4"));
		;
		separator1.setBounds(0, -1, 778, 2);
		panelBuscador.add(separator1);

		separator2 = new JSeparator();
		separator2.setForeground(Color.decode("#44bcf4"));
		;
		separator2.setBounds(0, 41, 778, 2);
		panelBuscador.add(separator2);

		comboBoxGeneracion = new JComboBox();
		comboBoxGeneracion.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxGeneracion.setModel(
				new DefaultComboBoxModel(new String[] { "Generación", "2019", "2020", "2021", "2022", "2023" }));
		comboBoxGeneracion.setBounds(81, 199, 151, 21);
		panelForm.add(comboBoxGeneracion);

		comboBoxSemestre = new JComboBox();
		comboBoxSemestre.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxSemestre.setModel(
				new DefaultComboBoxModel(new String[] { "Semestre", "1", "2", "3", "4", "5", "6", "7", "8" }));
		comboBoxSemestre.setBounds(313, 199, 151, 21);
		panelForm.add(comboBoxSemestre);

		dateChooserFechaNacimiento = new JDateChooser();
		dateChooserFechaNacimiento.setBounds(545, 115, 151, 19);
		dateChooserFechaNacimiento.setFont(new Font("Cambria", Font.PLAIN, 15));
		panelForm.add(dateChooserFechaNacimiento);

		panelPrincipal.add(imagenFondoContent);

	}

	public void limpiarCampos() {
		comboBoxGeneracion.setSelectedIndex(0);
		comboBoxSemestre.setSelectedIndex(0);
		comboBoxDepartamento.setSelectedIndex(0);
		comboBoxGenero.setSelectedIndex(0);
		comboBoxITR.setSelectedIndex(0);
		comboBoxLocalidad.setSelectedIndex(0);
		textFieldApellidos.setText(null);
		textFieldDocumento.setText(null);
		textFieldMailInstitucional.setText(null);
		textFieldMailPersonal.setText(null);
		textFieldNombres.setText(null);
		textFieldTelefono.setText(null);
		textFieldMailInstitucional.setEnabled(true);
		textFieldDocumento.setEnabled(true);
		dateChooserFechaNacimiento.setDate(null);
	}

	public void limpiarCamposBuscador() {
		comboBoxFiltro.setSelectedIndex(0);
		textFieldBuscador.setText(null);
		lblFiltroDescripcion.setText(null);
		paraValidar = false;
	}

	public void listarEstudiantes() {
		tableEstudiantes.setModel(new TableModelEstudiante(ServiceEstudiante.listarEstudiantes()));
	}

}
