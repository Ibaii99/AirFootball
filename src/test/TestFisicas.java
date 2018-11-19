package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import entidades.Equipo;
import objetos.Pelota;
import ventanas.ventanaPartido;

class TestFisicas {
	private Pelota p;
	private Equipo e1;
	private Equipo e2;
	private ventanaPartido v;
	
	
	/** Aqui se declaran los elementos que se inicializan para hacer los test
	 *  Before es lo que se va a ejecutar antes de los test, NO ES UN TEST.
	 */
	@Before
	void testCrearObjetos() {
		p = new Pelota(Color.black, "jabulani", 0, 0, 20, 25);
		e1 = new Equipo("Futbol club Barcelona", "FCB", Color.red, 50, 70);
		e2 = new Equipo("Real Madrid", "RM", Color.white, 50, 70);
		v = new ventanaPartido(e1, e2, p);
	}
	
	
	@Test
	void testCambiosDePropiedades() {
		e1.getBolaEquipo().setMasa(50);
		assertEquals(50, e1.getBolaEquipo().getMasa());
	}
	
	@Test
	void testMovimiento() {
		
	}
	

}
