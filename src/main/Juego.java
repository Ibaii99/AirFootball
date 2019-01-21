
package main;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import objetos.Pelota;
import objetos.Poste;
import ventanas.ventanaPartido;
import javax.swing.ImageIcon;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import jugador.Jugador;
import ventanas.Inicio;

public class Juego {

	public static ventanaPartido v; // la ventana se creara cuando se elijan los equipos con los que se va a jugar

	private static BaseDeDatos bd = new BaseDeDatos("ALPHA"); 

	private static Pelota p = new Pelota(Color.black, "jabulani", 0, 0, 20, 1, null, false);

	private static FisicasNuevas f = new FisicasNuevas();

	public static void main(String[] args) {

		try { 
			bd.crearTabla();
			bd.anyadirEquiposStandar();
		} catch (Exception e) {
		}
		Inicio i = new Inicio(bd, f);
		i.setVisible(true);

	}
}
