package entidades;

import java.awt.Color;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import jugador.Jugador;
import menuPrincipal.Liga;

public class BaseDeDatos {

	private static Logger logger = Logger.getLogger("baseDeDatos");

	private static Connection con;

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		BaseDeDatos.logger = logger;
	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		BaseDeDatos.con = con;
	}

	public static Statement getJugadores() {
		return jugadores;
	}

	public static void setJugadores(Statement jugadores) {
		BaseDeDatos.jugadores = jugadores;
	}

	public static Statement getLiga() {
		return liga;
	}

	public static void setLiga(Statement liga) {
		BaseDeDatos.liga = liga;
	}

	public static Statement getEquipo() {
		return equipo;
	}

	public static void setEquipo(Statement equipo) {
		BaseDeDatos.equipo = equipo;
	}

	public static Statement getJugador() {
		return jugador;
	}

	public static void setJugador(Statement jugador) {
		BaseDeDatos.jugador = jugador;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		BaseDeDatos.rs = rs;
	}

	public static Jugador getJugadorJ() {
		return jugadorJ;
	}

	public static void setJugadorJ(Jugador jugadorJ) {
		BaseDeDatos.jugadorJ = jugadorJ;
	}

	public static int getCodLiga() {
		return codLiga;
	}

	public static void setCodLiga(int codLiga) {
		BaseDeDatos.codLiga = codLiga;
	}

	public static void setNombre(String nombre) {
		BaseDeDatos.nombre = nombre;
	}

	private static Statement jugadores;
	private static Statement liga;
	private static Statement equipo;
	private static Statement jugador;

	private static ResultSet rs;
	private static String nombre;
	private static Jugador jugadorJ;
	private static int codLiga = 1;

	/**
	 * Constructor de base de datos con un string para que el nombre del archivo
	 * cambie si queremos
	 * 
	 * @param nombreBD
	 */
	public BaseDeDatos(String nombreBD) {
		this.nombre = nombreBD;

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////// Metodos de
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// crear////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Metodo para crear las tablas de Liga y de Jugadores, las de equipos se crean
	 * a parte
	 */
	public void crearTabla() {
		String comando = "";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:airHockey" + nombre + ".db");
			System.out.println("inicio");

			try {
				equipo = con.createStatement();
				comando = "CREATE TABLE Equipos('fk_CodLiga' INTEGER REFERENCES LIGA(CodLiga),'fk_Nombre_jugador' TEXT REFERENCES Jugadores(Nombre) ,'Siglas' TEXT NOT NULL, 'Nombre' TEXT NOT NULL, 'Puntos' INTEGER, 'Goles Encajados Totales' INTEGER, 'Goles Encajados Local' INTEGER, 'Goles Encajados Visitante' INTEGER, 'Goles A Favor Totales' INTEGER, 'Goles A Favor Local' INTEGER, 'Goles A Favor Visitante' INTEGER, 'Derrotas Totales' INTEGER, 'Derrotas Local' INTEGER, 'Derrotas Visitante' INTEGER, 'Victorias Totales' INTEGER, 'Victorias Local' INTEGER, 'Victorias Visitante' INTEGER, 'Empates Totales' INTEGER, 'Empates Local' INTEGER, 'Empates Visitante' INTEGER, 'Color' TEXT, 'Icono' TEXT, PRIMARY KEY('Siglas') )";
				logger.log(Level.INFO, "BD: " + comando);
				equipo.executeQuery(comando);
			} catch (Exception i) {
				i.printStackTrace();
			} // Se lanza si la tabla ya existe - no hay problema
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
				comando = "CREATE TABLE Jugadores( 'Nombre' TEXT PRIMARY KEY NOT NULL, 'Password' TEXT NOT NULL, 'cantidadPartidas' INTEGER DEFAULT 0)";
				logger.log(Level.INFO, "BD: " + comando);
				jugadores.execute(comando);
			} catch (SQLException i) {
				i.printStackTrace();
			} // Se lanza si la tabla ya existe - no hay problema
			con.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	/**
	 * Metodo para crear la tabla de equipos del jugador correspondiente La tabla
	 * cogera el nomre del jugador para saber que equipos y que resultados Tiene la
	 * partida del jugador
	 * 
	 * @param j
	 *            Jugador
	 */
	public void crearTablaEquipos(Jugador j) {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:airHockey" + nombre + ".db");
			equipo = con.createStatement();
			String comando = "CREATE TABLE Equipos" + j.getNombre()
					+ "('fk_CodLiga' INTEGER REFERENCES LIGA(CodLiga),'fk_Nombre_jugador' TEXT REFERENCES Jugadores(Nombre) ,'Siglas' TEXT NOT NULL, 'Nombre' TEXT NOT NULL, 'Puntos' INTEGER, 'Goles Encajados Totales' INTEGER, 'Goles Encajados Local' INTEGER, 'Goles Encajados Visitante' INTEGER, 'Goles A Favor Totales' INTEGER, 'Goles A Favor Local' INTEGER, 'Goles A Favor Visitante' INTEGER, 'Derrotas Totales' INTEGER, 'Derrotas Local' INTEGER, 'Derrotas Visitante' INTEGER, 'Victorias Totales' INTEGER, 'Victorias Local' INTEGER, 'Victorias Visitante' INTEGER, 'Empates Totales' INTEGER, 'Empates Local' INTEGER, 'Empates Visitante' INTEGER, 'Color' TEXT, 'Icono' TEXT, PRIMARY KEY('Siglas') )";
			logger.log(Level.INFO, "BD: " + comando);
			equipo.executeQuery(comando);
		} catch (Exception i) {
			i.printStackTrace();
		} // Se lanza si la tabla ya existe - no hay problema
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////// Metodos de
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// añadir///////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo pdara añadir jugadores a la tabla jugadores, los jugadores que
	 * estaran 'registrados'
	 * 
	 * @param jugadorJ
	 *            Jugador
	 */
	public void anyadirJugador(Jugador jugadorJ) {

		String contrasenya = "";
		for (int e = 0; e < jugadorJ.getPassword().length; e++) {
			contrasenya += jugadorJ.getPassword()[e];
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlite:airHockey" + nombre + ".db");
			jugador = con.createStatement();
			String query = "INSERT INTO Jugadores('Nombre','Password','cantidadPartidas') VALUES ('"
					+ jugadorJ.getNombre() + "','" + contrasenya + "','" + jugadorJ.getCodLiga() + "')";
			ResultSet rs = jugador.executeQuery(query);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para añadir una liga a un jugador, es decir crear una liga nueva
	 * desde 0
	 * 
	 * @param nombreLiga
	 *            Nombre de la liga
	 * @param jugadorJ
	 *            Jugador que juega la liga
	 */
	public void anyadirLiga(String nombreLiga, Jugador jugadorJ) {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:airHockey" + nombre + ".db");
			liga = con.createStatement();
			String query = "INSERT INTO Liga('codLigaJugador','fk_Nombre_Jugador', 'Nombre') VALUES ('"
					+ jugadorJ.getCodLiga() + "','" + jugadorJ.getNombre() + "','" + nombreLiga + "')";
			jugadorJ.incCodLiga();
			logger.log(Level.INFO, query);
			liga.execute(query);

			// System.out.println(rs.getString("CodLiga"));
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para añadir Equipos a la base de datos del jugador correspondiente
	 * 
	 * @param e
	 *            Equipo que se va a añadir
	 * @param indiceLiga
	 *            Indice de la liga que esta jugando el Jugador
	 * @param j
	 *            Jugador al que se le van a añadir los equipos
	 */
	public void anyadirEquipo(Equipo e, int indiceLiga, Jugador j) {
		String comando = "";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:airHockey" + nombre + ".db");
			equipo = con.createStatement();

			comando = "INSERT INTO Equipos" + j.getNombre()
					+ " ('fk_CodLiga', 'fk_Nombre_Jugador','Siglas', 'Nombre', 'Puntos', 'Goles Encajados Totales', 'Goles Encajados Local', 'Goles Encajados Visitante', 'Goles A Favor Totales', 'Goles A Favor Local', 'Goles A Favor Visitante', 'Derrotas Totales', 'Derrotas Local', 'Derrotas Visitante', 'Victorias Totales', 'Victorias Local', 'Victorias Visitante', 'Empates Totales', 'Empates Local', 'Empates Visitante', 'Color', 'Icono')";
			comando += " VALUES ('" + indiceLiga + "','" + j.getNombre() + "','" + e.getSiglas() + "','" + e.getNombre()
					+ "','" + e.getPuntos() + "','" + e.getGolesEnContraTotales() + "','" + e.getGolesEnContraLocal()
					+ "','" + e.getGolesEnContraVisitante() + "','" + e.getGolesAFavorTotales() + "','"
					+ e.getGolesAFavorLocal() + "','" + e.getGolesAFavorVisitante() + "','" + e.getDerrotasTotales()
					+ "','" + e.getDerrotasLocal() + "','" + e.getDerrotasVisitante() + "','" + e.getVictoriasTotales()
					+ "','" + e.getVictoriasLocal() + "','" + e.getVictoriasVisitante() + "','" + e.getEmpatesTotales()
					+ "','" + e.getEmpatesLocal() + "','" + e.getEmpatesVisitante() + "','"
					+ e.getBolaEquipo().getColor().getRGB() + "','" + e.getBolaEquipo().getRutaImagen() + "')";
			equipo.execute(comando);

			con.close();

		} catch (Exception o) {
			o.printStackTrace();
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////// Metodos de
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// recibir////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo para select de equipos que coge los datos del result set y crea un
	 * objeto equipo
	 * 
	 * @param rs
	 *            Sentencia de select del equipo
	 * @return Objeto Equipo
	 * @throws SQLException
	 */
	public Equipo convertirAEquipo(String nombreEquipo, Jugador j) {
		Equipo e = null;
		try {
			init();
			Class.forName("org.sqlite.JDBC");
			String query1 = "SELECT * FROM EQUIPOS" + j.getNombre() + " WHERE NOMBRE='" + nombreEquipo + "'; ";
			ResultSet rs = con.createStatement().executeQuery(query1);

			String nombre = rs.getString("Nombre");
			String siglas = rs.getString("Siglas");
			int puntos = Integer.parseInt(rs.getString("Puntos"));
			int get = Integer.parseInt(rs.getString("Goles Encajados Totales"));
			int gel = Integer.parseInt(rs.getString("Goles Encajados Local"));
			int gev = Integer.parseInt(rs.getString("Goles Encajados Visitante"));
			int gaft = Integer.parseInt(rs.getString("Goles A Favor Totales"));
			int gafl = Integer.parseInt(rs.getString("Goles A Favor Local"));
			int gafv = Integer.parseInt(rs.getString("Goles A Favor Visitante"));
			int dt = Integer.parseInt(rs.getString("Derrotas Totales"));
			int dl = Integer.parseInt(rs.getString("Derrotas Local"));
			int dv = Integer.parseInt(rs.getString("Derrotas Visitante"));
			int vt = Integer.parseInt(rs.getString("Victorias Totales"));
			int vl = Integer.parseInt(rs.getString("Victorias Local"));
			int vv = Integer.parseInt(rs.getString("Victorias Visitante"));
			int et = Integer.parseInt(rs.getString("Empates Totales"));
			int el = Integer.parseInt(rs.getString("Empates Local"));
			int ev = Integer.parseInt(rs.getString("Empates Visitante"));

			int colorRGB = Integer.parseInt(rs.getString("Color"));
			Color color = new Color(colorRGB);

			String rutaImagen = rs.getString("Icono");
			e = new Equipo(siglas, nombre, puntos, color, rutaImagen, rutaImagen, get, gel, gev, gaft, gafv, gafl, vt,
					vl, vv, dt, dl, dv, et, el, ev);
			e.toString();
			close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return e;
	}

	/**
	 * Metodo para cargar un usuario de la base de datos si el nombre y la
	 * contraseña son correctos
	 * 
	 * @param nombre
	 *            Nombre del Jugador
	 * @param password
	 *            Contraseña del Jugador
	 * @return Jugador con los atributos de la base de datos
	 */
	public Jugador convertirAJugador(String nombre, char[] password) {
		Jugador j = null;
		String contrasenya = "";
		for (int e = 0; e < password.length; e++) {
			contrasenya += password[e];
		}
		if (estaJugadorEnBaseDeDatos(nombre, password)) {
			try {
				init();
				Class.forName("org.sqlite.JDBC");
				String query1 = "SELECT * FROM JUGADORES WHERE NOMBRE='" + nombre + "' AND PASSWORD='" + contrasenya
						+ "'; ";
				ResultSet rs = con.createStatement().executeQuery(query1);
				System.out.println(rs.getString("NOMBRE"));// Prueba
				j = new Jugador(nombre, password, rs.getInt("cantidadPartidas"));
				rs.close();
				close();
			} catch (Exception u) {
				JOptionPane.showMessageDialog(null, "ESTE JUGADOR NO ESTA REGISTRADO O LA CONTRASE�A ES ERRONEA",
						"ERROR", JOptionPane.WARNING_MESSAGE);
				System.out.println("No registrado");
			}
		}

		return j;
	}

	/**
	 * Metodo para saber si un Jugador esta en la Base De Datos con ese nombre y
	 * contraseña
	 * 
	 * @param nombre
	 *            Nombre del jugador
	 * @param contrasenya
	 *            Contraseña del jugador
	 * @return True si existe // False si no existe
	 * @throws SQLException
	 */
	public boolean estaJugadorEnBaseDeDatos(String nombre, char[] password) {
		try {
			init();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String contrasenya = "";
		for (int e = 0; e < password.length; e++) {
			contrasenya += password[e];
		}

		boolean esta = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String query1 = "SELECT * FROM JUGADORES WHERE NOMBRE='" + nombre + "' AND PASSWORD='" + contrasenya
					+ "'; ";
			System.out.println(con);
			ResultSet rs = con.createStatement().executeQuery(query1);
			if (rs != null)
				esta = true;

		} catch (Exception u) {
			u.printStackTrace();
		}
		try {
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esta;
	}

	/**
	 * Metodo para saber si un Jugador esta en la Base De Datos
	 * 
	 * @param nombre
	 *            Nombre del jugador
	 * @param contrasenya
	 *            Contraseña del jugador
	 * @return True si eciste // False si no existe
	 */
	public boolean estaJugadorRegistrado(String nombre) {
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean esta = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String query1 = "SELECT * FROM JUGADORES WHERE NOMBRE='" + nombre + "'; ";
			ResultSet rs = con.createStatement().executeQuery(query1);
			if (rs != null)
				esta = true;
			close();
		} catch (Exception u) {
			u.printStackTrace();
		}

		return esta;
	}

	public static String getNombre() {
		return nombre;
	}

	public void anyadirTodosLosEquipos(int indice, Jugador j) {
		try {
			anyadirEquipo(new Equipo("ALA", "Deportivo Alav�s", 0, Color.BLUE, ("iconos/equipos/ala.png"),
					"iconos/equipos/ala.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("ATH", "Athletic Club", 0, Color.RED, ("iconos/equipos/ath.png"),
					"iconos/equipos/ath.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("ATM", "Atl�tico de Madrid", 0, Color.RED, ("iconos/equipos/atl.png"),
					"iconos/equipos/atl.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("BAR", "FC Barcelona", 0, Color.BLUE, ("iconos/equipos/bar.png"),
					"iconos/equipos/bar.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("BET", "Real Betis", 0, Color.BLUE, ("iconos/equipos/bet.png"),
					"iconos/equipos/bet.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("CEL", "Celta de Vigo", 0, Color.BLUE, ("iconos/equipos/cel.png"),
					"iconos/equipos/cel.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("DEP", "Deportivo de La Coru�a", 0, Color.BLUE, ("iconos/equipos/dep.png"),
					"iconos/equipos/dep.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("EIB", "SD Eibar", 0, Color.BLUE, ("iconos/equipos/eib.png"),
					"iconos/equipos/eib.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("ESP", "RCD Espanyol", 0, Color.BLUE, ("iconos/equipos/esp.png"),
					"iconos/equipos/esp.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("GET", "Getafe CF", 0, Color.BLUE, ("iconos/equipos/get.png"),
					"iconos/equipos/get.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("GIR", "Girona FC", 0, Color.BLUE, ("iconos/equipos/gir.png"),
					"iconos/equipos/gir.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("HUE", "SD Huesca", 0, Color.BLUE, ("iconos/equipos/hue.png"),
					"iconos/equipos/hue.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("LEG", "Legan�s", 0, Color.BLUE, ("iconos/equipos/leg.png"),
					"iconos/equipos/leg.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("RAY", "Rayo Vallecano", 0, Color.BLUE, ("iconos/equipos/ray.png"),
					"iconos/equipos/ray.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("RMA", "Real Madrid CF", 0, Color.BLUE, ("iconos/equipos/rma.png"),
					"iconos/equipos/rma.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("RSO", "Real Sociedad", 0, Color.BLUE, ("iconos/equipos/rso.png"),
					"iconos/equipos/rso.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("SEV", "Sevilla FC", 0, Color.BLUE, ("iconos/equipos/sev.png"),
					"iconos/equipos/sev.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("VAL", "Valencia CF", 0, Color.BLUE, ("iconos/equipos/val.png"),
					"iconos/equipos/val.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("VLL", "Real Valladolid", 0, Color.BLUE, ("iconos/equipos/vll.png"),
					"iconos/equipos/vll.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);
			anyadirEquipo(new Equipo("VLR", "Villarreal CF", 0, Color.BLUE, ("iconos/equipos/vlr.png"),
					"iconos/equipos/vlr.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), indice, j);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void anyadirEquiposStandar() {
		char[] a = { 'a', 'b' };
		anyadirTodosLosEquipos(0, new Jugador("", a, 0));
		
	}

	public void init() throws SQLException {
		con = DriverManager.getConnection("jdbc:sqlite:airHockey" + nombre + ".db");
	}

	public void close() throws SQLException {
		if (con != null)
			con.close();
	}
	public void actualizarEquipo(Jugador j, Equipo eNuevo) {
		
		try {
			init();
			Class.forName("org.sqlite.JDBC");
			String query1 = "UPDATE Equipos" + j.getNombre() +
					"SET 'fk_CodLiga'=" + j.getCodLiga() + ", 'fk_Nombre_Jugador'=" + j.getNombre()+",'Siglas'=" + eNuevo.getSiglas() 
					 +", 'Nombre'=" + eNuevo.getNombre() + ", 'Puntos'=" + eNuevo.getPuntos()+", 'Goles Encajados Totales'=" + eNuevo.getGolesEnContraTotales()
					+", 'Goles Encajados Local'=" + eNuevo.getGolesEnContraLocal()+",'Goles Encajados Visitante'=" + eNuevo.getGolesEnContraVisitante()
					+", 'Goles A Favor Totales'=" + eNuevo.getGolesAFavorTotales()+", 'Goles A Favor Local'=" + eNuevo.getGolesAFavorLocal() +", 'Goles A Favor Visitante'=" + eNuevo.getGolesAFavorVisitante()
					+", 'Derrotas Totales'=" + eNuevo.getDerrotasTotales()+", 'Derrotas Local'=" + eNuevo.getDerrotasLocal()+", 'Derrotas Visitante'=" + eNuevo.getDerrotasVisitante() 
					+", 'Victorias Totales'=" + eNuevo.getVictoriasTotales()+", 'Victorias Local'=" + eNuevo.getVictoriasLocal()+", 'Victorias Visitante'=" + eNuevo.getVictoriasVisitante()
					+", 'Empates Totales'=" + eNuevo.getEmpatesTotales()+", 'Empates Local'=" + eNuevo.getEmpatesLocal()+", 'Empates Visitante'=" + eNuevo.getEmpatesVisitante()
					+", 'Color'=" + eNuevo.getBolaEquipo().getColor().getRGB()+", 'Icono'=" +eNuevo.getBolaEquipo().getRutaImagen() + 
					" WHERE NOMBRE='" + eNuevo.getNombre() + "'; ";
			ResultSet rs = con.createStatement().executeQuery(query1);
		}catch(Exception i) {
			
		}
	}
}
