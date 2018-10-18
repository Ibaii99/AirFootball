package objetos;
import ventanas.*;
import fisicas.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class ObjetoFisico {
	protected double x;      // Coordenada x del objeto
	protected double y;      // Coordenada y del objeto
	protected char color;
	protected boolean bota;
	protected String nombre;
	protected double velocidadX; // Velocidad horizontal del objeto - se inicia a 0
	protected double velocidadY; // Velocidad vertical del objeto - se inicia a 0
	
	protected double velXInicial = 0;   // Velocidad anterior al �ltimo movimiento
	protected double velYInicial = 0;
	protected double antX = 0;  // Posici�n anterior al �ltimo movimiento
	protected double antY = 0;
	
	public static boolean DIBUJAR_VELOCIDAD = false;
	
	/** Constructor de objeto f�sico con datos
	 * @param x	Coordenada x del centro de la pelota
	 * @param y	Coordenada y del centro de la pelota
	 * @param radio	Radio de la pelota
	 * @param color	Color de la pelota (p ej. 'a' = azul)
	 * @param bota	Informaci�n de si la pelota bota (true) o no (false)
	 */
	public ObjetoFisico(double x, double y, char color, boolean bota) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.bota = bota;
		this.nombre = "";  // Evita el nullpointer
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setPosicion(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public boolean isBota() {
		return bota;
	}

	public void setBota(boolean bota) {
		this.bota = bota;
	}
	
	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public double getVelocidadX() {
		return velocidadX;
	}
	
	public double getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadX( double vel ) {
		velocidadX = vel;
	}

	public void setVelocidadY( double vel ) {
		velocidadY = vel;
	}
	
	/** Define la velocidad actual del objeto
	 * @param vectorVel	Vector de velocidad (x,y)
	 */
	public void setVelocidad( Point2D vectorVel ) {
		velocidadX = vectorVel.getX();
		velocidadY = vectorVel.getY();
	}

	/** A�ade una velocidad X a la actual del objeto
	 * @param vel	Velocidad a a�adir a la velocidad X
	 */
	public void addVelocidadX( double vel ) {
		velocidadX += vel;
	}

	/** A�ade una velocidad Y a la actual del objeto
	 * @param vel	Velocidad a a�adir a la velocidad Y
	 */
	public void addVelocidadY( double vel ) {
		velocidadY += vel;
	}

	/** A�ade una velocidad a la actual del objeto
	 * @param vectorVel	Vector a�adido de velocidad (x,y)
	 */
	public void addVelocidad( Point2D vectorVel ) {
		velocidadX += vectorVel.getX();
		velocidadY += vectorVel.getY();
	}

	/** A�ade una velocidad a la actual del objeto
	 * @param velX	Velocidad a a�adir a la velocidad X
	 * @param velY	Velocidad a a�adir a la velocidad Y
	 */
	public void addVelocidad( double velX, double velY) {
		velocidadX += velX;
		velocidadY += velY;
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
	public void dibuja( VentanaGrafica v ) {
		// No se sabe c�mo dibujar el objeto... pero s� la velocidad
		if (DIBUJAR_VELOCIDAD) {
			v.dibujaFlecha( x,  y, x+velocidadX/10, y+velocidadY/10, 1.5f, Color.orange );
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
	
	/** Provoca el movimiento solo horizontal del objeto.
	 * @param v	Ventana de referencia y dibujado
	 * @param miliSgs	Tiempo de ca�da
	 * @param dibujar	true si se quiere borrar y dibujar el objeto en la ventana, false si se hace aparte
	 */
	public void mueveUnPocoX( VentanaGrafica v, long miliSgs, boolean dibujar ) {
		// 1. C�lculos previos
		velXInicial = velocidadX;
		antX = x;
		antY = y;
		// 2. Borrado si procede
		if (dibujar) borra( v );
		// 3. Cambio de posici�n (x)
		setX( Fisica.calcEspacio( getX(), miliSgs, velocidadX ) );
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
	public boolean mueveUnPoco( VentanaGrafica v, long miliSgs, boolean dibujar, Point2D aceleracion ) {
		// 1. C�lculos previos
		Point2D miAceleracion = (aceleracion==null) 
			? new Point2D.Double( 0.0, Fisica.GRAVEDAD ) 
			: new Point2D.Double( aceleracion.getX(), aceleracion.getY() + Fisica.GRAVEDAD );
		velYInicial = velocidadY;  // Guardamos datos para posibles correcciones
		velXInicial = velocidadX;
		antY = y;
		antX = x;
		// 2. Borrado si procede
		if (dibujar) borra( v );
		// 3. Cambio de posici�n (x e y)
		setX( Fisica.calcEspacio( getX(), miliSgs, velocidadX, miAceleracion.getX() ) );
			// setX( Fisica.calcEspacio( getX(), miliSgs, velocidadX ) );  si sabemos que no hay fuerzas horizontales
		setY( Fisica.calcEspacio( getY(), miliSgs, velocidadY, miAceleracion.getY() ) );
			// setY( Fisica.calcEspacio( getY(), miliSgs, velocidadY, Fisica.GRAVEDAD ) );  si solo hay gravedad como fuerza vertical
		// 4. Dibujado si procede
		if (dibujar) dibuja( v );
		// 5. Actualizaci�n de velocidad final
		// Actualizamos la velocidad final con la gravedad     
		setVelocidadX( Fisica.calcVelocidad( getVelocidadX(), miliSgs, miAceleracion.getX() ));
			// no hace falta si no hay aceleraci�n horizontal (cambio de velocidad=0)
		setVelocidadY( Fisica.calcVelocidad( getVelocidadY(), miliSgs, miAceleracion.getY() ));
			// setVelocidadY( Fisica.calcVelocidad( getVelocidadY(), miliSgs, Fisica.GRAVEDAD ));   si solo hay gravedad como fuerza vertical
		return Fisica.igualACero( y-antY ) && (chocaConBorde(v)>=8);
	}
	
		
	/** Devuelve los p�xels horizontales avanzados en el �ltimo movimiento del objeto
	 * @return	N� de pixels avanzados en X en la �ltima llamada a {@link #mueveUnPoco(VentanaGrafica, long, boolean)}
	 */
	public double getAvanceX() {
		return x - antX;
	}
	
	/** Devuelve los p�xels verticales avanzados en el �ltimo movimiento del objeto
	 * @return	N� de pixels avanzados en Y en la �ltima llamada a {@link #mueveUnPoco(VentanaGrafica, long, boolean)}
	 */
	public double getAvanceY() {
		return y - antY;
	}
	
	
	/** Aplicamos un rebote vertical al objeto donde est� (si no bota, se queda parado)
	 * @param coefRestitucion	Coeficiente de restituci�n de la velocidad (entre 0.0 y 1.0)
	 */
	public void rebotaVertical( double coefRestitucion ) {
		// Invertimos la velocidad vertical (rebote)
		if (bota) {  // Si rebota se le devuelve la velocidad con un % de restituci�n
			velocidadY = -velocidadY * coefRestitucion;
		} else {  // Si no bota se queda parada
			velocidadY = 0;
		}
	}
	
	/** Aplicamos un rebote horizontal al objeto donde est� (si no bota, se queda parado)
	 * @param coefRestitucion	Coeficiente de restituci�n de la velocidad (entre 0.0 y 1.0)
	 */
	public void rebotaHorizontal( double coefRestitucion ) {
		// Invertimos la velocidad vertical (rebote)
		if (bota) {  // Si rebota se le devuelve la velocidad con un % de restituci�n
			velocidadX = -velocidadX * coefRestitucion;
		} else {  // Si no bota se queda parada
			velocidadX = 0;
		}
	}
	
	/** Ajusta el objeto al suelo, si se ha "pasado" del suelo. Ajusta la velocidad a la que ten�a cuando lo toc�.
	 * @param v	Ventana de la que ajustar al suelo
	 * @param dibujar	true si se quiere dibujar, false en caso contrario
	 */
	public abstract void corrigeChoqueInferior( VentanaGrafica v, boolean dibujar );
	
	/** Ajusta el objeto al lateral
	 * @param v	Ventana de la que ajustar al lateral
	 * @param izquierda	true para izquierda, false para derecha
	 * @param dibujar	true si se quiere dibujar, false en caso contrario
	 */
	public abstract void corrigeChoqueLateral( VentanaGrafica v, boolean izquierda, boolean dibujar );
	
	/** Detecta el choque del objeto con los bordes de la ventana
	 * @param v	Ventana con la que probar el choque
	 * @return	Devuelve un n�mero formado por la suma de: 0 si no choca, 1 si choca con la izquierda, 2 con la derecha, 4 arriba, 8 abajo.
	 */
	public abstract int chocaConBorde( VentanaGrafica v );
	
	/** Detecta el choque del objeto con otro
	 * @param objeto2	Objeto con el que probar el choque
	 * @return	Devuelve null si no chocan, un vector con forma de punto indicando el �ngulo y amplitud del choque sobre el objeto en curso
	 */
	public abstract Point chocaConObjeto( ObjetoFisico objeto2 );
	
	/** Comprueba si el objeto incluye a un punto dado
	 * @param punto	Punto a chequear
	 * @return	true si el punto est� dentro del objeto, false en caso contrario
	 */
	public abstract boolean contieneA( Point punto );

}
