package context.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.EventoEstudiante;
import com.entities.Tipo;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class Generar {

	private String obtenerFechaFormateada(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(fecha);
	}

	private Paragraph crearDescripcion(Estudiante oEstudiante, Constancia oConstancia) {
		com.itextpdf.text.Font pointFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

		String descripcionConDatos = reemplazarPalabrasClave(oConstancia.getTipo().getDescripcion(), oEstudiante,
				oConstancia);
		Paragraph descripcion = new Paragraph(descripcionConDatos, pointFont);

		return descripcion;
	}

	public void constancia(Constancia constancia, Estudiante estudiante) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int option = fileChooser.showSaveDialog(null);

		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String path = file.getAbsolutePath();

			try {
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document,
						new FileOutputStream(path + "/Constancia_de_" + constancia.getTipo().getNombre().trim() + "_"
								+ estudiante.getUsuario().getNombreCompleto() + ".pdf"));
				document.open();

				com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
				com.itextpdf.text.Font pointFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
				com.itextpdf.text.Font pointFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
				com.itextpdf.text.Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

				String rutaImagen = "/view/img/Utec.png";

				// Crear objeto Image con la ruta de la imagen
				Image imagen = Image.getInstance(getClass().getResource(rutaImagen));
				imagen.scaleToFit(85, 85);

				// Agregar la imagen al documento
				document.add(imagen);

				Paragraph title = new Paragraph("Constancia de " + constancia.getTipo().getNombre(), titleFont);

				title.setAlignment(Element.ALIGN_CENTER);

				document.add(title);
				document.add(Chunk.NEWLINE);

				Paragraph descripcion = crearDescripcion(estudiante, constancia);

				descripcion.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(descripcion);
				document.add(Chunk.NEWLINE);

				Paragraph seExtiende = new Paragraph(
						"A los efectos de ser presentada ante quien corresponda, se extiende dicha constancia.",
						pointFont);

				seExtiende.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(seExtiende);
				document.add(Chunk.NEWLINE);

				byte[] firmaBytes = Buscar
						.analistaDeConstancia(constancia.getIdConstancia(), constancia.getEstado().getIdEstado())
						.getFirma();
				Image imagenFirma = Image.getInstance(firmaBytes);

				// Escalar y alinear la imagen según sea necesario
				imagenFirma.scaleToFit(130, 130);
				imagenFirma.setAlignment(Element.ALIGN_CENTER);

				// Agregar la imagen de firma al documento
				document.add(imagenFirma);
				document.add(Chunk.NEWLINE);

				Paragraph datosAnalista = new Paragraph(
						Buscar.analistaDeConstancia(constancia.getIdConstancia(), constancia.getEstado().getIdEstado())
								.getUsuario().getNombreCompleto() + "\nAnalista de UTEC",
						pointFontBold);
				datosAnalista.setAlignment(Element.ALIGN_CENTER);
				document.add(datosAnalista);
				document.add(Chunk.NEWLINE);

				String rutaSello = "/view/img/ImagenSelloUtec.png";

				// Crear objeto Image con la ruta de la imagen
				Image imagenSello = Image.getInstance(getClass().getResource(rutaSello));
				imagenSello.scaleToFit(85, 85);
				imagenSello.setAlignment(Element.ALIGN_CENTER);

				// Agregar la imagen al documento
				document.add(imagenSello);

				PdfContentByte canvas = writer.getDirectContent();
				canvas.saveState();

				// Dibujar la línea separadora
				canvas.setLineWidth(1f);
				canvas.moveTo(document.left(), document.bottom());
				canvas.lineTo(document.right(), document.bottom());
				canvas.stroke();

				// Restaurar la posición del contenido
				canvas.restoreState();

				// Agregar el pie de página
				Phrase pieDePagina = new Phrase(
						"Av. Italia 6201 Edificio Los Talas - CP 11500 Montevideo, Uruguay - Tel. (+598) 26038832 - secretaria@utec.edu.uy.",
						footerFont);

				// Posicionar el pie de página
				PdfContentByte canvas2 = writer.getDirectContent();
				ColumnText.showTextAligned(canvas2, Element.ALIGN_LEFT, pieDePagina, 40, 20, 0);

				document.add(Chunk.NEWLINE);

				document.close();
				JOptionPane.showMessageDialog(null, "¡PDF generado exitosamente!");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private String reemplazarPalabrasClave(String descripcion, Estudiante oEstudiante, Constancia oConstancia) {
		System.out.println(descripcion);
		// Expresión regular para encontrar palabras clave entre &
		Pattern pattern = Pattern.compile("&([^&]+)&");

		// Crea un objeto Matcher para buscar &palabra& en la descripción
		Matcher matcher = pattern.matcher(descripcion);

		// Crea un objeto StringBuffer para almacenar el resultado final
		StringBuffer sb = new StringBuffer();

		// Itera sobre todas las coincidencias encontradas
		while (matcher.find()) {
			// Obtiene la palabra clave encontrada entre los caracteres &
			String palabraClave = matcher.group(1);

			// Obtiene el reemplazo correspondiente a la palabra clave
			String reemplazo = getAtributo(palabraClave, oEstudiante, oConstancia);

			// Realiza el reemplazo en el objeto StringBuffer
			matcher.appendReplacement(sb, reemplazo);
		}

		// Agrega el resto de la descripción al objeto StringBuffer
		matcher.appendTail(sb);
		return sb.toString();
	}

	private String getAtributo(String palabra, Estudiante oEstudiante, Constancia oConstancia) {

		// Estudiante
		if (palabra.equals("cedula")) {
			return oEstudiante.getUsuario().getDocumento().toString();
		}
		if (palabra.equals("nombres")) {
			return oEstudiante.getUsuario().getNombres();
		}
		if (palabra.equals("apellidos")) {
			return oEstudiante.getUsuario().getApellidos();
		}
		if (palabra.equals("nombreCompleto")) {
			return oEstudiante.getUsuario().getNombreCompleto();
		}
		if (palabra.equals("nombreItr")) {
			return oEstudiante.getUsuario().getItr().getNombre();
		}
		if (palabra.equals("localidadItr")) {
			return oEstudiante.getUsuario().getItr().getDepartamento().getNombre();
		}
		if (palabra.equals("fechaNacimiento")) {
			return obtenerFechaFormateada(oEstudiante.getUsuario().getFechaNacimiento());
		}
		if (palabra.equals("edad")) {
			return oEstudiante.getUsuario().getEdad();
		}
		if (palabra.equals("localidadEstudiante")) {
			return oEstudiante.getUsuario().getLocalidad().getNombre();
		}
		if (palabra.equals("departamentoEstudiante")) {
			return oEstudiante.getUsuario().getDepartamento().getNombre();
		}
		if (palabra.equals("telefono")) {
			return oEstudiante.getUsuario().getTelefono();
		}
		if (palabra.equals("mailPersonal")) {
			return oEstudiante.getUsuario().getMailPersonal();
		}
		if (palabra.equals("mailInstitucional")) {
			return oEstudiante.getUsuario().getMailInstitucional();
		}

		// Constancia
		if (palabra.equals("fechaExpedido")) {
			Date fechaExp = new Date();
			return obtenerFechaFormateada(fechaExp);
		}
		if (palabra.equals("tipoConstancia")) {
			return oConstancia.getTipo().getNombre();
		}

		// Evento
		if (palabra.equals("nombreEvento")) {
			return oConstancia.getEvento().getTitulo();
		}
		if (palabra.equals("fechaInicioEvento")) {
			return obtenerFechaFormateada(oConstancia.getEvento().getFechaHoraInicio());
		}
		if (palabra.equals("fechaFinEvento")) {
			return obtenerFechaFormateada(oConstancia.getEvento().getFechaHoraFin());
		}

		return ""; // Palabra clave no reconocida, retornar cadena vacía
	}

	public void escolaridad(ArrayList<EventoEstudiante> eventoEstudiante, Estudiante estudiante) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int option = fileChooser.showSaveDialog(null);

		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String path = file.getAbsolutePath();

			try {
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
						path + "/Escolaridad_de_" + estudiante.getUsuario().getNombreCompleto() + ".pdf"));
				document.open();

				com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
				com.itextpdf.text.Font pointFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
				com.itextpdf.text.Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

				String rutaImagen = "/view/img/Utec.png";

				// Crear objeto Image con la ruta de la imagen
				Image imagen = Image.getInstance(getClass().getResource(rutaImagen));
				imagen.scaleToFit(85, 85);

				// Agregar la imagen al documento
				document.add(imagen);

				Paragraph title = new Paragraph("Escolaridad de " + estudiante.getUsuario().getNombreCompleto(),
						titleFont);

				title.setAlignment(Element.ALIGN_CENTER);

				document.add(title);
				document.add(Chunk.NEWLINE);

				String descripcionConDatos = reemplazarPalabrasClave(
						"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha cursado los siguientes cursos:",
						estudiante, new Constancia());
				Paragraph descripcion = new Paragraph(descripcionConDatos, pointFont);

				descripcion.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(descripcion);
				document.add(Chunk.NEWLINE);

				String escolaridad = "";
				BigDecimal notaTotal = BigDecimal.ZERO; // Inicializamos notaTotal en 0
				int contadorNotas = 0; // Inicializamos el contador de notas en 0

				for (var aux : eventoEstudiante) {
					if (aux.getAsistencia().equals("S")) {
						notaTotal = notaTotal.add(aux.getCalificacion()); // Sumamos la calificación a notaTotal
						contadorNotas++; // Incrementamos el contador de notas
						escolaridad += Buscar.eventoPorId(aux.getId().getIdEvento()).getTitulo() + " - Nota: "
								+ aux.getCalificacion() + "\n";
					}
				}
				BigDecimal promedio = notaTotal.divide(BigDecimal.valueOf(contadorNotas), 2, RoundingMode.HALF_UP);

				Paragraph notasCursos = new Paragraph(escolaridad);
				notasCursos.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(notasCursos);
				document.add(Chunk.NEWLINE);

				Paragraph promedioNotas = new Paragraph("El promedio de la nota es: " + promedio.toPlainString());
				promedioNotas.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(promedioNotas);

				document.add(Chunk.NEWLINE);

				String rutaSello = "/view/img/ImagenSelloUtec.png";

				// Crear objeto Image con la ruta de la imagen
				Image imagenSello = Image.getInstance(getClass().getResource(rutaSello));
				imagenSello.scaleToFit(85, 85);
				imagenSello.setAlignment(Element.ALIGN_CENTER);

				// Agregar la imagen al documento
				document.add(imagenSello);

				PdfContentByte canvas = writer.getDirectContent();
				canvas.saveState();

				// Dibujar la línea separadora
				canvas.setLineWidth(1f);
				canvas.moveTo(document.left(), document.bottom());
				canvas.lineTo(document.right(), document.bottom());
				canvas.stroke();

				// Restaurar la posición del contenido
				canvas.restoreState();

				// Agregar el pie de página
				Phrase pieDePagina = new Phrase(
						"Av. Italia 6201 Edificio Los Talas - CP 11500 Montevideo, Uruguay - Tel. (+598) 26038832 - secretaria@utec.edu.uy.",
						footerFont);

				// Posicionar el pie de página
				PdfContentByte canvas2 = writer.getDirectContent();
				ColumnText.showTextAligned(canvas2, Element.ALIGN_LEFT, pieDePagina, 40, 20, 0);

				document.add(Chunk.NEWLINE);

				document.close();
				JOptionPane.showMessageDialog(null, "¡PDF generado exitosamente!");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
