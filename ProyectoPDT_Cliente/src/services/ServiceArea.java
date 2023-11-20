package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Area;
import com.services.usuario.tutor.area.AreaBeanRemote;

public class ServiceArea {
	
	private static AreaBeanRemote getService() {
		try {
			AreaBeanRemote areaBean = (AreaBeanRemote) InitialContext.doLookup("ejb:/ProyectoPDT_EJB/AreaBean!com.services.usuario.tutor.area.AreaBeanRemote");
			return areaBean;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Area crearArea(Area oArea) {
		try {
			var areaBean = getService();
			return areaBean.crearArea(oArea);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<Area> listarAreas(){
		try {
			var areaBean = getService();
			return areaBean.listarAreas();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<Area> listarAreasFiltro(String filtro){
		try {
			var areaBean = getService();
			return areaBean.listarAreasFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

}
