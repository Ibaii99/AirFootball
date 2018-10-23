package objetos;

import java.awt.Image;

import fisicas.Fisicas;

public abstract class Objetos extends Fisicas{
	
	protected double x;
	protected double y;
	protected double velX;
	protected double velY;
	
	protected double xAntes = 0;
	protected double yAntes = 0;
	protected double velXAntes = 0;
	protected double velYAntes = 0;
	
	protected double masa;
	protected Image imagenObjeto;
	
	protected double objetoAlto;
	protected double objetoAncho;
	
	public static boolean DIBUJAR_VELOCIDAD = false;

	/** Constructor de objeto f�sico con datos
	 * @param x	Coordenada x del centro de la pelota
	 * @param y	Coordenada y del centro de la pelota
	 * @param radio	Radio de la pelota
	 * @param color	Color de la pelota (p ej. 'a' = azul)
	 * @param bota	Informaci�n de si la pelota bota (true) o no (false)
	 */
	public Objetos(double x, double y, char color, boolean bota) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.bota = bota;
		this.nombre = "";  // Evita el nullpointer
	}
	
}
