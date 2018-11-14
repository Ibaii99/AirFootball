package fisicas;

import entidades.Equipo;
import objetos.Pelota;
import objetos.Poste;
import ventanas.ventanaPartido;

/**
 * @author ibai
 *
 */
public class FisicasNuevas {
	// Tengo que hacer metodos para saber la posicion esperada de la pelota, 
	// Y el tiempo en el que se calcule eso
	/** Metodo para cambiar la posición de la pelota
	 * @param p	Pelota a la que vas a cambiar la posicion
	 * @param x	Posicion en eje x
	 * @param y Posicion en eje y
	 */
	public void muevePelota(Pelota p, double x, double y) {
		p.setxAntes(p.getX());
		p.setyAntes(p.getY());
		p.setY(y);
		p.setX(x);
	}
	
	/** Metodo para cambiar la velocidad de la pelota
	 * @param p		Pelota a la que vas a cambiar la velocidad
	 * @param velX	Velocidad en x
	 * @param velY	Velocidad en y
	 */
	public void cambiarVelocidad(Pelota p, double velX, double velY) {
		p.setVelXAntes(p.getVelX());
		p.setVelYAntes(p.getVelY());
		p.setVelX(velX);
		p.setVelY(velY);
	}
	
	/** Metodo para saber si hay o ha habido un choque en las posiciones de las pelotas
	 * @param p1 Pelota 1
	 * @param p2 Pelota 2
	 * @return Devuelve true si es verdad, false si no
	 */
	public boolean chocanPelotas(Pelota p1, Pelota p2) {
		boolean chocan = false;
		if(Math.abs(p1.getX() - p2.getX())<= (p1.getRadio() + p2.getRadio()))chocan = true;
		if(Math.abs(p1.getY() - p2.getY())<= (p1.getRadio() + p2.getRadio()))chocan = true;
		return chocan;
	}
	
	
	/** Metodo para saber si ha ocurrido u ocurre algun choque en los bordes del campo
	 * @param v	 Ventana donde se juega el partido
	 * @param p	 Pelota con la que se esta jugando
	 * @return	 Devuelve: True si ha ocurrido u ocurre / False si no ocurre o no ha ocurrido
	 */
	public boolean rebotaeEnBorde(ventanaPartido v, Pelota p) {
		boolean hayRebote = false;
		if(v.getAnchuraCampo()<= (p.getX() + p.getRadio()))hayRebote = true;	//Choca en la derecha
		if(v.getAlturaCampo() <= (p.getY() + p.getRadio()))hayRebote = true;	//Choca arriba
		if((p.getX() - p.getRadio()) <= 0)hayRebote = true;						//Choca a la izquierda
		if((p.getY() - p.getRadio()) <= 0)hayRebote = true;						//Choca abajo
		return hayRebote;
	}
	
	
	/** Metodo para saber si la pelota ha chocado o choca contra un poste
	 * @param v			Ventana donde se juega el partido
	 * @param pelota	Pelota con la que se esta jugando
	 * @param palo		Palo del que se quiere saber
	 * @return			Devuelve: True si ha ocurrido u ocurre / False si no ocurre o no ha ocurrido
	 */
	public boolean daAlPoste(ventanaPartido v, Pelota pelota, Poste palo) {
		boolean hayChoque = false;
		if( Math.abs(palo.getX() - pelota.getX()) <= (pelota.getRadio() + palo.RADIO_POSTE) ) hayChoque = true;
		if( Math.abs(palo.getY() - pelota.getY()) <= (pelota.getRadio() + palo.RADIO_POSTE) ) hayChoque = true;
		return hayChoque;
	}
	
	/**	Metodo para cambiar las velocidades de una pelota cuando esta choca con un equipo
	 *  Suponiendo un choque elastico de velPelo = velPelo*masaPelo - velEqui*masaEqui
	 *  El equipo no se verá afectado por el choque
	 * @param p			Pelota que va a sufrir el choque
	 * @param equipo	Equipo con el que choca la pelota
	 */
	public void cambioVelocidadesChoquePelotaEquipo (Pelota p,Equipo equipo) {
		p.setVelX(p.getVelX()*p.getMasa() - equipo.getBolaEquipo().getVelX()*equipo.getBolaEquipo().getMasa());
		p.setVelY(p.getVelY()*p.getMasa() - equipo.getBolaEquipo().getVelY()*equipo.getBolaEquipo().getMasa());
	}

	
	/** El choque con los postes es totalmente inelástico, ya que el poste no tiene velocidad ni va a sufrir
	 *  ninguna consecuencia por el choque
	 * @param v
	 * @param paloArribDer
	 * @param paloAbajoDer
	 * @param paloArribIzq
	 * @param paloAbajoIzq
	 * @param p
	 */
	public void choqueConPalos(ventanaPartido v, Poste paloArribDer, Poste paloAbajoDer, Poste paloArribIzq, Poste paloAbajoIzq, Pelota p) {
		
		if(daAlPoste(v, p, paloArribDer)) {
			
		}
		
		if(daAlPoste(v, p, paloAbajoDer)) {
		
		}
		
		if(daAlPoste(v, p, paloArribIzq)) {
			
		}
		
		if(daAlPoste(v, p, paloAbajoIzq)) {
			
		}
	}
	
	
	public void reboteConPoste() {
		
	}

	/** Metodo para saber si un numero es 0, redondeo
	 * @param num	Numero del que se quiere saber si es muy proximo a 0
	 * @return		Devuelve: True-> si es 0 / False-> si no es 0
	 */
	public static boolean igualACero( double num ) {
		return Math.abs(num)<=1E-12;  // 1 * 10^-12
	}
	
}
