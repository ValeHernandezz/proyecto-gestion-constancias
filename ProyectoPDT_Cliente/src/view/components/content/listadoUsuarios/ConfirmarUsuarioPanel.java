package view.components.content.listadoUsuarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.entities.Usuario;

import context.Fabrica;
import services.ServiceEmail;
import services.ServiceUsuario;
import view.components.content.home.BienvenidoPanel;
import view.components.utils.MostrarPanel;
import view.img.ImagenesHelper;
import view.utils.TableModelUsuario;

public class ConfirmarUsuarioPanel extends JPanel {

	// JPanel
	private JPanel panelContent;

	// JButton
	private JButton buttonRegresar;

	// JLabel
	private JLabel lblConfirmaciónDeUsuarios;
	private JLabel lblDescripcion;

	// JTable y JScrollPane
	private JTable tableUsuarios;
	private JScrollPane scrollPaneTabla;

	// Imágenes
	private ImagenesHelper imagenFondoContent = new ImagenesHelper("/view/img/ImagenFondoContent.png", 798, 550);

	public ConfirmarUsuarioPanel(MostrarPanel panel) {
		setLayout(null);

		panelContent = new JPanel();
		panelContent.setBackground(new Color(128, 128, 128));
		panelContent.setOpaque(false);
		panelContent.setBounds(0, 0, 798, 550);
		add(panelContent);
		panelContent.setLayout(null);

		buttonRegresar = new JButton("⇦");
		buttonRegresar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				panel.mostrarPanelContent(prepararPanel(new BienvenidoPanel(), 798, 550));

			}
		});
		buttonRegresar.setFont(new Font("Cambria", Font.PLAIN, 13));
		buttonRegresar.setBounds(10, 10, 45, 25);
		panelContent.add(buttonRegresar);

		lblConfirmaciónDeUsuarios = new JLabel("Confirmación de Usuarios");
		lblConfirmaciónDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmaciónDeUsuarios.setFont(new Font("Rockwell", Font.BOLD, 40));
		lblConfirmaciónDeUsuarios.setBounds(116, 14, 565, 58);
		panelContent.add(lblConfirmaciónDeUsuarios);

		lblDescripcion = new JLabel(getMensaje());
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setFont(new Font("Cambria", Font.PLAIN, 20));
		lblDescripcion.setBounds(10, 87, 778, 25);
		panelContent.add(lblDescripcion);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(10, 138, 778, 402);
		panelContent.add(scrollPaneTabla);

		tableUsuarios = new JTable();
		tableUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tableUsuarios.getSelectedRow();
				String documento = (String) tableUsuarios.getValueAt(filaSeleccionada, 0);

				Usuario oUsuarioConfirmado = Fabrica.buscarUsuarioPorCampoYFiltro(documento, "Documento").get(0);

				if (filaSeleccionada != -1) {
					int aceptar = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea confirmar el usuario?",
							"Confirmar", JOptionPane.YES_NO_OPTION);

					if (aceptar == 0) {
						oUsuarioConfirmado.setConfirmado("S");
						var usuarioActualizado = ServiceUsuario.actualizarUsuario(oUsuarioConfirmado);
						if (usuarioActualizado != null) {
							tableUsuarios.setModel(
									new TableModelUsuario(Fabrica.buscarUsuarioPorCampoYFiltro("N", "Confirmar")));
							lblDescripcion.setText(getMensaje());
							ServiceEmail.mandarMailConfirmado(oUsuarioConfirmado);
							JOptionPane.showMessageDialog(null, "Usuario confirmado con éxito.", "Éxito",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						JOptionPane.showMessageDialog(null, "No se pudo confirmar el usuario", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		if (Fabrica.buscarUsuarioPorCampoYFiltro("N", "Confirmar").size() != 0) {
			tableUsuarios.setModel(new TableModelUsuario(Fabrica.buscarUsuarioPorCampoYFiltro("N", "Confirmar")));
		}
		scrollPaneTabla.setViewportView(tableUsuarios);

		panelContent.add(imagenFondoContent);

	}

	public JPanel prepararPanel(JPanel panel, int largo, int alto) {
		panel.setBounds(0, 0, largo, alto);
		return panel;
	}

	public String getMensaje() {
		String mensaje = Fabrica.buscarUsuarioPorCampoYFiltro("N", "Confirmar").size() != 0
				? "Seleccione un usuario para confirmarlo."
				: "No existen usuarios a confirmar.";
		return mensaje;
	}
}
