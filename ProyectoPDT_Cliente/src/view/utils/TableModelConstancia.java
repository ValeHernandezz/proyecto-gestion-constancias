package view.utils;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.Constancia;

import context.helpers.Buscar;

public class TableModelConstancia implements TableModel {

	private ArrayList<Constancia> listaDeConstancias;

	public TableModelConstancia(ArrayList<Constancia> listaDeConstancias) {
		this.listaDeConstancias = listaDeConstancias;
	}

	public int getRowCount() {
		return listaDeConstancias.size();
	}

	public int getColumnCount() {
		return 8;
	}

	public String getColumnName(int columnIndex) {

		String nombreColumna = null;

		switch (columnIndex) {

		case 0:
			nombreColumna = "Tipo de Constancia";
			break;

		case 1:
			nombreColumna = "Evento";
			break;

		case 2:
			nombreColumna = "Detalle";
			break;

		case 3:
			nombreColumna = "Fecha y Hora";
			break;

		case 4:
			nombreColumna = "Nombre estudiante";
			break;

		case 5:
			nombreColumna = "Documento estudiante";
			break;
		case 6:
			nombreColumna = "Estado";
			break;
		case 7:
			nombreColumna = "Analista";
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

		Constancia oConstancia = listaDeConstancias.get(rowIndex);

		String valor = null;

		switch (columnIndex) {

		case 0:
			valor = oConstancia.getTipo().getNombre();
			break;

		case 1:
			valor = oConstancia.getEvento().getTitulo();
			break;

		case 2:
			valor = oConstancia.getDetalle();
			break;

		case 3:
			valor = oConstancia.getFechaHora().toLocaleString();
			break;

		case 4:
			valor = oConstancia.getEstudiante().getUsuario().getNombreCompleto();
			break;

		case 5:
			valor = oConstancia.getEstudiante().getUsuario().getDocumento().toString();
			break;
		case 6:
			valor = oConstancia.getEstado().getDescripcion();
			break;
		case 7:
			valor = Buscar.analistaDeConstancia(oConstancia.getIdConstancia(),
					oConstancia.getEstado().getIdEstado()) == null ? "-"
							: Buscar.analistaDeConstancia(oConstancia.getIdConstancia(),
									oConstancia.getEstado().getIdEstado()).getUsuario().getNombreCompleto();
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
