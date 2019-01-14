package entidades;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import objetos.Objetos;
import objetos.Pelota;

/**
 * @author ibai
 *
 */
public class Equipo{
	private Pelota bolaEquipo;
	private String nombre;		
	
	private String siglas; // Char para crear un formato de reconocimiento de siglas con lo dado en clase
	private String imagen;
	private int puntos;

	
////////////////////////////Atributos para estadísticas///////////////////////////////////////////////////
	private int golesEnContraTotales;
	private int golesEnContraVisitante;
	private int golesEnContraLocal;
	
	private int golesAFavorTotales;
	private int golesAFavorVisitante;
	private int golesAFavorLocal;
	
	private int victoriasTotales;
	private int victoriasLocal;
	private int victoriasVisitante;
	
	private int derrotasTotales;
	private int derrotasLocal;
	private int derrotasVisitante;
	
	private int empatesTotales;
	private int empatesLocal;
	private int empatesVisitante;
	

	
	

	/** Constructor de Equipo completo para datos leidos desde BD
	 * @param siglas
	 * @param nombre
	 * @param puntos
	 * @param color
	 * @param imagen
	 * @param rutaImagen
	 * @param golesEnContraTotales
	 * @param golesEnContraLocal
	 * @param golesEnContraVisitante
	 * @param golesAFavorTotales
	 * @param golesAFavorVisitante
	 * @param golesAFavorLocal
	 * @param victoriasTotales
	 * @param victoriasLocal
	 * @param victoriasVisitante
	 * @param derrotasTotales
	 * @param derrotasLocal
	 * @param derrotasVisitante
	 * @param empatesTotales
	 * @param empatesLocal
	 * @param empatesVisitante
	 */
	public Equipo(String siglas, String nombre, int puntos, Color color, String imagen, String rutaImagen,
		int golesEnContraTotales, int golesEnContraLocal, int golesEnContraVisitante, int golesAFavorTotales,
		int golesAFavorVisitante, int golesAFavorLocal, int victoriasTotales, int victoriasLocal,
		int victoriasVisitante, int derrotasTotales, int derrotasLocal, int derrotasVisitante, int empatesTotales,
		int empatesLocal, int empatesVisitante) {
			this.siglas = siglas;
			this.nombre = nombre;
			this.puntos = puntos;
			this.bolaEquipo = new Pelota(color, nombre, Pelota.POSICION_X_POR_DEFECTO, Pelota.POSICION_Y_POR_DEFECTO, Pelota.RADIO_POR_DEFECTO_EQUIPO, Pelota.MASA_POR_DEFECTO, imagen, true);
			this.golesEnContraTotales = golesEnContraTotales;
			this.golesEnContraLocal = golesEnContraLocal;
			this.golesEnContraVisitante = golesEnContraVisitante;
			this.golesAFavorTotales = golesAFavorTotales;
			this.golesAFavorVisitante = golesAFavorVisitante;
			this.golesAFavorLocal = golesAFavorLocal;
			this.victoriasTotales = victoriasTotales;
			this.victoriasLocal = victoriasLocal;
			this.victoriasVisitante = victoriasVisitante;
			this.derrotasTotales = derrotasTotales;
			this.derrotasLocal = derrotasLocal;
			this.derrotasVisitante = derrotasVisitante;
			this.empatesTotales = empatesTotales;
			this.empatesLocal = empatesLocal;
			this.empatesVisitante = empatesVisitante;
	}
	
	@Override
	public String toString() {
		String a = 	this.siglas+"  "+this.nombre+"  "+this.puntos+"  "+this.bolaEquipo.toString()+"  "
					+this.golesEnContraTotales+"  "+this.golesEnContraLocal+"  "+this.golesEnContraVisitante+"  "
					+this.golesAFavorTotales+"  "+this.golesAFavorLocal+"  " +this.golesAFavorVisitante+"  "
					+this.victoriasTotales+"  "+this.victoriasLocal+"  "+this.victoriasVisitante+"  "
					+this.derrotasTotales+"  "+this.derrotasLocal+"  "+this.derrotasVisitante+"  "
					+this.empatesTotales+"  "+this.empatesLocal+"  "+this.empatesVisitante;
		return a;
	}

	
	/** Metodo constructor de Equipo con imagen 
	 * @param nombre	Nombre del equipo
	 * @param siglas	Siglas del Equipo
	 * @param imagen	Imagen de la bola
	 * @param radio		Radio de la bola
	 * @param masa		Masa de la bola
	 */
	public Equipo(String nombre, String siglas, String imagen, double radio, double masa) {
		this.siglas = siglas;
		this.nombre = nombre;
		bolaEquipo = new Pelota(imagen, nombre, 0, 0, radio, masa);
	}
	
	/** Metodo constructor de Equipo con color 
	 * @param nombre	Nombre del equipo
	 * @param siglas	Siglas del Equipo
	 * @param imagen	Imagen de la bola
	 * @param radio		Radio de la bola
	 * @param masa		Masa de la bola
	 */
	public Equipo(String nombre, String siglas, Color color, double radio, double masa) {
		this.siglas = siglas;
		this.nombre = nombre;
		bolaEquipo = new Pelota(color, nombre, 0, 0, radio, masa);
	}



	/** Constructor de Equipo sencillo con color y masa por defeto
	 * @param nombre	Nombre del equipo
	 * @param siglas	Siglas del equipo
	 * @param radio		Radio de la bola Equipo
	 * @param color		Color de la bola de Equipo
	 */
	public Equipo(String nombre, String siglas, double radio, Color color) {
		this.siglas = siglas;
		bolaEquipo = new Pelota(color,nombre,radio);
		bolaEquipo.setBota(false);
	}

	/** Constructor de Equipo sencillo con imagen y masa por defeto
	 * @param nombre	Nombre del equipo
	 * @param siglas	Siglas del equipo
	 * @param radio		Radio de la bola Equipo
	 * @param image		Ruta de la imagen del equipo
	 */
	public Equipo(String nombre, String siglas, double radio, String image) {
		this.siglas = siglas;
		bolaEquipo = new Pelota(image,nombre,radio);
		bolaEquipo.setBota(false);
	}
	
	/** Metodo para añadir puntos al equipo segun el partido
	 * @param puntos Puntos a sumar, seran 0, 1 o 3
	 */
	public void anyadirPuntos(int puntos) {
		this.puntos += puntos;
	}


	
	/**	Metodo para añadir estadisticas cuando el equipo juega de Visitante
	 * @param haGanado			true si gana, false si empata o pierde
	 * @param haEmpatado 		true si empata, false si pierde
	 * @param golesAFavor		goles marcados por el equipo
	 * @param golesEnContra		goles encajados al equipo
	 */
	public void anyadirEstadisticasVisitante(boolean haGanado, boolean haEmpatado, int golesAFavor, int golesEnContra) {
		if(!haGanado) {
			if(haEmpatado){empatesVisitante += 1;}
			else if(!haEmpatado) {derrotasVisitante += 1;}
			}
		else if(haGanado) {victoriasVisitante += 1;}
		
		golesAFavorVisitante += golesAFavor;
		golesEnContraVisitante += golesEnContra;
		calcularEstadisticasTotales();
	}
	
	/**	Metodo para añadir estadisticas cuando el equipo juega de LOCAL
	 * @param haGanado			true si gana, false si empata o pierde
	 * @param haEmpatado 		true si empata, false si pierde
	 * @param golesAFavor		goles marcados por el equipo
	 * @param golesEnContra		goles encajados al equipo
	 */
	public void anyadirEstadisticasLocal(boolean haGanado, boolean haEmpatado, int golesAFavor, int golesEnContra) {
		if(!haGanado) {
			if(haEmpatado){empatesLocal += 1;}
			else if(!haEmpatado) {derrotasLocal += 1;}
			}
		else if(haGanado) {victoriasLocal += 1;}
		
		golesAFavorLocal += golesAFavor;
		golesEnContraLocal += golesEnContra;
		calcularEstadisticasTotales();
	}
	
	/** Metodo que calcula las estadisticas totales partiendo de las estadisticas locales y visitantes
	 */
	private void calcularEstadisticasTotales() {
		derrotasTotales = derrotasVisitante + derrotasLocal;
		victoriasTotales = victoriasVisitante + victoriasLocal;
		empatesTotales = empatesVisitante + empatesLocal;
		
		golesAFavorTotales = golesAFavorVisitante + golesAFavorLocal;
		golesEnContraTotales = golesEnContraVisitante + golesEnContraLocal;
	}
	



	public String getNombre() {
		return nombre;
	}

	public String getSiglas() {
		return siglas;
	}

	public int getPuntos() {
		return puntos;
	}




	public int getGolesEnContraTotales() {
		return golesEnContraTotales;
	}

	public int getGolesEnContraVisitante() {
		return golesEnContraVisitante;
	}

	public int getGolesEnContraLocal() {
		return golesEnContraLocal;
	}

	public int getGolesAFavorTotales() {
		return golesAFavorTotales;
	}

	public int getGolesAFavorVisitante() {
		return golesAFavorVisitante;
	}

	public int getGolesAFavorLocal() {
		return golesAFavorLocal;
	}

	public int getVictoriasTotales() {
		return victoriasTotales;
	}

	public int getVictoriasLocal() {
		return victoriasLocal;
	}

	public int getVictoriasVisitante() {
		return victoriasVisitante;
	}

	public int getDerrotasTotales() {
		return derrotasTotales;
	}

	public int getDerrotasLocal() {
		return derrotasLocal;
	}

	public int getDerrotasVisitante() {
		return derrotasVisitante;
	}

	public int getEmpatesTotales() {
		return empatesTotales;
	}

	public int getEmpatesLocal() {
		return empatesLocal;
	}

	public int getEmpatesVisitante() {
		return empatesVisitante;
	}

	public Pelota getBolaEquipo() {
		return bolaEquipo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setBolaEquipo(Pelota bolaEquipo) {
		this.bolaEquipo = bolaEquipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void setGolesEnContraTotales(int golesEnContraTotales) {
		this.golesEnContraTotales = golesEnContraTotales;
	}

	public void setGolesEnContraVisitante(int golesEnContraVisitante) {
		this.golesEnContraVisitante = golesEnContraVisitante;
	}

	public void setGolesEnContraLocal(int golesEnContraLocal) {
		this.golesEnContraLocal = golesEnContraLocal;
	}

	public void setGolesAFavorTotales(int golesAFavorTotales) {
		this.golesAFavorTotales = golesAFavorTotales;
	}

	public void setGolesAFavorVisitante(int golesAFavorVisitante) {
		this.golesAFavorVisitante = golesAFavorVisitante;
	}

	public void setGolesAFavorLocal(int golesAFavorLocal) {
		this.golesAFavorLocal = golesAFavorLocal;
	}

	public void setVictoriasTotales(int victoriasTotales) {
		this.victoriasTotales = victoriasTotales;
	}

	public void setVictoriasLocal(int victoriasLocal) {
		this.victoriasLocal = victoriasLocal;
	}

	public void setVictoriasVisitante(int victoriasVisitante) {
		this.victoriasVisitante = victoriasVisitante;
	}

	public void setDerrotasTotales(int derrotasTotales) {
		this.derrotasTotales = derrotasTotales;
	}

	public void setDerrotasLocal(int derrotasLocal) {
		this.derrotasLocal = derrotasLocal;
	}

	public void setDerrotasVisitante(int derrotasVisitante) {
		this.derrotasVisitante = derrotasVisitante;
	}

	public void setEmpatesTotales(int empatesTotales) {
		this.empatesTotales = empatesTotales;
	}

	public void setEmpatesLocal(int empatesLocal) {
		this.empatesLocal = empatesLocal;
	}

	public void setEmpatesVisitante(int empatesVisitante) {
		this.empatesVisitante = empatesVisitante;
	} 
	

	
	



	
	

}
