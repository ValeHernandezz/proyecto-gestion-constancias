package view.components.content.listadoSolicitudes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.entities.Constancia;
import com.entities.Estudiante;

import context.helpers.Buscar;
import view.img.ImagenesHelper;
import view.utils.TableModelConstancia;

public class ConstanciasEstudiante extends JFrame {

	// JPanel
	private JPanel panelPrincipal;

	// JLabel
	private JLabel lblTitulo;
	private JLabel lblDocumentoEstudiante;
	private JLabel lblNumerodeConstancias;
	private JLabel lblEdad;
	private JLabel lblItr;
	private JLabel lblGeneracion;
	private JLabel lblSemestre;

	// JSeparator
	private JSeparator separator1;
	private JSeparator separator2;

	// JTable y JScrollPane
	private JTable tableConstancias;
	private JScrollPane scrollPaneTabla;

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConstanciasEstudiante frame = new ConstanciasEstudiante(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ConstanciasEstudiante(Estudiante oEstudiante) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ConstanciasEstudiante.class.getResource("/view/img/ImagenLogoApp.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Constancias de " + oEstudiante.getUsuario().getNombreCompleto());
		setBounds(100, 100, 801, 458);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(192, 192, 192));
		panelPrincipal.setBounds(0, 0, 798, 550);

		panelPrincipal.setLayout(null);

		setContentPane(panelPrincipal);

		lblTitulo = new JLabel("Constancias de " + oEstudiante.getUsuario().getNombreCompleto());
		lblTitulo.setBounds(17, 17, 753, 39);
		panelPrincipal.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 30));

		separator1 = new JSeparator();
		separator1.setForeground(new Color(68, 188, 244));
		separator1.setBounds(30, 73, 727, 2);
		panelPrincipal.add(separator1);

		lblDocumentoEstudiante = new JLabel("Documento: " + oEstudiante.getUsuario().getDocumento());
		lblDocumentoEstudiante.setBounds(73, 92, 165, 25);
		panelPrincipal.add(lblDocumentoEstudiante);
		lblDocumentoEstudiante.setFont(new Font("Cambria", Font.PLAIN, 15));

		lblNumerodeConstancias = new JLabel(
				"Contancias: " + Buscar.constanciaPorNombreUsuario(oEstudiante.getUsuario().getNombreUsuario()).size());
		lblNumerodeConstancias.setBounds(311, 92, 165, 25);
		panelPrincipal.add(lblNumerodeConstancias);
		lblNumerodeConstancias.setFont(new Font("Cambria", Font.PLAIN, 15));

		lblEdad = new JLabel("Edad: " + oEstudiante.getUsuario().getEdad());
		lblEdad.setBounds(549, 92, 165, 25);
		panelPrincipal.add(lblEdad);
		lblEdad.setFont(new Font("Cambria", Font.PLAIN, 15));

		lblItr = new JLabel("ITR: " + oEstudiante.getUsuario().getItr().getNombre());
		lblItr.setBounds(73, 134, 165, 25);
		panelPrincipal.add(lblItr);
		lblItr.setFont(new Font("Cambria", Font.PLAIN, 15));

		lblGeneracion = new JLabel("Generación: " + oEstudiante.getGeneracion());
		lblGeneracion.setBounds(311, 134, 165, 25);
		panelPrincipal.add(lblGeneracion);
		lblGeneracion.setFont(new Font("Cambria", Font.PLAIN, 15));

		lblSemestre = new JLabel("Semestre: " + oEstudiante.getSemestre());
		lblSemestre.setBounds(549, 134, 165, 25);
		panelPrincipal.add(lblSemestre);
		lblSemestre.setFont(new Font("Cambria", Font.PLAIN, 15));

		separator2 = new JSeparator();
		separator2.setForeground(new Color(68, 188, 244));
		separator2.setBounds(30, 176, 727, 2);
		panelPrincipal.add(separator2);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(30, 195, 727, 208);
		panelPrincipal.add(scrollPaneTabla);

		tableConstancias = new JTable();
		tableConstancias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableConstancias.getSelectedRow();

				if (filaSeleccionada != -1) {

					String tipo = (String) tableConstancias.getValueAt(filaSeleccionada, 0);
					String evento = (String) tableConstancias.getValueAt(filaSeleccionada, 1);
					String documentoEstudiante = (String) tableConstancias.getValueAt(filaSeleccionada, 5);

					var constancia = Buscar.idConstanciaAnalista(documentoEstudiante, evento, tipo);
					var estudiante = Buscar.estudianteFiltro(documentoEstudiante, "Documento").get(0);

					int opcion = JOptionPane.showConfirmDialog(null, "Desea modificar el estado de la constancia");
					if (opcion == 0) {
						FichaConstanciaAnalista oGestionConstancia = new FichaConstanciaAnalista(constancia, estudiante,
								tableConstancias);
						oGestionConstancia.setLocationRelativeTo(null);
						oGestionConstancia.setVisible(true);

					}
				}

			}
		});
		tableConstancias.setModel(new TableModelConstancia(new ArrayList<Constancia>()));
		scrollPaneTabla.setViewportView(tableConstancias);
		imagenFondoContent.setBounds(0, 0, 787, 421);

		panelPrincipal.add(imagenFondoContent);

		cargarLista(oEstudiante);

	}

	public void cargarLista(Estudiante estudiante) {
		tableConstancias.setModel(new TableModelConstancia(
				Buscar.constanciaPorNombreUsuario(estudiante.getUsuario().getNombreUsuario())));
	}
}
