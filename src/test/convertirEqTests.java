package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import jugador.Jugador;
import objetos.Pelota;
import ventanas.ventanaPartido;

/**
 * @author Jorge 
 * Tests en JUnit del metodo convertirAEquipo de la clase
 *         baseDeDatos, el cual recorre la BD en busca del equipo cuyo nombre
 *         sea el contenido del string que le pasemos.
 */
class convertirEqTests {

	private static ventanaPartido vp;
	private static Equipo eLocal;
	private static BaseDeDatos bd = new BaseDeDatos("ALPHA");
	private static Pelota p = new Pelota(Color.black, "jabulani", 0, 0, 20, 1, null, false);
	private static FisicasNuevas f = new FisicasNuevas();

	@Test
	void test() {
		try {
			bd.crearTabla();
			bd.anyadirEquiposStandar();
			bd.init();

		} catch (Exception e) {

		}
		try {
			bd.init();
			char[] a = { 'a', 'b', 'c' };
			eLocal = bd.convertirAEquipo("FC Barcelona", new Jugador("", a, 0), 0);
			bd.close();
			assertEquals("FC Barcelona", eLocal.getNombre());
			assertEquals("iconos/equipos/bar.png", eLocal.getBolaEquipo().getRutaImagen());
		} catch (Exception e) {

		}
	}

}
