package view.utils;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.Usuario;

public class TableModelUsuario implements TableModel {

	private ArrayList<Usuario> listaDeUsuarios;

	public TableModelUsuario(ArrayList<Usuario> listaPersonas) {
		this.listaDeUsuarios = listaPersonas;
	}

	public int getRowCount() {
		return listaDeUsuarios.size();
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
			nombreColumna = "Rol";
			break;

		case 4:
			nombreColumna = "Mail personal";
			break;

		case 5:
			nombreColumna = "Mail institucional";
			break;

		case 6:
			nombreColumna = "Género";
			break;

		case 7:
			nombreColumna = "Departamento";
			break;

		case 8:
			nombreColumna = "Localidad";
			break;

		case 9:
			nombreColumna = "ITR";
			break;

		case 10:
			nombreColumna = "Teléfono";
			break;

		}

		return nombreColumna;

	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex != 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		Usuario oUsuario = listaDeUsuarios.get(rowIndex);

		String valor = null;

		switch (columnIndex) {

		case 0:
			valor = String.valueOf(oUsuario.getDocumento());
			break;

		case 1:
			valor = oUsuario.getSegundoNombre() != null ? oUsuario.getPrimerNombre() + " " + oUsuario.getSegundoNombre() : oUsuario.getPrimerNombre();
			break;

		case 2:
			valor = oUsuario.getSegundoApellido() != null ? oUsuario.getPrimerApellido() + " " + oUsuario.getSegundoApellido() : oUsuario.getPrimerApellido();
			break;

		case 3:
			valor = oUsuario.getRol().getDescripcion();
			break;

		case 4:
			valor = oUsuario.getMailPersonal();
			break;

		case 5:
			valor = oUsuario.getMailInstitucional();
			break;

		case 6:
			valor = oUsuario.getGenero().getNombre();
			break;

		case 7:
			valor = oUsuario.getDepartamento().getNombre();
			break;

		case 8:
			valor = oUsuario.getLocalidad().getNombre();
			break;

		case 9:
			valor = oUsuario.getItr().getNombre();
			break;

		case 10:
			valor = oUsuario.getTelefono();
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