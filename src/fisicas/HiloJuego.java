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
	public synchronized void start() {
		fisicas.choquePelotaBorde(v, p);
		fisicas.choquePelotaEquipo(p, eLocal);
		fisicas.choquePelotaEquipo(p, eVisitante);
		fisicas.muevePelota(p, fisicas.TIEMPO, v);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		v.actualizarPosicionObjetos();
		v.actualizarCampo();
		System.out.println("x: "+p.getX()+ "  y: "+ p.getY());	
		System.out.println("velX: "+p.getVelX() + "  velY: "+p.getVelY());
		
		}
	}


