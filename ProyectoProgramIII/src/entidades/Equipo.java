package entidades;

import java.awt.Image;
import java.util.ArrayList;

public class Equipo {
	
	private Image escudo;				// Una imagen directamente asignada, mejor que una ruta
	private String nombre;			
	private char siglas;				// Char para crear un formato de reconocimiento de siglas con lo dado en clase
	private int puntos;
	
	private int golesEnContra;
	private int golesAFavor;
	
	private int victoriasTotales;
	private int victoriasLocal;
	private int victoriasVisitante;
	
	private int derrotasTotales;
	private int derrotasLocal;
	private int derrotasVisitante;
	
	private ArrayList<Partidos> arrayPartidosJugados;


}
