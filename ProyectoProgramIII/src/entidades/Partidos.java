package entidades;

/**
 * @author ibai
 *
 */
public class Partidos {
	
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	
	private Equipo equipoGanador;
	
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

	
	
	
	/** Metodo de añadir los datos del partido a las estadisitcas de los equipos.
	 * 
	 */
	public void anyadirDatosAEquipos() {
		if(golesLocal < golesVisitante) {
			equipoLocal.anyadirPuntos(0);
			equipoVisitante.anyadirPuntos(3);
			equipoLocal.anyadirEstadisticasLocal(false, false, golesLocal, golesVisitante);
			equipoVisitante.anyadirEstadisticasVisitante(true, false, golesVisitante, golesLocal);
		}
		else if(golesLocal > golesVisitante){
			equipoLocal.anyadirPuntos(3);
			equipoVisitante.anyadirPuntos(0);
			equipoLocal.anyadirEstadisticasLocal(true, false, golesLocal, golesVisitante);
			equipoVisitante.anyadirEstadisticasVisitante(false, false, golesVisitante, golesLocal);
		}
		else if(golesLocal == golesVisitante) {
			equipoLocal.anyadirPuntos(1);
			equipoVisitante.anyadirPuntos(1);
			equipoLocal.anyadirEstadisticasLocal(false, true, golesLocal, golesVisitante);
			equipoVisitante.anyadirEstadisticasVisitante(false, true, golesVisitante, golesLocal);
		}
		
		
	}
	
	
	/** Metodo que calcula el equipo ganador y lo agrega al atributo
	 * 	Deja el equipo ganador como null si hay empate
	 */
	public void calcularGanador() {
		try {
			if(golesLocal < golesVisitante) {
				equipoLocal.anyadirPuntos(0);
				equipoVisitante.anyadirPuntos(3);
				equipoGanador = equipoVisitante;
			}
			else if(golesLocal > golesVisitante){
				equipoLocal.anyadirPuntos(3);
				equipoVisitante.anyadirPuntos(0);
				equipoGanador = equipoLocal;
			}
			else if(golesLocal == golesVisitante) {
				equipoLocal.anyadirPuntos(1);
				equipoVisitante.anyadirPuntos(1);
				equipoGanador = null;
			}
		}
		catch(NullPointerException e) {
			System.out.println("Hay error en Calcular ganador, los goles son null");
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



