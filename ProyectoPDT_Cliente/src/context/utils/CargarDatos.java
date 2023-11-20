package context.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.entities.Analista;
import com.entities.Area;
import com.entities.Constancia;
import com.entities.Departamento;
import com.entities.Estado;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.EventoEstudiante;
import com.entities.EventoEstudiantePK;
import com.entities.Genero;
import com.entities.Itr;
import com.entities.Localidad;
import com.entities.Rol;
import com.entities.Tipo;
import com.entities.Tutor;
import com.entities.Usuario;

import context.helpers.Crear;
import services.ServiceArea;
import services.ServiceConstancia;
import services.ServiceEstado;
import services.ServiceEstudiante;
import services.ServiceEvento;
import services.ServiceEventoEstudiante;
import services.ServiceGenero;
import services.ServiceRol;
import services.ServiceTipo;
import services.ServiceUbicacion;
import services.ServiceItr;

public class CargarDatos {

	public static void empezar() {

		var contador = ServiceRol.listarRoles().size();

		if (contador != 0) {
			return;
		}

		Rol oRol = new Rol("Analista");
		Rol oRol2 = new Rol("Estudiante");
		Rol oRol3 = new Rol("Tutor");
		Rol oRol4 = new Rol("Encargado");

		var rolCreado = ServiceRol.crearRol(oRol);
		var rolCreado2 = ServiceRol.crearRol(oRol2);
		var rolCreado3 = ServiceRol.crearRol(oRol3);
		var rolCreado4 = ServiceRol.crearRol(oRol4);

		Genero genero = new Genero("Masculino");
		Genero genero2 = new Genero("Femenino");
		Genero genero3 = new Genero("Otro");

		var generoCreado = ServiceGenero.crearGenero(genero);
		var generoCreado2 = ServiceGenero.crearGenero(genero2);
		var generoCreado3 = ServiceGenero.crearGenero(genero3);

		Departamento departamento = new Departamento("Artigas");
		Departamento departamento2 = new Departamento("Canelones");
		Departamento departamento3 = new Departamento("Cerro Largo");
		Departamento departamento4 = new Departamento("Colonia");
		Departamento departamento5 = new Departamento("Durazno");
		Departamento departamento6 = new Departamento("Flores");
		Departamento departamento7 = new Departamento("Florida");
		Departamento departamento8 = new Departamento("Lavalleja");
		Departamento departamento9 = new Departamento("Maldonado");
		Departamento departamento10 = new Departamento("Montevideo");
		Departamento departamento11 = new Departamento("Paysandú");
		Departamento departamento12 = new Departamento("Río Negro");
		Departamento departamento13 = new Departamento("Rivera");
		Departamento departamento14 = new Departamento("Rocha");
		Departamento departamento15 = new Departamento("Salto");
		Departamento departamento16 = new Departamento("San José");
		Departamento departamento17 = new Departamento("Soriano");
		Departamento departamento18 = new Departamento("Tacuarembó");
		Departamento departamento19 = new Departamento("Treinta y Tres");

		var departamentoCreado = ServiceUbicacion.crearDepartamento(departamento);
		var departamentoCreado2 = ServiceUbicacion.crearDepartamento(departamento2);
		var departamentoCreado3 = ServiceUbicacion.crearDepartamento(departamento3);
		var departamentoCreado4 = ServiceUbicacion.crearDepartamento(departamento4);
		var departamentoCreado5 = ServiceUbicacion.crearDepartamento(departamento5);
		var departamentoCreado6 = ServiceUbicacion.crearDepartamento(departamento6);
		var departamentoCreado7 = ServiceUbicacion.crearDepartamento(departamento7);
		var departamentoCreado8 = ServiceUbicacion.crearDepartamento(departamento8);
		var departamentoCreado9 = ServiceUbicacion.crearDepartamento(departamento9);
		var departamentoCreado10 = ServiceUbicacion.crearDepartamento(departamento10);
		var departamentoCreado11 = ServiceUbicacion.crearDepartamento(departamento11);
		var departamentoCreado12 = ServiceUbicacion.crearDepartamento(departamento12);
		var departamentoCreado13 = ServiceUbicacion.crearDepartamento(departamento13);
		var departamentoCreado14 = ServiceUbicacion.crearDepartamento(departamento14);
		var departamentoCreado15 = ServiceUbicacion.crearDepartamento(departamento15);
		var departamentoCreado16 = ServiceUbicacion.crearDepartamento(departamento16);
		var departamentoCreado17 = ServiceUbicacion.crearDepartamento(departamento17);
		var departamentoCreado18 = ServiceUbicacion.crearDepartamento(departamento18);
		var departamentoCreado19 = ServiceUbicacion.crearDepartamento(departamento19);

		Localidad localidad4 = new Localidad("Artigas", departamentoCreado);
		Localidad localidad5 = new Localidad("Bella Unión", departamentoCreado);
		Localidad localidad6 = new Localidad("Tomás Gomensoro", departamentoCreado);

		Localidad localidad7 = new Localidad("Canelones", departamentoCreado2);
		Localidad localidad8 = new Localidad("Ciudad de la Costa", departamentoCreado2);
		Localidad localidad9 = new Localidad("Las Piedras", departamentoCreado2);

		Localidad localidad10 = new Localidad("Melo", departamentoCreado3);
		Localidad localidad11 = new Localidad("Río Branco", departamentoCreado3);
		Localidad localidad12 = new Localidad("Fraile Muerto", departamentoCreado3);

		Localidad localidad13 = new Localidad("Colonia del Sacramento", departamentoCreado4);
		Localidad localidad14 = new Localidad("Nueva Helvecia", departamentoCreado4);
		Localidad localidad15 = new Localidad("Carmelo", departamentoCreado4);

		Localidad localidad16 = new Localidad("Durazno", departamentoCreado5);
		Localidad localidad17 = new Localidad("Sarandí del Yí", departamentoCreado5);
		Localidad localidad18 = new Localidad("Villa del Carmen", departamentoCreado5);

		Localidad localidad19 = new Localidad("Trinidad", departamentoCreado6);
		Localidad localidad20 = new Localidad("Ismael Cortinas", departamentoCreado6);
		Localidad localidad21 = new Localidad("Andresito", departamentoCreado6);

		Localidad localidad22 = new Localidad("Florida", departamentoCreado7);
		Localidad localidad23 = new Localidad("Sarandí Grande", departamentoCreado7);
		Localidad localidad24 = new Localidad("Casupá", departamentoCreado7);

		Localidad localidad = new Localidad("Minas", departamentoCreado8);
		Localidad localidad2 = new Localidad("José Pedro Varela", departamentoCreado8);
		Localidad localidad3 = new Localidad("Solís de Mataojo", departamentoCreado8);

		Localidad localidad25 = new Localidad("Maldonado", departamentoCreado9);
		Localidad localidad26 = new Localidad("Punta del Este", departamentoCreado9);
		Localidad localidad27 = new Localidad("San Carlos", departamentoCreado9);

		Localidad localidad28 = new Localidad("Montevideo", departamentoCreado10);

		Localidad localidad31 = new Localidad("Paysandú", departamentoCreado11);
		Localidad localidad32 = new Localidad("Guichón", departamentoCreado11);
		Localidad localidad33 = new Localidad("Quebracho", departamentoCreado11);

		Localidad localidad34 = new Localidad("Fray Bentos", departamentoCreado12);
		Localidad localidad35 = new Localidad("Young", departamentoCreado12);
		Localidad localidad36 = new Localidad("Nuevo Berlín", departamentoCreado12);

		Localidad localidad37 = new Localidad("Rivera", departamentoCreado13);
		Localidad localidad38 = new Localidad("Tranqueras", departamentoCreado13);
		Localidad localidad39 = new Localidad("Minas de Corrales", departamentoCreado13);

		Localidad localidad40 = new Localidad("Lascano", departamentoCreado14);
		Localidad localidad41 = new Localidad("Chuy", departamentoCreado14);
		Localidad localidad42 = new Localidad("Castillos", departamentoCreado14);

		Localidad localidad43 = new Localidad("Salto", departamentoCreado15);
		Localidad localidad44 = new Localidad("Constitución", departamentoCreado15);
		Localidad localidad45 = new Localidad("Belén", departamentoCreado15);

		Localidad localidad46 = new Localidad("San José de Mayo", departamentoCreado16);
		Localidad localidad47 = new Localidad("Ciudad del Plata", departamentoCreado16);
		Localidad localidad48 = new Localidad("Libertad", departamentoCreado16);

		Localidad localidad49 = new Localidad("Mercedes", departamentoCreado17);
		Localidad localidad50 = new Localidad("Dolores", departamentoCreado17);
		Localidad localidad51 = new Localidad("Cardona", departamentoCreado17);

		Localidad localidad52 = new Localidad("Tacuarembó", departamentoCreado18);
		Localidad localidad53 = new Localidad("Paso de los Toros", departamentoCreado18);
		Localidad localidad54 = new Localidad("San Gregorio de Polanco", departamentoCreado18);

		Localidad localidad55 = new Localidad("Treinta y Tres", departamentoCreado19);
		Localidad localidad56 = new Localidad("Vergara", departamentoCreado19);
		Localidad localidad57 = new Localidad("Santa Clara De Olimar", departamentoCreado19);

		var localidadCreada = ServiceUbicacion.crearLocalidad(localidad);
		var localidadCreada2 = ServiceUbicacion.crearLocalidad(localidad2);
		var localidadCreada3 = ServiceUbicacion.crearLocalidad(localidad3);
		var localidadCreada4 = ServiceUbicacion.crearLocalidad(localidad4);
		var localidadCreada5 = ServiceUbicacion.crearLocalidad(localidad5);
		var localidadCreada6 = ServiceUbicacion.crearLocalidad(localidad6);
		var localidadCreada7 = ServiceUbicacion.crearLocalidad(localidad7);
		var localidadCreada8 = ServiceUbicacion.crearLocalidad(localidad8);
		var localidadCreada9 = ServiceUbicacion.crearLocalidad(localidad9);
		var localidadCreada10 = ServiceUbicacion.crearLocalidad(localidad10);
		var localidadCreada11 = ServiceUbicacion.crearLocalidad(localidad11);
		var localidadCreada12 = ServiceUbicacion.crearLocalidad(localidad12);
		var localidadCreada13 = ServiceUbicacion.crearLocalidad(localidad13);
		var localidadCreada14 = ServiceUbicacion.crearLocalidad(localidad14);
		var localidadCreada15 = ServiceUbicacion.crearLocalidad(localidad15);
		var localidadCreada16 = ServiceUbicacion.crearLocalidad(localidad16);
		var localidadCreada17 = ServiceUbicacion.crearLocalidad(localidad17);
		var localidadCreada18 = ServiceUbicacion.crearLocalidad(localidad18);
		var localidadCreada19 = ServiceUbicacion.crearLocalidad(localidad19);
		var localidadCreada20 = ServiceUbicacion.crearLocalidad(localidad20);
		var localidadCreada21 = ServiceUbicacion.crearLocalidad(localidad21);
		var localidadCreada22 = ServiceUbicacion.crearLocalidad(localidad22);
		var localidadCreada23 = ServiceUbicacion.crearLocalidad(localidad23);
		var localidadCreada24 = ServiceUbicacion.crearLocalidad(localidad24);
		var localidadCreada25 = ServiceUbicacion.crearLocalidad(localidad25);
		var localidadCreada26 = ServiceUbicacion.crearLocalidad(localidad26);
		var localidadCreada27 = ServiceUbicacion.crearLocalidad(localidad27);
		var localidadCreada28 = ServiceUbicacion.crearLocalidad(localidad28);
		var localidadCreada31 = ServiceUbicacion.crearLocalidad(localidad31);
		var localidadCreada32 = ServiceUbicacion.crearLocalidad(localidad32);
		var localidadCreada33 = ServiceUbicacion.crearLocalidad(localidad33);
		var localidadCreada34 = ServiceUbicacion.crearLocalidad(localidad34);
		var localidadCreada35 = ServiceUbicacion.crearLocalidad(localidad35);
		var localidadCreada36 = ServiceUbicacion.crearLocalidad(localidad36);
		var localidadCreada37 = ServiceUbicacion.crearLocalidad(localidad37);
		var localidadCreada38 = ServiceUbicacion.crearLocalidad(localidad38);
		var localidadCreada39 = ServiceUbicacion.crearLocalidad(localidad39);
		var localidadCreada40 = ServiceUbicacion.crearLocalidad(localidad40);
		var localidadCreada41 = ServiceUbicacion.crearLocalidad(localidad41);
		var localidadCreada42 = ServiceUbicacion.crearLocalidad(localidad42);
		var localidadCreada43 = ServiceUbicacion.crearLocalidad(localidad43);
		var localidadCreada44 = ServiceUbicacion.crearLocalidad(localidad44);
		var localidadCreada45 = ServiceUbicacion.crearLocalidad(localidad45);
		var localidadCreada46 = ServiceUbicacion.crearLocalidad(localidad46);
		var localidadCreada47 = ServiceUbicacion.crearLocalidad(localidad47);
		var localidadCreada48 = ServiceUbicacion.crearLocalidad(localidad48);
		var localidadCreada49 = ServiceUbicacion.crearLocalidad(localidad49);
		var localidadCreada50 = ServiceUbicacion.crearLocalidad(localidad50);
		var localidadCreada51 = ServiceUbicacion.crearLocalidad(localidad51);
		var localidadCreada52 = ServiceUbicacion.crearLocalidad(localidad52);
		var localidadCreada53 = ServiceUbicacion.crearLocalidad(localidad53);
		var localidadCreada54 = ServiceUbicacion.crearLocalidad(localidad54);
		var localidadCreada55 = ServiceUbicacion.crearLocalidad(localidad55);
		var localidadCreada56 = ServiceUbicacion.crearLocalidad(localidad56);
		var localidadCreada57 = ServiceUbicacion.crearLocalidad(localidad57);

		Itr itr = new Itr("ITR Suroeste", departamentoCreado, "S");
		Itr itr2 = new Itr("ITR Centro-Sur", departamentoCreado2, "S");
		Itr itr3 = new Itr("ITR Norte", departamentoCreado3, "S");

		var itrCreada = ServiceItr.crearItr(itr);
		var itrCreada2 = ServiceItr.crearItr(itr2);
		var itrCreada3 = ServiceItr.crearItr(itr3);

		// Edad Martina
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2003);
		calendar.set(Calendar.MONTH, Calendar.MAY);
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		Date dateMartina = calendar.getTime();

		// Edad Rony
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.YEAR, 2003);
		calendar2.set(Calendar.MONTH, Calendar.NOVEMBER);
		calendar2.set(Calendar.DAY_OF_MONTH, 06);
		Date dateRony = calendar2.getTime();

		// Edad Matias
		Calendar calendar3 = Calendar.getInstance();
		calendar3.set(Calendar.YEAR, 2003);
		calendar3.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar3.set(Calendar.DAY_OF_MONTH, 23);
		Date dateMatias = calendar3.getTime();

		// Edad Valentina
		Calendar calendar4 = Calendar.getInstance();
		calendar4.set(Calendar.YEAR, 2003);
		calendar4.set(Calendar.MONTH, Calendar.OCTOBER);
		calendar4.set(Calendar.DAY_OF_MONTH, 12);
		Date dateValentina = calendar4.getTime();

		// Edad Pepe
		Calendar calendar5 = Calendar.getInstance();
		calendar5.set(Calendar.YEAR, 2000);
		calendar5.set(Calendar.MONTH, Calendar.JANUARY);
		calendar5.set(Calendar.DAY_OF_MONTH, 21);
		Date datePepe = calendar5.getTime();

		// Edad Nico
		Calendar calendar6 = Calendar.getInstance();
		calendar6.set(Calendar.YEAR, 1999);
		calendar6.set(Calendar.MONTH, Calendar.NOVEMBER);
		calendar6.set(Calendar.DAY_OF_MONTH, 05);
		Date dateNico = calendar6.getTime();

		Calendar calendar7 = Calendar.getInstance();
		calendar7.set(Calendar.YEAR, 1990);
		calendar7.set(Calendar.MONTH, Calendar.JANUARY);
		calendar7.set(Calendar.DAY_OF_MONTH, 15);
		Date date1 = calendar7.getTime();

		Calendar calendar8 = Calendar.getInstance();
		calendar8.set(Calendar.YEAR, 1987);
		calendar8.set(Calendar.MONTH, Calendar.MARCH);
		calendar8.set(Calendar.DAY_OF_MONTH, 23);
		Date date2 = calendar8.getTime();

		Calendar calendar9 = Calendar.getInstance();
		calendar9.set(Calendar.YEAR, 1995);
		calendar9.set(Calendar.MONTH, Calendar.JULY);
		calendar9.set(Calendar.DAY_OF_MONTH, 8);
		Date date3 = calendar9.getTime();

		Calendar calendar10 = Calendar.getInstance();
		calendar10.set(Calendar.YEAR, 1993);
		calendar10.set(Calendar.MONTH, Calendar.APRIL);
		calendar10.set(Calendar.DAY_OF_MONTH, 30);
		Date date4 = calendar10.getTime();

		Calendar calendar11 = Calendar.getInstance();
		calendar11.set(Calendar.YEAR, 1991);
		calendar11.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar11.set(Calendar.DAY_OF_MONTH, 12);
		Date date5 = calendar11.getTime();

		Calendar calendar12 = Calendar.getInstance();
		calendar12.set(Calendar.YEAR, 1999);
		calendar12.set(Calendar.MONTH, Calendar.NOVEMBER);
		calendar12.set(Calendar.DAY_OF_MONTH, 5);
		Date date6 = calendar12.getTime();

		Calendar calendar13 = Calendar.getInstance();
		calendar13.set(Calendar.YEAR, 1988);
		calendar13.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar13.set(Calendar.DAY_OF_MONTH, 18);
		Date date7 = calendar7.getTime();

		BigDecimal cedula = new BigDecimal(50527424);
		BigDecimal cedula2 = new BigDecimal(53828615);
		BigDecimal cedula3 = new BigDecimal(54717322);
		BigDecimal cedula4 = new BigDecimal(54524880);
		BigDecimal cedula5 = new BigDecimal(55642853);
		BigDecimal cedula6 = new BigDecimal(29197002);

		BigDecimal cedula7 = new BigDecimal(49283746);
		BigDecimal cedula8 = new BigDecimal(23749523);
		BigDecimal cedula9 = new BigDecimal(34823453);
		BigDecimal cedula10 = new BigDecimal(22445556);
		BigDecimal cedula11 = new BigDecimal(33445566);
		BigDecimal cedula12 = new BigDecimal(26334455);
		BigDecimal cedula13 = new BigDecimal(11225566);

//		Usuario oUsuarioNuevo = new Usuario("12345678a", cedula, dateNico, "nicolas.melendez@utec.edu.uy",
//				"nico@hotmail.com", "nicolas.melendez", "Meléndez", "Nicolás", "Gutierrez", "Nahuel", "099616534", "S",
//				"S", departamentoCreado8, generoCreado, itrCreada, localidadCreada, rolCreado);

		Usuario oUsuarioNuevo2 = new Usuario("12345678a", cedula2, dateRony, "rony.palacios@utec.edu.uy",
				"rony@adinet.com", "rony.palacios", "Palacios", "Rony", "Guerra", "", "099542342", "S", "S",
				departamentoCreado8, generoCreado, itrCreada, localidadCreada, rolCreado3);

		Usuario oUsuarioNuevo3 = new Usuario("12345678a", cedula3, dateMartina, "matias.luzardo@utec.edu.uy",
				"matias@gmail.com", "matias.luzardo", "Luzardo", "Matias", "Frade", "Gabriel", "099444223", "S", "N",
				departamentoCreado7, generoCreado, itrCreada, localidadCreada22, rolCreado4);

		Usuario oUsuarioNuevo4 = new Usuario("12345678a", cedula4, dateValentina, "valentina.hernandez@utec.edu.uy",
				"vale@gmail.com", "valentina.hernandez", "Hernandez", "Valentina", "Modino", "", "094423443", "S", "S",
				departamentoCreado8, generoCreado2, itrCreada, localidadCreada, rolCreado);

		Usuario oUsuarioNuevo5 = new Usuario("12345678a", cedula5, dateMartina,
				"martina.fernandez@estudiantes.utec.edu.uy", "marti@gmail.com", "martina.fernandez", "Fernandez",
				"Martina", "Baladón", "", "092342344", "S", "S", departamentoCreado2, generoCreado2, itrCreada,
				localidadCreada8, rolCreado2);

		Usuario oUsuarioNuevo6 = new Usuario("12345678a", cedula6, datePepe, "pepe.lopez@estudiantes.utec.edu.uy",
				"pepe@gmail.com", "pepe.lopez", "Lopez", "Pepe", "Roman", "", "091342344", "S", "S",
				departamentoCreado3, generoCreado, itrCreada, localidadCreada12, rolCreado2);

		Usuario oUsuarioNuevo7 = new Usuario("23456789b", cedula7, date1, "juan.gonzalez@estudiantes.utec.edu.uy",
				"juan@gmail.com", "juan.gonzalez", "Gonzalez", "Juan", "Carlos", "Herrera", "098765432", "S", "S",
				departamentoCreado2, generoCreado2, itrCreada2, localidadCreada8, rolCreado2);

		Usuario oUsuarioNuevo8 = new Usuario("34567890c", cedula8, date2, "maria.rodriguez@estudiantes.utec.edu.uy",
				"maria@gmail.com", "maria.rodriguez", "Rodriguez", "Maria", "Fernanda", "Furtado", "095623421", "S",
				"N", departamentoCreado3, generoCreado, itrCreada, localidadCreada10, rolCreado2);

		Usuario oUsuarioNuevo9 = new Usuario("45678901d", cedula9, date3, "ana.martinez@estudiantes.utec.edu.uy",
				"ana@gmail.com", "ana.martinez", "Martinez", "Ana", "Laura", "Silva", "092345678", "S", "S",
				departamentoCreado4, generoCreado2, itrCreada2, localidadCreada14, rolCreado2);

		Usuario oUsuarioNuevo10 = new Usuario("56789012e", cedula10, date4, "luis.perez@estudiantes.utec.edu.uy",
				"luis@gmail.com", "luis.perez", "Perez", "Luis", "Alberto", "Urterio", "096734521", "S", "S",
				departamentoCreado5, generoCreado, itrCreada, localidadCreada18, rolCreado2);

		Usuario oUsuarioNuevo11 = new Usuario("67890123f", cedula11, date5, "laura.garcia@estudiantes.utec.edu.uy",
				"laura@gmail.com", "laura.garcia", "Garcia", "Laura", "Isabel", "", "091234567", "S", "N",
				departamentoCreado6, generoCreado2, itrCreada2, localidadCreada21, rolCreado2);

		Usuario oUsuarioNuevo12 = new Usuario("78901234g", cedula12, date6, "pedro.fernandez@utec.edu.uy",
				"pedro@gmail.com", "pedro.fernandez", "Fernandez", "Pedro", "Jose", "Gonzalez", "098765432", "S", "N",
				departamentoCreado7, generoCreado, itrCreada, localidadCreada23, rolCreado3);

		Usuario oUsuarioNuevo13 = new Usuario("89012345h", cedula13, date7, "carla.sanchez@utec.edu.uy",
				"carla@gmail.com", "carla.sanchez", "Sanchez", "Carla", "Lucia", "", "092345678", "N", "S",
				departamentoCreado8, generoCreado2, itrCreada2, localidadCreada2, rolCreado4);

//		var oUsuarioCreado = Crear.usuario(oUsuarioNuevo, new Analista());
		var oUsuarioCreado4 = Crear.usuario(oUsuarioNuevo4, new Analista());

		var oUsuarioCreado5 = Crear.usuario(oUsuarioNuevo5, new Estudiante("2019", new BigDecimal(4)));
		var oUsuarioCreado6 = Crear.usuario(oUsuarioNuevo6, new Estudiante("2022", new BigDecimal(1)));
		var oUsuarioCreado7 = Crear.usuario(oUsuarioNuevo7, new Estudiante("2018", new BigDecimal(4)));
		var oUsuarioCreado8 = Crear.usuario(oUsuarioNuevo8, new Estudiante("2020", new BigDecimal(3)));
		var oUsuarioCreado9 = Crear.usuario(oUsuarioNuevo9, new Estudiante("2019", new BigDecimal(2)));
		var oUsuarioCreado10 = Crear.usuario(oUsuarioNuevo10, new Estudiante("2023", new BigDecimal(1)));
		var oUsuarioCreado11 = Crear.usuario(oUsuarioNuevo11, new Estudiante("2019", new BigDecimal(3)));

		Area oArea = new Area("Programación");
		Area oArea2 = new Area("Testing");
		Area oArea3 = new Area("Base de Datos");
		Area oArea4 = new Area("Infraestructura");

		var oAreaCreada = ServiceArea.crearArea(oArea);
		var oAreaCreada2 = ServiceArea.crearArea(oArea2);
		var oAreaCreada3 = ServiceArea.crearArea(oArea3);
		var oAreaCreada4 = ServiceArea.crearArea(oArea4);

		var oUsuarioCreado3 = Crear.usuario(oUsuarioNuevo2, new Tutor(oAreaCreada));
		var oUsuarioCreado2 = Crear.usuario(oUsuarioNuevo3, new Tutor(oAreaCreada2));
		var oUsuarioCreado12 = Crear.usuario(oUsuarioNuevo12, new Tutor(oAreaCreada));
		var oUsuarioCreado13 = Crear.usuario(oUsuarioNuevo13, new Tutor(oAreaCreada2));

		Estado oEstado1 = new Estado("Ingresado");
		Estado oEstado2 = new Estado("En proceso");
		Estado oEstado3 = new Estado("Finalizado");

		var estadoGuardado = ServiceEstado.crearEstado(oEstado1);
		var estadoGuardado2 = ServiceEstado.crearEstado(oEstado2);
		var estadoGuardado3 = ServiceEstado.crearEstado(oEstado3);

		// Crear los objetos Evento con los rangos de fechas correspondientes
		Evento oEvento1 = new Evento(new Date(123, 3, 30), new Date(123, 2, 1), "Ingles");
		Evento oEvento2 = new Evento(new Date(123, 5, 24), new Date(123, 3, 1), "Curso Impresion 3D");
		Evento oEvento3 = new Evento(new Date(123, 5, 6), new Date(123, 2, 1), "Vinculación con el medio 2023");
		Evento oEvento4 = new Evento(new Date(123, 7, 5), new Date(123, 4, 1),
				"Conferencia de Inteligencia Artificial");
		Evento oEvento5 = new Evento(new Date(123, 5, 14), new Date(123, 3, 14), "Hackathon de Desarrollo Web");
		Evento oEvento6 = new Evento(new Date(123, 6, 1), new Date(123, 3, 12), "Taller de Ciberseguridad");
		Evento oEvento7 = new Evento(new Date(123, 7, 16), new Date(123, 4, 8), "Congreso de Big Data");
		Evento oEvento8 = new Evento(new Date(123, 7, 12), new Date(123, 3, 4), "Charla sobre Blockchain");
		Evento oEvento9 = new Evento(new Date(123, 5, 1), new Date(123, 3, 11), "Feria de Startups");
		Evento oEvento10 = new Evento(new Date(123, 6, 1), new Date(123, 2, 7), "Encuentro de Innovación Tecnológica");
		Evento oEvento11 = new Evento(new Date(123, 7, 1), new Date(123, 3, 12), "Workshop de Programación en Python");
		Evento oEvento12 = new Evento(new Date(123, 6, 1), new Date(123, 2, 20),
				"Conferencia de Experiencia de Usuario");
		Evento oEvento13 = new Evento(new Date(123, 4, 1), new Date(123, 4, 6), "Hackathon de Internet de las Cosas");
		Evento oEvento14 = new Evento(new Date(123, 6, 1), new Date(123, 3, 1),
				"Taller de Desarrollo de Aplicaciones Móviles");

		var eventoGuardado = ServiceEvento.crearEvento(oEvento1);
		var eventoGuardado2 = ServiceEvento.crearEvento(oEvento2);
		var eventoGuardado3 = ServiceEvento.crearEvento(oEvento3);
		var eventoGuardado4 = ServiceEvento.crearEvento(oEvento4);
		var eventoGuardado5 = ServiceEvento.crearEvento(oEvento5);
		var eventoGuardado6 = ServiceEvento.crearEvento(oEvento6);
		var eventoGuardado7 = ServiceEvento.crearEvento(oEvento7);
		var eventoGuardado8 = ServiceEvento.crearEvento(oEvento8);
		var eventoGuardado9 = ServiceEvento.crearEvento(oEvento9);
		var eventoGuardado10 = ServiceEvento.crearEvento(oEvento10);
		var eventoGuardado11 = ServiceEvento.crearEvento(oEvento11);
		var eventoGuardado12 = ServiceEvento.crearEvento(oEvento12);
		var eventoGuardado13 = ServiceEvento.crearEvento(oEvento13);
		var eventoGuardado14 = ServiceEvento.crearEvento(oEvento14);

		Estado oEstadoCreado = ServiceEstado.listarEstados().get(0);

		Estudiante oEstudiante = ServiceEstudiante.listarEstudiantes().get(1);
		Estudiante oEstudiante2 = ServiceEstudiante.listarEstudiantes().get(0);

		var oEventoCreado = ServiceEvento.listarEventos().get(0);
		var oEventoCreado2 = ServiceEvento.listarEventos().get(1);
		var oEventoCreado3 = ServiceEvento.listarEventos().get(2);
		var oEventoCreado4 = ServiceEvento.listarEventos().get(3);
		var oEventoCreado5 = ServiceEvento.listarEventos().get(4);
		var oEventoCreado6 = ServiceEvento.listarEventos().get(5);
		var oEventoCreado7 = ServiceEvento.listarEventos().get(6);
		var oEventoCreado8 = ServiceEvento.listarEventos().get(7);
		var oEventoCreado13 = ServiceEvento.listarEventos().get(13);

		Tipo oTipo = new Tipo("Transporte", "S",
				"Se hace constar que &nombreCompleto& con Cédula de Identidad Nº &cedula& nacido en la fecha &fechaNacimiento& es estudiante de la Universidad Tecnológica (UTEC) y se encuentra cursando la carrera Licenciatura en Tecnologías de la Información dictada en el departamento de &localidadItr&.\n\nSe expide la presente constancia el día &fechaExpedido& para su presentación ante la empresa de transporte en el marco de la Resolución de la Dirección Nacional de Transporte, EXP 14/7/1012, que habilita a los estudiantes que cursan carreras en la UTEC, que al 1° de enero de cada año sean menores de 30 años, a acceder a la bonificación dispuesta por el Artículo 2.12 del Decreto 218/09 de fecha 11 de mayo de 2009, a adquirir boletos abono de valor igual al 50% (cincuenta por ciento).");

		Tipo oTipo2 = new Tipo("Presencial Común", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha asistido a la jornada presencial el día &fechaInicioEvento& en el &nombreItr&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo3 = new Tipo("Presencial Prueba", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha asistido a la prueba presencial del evento &nombreEvento& el día &fechaInicioEvento& en el &nombreItr&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo4 = new Tipo("Estudiante Activo", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& es estudiante de esta institución y se encuentra cursando la carrera de Licenciatura en Tecnologías de la Información que se desarrolla en el departamento de &localidadItr&.\n\nAsiste a clase cada 15 días entre 9:00 AM y las 5:00 PM.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo5 = new Tipo("Exámen", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& es estudiante de esta institución y se encuentra cursando la carrera de Licenciatura en Tecnologías de la Información.\n\nEl día &fechaInicioEvento& ha rendido el examen de &nombreEvento&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo6 = new Tipo("Vinculación con el Medio", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha obtenido los créditos por cursar &nombreEvento&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo7 = new Tipo("UTEC Innova", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha obtenido los créditos por cursar &nombreEvento&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo8 = new Tipo("Optativa", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha obtenido los créditos por cursar &nombreEvento&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		boolean tipoCreado = ServiceTipo.crearTipoConstancia(oTipo);
		boolean tipoCreado2 = ServiceTipo.crearTipoConstancia(oTipo2);
		boolean tipoCreado3 = ServiceTipo.crearTipoConstancia(oTipo3);
		boolean tipoCreado4 = ServiceTipo.crearTipoConstancia(oTipo4);
		boolean tipoCreado5 = ServiceTipo.crearTipoConstancia(oTipo5);
		boolean tipoCreado6 = ServiceTipo.crearTipoConstancia(oTipo6);
		boolean tipoCreado7 = ServiceTipo.crearTipoConstancia(oTipo7);
		boolean tipoCreado8 = ServiceTipo.crearTipoConstancia(oTipo8);

		var tipo1 = ServiceTipo.listarTiposConstancias().get(0);
		var tipo2 = ServiceTipo.listarTiposConstancias().get(1);
		var tipo3 = ServiceTipo.listarTiposConstancias().get(2);
		var tipo4 = ServiceTipo.listarTiposConstancias().get(3);
		var tipo5 = ServiceTipo.listarTiposConstancias().get(4);
		var tipo6 = ServiceTipo.listarTiposConstancias().get(5);
		var tipo7 = ServiceTipo.listarTiposConstancias().get(6);
		var tipo8 = ServiceTipo.listarTiposConstancias().get(7);

		// Constancia(String detalle, Date fechaHora, Estado estado, Estudiante
		// estudiante, Evento evento)
		Constancia oConstancia1 = new Constancia("Necesito renovar mi abono.", new Date(), oEstadoCreado, oEstudiante,
				oEventoCreado2, tipo1);
		Constancia oConstancia2 = new Constancia("Di la prueba de ingles pero no me dieron los créditos.", new Date(),
				oEstadoCreado, oEstudiante2, oEventoCreado, tipo2);
		Constancia oConstancia3 = new Constancia(
				"Necesito una constancia porque terminé el Hackatón de Desarrollo Web.", new Date(), oEstadoCreado,
				oEstudiante2, oEventoCreado4, tipo3);
		Constancia oConstancia4 = new Constancia(
				"Hola! Necesito que se me valide que soy estudiante activo de esta carrera.", new Date(), oEstadoCreado,
				oEstudiante, oEventoCreado13, tipo4);
		Constancia oConstancia5 = new Constancia(
				"Necesito una constancia para el trabajo de que realicé el exámen de este evento.", new Date(),
				oEstadoCreado, oEstudiante2, oEventoCreado5, tipo5);
		Constancia oConstancia6 = new Constancia(
				"Necesito una constancia de que termine vinculación con el medio 2023.", new Date(), oEstadoCreado,
				oEstudiante2, oEventoCreado3, tipo6);
		Constancia oConstancia7 = new Constancia(
				"Hice un curso de UTEC Innova y no tengo una constancia que necesito para mi trabajo.", new Date(),
				oEstadoCreado, oEstudiante2, oEventoCreado7, tipo7);
		Constancia oConstancia8 = new Constancia("Realicé esta optativa y necesito una constancia de esta.", new Date(),
				oEstadoCreado, oEstudiante2, oEventoCreado8, tipo8);

		var constanciaGuardada = ServiceConstancia.crearConstancia(oConstancia1);
		var constanciaGuardada2 = ServiceConstancia.crearConstancia(oConstancia2);
		var constanciaGuardada3 = ServiceConstancia.crearConstancia(oConstancia3);
		var constanciaGuardada4 = ServiceConstancia.crearConstancia(oConstancia4);
		var constanciaGuardada5 = ServiceConstancia.crearConstancia(oConstancia5);
		var constanciaGuardada6 = ServiceConstancia.crearConstancia(oConstancia6);
		var constanciaGuardada7 = ServiceConstancia.crearConstancia(oConstancia7);
		var constanciaGuardada8 = ServiceConstancia.crearConstancia(oConstancia8);

		var evento = ServiceEvento.listarEventos().get(0);
		var evento2 = ServiceEvento.listarEventos().get(1);
		var evento3 = ServiceEvento.listarEventos().get(2);
		var evento4 = ServiceEvento.listarEventos().get(3);
		var evento5 = ServiceEvento.listarEventos().get(4);
		var evento6 = ServiceEvento.listarEventos().get(5);
		var evento7 = ServiceEvento.listarEventos().get(6);

		var estudiante = ServiceEstudiante.listarEstudiantes().get(0);
		var estudiante2 = ServiceEstudiante.listarEstudiantes().get(1);

		// EventoEstudiantePK(long idEvento, long idEstudiante)
		EventoEstudiantePK id2 = new EventoEstudiantePK(evento.getIdEvento(), estudiante2.getIdEstudiante());

		EventoEstudiantePK id = new EventoEstudiantePK(evento.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id3 = new EventoEstudiantePK(evento2.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id4 = new EventoEstudiantePK(evento3.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id5 = new EventoEstudiantePK(evento4.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id6 = new EventoEstudiantePK(evento5.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id7 = new EventoEstudiantePK(evento6.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id8 = new EventoEstudiantePK(evento7.getIdEvento(), estudiante.getIdEstudiante());

		// EventoEstudiante(EventoEstudiantePK id, String asistencia, BigDecimal
		// calificacion)
		BigDecimal nota0 = new BigDecimal(0);
		BigDecimal nota = new BigDecimal(1);
		BigDecimal nota2 = new BigDecimal(2);
		BigDecimal nota3 = new BigDecimal(3);
		BigDecimal nota4 = new BigDecimal(4);
		BigDecimal nota5 = new BigDecimal(5);

		EventoEstudiante oEventoEstudiante = new EventoEstudiante(id, "S", nota);
		EventoEstudiante oEventoEstudiante2 = new EventoEstudiante(id2, "N", nota0);
		EventoEstudiante oEventoEstudiante3 = new EventoEstudiante(id3, "S", nota2);
		EventoEstudiante oEventoEstudiante4 = new EventoEstudiante(id4, "S", nota2);
		EventoEstudiante oEventoEstudiante5 = new EventoEstudiante(id5, "S", nota);
		EventoEstudiante oEventoEstudiante6 = new EventoEstudiante(id6, "S", nota5);
		EventoEstudiante oEventoEstudiante7 = new EventoEstudiante(id7, "N", nota0);
		EventoEstudiante oEventoEstudiante8 = new EventoEstudiante(id8, "S", nota2);

		boolean creado = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante);
		boolean creado2 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante2);
		boolean creado3 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante3);
		boolean creado4 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante4);
		boolean creado5 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante5);
		boolean creado6 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante6);
		boolean creado7 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante7);
		boolean creado8 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante8);

	}

}
