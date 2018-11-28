package entidades;

import java.awt.Color;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import jugador.Jugador;
import menuPrincipal.Liga;

public class BaseDeDatos {
	
	private static Logger logger = Logger.getLogger( "baseDeDatos" );

	private static Connection con;
	private static Statement jugadores;
	private static Statement liga;
	private static Statement equipo;
	private static Statement jugador;
	private static ResultSet rs;
	private static String nombre;

	
	public BaseDeDatos(Jugador j) {
		this.nombre = j.getNombre();
	}
	
	public void anyadirJugador(Jugador j) {
		
		String contrasenya = "";
		for(int e = 0; e < j.getPassword().length; e++) {
			contrasenya += j.getPassword()[e];
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlite:airHockey"+ j.getNombre()+".db");
			jugador = con.createStatement();
			String query = "INSERT INTO Jugadores('Nombre','Password') VALUES ('"+j.getNombre()+"','"+contrasenya +"')";
			jugador.executeQuery(query);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
	public void anyadirLiga(Jugador j, String nombreLiga) {
		
		String contrasenya = "";
		for(int e = 0; e < j.getPassword().length; e++) {
			contrasenya += j.getPassword()[e];
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlite:airHockey"+ j.getNombre()+".db");
			liga = con.createStatement();
			String query = "INSERT INTO Liga('CodLiga','fk_Nombre_Jugador', 'Nombre') VALUES (' ','"+j.getNombre()+"','"+nombreLiga +"')";
			liga.executeQuery(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}}

	
	public void crearTabla() throws ClassNotFoundException {
		String comando = "";
		try {
		Class.forName( "org.sqlite.JDBC" );

		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:airHockey"+ nombre +".db");
		System.out.println("inicio");
		
		try {
			equipo = con.createStatement();
			comando = "CREATE TABLE \"Equipos\" ('fk_CodLiga' INTEGER REFERENCES LIGA(CodLiga) ,'Siglas' TEXT NOT NULL, 'Nombre' TEXT NOT NULL, 'Puntos' INTEGER, 'Goles Encajados Totales' INTEGER, 'Goles Encajados Local' INTEGER, 'Goles Encajados Visitante' INTEGER, 'Goles A Favor Totales' INTEGER, 'Goles A Favor Local' INTEGER, 'Goles A Favor Visitante' INTEGER, 'Derrotas Totales' INTEGER, 'Derrotas Local' INTEGER, 'Derrotas Visitante' INTEGER, 'Victorias Totales' INTEGER, 'Victorias Local' INTEGER, 'Victorias Visitante' INTEGER, 'Empates Totales' INTEGER, 'Empates Local' INTEGER, 'Empates Visitante' INTEGER, 'Color' TEXT, 'Icono' TEXT, PRIMARY KEY('Siglas') )";
			logger.log(Level.INFO, "BD: " + comando);
			equipo.executeUpdate(comando);
		} catch (SQLException i) {
			i.printStackTrace();
		} // Se lanza si la tabla ya existe - no hay problema

		try {
			liga = con.createStatement();
			comando = "CREATE TABLE \"Liga\" ( 'CodLiga' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'fk_Nombre_Jugador' TEXT REFERENCES JUGADORES(NOMBRE), 'Nombre' TEXT NOT NULL)";
			logger.log(Level.INFO, "BD: " + comando);
			liga.executeUpdate(comando);
		} catch (SQLException i) {
			i.printStackTrace();
		} // Se lanza si la tabla ya existe - no hay problema
			
		try {
			jugadores = con.createStatement();
			comando = "CREATE TABLE \"Jugadores\" ( 'Nombre' TEXT PRIMARY KEY NOT NULL, 'Password' TEXT NOT NULL)";
			logger.log( Level.INFO, "BD: " + comando );
			jugadores.executeUpdate( comando );
				} catch (SQLException i) {i.printStackTrace(); }// Se lanza si la tabla ya existe - no hay problema
		con.close();	
		}
			catch (SQLException i) {i.printStackTrace(); }}
	
		
	public void anyadirEquipo(Equipo e, int indiceLiga) {
		String comando = "";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
			equipo = con.createStatement();
		//	comando = "alter table equipos modify puntos default null;";
		//	comando = "INSERT INTO EQUIPOS ( Siglas, Nombre, Puntos, Goles Encajados Totales, Goles Encajados Local, Goles Encajados Visitante, Goles A Favor Totales, Goles A Favor Local, Goles A Favor Visitante, Derrotas Totales, Derrotas Local, Derrotas Visitante, Victorias Totales, Victorias Local, Victorias Visitante, Empates Totales, Empates Local, Empates Visitante, Color, Icono)";
			
			comando = "INSERT INTO Equipos ('fk_CodLiga', 'Siglas', 'Nombre', 'Puntos', 'Goles Encajados Totales', 'Goles Encajados Local', 'Goles Encajados Visitante', 'Goles A Favor Totales', 'Goles A Favor Local', 'Goles A Favor Visitante', 'Derrotas Totales', 'Derrotas Local', 'Derrotas Visitante', 'Victorias Totales', 'Victorias Local', 'Victorias Visitante', 'Empates Totales', 'Empates Local', 'Empates Visitante', 'Color', 'Icono')";
			comando += " VALUES ('" + indiceLiga + "','"+ e.getSiglas() + "','" + e.getNombre() + "','" + e.getPuntos() + "','"
					+ e.getGolesEnContraTotales() + "','" + e.getGolesEnContraLocal() + "','"
					+ e.getGolesEnContraVisitante() + "','" + e.getGolesAFavorTotales() + "','" + e.getGolesAFavorLocal()
					+ "','" + e.getGolesAFavorVisitante() + "','" + e.getDerrotasTotales() + "','" + e.getDerrotasLocal()
					+ "','" + e.getDerrotasVisitante() + "','" + e.getVictoriasTotales() + "','" + e.getVictoriasLocal() + "','"
					+ e.getVictoriasVisitante() + "','" + e.getEmpatesTotales() + "','" + e.getEmpatesLocal() + "','"
					+ e.getEmpatesVisitante() + "','" + e.getBolaEquipo().getColor().toString() + "','" + e.getBolaEquipo().getRutaImagen() + "')"; 
//			comando = "INSERT INTO Equipos ( 'Siglas', 'Nombre')";
//			comando += " VALUES (" + e.getSiglas() + ""," + e.getNombre()+")"; 
			equipo.executeUpdate(comando);
		} catch (Exception o) {
			
		}
//		try {
//			Class.forName( "org.sqlite.JDBC" );
//			con = DriverManager.getConnection( "jdbc:sqlite:airHockey.db" );
//	
//			comando = "INSERT INTO EQUIPOS ( Siglas, Nombre, Puntos, Goles Encajados Totales, Goles Encajados Local, Goles Encajados Visitante, Goles A Favor Totales, Goles A Favor Local, Goles A Favor Visitante, Derrotas Totales, Derrotas Local, Derrotas Visitante, Victorias Totales, Victorias Local, Victorias Visitante, Empates Totales, Empates Local, Empates Visitante, Color, Icono)";
//								comando += "VALUES ("+e.getSiglas()+","+e.getNombre()+","+e.getPuntos()+","+e.getGolesEnContraTotales()+","+e.getGolesEnContraLocal()+","+e.getGolesEnContraVisitante()+","+e.getGolesAFavorTotales()+","+e.getGolesAFavorLocal()+","+e.getGolesAFavorVisitante()+","+e.getDerrotasTotales()+","+e.getDerrotasLocal()+","+e.getDerrotasVisitante()+","+e.getVictoriasTotales()+","+e.getVictoriasLocal()+","+e.getVictoriasVisitante()+","+e.getEmpatesTotales()+","+e.getEmpatesLocal()+","+e.getEmpatesVisitante()+","+e.getColor().toString()+","+e.getRutaImagen()+","+e.getSiglas()+") ;";
////			equipo.execute(comando);
//			
//			equipo.executeUpdate(comando);
//			
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
	}
	
	/** Metodo para select de equipos que coge los datos del result set y crea un objeto equipo
	 * @param rs 	Sentencia de select del equipo
	 * @return		Objeto Equipo
	 * @throws SQLException
	 */
	public Equipo convertirAEquipo(ResultSet rs) throws SQLException {
		
		String nombre = rs.getString("Nombre");
		String siglas= rs.getString("Siglas");
		int puntos= Integer.parseInt(rs.getString("Puntos"));
		int get= Integer.parseInt(rs.getString("Goles Encajados Totales"));
		int gel= Integer.parseInt(rs.getString("Goles Encajados Local"));
		int gev= Integer.parseInt(rs.getString("Goles Encajados Visitante"));
		int gaft= Integer.parseInt(rs.getString("Goles A Favor Totales"));
		int gafl= Integer.parseInt(rs.getString("Goles A Favor Local"));
		int gafv= Integer.parseInt(rs.getString("Goles A Favor Visitante"));
		int dt= Integer.parseInt(rs.getString("Derrotas Totales"));
		int dl= Integer.parseInt(rs.getString("Derrotas Local"));
		int dv= Integer.parseInt(rs.getString("Derrotas Visitante"));
		int vt= Integer.parseInt(rs.getString("Victorias Totales"));
		int vl= Integer.parseInt(rs.getString("Victorias Local"));
		int vv= Integer.parseInt(rs.getString("Victorias Visitante"));
		int et= Integer.parseInt(rs.getString("Empates Totales"));
		int el= Integer.parseInt(rs.getString("Empates Local"));
		int ev= Integer.parseInt(rs.getString("Empates Visitante"));
		
		int colorRGB = Integer.parseInt(rs.getString("Color"));
		Color color = new Color(colorRGB);
		
		String rutaImagen = rs.getString("Icono");
		Equipo e = new Equipo(siglas, nombre, puntos,color , rutaImagen, rutaImagen, get, gel, gev, gaft, gafv, gafl, vt, vl, vv, dt, dl, dv, et, el, ev);
		e.toString();
		return e;	}
}
