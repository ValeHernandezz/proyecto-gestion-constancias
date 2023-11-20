package view.components.content.auth.users;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;

import com.entities.Analista;
import com.entities.Area;
import com.entities.Departamento;
import com.entities.Genero;
import com.entities.Itr;
import com.entities.Localidad;
import com.entities.Rol;
import com.entities.Tutor;
import com.entities.Usuario;

import context.Fabrica;
import context.helpers.Buscar;
import context.helpers.Crear;
import services.ServiceAnalista;
import services.ServiceArea;
import services.ServiceEmail;
import services.ServiceGenero;
import services.ServiceItr;
import services.ServiceRol;
import services.ServiceTutor;
import services.ServiceUbicacion;
import view.components.content.auth.RegistrarPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.validaciones.ValidacionTutor;
import view.validaciones.ValidacionUsuario;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class TutorRegistroPanel extends JPanel {

	// JPanel
	private JPanel panelContent;
	private JPanel panelDatos;

	// JLabel
	private JLabel lblSubtituloTutor;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblCedula;
	private JLabel lblTelefono;
	private JLabel lblMailPersonal;
	private JLabel lblMailInstitucional;
	private JLabel lblClave;
	private JLabel lblFechaNacimiento;
	private JLabel lblObligatoriedadCampos;

	// JTextField
	private JTextField textFieldNombres;
	private JTextField textFieldApellidos;
	private JTextField textFieldCedula;
	private JTextField textFieldTelefono;
	private JTextField textFieldMailPersonal;
	private JTextField textFieldMailInstitucional;
	private JTextField textFieldClave;

	// JDateChooser
	private JDateChooser dateChooserFechaNacimiento;

	// JComboBox
	private JComboBox comboBoxGenero;
	private JComboBox comboBoxITR;
	private JComboBox comboBoxDepartamento;
	private JComboBox comboBoxLocalidad;
	private JComboBox comboBoxArea;
	private JComboBox comboBoxRol;

	// JButton
	private JButton buttonRegresar;
	private JButton btnSolicitarRegistro;

	// Listas
	private ArrayList<Genero> listaDeGeneros = ServiceGenero.listarGeneros();
	private ArrayList<Itr> listaDeItrs = Buscar.itrsActivos("S");
	private ArrayList<Departamento> listaDeDepartamentos = ServiceUbicacion.listarDepartamentos();
	private ArrayList<Area> listaDeAreas = ServiceArea.listarAreas();

	// Imágenes
	private ImagenesHelper imagenContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 1008, 468);

	// Extras
	private String departamento = null;

	public TutorRegistroPanel(MostrarPanel panel) {
		setLayout(null);

		panelContent = new JPanel();
		panelContent.setLayout(null);
		panelContent.setBounds(0, 0, 1008, 468);
		add(panelContent);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new RegistrarPanel(panel), 1028, 570));

			}
		});
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelContent.add(buttonRegresar);
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));

		lblSubtituloTutor = new JLabel("Tutor");
		lblSubtituloTutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtituloTutor.setFont(new Font("Rockwell", Font.BOLD, 30));
		lblSubtituloTutor.setBounds(70, 28, 867, 48);
		panelContent.add(lblSubtituloTutor);

		panelDatos = new JPanel();
		panelDatos.setLayout(null);
		panelDatos.setBounds(24, 94, 959, 364);
		panelDatos.setOpaque(false);
		panelContent.add(panelDatos);

		lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblNombres.setBounds(27, 25, 206, 20);
		panelDatos.add(lblNombres);

		textFieldNombres = new JTextField();
		textFieldNombres.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldNombres.setColumns(10);
		textFieldNombres.setBounds(27, 51, 206, 19);
		panelDatos.add(textFieldNombres);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblApellidos.setBounds(260, 25, 206, 20);
		panelDatos.add(lblApellidos);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(260, 51, 206, 19);
		panelDatos.add(textFieldApellidos);

		lblCedula = new JLabel("Cédula");
		lblCedula.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblCedula.setBounds(493, 25, 206, 20);
		panelDatos.add(lblCedula);

		textFieldCedula = new JTextField();
		textFieldCedula.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldCedula.setColumns(10);
		textFieldCedula.setBounds(493, 51, 206, 19);
		panelDatos.add(textFieldCedula);

		lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblTelefono.setBounds(726, 25, 206, 20);
		panelDatos.add(lblTelefono);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(726, 51, 206, 19);
		panelDatos.add(textFieldTelefono);

		lblMailPersonal = new JLabel("Mail Personal");
		lblMailPersonal.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblMailPersonal.setBounds(27, 104, 206, 20);
		panelDatos.add(lblMailPersonal);

		textFieldMailPersonal = new JTextField();
		textFieldMailPersonal.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldMailPersonal.setColumns(10);
		textFieldMailPersonal.setBounds(27, 130, 206, 19);
		panelDatos.add(textFieldMailPersonal);

		lblMailInstitucional = new JLabel("Mail Institucional");
		lblMailInstitucional.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblMailInstitucional.setBounds(260, 104, 206, 20);
		panelDatos.add(lblMailInstitucional);

		textFieldMailInstitucional = new JTextField();
		textFieldMailInstitucional.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldMailInstitucional.setColumns(10);
		textFieldMailInstitucional.setBounds(260, 130, 206, 19);
		panelDatos.add(textFieldMailInstitucional);

		lblClave = new JLabel("Contraseña");
		lblClave.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblClave.setBounds(493, 104, 206, 20);
		panelDatos.add(lblClave);

		textFieldClave = new JTextField();
		textFieldClave.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldClave.setColumns(10);
		textFieldClave.setBounds(493, 130, 206, 19);
		panelDatos.add(textFieldClave);

		lblFechaNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaNacimiento.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblFechaNacimiento.setBounds(726, 104, 206, 20);
		panelDatos.add(lblFechaNacimiento);

		dateChooserFechaNacimiento = new JDateChooser();
		dateChooserFechaNacimiento.setFont(new Font("Cambria", Font.PLAIN, 15));
		dateChooserFechaNacimiento.setBounds(726, 130, 206, 19);
		panelDatos.add(dateChooserFechaNacimiento);

		String[] generoStrings = new String[listaDeGeneros.size() + 1];
		generoStrings[0] = "Elige un género";
		for (int i = 0; i < listaDeGeneros.size(); i++) {
			generoStrings[i + 1] = listaDeGeneros.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelGenero = new DefaultComboBoxModel<>(generoStrings);

		comboBoxGenero = new JComboBox(comboBoxModelGenero);
		comboBoxGenero.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxGenero.setBounds(27, 183, 206, 21);
		panelDatos.add(comboBoxGenero);

		String[] itrsStrings = new String[listaDeItrs.size() + 1];
		itrsStrings[0] = "Elige un ITR";
		for (int i = 0; i < listaDeItrs.size(); i++) {
			itrsStrings[i + 1] = listaDeItrs.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelItr = new DefaultComboBoxModel<>(itrsStrings);

		comboBoxITR = new JComboBox(comboBoxModelItr);
		comboBoxITR.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxITR.setBounds(260, 183, 206, 21);
		panelDatos.add(comboBoxITR);

		String[] departamentoStrings = new String[listaDeDepartamentos.size() + 1];
		departamentoStrings[0] = "Elige un departamento";
		for (int i = 0; i < listaDeDepartamentos.size(); i++) {
			departamentoStrings[i + 1] = listaDeDepartamentos.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelDepartamento = new DefaultComboBoxModel<>(departamentoStrings);

		comboBoxDepartamento = new JComboBox(comboBoxModelDepartamento);
		comboBoxDepartamento.setFont(new Font("Cambria", Font.PLAIN, 15));
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
		comboBoxDepartamento.setBounds(493, 183, 206, 21);
		panelDatos.add(comboBoxDepartamento);

		String[] localidadStrings = new String[1];
		localidadStrings[0] = "Elige un localidad";
		DefaultComboBoxModel<String> comboBoxModelLocalidad = new DefaultComboBoxModel<>(localidadStrings);

		comboBoxLocalidad = new JComboBox(comboBoxModelLocalidad);
		comboBoxLocalidad.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxLocalidad.setBounds(726, 183, 206, 21);
		comboBoxLocalidad.setEnabled(false);
		panelDatos.add(comboBoxLocalidad);

		String[] areaStrings = new String[listaDeAreas.size() + 1];
		areaStrings[0] = "Elige un área";
		for (int i = 0; i < listaDeAreas.size(); i++) {
			areaStrings[i + 1] = listaDeAreas.get(i).getDescripcion();
		}
		DefaultComboBoxModel<String> comboBoxModelArea = new DefaultComboBoxModel<>(areaStrings);

		comboBoxArea = new JComboBox(comboBoxModelArea);
		comboBoxArea.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxArea.setBounds(260, 236, 206, 21);
		panelDatos.add(comboBoxArea);

		comboBoxRol = new JComboBox();
		comboBoxRol.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxRol.setModel(new DefaultComboBoxModel(new String[] { "Rol", "Encargado", "Tutor" }));
		comboBoxRol.setBounds(493, 236, 206, 21);
		panelDatos.add(comboBoxRol);

		btnSolicitarRegistro = new JButton("Solicitar Registro");
		btnSolicitarRegistro.setFont(new Font("Cambria", Font.PLAIN, 17));
		btnSolicitarRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int comboIndexDepartamento = comboBoxDepartamento.getSelectedIndex();
					int comboIndexGenero = comboBoxGenero.getSelectedIndex();
					int comboIndexLocalidad = comboBoxLocalidad.getSelectedIndex();
					int comboIndexItr = comboBoxITR.getSelectedIndex();
					int comboIndexRol = comboBoxRol.getSelectedIndex();
					int comboIndexArea = comboBoxArea.getSelectedIndex();

					boolean comboBoxesCompletos = ValidacionTutor.validarComboBoxesTutor(comboIndexDepartamento,
							comboIndexGenero, comboIndexLocalidad, comboIndexItr, comboIndexRol, comboIndexArea);

					if (!comboBoxesCompletos) {
						return;
					}

					String nombres[] = textFieldNombres.getText().split(" ");
					String apellidos[] = textFieldApellidos.getText().split(" ");

					String primerNombre = nombres.length > 0 ? nombres[0] : "";
					String segundoNombre = nombres.length > 1 ? nombres[1] : "";

					String primerApellido = apellidos.length > 0 ? apellidos[0] : "";
					String segundoApellido = apellidos.length > 1 ? apellidos[1] : "";

					// Valida si el campo cédula está vacío o tiene espacios
					if (textFieldCedula.getText().trim().equals("")
							|| !ValidacionUsuario.esUnNumero(textFieldCedula.getText())) {
						JOptionPane.showMessageDialog(null,
								"La cédula debe tener 8 dígitos numéricos sin puntos ni guiones.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Valida si la cédula ingresada existe o no en la base de datos
					if (ValidacionUsuario.existeLaCedula(textFieldCedula.getText())) {
						JOptionPane.showMessageDialog(null, "Ya existe un usuario con esa cédula.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					BigDecimal cedula = new BigDecimal(textFieldCedula.getText());
					String clave = textFieldClave.getText();
					String mailPersonal = textFieldMailPersonal.getText();
					String mailInstitucional = textFieldMailInstitucional.getText();
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

					boolean datosValidos = ValidacionUsuario.validarUnUsuario(clave, String.valueOf(cedula),
							fechaNacimiento, mailInstitucional, mailPersonal, primerApellido, primerNombre,
							segundoApellido, segundoNombre, telefono, "funcionario");

					if (!datosValidos) {
						return;
					}

					String nombreUsuario = Fabrica.generarNombreUsuario(mailInstitucional);

					Usuario oUsuarioNuevo = new Usuario(clave, cedula, fechaNacimiento, mailInstitucional, mailPersonal,
							nombreUsuario.toLowerCase(), primerApellido, primerNombre, segundoApellido, segundoNombre,
							telefono, "S", "N", departamento, genero, itr, localidad, rol);

					Area oArea = ServiceArea.listarAreasFiltro(comboBoxArea.getSelectedItem().toString()).get(0);

					Tutor oTutorNuevo = new Tutor(oArea);

					var oTutorCreado = Crear.usuario(oUsuarioNuevo, oTutorNuevo);

					if (oTutorCreado) {
						limpiarCampos();
						ServiceEmail.mandarMailRegistro(oUsuarioNuevo);
						JOptionPane.showMessageDialog(null, "El tutor ha solicitado exitosamente el registro.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido solicitar el registro del tutor.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error en la Base de Datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		lblObligatoriedadCampos = new JLabel("Todos los campos son obligatorios.");
		lblObligatoriedadCampos.setHorizontalAlignment(SwingConstants.CENTER);
		lblObligatoriedadCampos.setForeground(new Color(221, 0, 0));
		lblObligatoriedadCampos.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblObligatoriedadCampos.setBounds(194, 280, 570, 20);
		panelDatos.add(lblObligatoriedadCampos);
		btnSolicitarRegistro.setBounds(397, 314, 165, 25);
		panelDatos.add(btnSolicitarRegistro);

		// Agrega la imagen al panel indicado
		panelContent.add(imagenContent);

	}

	public void limpiarCampos() {
		textFieldNombres.setText(null);
		textFieldApellidos.setText(null);
		textFieldCedula.setText(null);
		textFieldTelefono.setText(null);
		textFieldMailPersonal.setText(null);
		textFieldMailInstitucional.setText(null);
		textFieldClave.setText(null);
		dateChooserFechaNacimiento.setDate(null);
		comboBoxGenero.setSelectedIndex(0);
		comboBoxITR.setSelectedIndex(0);
		comboBoxDepartamento.setSelectedIndex(0);
		comboBoxLocalidad.setSelectedIndex(0);
		comboBoxArea.setSelectedIndex(0);
		comboBoxRol.setSelectedIndex(0);
	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
