package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Genero;
import com.services.usuario.genero.GeneroBeanRemote;

public class ServiceGenero {

	private static GeneroBeanRemote getService() {
		try {
			GeneroBeanRemote generoBean = (GeneroBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_EJB/GeneroBean!com.services.usuario.genero.GeneroBeanRemote");
			return generoBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static Genero crearGenero(Genero oGenero) {
		try {
			var generoBean = getService();
			return generoBean.crearGenero(oGenero);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Genero> listarGeneros() {
		try {
			var generoBean = getService();
			return generoBean.listarGeneros();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<Genero> listarGenerosFiltro(String filtro){
		try {
			var generoBean = getService();
			return generoBean.listarGenerosFiltro(filtro);			
		} catch (Exception e) {
			return null;
		}
	}

}
