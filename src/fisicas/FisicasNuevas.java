package fisicas;

import objetos.Pelota;
import ventanas.ventanaPartido;

public class FisicasNuevas {

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
	
	
	public void rebotaeEnBorde(ventanaPartido v) {
		
	}
	
	public void daAlPoste() {
		
	}
	
	public void reboteConPoste() {
		
	}
}
