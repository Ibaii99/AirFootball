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
 * @author ibai
 *
 */
class TestPelotasYFisicas{
	private static char[] pass = {0,0,0};
	private static Jugador j = new Jugador("", pass);
	private static Pelota p = new Pelota(Color.black, "jabulani", 0, 0, 20, 25);
	private static Equipo e1 = new Equipo("ALA", "Deportivo Alav�s", 0, Color.BLUE, ("iconos/equipos/ala.png"), "iconos/equipos/ala.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	private static Equipo e2=new Equipo("ATH", "Athletic Club", 0, Color.RED, ("iconos/equipos/ath.png"), "iconos/equipos/ath.png", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	private static FisicasNuevas fisicas = new FisicasNuevas();
	private static BaseDeDatos bd = new BaseDeDatos("ALPHA");
	private static ventanaPartido v = new ventanaPartido(e1, e2, p, true, false,true, false, fisicas, bd, j, 0, null, 0);
	

	
	/** Comprobación de que los objetos se crean bien y no 
	 *  son null, es decir, que no haya errores de creacion
	 */
	@Test
	void testCrearObjetos() {
		
		assertNotNull(p);
		assertNotNull(bd);
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
		// Comprobamos que al cambiar los atributos del equipo estos se establecen bien
		e1.getBolaEquipo().setMasa(50);
		assertEquals(50, e1.getBolaEquipo().getMasa());
	}
	
	@Test
	void testMovimientoDePelota() {
		
	}
	
	
	/** Test para comprobar que el avance del vehiculo es 
	 *  omogeneo independientemente de su posicion, es decir
	 *  que su degrado es regular en todas las posiciones
	 */
	@Test
	void testDegradadoRegular() {
		p.setX(30);
		p.setY(30);
		// Le pongo una posicion a la pelota
		p.setVelX(1);
		p.setVelY(1);
		// Le damos una velocidad en ambos ejes
		fisicas.muevePelota(p, fisicas.TIEMPO, v);
		// Movemos la pelota
		assertEquals(p.getX(), p.getY());
		// La distancia recorrida en x será la misma que en y
	}
	
	
	
	
	/** Test para comprobar que la pelota se mueve hasta llegar a cero
	 * 	y que su velocidad va decreciendo hasta ser "igual a cero"
	 */
	@Test
	void testAvanceHastaCero(){
		p.setX(v.getWidth()/2);
		p.setY(v.getHeight()/2);
		// Establezco una posicion para x
		p.setVelX(4);
		// Le damos una velocida en X
		while(!fisicas.igualACero(p.getVelX())){
		fisicas.muevePelota(p, 1, v);
		v.degradarVelocidad();
		assertTrue(p.getVelXAntes() > p.getVelX());}
		// Comprobamos que la pelota avanza hasta que 
		// Su velocidad es "cero"
		double VelFinal = p.getVelX();
		for(int e = 0; e < 4; e++) {
		fisicas.muevePelota(p, 1, v);
		v.degradarVelocidad();}
		// Programo un par de movimientos más para comparar que la pelota sigue siendo cero
		assertEquals(fisicas.igualACero(p.getVelX()), fisicas.igualACero(VelFinal));
	}
	
	
	

}
