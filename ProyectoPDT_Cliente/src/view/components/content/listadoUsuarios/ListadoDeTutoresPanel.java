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

import com.entities.Area;
import com.entities.Departamento;
import com.entities.Genero;
import com.entities.Itr;
import com.entities.Localidad;
import com.entities.Rol;
import com.entities.Tutor;
import com.entities.Usuario;
import com.toedter.calendar.JDateChooser;

import context.Fabrica;
import context.helpers.Actualizar;
import context.helpers.Borrar;
import context.helpers.Buscar;
import services.ServiceArea;
import services.ServiceEmail;
import services.ServiceGenero;
import services.ServiceItr;
import services.ServiceRol;
import services.ServiceTutor;
import services.ServiceUbicacion;
import services.ServiceUsuario;
import view.components.content.section.ListadoDeUsuariosPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelTutor;
import view.validaciones.ValidacionTutor;
import view.validaciones.ValidacionUsuario;

public class ListadoDeTutoresPanel extends JPanel {

	// JPanel
	private JPanel panelPrincipal;
	private JPanel panelForm;
	private JPanel panelBuscador;

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

	// JButton
	private JButton buttonModificar;
	private JButton buttonEliminar;
	private JButton buttonLimpiarCampos;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JButton btnListarTodo;
	private JButton buttonRegresar;

	// JTextField
	private JTextField textFieldNombres;
	private JTextField textFieldApellidos;
	private JTextField textFieldCedula;
	private JTextField textFieldTelefono;
	private JTextField textFieldMailPersonal;
	private JTextField textFieldMailInstitucional;
	private JTextField textFieldBuscador;

	// JComboBox
	private JComboBox comboBoxGenero;
	private JComboBox comboBoxDepartamento;
	private JComboBox comboBoxArea;
	private JComboBox comboBoxFiltro;
	private JComboBox comboBoxRol;
	private JComboBox comboBoxLocalidad;
	private JComboBox comboBoxITR;

	// JTable y JScrollPane
	private JTable tableTutores;
	private JScrollPane scrollPaneTabla;

	// JDateChooser
	private JDateChooser dateChooserFechaNacimiento;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// Listas
	private ArrayList<Genero> listaDeGeneros = ServiceGenero.listarGeneros();
	private ArrayList<Itr> listaDeItrs = Buscar.itrsActivos("S");
	private ArrayList<Departamento> listaDeDepartamentos = ServiceUbicacion.listarDepartamentos();
	private ArrayList<Area> listaDeAreas = ServiceArea.listarAreas();

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	// Extras
	private boolean paraValidar = false;
	private boolean paraActivar = false;
	private String departamento = null;

	public ListadoDeTutoresPanel(MostrarPanel panel) {
		setLayout(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		panelPrincipal.setBounds(0, 0, 798, 550);
		add(panelPrincipal);

		lblListadoDeEstudiantes = new JLabel("Listado de Tutores");
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

		textFieldCedula = new JTextField();
		textFieldCedula.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldCedula.setColumns(10);
		textFieldCedula.setBounds(404, 48, 151, 19);
		panelForm.add(textFieldCedula);

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

		dateChooserFechaNacimiento = new JDateChooser();
		dateChooserFechaNacimiento.setBounds(545, 115, 151, 19);
		dateChooserFechaNacimiento.setFont(new Font("Cambria", Font.PLAIN, 15));
		panelForm.add(dateChooserFechaNacimiento);

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
			@Override
			public void mouseClicked(MouseEvent e) {
				// Consigo los index de los comboBox
				int comboIndexDepartamento = comboBoxDepartamento.getSelectedIndex();
				int comboIndexGenero = comboBoxGenero.getSelectedIndex();
				int comboIndexLocalidad = comboBoxLocalidad.getSelectedIndex();
				int comboIndexItr = comboBoxITR.getSelectedIndex();
				int comboIndexRol = comboBoxRol.getSelectedIndex();
				int comboIndexArea = comboBoxArea.getSelectedIndex();

				// Verifico si son != 0
				boolean comboBoxesCompletos = ValidacionTutor.validarComboBoxesTutor(comboIndexDepartamento,
						comboIndexGenero, comboIndexLocalidad, comboIndexItr, comboIndexRol, comboIndexArea);

				if (!comboBoxesCompletos) {
					return;
				}

				// Obtengo los datos desde el form
				try {

					String nombres[] = textFieldNombres.getText().split(" ");
					String apellidos[] = textFieldApellidos.getText().split(" ");

					String primerNombre = nombres.length > 0 ? nombres[0] : "";
					String segundoNombre = nombres.length > 1 ? nombres[1] : "";

					String primerApellido = apellidos.length > 0 ? apellidos[0] : "";
					String segundoApellido = apellidos.length > 1 ? apellidos[1] : "";

					BigDecimal cedula = new BigDecimal(textFieldCedula.getText());

					String mailPersonal = textFieldMailPersonal.getText();

					String telefono = textFieldTelefono.getText();

					Rol rol = ServiceRol.listarRolesFiltro(comboBoxRol.getSelectedItem().toString()).get(0);
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

					var tutorAntiguo = Buscar.tutorFiltro(String.valueOf(cedula), "Documento").get(0);

					// Valido los datos
					boolean datosValidos = ValidacionUsuario.validarUnUsuario(tutorAntiguo.getUsuario().getClave(),
							String.valueOf(cedula), fechaNacimiento, tutorAntiguo.getUsuario().getMailInstitucional(),
							mailPersonal, primerApellido, primerNombre, segundoApellido, segundoNombre, telefono,
							"funcionario");

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

					// Creo una instancia para poder actualizar la entidad en la bd
					Usuario oUsuarioNuevo = new Usuario(tutorAntiguo.getUsuario().getClave(), cedula, fechaNacimiento,
							tutorAntiguo.getUsuario().getMailInstitucional(), mailPersonal,
							tutorAntiguo.getUsuario().getNombreUsuario(), primerApellido, primerNombre, segundoApellido,
							segundoNombre, telefono, tutorAntiguo.getUsuario().getActivo(),
							tutorAntiguo.getUsuario().getConfirmado(), departamento, genero, itr, localidad, rol);

					oUsuarioNuevo.setIdUsuario(tutorAntiguo.getUsuario().getIdUsuario());

					Area oArea = ServiceArea.listarAreasFiltro(comboBoxArea.getSelectedItem().toString()).get(0);

					// Creo una instancia para poder actualizar la entidad en la bd
					Tutor oTutorNuevo = new Tutor(oArea, oUsuarioNuevo);
					oTutorNuevo.setIdTutor(tutorAntiguo.getIdTutor());

					boolean oTutorCreado = Actualizar.usuario(oUsuarioNuevo, oTutorNuevo);

					if (oTutorCreado) {
						limpiarCampos();
						Fabrica.cargarListas();
						listarTutores();
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
				if (textFieldCedula.getText().length() <= 5) {
					JOptionPane.showMessageDialog(null, "Seleccione un tutor de la lista");
					return;
				}
				try {
					int confirmar = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de que desea eliminar este usuario?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);
					if (confirmar == 0) {
						var usuarioAEliminar = Buscar.tutorFiltro(textFieldCedula.getText(), "Documento").get(0);
						boolean respuesta = Borrar.darBajaLogica(usuarioAEliminar.getUsuario());
						if (respuesta) {
							limpiarCampos();
							Fabrica.cargarListas();
							listarTutores();
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
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
			}
		});
		buttonLimpiarCampos.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonLimpiarCampos.setBounds(604, 242, 136, 21);
		panelForm.add(buttonLimpiarCampos);

		String[] areaStrings = new String[listaDeAreas.size() + 1];
		areaStrings[0] = "Elige un área";

		for (int i = 0; i < listaDeAreas.size(); i++) {
			areaStrings[i + 1] = listaDeAreas.get(i).getDescripcion();
		}
		DefaultComboBoxModel<String> comboBoxModelArea = new DefaultComboBoxModel<>(areaStrings);

		comboBoxArea = new JComboBox(comboBoxModelArea);
		comboBoxArea.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxArea.setBounds(81, 199, 151, 21);
		panelForm.add(comboBoxArea);

		comboBoxRol = new JComboBox();
		comboBoxRol.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxRol.setModel(new DefaultComboBoxModel(new String[] { "Rol", "Encargado", "Tutor" }));
		comboBoxRol.setBounds(313, 199, 151, 21);
		panelForm.add(comboBoxRol);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(10, 407, 778, 133);
		panelPrincipal.add(scrollPaneTabla);

		tableTutores = new JTable();
		tableTutores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int filaSeleccionada = tableTutores.getSelectedRow();

				if (paraValidar) {

					if (filaSeleccionada != -1) {
						String documento = (String) tableTutores.getValueAt(filaSeleccionada, 0);

						Usuario oUsuarioConfirmado = Buscar.tutorFiltro(documento, "Documento").get(0).getUsuario();

						int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea confirmar el usuario?");

						if (aceptar == 0) {
							oUsuarioConfirmado.setConfirmado("S");
							var usuarioActualizado = ServiceUsuario.actualizarUsuario(oUsuarioConfirmado);
							if (usuarioActualizado != null) {
								tableTutores.setModel(new TableModelTutor(Buscar.tutoresSinConfirmar()));
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
						String documento = (String) tableTutores.getValueAt(filaSeleccionada, 0);

						Usuario oUsuarioActivado = Buscar.tutorFiltro(documento, "Documento").get(0).getUsuario();

						int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea activar el usuario?");

						if (aceptar == 0) {
							oUsuarioActivado.setActivo("S");
							var usuarioActualizado = ServiceUsuario.actualizarUsuario(oUsuarioActivado);
							if (usuarioActualizado != null) {
								tableTutores.setModel(new TableModelTutor(Buscar.listarTutoresActivos("N")));
								JOptionPane.showMessageDialog(null, "Usuario activado con éxito");
								return;
							}
							JOptionPane.showMessageDialog(null, "No se pudo activar el usuario");
						}
					}
				}
				if (filaSeleccionada != -1) {

					String documento = (String) tableTutores.getValueAt(filaSeleccionada, 0);
					String nombres = (String) tableTutores.getValueAt(filaSeleccionada, 1);
					String apellidos = (String) tableTutores.getValueAt(filaSeleccionada, 2);
					String mailPersonal = (String) tableTutores.getValueAt(filaSeleccionada, 3);
					String mailInstitucional = (String) tableTutores.getValueAt(filaSeleccionada, 4);
					String genero = (String) tableTutores.getValueAt(filaSeleccionada, 5);
					String departamento = (String) tableTutores.getValueAt(filaSeleccionada, 6);
					String localidad = (String) tableTutores.getValueAt(filaSeleccionada, 7);
					String itr = (String) tableTutores.getValueAt(filaSeleccionada, 8);
					String telefono = (String) tableTutores.getValueAt(filaSeleccionada, 9);
					String area = (String) tableTutores.getValueAt(filaSeleccionada, 10);
					String rol = (String) tableTutores.getValueAt(filaSeleccionada, 11);
					String fechaSeleccionada = (String) tableTutores.getValueAt(filaSeleccionada, 12);

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
					comboBoxArea.setSelectedItem(area);
					comboBoxRol.setSelectedItem(rol);
					dateChooserFechaNacimiento.setDate(fechaNacimiento);
					textFieldMailInstitucional.setEnabled(false);
					textFieldCedula.setEnabled(false);

				}
			}
		});
		tableTutores.setModel(new TableModelTutor(Buscar.listarTutoresActivos("S")));
		scrollPaneTabla.setViewportView(tableTutores);

		panelBuscador = new JPanel();
		panelBuscador.setLayout(null);
		panelBuscador.setBounds(10, 332, 778, 76);
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
				"Apellidos", "Documento", "Sin validar", "Itr", "Eliminados", "Activos" }));
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
						tableTutores.setModel(new TableModelTutor(Buscar.listarTutoresActivos("S")));
						return;
					}

					if (selectedItem.equals("Eliminados")) {
						paraActivar = true;
						lblFiltroDescripcion.setText("Seleccione un tutor para activar su cuenta.");
						tableTutores.setModel(new TableModelTutor(Buscar.listarTutoresActivos("N")));
						return;
					}

					if (selectedItem.equals("Sin validar")) {
						paraValidar = true;
						lblFiltroDescripcion.setText("Seleccione un tutor para validar su cuenta.");
						tableTutores.setModel(new TableModelTutor(Buscar.tutoresSinConfirmar()));
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

					if (campo.equals("Rol")) {
						var lista = Buscar.tutorPorRol(filtro);

						if (lista.size() == 0) {
							JOptionPane.showMessageDialog(null, "No se encontraron tutores con ese rol", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						TableModelTutor oModelTutor = new TableModelTutor(lista);
						tableTutores.setModel(oModelTutor);
						return;
					}
					if (campo.equals("Itr")) {
						var lista = Buscar.tutoresITR(filtro);

						if (lista.size() == 0) {
							JOptionPane.showMessageDialog(null, "No se encontraron tutores para esa Itr", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						TableModelTutor oModelTutor = new TableModelTutor(lista);
						tableTutores.setModel(oModelTutor);
						return;
					}
					var lista = Buscar.tutorFiltro(filtro, campo);

					if (lista.size() == 0) {
						JOptionPane.showMessageDialog(null, "No se encontraron resultados.", "Información",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					TableModelTutor oModelTutor = new TableModelTutor(lista);
					tableTutores.setModel(oModelTutor);
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
				listarTutores();
				limpiarCamposBuscador();
			}
		});
		btnListarTodo.setFont(new Font("Cambria", Font.PLAIN, 12));
		btnListarTodo.setBounds(650, 11, 104, 21);
		panelBuscador.add(btnListarTodo);

		lblFiltroDescripcion.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		lblFiltroDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltroDescripcion.setBounds(146, 46, 486, 25);
		panelBuscador.add(lblFiltroDescripcion);

		separator2 = new JSeparator();
		separator2.setForeground(Color.decode("#44bcf4"));
		separator2.setBounds(0, 41, 778, 2);
		panelBuscador.add(separator2);

		separator1 = new JSeparator();
		separator1.setForeground(Color.decode("#44bcf4"));
		;
		separator1.setBounds(0, 278, 778, 2);
		panelForm.add(separator1);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new ListadoDeUsuariosPanel(panel), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelPrincipal.add(buttonRegresar);

		panelPrincipal.add(imagenFondoContent);

	}

	public void limpiarTabla() {
		tableTutores.setModel(new TableModelTutor(new ArrayList<Tutor>()));
	}

	public void listarTutores() {
		tableTutores.setModel(new TableModelTutor(ServiceTutor.listarTutores()));
	}

	public void limpiarCampos() {
		comboBoxArea.setSelectedIndex(0);
		comboBoxDepartamento.setSelectedIndex(0);
		comboBoxGenero.setSelectedIndex(0);
		comboBoxITR.setSelectedIndex(0);
		comboBoxLocalidad.setSelectedIndex(0);
		comboBoxRol.setSelectedIndex(0);
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

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}

}
