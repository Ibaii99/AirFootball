package fisicas;

import java.awt.Point;

import entidades.BaseDeDatos;
import objetos.Pelota;
import ventanas.ventanaPartido;

public class PruebaFisicas {
	public static void main(String[] args) {
		BaseDeDatos b = new BaseDeDatos();
		b.crearTabla();
	}
	public void ChocanEntreSi(Pelota p1, Pelota p2) {
		Point vectorPelota1 = p1.chocaConObjeto(p2);
		Point vectorPelota2 = p2.chocaConObjeto(p1);
		// estas son las direcciones que tienen que tomar cada pelota
		
		p1.setVelocidad(vectorPelota1);
		p2.setVelocidad(vectorPelota2);
		
		
	}
	
	// Primero hay que hacer metodo de calcular si chocan
	// Devuelva la direccion y velocidad con la que rebotan las pelotas
	
	public void chocaEnVentana(ventanaPartido v, Pelota p) {
	//	if(p.)
	}
	// Metodo de rebota en los laterales
	// Devuelve la direccion y velocidad con la que rebota la pelota
	
	// Metodo de que el objeto decelere continuamente por el rozamiento con la superficie
	// Edite la velocidad de la pelota, pero en ambos ejes, y por igual.
	

}
