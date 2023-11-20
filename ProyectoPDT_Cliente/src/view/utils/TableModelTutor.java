package view.utils;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.Tutor;

public class TableModelTutor implements TableModel {
	private ArrayList<Tutor> listaDeTutores;

	public TableModelTutor(ArrayList<Tutor> listaDeTutores) {
		this.listaDeTutores = listaDeTutores;
	}

	public int getRowCount() {
		return listaDeTutores.size();
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
			nombreColumna = "Área";
			break;

		case 11:
			nombreColumna = "Rol";
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

		Tutor oTutor = listaDeTutores.get(rowIndex);

		String valor = null;

		switch (columnIndex) {

		case 0:
			valor = String.valueOf(oTutor.getUsuario().getDocumento());
			break;

		case 1:
			valor = oTutor.getUsuario().getSegundoNombre() != null
					? oTutor.getUsuario().getPrimerNombre() + " " + oTutor.getUsuario().getSegundoNombre()
					: oTutor.getUsuario().getPrimerNombre();
			break;

		case 2:
			valor = oTutor.getUsuario().getSegundoApellido() != null
					? oTutor.getUsuario().getPrimerApellido() + " " + oTutor.getUsuario().getSegundoApellido()
					: oTutor.getUsuario().getPrimerApellido();
			break;
		case 3:
			valor = oTutor.getUsuario().getMailPersonal();
			break;

		case 4:
			valor = oTutor.getUsuario().getMailInstitucional();
			break;

		case 5:
			valor = oTutor.getUsuario().getGenero().getNombre();
			break;

		case 6:
			valor = oTutor.getUsuario().getDepartamento().getNombre();
			break;

		case 7:
			valor = oTutor.getUsuario().getLocalidad().getNombre();
			break;

		case 8:
			valor = oTutor.getUsuario().getItr().getNombre();
			break;

		case 9:
			valor = oTutor.getUsuario().getTelefono();
			break;

		case 10:
			valor = oTutor.getArea().getDescripcion();
			break;

		case 11:
			valor = oTutor.getUsuario().getRol().getDescripcion();
			break;

		case 12:
			valor = oTutor.getUsuario().getFechaNacimiento().toString();
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
