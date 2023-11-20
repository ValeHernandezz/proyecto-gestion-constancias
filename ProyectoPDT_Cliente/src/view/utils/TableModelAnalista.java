package view.utils;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.Analista;

public class TableModelAnalista implements TableModel {

	private ArrayList<Analista> listaDeAnalistas;

	public TableModelAnalista(ArrayList<Analista> listaDeAnalistas) {
		this.listaDeAnalistas = listaDeAnalistas;
	}

	public int getRowCount() {
		return listaDeAnalistas.size();
	}

	public int getColumnCount() {
		return 11;
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

		Analista oAnalista = listaDeAnalistas.get(rowIndex);

		String valor = null;

		switch (columnIndex) {

		case 0:
			valor = String.valueOf(oAnalista.getUsuario().getDocumento());
			break;

		case 1:
			valor = oAnalista.getUsuario().getSegundoNombre() != null
					? oAnalista.getUsuario().getPrimerNombre() + " " + oAnalista.getUsuario().getSegundoNombre()
					: oAnalista.getUsuario().getPrimerNombre();
			break;

		case 2:
			valor = oAnalista.getUsuario().getSegundoApellido() != null
					? oAnalista.getUsuario().getPrimerApellido() + " " + oAnalista.getUsuario().getSegundoApellido()
					: oAnalista.getUsuario().getPrimerApellido();
			break;
		case 3:
			valor = oAnalista.getUsuario().getMailPersonal();
			break;

		case 4:
			valor = oAnalista.getUsuario().getMailInstitucional();
			break;

		case 5:
			valor = oAnalista.getUsuario().getGenero().getNombre();
			break;

		case 6:
			valor = oAnalista.getUsuario().getDepartamento().getNombre();
			break;

		case 7:
			valor = oAnalista.getUsuario().getLocalidad().getNombre();
			break;

		case 8:
			valor = oAnalista.getUsuario().getItr().getNombre();
			break;

		case 9:
			valor = oAnalista.getUsuario().getTelefono();
			break;

		case 10:
			valor = oAnalista.getUsuario().getFechaNacimiento().toString();
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
