package view.components.content.profile;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.entities.Analista;
import com.entities.Departamento;
import com.entities.Genero;
import com.entities.Itr;
import com.entities.Localidad;
import com.entities.Rol;
import com.entities.Usuario;
import com.toedter.calendar.JDateChooser;

import context.Fabrica;
import context.helpers.Actualizar;
import context.helpers.Buscar;
import services.ServiceGenero;
import services.ServiceItr;
import services.ServiceRol;
import services.ServiceUbicacion;
import view.components.content.firmaElectronica.CargarFirmaElectronicaFrame;
import view.components.content.home.BienvenidoPanel;
import view.components.nav.MenuAnalista;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.validaciones.ValidacionAnalista;
import view.validaciones.ValidacionUsuario;

public class MiPerfilAnalistaPanel extends JPanel {

	// JPanel
	private JPanel panelContent;
	private JPanel panelForm;

	// JLabel
	private JLabel labelMiPerfilTitulo;
	private JLabel labelNombres;
	private JLabel labelApellidos;
	private JLabel labelCedula;
	private JLabel lblTelefono;
	private JLabel labelMailPersonal;
	private JLabel labelMailInstitucional;
	private JLabel lblClave;
	private JLabel lblFechaDeNacimiento;

	// JTextField
	private JTextField textFieldNombres;
	private JTextField textFieldApellidos;
	private JTextField textFieldCedula;
	private JTextField textFieldTelefono;
	private JTextField textFieldMailPersonal;
	private JTextField textFieldMailInstitucional;

	// JPasswordField
	private JPasswordField passwordFieldClave;

	// JDateChooser
	private JDateChooser dateChooserFechaNacimiento;

	// JComboBox
	private JComboBox comboBoxDepartamento;
	private JComboBox comboBoxLocalidad;
	private JComboBox comboBoxITR;
	private JComboBox comboBoxGenero;

	// JButton
	private JButton buttonRegresar;
	private JButton buttonModificar;
	private JButton btnCambiarFirma;

	// Listas
	private ArrayList<Genero> listaDeGeneros = ServiceGenero.listarGeneros();
	private ArrayList<Itr> listaDeItrs = Buscar.itrsActivos("S");
	private ArrayList<Departamento> listaDeDepartamentos = ServiceUbicacion.listarDepartamentos();

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	// Extras
	private Usuario oUsuarioLogueado;
	private String departamento = null;

	// Método para cargar datos del usuario en los textField y comboBoxes
	private void cargarDatos() {
		oUsuarioLogueado = Fabrica.getoUsuarioLogueado();
		textFieldNombres.setText(oUsuarioLogueado.getNombres());
		textFieldApellidos.setText(oUsuarioLogueado.getApellidos());
		textFieldCedula.setText(String.valueOf(oUsuarioLogueado.getDocumento()));
		textFieldTelefono.setText(oUsuarioLogueado.getTelefono());
		textFieldMailPersonal.setText(oUsuarioLogueado.getMailPersonal());
		textFieldMailInstitucional.setText(oUsuarioLogueado.getMailInstitucional());
		passwordFieldClave.setText(oUsuarioLogueado.getClave());
		dateChooserFechaNacimiento.setDate(oUsuarioLogueado.getFechaNacimiento());
		comboBoxDepartamento.setSelectedItem(oUsuarioLogueado.getDepartamento().getNombre());
		comboBoxLocalidad.setSelectedItem(oUsuarioLogueado.getLocalidad().getNombre());
		comboBoxITR.setSelectedItem(oUsuarioLogueado.getItr().getNombre());
		comboBoxGenero.setSelectedItem(oUsuarioLogueado.getGenero().getNombre());
	}

	public MiPerfilAnalistaPanel(MostrarPanel panel) {

		setLayout(null);

		panelContent = new JPanel();
		panelContent.setBounds(0, 0, 798, 550);
		add(panelContent);
		panelContent.setLayout(null);
		panelContent.setBackground(Color.decode("#828588"));

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new BienvenidoPanel(), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelContent.add(buttonRegresar);

		labelMiPerfilTitulo = new JLabel("Mi Perfil");
		labelMiPerfilTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelMiPerfilTitulo.setFont(new Font("Rockwell", Font.BOLD, 40));
		labelMiPerfilTitulo.setBounds(156, 67, 486, 36);
		panelContent.add(labelMiPerfilTitulo);

		panelForm = new JPanel();
		panelForm.setLayout(null);
		panelForm.setBounds(0, 164, 798, 252);
		panelForm.setOpaque(false);
		panelContent.add(panelForm);

		labelNombres = new JLabel("Nombres");
		labelNombres.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelNombres.setBounds(38, 26, 151, 20);
		panelForm.add(labelNombres);

		textFieldNombres = new JTextField();
		textFieldNombres.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldNombres.setColumns(10);
		textFieldNombres.setBounds(38, 51, 151, 19);
		panelForm.add(textFieldNombres);

		labelApellidos = new JLabel("Apellidos");
		labelApellidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelApellidos.setBounds(227, 26, 151, 20);
		panelForm.add(labelApellidos);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(227, 51, 151, 19);
		panelForm.add(textFieldApellidos);

		labelCedula = new JLabel("Cédula");
		labelCedula.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelCedula.setBounds(416, 26, 151, 20);
		panelForm.add(labelCedula);

		textFieldCedula = new JTextField();
		textFieldCedula.setEnabled(false);
		textFieldCedula.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldCedula.setColumns(10);
		textFieldCedula.setBounds(416, 51, 151, 19);
		panelForm.add(textFieldCedula);

		lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblTelefono.setBounds(605, 26, 151, 20);
		panelForm.add(lblTelefono);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(605, 51, 151, 19);
		panelForm.add(textFieldTelefono);

		labelMailPersonal = new JLabel("Mail Personal");
		labelMailPersonal.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelMailPersonal.setBounds(38, 93, 151, 20);
		panelForm.add(labelMailPersonal);

		textFieldMailPersonal = new JTextField();
		textFieldMailPersonal.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldMailPersonal.setColumns(10);
		textFieldMailPersonal.setBounds(38, 118, 151, 19);
		panelForm.add(textFieldMailPersonal);

		labelMailInstitucional = new JLabel("Mail Institucional");
		labelMailInstitucional.setFont(new Font("Cambria", Font.PLAIN, 15));
		labelMailInstitucional.setBounds(227, 94, 151, 18);
		panelForm.add(labelMailInstitucional);

		textFieldMailInstitucional = new JTextField();
		textFieldMailInstitucional.setEnabled(false);
		textFieldMailInstitucional.setFont(new Font("Cambria", Font.PLAIN, 15));
		textFieldMailInstitucional.setColumns(10);
		textFieldMailInstitucional.setBounds(227, 118, 151, 19);
		panelForm.add(textFieldMailInstitucional);

		lblClave = new JLabel("Contraseña");
		lblClave.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblClave.setBounds(416, 93, 151, 20);
		panelForm.add(lblClave);

		passwordFieldClave = new JPasswordField();
		passwordFieldClave.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {

				passwordFieldClave.setEchoChar((char) 0);

			}

			public void focusLost(FocusEvent e) {

				passwordFieldClave.setEchoChar('\u2022');

			}
		});
		passwordFieldClave.setFont(new Font("Cambria", Font.PLAIN, 15));
		passwordFieldClave.setBounds(416, 118, 151, 19);
		panelForm.add(passwordFieldClave);

		lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblFechaDeNacimiento.setBounds(605, 93, 151, 20);
		panelForm.add(lblFechaDeNacimiento);

		dateChooserFechaNacimiento = new JDateChooser();
		dateChooserFechaNacimiento.setFont(new Font("Cambria", Font.PLAIN, 15));
		dateChooserFechaNacimiento.setBounds(605, 118, 151, 19);
		panelForm.add(dateChooserFechaNacimiento);

		String[] generoStrings = new String[listaDeGeneros.size() + 1];
		generoStrings[0] = "Elige un género";
		for (int i = 0; i < listaDeGeneros.size(); i++) {
			generoStrings[i + 1] = listaDeGeneros.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelGenero = new DefaultComboBoxModel<>(generoStrings);

		comboBoxGenero = new JComboBox(comboBoxModelGenero);
		comboBoxGenero.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxGenero.setBounds(38, 160, 151, 21);
		panelForm.add(comboBoxGenero);

		String[] itrsStrings = new String[listaDeItrs.size() + 1];
		itrsStrings[0] = "Elige un ITR";
		for (int i = 0; i < listaDeItrs.size(); i++) {
			itrsStrings[i + 1] = listaDeItrs.get(i).getNombre();
		}
		DefaultComboBoxModel<String> comboBoxModelItr = new DefaultComboBoxModel<>(itrsStrings);

		comboBoxITR = new JComboBox(comboBoxModelItr);
		comboBoxITR.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxITR.setBounds(227, 160, 151, 21);
		panelForm.add(comboBoxITR);

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
		comboBoxDepartamento.setBounds(416, 160, 151, 21);
		panelForm.add(comboBoxDepartamento);

		String[] localidadStrings = new String[1];
		localidadStrings[0] = "Elige un localidad";
		DefaultComboBoxModel<String> comboBoxModelLocalidad = new DefaultComboBoxModel<>(localidadStrings);

		comboBoxLocalidad = new JComboBox(comboBoxModelLocalidad);
		comboBoxLocalidad.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxLocalidad.setBounds(605, 160, 151, 21);
		comboBoxLocalidad.setEnabled(false);
		panelForm.add(comboBoxLocalidad);

		btnCambiarFirma = new JButton("¿Desea cambiar su firma electrónica?");
		btnCambiarFirma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				CargarFirmaElectronicaFrame cargarFirmaElectronicaFrame = new CargarFirmaElectronicaFrame(Buscar
						.analistaFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento").get(0),
						true);
				cargarFirmaElectronicaFrame.setLocationRelativeTo(null);
				cargarFirmaElectronicaFrame.setVisible(true);

			}
		});
		btnCambiarFirma.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnCambiarFirma.setBounds(256, 202, 285, 25);
		panelForm.add(btnCambiarFirma);

		buttonModificar = new JButton("Editar");
		buttonModificar.addMouseListener(new MouseAdapter() {
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

					// Valida si el campo cedula está vacío o tiene espacios
					if (textFieldCedula.getText().trim().equals("")
							|| !ValidacionUsuario.esUnNumero(textFieldCedula.getText())) {
						JOptionPane.showMessageDialog(null,
								"Error: La cédula debe tener 8 dígitos numéricos sin puntos ni guiones.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					BigDecimal cedula = new BigDecimal(textFieldCedula.getText());
					String mailPersonal = textFieldMailPersonal.getText();
					String mailInstitucional = textFieldMailInstitucional.getText();
					String clave = passwordFieldClave.getText();
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
					var datosAntiguos = Buscar.analistaFiltro(String.valueOf(cedula), "Documento").get(0);

					// Valido los datos
					boolean datosValidos = ValidacionUsuario.validarUnUsuario(clave, String.valueOf(cedula),
							fechaNacimiento, mailInstitucional, mailPersonal, primerApellido, primerNombre,
							segundoApellido, segundoNombre, telefono, "funcionario");

					if (!datosValidos) {
						return;
					}

					// NOTA IMPORTANTE: Al ser solo el evento de actualizar debemos setearle los
					// valores antiguos de activo y confirmado.
					// (El activo solo cambia con la accion de borrado y confirmado solo lo hace un
					// analista pero no puede desconfirmar )
					Usuario oUsuarioActualizado = new Usuario(clave, cedula, fechaNacimiento, mailInstitucional,
							mailPersonal, datosAntiguos.getUsuario().getNombreUsuario(), primerApellido, primerNombre,
							segundoApellido, segundoNombre, telefono, datosAntiguos.getUsuario().getActivo(),
							datosAntiguos.getUsuario().getConfirmado(), departamento, genero, itr, localidad, rol);

					// Le pongo el id que le corresponde al usuario
					oUsuarioActualizado.setIdUsuario(datosAntiguos.getUsuario().getIdUsuario());

					int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea editar sus datos?",
							"Confirmar edición", JOptionPane.YES_NO_OPTION);

					if (confirmacion != 0) {
						cargarDatos();
						return;
					}

					// Actualizo los datos
					// Nota: como analista no tiene datos adiccionales a los que "hereda" de Usuario
					// solamente debemos actualizar el usuario
					var oUsuarioEditado = Actualizar.usuario(oUsuarioActualizado,
							new Analista(datosAntiguos.getIdAnalista()));

					if (oUsuarioEditado) {
						// Si todo sale bien limpiamos los campos y actualizamos las listas
						Fabrica.setoUsuarioLogueado(
								Fabrica.buscarUsuarioPorCampoYFiltro(String.valueOf(cedula), "Documento").get(0));
						cargarDatos();
						JOptionPane.showMessageDialog(null, "Su perfil se ha editado exitosamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						return;

					} else {
						// Vino null del servidor
						JOptionPane.showMessageDialog(null,
								"No se pudo editar su perfil.\nPor favor, inténtelo de nuevo más tarde.", "Error",
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
		buttonModificar.setBounds(356, 459, 85, 25);
		panelContent.add(buttonModificar);
		buttonModificar.setFont(new Font("Cambria", Font.PLAIN, 15));

		panelContent.add(imagenFondoContent);

		cargarDatos();

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
