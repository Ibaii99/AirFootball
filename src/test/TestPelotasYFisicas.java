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

/**
 * @author ibai
 *
 */
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
	
	@Test
	void testAvanceSinPasarseDelTope(){

		//TODO en actualizar posicion objetos se pone la x y la y de la pelota a 0
		// si no se pone un sleep no coge bien la anchura y la altura del panel
		v = new ventanaPartido(e1, e2, p, true, false,false);

		v.setAlwaysOnTop(false);
		v.setVisible(false);
		
		//hay que dar tiempo a que se genere la ventana para que los tamaños esten cargados
		//sino va a dar error
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		
		//se colocan los elementos en su posicion ideal
		v.colocarEnPosInicial(p, e1, e2);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		v.actualizarPosicionObjetos(p, e1, e2);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		//como la pelota parte del medio calculo el trayecto que va a hacer, la mitad del campo
		//y le sumo 8 para que comprobar que aun queriendo avanzar 8 veces en esa direccion el programa 
		//no le va a dejar tener esa posicion
		
		for(long e = 0; e < (v.getAnchuraCampo()/2 + 8); e++) {
			//Pongo una pausa para que mostrar las cosas en pantalla sea más claro si se quiere
				
			try {
				Thread.sleep(50);
			} catch (InterruptedException e3) {
				e3.printStackTrace();
			}
		
			fisicas.cambiarVelocidad(p, 1, 1);
			

			
			fisicas.muevePelota(p, 1, v);
			
			System.out.println("Movimiento nº "+e+"	x: "+p.getX()+ "...... y: " + p.getY());
			System.out.println("Tamaño del panel: x: "+ v.getAnchuraCampo() + " y: "+v.getAlturaCampo());
			v.actualizarPosicionObjetos(p, e1, e2);
			
		//	System.out.println(p.getX() + "de" + v.getAnchuraCampo());
			
		}
		
		System.out.println("ya ha llegado al borde de la derecha");
		assertEquals(p.getX(), v.getAnchuraCampo()-p.getRadio()- 1);	
		System.out.println("");
		assertEquals(p.getY(), v.getAlturaCampo()-p.getRadio() - 1);
		v.dispose();
		
	
		
	}
	
	
	

}
