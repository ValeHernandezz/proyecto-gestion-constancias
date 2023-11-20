package view.components.content.listadoSolicitudes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.entities.Constancia;
import com.entities.Estado;
import com.entities.Estudiante;

import context.helpers.Buscar;
import context.helpers.Generar;
import services.ServiceEstado;
import view.img.ImagenesHelper;

public class FichaConstanciaEstudiante extends JFrame {

	// JPanel
	private JPanel panelContent;
	private JPanel panelDatosConstancia;
	private JPanel panelDatosEstudiante;

	// JLabel
	private JLabel lblTitulo;
	private JLabel lblDatosConstancia;
	private JLabel lblDetalleConstancia;
	private JLabel lblTipo;
	private JLabel lblEvento;
	private JLabel lblFechaRegistro;
	private JLabel lblRespuesta;
	private JLabel lblAnalista;
	private JLabel lblDetalleDeEstado;

	// JTextField
	private JTextArea txtDetalleConstancia;
	private JTextArea txtDetalleDeEstado;

	// JScrollPane
	private JScrollPane scrollPaneDetalleConstancia;
	private JScrollPane scrollPaneDetalleEstado;

	// JSeparator
	private JSeparator separator2;
	private JSeparator separator1;

	// JButton
	private JButton btnDescargarPDF;

	// Listas
	private ArrayList<Estado> listaDeEstados = ServiceEstado.listarEstados();

	// Listas
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 744, 559);

	// Extras
	private Generar oGenerar = new Generar();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FichaConstanciaEstudiante frame = new FichaConstanciaEstudiante(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FichaConstanciaEstudiante(Constancia constancia, Estudiante estudiante) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FichaConstanciaEstudiante.class.getResource("/view/img/ImagenLogoApp.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Constancia de " + constancia.getTipo().getNombre());
		setBounds(100, 100, 744, 482);
		panelContent = new JPanel();
		panelContent.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelContent);
		panelContent.setLayout(null);

		String[] estadosString = new String[listaDeEstados.size() + 1];
		estadosString[0] = "Elige un estado";

		for (int i = 0; i < listaDeEstados.size(); i++) {
			estadosString[i + 1] = listaDeEstados.get(i).getDescripcion();
		}
		DefaultComboBoxModel<String> comboBoxModelEstado = new DefaultComboBoxModel<>(estadosString);

		lblTitulo = new JLabel("Constancia de " + constancia.getTipo().getNombre());
		lblTitulo.setBounds(0, 16, 730, 58);
		panelContent.add(lblTitulo);
		lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 33));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		panelDatosConstancia = new JPanel();
		panelDatosConstancia.setOpaque(false);
		panelDatosConstancia.setBounds(9, 101, 384, 252);
		panelContent.add(panelDatosConstancia);
		panelDatosConstancia.setLayout(null);

		lblDatosConstancia = new JLabel("Datos de la Constancia:");
		lblDatosConstancia.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosConstancia.setFont(new Font("Cambria", Font.BOLD, 20));
		lblDatosConstancia.setBounds(10, 10, 364, 25);
		panelDatosConstancia.add(lblDatosConstancia);

		lblDetalleConstancia = new JLabel("Detalle:");
		lblDetalleConstancia.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblDetalleConstancia.setBounds(10, 45, 364, 25);
		panelDatosConstancia.add(lblDetalleConstancia);

		txtDetalleConstancia = new JTextArea(constancia.getDetalle());
		txtDetalleConstancia.setFont(new Font("Cambria", Font.PLAIN, 15));
		txtDetalleConstancia.setBounds(1, 26, 335, 26);
		txtDetalleConstancia.setOpaque(false);
		txtDetalleConstancia.setLineWrap(true);
		txtDetalleConstancia.setWrapStyleWord(true);
		txtDetalleConstancia.setEditable(false);

		scrollPaneDetalleConstancia = new JScrollPane(txtDetalleConstancia);
		scrollPaneDetalleConstancia.setBounds(10, 80, 364, 53);
		scrollPaneDetalleConstancia.setOpaque(false);
		panelDatosConstancia.add(scrollPaneDetalleConstancia);

		lblTipo = new JLabel("Tipo: " + constancia.getTipo().getNombre());
		lblTipo.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblTipo.setBounds(10, 143, 364, 25);
		panelDatosConstancia.add(lblTipo);

		lblEvento = new JLabel("Evento: " + constancia.getEvento().getTitulo());
		lblEvento.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblEvento.setBounds(10, 178, 364, 25);
		panelDatosConstancia.add(lblEvento);

		lblFechaRegistro = new JLabel("Fecha y Hora de solicitud: " + constancia.getFechaHora().toLocaleString());
		lblFechaRegistro.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblFechaRegistro.setBounds(10, 213, 364, 25);
		panelDatosConstancia.add(lblFechaRegistro);

		separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(402, 90, 2, 274);
		panelContent.add(separator1);

		panelDatosEstudiante = new JPanel();
		panelDatosEstudiante.setOpaque(false);
		panelDatosEstudiante.setBounds(413, 101, 307, 252);
		panelContent.add(panelDatosEstudiante);
		panelDatosEstudiante.setLayout(null);
		
				lblRespuesta = new JLabel("Respuesta:");
				lblRespuesta.setHorizontalAlignment(SwingConstants.LEFT);
				lblRespuesta.setFont(new Font("Cambria", Font.BOLD, 20));
				lblRespuesta.setBounds(10, 10, 287, 25);
				panelDatosEstudiante.add(lblRespuesta);

		lblAnalista = new JLabel("Analista: "
				+ Buscar.analistaDeConstancia(constancia.getIdConstancia(), constancia.getEstado().getIdEstado())
						.getUsuario().getNombreCompleto());
		lblAnalista.setBounds(10, 45, 287, 25);
		panelDatosEstudiante.add(lblAnalista);
		lblAnalista.setFont(new Font("Cambria", Font.PLAIN, 15));

		lblDetalleDeEstado = new JLabel("Detalle de estado: ");
		lblDetalleDeEstado.setBounds(10, 80, 287, 25);
		panelDatosEstudiante.add(lblDetalleDeEstado);
		lblDetalleDeEstado.setFont(new Font("Cambria", Font.PLAIN, 15));

		txtDetalleDeEstado = new JTextArea((Buscar.detalleEstadoDeConstancia(constancia.getIdConstancia(),
				constancia.getEstado().getIdEstado()) == null ? ""
						: Buscar.detalleEstadoDeConstancia(constancia.getIdConstancia(),
								constancia.getEstado().getIdEstado())));
		txtDetalleDeEstado.setFont(new Font("Cambria", Font.PLAIN, 15));
		txtDetalleDeEstado.setBounds(10, 226, 337, 40);
		txtDetalleDeEstado.setOpaque(false);
		txtDetalleDeEstado.setLineWrap(true);
		txtDetalleDeEstado.setWrapStyleWord(true);
		txtDetalleDeEstado.setEditable(false);

		scrollPaneDetalleEstado = new JScrollPane(txtDetalleDeEstado);
		scrollPaneDetalleEstado.setBounds(10, 118, 287, 122);
		panelDatosEstudiante.add(scrollPaneDetalleEstado);
		scrollPaneDetalleEstado.setOpaque(false);

		separator2 = new JSeparator();
		separator2.setBounds(10, 380, 710, 2);
		panelContent.add(separator2);
		separator2.setForeground(new Color(68, 188, 244));

		if (constancia.getEstado().getDescripcion().equals("Finalizado")) {
			btnDescargarPDF = new JButton("Descargar PDF");
			btnDescargarPDF.setFont(new Font("Cambria", Font.PLAIN, 15));
			btnDescargarPDF.setBounds(296, 398, 138, 30);
			panelContent.add(btnDescargarPDF);
			btnDescargarPDF.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					oGenerar.constancia(constancia, estudiante);
				}

			});
		}
		imagenPanelContent.setBounds(0, 0, 730, 445);

		panelContent.add(imagenPanelContent);
		
	}
}
