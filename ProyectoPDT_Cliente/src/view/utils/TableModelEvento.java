package view.utils;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.Evento;

public class TableModelEvento implements TableModel {

	private ArrayList<Evento> listaDeEventos;

	public TableModelEvento(ArrayList<Evento> listaDeEventos) {
		this.listaDeEventos = listaDeEventos;
	}

	public int getRowCount() {
		return listaDeEventos.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int columnIndex) {

		String nombreColumna = null;

		switch (columnIndex) {

		case 0:
			nombreColumna = "Nombre del Evento";
			break;

		case 1:
			nombreColumna = "Fecha de Inicio";
			break;

		case 2:
			nombreColumna = "Fecha de Final";
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

		Evento oEvento = listaDeEventos.get(rowIndex);

		String valor = null;

		switch (columnIndex) {

		case 0:
			valor = oEvento.getTitulo();
			break;

		case 1:
			valor = oEvento.getFechaHoraInicio().toLocaleString();
			break;

		case 2:
			valor = oEvento.getFechaHoraFin().toLocaleString();
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
