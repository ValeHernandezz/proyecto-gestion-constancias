package view.components.content.listadoTipoConstancia;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.entities.Tipo;

import services.ServiceTipo;
import view.components.content.listadoTipoConstancia.diccionario.DiccionarioFrame;
import view.img.ImagenesHelper;

public class ModificacionPlantillaFrame extends JFrame {

	// JPanel
	private JPanel contentPane;
	private JPanel panelContent;

	// JLabel
	private JLabel labelTitulo;
	private JLabel lblInfo;
	private JLabel lblDescripcion;
	private JButton btnDiccionario;
	private JButton btnModificar;

	// JTextArea y JScrollPane
	private JScrollPane scrollPaneDescripcion;
	private JTextArea textAreaDescripcion;

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 578, 511);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificacionPlantillaFrame frame = new ModificacionPlantillaFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ModificacionPlantillaFrame(Tipo oTipo) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ModificacionPlantillaFrame.class.getResource("/view/img/ImagenLogoApp.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 592, 548);
		setTitle("Modificar plantilla PDF de Constancia " + oTipo.getNombre());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelContent = new JPanel();
		panelContent.setBounds(0, 0, 578, 511);
		contentPane.add(panelContent);
		panelContent.setLayout(null);

		labelTitulo = new JLabel("Modificar Plantilla");
		labelTitulo.setBounds(46, 13, 485, 46);
		panelContent.add(labelTitulo);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Rockwell", Font.BOLD, 40));

		lblInfo = new JLabel(
				"<html><div style='text-align: center;'>Para poder modificar el modelo de la plantilla, por favor revise el diccionario para ver las palabras claves a insertar.</div></html>");
		lblInfo.setBounds(35, 72, 508, 74);
		panelContent.add(lblInfo);
		lblInfo.setFont(new Font("Cambria", Font.PLAIN, 19));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

		btnDiccionario = new JButton("Ver Diccionario");
		btnDiccionario.setBounds(215, 159, 148, 25);
		btnDiccionario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiccionarioFrame diccionarioFrame = new DiccionarioFrame();
				diccionarioFrame.setLocationRelativeTo(null);
				diccionarioFrame.setVisible(true);
			}
		});
		panelContent.add(btnDiccionario);
		btnDiccionario.setFont(new Font("Cambria", Font.PLAIN, 15));

		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(35, 197, 115, 25);
		panelContent.add(lblDescripcion);
		lblDescripcion.setFont(new Font("Cambria", Font.BOLD, 15));

		scrollPaneDescripcion = new JScrollPane();
		scrollPaneDescripcion.setBounds(35, 235, 508, 220);
		panelContent.add(scrollPaneDescripcion);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setFont(new Font("Cambria", Font.PLAIN, 15));
		scrollPaneDescripcion.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setLineWrap(true); // Salto de línea automático
		textAreaDescripcion.setWrapStyleWord(true);

		// Establecer el valor predeterminado del TextArea
		textAreaDescripcion.setText(oTipo.getDescripcion());

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(228, 468, 122, 25);
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				oTipo.setDescripcion(textAreaDescripcion.getText());

				boolean actualizarTipoContancia = ServiceTipo.actualizarTipoConstancia(oTipo);
				if (actualizarTipoContancia) {

					JOptionPane.showMessageDialog(null,
							"El tipo de constancia " + oTipo.getNombre() + " se ha editado exitosamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
					return;

				} else {

					JOptionPane.showMessageDialog(null,
							"El tipo de constancia " + oTipo.getNombre() + " no se ha podido editar con éxito.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		panelContent.add(btnModificar);
		btnModificar.setFont(new Font("Cambria", Font.PLAIN, 15));

		panelContent.add(imagenPanelContent);

	}
}
