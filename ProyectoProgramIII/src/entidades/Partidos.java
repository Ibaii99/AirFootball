package entidades;

/**
 * @author ibai
 *
 */
public class Partidos {
	
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	
	private int golesLocal;
	private int golesVisitante;
	
	/**	Constructor Standard de un Partido
	 * @param equipoLocal	Equipo Local del Partido
	 * @param equipoVisitante	Equipo Visitante del Partido
	 * @param golesLocal		Goles del Equipo Local
	 * @param golesVisitante	Goles del Equipo Visitante
	 */
	public Partidos(Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante, boolean terminaPorTiempo, boolean terminaPorGoles) {
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesLocal= golesLocal;
		this.golesVisitante = golesVisitante;

		}

	public void calcularGanador() {
		try {
			if(golesLocal < golesVisitante) {
				equipoLocal.
			}
			else if(golesLocal > golesVisitante){
					
			}
			else if(golesLocal == golesVisitante) {
				
			}
		}
		catch(NullPointerException e) {
			
		}
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}
	
	
	
	
	}



