package view.components.content.listadoSolicitudes;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.entities.Constancia;
import com.entities.Estado;
import com.entities.EstadoConstancia;
import com.entities.EstadoConstanciaPK;
import com.entities.Estudiante;

import context.Fabrica;
import context.helpers.Buscar;
import services.ServiceConstancia;
import services.ServiceEmail;
import services.ServiceEstado;
import services.ServiceEstadoConstancia;
import view.img.ImagenesHelper;
import view.utils.TableModelConstancia;

public class FichaConstanciaAnalista extends JFrame {

	// JPanel
	private JPanel contentPane;
	private JPanel panelContent;
	private JPanel panelForm;
	private JPanel panelDatos;
	private JPanel panelDatosConstancia;
	private JPanel panelDatosEstudiante;

	// JLabel
	private JLabel lblTitulo;
	private JLabel lblDetalle;
	private JLabel lblCargar;
	private JLabel lblDatosConstancia;
	private JLabel lblDetalleConstancia;
	private JLabel lblTipo;
	private JLabel lblEvento;
	private JLabel lblFechaRegistro;
	private JLabel lblAnalista;
	private JLabel lblDetalleDeEstado;
	private JLabel lblEstudianteDatos;
	private JLabel lblNombreEstudiante;
	private JLabel lblDocumento;
	private JLabel lblEdad;
	private JLabel lblItrEstudiante;
	private JLabel lblGeneracionEstudiante;
	private JLabel lblSemestre;
	private JLabel lblCambiarEstado;

	// JButton
	private JButton btnCambiarEstado;

	// JTextField
	private JTextField textFieldDetalle;

	// JScrollPane
	private JScrollPane scrollPaneDetalleConstancia;
	private JScrollPane scrollPaneDetalleEstado;

	// JTextArea
	private JTextArea txtDetalleConstancia;
	private JTextArea txtDetalleDeEstado;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// JComboBox
	private JComboBox comboBoxEstados;

	// Listas
	private ArrayList<Estado> listaDeEstados = ServiceEstado.listarEstados();

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 817, 679);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FichaConstanciaAnalista frame = new FichaConstanciaAnalista(null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void cargarListaConstancias(JTable tableConstancias) {
		ArrayList<Constancia> lista = ServiceConstancia.listarConstancias();
		if (lista != null) {
			tableConstancias.setModel(new TableModelConstancia(lista));
		}
	}

	public FichaConstanciaAnalista(Constancia constancia, Estudiante estudiante, JTable tableConstancias) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FichaConstanciaAnalista.class.getResource("/view/img/ImagenLogoApp.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Ficha de Constancia de " + constancia.getTipo().getNombre());
		setBounds(100, 100, 766, 716);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelContent = new JPanel();
		panelContent.setLayout(null);
		panelContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContent.setBounds(0, 0, 752, 679);
		contentPane.add(panelContent);

		lblTitulo = new JLabel("Ficha de Constancia de " + constancia.getTipo().getNombre());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 33));
		lblTitulo.setBounds(0, 19, 753, 58);
		panelContent.add(lblTitulo);

		panelForm = new JPanel();
		panelForm.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.decode("#44bcf4")));
		panelForm.setOpaque(false);
		panelForm.setBounds(95, 96, 561, 132);
		panelContent.add(panelForm);
		panelForm.setLayout(null);

		lblCambiarEstado = new JLabel("Cambiar estado:");
		lblCambiarEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambiarEstado.setFont(new Font("Cambria", Font.BOLD, 20));
		lblCambiarEstado.setBounds(180, 15, 201, 25);
		panelForm.add(lblCambiarEstado);

		lblDetalle = new JLabel("Detalle:");
		lblDetalle.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblDetalle.setBounds(26, 55, 108, 20);
		panelForm.add(lblDetalle);

		textFieldDetalle = new JTextField();
		textFieldDetalle.setBounds(26, 90, 214, 24);
		panelForm.add(textFieldDetalle);
		textFieldDetalle.setColumns(10);

		String[] estadosString = null;

		if (constancia != null && constancia.getEstado() != null) {

			String estadoDescripcion = constancia.getEstado().getDescripcion();

			if (estadoDescripcion.equals("Ingresado")) {

				estadosString = new String[3];
				estadosString[0] = "Elige un estado";

				int index = 1;

				for (int i = 0; i < listaDeEstados.size(); i++) {

					if (!listaDeEstados.get(i).getDescripcion().equalsIgnoreCase("Ingresado")) {

						estadosString[index] = listaDeEstados.get(i).getDescripcion();
						index++; // Incrementa el índice después de asignar un valor en estadosString

					}

				}

			} else if (estadoDescripcion.equals("En proceso")) {

				estadosString = new String[2];
				estadosString[0] = "Elige un estado";
				estadosString[1] = listaDeEstados.get(2).getDescripcion();

			} else if (estadoDescripcion.equals("Finalizado")) {
				estadosString = new String[1];
				estadosString[0] = "Elige un estado";

			}
		}

		DefaultComboBoxModel<String> comboBoxModelEstado = new DefaultComboBoxModel<>(estadosString);

		comboBoxEstados = new JComboBox<>(comboBoxModelEstado);
		comboBoxEstados.setFont(new Font("Cambria", Font.PLAIN, 15));
		comboBoxEstados.setBounds(266, 92, 141, 21);
		panelForm.add(comboBoxEstados);

		btnCambiarEstado = new JButton("Cambiar");
		btnCambiarEstado.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnCambiarEstado.setBounds(433, 90, 99, 25);
		panelForm.add(btnCambiarEstado);

		lblCargar = new JLabel("");
		lblCargar.setFont(new Font("Cambria", Font.PLAIN, 12));
		lblCargar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargar.setBounds(433, 55, 99, 25);
		panelForm.add(lblCargar);
		btnCambiarEstado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (textFieldDetalle.getText().trim().equals("") || comboBoxEstados.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos para cambiar de estado.");
					return;
				}

				try {
					lblCargar.setText("Cargando...");
					String detalle = textFieldDetalle.getText();
					Estado estado = ServiceEstado.listarEstadosFiltro(comboBoxEstados.getSelectedItem().toString())
							.get(0);

					var analista = Buscar
							.analistaFiltro(Fabrica.getoUsuarioLogueado().getDocumento().toString(), "Documento")
							.get(0);
					Date date = new Date();

					if (analista.getFirma() == null) {
						JOptionPane.showMessageDialog(null,
								"No puede cambiar el estado de una constancia si no tiene una firma electronica asociada.");
						return;
					}

					EstadoConstanciaPK oEstadoConstanciaPK = new EstadoConstanciaPK(constancia.getIdConstancia(),
							analista.getIdAnalista(), date, estado.getIdEstado());

					EstadoConstancia oEstadoConstancia = new EstadoConstancia(oEstadoConstanciaPK, detalle);
					int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro qué desea cambiar el estado?",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (opcion != 0) {
						return;
					}
					constancia.setEstado(estado);

					var actualizarConstancia = ServiceConstancia.actualizarConstancia(constancia);
					var guardarEstadoConstancia = ServiceEstadoConstancia.crearEstadoConstancia(oEstadoConstancia);

					if (guardarEstadoConstancia && actualizarConstancia) {
						Fabrica.cargarListas();
						cargarListaConstancias(tableConstancias);
						var enviarMail = ServiceEmail.mandarMail(constancia, estudiante);

						if (!enviarMail) {
							JOptionPane.showMessageDialog(null, "No se pudo enviar el mail");
							return;
						}

						lblCargar.setText("");
						JOptionPane.showMessageDialog(null, "Estado cambiado");
						dispose();
						return;
					} else {
						lblCargar.setText("");
						JOptionPane.showMessageDialog(null, "Algo salio mal");
						return;
					}
				} catch (Exception e2) {
					lblCargar.setText("");
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos para cambiar de estado.");
					return;
				}
			}
		});

		panelDatos = new JPanel();
		panelDatos.setLayout(null);
		panelDatos.setOpaque(false);
		panelDatos.setBounds(9, 247, 733, 413);
		panelContent.add(panelDatos);

		panelDatosConstancia = new JPanel();
		panelDatosConstancia.setLayout(null);
		panelDatosConstancia.setOpaque(false);
		panelDatosConstancia.setBounds(10, 10, 384, 393);
		panelDatos.add(panelDatosConstancia);

		lblDatosConstancia = new JLabel("Datos de la Constancia:");
		lblDatosConstancia.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosConstancia.setFont(new Font("Cambria", Font.BOLD, 20));
		lblDatosConstancia.setBounds(10, 10, 364, 25);
		panelDatosConstancia.add(lblDatosConstancia);

		lblDetalleConstancia = new JLabel("Detalle:");
		lblDetalleConstancia.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblDetalleConstancia.setBounds(10, 45, 364, 25);
		panelDatosConstancia.add(lblDetalleConstancia);

		scrollPaneDetalleConstancia = new JScrollPane((Component) null);
		scrollPaneDetalleConstancia.setOpaque(false);
		scrollPaneDetalleConstancia.setBounds(10, 80, 364, 51);
		panelDatosConstancia.add(scrollPaneDetalleConstancia);

		txtDetalleConstancia = new JTextArea(constancia.getDetalle());
		scrollPaneDetalleConstancia.setViewportView(txtDetalleConstancia);
		txtDetalleConstancia.setWrapStyleWord(true);
		txtDetalleConstancia.setOpaque(false);
		txtDetalleConstancia.setLineWrap(true);
		txtDetalleConstancia.setFont(new Font("Cambria", Font.PLAIN, 13));
		txtDetalleConstancia.setEditable(false);

		lblTipo = new JLabel("Tipo: " + constancia.getTipo().getNombre());
		lblTipo.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblTipo.setBounds(10, 141, 364, 25);
		panelDatosConstancia.add(lblTipo);

		lblEvento = new JLabel("Evento: " + constancia.getEvento().getTitulo());
		lblEvento.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblEvento.setBounds(10, 176, 364, 25);
		panelDatosConstancia.add(lblEvento);

		lblFechaRegistro = new JLabel("Fecha y Hora de solicitud: " + constancia.getFechaHora().toLocaleString());
		lblFechaRegistro.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblFechaRegistro.setBounds(10, 211, 364, 25);
		panelDatosConstancia.add(lblFechaRegistro);

		separator1 = new JSeparator();
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(10, 246, 364, 2);
		panelDatosConstancia.add(separator1);

		lblAnalista = new JLabel("Analista: " + (Buscar.analistaDeConstancia(constancia.getIdConstancia(),
				constancia.getEstado().getIdEstado()) != null ? Buscar
						.analistaDeConstancia(constancia.getIdConstancia(), constancia.getEstado().getIdEstado())
						.getUsuario().getNombreCompleto() : "-"));
		lblAnalista.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblAnalista.setBounds(10, 258, 364, 25);
		panelDatosConstancia.add(lblAnalista);

		lblDetalleDeEstado = new JLabel("Detalle del estado: ");
		lblDetalleDeEstado.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblDetalleDeEstado.setBounds(10, 293, 364, 25);
		panelDatosConstancia.add(lblDetalleDeEstado);

		scrollPaneDetalleEstado = new JScrollPane((Component) null);
		scrollPaneDetalleEstado.setOpaque(false);
		scrollPaneDetalleEstado.setBounds(10, 328, 364, 55);
		panelDatosConstancia.add(scrollPaneDetalleEstado);

		txtDetalleDeEstado = new JTextArea((Buscar.detalleEstadoDeConstancia(constancia.getIdConstancia(),
				constancia.getEstado().getIdEstado()) == null ? ""
						: Buscar.detalleEstadoDeConstancia(constancia.getIdConstancia(),
								constancia.getEstado().getIdEstado())));
		scrollPaneDetalleEstado.setViewportView(txtDetalleDeEstado);
		txtDetalleDeEstado.setWrapStyleWord(true);
		txtDetalleDeEstado.setOpaque(false);
		txtDetalleDeEstado.setLineWrap(true);
		txtDetalleDeEstado.setFont(new Font("Cambria", Font.PLAIN, 13));
		txtDetalleDeEstado.setEditable(false);

		separator2 = new JSeparator();
		separator2.setBounds(404, 0, 2, 413);
		panelDatos.add(separator2);
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setForeground(new Color(68, 188, 244));

		panelDatosEstudiante = new JPanel();
		panelDatosEstudiante.setLayout(null);
		panelDatosEstudiante.setOpaque(false);
		panelDatosEstudiante.setBounds(416, 57, 307, 298);
		panelDatos.add(panelDatosEstudiante);

		lblEstudianteDatos = new JLabel("Datos del Estudiante:");
		lblEstudianteDatos.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstudianteDatos.setFont(new Font("Cambria", Font.BOLD, 20));
		lblEstudianteDatos.setBounds(10, 10, 287, 25);
		panelDatosEstudiante.add(lblEstudianteDatos);

		lblNombreEstudiante = new JLabel("Nombre: " + estudiante.getUsuario().getNombreCompleto());
		lblNombreEstudiante.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblNombreEstudiante.setBounds(10, 49, 287, 25);
		panelDatosEstudiante.add(lblNombreEstudiante);

		lblDocumento = new JLabel("Documento: " + estudiante.getUsuario().getDocumento().toString());
		lblDocumento.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblDocumento.setBounds(10, 86, 287, 25);
		panelDatosEstudiante.add(lblDocumento);

		lblEdad = new JLabel("Edad: " + estudiante.getUsuario().getEdad());
		lblEdad.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblEdad.setBounds(10, 123, 287, 25);
		panelDatosEstudiante.add(lblEdad);

		lblItrEstudiante = new JLabel("ITR: " + estudiante.getUsuario().getItr().getNombre());
		lblItrEstudiante.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblItrEstudiante.setBounds(10, 160, 287, 25);
		panelDatosEstudiante.add(lblItrEstudiante);

		lblGeneracionEstudiante = new JLabel("Generación: " + estudiante.getGeneracion());
		lblGeneracionEstudiante.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblGeneracionEstudiante.setBounds(10, 197, 287, 25);
		panelDatosEstudiante.add(lblGeneracionEstudiante);

		lblSemestre = new JLabel("Semestre: " + estudiante.getSemestre().toString());
		lblSemestre.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblSemestre.setBounds(10, 234, 287, 25);
		panelDatosEstudiante.add(lblSemestre);
		imagenPanelContent.setBounds(0, 0, 753, 679);

		panelContent.add(imagenPanelContent);

	}
}
