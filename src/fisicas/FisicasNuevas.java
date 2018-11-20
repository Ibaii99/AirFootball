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
	private static double TIEMPO = 16;
	
	// Tengo que hacer metodos para saber la posicion esperada de la pelota, 
	// Y el tiempo en el que se calcule eso
	
	/** Metodo para cambiar la posición de la pelota, 
	 *	posicionInicial + velocidad * tiempo = posicionActual
	 *	Si la pelota sobrepasa los limites del campo no avanza
	 * @param p			Pelota a la que vas a cambiar la posicion
	 * @param tiempo	Tiempo que ha pasado para mover
	 */
	public void muevePelota(Pelota p, double tiempo, ventanaPartido v) {
		p.setxAntes(p.getX());
		p.setyAntes(p.getY());
		
		double posicionFuturaX = p.getX()+(p.getVelX()*tiempo);
		double posicionFuturaY = p.getY()+(p.getVelY()*tiempo);
		
		if(posicionFuturaX > (v.getPanelCampo().getSize().getWidth()-1-p.getRadio())) posicionFuturaX = (v.getPanelCampo().getSize().getWidth()-p.getRadio()-1);
		if(posicionFuturaY > (v.getPanelCampo().getSize().getHeight()-1-p.getRadio()))  posicionFuturaY = (v.getPanelCampo().getSize().getHeight() -p.getRadio()-1);
		
		if(posicionFuturaY <  p.getRadio() ) posicionFuturaY = (p.getRadio()+1);
		if(posicionFuturaX <  p.getRadio() ) posicionFuturaX = (p.getRadio()+1);
		
		
		p.setY(posicionFuturaY);
		p.setX(posicionFuturaX);
		
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
	
	/** Metodo para saber si hay o ha habido un choque en las posiciones de la pelota y el equipo
	 * @param p1 Pelota 1
	 * @param equipo Equipo con el que choca
	 * @return Devuelve true si es verdad, false si no
	 */
	public boolean chocanPelotas(Pelota p1, Equipo equipo) {
		boolean chocan = false;
		if(Math.abs(p1.getX() - equipo.getBolaEquipo().getX())<= (p1.getRadio() + equipo.getBolaEquipo().getRadio()))chocan = true;
		if(Math.abs(p1.getY() - equipo.getBolaEquipo().getY())<= (p1.getRadio() + equipo.getBolaEquipo().getRadio()))chocan = true;
		return chocan;
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
	
	
	/** Metodo para saber si ha ocurrido u ocurre algun choque en los bordes del campo
	 * @param v	 Ventana donde se juega el partido
	 * @param p	 Pelota con la que se esta jugando
	 * @return	 Devuelve: True si ha ocurrido u ocurre / False si no ocurre o no ha ocurrido
	 */
	public boolean rebotaeEnBorde(ventanaPartido v, Pelota p) {
		boolean hayRebote = false;
		if(v.getPanelCampo().getSize().getWidth()  <= (p.getX() + p.getRadio()))hayRebote = true;	//Choca en la derecha
		if(v.getPanelCampo().getSize().getHeight() <= (p.getY() + p.getRadio()))hayRebote = true;	//Choca arriba
		if((p.getX() - p.getRadio()) <= 0)hayRebote = true;						//Choca a la izquierda
		if((p.getY() - p.getRadio()) <= 0)hayRebote = true;						//Choca abajo
		return hayRebote;
	}
	
	/**	Metodo para cambiar posicion si hay choque con un lateral
	 * @param v		Ventana donde ocurre el choque
	 * @param p		Pelota que genera el choque
	 */
	public void choqueEnBorde(ventanaPartido v, Pelota p) {
		// Invierto los vectores de velocidad
		cambiarVelocidad(p, -p.getVelX(), -p.getY());
	}
	
	private int veces = 1;
	//Aqui hay que terminar esto
	public double puntoExactoChoqueConBorde(ventanaPartido v, Pelota p) {
		boolean hayReboteAntes = false;
		boolean hayReboteAhora = false;
		double tiempoExacto = TIEMPO -1/(veces);
		hayReboteAhora = rebotaeEnBorde(v, p);
		if(hayReboteAhora) {
			hayReboteAntes = hayReboteAhora;
			puntoExactoChoqueConBorde(v, p);
		} else if (!hayReboteAhora && hayReboteAntes) {
			
			
		}
		return tiempoExacto;
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
		//TODO el rebote es igual que contra un lateral del campo
		if(daAlPoste(v, p, paloArribDer)) {
			
		}
		
		if(daAlPoste(v, p, paloAbajoDer)) {
		
		}
		
		if(daAlPoste(v, p, paloArribIzq)) {
			
		}
		
		if(daAlPoste(v, p, paloAbajoIzq)) {
			
		}
	}
	
	



	/** Metodo para saber si un numero es 0, redondeo
	 * @param num	Numero del que se quiere saber si es muy proximo a 0
	 * @return		Devuelve: True-> si es 0 / False-> si no es 0
	 */
	public static boolean igualACero( double num ) {
		return Math.abs(num)<=1E-12;  // 1 * 10^-12
	}
	
}
