package fisicas;

/**
 * @author ibai
 *
 */
public class Fisicas {
	public static double º1º = 2000.0;  // P�xels por segundo cuadrado
	
	
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
}
