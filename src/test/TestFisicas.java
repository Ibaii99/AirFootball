package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import entidades.Equipo;
import objetos.Pelota;
import ventanas.ventanaPartido;

class TestFisicas {
	private Pelota p = new Pelota(Color.black, "jabulani", 0, 0, 20, 25);
	private Equipo e1 = new Equipo("Futbol club Barcelona", "FCB", Color.red, 50, 70);
	private Equipo e2 = new Equipo("Real Madrid", "RM", Color.white, 50, 70);
	private ventanaPartido v = new ventanaPartido(e1, e2, p);
	private Fisicas
	
	
	/** Comprobación de que los objetos se crean bien y no 
	 *  son null, es decir, que no haya errores de creacion
	 */
	@Test
	void testCrearObjetos() {
		assertNotNull(p);
		assertNotNull(e1);
		assertNotNull(e2);
		assertNotNull(v);
	}
	
	
	@Test
	void testCambiosDePropiedades() {
		e1.getBolaEquipo().setMasa(50);
		assertEquals(50, e1.getBolaEquipo().getMasa());
	}
	
	@Test
	void testMovimiento() {
		p.addVelocidad(1, 0);
		p.muevePelota(p, tiempo);
		
	}
	

}
