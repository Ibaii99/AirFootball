package jugador;

import java.util.ArrayList;

import menuPrincipal.Liga;
import objetos.Pelota;

public class Jugador {
	private int ligasJugadas;
	private int ligasGanadas;
	
	private ArrayList<Pelota> pelotasDesbloqueadas;
	private String Nombre;
	private char[] Password;
	private ArrayList<Liga> ligasEnCurso;
	

	/** El modo Arcade le dejará al usuario desbloquear pelotas nuevas para elegir
	 * @param pelota Pelota nueva desbloqueada
	 */
	public void anyadirPelota(Pelota pelota) {
		pelotasDesbloqueadas.add(pelota);
	}
}
