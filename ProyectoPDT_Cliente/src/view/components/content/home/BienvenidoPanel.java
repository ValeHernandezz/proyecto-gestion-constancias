package view.components.content.home;

import javax.swing.JPanel;

import view.img.ImagenesHelper;

import java.awt.Color;

public class BienvenidoPanel extends JPanel {

	// JPanel
	private JPanel panelBienvenido;
	private JPanel panelImagenLogoEquipo;
	private JPanel panelImagenLogoUtec;
	private JPanel panelImagenLogoCompleto;

	// Imágenes
	private ImagenesHelper imagenLogoEquipo = new ImagenesHelper("/view/img/LogoEquipo.png", 86, 50);
	private ImagenesHelper imagenLogoUtec = new ImagenesHelper("/view/img/Utec.png", 92, 65);
	private ImagenesHelper imagenLogoCompleto = new ImagenesHelper("/view/img/ImagenBienvenidos.png", 738, 328);

	public BienvenidoPanel() {
		setLayout(null);

		panelBienvenido = new JPanel();
		panelBienvenido.setBackground(new Color(255, 255, 255));
		panelBienvenido.setBorder(null);
		panelBienvenido.setBounds(0, 0, 798, 550);
		add(panelBienvenido);
		panelBienvenido.setLayout(null);

		panelImagenLogoEquipo = new JPanel();
		panelImagenLogoEquipo.setBounds(206, 45, 86, 50);
		panelBienvenido.add(panelImagenLogoEquipo);
		panelImagenLogoEquipo.setLayout(null);
		imagenLogoEquipo.setLocation(0, 0);

		panelImagenLogoEquipo.add(imagenLogoEquipo);

		panelImagenLogoCompleto = new JPanel();
		panelImagenLogoCompleto.setBounds(30, 169, 738, 328);
		panelBienvenido.add(panelImagenLogoCompleto);
		panelImagenLogoCompleto.setLayout(null);

		panelImagenLogoCompleto.add(imagenLogoCompleto);

		panelImagenLogoUtec = new JPanel();
		panelImagenLogoUtec.setBounds(498, 30, 92, 65);
		panelBienvenido.add(panelImagenLogoUtec);
		panelImagenLogoUtec.setLayout(null);
		imagenLogoUtec.setLocation(0, 0);

		panelImagenLogoUtec.add(imagenLogoUtec);

	}
}
