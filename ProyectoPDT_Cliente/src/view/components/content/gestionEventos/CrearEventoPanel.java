package view.components.content.gestionEventos;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.entities.Evento;
import com.entities.Tutor;
import com.toedter.calendar.JDateChooser;

import context.helpers.Buscar;
import context.helpers.Crear;
import services.ServiceEvento;
import view.components.content.section.GestionDeEventosAnalistaPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;

public class CrearEventoPanel extends JPanel {

	// JPanel
	private JPanel panelPrincipal;
	private JPanel panelForm;
	private JPanel panelAsignar;

	// JButton
	private JButton buttonRegresar;
	private JButton btnAsignarTutor;
	private JButton btnBuscar;
	private JButton btnCrearEvento;

	// JLabel
	private JLabel labelCrearEvento;
	private JLabel lblNombreEvento;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JLabel lblAsignarTutor;
	private JLabel lblNombreTutor;
	private JLabel lblDocumentoTutor;
	private JLabel lblTutoresAsignados;

	// JTextField
	private JTextField textFieldNombreEvento;
	private JTextField textFieldBuscar;

	// JDateChooser
	private JDateChooser dateChooserFechaInicio;
	private JDateChooser dateChooserFechaFin;

	// JComboBox
	private JComboBox comboBoxFiltro;

	// Listas
	private ArrayList<Tutor> listaDeTutor = new ArrayList<Tutor>();

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public CrearEventoPanel(MostrarPanel panel) {
		setLayout(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setBounds(0, 0, 798, 550);
		add(panelPrincipal);
		panelPrincipal.setLayout(null);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new GestionDeEventosAnalistaPanel(panel), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelPrincipal.add(buttonRegresar);

		labelCrearEvento = new JLabel("Crear Evento");
		labelCrearEvento.setHorizontalAlignment(SwingConstants.CENTER);
		labelCrearEvento.setFont(new Font("Rockwell", Font.BOLD, 40));
		labelCrearEvento.setBounds(156, 14, 486, 58);
		panelPrincipal.add(labelCrearEvento);

		panelForm = new JPanel();
		panelForm.setBounds(172, 95, 454, 445);
		panelForm.setOpaque(false);
		panelPrincipal.add(panelForm);
		panelForm.setLayout(null);

		lblNombreEvento = new JLabel("Nombre del Evento:");
		lblNombreEvento.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNombreEvento.setBounds(110, 10, 233, 20);
		panelForm.add(lblNombreEvento);

		textFieldNombreEvento = new JTextField();
		textFieldNombreEvento.setBounds(110, 35, 233, 19);
		panelForm.add(textFieldNombreEvento);
		textFieldNombreEvento.setColumns(10);

		lblFechaInicio = new JLabel("Fecha de Inicio:");
		lblFechaInicio.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblFechaInicio.setBounds(50, 72, 151, 20);
		panelForm.add(lblFechaInicio);

		dateChooserFechaInicio = new JDateChooser();
		dateChooserFechaInicio.getCalendarButton().setFont(new Font("Cambria", Font.PLAIN, 15));
		dateChooserFechaInicio.setBounds(50, 97, 151, 19);
		panelForm.add(dateChooserFechaInicio);

		lblFechaFin = new JLabel("Fecha de Finalización:");
		lblFechaFin.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblFechaFin.setBounds(251, 72, 147, 20);
		panelForm.add(lblFechaFin);

		dateChooserFechaFin = new JDateChooser();
		dateChooserFechaFin.getCalendarButton().setFont(new Font("Cambria", Font.PLAIN, 15));
		dateChooserFechaFin.setBounds(251, 97, 151, 19);
		panelForm.add(dateChooserFechaFin);

		lblTutoresAsignados = new JLabel("Tutores asignados:");
		lblTutoresAsignados.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblTutoresAsignados.setBounds(10, 136, 434, 20);
		panelForm.add(lblTutoresAsignados);

		panelAsignar = new JPanel();
		panelAsignar.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.decode("#44bcf4")));
		panelAsignar.setOpaque(false);
		panelAsignar.setBounds(0, 181, 454, 198);
		panelForm.add(panelAsignar);
		panelAsignar.setLayout(null);

		lblAsignarTutor = new JLabel("Asignar tutores:");
		lblAsignarTutor.setFont(new Font("Cambria", Font.BOLD, 20));
		lblAsignarTutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignarTutor.setBounds(135, 14, 183, 30);
		panelAsignar.add(lblAsignarTutor);

		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(16, 59, 153, 19);
		panelAsignar.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);

		comboBoxFiltro = new JComboBox();
		comboBoxFiltro.setModel(new DefaultComboBoxModel(new String[] { "Filtro", "Documento", "Nombre Completo" }));
		comboBoxFiltro.setBounds(185, 58, 153, 21);
		panelAsignar.add(comboBoxFiltro);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String campo = comboBoxFiltro.getSelectedItem().toString();
					String filtro = textFieldBuscar.getText();

					var tutorFiltrado = Buscar.tutorFiltro(filtro, campo).get(0);

					if (tutorFiltrado != null) {
						lblNombreTutor.setText(tutorFiltrado.getUsuario().getNombreCompleto());
						lblDocumentoTutor.setText(tutorFiltrado.getUsuario().getDocumento().toString());
						btnAsignarTutor.setEnabled(true);

					} else {
						JOptionPane.showMessageDialog(null, "No existe el tutor que desea buscar. Inténtelo de nuevo.",
								"Información", JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Inténtelo de nuevo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnBuscar.setBounds(354, 58, 83, 25);
		panelAsignar.add(btnBuscar);

		lblNombreTutor = new JLabel("");
		lblNombreTutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTutor.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNombreTutor.setBounds(16, 93, 421, 20);
		panelAsignar.add(lblNombreTutor);

		lblDocumentoTutor = new JLabel("");
		lblDocumentoTutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocumentoTutor.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblDocumentoTutor.setBounds(16, 127, 421, 20);
		panelAsignar.add(lblDocumentoTutor);

		btnAsignarTutor = new JButton("Asignar");
		btnAsignarTutor.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnAsignarTutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean existe = false;
				for (Tutor tutor : listaDeTutor) {
					if (tutor.getUsuario().getDocumento().toString().equals(lblDocumentoTutor.getText())) {
						JOptionPane.showMessageDialog(null, "El tutor ya ha sido asignado.", "Información",
								JOptionPane.INFORMATION_MESSAGE);
						existe = true;
						break;
					}
				}

				if (existe) {
					return;
				}

				int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea asignar este tutor?",
						"Confirmar asignación", JOptionPane.YES_NO_OPTION);

				if (confirmar != 0) {
					return;
				}

				listaDeTutor.add(Buscar.tutorFiltro(lblDocumentoTutor.getText(), "Documento").get(0));

				String nuevoString = lblTutoresAsignados.getText() + " " + lblNombreTutor.getText() + ",";
				lblTutoresAsignados.setText(nuevoString);

				btnAsignarTutor.setEnabled(false);
				lblDocumentoTutor.setText(null);
				lblNombreTutor.setText(null);
				textFieldBuscar.setText(null);
				comboBoxFiltro.setSelectedIndex(0);

			}
		});
		btnAsignarTutor.setEnabled(false);
		btnAsignarTutor.setBounds(184, 161, 85, 25);
		panelAsignar.add(btnAsignarTutor);

		btnCrearEvento = new JButton("Crear");
		btnCrearEvento.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnCrearEvento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					if (textFieldNombreEvento.getText().trim().equals("") || dateChooserFechaInicio.getDate() == null
							|| dateChooserFechaFin.getDate() == null || listaDeTutor.size() == 0) {
						JOptionPane.showMessageDialog(null,
								"Se deben completar todos los campos obligatorios\npara crear un evento.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					String titulo = textFieldNombreEvento.getText();

					Date selectedDate = dateChooserFechaInicio.getDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(selectedDate);

					int anioInicio = cal.get(Calendar.YEAR);
					int mesInicio = cal.get(Calendar.MONTH);
					int diaInicio = cal.get(Calendar.DAY_OF_MONTH);
					int horaInicio = cal.get(Calendar.HOUR_OF_DAY);
					int minutoInicio = cal.get(Calendar.MINUTE);

					// Crea una instancia de Calendar y establece los valores obtenidos para la
					// fecha de inicio
					Calendar fechaInicioCal = Calendar.getInstance();
					fechaInicioCal.set(anioInicio, mesInicio, diaInicio, horaInicio, minutoInicio);

					// Obtiene la instancia de Date a partir de Calendar para la fecha de inicio
					Date fechaInicio = fechaInicioCal.getTime();

					Date selectedDateFin = dateChooserFechaFin.getDate();
					Calendar calFin = Calendar.getInstance();
					calFin.setTime(selectedDateFin);

					int anioFin = calFin.get(Calendar.YEAR);
					int mesFin = calFin.get(Calendar.MONTH);
					int diaFin = calFin.get(Calendar.DAY_OF_MONTH);
					int horaFin = calFin.get(Calendar.HOUR_OF_DAY);
					int minutoFin = calFin.get(Calendar.MINUTE);

					// Crea una instancia de Calendar y establece los valores obtenidos para la
					// fecha de fin
					Calendar fechaFinCal = Calendar.getInstance();
					fechaFinCal.set(anioFin, mesFin, diaFin, horaFin, minutoFin);

					// Obtiene la instancia de Date a partir de Calendar para la fecha de fin
					Date fechaFin = fechaFinCal.getTime();

					if (fechaFin.getTime() < fechaInicio.getTime()) {
						JOptionPane.showMessageDialog(null,
								"La fecha de finalización debe ser mayor o igual\na la fecha de inicio.", "Información",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					// Evento(Date fechaHoraFin, Date fechaHoraInicio, String titulo)
					Evento oEventoNuevo = new Evento(fechaFin, fechaInicio, titulo);

					boolean eventoCreado = Crear.crearUnEvento(oEventoNuevo, listaDeTutor);

					if (eventoCreado) {
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "El evento ha sido creado exitosamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "No se pudo crear el evento.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Inténtelo de nuevo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnCrearEvento.setBounds(184, 399, 85, 25);
		panelForm.add(btnCrearEvento);

		panelPrincipal.add(imagenFondoContent);

	}

	public void limpiarCampos() {
		textFieldNombreEvento.setText(null);
		dateChooserFechaInicio.setDate(null);
		dateChooserFechaFin.setDate(null);
		lblTutoresAsignados.setText("Tutores asignados: ");
		listaDeTutor.clear();

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}
}
