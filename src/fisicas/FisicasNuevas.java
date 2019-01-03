package fisicas;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.plaf.ActionMapUIResource;

import entidades.Equipo;
import objetos.Pelota;
import objetos.Poste;
import ventanas.ventanaPartido;

/**
 * @author Ibai
 * @author Jorge (simplemente arreglar algunos bugs para que funcione el hilo correctamente, el autor de los métodos es Ibai)
 *
 */
public class FisicasNuevas {
	public static double TIEMPO = 1;
	public static double COEFICIENTE_PERDIDA_PELOTA = 0.991;
	public static double COEFICIENTE_PERDIDA_EQUIPO = 0.90;
	public static double VELOCIDAD_MAX_PELOTA = 6;
	public static double VELOCIDAD_MAX_EQUIPO = 5;
	private static double MARGEN_SEGURIDAD_REBOTES = 10;
	
	// Tengo que hacer metodos para saber la posicion esperada de la pelota, 
	// Y el tiempo en el que se calcule eso
	
	
	///////////////////////////////PELOTA Y EQUIPOS///////////////////////////////////////
	
	/** Metodo que calcula el choque entre la pelota y un equipo SOLO si hay choque
	 * 	sino el metodo no hace nada
	 * @param p			Pelota
	 * @param equipo	Equipo
	 */
	public void choquePelotaEquipo(Pelota p,Equipo equipo) {
		if(chocanPelotas(p, equipo)) cambioVelocidadesChoquePelotaEquipo(p, equipo);
	}
	 
	///////////////////////Metodos internos para calcularlo/////////////////////////////////
	
	/** Metodo para saber si hay o ha habido un choque en las posiciones de la pelota y el equipo
	 * @param p1 Pelota 1
	 * @param equipo Equipo con el que choca
	 * @return Devuelve true si es verdad, false si no
	 */
	private boolean chocanPelotas(Pelota p1, Equipo equipo) {
		boolean chocan = false;
		if(Math.abs(p1.getX() - equipo.getBolaEquipo().getX())<= (p1.getRadio() + equipo.getBolaEquipo().getRadio()) && Math.abs(p1.getY() - equipo.getBolaEquipo().getY())<= (p1.getRadio() + equipo.getBolaEquipo().getRadio()))chocan = true;
		
		if((equipo.getBolaEquipo().getX()-equipo.getBolaEquipo().getRadio()) < p1.getX() && p1.getX() < (equipo.getBolaEquipo().getX()+equipo.getBolaEquipo().getRadio())
				&& (equipo.getBolaEquipo().getY()-equipo.getBolaEquipo().getRadio()) < p1.getY() && p1.getY() < (equipo.getBolaEquipo().getY()+equipo.getBolaEquipo().getRadio())
				) chocan = false;
		return chocan;
	}
	/**	Metodo para cambiar las velocidades de una pelota cuando esta choca con un equipo
	 *  Suponiendo un choque elastico de velPelo = velPelo*masaPelo - velEqui*masaEqui
	 *  El equipo no se verï¿½ afectado por el choque y si la pelota se metre dentro del equipo
	 *  se saca
	 * @param p			Pelota que va a sufrir el choque
	 * @param equipo	Equipo con el que choca la pelota
	 */
	private void cambioVelocidadesChoquePelotaEquipo (Pelota p,Equipo equipo) {
		p.setxAntes(p.getX());
		p.setyAntes(p.getY());
		
	
		if((equipo.getBolaEquipo().getX()-equipo.getBolaEquipo().getRadio()-MARGEN_SEGURIDAD_REBOTES) < p.getX() && p.getX() < (equipo.getBolaEquipo().getX()+equipo.getBolaEquipo().getRadio()+MARGEN_SEGURIDAD_REBOTES)
				&& (equipo.getBolaEquipo().getY()-equipo.getBolaEquipo().getRadio()-MARGEN_SEGURIDAD_REBOTES) < p.getY() && p.getY() < (equipo.getBolaEquipo().getY()+equipo.getBolaEquipo().getRadio()+MARGEN_SEGURIDAD_REBOTES)) { 
//				try {
//				Robot robot = new Robot();
//				robot.keyPress(KeyEvent.VK_U);
//				robot.keyRelease(KeyEvent.VK_U);
//				
//				robot.keyPress(KeyEvent.VK_W);
//				robot.keyRelease(KeyEvent.VK_W);
//				
//				robot.keyPress(KeyEvent.VK_U);
//				robot.keyRelease(KeyEvent.VK_U);
//				
//				robot.keyPress(KeyEvent.VK_UP);
//				robot.keyRelease(KeyEvent.VK_UP);
//				
//				robot.keyPress(KeyEvent.VK_U);
//				robot.keyRelease(KeyEvent.VK_U);
//				
//				}catch(Exception eee) {
//					
//				}
			
				System.out.println("DENTRO");
		
			//TODO
				
				if(p.getX() > equipo.getBolaEquipo().getX()+equipo.getBolaEquipo().getRadio()-MARGEN_SEGURIDAD_REBOTES) {p.setX(p.getX()+p.getRadio()+equipo.getBolaEquipo().getRadio()+MARGEN_SEGURIDAD_REBOTES);p.setY(equipo.getBolaEquipo().getY());}// la bola esta a la derecha del equipo
				if(p.getX() < equipo.getBolaEquipo().getX()-equipo.getBolaEquipo().getRadio()+MARGEN_SEGURIDAD_REBOTES) {p.setX(p.getX()-p.getRadio()-equipo.getBolaEquipo().getRadio()-MARGEN_SEGURIDAD_REBOTES);p.setY(equipo.getBolaEquipo().getY());}// la bola esta a la izquierda del equipo
				if(p.getY() > equipo.getBolaEquipo().getY()+equipo.getBolaEquipo().getRadio()-MARGEN_SEGURIDAD_REBOTES) {p.setY(p.getY()+p.getRadio()+equipo.getBolaEquipo().getRadio()+MARGEN_SEGURIDAD_REBOTES);p.setX(equipo.getBolaEquipo().getX());}// la bola esta encima del equipo
				if(p.getY() < equipo.getBolaEquipo().getY()-equipo.getBolaEquipo().getRadio()+MARGEN_SEGURIDAD_REBOTES) {p.setY(p.getY()-p.getRadio()-equipo.getBolaEquipo().getRadio()-MARGEN_SEGURIDAD_REBOTES);p.setX(equipo.getBolaEquipo().getX());}// la bola esta debajo del equipo
				//cambioVelocidadesChoquePelotaEquipo(p, equipo);

//				if(p.getVelX()<0)p.setVelX(p.getVelX() -equipo.getBolaEquipo().getRadio()/2);
//				if(p.getVelX()>0)p.setVelX(p.getVelX() +equipo.getBolaEquipo().getRadio()/2);
//				
//				if(p.getVelY()<0)p.setVelY(p.getVelY() -equipo.getBolaEquipo().getRadio()/2);
//				if(p.getVelY()>0)p.setVelY(p.getVelY() +equipo.getBolaEquipo().getRadio()/2);
//				
			}
		if(!igualACero(equipo.getBolaEquipo().getVelX())&& !igualACero(equipo.getBolaEquipo().getVelY())) {
			cambiarVelocidadPelota(p, (-p.getVelX()*p.getMasa() + equipo.getBolaEquipo().getVelX()*equipo.getBolaEquipo().getMasa())*4, (-p.getVelY()*p.getMasa() + equipo.getBolaEquipo().getVelY()*equipo.getBolaEquipo().getMasa())*4);//multiplico por dos para que no se quede pegado al jlabel
			
			}
			
			if(igualACero(equipo.getBolaEquipo().getVelX())&& igualACero(equipo.getBolaEquipo().getVelY())) {
				cambiarVelocidadPelota(p, -(p.getVelX()*p.getMasa() )*2,-(p.getVelY()*p.getMasa())*2); //multiplico por dos para que no se quede pegado al jlabel
				
			}
		
			
			
			System.out.println(equipo.getBolaEquipo().getX() + "   " + equipo.getBolaEquipo().getY());
			System.out.println(p.getX() + "   " + p.getY());
			
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////PELOTA Y BORDES///////////////////////////////////////
	
	public static void setTIEMPO(double tIEMPO) {
		TIEMPO = tIEMPO;
	}

	/** Metodo que calcula el choque de la pelota con el borde SOLO si ocurre el choque
	 * @param v		Ventana con los bordes
	 * @param p		Pelota con la que se juega
	 */
	public void choquePelotaBorde(ventanaPartido v, Pelota p) {
		if(rebotaeEnBordeArribAbaj(v, p)) choqueEnBordeArribaoAbajo(v, p);
		if(rebotaeEnBordederIzq(v, p)) choqueEnBordeIzquDer(v, p);
	}
	
	///////////////////////Metodos internos para calcularlo/////////////////////////////////
	
	/** Metodo para saber si ha ocurrido u ocurre algun choque en los bordes verticales del campo
	 * @param v	 Ventana donde se juega el partido
	 * @param p	 Pelota con la que se esta jugando
	 * @return	 Devuelve: True si ha ocurrido u ocurre / False si no ocurre o no ha ocurrido
	 */
	private boolean rebotaeEnBordeArribAbaj(ventanaPartido v, Pelota p) {
		boolean hayRebote = false;
		if(v.getPanelCampo().getSize().getHeight() <= (p.getY() + p.getRadio()+1))hayRebote = true;	//Choca arriba
		if((p.getY() - p.getRadio()) <= 0)hayRebote = true;						//Choca abajo
		return hayRebote;
	}
	/** Metodo para saber si ha ocurrido u ocurre algun choque en los bordes verticales del campo
	 * @param v	 Ventana donde se juega el partido
	 * @param p	 Pelota con la que se esta jugando
	 * @return	 Devuelve: True si ha ocurrido u ocurre / False si no ocurre o no ha ocurrido
	 */
	private boolean rebotaeEnBordederIzq(ventanaPartido v, Pelota p) {
		boolean hayRebote = false;
		if(v.getPanelCampo().getSize().getWidth()  <= (p.getX() + p.getRadio()+1))hayRebote = true;	//Choca en la derecha
		if((p.getX() - p.getRadio()) <= 0)hayRebote = true;						//Choca a la izquierda
		return hayRebote;
	}

	/**	Metodo para cambiar posicion si hay choque Arriba o abajo
	 *  y reduce la velocidad de la bola como consecuencia del impacto
	 *  para hacerlo mas real se reduce un 10%
	 * @param v		Ventana donde ocurre el choque
	 * @param p		Pelota que genera el choque
	 */
	private void choqueEnBordeArribaoAbajo(ventanaPartido v, Pelota p) {
		// Invierto los vectores de velocidad
		if(!igualACero(p.getVelY())&&!igualACero(p.getVelX())) {
			cambiarVelocidadPelota(p, p.getVelX()*0.90, -p.getVelY()*0.90);
		}
		if(igualACero(p.getVelY())&&igualACero(p.getVelX())) {
		cambiarVelocidadPelota(p, 0, 0);}
	}

	/**	Metodo para cambiar posicion si hay choque a la derecha o izquierda
	 *  y reduce la velocidad de la bola como consecuencia del impacto
	 *  para hacerlo mas real se reduce un 10%
	 * @param v		Ventana donde ocurre el choque
	 * @param p		Pelota que genera el choque
	 */
	private void choqueEnBordeIzquDer(ventanaPartido v, Pelota p) {
		// Invierto los vectores de velocidad
		if(!igualACero(p.getVelY())&&!igualACero(p.getVelX())) {
			cambiarVelocidadPelota(p, -p.getVelX()*0.90, p.getVelY()*0.90);
		}
		if(igualACero(p.getVelY())&&igualACero(p.getVelX())) {
		cambiarVelocidadPelota(p, 0, 0);}
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////PELOTA Y POSTES///////////////////////////////////////
	
	/** Metodo que calcula el choque de la pelota con un poste SOLO si ocurre
	 * @param v					Ventana de partido
	 * @param paloArribDer		Poste
	 * @param paloAbajoDer		Poste
	 * @param paloArribIzq		Poste
	 * @param paloAbajoIzq		Poste
	 * @param p					Pelota
	 */
	public void choquePelotaPoste(ventanaPartido v, Poste paloArribDer, Poste paloAbajoDer, Poste paloArribIzq, Poste paloAbajoIzq, Pelota p) {
		if(daAlPoste(v, p, paloArribDer) || daAlPoste(v, p, paloAbajoDer) ||daAlPoste(v, p, paloArribIzq) ||daAlPoste(v, p, paloAbajoIzq)) {
			p.setVelX(-p.getVelXAntes()*COEFICIENTE_PERDIDA_PELOTA);
			p.setVelY(-p.getVelYAntes()*COEFICIENTE_PERDIDA_PELOTA);}
		}
	
	///////////////////////Metodos internos para calcularlo/////////////////////////////////
	
	/** Metodo para saber si la pelota ha chocado o choca contra un poste
	 * @param v			Ventana donde se juega el partido
	 * @param pelota	Pelota con la que se esta jugando
	 * @param palo		Palo del que se quiere saber
	 * @return			Devuelve: True si ha ocurrido u ocurre / False si no ocurre o no ha ocurrido
	 */
	public boolean daAlPoste(ventanaPartido v, Pelota pelota, Poste palo) {
		boolean hayChoque = false;
		if( Math.abs(palo.getX() - pelota.getX()) <= (pelota.getRadio() + palo.RADIO_POSTE) && Math.abs(palo.getY() - pelota.getY()) <= (pelota.getRadio() + palo.RADIO_POSTE)) hayChoque = true;
		
		return hayChoque;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
///////////////////////Metodos para cambiar propiedades de las pelotas/////////////////////////////////
	
	/** Metodo para cambiar la posiciï¿½n de la pelota, 
	 *	posicionInicial + velocidad * tiempo = posicionActual
	 *	Si la pelota sobrepasa los limites del campo no avanza
	 * @param p			Pelota a la que vas a cambiar la posicion
	 * @param tiempo	Tiempo que ha pasado para mover
	 */
	public void muevePelota(Pelota p, double tiempo, ventanaPartido v) {
		
		if(!igualACero(p.getVelX()) && !igualACero(p.getVelY())) {
		p.setxAntes(p.getX());
		p.setyAntes(p.getY());
		
		double posicionFuturaX = p.getX()+(p.getVelX()*tiempo);
		double posicionFuturaY = p.getY()+(p.getVelY()*tiempo);
		
		if(posicionFuturaX > (v.getPanelCampo().getSize().getWidth()-1-p.getRadio())) posicionFuturaX = (v.getPanelCampo().getSize().getWidth()-p.getRadio());
		if(posicionFuturaY > (v.getPanelCampo().getSize().getHeight()-1-p.getRadio()))  posicionFuturaY = (v.getPanelCampo().getSize().getHeight() -p.getRadio());
		
		if(posicionFuturaY <  p.getRadio() ) posicionFuturaY = (p.getRadio());
		if(posicionFuturaX <  p.getRadio() ) posicionFuturaX = (p.getRadio());
		
		
		p.setY(posicionFuturaY);
		p.setX(posicionFuturaX);
		
		}if(igualACero(p.getVelX()) && igualACero(p.getVelY())) {
			p.setY(p.getY());
			p.setX(p.getX());
		}
		
		
	}
	/** Metodo para cambiar la velocidad de la pelota
	 * @param p		Pelota a la que vas a cambiar la velocidad
	 * @param velX	Velocidad en x
	 * @param velY	Velocidad en y
	 */
	public void cambiarVelocidadPelota(Pelota p, double velX, double velY) {
		p.setVelXAntes(p.getVelX());
		p.setVelYAntes(p.getVelY());
		p.setVelY(velY);
		p.setVelX(velX);
		if(p.getVelX() > VELOCIDAD_MAX_PELOTA) p.setVelX(VELOCIDAD_MAX_PELOTA);
		if(p.getVelY() > VELOCIDAD_MAX_PELOTA) p.setVelY(VELOCIDAD_MAX_PELOTA);
		if(p.getVelX() < -VELOCIDAD_MAX_PELOTA) p.setVelX(-VELOCIDAD_MAX_PELOTA);
		if(p.getVelY() < -VELOCIDAD_MAX_PELOTA) p.setVelY(-VELOCIDAD_MAX_PELOTA);
	}

	
	public void mueveEquipo(Equipo e, double tiempo, ventanaPartido v) {
	if(!igualACero(e.getBolaEquipo().getVelX()) && !igualACero(e.getBolaEquipo().getVelY())) {
		e.getBolaEquipo().setxAntes(e.getBolaEquipo().getX());
		e.getBolaEquipo().setyAntes(e.getBolaEquipo().getY());
		
		double posicionFuturaX = e.getBolaEquipo().getX()+(e.getBolaEquipo().getVelX()*tiempo);
		double posicionFuturaY = e.getBolaEquipo().getY()+(e.getBolaEquipo().getVelY()*tiempo);
		
		if(posicionFuturaX > (v.getPanelCampo().getSize().getWidth()-1-e.getBolaEquipo().getRadio())) posicionFuturaX = (v.getPanelCampo().getSize().getWidth()-e.getBolaEquipo().getRadio());
		if(posicionFuturaY > (v.getPanelCampo().getSize().getHeight()-1-e.getBolaEquipo().getRadio()))  posicionFuturaY = (v.getPanelCampo().getSize().getHeight() -e.getBolaEquipo().getRadio());
		
		if(posicionFuturaY <  e.getBolaEquipo().getRadio() ) posicionFuturaY = (e.getBolaEquipo().getRadio());
		if(posicionFuturaX <  e.getBolaEquipo().getRadio() ) posicionFuturaX = (e.getBolaEquipo().getRadio());
		
		
		e.getBolaEquipo().setY(posicionFuturaY);
		e.getBolaEquipo().setX(posicionFuturaX);	}
	if(igualACero(e.getBolaEquipo().getVelX()) && igualACero(e.getBolaEquipo().getVelY())) {
		e.getBolaEquipo().setY(e.getBolaEquipo().getyAntes());
		e.getBolaEquipo().setX(e.getBolaEquipo().getxAntes());
	}
	
	}
//TODO
	public void cambiarVelocidadEquipo(Equipo e, double velX, double velY) {
		e.getBolaEquipo().setVelXAntes(e.getBolaEquipo().getVelX());
		e.getBolaEquipo().setVelYAntes(e.getBolaEquipo().getVelY());
		e.getBolaEquipo().setVelY(velY);
		e.getBolaEquipo().setVelX(velX);
		if(e.getBolaEquipo().getVelX() > VELOCIDAD_MAX_EQUIPO) e.getBolaEquipo().setVelX(VELOCIDAD_MAX_EQUIPO);
		if(e.getBolaEquipo().getVelY() > VELOCIDAD_MAX_EQUIPO) e.getBolaEquipo().setVelY(VELOCIDAD_MAX_EQUIPO);
		if(e.getBolaEquipo().getVelX() < -VELOCIDAD_MAX_EQUIPO) e.getBolaEquipo().setVelX(-VELOCIDAD_MAX_EQUIPO);
		if(e.getBolaEquipo().getVelY() < -VELOCIDAD_MAX_EQUIPO) e.getBolaEquipo().setVelY(-VELOCIDAD_MAX_EQUIPO);
	}



	
	/** Metodo para saber si un numero es 0, redondeo
	 * @param num	Numero del que se quiere saber si es muy proximo a 0
	 * @return		Devuelve: True-> si es 0 / False-> si no es 0
	 */
	public static boolean igualACero( double num ) {
		return Math.abs(num)<=1E-12;  // 1 * 10^-12
	}
	
}