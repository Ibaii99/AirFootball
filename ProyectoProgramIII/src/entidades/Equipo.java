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
	
	public void anyadirEstadisticasVisitante(boolean haGanado) {
		if(!haGanado) {
		derrotasVisitante += 1;
		}
		if(haGanado) {
		victoriasVisitante +=1;
		}
		
		golesAFavorVisitante += ;
		golesEnContraVisitante += ;
	}
	
	public void anyadirEstadisticasLocal() {
		
	}
	
	public void calcularEstadisticasTotales() {
		derrotasTotales = derrotasVisitante + derrotasLocal;
		victoriasTotales = victoriasVisitante + victoriasLocal;
		golesAFavorTotales = golesAFavorVisitante + golesAFavorLocal;
		golesEnContraTotales = golesEnContraVisitante + golesEnContraLocal;
	}
	

}
