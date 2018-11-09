package entidades;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatos {
	private static Logger logger = Logger.getLogger( "MiniPracticaBD" );
	private static Connection con;
	private static Statement jugadores;
	private static Statement liga;
	private static Statement equipo;
	private static ResultSet rs;

	public void crearTabla() {
	String comando ="";
	try {
	Class.forName( "org.sqlite.JDBC" );
	con = DriverManager.getConnection( "jdbc:sqlite:airHockey.db" );
	equipo = con.createStatement();
	liga = con.createStatement();
	jugadores = con.createStatement();
		try {
			comando = "CREATE TABLE \"Equipos\" ( `Siglas` TEXT NOT NULL, `Nombre` TEXT NOT NULL, `Puntos` INTEGER, `Goles Encajados Totales` INTEGER, `Goles Encajados Local` INTEGER, `Goles Encajados Visitante` INTEGER, `Goles A Favor Totales` INTEGER, `Goles A Favor Local` INTEGER, `Goles A Favor Visitante` INTEGER, `Derrotas Totales` INTEGER, `Derrotas Local` INTEGER, `Derrotas Visitante` INTEGER, `Victorias Totales` INTEGER, `Victorias Local` INTEGER, `Victorias Visitante` INTEGER, `Empates Totales` INTEGER, `Empates Local` INTEGER, `Empates Visitante` INTEGER, `Color` TEXT, `Icono` TEXT, PRIMARY KEY(`Siglas`) )";
			logger.log( Level.INFO, "BD: " + comando );
			equipo.executeUpdate( comando );
		} catch (SQLException i) {} // Se lanza si la tabla ya existe - no hay problema
		
		try {
			comando = "CREATE TABLE \"Liga\" ( `CodLiga` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT )";
			logger.log( Level.INFO, "BD: " + comando );
			liga.executeUpdate( comando );
		} catch (SQLException i) {} // Se lanza si la tabla ya existe - no hay problema
		
		try {
			comando = "CREATE TABLE \"Jugadores\" ( `Nombre` TEXT NOT NULL, `Password` TEXT NOT NULL, PRIMARY KEY(`Nombre`) )";
			logger.log( Level.INFO, "BD: " + comando );
			jugadores.executeUpdate( comando );
		} catch (SQLException i) {} // Se lanza si la tabla ya existe - no hay problema
	
	}catch(Exception e) {}
}

	public void anyadirEquipo(Equipo e) {
		String comando ="";
		try {
		Class.forName( "org.sqlite.JDBC" );
		con = DriverManager.getConnection( "jdbc:sqlite:airHockey.db" );
		comando = "INSERT INTO TABLE \"Equipos\" ( `Siglas` TEXT NOT NULL, `Nombre` TEXT NOT NULL, `Puntos` INTEGER, `Goles Encajados Totales` INTEGER, `Goles Encajados Local` INTEGER, `Goles Encajados Visitante` INTEGER, `Goles A Favor Totales` INTEGER, `Goles A Favor Local` INTEGER, `Goles A Favor Visitante` INTEGER, `Derrotas Totales` INTEGER, `Derrotas Local` INTEGER, `Derrotas Visitante` INTEGER, `Victorias Totales` INTEGER, `Victorias Local` INTEGER, `Victorias Visitante` INTEGER, `Empates Totales` INTEGER, `Empates Local` INTEGER, `Empates Visitante` INTEGER, `Color` TEXT, `Icono` TEXT, PRIMARY KEY(`Siglas`) )";
		comando += "VALUES ("+e.getSiglas()+","+e.getNombre()+",";
		} catch(Exception o) {}
	}
}

