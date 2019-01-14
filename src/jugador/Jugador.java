package jugador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import entidades.BaseDeDatos;
import entidades.Equipo;
import menuPrincipal.Liga;
import objetos.Pelota;
import ventanas.VentanaLiga;

public class Jugador {
	private int ligasJugadas;
	private int ligasGanadas;
	
	private ArrayList<Pelota> pelotasDesbloqueadas;
	private String nombre;
	private char[] password;
	private int codLiga;
	
	
	
	public void setLigasJugadas(int ligasJugadas) {
		this.ligasJugadas = ligasJugadas;
	}

	public void setLigasGanadas(int ligasGanadas) {
		this.ligasGanadas = ligasGanadas;
	}

	public void setPelotasDesbloqueadas(ArrayList<Pelota> pelotasDesbloqueadas) {
		this.pelotasDesbloqueadas = pelotasDesbloqueadas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public void setCodLiga(int codLiga) {
		this.codLiga = codLiga;
	}

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
	public Jugador(String nombre, char[] password) {
		this.password = password;
		this.nombre = nombre;
		codLiga = 1;
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
	public ArrayList<Equipo> devolverPartidosRestantes(int numLiga, BaseDeDatos bd){
		ArrayList<Equipo> lista = new ArrayList<Equipo>();
		String rutaPart = this.getNombre().toUpperCase() + numLiga + "Partidos.txt";
		File archivoPart = new File(rutaPart);
		 try {
		    	if(archivoPart.exists()) {
		    		BufferedReader br = new BufferedReader(new FileReader(archivoPart));
		    		String cadena = br.readLine();
		    		while(cadena != null) {
		    		lista.add(bd.convertirAEquipo(cadena, this, numLiga));
		    		cadena = br.readLine();
		    		
		    		}
		            br.close();}
		    }catch(Exception i) {
//		    	i.printStackTrace();
		    	}
		 
		 
		return lista;
		 
	}
	


	
}

