package view.utils;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.Itr;

public class TableModelItr implements TableModel {

	private ArrayList<Itr> listaDeItr;

	public TableModelItr(ArrayList<Itr> listaDeItr) {
		this.listaDeItr = listaDeItr;
	}

	public int getRowCount() {
		return listaDeItr.size();
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int columnIndex) {

		String nombreColumna = null;

		switch (columnIndex) {

		case 0:
			nombreColumna = "Nombre";
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

		Itr oItr = listaDeItr.get(rowIndex);

		String valor = null;

		switch (columnIndex) {

		case 0:
			valor = oItr.getNombre();
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
