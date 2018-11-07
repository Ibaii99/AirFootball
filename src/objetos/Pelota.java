package objetos;

import java.awt.Color;
import java.awt.Image;

/**
 * @author ibai
 *
 */
public class Pelota extends Objetos{
	private double radio;
	
	/** Metodo constructor de la clase pelota
	 * @param color	Color de la pelota
	 * @param nombre Nombre de la pelota
	 * @param alto Altura de la pelota
	 * @param ancho Anchura de la pelota
	 * @param masa	Masa de la pelota
	 */
	public Pelota(Color color, String nombre, double x, double y, double radio, double masa) {
		super(x, y, color, true, nombre,radio*2,radio*2, masa);
		this.radio = radio;
	}
	
	/** Constructor sencillo de pelota, posiciones: 0,0 y masa=50
	 * @param color	Color a elegir
	 * @param nombre Nombre a elegir	
	 * @param radio	Radio de la pelota
	 */
	public Pelota(Color color, String nombre, double radio) {
		this(color, nombre, radio, 0, 0, 50);
		this.radio = radio;
	}
	
	/** Constructor sencillo de pelota con imagen, posiciones: 0,0 y masa=50
	 * @param imagen
	 * @param nombre
	 * @param radio
	 */
	public Pelota(Image imagen, String nombre, double radio) {
		super(radio, radio, Color.BLACK, true, nombre, 0, 0, 50);
		this.setImagenObjeto(imagen);
		this.radio = radio;
	}

	
	
}
