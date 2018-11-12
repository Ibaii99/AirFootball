package main;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import objetos.Poste;
import ventanas.ventanaPartido;
import javax.swing.ImageIcon;

import entidades.BaseDeDatos;
import entidades.Equipo;
import ventanas.Inicio;
public class Juego {
	
	public ventanaPartido v = new ventanaPartido();
	public Poste posteArribaIzquierda = new Poste(v, true, true);
	public Poste posteAbajoIzquierda = new Poste(v, true, false);
	
	public Poste posteArribaDerecha = new Poste(v, false, true);
	public Poste posteAbajoDerecha = new Poste(v, false, false);
	
	
	public static void main(String[] args) {
		BaseDeDatos bd = new BaseDeDatos();
		bd.anyadirEquipo(new Equipo("ALA", "Deportivo Alavés", 0, Color.BLUE, new ImageIcon("iconos/equipos/ala.png"),
				"iconos/equipos/ala.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("ATH", "Athletic Club", 0, Color.RED, new ImageIcon("iconos/equipos/ath.png"),
				"iconos/equipos/ath.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("ATM", "Atlético de Madrid", 0, Color.RED, new ImageIcon("iconos/equipos/atl.png"),
				"iconos/equipos/atl.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("BAR", "FC Barcelona", 0, Color.BLUE, new ImageIcon("iconos/equipos/bar.png"),
				"iconos/equipos/bar.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("BET", "Real Betis", 0, Color.BLUE, new ImageIcon("iconos/equipos/bet.png"),
				"iconos/equipos/bet.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("CEL", "Celta de Vigo", 0, Color.BLUE, new ImageIcon("iconos/equipos/cel.png"),
				"iconos/equipos/cel.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(
				new Equipo("DEP", "Deportivo de La Coruña", 0, Color.BLUE, new ImageIcon("iconos/equipos/dep.png"),
						"iconos/equipos/dep.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("EIB", "SD Eibar", 0, Color.BLUE, new ImageIcon("iconos/equipos/eib.png"),
				"iconos/equipos/eib.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("ESP", "RCD Espanyol", 0, Color.BLUE, new ImageIcon("iconos/equipos/esp.png"),
				"iconos/equipos/esp.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("GET", "Getafe CF", 0, Color.BLUE, new ImageIcon("iconos/equipos/get.png"),
				"iconos/equipos/get.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("GIR", "Girona FC", 0, Color.BLUE, new ImageIcon("iconos/equipos/gir.png"),
				"iconos/equipos/gir.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("HUE", "SD Huesca", 0, Color.BLUE, new ImageIcon("iconos/equipos/hue.png"),
				"iconos/equipos/hue.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("LEG", "Leganés", 0, Color.BLUE, new ImageIcon("iconos/equipos/leg.png"),
				"iconos/equipos/leg.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("RAY", "Rayo Vallecano", 0, Color.BLUE, new ImageIcon("iconos/equipos/ray.png"),
				"iconos/equipos/ray.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("RMA", "Real Madrid CF", 0, Color.BLUE, new ImageIcon("iconos/equipos/rma.png"),
				"iconos/equipos/rma.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("RSO", "Real Sociedad", 0, Color.BLUE, new ImageIcon("iconos/equipos/rso.png"),
				"iconos/equipos/rso.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("SEV", "Sevilla FC", 0, Color.BLUE, new ImageIcon("iconos/equipos/sev.png"),
				"iconos/equipos/sev.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("VAL", "Valencia CF", 0, Color.BLUE, new ImageIcon("iconos/equipos/val.png"),
				"iconos/equipos/val.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("VLL", "Real Valladolid", 0, Color.BLUE, new ImageIcon("iconos/equipos/vll.png"),
				"iconos/equipos/vll.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		bd.anyadirEquipo(new Equipo("VLR", "Villarreal CF", 0, Color.BLUE, new ImageIcon("iconos/equipos/vlr.png"),
				"iconos/equipos/vlr.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
	}
}
	
	



/**
 * (char siglas, String nombre, int puntos, Color color, ImageIcon imagen,
 * String rutaImagen, int golesEnContraTotales, int golesEnContraLocal, int
 * golesEnContraVisitante, int golesAFavorTotales, int golesAFavorVisitante, int
 * golesAFavorLocal, int victoriasTotales, int victoriasLocal, int
 * victoriasVisitante, int derrotasTotales, int derrotasLocal, int
 * derrotasVisitante, int empatesTotales, int empatesLocal, int
 * empatesVisitante) {
 **/