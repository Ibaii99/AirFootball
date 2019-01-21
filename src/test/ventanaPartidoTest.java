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

	private static ventanaPartido vp;
	private static Equipo eLocal;
	private static Equipo eVisitante;
	private static BaseDeDatos bd;
	private static Pelota p = new Pelota(Color.black, "jabulani", 0, 0, 20, 1, null, false);
	private static FisicasNuevas f = new FisicasNuevas();
	try {
		bd = new BaseDeDatos("JUnit");
		try {
			bd.crearTabla();
			bd.anyadirEquiposStandar();
			
		}catch (Exception e) {
			
		}
		bd.init();
		
		char[] a = { 'a', 'b', 'c' };
		eLocal = bd.convertirAEquipo("FC Barcelona", new Jugador("", a, 0), 0);
//		eVisitante = bd.convertirAEquipo("Real Betis", new Jugador("", a, 0), 0);
		bd.close();
	}catch (Exception ss) {
		
	}
	
	@Test
	void test() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(eLocal.getNombre());
		assertEquals(eLocal.getBolaEquipo().getRutaImagen(), "/iconos/equipos/bar.png");
	}

}
