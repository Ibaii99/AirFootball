package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import jugador.Jugador;
import objetos.Pelota;
import ventanas.ventanaPartido;

class ventanaPartidoTest {

	private ventanaPartido vp;
	private Equipo eLocal;
	private Equipo eVisitante;
	private BaseDeDatos bd;
	private static Pelota p = new Pelota(Color.black, "jabulani", 0, 0, 20, 1, null, false);
	private static FisicasNuevas f = new FisicasNuevas();
	
	@Before
	public void before() throws SQLException {
		bd = new BaseDeDatos("ALPHA");
		bd.init();
		char[] a = { 'a', 'b', 'c' };
		eLocal = bd.convertirAEquipo("FC Barcelona", new Jugador("", a, 0), 0);
//		eVisitante = bd.convertirAEquipo("Real Betis", new Jugador("", a, 0), 0);
		bd.close();
	}
	
	
	@Test
	void test() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(eLocal);
		assertEquals(eLocal.getBolaEquipo().getRutaImagen(), "/iconos/equipos/bar.png");;
	}

}
