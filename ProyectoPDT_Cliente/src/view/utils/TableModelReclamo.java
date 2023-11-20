package view.utils;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.Reclamo;

import context.helpers.Buscar;

public class TableModelReclamo implements TableModel {
	private ArrayList<Reclamo> listaDeReclamo;

	public TableModelReclamo(ArrayList<Reclamo> listaDeReclamo) {
		this.listaDeReclamo = listaDeReclamo;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaDeReclamo.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		String nombreColumna = null;

		switch (columnIndex) {
		case 0:
			nombreColumna = "ID";
			break;
		case 1:
			nombreColumna = "Titulo";
			break;
		case 2:
			nombreColumna = "Detalle";
			break;

		case 3:
			nombreColumna = "Fecha";
			break;

		case 4:
			nombreColumna = "Evento";
			break;
		case 5:
			nombreColumna = "Estado";
			break;
		}

		return nombreColumna;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reclamo oReclamo = listaDeReclamo.get(rowIndex);

		String valor = null;

		switch (columnIndex) {
		case 0:
			valor = String.valueOf(oReclamo.getIdReclamo());
			break;
		case 1:
			valor = oReclamo.getTitulo();
			break;
		case 2:
			valor = oReclamo.getDetalle();
			break;
		case 3:
			valor = oReclamo.getFechaHora().toString();
			break;
		case 4:
			valor = Buscar.eventoPorId(oReclamo.getIdEvento()).getTitulo();
			break;
		case 5:
			valor = Buscar.estadoPorId(oReclamo.getIdEstado());
		}
		return valor;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
