package view.components.content.firmaElectronica;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.entities.Analista;

import context.helpers.Actualizar;
import view.img.ImagenesHelper;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import javax.swing.JEditorPane;
import java.awt.Toolkit;

public class CargarFirmaElectronicaFrame extends JFrame {

	// JPanel
	private JPanel panelContent;

	// JLabel
	private JLabel lblInstruccion;

	// JButton
	private JButton btnCargarImagen;

	// JEditorPane
	private JEditorPane editorPaneGenerarFirmaElectronica;

	// Imágenes
	private ImagenesHelper imagenPanelContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 497, 223);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargarFirmaElectronicaFrame frame = new CargarFirmaElectronicaFrame(null, false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CargarFirmaElectronicaFrame(Analista oAnalista, boolean cerrar) {
		setTitle("Cargar firma electrónica");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(CargarFirmaElectronicaFrame.class.getResource("/view/img/ImagenLogoApp.png")));
		setResizable(false);
		setDefaultCloseOperation(cerrar ? WindowConstants.DISPOSE_ON_CLOSE : WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 497, 223);

		panelContent = new JPanel();
		panelContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContent);
		panelContent.setLayout(null);

		lblInstruccion = new JLabel("Por favor, seleccione una imagen con su firma:");
		lblInstruccion.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruccion.setBounds(33, 22, 416, 45);
		panelContent.add(lblInstruccion);

		btnCargarImagen = new JButton("Cargar Imagen");
		btnCargarImagen.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnCargarImagen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "png", "jpg", "jpeg");
				fileChooser.setFileFilter(filter);

				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						// Leer el archivo y convertirlo a bytes
						byte[] firmaBytes = Files.readAllBytes(selectedFile.toPath());

						// Asignar los bytes de la firma al atributo
						oAnalista.setFirma(firmaBytes);

						// Guardar el objeto Analista actualizado en la base de datos
						boolean firmaGuardada = Actualizar.usuario(oAnalista.getUsuario(), oAnalista);

						if (firmaGuardada) {
							// Mostrar mensaje de éxito
							JOptionPane.showMessageDialog(null, "La imagen se ha cargado correctamente.", "Éxito",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
							return;
						} else {
							JOptionPane.showMessageDialog(null,
									"No se pudo cargar la firma electrónica. Por favor, inténtalo de nuevo.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

					} catch (IOException ex) {
						ex.printStackTrace();
						// Mostrar mensaje de error
						JOptionPane.showMessageDialog(null,
								"Se produjo un error al cargar la imagen. Por favor, inténtalo de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCargarImagen.setBounds(168, 89, 146, 25);
		panelContent.add(btnCargarImagen);

		editorPaneGenerarFirmaElectronica = new JEditorPane();
		editorPaneGenerarFirmaElectronica.setFont(new Font("Cambria", Font.PLAIN, 15));
		editorPaneGenerarFirmaElectronica.setBounds(62, 136, 358, 25);
		editorPaneGenerarFirmaElectronica.setContentType("text/html");
		editorPaneGenerarFirmaElectronica.setText(
				"<html><div style='text-align:center; background-color: transparent; color: #828588; height: 100%; display: flex; align-items: center;'>¿No posees una firma electrónica? Genera una <a href=\"https://www.ejemplo.com\">aquí</a></div></html>");
		editorPaneGenerarFirmaElectronica.setEditable(false);
		editorPaneGenerarFirmaElectronica.setOpaque(false);
		editorPaneGenerarFirmaElectronica.setBorder(BorderFactory.createEmptyBorder());
		editorPaneGenerarFirmaElectronica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						try {
							Desktop.getDesktop().browse(new URI("https://signature-maker.net/signature-creator"));
						} catch (IOException | URISyntaxException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
		panelContent.add(editorPaneGenerarFirmaElectronica);
		imagenPanelContent.setBounds(0, 0, 483, 186);

		panelContent.add(imagenPanelContent);
	}
}
