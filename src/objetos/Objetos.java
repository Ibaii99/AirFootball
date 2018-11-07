package objetos;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;

import fisicas.Fisicas;
import tema02.mundoConHerencia.VentanaGrafica;
import ventanas.ventanaPartido;

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
	private double velocidad;
	

	protected double velXAntes = 0;
	protected double velYAntes = 0;
	private double velocidadAntes = 0;	
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Image getImagenObjeto() {
		return imagenObjeto;
	}

	public void setImagenObjeto(Image imagenObjeto) {
		this.imagenObjeto = imagenObjeto;
	}

	public double getMasa() {
		return masa;
	}

	public boolean isBota() {
		return bota;
	}

	public String getNombre() {
		return nombre;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getxAntes() {
		return xAntes;
	}

	public double getyAntes() {
		return yAntes;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public double getVelXAntes() {
		return velXAntes;
	}

	public double getVelYAntes() {
		return velYAntes;
	}

	public double getObjetoAlto() {
		return objetoAlto;
	}

	public double getObjetoAncho() {
		return objetoAncho;
	}

	public static boolean isDIBUJAR_VELOCIDAD() {
		return DIBUJAR_VELOCIDAD;
	}

	
	/** Define la velocidad actual del objeto
	 * @param vectorVel	Vector de velocidad (x,y)
	 */
	public void setVelocidad( Point2D vectorVel ) {
		velX = vectorVel.getX();
		velY = vectorVel.getY();
	}

	/** A�ade una velocidad X a la actual del objeto
	 * @param vel	Velocidad a a�adir a la velocidad X
	 */
	public void addVelocidadX( double vel ) {
		velX += vel;
	}

	/** A�ade una velocidad Y a la actual del objeto
	 * @param vel	Velocidad a a�adir a la velocidad Y
	 */
	public void addVelocidadY( double vel ) {
		velY += vel;
	}

	/** A�ade una velocidad a la actual del objeto
	 * @param vectorVel	Vector a�adido de velocidad (x,y)
	 */
	public void addVelocidad( Point2D vectorVel ) {
		velX += vectorVel.getX();
		velY += vectorVel.getY();
	}

	/** A�ade una velocidad a la actual del objeto
	 * @param velX	Velocidad a a�adir a la velocidad X
	 * @param velY	Velocidad a a�adir a la velocidad Y
	 */
	public void addVelocidad( double velX, double velY) {
		velX += velX;
		velY += velY;
	}

	/** Calcula el volumen del objeto
	 * @return	Volumen del objeto
	 */
	public abstract double getVolumen();
	
	/** Calcula el �rea del objeto
	 * @return	Area del objeto
	 */
	public abstract double getArea();
	
	/** Dibuja el objeto en una ventana, en el color correspondiente (por defecto, negro)
	 * @param v	Ventana en la que dibujar el objeto
	 */
	public void dibuja( ventanaPartido v ) {
		// No se sabe c�mo dibujar el objeto... pero s� la velocidad
		if (DIBUJAR_VELOCIDAD) {
			v.dibujaFlecha( x,  y, x+velX/10, y+velY/10, 1.5f, Color.orange );
		}
	}
	
	/** Devuelve el color del objeto en formato AWT
	 * @return	color del objeto, negro si no es interpretable
	 */
	public Color getAWTColor() {
		Color colorDeAWT;
		switch (this.color) {
			case 'a': {
				colorDeAWT = Color.blue;
				break;  // Recordar el break!!!
			}
			case 'r': {
				colorDeAWT = Color.red;
				break;
			}
			case 'v': {
				colorDeAWT = Color.green;
				break;
			}
			default: {
				colorDeAWT = Color.black;  // Por defecto negro
				break;
			}
		}
		return colorDeAWT;
	}
	
	/** Borra el objeto en una ventana
	 * @param v	Ventana en la que borrar el objeto
	 */
	public abstract void borra( VentanaGrafica v );

	/** Provoca el movimiento y ca�da del objeto.
	 * La ca�da se producir� en funci�n de la velocidad e ir� increment�ndose con la gravedad.
	 * @param v	Ventana de referencia y dibujado
	 * @param miliSgs	Tiempo de ca�da
	 * @param dibujar	true si se quiere borrar y dibujar el objeto en la ventana, false si se hace aparte
	 * @return	true si se cae, false si ya se ha parado en el suelo
	 */
	public boolean mueveUnPoco( VentanaGrafica v, long miliSgs, boolean dibujar ) {
		return mueveUnPoco( v, miliSgs, dibujar, null );
	}
	
	
}
