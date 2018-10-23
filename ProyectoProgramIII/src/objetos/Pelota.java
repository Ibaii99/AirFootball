package objetos;

import java.awt.Color;
import java.awt.Image;

/**
 * @author ibai
 *
 */
public class Pelota extends Objetos{
	
	/** Metodo constructor de la clase pelota
	 * @param color	Color de la pelota
	 * @param nombre Nombre de la pelota
	 * @param alto Altura de la pelota
	 * @param ancho Anchura de la pelota
	 * @param masa	Masa de la pelota
	 */
	public Pelota(Color color, String nombre, double alto, double ancho, double masa) {
		super(0, 0, color, true, nombre,alto, ancho, masa);
	}
	
	
}
