package entidades;
import java.sql.*;
import java.util.logging.Logger;

public class BaseDeDatos {
	private static Logger logger = Logger.getLogger( "MiniPracticaBD" );
	private static Connection con;
	private static Statement Usuario;
	private static Statement Liga;
	private static Statement Equipo;
	private static ResultSet rs;

	public void crearTabla() {
	String comando ="";
	try {
	Class.forName( "org.sqlite.JDBC" );
	con = DriverManager.getConnection( "jdbc:sqlite:test.db" );
	Equipo = con.createStatement();
	}
	catch(Exception e) {
		
	}
}
}

