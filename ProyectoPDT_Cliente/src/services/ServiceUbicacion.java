package services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.entities.Departamento;
import com.entities.Localidad;
import com.services.ubicacion.departamento.DepartamentoBeanRemote;
import com.services.ubicacion.localidad.LocalidadBeanRemote;

public class ServiceUbicacion {

	private static DepartamentoBeanRemote getServiceDepartamento() {
		try {
			DepartamentoBeanRemote departamentoBean = (DepartamentoBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_EJB/DepartamentoBean!com.services.ubicacion.departamento.DepartamentoBeanRemote");
			return departamentoBean;
		} catch (Exception e) {
			return null;
		}

	}

	private static LocalidadBeanRemote getServiceLocalidad() {
		try {
			LocalidadBeanRemote departamentoLocalidad = (LocalidadBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_EJB/LocalidadBean!com.services.ubicacion.localidad.LocalidadBeanRemote");
			return departamentoLocalidad;
		} catch (Exception e) {
			return null;
		}
	}

	public static Departamento crearDepartamento(Departamento oDepartamento) {
		try {
			var departamentoBean = getServiceDepartamento();
			return departamentoBean.crearDepartamento(oDepartamento);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Departamento> listarDepartamentos() {
		try {
			var departamentoBean = getServiceDepartamento();
			return departamentoBean.listarDepartamentos();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<Departamento> listarDepartamentosFiltro(String filtro) {
		try {
			var departamentoBean = getServiceDepartamento();
			return departamentoBean.listarDepartamentosFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Localidad crearLocalidad(Localidad oLocalidad) {
		try {
			var localidadBean = getServiceLocalidad();
			return localidadBean.crearLocalidad(oLocalidad);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Localidad> listarLocalidades() {
		try {
			var localidadBean = getServiceLocalidad();
			return localidadBean.listarLocalidades();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<Localidad> listarLocalidadesFiltro(String filtro) {
		try {
			var localidadBean = getServiceLocalidad();
			return localidadBean.listarLocalidadesFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

}
