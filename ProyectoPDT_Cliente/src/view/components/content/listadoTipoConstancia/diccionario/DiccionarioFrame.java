package view.components.content.listadoTipoConstancia.diccionario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.img.ImagenesHelper;

public class DiccionarioFrame extends JFrame {

	// JPanel
	private JPanel contentPane;
	private JPanel panelContent;

	// JLabel
	private JLabel lblDiccionario;
	private JLabel lblDescripcion;
	private JLabel lblDatosEstudiante;
	private JLabel lblDatosConstancia;
	private JLabel lblDatosEvento;
	private JLabel lblPalabrasClaveEstudiante;
	private JLabel lblPalabrasClaveConstancia;
	private JLabel lblPalabrasClaveEvento;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// Imágenes
	private ImagenesHelper imagenContentPanel = new ImagenesHelper("/view/img/ImagenFondoContent.png", 724, 511);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiccionarioFrame frame = new DiccionarioFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DiccionarioFrame() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(DiccionarioFrame.class.getResource("/view/img/ImagenLogoApp.png")));
		setResizable(false);
		setTitle("Diccionario de palabras claves");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 738, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelContent = new JPanel();
		panelContent.setBounds(0, 0, 724, 544);
		contentPane.add(panelContent);
		panelContent.setLayout(null);

		lblDiccionario = new JLabel("Diccionario");
		lblDiccionario.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiccionario.setFont(new Font("Rockwell", Font.BOLD, 40));
		lblDiccionario.setBounds(2, 27, 721, 46);
		panelContent.add(lblDiccionario);

		lblDescripcion = new JLabel(
				"<html><div style='text-align: justify;'>A continuación, encontrarás una lista de palabras clave que puedes emplear para personalizar una plantilla de PDF.<br/>Te recomendamos tener en cuenta tanto las mayúsculas como las minúsculas al utilizar estas palabras clave en el documento.<br/>Cada palabra clave se muestra entre dos ampersands (&&) y su contenido correspondiente.</div></html>");
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcion.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setBounds(21, 100, 682, 100);
		panelContent.add(lblDescripcion);

		lblDatosEstudiante = new JLabel("Datos del Estudiante:");
		lblDatosEstudiante.setFont(new Font("Cambria", Font.BOLD, 20));
		lblDatosEstudiante.setBounds(21, 227, 293, 30);
		panelContent.add(lblDatosEstudiante);

		lblPalabrasClaveEstudiante = new JLabel(
				"<html>Cédula - &cedula&<br>Nombres - &nombres&<br>Apellidos - &apellidos&<br>Nombre completo - &nombreCompleto&<br>Fecha de Nacimiento - &fechaNacimiento&<br>Edad - &edad&<br>Localidad del ITR - &localidadItr&<br>Nombre del ITR - &nombreItr&<br> Localidad del Estudiante - &localidadEstudiante&<br>Departamento del Estudiante - &departamentoEstudiante&<br>Teléfono - &telefono&<br>Mail Personal - &mailPersonal&<br>Mail Institucional - &mailInstitucional&</html>");
		lblPalabrasClaveEstudiante.setHorizontalAlignment(SwingConstants.LEFT);
		lblPalabrasClaveEstudiante.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblPalabrasClaveEstudiante.setVerticalAlignment(SwingConstants.TOP);
		lblPalabrasClaveEstudiante.setBounds(21, 261, 414, 253);
		lblPalabrasClaveEstudiante.setPreferredSize(new Dimension(209, 13));
		panelContent.add(lblPalabrasClaveEstudiante);

		separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(410, 227, 2, 287);
		panelContent.add(separator1);

		lblDatosConstancia = new JLabel("Datos de la Constancia:");
		lblDatosConstancia.setFont(new Font("Cambria", Font.BOLD, 20));
		lblDatosConstancia.setBounds(430, 259, 293, 30);
		panelContent.add(lblDatosConstancia);

		lblPalabrasClaveConstancia = new JLabel(
				"<html>Fecha Expedido - &fechaExpedido&<br>Tipo Constancia - &tipoConstancia&</html>");
		lblPalabrasClaveConstancia.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblPalabrasClaveConstancia.setVerticalAlignment(SwingConstants.TOP);
		lblPalabrasClaveConstancia.setBounds(430, 293, 260, 46);
		lblPalabrasClaveConstancia.setPreferredSize(new Dimension(209, 13));
		panelContent.add(lblPalabrasClaveConstancia);

		separator2 = new JSeparator();
		separator2.setForeground(new Color(68, 188, 244));
		separator2.setBounds(410, 369, 304, 2);
		panelContent.add(separator2);

		lblDatosEvento = new JLabel("Datos del Evento:");
		lblDatosEvento.setFont(new Font("Cambria", Font.BOLD, 20));
		lblDatosEvento.setBounds(430, 394, 293, 30);
		panelContent.add(lblDatosEvento);

		lblPalabrasClaveEvento = new JLabel(
				"<html>Nombre Evento - &nombreEvento&<br>Fecha Inicio Evento - &fechaInicioEvento&<br>Fecha Fin Evento - &fechaFinEvento&</html>");
		lblPalabrasClaveEvento.setFont(new Font("Cambria", Font.PLAIN, 15));
		lblPalabrasClaveEvento.setVerticalAlignment(SwingConstants.TOP);
		lblPalabrasClaveEvento.setBounds(430, 430, 293, 66);
		lblPalabrasClaveEvento.setPreferredSize(new Dimension(209, 13));
		panelContent.add(lblPalabrasClaveEvento);
		imagenContentPanel.setBounds(0, 0, 724, 544);

		panelContent.add(imagenContentPanel);

	}
}
