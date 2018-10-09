package physics;

public class Physics {
	
	public static double GRAVEDAD = 9800.0;  // Pixels por segundo cuadrado
	
	public static double calcVelocidad( double energia, double masa ) {
		return Math.sqrt( 2.0 * energia / masa );
	}
	
	
	
	
	
	
	
}
