package entidades;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatos {
	
	private static Logger logger = Logger.getLogger( "baseDeDatos" );

	private static Connection con;
	private static Statement jugadores;
	private static Statement liga;
	private static Statement equipo;
	private static ResultSet rs;

	public void crearTabla() throws ClassNotFoundException {

		String comando = "";
		try {
		Class.forName( "org.sqlite.JDBC" );
		con = DriverManager.getConnection( "jdbc:sqlite:airHockey.db" );
	
			try {
	
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
				equipo = con.createStatement();
				liga = con.createStatement();
				jugadores = con.createStatement();
				System.out.println("inicio");
				try {
					comando = "CREATE TABLE \"Equipos\" ( 'Siglas' TEXT NOT NULL, 'Nombre' TEXT NOT NULL, 'Puntos' INTEGER, 'Goles Encajados Totales' INTEGER, 'Goles Encajados Local' INTEGER, 'Goles Encajados Visitante' INTEGER, 'Goles A Favor Totales' INTEGER, 'Goles A Favor Local' INTEGER, 'Goles A Favor Visitante' INTEGER, 'Derrotas Totales' INTEGER, 'Derrotas Local' INTEGER, 'Derrotas Visitante' INTEGER, 'Victorias Totales' INTEGER, 'Victorias Local' INTEGER, 'Victorias Visitante' INTEGER, 'Empates Totales' INTEGER, 'Empates Local' INTEGER, 'Empates Visitante' INTEGER, 'Color' TEXT, 'Icono' TEXT, PRIMARY KEY('Siglas') )";
					logger.log(Level.INFO, "BD: " + comando);
					equipo.executeUpdate(comando);
				} catch (SQLException i) {
					i.printStackTrace();
				} // Se lanza si la tabla ya existe - no hay problema
	
				try {
					comando = "CREATE TABLE \"Liga\" ( 'CodLiga' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT )";
					logger.log(Level.INFO, "BD: " + comando);
					liga.executeUpdate(comando);
					System.out.println(comando);
				} catch (SQLException i) {
					i.printStackTrace();
				} // Se lanza si la tabla ya existe - no hay problema
	
				try {
					comando = "CREATE TABLE \"Jugadores\" ( 'Nombre' TEXT NOT NULL, 'Password' TEXT NOT NULL, PRIMARY KEY('Nombre') )";
					logger.log(Level.INFO, "BD: " + comando);
					jugadores.executeUpdate(comando);
					System.out.println(comando);
				} catch (SQLException i) {
					i.printStackTrace();
				} // Se lanza si la tabla ya existe - no hay problema
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		
//			equipo = con.createStatement();
//			comando = "CREATE TABLE \"Equipos\" ( 'Siglas' TEXT NOT NULL, 'Nombre' TEXT NOT NULL, 'Puntos' INTEGER, 'Goles Encajados Totales' INTEGER, 'Goles Encajados Local' INTEGER, 'Goles Encajados Visitante' INTEGER, 'Goles A Favor Totales' INTEGER, 'Goles A Favor Local' INTEGER, 'Goles A Favor Visitante' INTEGER, 'Derrotas Totales' INTEGER, 'Derrotas Local' INTEGER, 'Derrotas Visitante' INTEGER, 'Victorias Totales' INTEGER, 'Victorias Local' INTEGER, 'Victorias Visitante' INTEGER, 'Empates Totales' INTEGER, 'Empates Local' INTEGER, 'Empates Visitante' INTEGER, 'Color' TEXT, 'Icono' TEXT, 'Siglas' VARCHAR PRIMARY KEY NOT NULL )";
//			logger.log( Level.INFO, "BD: " + comando );
//			equipo.executeUpdate( comando );
			equipo.close();
			
		} catch (SQLException i) { 
			i.printStackTrace();
		} // Se lanza si la tabla ya existe - no hay problema
		
		try {
			liga = con.createStatement();
			comando = "CREATE TABLE \"Liga\" ( 'CodLiga' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT )";
			logger.log( Level.INFO, "BD: " + comando );
			liga.executeUpdate( comando );
		} catch (SQLException i) {} // Se lanza si la tabla ya existe - no hay problema
		
		try {
			jugadores = con.createStatement();
			comando = "CREATE TABLE \"Jugadores\" ( 'Nombre' TEXT NOT NULL, 'Password' TEXT NOT NULL, PRIMARY KEY('Nombre') )";
			logger.log( Level.INFO, "BD: " + comando );
			jugadores.executeUpdate( comando );
		} catch (SQLException i) {
			
		} // Se lanza si la tabla ya existe - no hay problema
	
	catch(Exception e) {
	}
	}


	public void anyadirEquipo(Equipo e) {
		String comando = "";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
			equipo = con.createStatement();
		//	comando = "alter table equipos modify puntos default null;";
		//	comando = "INSERT INTO EQUIPOS ( Siglas, Nombre, Puntos, Goles Encajados Totales, Goles Encajados Local, Goles Encajados Visitante, Goles A Favor Totales, Goles A Favor Local, Goles A Favor Visitante, Derrotas Totales, Derrotas Local, Derrotas Visitante, Victorias Totales, Victorias Local, Victorias Visitante, Empates Totales, Empates Local, Empates Visitante, Color, Icono)";
			
			comando = "INSERT INTO Equipos ( 'Siglas', 'Nombre', 'Puntos', 'Goles Encajados Totales', 'Goles Encajados Local', 'Goles Encajados Visitante', 'Goles A Favor Totales', 'Goles A Favor Local', 'Goles A Favor Visitante', 'Derrotas Totales', 'Derrotas Local', 'Derrotas Visitante', 'Victorias Totales', 'Victorias Local', 'Victorias Visitante', 'Empates Totales', 'Empates Local', 'Empates Visitante', 'Color', 'Icono')";
			comando += " VALUES ('" + e.getSiglas() + "','" + e.getNombre() + "','" + e.getPuntos() + "','"
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
}
