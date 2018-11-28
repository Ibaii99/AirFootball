package jugador;

import java.util.ArrayList;

import menuPrincipal.Liga;
import objetos.Pelota;

public class Jugador {
	private int ligasJugadas;
	private int ligasGanadas;
	
	private ArrayList<Pelota> pelotasDesbloqueadas;
	private String nombre;
	private char[] password;
	private int codLiga = 0;
	
	
	public int getCodLiga() {
		return codLiga;
	}

	public void incCodLiga() {
		codLiga++;
	}

	/** El modo Arcade le dejará al usuario desbloquear pelotas nuevas para elegir
	 * @param pelota Pelota nueva desbloqueada
	 */
	public void anyadirPelota(Pelota pelota) {
		pelotasDesbloqueadas.add(pelota);
	}
	public Jugador(String nombre, char[] password, int numero) {
		this.password = password;
		this.nombre = nombre;
		codLiga = numero;
	}
	public int getLigasJugadas() {
		return ligasJugadas;
	}
	public int getLigasGanadas() {
		return ligasGanadas;
	}
	public ArrayList<Pelota> getPelotasDesbloqueadas() {
		return pelotasDesbloqueadas;
	}
	public String getNombre() {
		return nombre;
	}
	public char[] getPassword() {
		return password;
	}


	
}

