package objetos;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


import ventanas.ventanaPartido;

/**
 * @author ibai
 *
 */
public class Pelota extends Objetos{
	
	public static double MASA_POR_DEFECTO = 2;			// Masa por defecto de los constructores simples
	public static double POSICION_Y_POR_DEFECTO = 0 ;		// Posicion Y por defecto de los constructores simples
	public static double POSICION_X_POR_DEFECTO = 0 ;		// Posicion X por defecto de los constructores simples
	public static double RADIO_POR_DEFECTO_EQUIPO = 40;
	
	/** Metodo constructor de la clase pelota con todos los atributos
	 * @param color		Color de la pelota
	 * @param nombre 	Nombre de la pelota
	 * @param masa		Masa de la pelota
	 * @param x			Posicion x inicial de la pelota
	 * @param y			Posicion y inicial de la pelota
	 * @param radio		Radio de la pelota
	 */
	public Pelota(Color color, String nombre, double x, double y, double radio, double masa, String imagen, boolean esEquipo) {
		super(x+ radio, y + radio, color, (!esEquipo), nombre, radio, masa, imagen);
	}
	/** Metodo constructor de la clase pelota SIN imagen y con rebote
	 * @param color		Color de la pelota
	 * @param nombre 	Nombre de la pelota
	 * @param masa		Masa de la pelota
	 * @param x			Posicion x inicial de la pelota
	 * @param y			Posicion y inicial de la pelota
	 * @param radio		Radio de la pelota
	 */
	public Pelota(Color color, String nombre, double x, double y, double radio, double masa) {
		super(x+ radio, y + radio, color, true, nombre, radio, masa, null);
	}
	
	/** Metodo constructor de la clase pelota CON imagen y con rebote
	 * @param imagen	Ruta de la imagen
	 * @param nombre 	Nombre de la pelota
	 * @param masa		Masa de la pelota
	 * @param x			Posicion x inicial de la pelota
	 * @param y			Posicion y inicial de la pelota
	 * @param radio		Radio de la pelota
	 */
	public Pelota(String imagen, String nombre, double x, double y, double radio, double masa) {
		super(x+ radio, y + radio, null, true, nombre, radio, masa, imagen);
	}

	/** Constructor sencillo de pelota, posiciones y masa por defecto y SIN imagen
	 * @param color	Color a elegir
	 * @param nombre Nombre a elegir	
	 * @param radio	Radio de la pelota
	 */
	public Pelota(Color color, String nombre, double radio) {
		super(POSICION_X_POR_DEFECTO+radio, POSICION_Y_POR_DEFECTO+radio, color, true, nombre, radio, MASA_POR_DEFECTO, null);
	}
	
	/** Constructor sencillo de pelota, posiciones y masa por defecto  y CON imagen
	 * @param imagen
	 * @param nombre
	 * @param radio
	 */
	public Pelota(String imagen, String nombre, double radio) {
		super(POSICION_X_POR_DEFECTO+radio, POSICION_Y_POR_DEFECTO+radio, null, true, nombre, radio, MASA_POR_DEFECTO, imagen);
	}

	
	
	
	
	/** Calcula el volumen de la pelota partiendo de su informaci�n de radio
	 * @return	Volumen de la pelota suponiendo una esfera perfecta
	 */
	@Override
	public double getVolumen() {
		return 4.0/3*Math.PI*radio*radio*radio;
	}
	
	/** Calcula el �rea de la pelota partiendo de su informaci�n de radio
	 * @return	Area de la pelota suponiendo una circunferencia perfecta
	 */
	@Override
	public double getArea() {
		return Math.PI*radio*radio;
	}
	
//	/** Dibuja la pelota en una ventana, en el color correspondiente de la pelota (por defecto, negro)
//	 * @param v	Ventana en la que dibujar la pelota
//	 */
//	@Override
//	public void dibuja( ventanaPartido v ) {
//		v.dibujaCirculo( x, y, radio, 1.5f, getColor() );
//		super.dibuja( v );  // Para dibujar la velocidad si procede
//	}
//	
//	/** Borra la pelota en una ventana
//	 * @param v	Ventana en la que borrar la pelota
//	 */
//	@Override
//	public void borra( ventanaPartido v ) {
//		v.borraCirculo( x, y, radio, 1.5f );
//	}
//
//	/** Ajusta la pelota al suelo, si se ha "pasado" del suelo. Ajusta la velocidad a la que ten�a cuando lo toc�.
//	 * @param v	Ventana de la que ajustar al suelo
//	 * @param dibujar	true si se quiere dibujar la pelota, false en caso contrario
//	 */
//	@Override
//	public void corrigeChoqueInferior( ventanaPartido v, boolean dibujar ) {
//		double dondeToca = v.getAltura() - radio;  // Coordenada y en la que se toca el suelo, a la que hay que ajustar
//		if (dondeToca >= y) return;  // Si no est� pasando el suelo, no se hace nada 
//		setY( dondeToca );
//		// Si estaba cayendo con una velocidad positiva (hacia abajo), entonces la velocidad real que ten�a cuando
//		// "toc�" el suelo era algo menor. Corregimos posici�n y velocidad (aproximadamente)
//		if (velYAntes>0 && velY>0) {
//			double tiempoChoque = Fisicas.calcTiempoHastaEspacio( velYAntes, yAntes, Fisicas.ROZAMIENTO, dondeToca);
//			if (tiempoChoque > 0) {  // Si hay un error en el c�lculo no se aplica correcci�n
//				if (dibujar) borra( v );
//				long tiempoMsgs = Math.round(tiempoChoque*1000.0);
//				setX( Fisicas.calcEspacio( xAntes, tiempoMsgs, velXAntes ) );  // Corrige la posici�n
//				setVelY( Fisicas.calcVelocidad( velYAntes, tiempoMsgs, Fisicas.ROZAMIENTO )); // Corrige la velocidad
//				if (dibujar) dibuja( v );
//			}
//			// System.out.println( "Datos: " + tiempoChoque + " " + velYInicial + " " + antY + " " + radio + " " + (velYInicial*velYInicial - 2.0*Fisica.GRAVEDAD*(antY-(v.getAltura()-radio))) );
//		}
//	}
//	
//	/** Ajusta la pelota al lateral
//	 * @param v	Ventana de la que ajustar al lateral
//	 * @param izquierda	true para izquierda, false para derecha
//	 * @param dibujar	true si se quiere dibujar la pelota, false en caso contrario
//	 */
//	@Override
//	public void corrigeChoqueLateral( ventanaPartido v, boolean izquierda, boolean dibujar ) {
//		// Corregimos al lateral sin tocar la velocidad
//		if (dibujar) borra( v );
//		if (izquierda) {
//			setX( radio );
//		} else {
//			setX( v.getAnchura() - radio );
//		}
//		if (dibujar) dibuja( v );
//	}
//	
//	/** Detecta el choque de la pelota con los bordes de la ventana
//	 * @param v	Ventana con la que probar el choque
//	 * @return	Devuelve un n�mero formado por la suma de: 0 si no choca, 1 si choca con la izquierda, 2 con la derecha, 4 arriba, 8 abajo.
//	 */
//	@Override
//	public int chocaConBorde( ventanaPartido v ) {
//		int ret = 0;
//		if (x-radio<=0) ret += 1;
//		if (x+radio>=v.getAnchura()) ret += 2;
//		if (y-radio<=0) ret += 4;
//		if (y+radio>=v.getAltura()) ret += 8;
//		return ret;
//	}
//	
//	/** Detecta el choque de la pelota con otro objeto
//	 * @param pelota2	Pelota con la que probar el choque
//	 * @return	Devuelve null si no chocan, un vector con forma de punto indicando el �ngulo y amplitud del choque sobre la pelota en curso
//	 */
//	@Override
//	public Point chocaConObjeto( Objetos objeto2 ) {
//		if (objeto2 instanceof Pelota) {
//			Pelota pelota2 = (Pelota) objeto2;
//			Point p = new Point();
//			p.setLocation( pelota2.x - x, pelota2.y - y );
//			double dist = p.distance(0,0);
//			double moduloChoque = radio + pelota2.radio - dist;
//			if (moduloChoque < 0) return null;
//			p.setLocation( p.getX() * moduloChoque / dist, p.getY() * moduloChoque / dist );
//			return p;
//		} else if (objeto2 instanceof Poste) {
//			Point choca = objeto2.chocaConObjeto( this );
//			if (choca!=null)
//				choca.setLocation( -choca.getX(), -choca.getY() );
//			return choca;
//		} else {
//			return null;
//		}
//	}
//	
//	/** Comprueba si la pelota incluye a un punto dado
//	 * @param punto	Punto a chequear
//	 * @return	true si el punto est� dentro de la pelota, false en caso contrario
//	 */
//	@Override
//	public boolean contieneA( Point punto ) {
//		double dist = punto.distance( x, y );
//		return dist <= radio;
//	}

	@Override
	public String toString() {
		return String.format( "Pelota %1s (%2$7.2f,%3$7.2f) R=%4$5.1f Vel.=(%5$6.3f,%6$6.3f)", nombre, x, y, radio, velX, velY );
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public double getRadio() {
		return radio;
	}

	}
	

