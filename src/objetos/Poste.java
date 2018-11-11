package objetos;

import java.awt.Color;
import java.awt.Point;

import ventanas.ventanaPartido;

/**
 * @author ibai
 *
 */
public class Poste extends Objetos{
	double ANCHURA_POSTE = 25;
	double MASA_POSTE = 99999;
	double ANCHURA_PORTERIA = 120;
	
	public Poste(ventanaPartido v, boolean esPaloDeLaDerecha ) {
		if(esPaloDeLaDerecha)super(0, calcularPosicionDelPalo(v, ANCHURA_PORTERIA), Color.WHITE, false, "Palo izquierda", ANCHURA_POSTE, MASA_POSTE, null);
	}

	/** Metodo para calcular la posicion de un palo de la porteria
	 * @param v					Ventana donde se van a colocar
	 * @param anchuraPorteria	Anchura que va a tener la porteria
	 * @return					devuelve la posicion y del palo
	 */
	public double calcularPosicionDelPalo(ventanaPartido v, double anchuraPorteria) {
		double mitadDeLaPorteria = (v.getAlturaCampo())/2;
		return(mitadDeLaPorteria + anchuraPorteria);
	}

	@Override
	public double getVolumen() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}


	}


