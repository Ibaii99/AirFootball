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
	
	

}
