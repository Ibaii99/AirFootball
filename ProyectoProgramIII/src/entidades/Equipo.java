package entidades;

import java.awt.Image;
import java.util.ArrayList;

import objetos.Objetos;

/**
 * @author ibai
 *
 */
public class Equipo extends Objetos{
	
	private String nombre;			
	private char siglas;				// Char para crear un formato de reconocimiento de siglas con lo dado en clase
	
	private int puntos;
	
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
	
	
	
	private ArrayList<Partidos> arrayPartidosJugados;
	
	/** Constructor de la clase equipo
	 * @param nombre	Nombre del equipo
	 * @param siglas	Siglas del equipo
	 */
	public Equipo(String nombre, char siglas) {
		this.nombre = nombre;
		this.siglas = siglas;
	}

	/** Metodo para añadir puntos al equipo segun el partido
	 * @param puntos Puntos a sumar, seran 0, 1 o 3
	 */
	public void anyadirPuntos(int puntos) {
		this.puntos += puntos;
	}

	/** Metodo para guardar el partido jugado por el Equipo
	 * @param partido	Partido jugado
	 */
	public void anyadirPartidoJugado(Partidos partido) {
		arrayPartidosJugados.add(partido);
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
	}
	
	/** Metodo que calcula las estadisticas totales partiendo de las estadisticas locales y visitantes
	 */
	public void calcularEstadisticasTotales() {
		derrotasTotales = derrotasVisitante + derrotasLocal;
		victoriasTotales = victoriasVisitante + victoriasLocal;
		empatesTotales = empatesVisitante + empatesLocal;
		
		golesAFavorTotales = golesAFavorVisitante + golesAFavorLocal;
		golesEnContraTotales = golesEnContraVisitante + golesEnContraLocal;
	}

	public String getNombre() {
		return nombre;
	}

	public char getSiglas() {
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

	public ArrayList<Partidos> getArrayPartidosJugados() {
		return arrayPartidosJugados;
	}
	
	

}
