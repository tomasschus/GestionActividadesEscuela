package Controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import Modelo.ConexionMySQL;

public class controlador {

	@SuppressWarnings("unused")
	private ConexionMySQL sql ;
	private Date fecha;
	@SuppressWarnings("unused")
	private SimpleDateFormat date;
	public String[][] tabla;
	
	public controlador() throws Exception {
		this.sql = new ConexionMySQL();
		sql.MySQLConnection();
		tabla = sql.getDatosAlumnos();
	}
	
	
	public String fechaactual() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		return date;
	}


	public String[][] getTabla() {
		return tabla;
	}


	public void setTabla(String[][] tabla) {
		this.tabla = tabla;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getCantidadAlumnos() {
		int total=0;
		while(tabla[total][0] != null) {
			total++;
		}
		return total;
	}
	
	public int getCorrectos(int act) {
		int total=0,cant=0;
		act=act+1;
		while(tabla[cant][0] != null) {
			if((tabla[cant][act])!="" || (tabla[cant][act])!="") {
				if(Integer.parseInt(tabla[cant][act])==1 || Integer.parseInt(tabla[cant][act])==2) {
					total++;
				}
			}
			cant++;
		}
		return total;
	}
	
	public int getIncorrectos(int act) {
		int total=0,cant=0;
		act=act+1;
		while(tabla[cant][0] != null) {
			if((tabla[cant][act])!="" || (tabla[cant][act])!="") {
				if(Integer.parseInt(tabla[cant][act])==3 || Integer.parseInt(tabla[cant][act])==9) {
					total++;
				}
			}
			cant++;
		}
		return total;
	}


	public String getCorrectosAlumno(int alumno) {
		alumno--;
		float porc = 0;
		int actividad=2, activas = 0, correctas=0;
		while(actividad<14) {
			if((tabla[alumno][actividad])!="" || (tabla[alumno][actividad])!="") {
				if(Integer.parseInt(tabla[alumno][actividad])==1 || Integer.parseInt(tabla[alumno][actividad])==2) {
					correctas++;
				}
				activas++;
			}
			actividad++;
		
		}
		porc = ( correctas *100 )/activas;
		return "Cantidad de actividades: " + activas + '\n' + "Cantidad correctas: " + correctas + "( " + porc + "% )";
	}


	public String getIncorrectosAlumno(int alumno) {
		alumno--;
		float porc = 0;
		int actividad=2, activas = 0, incorrectas=0;
		while(actividad<14) {
			if((tabla[alumno][actividad])!="" || (tabla[alumno][actividad])!="") {
				if(Integer.parseInt(tabla[alumno][actividad])==3 || Integer.parseInt(tabla[alumno][actividad])==9) {
					incorrectas++;
				}
				activas++;
			}
			actividad++;
		
		}
		porc = ( incorrectas *100 )/activas;
		return "Cantidad de actividades: " + activas + '\n' + "Cantidad Incorrectas: " + incorrectas + "( " + porc + "% )";		
	}
}
