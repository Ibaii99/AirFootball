package objetos;

import java.awt.Color;
import java.awt.Point;

import ventanas.ventanaPartido;

/**
 * @author ibai
 *
 */
public class Poste extends Objetos{
	
	public Poste(ventanaPartido v) {
		super(x, altura, Color.WHITE, false, "Poste", radio, masa)
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


