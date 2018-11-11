package objetos;

import java.awt.Color;
import java.awt.Point;

import ventanas.ventanaPartido;

/**
 * @author ibai
 *
 */
public class Poste extends Objetos{
	public static double RADIO_POSTE = 25;
	public static double MASA_POSTE = 99999;
	public static double ANCHURA_PORTERIA = 120;
	
	
	/** Metodo constructor de un poste, 
	 *  Si es poste de arriba te lo situa en : La mitad del campo + la anchura de la porteria
	 *  si es poste de abajo te lo situa en  : La mitad del campo - la anchura de la porteria
	 * @param v					Ventana de juego
	 * @param esPosteIzquierda	Booleano para colocar en el eje X el poste segun en que parte este		
	 * @param esPosteArriba		Booleano para colocar en el eje y el poste segun en que parte este
	 */
	public Poste(ventanaPartido v, boolean esPosteIzquierda, boolean esPosteArriba) {
		super(posicionPoste(v, esPosteIzquierda), ( (v.getAlturaCampo()/2)  + alturaPoste(esPosteArriba)), Color.WHITE, false, "Palo izquierda", RADIO_POSTE, MASA_POSTE, null);
	}


	private static double posicionPoste(ventanaPartido v,boolean esPosteIzquierda) {
		double posicion = v.getAnchuraCampo() - RADIO_POSTE;
		if(esPosteIzquierda) posicion = RADIO_POSTE;
		return posicion;
	}
	
	private static double alturaPoste(boolean esPosteArriba) {
		double medida = -ANCHURA_PORTERIA;
		if(esPosteArriba) medida = ANCHURA_PORTERIA;
		return medida;
	}


	@Override
	public double getVolumen() {
		return Math.PI*(RADIO_POSTE*RADIO_POSTE*RADIO_POSTE)*4/3;
	}

	@Override
	public double getArea() {
		return Math.PI*(RADIO_POSTE*RADIO_POSTE);
	}


	}


