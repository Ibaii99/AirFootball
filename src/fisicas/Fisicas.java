package fisicas;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

import tema02.mundoConHerencia.Bloque;
import tema02.mundoConHerencia.Fisica;
import tema02.mundoConHerencia.ObjetoFisico;
import tema02.mundoConHerencia.Pelota;
import tema02.mundoConHerencia.PolarPoint;
import tema02.mundoConHerencia.VentanaGrafica;

/**
 * @author ibai
 *
 */
public class Fisicas {
	public static double ROZAMIENTO = 2000.0;  // P�xels por segundo cuadrado
	
	
//////////////////////////////////Metodos sin aceleración///////////////////////////////////////////////////////////
	
	/**	Calcula la velocidad provocada por una energ�a aplicada sobre un objeto
	 * @param energ�a aplicada, en "julixels" (kgs.*pixel^2/seg^2)<br/>
	 *  (un newtixel = trabajo realizado por una fuerza constante de un newtixel durante un pixel en la direcci�n de la fuerza)
	 * @param masa	Masa del objeto en "kgs."
	 * @return	Aceleraci�n provocada, en pixels/segundo^2
	 */
	public static double calcVelocidad( double energia, double masa ) {
		// Ec = 1/2 * m * v^2  (Energ�a cin�tica)
		//   O sea v = sqrt( 2 * Ec / m )
		return Math.sqrt( 2.0 * energia / masa ); 
	}
	
	/** Calcula el cambio de espacio, considerando un movimiento uniforme (sin aceleraci�n)   s(fin) = s(ini) + v * t
	 * @param espacioIni	Espacio inicial (pixels)
	 * @param tiempo	Tiempo transcurrido desde el espacio inicial (milisegundos)
	 * @param vIni	Velocidad inicial (p�xels/seg)
	 * @param aceleracion	Aceleraci�n aplicada (p�xels/seg^2)
	 * @return	Nuevo espacio
	 */
	public static double calcEspacio( double espacioIni, long tiempo, double vIni ) {
		return vIni * tiempo * 0.001 + espacioIni;
	}
	
	/** Calcula el tiempo que falta para que un objeto llegue a un espacio determinado, con movimiento uniforme
	 * @param vIni	Velocidad inicial (p�xels/seg)
	 * @param espIni	Espacio inicial (p�xels)
	 * @param donde	Espacio al que llegar (p�xels)
	 * @return	Tiempo que falta (segundos). Si no es posible que el objeto llegue, valor negativo.
	 */
	public static double calcTiempoHastaEspacio( double vIni, double espIni, double donde ) {
		// s[objetivo] = v[ini]*t[objetivo] + s[ini]  |
		//     t[objetivo] = (s[objetivo] - s[ini]) / v[ini]
		if (igualACero( vIni )) return -1.0;
		double tiempo = (donde - espIni) / vIni;
		if (Double.isInfinite(tiempo)) return -1.0;  // Si error aritm�tico, tiempo negativo para indicar que nunca llega
		return tiempo;
	}
	
//////////////////////////////////Metodos con aceleración///////////////////////////////////////////////////////////
	
	/**	Calcula la aceleraci�n provocada por una fuerza sobre un objeto con una masa
	 * @param fuerza aplicada, en "newtixels" (kgs.*pixel/seg^2)<br/>
	 *  (un newtixel = fuerza que aplicada durante un segundo a una masa de 1 kg incrementa su velocidad en 1 p�xel/seg)
	 * @param masa	Masa del objeto en "kgs."
	 * @return	Aceleraci�n provocada, en pixels/segundo^2
	 */
	public static double calcAceleracion( double fuerza, double masa ) {
		return fuerza / masa;  // 2a Ley de Newton   F = m * a     (a = F/m)
	}
	
	/** Calcula el cambio de espacio, considerando un movimiento uniformemente acelerado  s(fin) = 1/2*a*t^2 + v(ini)*t + s(ini)
	 * @param espacioIni	Espacio inicial (pixels)
	 * @param tiempoMsgs	Tiempo transcurrido desde el espacio inicial (milisegundos)
	 * @param vIni	Velocidad inicial (p�xels/seg)
	 * @param aceleracion	Aceleraci�n aplicada (p�xels/seg^2)
	 * @return	Nuevo espacio
	 */
	public static double calcEspacio( double espacioIni, long tiempoMsgs, double vIni, double aceleracion ) {
		return aceleracion * tiempoMsgs * tiempoMsgs * 0.0000005 + vIni * tiempoMsgs * 0.001 + espacioIni;
	}
	
	
	/** Calcula el tiempo que falta para que un objeto llegue a un espacio determinado, con movimiento uniformemente acelerado
	 * @param vIni	Velocidad inicial (p�xels/seg)
	 * @param espIni	Espacio inicial (p�xels)
	 * @param aceleracion	Aceleraci�n aplicada (p�xels/seg^2)
	 * @param donde	Espacio al que llegar (p�xels)
	 * @return	Tiempo que falta (segundos). Si no es posible que el objeto llegue, valor negativo.
	 */
	public static double calcTiempoHastaEspacio( double vIni, double espIni, double aceleracion, double donde ) {
		// s[objetivo] = 1/2*a*t[objetivo]^2 + v[ini]*t[objetivo] + s[ini]  |
		//     0 = 1/2*a*t[objetivo]^2 + v[ini]*t[objetivo] + (s[ini] - s[objetivo]) |  
		//        (*) Ec. segundo grado: ax2+bx+c=0  |  x = (-b +/- sqrt(b^2 - 4*a*c)) / (2*a)
		//     t[objetivo] = (-v[ini] + sqrt( v[ini]^2 - 4*1/2*a*(s[ini]-s[objetivo]) ) ) / (2*1/2*a)
		if (igualACero(aceleracion)) return calcTiempoHastaEspacio(vIni, espIni, donde);  // Si la aceleraci�n es cero el movimiento es uniforme
		double tiempo = (-vIni + Math.sqrt(vIni*vIni - 2.0*aceleracion*(espIni-donde))) / aceleracion;
		if (Double.isNaN(tiempo)) return -1.0;  // Si no puede llegar, tiempo negativo para indicar que nunca llega
		return tiempo;
	}
	
	/** Calcula el cambio de velocidad, considerando un movimiento uniformemente acelerado    v(fin) = v(ini) + a * t
	 * @param vIni	Velocidad inicial (p�xels/seg)
	 * @param tiempoMsgs	Tiempo transcurrido desde la velocidad inicial (milisegundos)
	 * @param aceleracion	Aceleraci�n aplicada (p�xels/seg^2)
	 * @return	Nueva velocidad
	 */
	public static double calcVelocidad( double vIni, long tiempoMsgs, double aceleracion ) {
		return vIni + aceleracion * 0.001 * tiempoMsgs;
	}
	
	
//////////////////////////////////Metodos generales///////////////////////////////////////////////////////////
	
	
	/** Calcula un choque el�stico entre dos cuerpos
	 * @param masa1	Masa del cuerpo 1 (Kg)
	 * @param vel1	Velocidad del cuerpo 1 lineal hacia el otro cuerpo en el sentido del cuerpo 1 al cuerpo 2
	 * @param masa2	Masa del cuerpo 2 (Kg)
	 * @param vel2	Velocidad del cuerpo 2 lineal hacia el otro cuerpo en el sentido del cuerpo 1 al cuerpo 2
	 * @return
	 */
	public static double[] calcChoque( double masa1, double vel1, double masa2, double vel2 ) {
		// F�rmula de choque perfectamente el�stico. Ver por ejemplo  http://www.fis.puc.cl/~rbenguri/cap4(Dinamica).pdf
		double[] velocFinal = new double[2];
		velocFinal[0] = ((masa1-masa2)*vel1 + 2*masa2*vel2)/(masa1+masa2);
		velocFinal[1] = ((masa2-masa1)*vel2 + 2*masa1*vel1)/(masa1+masa2);
		return velocFinal;
	}
	
	public static boolean igualACero( double num ) {
		return Math.abs(num)<=1E-12;  // 1 * 10^-12
	}
	/** Calcula el choque entre dos pelotas
	 * @param ventana	Ventana en la que ocurre el choque
	 * @param pelota	Pelota 1 que choca
	 * @param pelota2	Pelota 2 que choca
	 * @param milis	Milisegundos que pasan en el paso de movimiento
	 * @param visualizarChoque	true para visualizar la info del choque en la ventana y en consola
	 */
	public static void calcChoqueEntreObjetos( VentanaGrafica ventana, ObjetoFisico objeto, ObjetoFisico objeto2, long milis, boolean visualizarChoque ) {
		if (objeto instanceof Pelota && objeto2 instanceof objetos.Pelota) {
			Pelota pelota = (Pelota) objeto;
			Pelota pelota2 = (Pelota) objeto2;
			Point2D choque = pelota.chocaConObjeto( pelota2 );
			if (choque==null) return;
			if (visualizarChoque)
				System.out.println( "Choque entre " + pelota + " y " + pelota2 + " con vector " + choque );
			Point2D choqueLinea = new Point2D.Double( pelota2.getX()-pelota.getX(), pelota2.getY()-pelota.getY() );
			PolarPoint tangente = PolarPoint.pointToPolar( choqueLinea );
			tangente.transformaANuevoEje( Math.PI/2.0 );  // La tangente es la del choque girada 90 grados
			Point2D tangenteXY = tangente.toPoint();
			Point2D.Double velPelotaXY = new Point.Double( pelota.getVelocidadX(), pelota.getVelocidadY() );
			Point2D.Double velPelota2XY = new Point.Double( pelota2.getVelocidadX(), pelota2.getVelocidadY() );
			PolarPoint velPelota = PolarPoint.pointToPolar( velPelotaXY );
			PolarPoint velPelota2 = PolarPoint.pointToPolar( velPelota2XY );
			velPelota.transformaANuevoEje( tangenteXY );
			velPelota2.transformaANuevoEje( tangenteXY );
			Point2D nuevaVelPelota = velPelota.toPoint();
			Point2D nuevaVelPelota2 = velPelota2.toPoint();
			double[] velChoque = Fisica.calcChoque( pelota.getVolumen(), nuevaVelPelota.getY(), pelota2.getVolumen(), nuevaVelPelota2.getY() );
			nuevaVelPelota.setLocation( nuevaVelPelota.getX(), velChoque[0] );
			nuevaVelPelota2.setLocation( nuevaVelPelota2.getX(), velChoque[1] );
			if (visualizarChoque) {
				// Velocidades antes del choque
				ventana.dibujaFlecha( pelota.getX(), pelota.getY(), pelota.getX()+velPelotaXY.getX()/1000*milis, pelota.getY()+velPelotaXY.getY()/1000*milis, 4.0f, Color.green );
				ventana.dibujaFlecha( pelota2.getX(), pelota2.getY(), pelota2.getX()+velPelota2XY.getX()/1000*milis, pelota2.getY()+velPelota2XY.getY()/1000*milis, 4.0f, Color.green );
				// Eje de choque (magenta) y tangente (negro)
				ventana.dibujaLinea( 500, 200, 500+choqueLinea.getX(), 200+choqueLinea.getY(), 2.0f, Color.magenta );
				ventana.dibujaLinea( 500, 200, 500+tangenteXY.getX(), 200+tangenteXY.getY(), 2.0f, Color.black );
				// Vista de datos en consola
				System.out.println( "Cambio en choque:");
				System.out.println( "  Pelota 1: " + velPelotaXY + " es " + velPelota + " o sea " + nuevaVelPelota );
				System.out.println( "  Pelota 2: " + velPelota2XY + " es " + velPelota2 + " o sea " + nuevaVelPelota2 );
				System.out.println( "  Nueva vel pelota 1: " + nuevaVelPelota );
				System.out.println( "  Nueva vel pelota 2: " + nuevaVelPelota2 );
			}
			velPelota = PolarPoint.pointToPolar(nuevaVelPelota);
			velPelota2 = PolarPoint.pointToPolar(nuevaVelPelota2);
			velPelota.transformaANuevoEje( -Math.atan2( tangenteXY.getY(), tangenteXY.getX() ) );
			velPelota2.transformaANuevoEje( -Math.atan2( tangenteXY.getY(), tangenteXY.getX() ) );
			Point2D velPelotaFin = velPelota.toPoint();
			Point2D velPelota2Fin = velPelota2.toPoint();
			if (visualizarChoque) {
				// Velocidades despu�s del choque
				ventana.dibujaFlecha( pelota.getX(), pelota.getY(), pelota.getX()+velPelotaFin.getX()/1000*milis, pelota.getY()+velPelotaFin.getY()/1000*milis, 4.0f, Color.red );
				ventana.dibujaFlecha( pelota2.getX(), pelota2.getY(), pelota2.getX()+velPelota2Fin.getX()/1000*milis, pelota2.getY()+velPelota2Fin.getY()/1000*milis, 4.0f, Color.red );
				System.out.println( "  Vel fin pelota 1: " + velPelotaFin );
				System.out.println( "  Vel fin pelota 2: " + velPelota2Fin );
			}
			pelota.setVelocidad( velPelotaFin );
			pelota2.setVelocidad( velPelota2Fin );
			if (visualizarChoque) {  // Pelotas tras el choque sin correcci�n
				ventana.dibujaCirculo( pelota.getX(), pelota.getY(), pelota.getRadio(), 2.5f, pelota.getAWTColor() );
				ventana.dibujaCirculo( pelota2.getX(), pelota2.getY(), pelota2.getRadio(), 2.5f, pelota2.getAWTColor() );
				System.out.println( "Montado exacto: " + choque );
			}
			// Corrige posici�n para que no se monten (en funci�n de los avances previos)
			if (Fisicas.igualACero(choque.getX()) && Fisicas.igualACero(choque.getY())) { // Caso de choque est�tico en suelo
				double diferencia = 0.01;
				if (pelota.getX() < pelota2.getX()) diferencia = -diferencia;
				if (visualizarChoque) {  // Correcci�n x
					System.out.println( "  pelota 1 - x: " + pelota.getX() + " - correcci�n directa " + diferencia );
					System.out.println( "  pelota 2 - x: " + pelota2.getX() + " - correcci�n directa " + -diferencia );
				}
				pelota.setX( pelota.getX()+diferencia );  // Corrige y aleja un poquito para que no choquen
				pelota2.setX( pelota2.getX()-diferencia );
			}
			if (!Fisicas.igualACero(choque.getX())) {
				double diferencia = 0.0;
				if (!Fisicas.igualACero(pelota.getAvanceX())) diferencia = Math.abs(pelota.getAvanceX()) / (Math.abs(pelota.getAvanceX()) + Math.abs(pelota2.getAvanceX()));
				double diferencia2 = 1 - diferencia;
				if (visualizarChoque) {  // Correcci�n x
					System.out.println( "  pelota 1 - x: " + pelota.getX() + " - correcci�n " + diferencia );
					System.out.println( "  pelota 2 - x: " + pelota2.getX() + " - correcci�n " + diferencia2 );
				}
				pelota.setX( pelota.getX()-choque.getX()*diferencia*1.1 );  // Corrige y aleja un poquito para que no choquen
				pelota2.setX( pelota2.getX()+choque.getX()*diferencia2*1.1 );
			}
			if (!Fisicas.igualACero(choque.getY())) {
				double diferencia = 0.0;
				if (!Fisicas.igualACero(pelota.getAvanceY())) diferencia = Math.abs(pelota.getAvanceY()) / (Math.abs(pelota.getAvanceY()) + Math.abs(pelota2.getAvanceY()));
				double diferencia2 = 1 - diferencia;
				if (visualizarChoque) {  // Correcci�n y
					System.out.println( "  pelota 1 - y: " + pelota.getY() + " - correcci�n " + diferencia );
					System.out.println( "  pelota 2 - y: " + pelota2.getY() + " - correcci�n " + diferencia2 );
				}
				pelota.setY( pelota.getY()-choque.getY()*diferencia*1.1 );  // Corrige y aleja un poquito para que no choquen
				pelota2.setY( pelota2.getY()+choque.getY()*diferencia2*1.1 );
			}
			if (visualizarChoque) {  // Pelotas tras el choque con correcci�n
				ventana.dibujaCirculo( pelota.getX(), pelota.getY(), pelota.getRadio(), 3f, pelota.getAWTColor() );
				ventana.dibujaCirculo( pelota2.getX(), pelota2.getY(), pelota2.getRadio(), 3f, pelota2.getAWTColor() );
			}
		} else if (objeto instanceof Pelota && objeto2 instanceof Bloque) {
			calcChoqueEntreObjetos( ventana, objeto2, objeto, milis, visualizarChoque );
}
