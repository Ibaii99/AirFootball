package objetos;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;

import fisicas.Fisicas;

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

	public void setMasa(double masa) {
		this.masa = masa;
	}



	public void setBota(boolean bota) {
		this.bota = bota;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setX(double x) {
		this.x = x;
	}



	public void setY(double y) {
		this.y = y;
	}



	public void setxAntes(double xAntes) {
		this.xAntes = xAntes;
	}



	public void setyAntes(double yAntes) {
		this.yAntes = yAntes;
	}



	public void setVelX(double velX) {
		this.velX = velX;
	}



	public void setVelY(double velY) {
		this.velY = velY;
	}



	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}



	public void setVelXAntes(double velXAntes) {
		this.velXAntes = velXAntes;
	}



	public void setVelYAntes(double velYAntes) {
		this.velYAntes = velYAntes;
	}



	public void setVelocidadAntes(double velocidadAntes) {
		this.velocidadAntes = velocidadAntes;
	}



	public void setObjetoAlto(double objetoAlto) {
		this.objetoAlto = objetoAlto;
	}



	public void setObjetoAncho(double objetoAncho) {
		this.objetoAncho = objetoAncho;
	}



	public static void setDIBUJAR_VELOCIDAD(boolean dIBUJAR_VELOCIDAD) {
		DIBUJAR_VELOCIDAD = dIBUJAR_VELOCIDAD;
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
	
	/** Borra el objeto en una ventana
	 * @param v	Ventana en la que borrar el objeto
	 */
	public abstract void borra( ventanaPartido v );

	/** Provoca el movimiento y ca�da del objeto.
	 * La ca�da se producir� en funci�n de la velocidad e ir� increment�ndose con la gravedad.
	 * @param v	Ventana de referencia y dibujado
	 * @param miliSgs	Tiempo de ca�da
	 * @param dibujar	true si se quiere borrar y dibujar el objeto en la ventana, false si se hace aparte
	 * @return	true si se cae, false si ya se ha parado en el suelo
	 */
	public boolean mueveUnPoco( ventanaPartido v, long miliSgs, boolean dibujar ) {
		return mueveUnPoco( v, miliSgs, dibujar, null );
	}
	
	/** Provoca el movimiento solo horizontal del objeto.
	 * @param v	Ventana de referencia y dibujado
	 * @param miliSgs	Tiempo de ca�da
	 * @param dibujar	true si se quiere borrar y dibujar el objeto en la ventana, false si se hace aparte
	 */
	public void mueveUnPocoX( ventanaPartido v, long miliSgs, boolean dibujar ) {
		// 1. C�lculos previos
		velXAntes = velX;
		xAntes = x;
		yAntes = y;
		// 2. Borrado si procede
		if (dibujar) borra( v );
		// 3. Cambio de posici�n (x)
		setX( Fisicas.calcEspacio( getX(), miliSgs, velX ) );
		// 4. Dibujado si procede
		if (dibujar) dibuja( v );
		// 5. Actualizaci�n de velocidad final
		// Sin aceleraci�n sigue siendo la misma)
	}
	/** Provoca la ca�da del objeto (el m�todo lo dibuja cayendo en la ventana)
	 * La ca�da se producir� en funci�n de la velocidad e ir� increment�ndose con la gravedad,
	 * y se invertir� en el rebote (si lo hay)
	 * @param v	Ventana de referencia y dibujado
	 * @param miliSgs	Tiempo de ca�da
	 * @param dibujar	true si se quiere borrar y dibujar el objeto en la ventana, false si se hace aparte
	 * @param aceleracion	Aceleraci�n adicional a la gravedad a aplicar al objeto (si procede). Si es null, no se considera
	 * @return	true si se cae, false si ya se ha parado en el suelo
	 */
	public boolean mueveUnPoco( ventanaPartido v, long miliSgs, boolean dibujar, Point2D aceleracion ) {
		// 1. C�lculos previos
		Point2D miAceleracion = (aceleracion==null) 
			? new Point2D.Double( 0.0, Fisicas.ROZAMIENTO ) 
			: new Point2D.Double( aceleracion.getX(), aceleracion.getY() - Fisicas.ROZAMIENTO);
		velYAntes = velY;  // Guardamos datos para posibles correcciones
		velXAntes= velX;
		yAntes = y;
		xAntes = x;
		// 2. Borrado si procede
		if (dibujar) borra( v );
		// 3. Cambio de posici�n (x e y)
		setX( Fisicas.calcEspacio( getX(), miliSgs, velX, miAceleracion.getX() ) );
			// setX( Fisica.calcEspacio( getX(), miliSgs, velocidadX ) );  si sabemos que no hay fuerzas horizontales
		setY( Fisicas.calcEspacio( getY(), miliSgs, velY, miAceleracion.getY() ) );
			// setY( Fisica.calcEspacio( getY(), miliSgs, velocidadY, Fisica.GRAVEDAD ) );  si solo hay gravedad como fuerza vertical
		// 4. Dibujado si procede
		if (dibujar) dibuja( v );
		// 5. Actualizaci�n de velocidad final
		// Actualizamos la velocidad final con la gravedad     
		setVelX( Fisicas.calcVelocidad( getVelX(), miliSgs, miAceleracion.getX() ));
			// no hace falta si no hay aceleraci�n horizontal (cambio de velocidad=0)
		setVelY( Fisicas.calcVelocidad( getVelY(), miliSgs, miAceleracion.getY() ));
			// setVelocidadY( Fisica.calcVelocidad( getVelocidadY(), miliSgs, Fisica.GRAVEDAD ));   si solo hay gravedad como fuerza vertical
		return Fisicas.igualACero( y-yAntes ) && (chocaConBorde(v)>=8);
	}
	

	
	/** Devuelve los p�xels horizontales avanzados en el �ltimo movimiento del objeto
	 * @return	N� de pixels avanzados en X en la �ltima llamada a {@link #mueveUnPoco(VentanaGrafica, long, boolean)}
	 */
	public double getAvanceX() {
		return x - xAntes;
	}
	
	/** Devuelve los p�xels verticales avanzados en el �ltimo movimiento del objeto
	 * @return	N� de pixels avanzados en Y en la �ltima llamada a {@link #mueveUnPoco(VentanaGrafica, long, boolean)}
	 */
	public double getAvanceY() {
		return y - yAntes;
	}
	
	
	/** Aplicamos un rebote vertical al objeto donde est� (si no bota, se queda parado)
	 * @param coefRestitucion	Coeficiente de restituci�n de la velocidad (entre 0.0 y 1.0)
	 */
	public void rebotaVertical( double coefRestitucion ) {
		// Invertimos la velocidad vertical (rebote)
		if (bota) {  // Si rebota se le devuelve la velocidad con un % de restituci�n
			velY = -velY * coefRestitucion;
		} else {  // Si no bota se queda parada
			velY = 0;
		}
	}
	
	/** Aplicamos un rebote horizontal al objeto donde est� (si no bota, se queda parado)
	 * @param coefRestitucion	Coeficiente de restituci�n de la velocidad (entre 0.0 y 1.0)
	 */
	public void rebotaHorizontal( double coefRestitucion ) {
		// Invertimos la velocidad vertical (rebote)
		if (bota) {  // Si rebota se le devuelve la velocidad con un % de restituci�n
			velX = -velX * coefRestitucion;
		} else {  // Si no bota se queda parada
			velX = 0;
		}
	}
	
	/** Ajusta el objeto al suelo, si se ha "pasado" del suelo. Ajusta la velocidad a la que ten�a cuando lo toc�.
	 * @param v	Ventana de la que ajustar al suelo
	 * @param dibujar	true si se quiere dibujar, false en caso contrario
	 */
	public abstract void corrigeChoqueInferior( ventanaPartido v, boolean dibujar );
	
	/** Ajusta el objeto al lateral
	 * @param v	Ventana de la que ajustar al lateral
	 * @param izquierda	true para izquierda, false para derecha
	 * @param dibujar	true si se quiere dibujar, false en caso contrario
	 */
	public abstract void corrigeChoqueLateral( ventanaPartido v, boolean izquierda, boolean dibujar );
	
	/** Detecta el choque del objeto con los bordes de la ventana
	 * @param v	Ventana con la que probar el choque
	 * @return	Devuelve un n�mero formado por la suma de: 0 si no choca, 1 si choca con la izquierda, 2 con la derecha, 4 arriba, 8 abajo.
	 */
	public abstract int chocaConBorde( ventanaPartido v );
	
	/** Detecta el choque del objeto con otro
	 * @param objeto2	Objeto con el que probar el choque
	 * @return	Devuelve null si no chocan, un vector con forma de punto indicando el �ngulo y amplitud del choque sobre el objeto en curso
	 */
	public abstract Point chocaConObjeto( Objetos objeto2 );
	
	/** Comprueba si el objeto incluye a un punto dado
	 * @param punto	Punto a chequear
	 * @return	true si el punto est� dentro del objeto, false en caso contrario
	 */
	public abstract boolean contieneA( Point punto );

	
}
