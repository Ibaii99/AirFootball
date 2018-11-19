package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import entidades.Equipo;
import fisicas.FisicasNuevas;
import objetos.Pelota;
import ventanas.ventanaPartido;

class TestPelotasYFisicas{
	
	private Pelota p = new Pelota(Color.black, "jabulani", 0, 0, 20, 25);
	private Equipo e1 = new Equipo("Futbol club Barcelona", "FCB", Color.red, 50, 70);
	private Equipo e2 = new Equipo("Real Madrid", "RM", Color.white, 50, 70);
	private ventanaPartido v = new ventanaPartido(e1, e2, p, true, false,false);
	private FisicasNuevas fisicas = new FisicasNuevas();
	

	
	/** Comprobación de que los objetos se crean bien y no 
	 *  son null, es decir, que no haya errores de creacion
	 */
	@Test
	void testCrearObjetos() {
		assertNotNull(p);
		assertNotNull(e1);
		assertNotNull(e2);
		assertNotNull(v);
		assertNotNull(fisicas);
	}
	
	
	/** Comprobacion de que las alteraciones de los objetos estan bien hechas
	 * 	
	 */
	@Test
	void testCambiosDePropiedades() {
		e1.getBolaEquipo().setMasa(50);
		assertEquals(50, e1.getBolaEquipo().getMasa());
	}
	
	/**	Comprobación de que una pelota a la que se le da una velocidad de x=1 e y=5 
	 * 	partiendo de una posicion base de x=20 e y=15 en un periodo de tiempo de 2 se mueve a:
	 * 	la posicion inicial x + 2
	 * 	la posicion inicial y + 10 
	 */
	@Test
	void testMovimientoDePelota() {
		
		p.setX(20);
		p.setY(15);
		p.addVelocidad(1, 5);
		fisicas.muevePelota(p, 2, v);
		assertEquals(22, p.getX());
		assertEquals(25, p.getY());
	}
	

}
