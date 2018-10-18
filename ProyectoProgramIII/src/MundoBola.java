import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.Random;
import javax.swing.JOptionPane;

import fisicas.Fisica;
import objetos.Bloque;
import objetos.ObjetoFisico;
import objetos.Pelota;
import ventanas.VentanaGrafica;

public class MundoBola {
	private ObjetoFisico[] objetos;
	private int numObjetos;
	private VentanaGrafica ventana;
	
	private static final int NUM_MAX_BOLAS = 20;
	private static long MILIS_POR_MOVIMIENTO = 16;
	private static long MILIS_ENTRE_MOVTOS = 16;
	private static double VEL_MINIMA_NO_BOTE = -87.2;
	private static double PORC_RESTITUCION_SUELO = 0.85; // Restituci�n de rebote de suelo: 1.0 se bota el 100% - 0.0 no bota nada
	private static double COEF_ROZAMIENTO = 0.05; // 1.0 roza completamente (se para) - 0.0 no roza nada
	
	private static boolean VER_CHOQUES = false;
	private static boolean PAUSA = false;
	
	public MundoBola() {
		objetos = new ObjetoFisico[NUM_MAX_BOLAS];
		ventana = new VentanaGrafica( 1000, 800, "MundoBola v1.0" );
		numObjetos = 0;
	}
	
	public ObjetoFisico[] getObjetos() {
		return objetos;
	}
	
	public VentanaGrafica getVentana() {
		return ventana;
	}
	
	public boolean addObjeto( ObjetoFisico objeto ) {
		if (numObjetos==objetos.length-1) {
			JOptionPane.showMessageDialog( null, "No se pueden a�adir m�s objetos." );
			return false;
		}
		objeto.setNombre( "" + numObjetos );
		objetos[ numObjetos ] = objeto;
		numObjetos++;
		return true;
	}
	

	public static void main(String[] args) {
		crearYBotarMundo();
	}
	
	private static void crearYBotarMundo() {
		MundoBola mundo = new MundoBola();
		mundo.crearMundoTest();
		mundo.moverMundo();
	}
	
		private static Random random = new Random();
		public static int cont = 0;
	
	// Crea objetos de test en el mundo
	private void crearMundoTest() {
		int tipoTest = 4;
		if (tipoTest==1) {  // Ca�da en la misma vertical
			this.addObjeto( new Pelota( 200, 100, 100, 'r', true ) );
			this.addObjeto( new Pelota( 200, 400, 100, 'a', true ) );
		} else if (tipoTest==2) {  // Pelotas rodando por el suelo
			this.addObjeto( new Pelota( 200, 661, 100, 'r', true ) );
			this.addObjeto( new Pelota( 800, 661, 100, 'a', true ) );
			double velX = Fisica.calcVelocidad( 3E12, this.getObjetos()[0].getVolumen() );
			System.out.println( velX );
			this.getObjetos()[0].addVelocidad( new Point2D.Double( velX, 0.0 ) );
		} else if (tipoTest==3) {  // 3 Pelotas con choques ca�ticos
			this.addObjeto( new Pelota( 200, 400, 100, 'r', true ) );
			this.addObjeto( new Pelota( 250, 650, 50,  'a', true ) );
			this.addObjeto( new Pelota( 800, 120, 100, 'v', true ) );
			double velX = Fisica.calcVelocidad( 3E12, this.getObjetos()[1].getVolumen() );
			this.getObjetos()[1].addVelocidadX( velX );
			velX = Fisica.calcVelocidad( 3E12, this.getObjetos()[2].getVolumen() );
			this.getObjetos()[2].addVelocidadX( -velX );
		} else if (tipoTest==4) {  // Pelotas y bloques
			this.addObjeto( new Pelota( 200, 100, 100, 'r', true ) );
			this.addObjeto( new Pelota( 200, 400, 100, 'a', true ) );
			this.addObjeto( new Bloque( 100, 600, 300, 50, 'v', false ));
		}
	}
		
	// Permitiendo interacci�n con el rat�n para crear bolas, o lanzar las bolas en diagonal
	private void moverMundo() {
		VentanaGrafica v = this.getVentana();
		Point primerClick = null;
		Point ultimoClick = null;
		ObjetoFisico objetoClickado = null;
		PAUSA = true;
		ventana.setMensaje( "Pausa ON. Pulsa P para iniciar");
		while (!v.estaCerrada()) {  // hasta que se cierre la ventana
			// 0.- Cambiar par�metros con posibles indicaciones de teclado
			int tecla = ventana.getCodUltimaTeclaTecleada(); 
			if (tecla==KeyEvent.VK_V) {
				ObjetoFisico.DIBUJAR_VELOCIDAD = !ObjetoFisico.DIBUJAR_VELOCIDAD;
				ventana.setMensaje( "Dibujar velocidad " + (ObjetoFisico.DIBUJAR_VELOCIDAD ? "ON" : "OFF") );
			} else if (tecla==KeyEvent.VK_P) {
				PAUSA = !PAUSA;
				ventana.setMensaje( "Pausa " + (PAUSA ? "ON" : "OFF") );
			} else if (tecla==KeyEvent.VK_C) {
				VER_CHOQUES = !VER_CHOQUES;
				ventana.setMensaje( "Dibujar y parar c�lculo en choques " + (VER_CHOQUES ? "ON" : "OFF") );
			} else if (tecla==KeyEvent.VK_PLUS) {
				if (MILIS_POR_MOVIMIENTO<132) {
					MILIS_POR_MOVIMIENTO = MILIS_POR_MOVIMIENTO * 2;
					if (MILIS_POR_MOVIMIENTO >= MILIS_ENTRE_MOVTOS)
						ventana.setMensaje( "Tiempo visualizaci�n x" + (1.0 * MILIS_POR_MOVIMIENTO / MILIS_ENTRE_MOVTOS) );
					else 
						ventana.setMensaje( "Tiempo visualizaci�n /" + (1.0 * MILIS_ENTRE_MOVTOS / MILIS_POR_MOVIMIENTO) );
				}
			} else if (tecla==KeyEvent.VK_MINUS) {
				if (MILIS_POR_MOVIMIENTO>1) {
					MILIS_POR_MOVIMIENTO = MILIS_POR_MOVIMIENTO / 2;
					if (MILIS_POR_MOVIMIENTO >= MILIS_ENTRE_MOVTOS)
						ventana.setMensaje( "Tiempo visualizaci�n x" + (1.0 * MILIS_POR_MOVIMIENTO / MILIS_ENTRE_MOVTOS) );
					else 
						ventana.setMensaje( "Tiempo visualizaci�n /" + (1.0 * MILIS_ENTRE_MOVTOS / MILIS_POR_MOVIMIENTO) );
				}
			}
			// 1.- Chequear posible interacci�n de rat�n
			Point clickRaton = v.getRatonPulsado();
			if (clickRaton==null) {
				if (primerClick!=null && ultimoClick!=null && objetoClickado!=null && !primerClick.equals(ultimoClick)) { // Ha habido un drag sobre un objeto
					// Aplicar fuerza a objeto
					objetoClickado.setVelocidadX( (ultimoClick.x - primerClick.x)*10.0 );
					objetoClickado.setVelocidadY( (ultimoClick.y - primerClick.y)*10.0 );
				} else if (primerClick!=null && ultimoClick!=null && objetoClickado==null && !primerClick.equals(ultimoClick)) {  // Creaci�n de objeto nuevo
					double radio = primerClick.distance( ultimoClick );
					if (radio >= 5) {  // Por debajo de 10 p�xels no se considera
						char color = 'a';
						switch (random.nextInt( 3 )) {
							case 0: {
								color = 'r';
								break;
							}
							case 1: {
								color = 'v';
								break;
							}
						}
						Pelota pelota = new Pelota( primerClick.x, primerClick.y, radio, color, true );
						if (this.addObjeto( pelota ))
							pelota.dibuja( this.getVentana() );
					}
				}
				primerClick = null;
			} else {
				if (primerClick==null) {
					primerClick = clickRaton;
					ultimoClick = null;
					objetoClickado = null;
					for (ObjetoFisico objeto : this.getObjetos()) {
 						if (objeto!=null) {
							if (objeto.contieneA(primerClick)) {
								objetoClickado = objeto;
								break;
							}
						}
					}
				} else {
					ultimoClick = clickRaton;
				}
			}
			// 2.- Hacer movimiento de los objetos en el lapso de tiempo ocurrido
			if (!PAUSA) {
				for (ObjetoFisico objeto : this.getObjetos()) {
					if (objeto != null) {  // Ojo, solo con los objetos que haya!
						// Se mueve el objeto
						boolean enSuelo = (objeto.chocaConBorde(v)>=8 && Fisica.igualACero( objeto.getVelocidadY() ));
						if (enSuelo) {
							objeto.setVelocidadY( 0.0 );
							objeto.mueveUnPocoX( v, MILIS_POR_MOVIMIENTO, false );  // M�todo para movimiento sin gravedad   (sin dibujado)
						} else {
							objeto.mueveUnPoco( v, MILIS_POR_MOVIMIENTO, false );  // M�todo para movimiento con influencia de gravedad   (sin dibujado)
						}
					}
				}
			}
			// 3.- Calcular y corregir choques en el mundo
			if (!PAUSA) {
				boolean hayChoques;
				int numIteraciones = 0;
				do { 
					numIteraciones++;
					hayChoques = false;
					// 3a.- Comprobamos choques con los l�mites de la ventana
					for (ObjetoFisico objeto : this.getObjetos()) {
						if (objeto != null) {  // Ojo, solo con los objetos que haya!
							// Choque lateral
							int choque = objeto.chocaConBorde( v );
							if ((choque & 0b0001) != 0 && objeto.getVelocidadX()<0) { // Choque izquierda
								objeto.rebotaHorizontal( 1.0 );  // Rebota al 100% -sale hacia la derecha-
								objeto.corrigeChoqueLateral( v, true, false );
								hayChoques = true;
							} else if ((choque & 0b0010) != 0 && objeto.getVelocidadX()>0) {  // Choque derecha
								objeto.rebotaHorizontal( 1.0 );  // Rebota al 100% -sale hacia la izquierda-
								objeto.corrigeChoqueLateral( v, false, false );
								hayChoques = true;
							}
							// Choque en suelo
							if (choque>=8 && objeto.getVelocidadY()>0) {
								hayChoques = true;
								objeto.corrigeChoqueInferior( v, false );
								if (objeto.isBota()) {
									objeto.rebotaVertical( PORC_RESTITUCION_SUELO );  // Bota con % de restituci�n
									if (objeto.getVelocidadY() >= VEL_MINIMA_NO_BOTE) {
										// Si va a rebotar pero con una velocidad m�nima, se detiene el bote (umbral de parada)
										objeto.setVelocidadY( 0 );
									}
								} else {
									objeto.setVelocidadY( 0.0 );
								}
							}
						}
					}
					// 3b.- Comprobamos choques entre objetos
					// Probamos todas con todas (salen rebotadas en la direcci�n del choque)
					for (int i=0; i<this.getObjetos().length; i++) {
						ObjetoFisico objeto = this.getObjetos()[i]; 
						if (objeto != null) {
							for (int j=i+1; j<this.getObjetos().length; j++) {
								ObjetoFisico objeto2 = this.getObjetos()[j]; 
								if (objeto2 != null) {
									if (objeto.chocaConObjeto( objeto2 )!=null) {
										Fisica.calcChoqueEntreObjetos(ventana, objeto, objeto2, MILIS_POR_MOVIMIENTO, VER_CHOQUES );
										// Aplica velocidad de choque en funci�n de las masas (el que tiene masa m�s grande se ve menos afectado y viceversa)
										if (VER_CHOQUES) {  // Espera a pulsaci�n de rat�n
											if (ventana.getRatonPulsado()==null) { // Si el rat�n no est� pulsado...
												while (ventana.getRatonPulsado()==null && !ventana.estaCerrada()) {}  // Espera a pulsaci�n...
												while (ventana.getRatonPulsado()!=null && !ventana.estaCerrada()) {}  // ...y suelta
											}
										}
									}
								}
							}
						}
					}
				} while (hayChoques && numIteraciones<=3);
			}
			
			// 4.- Deslizarse por el suelo... con rozamiento 
			// (Si est� rodando en el suelo entonces actualizamos esa velocidad X con el rozamiento)
			if (!PAUSA) {
				for (ObjetoFisico objeto : this.getObjetos()) {
					if (objeto != null) {
						if (Fisica.igualACero(objeto.getAvanceY())) {
							if (VER_CHOQUES) {  // Ver c�mo evoluciona el rozamiento
								System.out.println( "  objeto deslizando: " + objeto.getNombre() + " - vel.X " + objeto.getVelocidadX() );
							}
							if (objeto.chocaConBorde(v)>=8) {
								if (Fisica.igualACero(objeto.getVelocidadX())) {
									objeto.setVelocidadX( 0.0 );
								} else {
									objeto.setVelocidadX( objeto.getVelocidadX() * (1.0 - COEF_ROZAMIENTO) );
									if (Fisica.igualACero(objeto.getVelocidadX())) {  // Si la velocidad es m�nima, se detiene
										objeto.setVelocidadX( 0.0 );
									}
									if (VER_CHOQUES) {  // Ver c�mo evoluciona el rozamiento
										System.out.println( "    corregida velocidad: " + objeto.getVelocidadX() );
									}
								}
							}
						}
					}
				}
			}
			
			// 5.- Dibujado expl�cito de todos los objetos
			this.getVentana().borra();  // Borra todo (nuevo m�todo)
			for (ObjetoFisico objeto : this.getObjetos()) {  // Y dibuja de nuevo todas las bolas
				if (objeto != null) {
					objeto.dibuja( this.getVentana() );
				}
			}
			
			// Feedback visual de interacciones
			if (primerClick!=null && ultimoClick!=null) {
				if (objetoClickado!=null) {  // Se est� queriendo imprimir velocidad a un objeto
					ventana.dibujaFlecha( primerClick.getX(), primerClick.getY(), ultimoClick.getX(), ultimoClick.getY(), 1.0f, Color.orange, 25 );
				} else {  // Se est� queriendo crear un objeto
					ventana.dibujaCirculo( primerClick.getX(), primerClick.getY(), primerClick.distance(ultimoClick), 1.0f, Color.orange );
				}
			}
			this.getVentana().espera( MILIS_ENTRE_MOVTOS );  // 50 veces por segundo (aprox)
		}
	}


}
