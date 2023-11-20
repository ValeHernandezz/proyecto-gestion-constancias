package view.utils;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.Estudiante;

public class TableModelEstudiante implements TableModel {

	private ArrayList<Estudiante> listaDeEstudiantes;

	public TableModelEstudiante(ArrayList<Estudiante> listaDeEstudiantes) {
		this.listaDeEstudiantes = listaDeEstudiantes;
	}

	public int getRowCount() {
		return listaDeEstudiantes.size();
	}

	public int getColumnCount() {
		return 13;
	}

	public String getColumnName(int columnIndex) {

		String nombreColumna = null;

		switch (columnIndex) {

		case 0:
			nombreColumna = "Cédula";
			break;

		case 1:
			nombreColumna = "Nombres";
			break;

		case 2:
			nombreColumna = "Apellidos";
			break;

		case 3:
			nombreColumna = "Mail personal";
			break;

		case 4:
			nombreColumna = "Mail institucional";
			break;

		case 5:
			nombreColumna = "Género";
			break;

		case 6:
			nombreColumna = "Departamento";
			break;

		case 7:
			nombreColumna = "Localidad";
			break;

		case 8:
			nombreColumna = "ITR";
			break;

		case 9:
			nombreColumna = "Teléfono";
			break;

		case 10:
			nombreColumna = "Generación";
			break;

		case 11:
			nombreColumna = "Semestre";
			break;
		case 12:
			nombreColumna = "Fecha nacimiento";
			break;
		}

		return nombreColumna;

	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		Estudiante oEstudiante = listaDeEstudiantes.get(rowIndex);

		String valor = null;

		switch (columnIndex) {

		case 0:
			valor = String.valueOf(oEstudiante.getUsuario().getDocumento());
			break;

		case 1:
			valor = oEstudiante.getUsuario().getSegundoNombre() != null
					? oEstudiante.getUsuario().getPrimerNombre() + " " + oEstudiante.getUsuario().getSegundoNombre()
					: oEstudiante.getUsuario().getPrimerNombre();
			break;

		case 2:
			valor = oEstudiante.getUsuario().getSegundoApellido() != null
					? oEstudiante.getUsuario().getPrimerApellido() + " " + oEstudiante.getUsuario().getSegundoApellido()
					: oEstudiante.getUsuario().getPrimerApellido();
			break;
		case 3:
			valor = oEstudiante.getUsuario().getMailPersonal();
			break;

		case 4:
			valor = oEstudiante.getUsuario().getMailInstitucional();
			break;

		case 5:
			valor = oEstudiante.getUsuario().getGenero().getNombre();
			break;

		case 6:
			valor = oEstudiante.getUsuario().getDepartamento().getNombre();
			break;

		case 7:
			valor = oEstudiante.getUsuario().getLocalidad().getNombre();
			break;

		case 8:
			valor = oEstudiante.getUsuario().getItr().getNombre();
			break;

		case 9:
			valor = oEstudiante.getUsuario().getTelefono();
			break;

		case 10:
			valor = oEstudiante.getGeneracion();
			break;

		case 11:
			valor = String.valueOf(oEstudiante.getSemestre());
			break;

		case 12:
			valor = oEstudiante.getUsuario().getFechaNacimiento().toString();
			break;

		}

		return valor;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
	}

	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
