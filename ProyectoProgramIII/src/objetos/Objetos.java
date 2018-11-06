package objetos;

import java.awt.Color;
import java.awt.Image;

import fisicas.Fisicas;

public abstract class Objetos extends Fisicas{
	
	protected double masa;
	protected boolean bota;
	
	protected String nombre;
	protected Color color;
	protected Image imagenObjeto;

///////////////////////Posicion///////////////////////////////
	protected double x;
	protected double y;
	
	protected double xAntes = 0;
	protected double yAntes = 0;
	
////////////////////////Velocidad///////////////////////////	
	protected double velX;
	protected double velY;

	protected double velXAntes = 0;
	protected double velYAntes = 0;
	
///////////////////////Tamaño//////////////////////////////////////	
	protected double objetoAlto;
	protected double objetoAncho;
	
	
	public static boolean DIBUJAR_VELOCIDAD = false;

	/** Constructor de objeto f�sico con datos
	 * @param x	Coordenada x del centro de la pelota
	 * @param y	Coordenada y del centro de la pelota
	 * @param color Color elegido de los de por defecto
	 * @param bota True si el objeto rebota
	 * @param nombre Nombre del objeto
	 * @param alto Altura del objeto
	 * @param ancho Anchura del objeto
	 */
	public Objetos(double x, double y, Color color, boolean bota, String nombre, double alto, double ancho, double masa) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.bota = bota;
		this.nombre = nombre; 
		this.objetoAlto = alto; 
		this.objetoAncho = ancho;
		this.masa = masa;
	}
	
}
