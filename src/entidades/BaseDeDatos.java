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
	private static Jugador jugadorJ;
	private static int codLiga = 1;
	
	/** Constructor de base de datos con un string para que el nombre del archivo cambie si queremos
	 * @param nombreBD
	 */
	public BaseDeDatos(String nombreBD) {
		this.nombre = nombreBD;
		
	}
	
	/** Metodo pdara añadir jugadores a la tabla jugadores, los jugadores que estaran 'registrados'
	 * @param jugadorJ	Jugador
	 */
	public void anyadirJugador(Jugador jugadorJ) {
		
		String contrasenya = "";
		for(int e = 0; e < jugadorJ.getPassword().length; e++) {
			contrasenya += jugadorJ.getPassword()[e];
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlite:airHockey"+ nombre+".db");
			jugador = con.createStatement();
			String query = "INSERT INTO Jugadores('Nombre','Password') VALUES ('"+jugadorJ.getNombre()+"','"+contrasenya +"')";
			ResultSet rs = jugador.executeQuery(query);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
	/** Metodo para añadir una liga a un jugador, es decir crear una liga nueva desde 0
	 * @param nombreLiga	Nombre de la liga
	 * @param jugadorJ		Jugador que juega la liga
	 */
	public void anyadirLiga(String nombreLiga, Jugador jugadorJ) {	
		try {
			con = DriverManager.getConnection("jdbc:sqlite:airHockey"+ nombre+".db");
			liga = con.createStatement();
			String query = "INSERT INTO Liga('codLigaJugador','fk_Nombre_Jugador', 'Nombre') VALUES ('"+jugadorJ.getCodLiga()+"','"+jugadorJ.getNombre()+"','"+nombreLiga +"')";
			jugadorJ.incCodLiga();
			logger.log(Level.INFO, query);
			liga.execute(query);
			
		//	System.out.println(rs.getString("CodLiga"));
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}}
	
	/** Metodo para crear la tabla de equipos del jugador correspondiente
	 *  La tabla cogera el nomre del jugador para saber que equipos y que resultados
	 *  Tiene la partida del jugador
	 * @param j 	Jugador
	 */
	public void crearTablaEquipos(Jugador j) {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:airHockey"+ nombre +".db");
			equipo = con.createStatement();
			String comando = "CREATE TABLE Equipos"+j.getNombre()+"('fk_CodLiga' INTEGER REFERENCES LIGA(CodLiga),'fk_Nombre_jugador' TEXT REFERENCES Jugadores(Nombre) ,'Siglas' TEXT NOT NULL, 'Nombre' TEXT NOT NULL, 'Puntos' INTEGER, 'Goles Encajados Totales' INTEGER, 'Goles Encajados Local' INTEGER, 'Goles Encajados Visitante' INTEGER, 'Goles A Favor Totales' INTEGER, 'Goles A Favor Local' INTEGER, 'Goles A Favor Visitante' INTEGER, 'Derrotas Totales' INTEGER, 'Derrotas Local' INTEGER, 'Derrotas Visitante' INTEGER, 'Victorias Totales' INTEGER, 'Victorias Local' INTEGER, 'Victorias Visitante' INTEGER, 'Empates Totales' INTEGER, 'Empates Local' INTEGER, 'Empates Visitante' INTEGER, 'Color' TEXT, 'Icono' TEXT, PRIMARY KEY('Siglas') )";
			logger.log(Level.INFO, "BD: " + comando);
			equipo.executeQuery(comando);
		} catch (Exception i) {
			i.printStackTrace();
		} // Se lanza si la tabla ya existe - no hay problema
	}
	
	
	/** Metodo para crear las tablas de Liga
	 *  y de Jugadores, las de equipos se crean a parte
	 */
	public void crearTabla() {
		String comando = "";
		try {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:airHockey"+ nombre +".db");
		System.out.println("inicio");
		
		try {
			liga = con.createStatement();
			comando = "CREATE TABLE Liga( 'CodLiga' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,'codLigaJugador' INTEGER, 'fk_Nombre_Jugador' TEXT REFERENCES JUGADORES(NOMBRE), 'Nombre' TEXT NOT NULL)";
			logger.log(Level.INFO, "BD: " + comando);
			liga.execute(comando);
		} catch (SQLException i) {
			i.printStackTrace();
		} // Se lanza si la tabla ya existe - no hay problema
			
		try {
			jugadores = con.createStatement();
			comando = "CREATE TABLE Jugadores( 'Nombre' TEXT PRIMARY KEY NOT NULL, 'Password' TEXT NOT NULL)";
			logger.log( Level.INFO, "BD: " + comando );
			jugadores.execute( comando );
				} catch (SQLException i) {i.printStackTrace(); }// Se lanza si la tabla ya existe - no hay problema
		con.close();	
		}
			catch (Exception i) {i.printStackTrace(); }}
	
		
	/** Metodo para añadir Equipos a la base de datos del jugador correspondiente
	 * @param e				Equipo que se va a añadir
	 * @param indiceLiga	Indice de la liga que esta jugando el Jugador
	 * @param j				Jugador al que se le van a añadir los equipos
	 */
	public void anyadirEquipo(Equipo e, int indiceLiga, Jugador j) {
		String comando = "";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:airHockey"+nombre+".db");
			equipo = con.createStatement();
		
			comando = "INSERT INTO Equipos"+j.getNombre()+" ('fk_CodLiga', 'fk_Nombre_Jugador','Siglas', 'Nombre', 'Puntos', 'Goles Encajados Totales', 'Goles Encajados Local', 'Goles Encajados Visitante', 'Goles A Favor Totales', 'Goles A Favor Local', 'Goles A Favor Visitante', 'Derrotas Totales', 'Derrotas Local', 'Derrotas Visitante', 'Victorias Totales', 'Victorias Local', 'Victorias Visitante', 'Empates Totales', 'Empates Local', 'Empates Visitante', 'Color', 'Icono')";
			comando += " VALUES ('" + indiceLiga + "','"+ j.getNombre()+ "','" +e.getSiglas() + "','" + e.getNombre() + "','" + e.getPuntos() + "','"
					+ e.getGolesEnContraTotales() + "','" + e.getGolesEnContraLocal() + "','"
					+ e.getGolesEnContraVisitante() + "','" + e.getGolesAFavorTotales() + "','" + e.getGolesAFavorLocal()
					+ "','" + e.getGolesAFavorVisitante() + "','" + e.getDerrotasTotales() + "','" + e.getDerrotasLocal()
					+ "','" + e.getDerrotasVisitante() + "','" + e.getVictoriasTotales() + "','" + e.getVictoriasLocal() + "','"
					+ e.getVictoriasVisitante() + "','" + e.getEmpatesTotales() + "','" + e.getEmpatesLocal() + "','"
					+ e.getEmpatesVisitante() + "','" + e.getBolaEquipo().getColor().getRGB() + "','" + e.getBolaEquipo().getRutaImagen() + "')"; 
			equipo.execute(comando);
			
			con.close();
			
		} catch (Exception o) {
			o.printStackTrace();
		}

		
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
