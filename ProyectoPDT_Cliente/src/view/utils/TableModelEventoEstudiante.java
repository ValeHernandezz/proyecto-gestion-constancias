package view.utils;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.entities.EventoEstudiante;

import context.helpers.Buscar;

public class TableModelEventoEstudiante implements TableModel {

	private ArrayList<EventoEstudiante> listaDeEventoEstudiante;

	public TableModelEventoEstudiante(ArrayList<EventoEstudiante> listaDeEventoEstudiante) {
		this.listaDeEventoEstudiante = listaDeEventoEstudiante;
	}

	public int getRowCount() {
		return listaDeEventoEstudiante.size();
	}

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int columnIndex) {

		String nombreColumna = null;

		switch (columnIndex) {

		case 0:
			nombreColumna = "Evento";
			break;

		case 1:
			nombreColumna = "Fecha de Inicio";
			break;
			
		case 2:
			nombreColumna = "Fecha de Fin";
			break;

		case 3:
			nombreColumna = "Asistencia";
			break;
		case 4:
			nombreColumna = "Calificación";
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

		EventoEstudiante oEventoEstudiante = listaDeEventoEstudiante.get(rowIndex);

		String valor = null;

		switch (columnIndex) {

		case 0:
			valor = String.valueOf(Buscar.eventoPorId(oEventoEstudiante.getId().getIdEvento()).getTitulo());
			break;

		case 1:
			valor = String.valueOf(Buscar.eventoPorId(oEventoEstudiante.getId().getIdEvento()).getFechaHoraInicio());
			break;
			
		case 2:
			valor = String.valueOf(Buscar.eventoPorId(oEventoEstudiante.getId().getIdEvento()).getFechaHoraFin());
			break;

		case 3:
			valor = oEventoEstudiante.getAsistencia().equals("S") ? "Asistió" : "No asistió";
			break;
			
		case 4:
			valor = oEventoEstudiante.getCalificacion().toString();
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
