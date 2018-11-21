package main;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

import objetos.Pelota;
import objetos.Poste;
import ventanas.ventanaPartido;
import javax.swing.ImageIcon;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import ventanas.Inicio;
public class Juego {
	
	public static ventanaPartido v;	// la ventana se creara cuando se elijan los equipos con los que se va a jugar
	public Poste posteArribaIzquierda = new Poste(v, true, true);
	public Poste posteAbajoIzquierda = new Poste(v, true, false);
	
	public Poste posteArribaDerecha = new Poste(v, false, true);
	public Poste posteAbajoDerecha = new Poste(v, false, false);
	
	
	private static Pelota p = new Pelota(Color.black, "jabulani", 0, 0, 20, 25);
	private static Equipo e1 = new Equipo("Futbol club Barcelona", "FCB", Color.red, 50, 70);
	private static Equipo e2 = new Equipo("Real Madrid", "RM", Color.white, 50, 70);
	
	
	
	public static void main(String[] args) {
		BaseDeDatos bd = new BaseDeDatos();
		//anyadirTodosLosEquipos(bd);
		
		v = new ventanaPartido(e1, e2, p, true, true, false);
		v.setVisible(true);
		// pruebas de la ventana de juego

		
		
	}

	private static void anyadirTodosLosEquipos(BaseDeDatos bd) {
		try {
			bd.anyadirEquipo(new Equipo("ALA", "Deportivo Alav�s", 0, Color.BLUE, ("iconos/equipos/ala.png"),
					"iconos/equipos/ala.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("ATH", "Athletic Club", 0, Color.RED, ("iconos/equipos/ath.png"),
					"iconos/equipos/ath.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("ATM", "Atl�tico de Madrid", 0, Color.RED, ("iconos/equipos/atl.png"),
					"iconos/equipos/atl.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("BAR", "FC Barcelona", 0, Color.BLUE, ("iconos/equipos/bar.png"),
					"iconos/equipos/bar.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("BET", "Real Betis", 0, Color.BLUE, ("iconos/equipos/bet.png"),
					"iconos/equipos/bet.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("CEL", "Celta de Vigo", 0, Color.BLUE, ("iconos/equipos/cel.png"),
					"iconos/equipos/cel.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(
					new Equipo("DEP", "Deportivo de La Coru�a", 0, Color.BLUE, ("iconos/equipos/dep.png"),
							"iconos/equipos/dep.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("EIB", "SD Eibar", 0, Color.BLUE, ("iconos/equipos/eib.png"),
					"iconos/equipos/eib.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("ESP", "RCD Espanyol", 0, Color.BLUE, ("iconos/equipos/esp.png"),
					"iconos/equipos/esp.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("GET", "Getafe CF", 0, Color.BLUE, ("iconos/equipos/get.png"),
					"iconos/equipos/get.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("GIR", "Girona FC", 0, Color.BLUE, ("iconos/equipos/gir.png"),
					"iconos/equipos/gir.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("HUE", "SD Huesca", 0, Color.BLUE, ("iconos/equipos/hue.png"),
					"iconos/equipos/hue.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("LEG", "Legan�s", 0, Color.BLUE, ("iconos/equipos/leg.png"),
					"iconos/equipos/leg.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("RAY", "Rayo Vallecano", 0, Color.BLUE, ("iconos/equipos/ray.png"),
					"iconos/equipos/ray.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("RMA", "Real Madrid CF", 0, Color.BLUE, ("iconos/equipos/rma.png"),
					"iconos/equipos/rma.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("RSO", "Real Sociedad", 0, Color.BLUE, ("iconos/equipos/rso.png"),
					"iconos/equipos/rso.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("SEV", "Sevilla FC", 0, Color.BLUE, ("iconos/equipos/sev.png"),
					"iconos/equipos/sev.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("VAL", "Valencia CF", 0, Color.BLUE, ("iconos/equipos/val.png"),
					"iconos/equipos/val.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("VLL", "Real Valladolid", 0, Color.BLUE, ("iconos/equipos/vll.png"),
					"iconos/equipos/vll.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			bd.anyadirEquipo(new Equipo("VLR", "Villarreal CF", 0, Color.BLUE, ("iconos/equipos/vlr.png"),
					"iconos/equipos/vlr.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			
			}catch(Exception e) {
				
			
	}
		
			Inicio i = new Inicio();
			i.setVisible(true);
	}
}
	
//	(*1)diseño recursivo de funcionameinto del juego
//			guardo el tiempo de la consulta anterior
//
//			con la velocidad de la pelota se donde va a ir y con eso llegara un momento que:
//			me de que hay o ha habido un rebote:
//					
// 					divido el tiempo a la mitad y miro si en ese momento tambn seguia ocurriendo choque(*1) 
//		[así hasta ir dividiendo todo el rato el tiempo desde el ultimo momento calculado con su tiempo hasta el inicial]
//					
//					cuando no hay choque se que el momento anterior es en el que ha habido el choque y devuelvo ese momento
//					con el tiempo tendre la posicion, vel*tiempo = espacio espacio+= espacio del objeto = posicion futura
//	



/**
 * (char siglas, String nombre, int puntos, Color color, ImageIcon imagen,
 * String rutaImagen, int golesEnContraTotales, int golesEnContraLocal, int
 * golesEnContraVisitante, int golesAFavorTotales, int golesAFavorVisitante, int
 * golesAFavorLocal, int victoriasTotales, int victoriasLocal, int
 * victoriasVisitante, int derrotasTotales, int derrotasLocal, int
 * derrotasVisitante, int empatesTotales, int empatesLocal, int
 * empatesVisitante) {
 **/