package main;

import objetos.Poste;
import ventanas.ventanaPartido;

public class Juego {
	
	public ventanaPartido v = new ventanaPartido();
	public Poste posteArribaIzquierda = new Poste(v, true, true);
	public Poste posteAbajoIzquierda = new Poste(v, true, false);
	
	public Poste posteArribaDerecha = new Poste(v, false, true);
	public Poste posteAbajoDerecha = new Poste(v, false, false);
	
	
	public static void main(String[] args) {
		
	}
	
//	(*1)diseño recursivo de funcionameinto del juego
//			guardo el tiempo de la consulta anterior
//
//			con la velocidad de la pelota se donde va a ir y con eso llegara un momento que:
//			me de que hay o ha habido un rebote:
//					
// 					divido el tiempo a la mitad y miro si en ese momento tambn seguia ocurriendo choque(*1) 
//		[así hasta ir dividiendo todo el rato el tiempo desde el ultimo momento calculado con su tiempo hasta el inicial]
//					
//					cuando no hay choque se que el momento anterior es en el que ha habido el choque y devuelvo ese momento
//					con el tiempo tendre la posicion, vel*tiempo = espacio espacio+= espacio del objeto = posicion futura
//	

}
