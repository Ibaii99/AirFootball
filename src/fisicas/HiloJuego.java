package fisicas;

import entidades.Equipo;
import objetos.Pelota;
import ventanas.ventanaPartido;

public class HiloJuego extends Thread {
	
	private FisicasNuevas fisicas = new FisicasNuevas();
	private Pelota p;
	private Equipo eLocal;
	private Equipo eVisitante;
	private ventanaPartido v;


	public HiloJuego(Pelota p, Equipo eLocal, Equipo eVisitante, ventanaPartido v) {
		this.p = p;
		this.eLocal = eLocal;
		this.eVisitante = eVisitante;
		this.v = v;
	}
	
	@Override
	public void run() {
	
		fisicas.choquePelotaBorde(v, p);
		fisicas.choquePelotaEquipo(p, eLocal);
		fisicas.choquePelotaEquipo(p, eVisitante);
		fisicas.muevePelota(p, fisicas.TIEMPO, v);
		v.actualizarCampo();
	}
}
