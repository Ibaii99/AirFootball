package ventanas;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/** Clase ventana sencilla para dibujado
 */
public class VentanaGrafica {
	private JFrame ventana;       // Ventana que se visualiza
	private boolean cerrada;      // L�gica de cierre (false al inicio)
	private JPanel panel;         // Panel principal
	private JLabel lMens;         // Etiqueta de texto de mensajes en la parte inferior
	private BufferedImage buffer; // Buffer gr�fico de la ventana
	private Graphics2D graphics;  // Objeto gr�fico sobre el que dibujar (del buffer)
	private Point pointPressed;   // Coordenada pulsada de rat�n (si existe)

		private Object lock = new Object();  // Tema de sincronizaci�n de hilos para el acceso como si no los hubiera
	
	/** Construye una nueva ventana gr�fica con fondo blanco y la visualiza en el centro de la pantalla
	 * @param anchura	Anchura en p�xels (valor positivo)
	 * @param altura	Altura en p�xels (valor positivo)
	 * @param titulo	T�tulo de la ventana
	 */
	@SuppressWarnings("serial")
	public VentanaGrafica( int anchura, int altura, String titulo ) {
		cerrada = false;
		ventana = new JFrame( titulo );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		ventana.setSize( anchura, altura );
		ventana.setLocationRelativeTo( null );
		buffer = new BufferedImage( 2000, 1500, BufferedImage.TYPE_INT_ARGB );
		graphics = buffer.createGraphics();
		graphics.setPaint ( Color.white );
		graphics.fillRect ( 0, 0, 2000, 1500 );
		panel = new JPanel() {
			{
				setLayout( new BorderLayout() );
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				((Graphics2D)g).drawImage( buffer, null, 0, 0 );
			}
		};
		lMens = new JLabel( " " );
		ventana.getContentPane().add( panel, BorderLayout.CENTER );
		ventana.getContentPane().add( lMens, BorderLayout.SOUTH );
		ventana.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrada = true;
			}
		});
		panel.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				synchronized (lock) {
					pointPressed = null;
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				synchronized (lock) {
					pointPressed = e.getPoint();
				}
			}
		});
		panel.addMouseMotionListener( new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				synchronized (lock) {
					pointPressed = e.getPoint();
				}
			}
		});
		ventana.setVisible( true );
	}
	/** Espera un tiempo y sigue
	 * @param milis	Milisegundos a esperar
	 */
	public void espera( long milis ) {
		try {
			Thread.sleep( milis );
		} catch (InterruptedException e) {
		}
	}
	/** Cierra la ventana (tambi�n ocurre cuando se pulsa el icono de cierre)
	 */
	public void acaba() {
		ventana.dispose();
		cerrada = true;
	}
	
	/** Consultor de estado de visibilidad de la ventana
	 * @return	false si sigue activa, true si ya se ha cerrado
	 */
	public boolean estaCerrada() {
		return cerrada;
	}
	
	/** Devuelve el punto donde est� el rat�n pulsado en este momento
	 * @return	Punto relativo a la ventana, null si el rat�n no est� siendo pulsado
	 */
	public Point getRatonPulsado() {
		synchronized (lock) {
			return pointPressed;
		}
	}
	
	/** Cambia el mensaje de la ventana (l�nea inferior de mensajes)
	 * @param mensaje	Texto de mensaje
	 */
	public void setMensaje( String mensaje ) {
		if (mensaje==null || mensaje.isEmpty())
			lMens.setText( " " );
		else
			lMens.setText( mensaje );
	}
	
	/** Devuelve la altura del panel de dibujo de la ventana
	 * @return	Altura del panel principal (�ltima coordenada y) en p�xels
	 */
	public int getAltura() {
		return panel.getHeight()-1;
	}
	
	/** Devuelve la anchura del panel de dibujo de la ventana
	 * @return	Anchura del panel principal (�ltima coordenada x) en p�xels
	 */
	public int getAnchura() {
		return panel.getWidth()-1;
	}
	
	/** Borra toda la ventana (pinta de color blanco)
	 */
	public void borra() {
		graphics.setColor( Color.white );
		graphics.fillRect( 0, 0, panel.getWidth()+2, panel.getHeight()+2 );
		panel.repaint();
	}
	
	/** Dibuja un rect�ngulo en la ventana
	 * @param rect�ngulo a dibujar
	 * @param grosor	Grueso de la l�nea del rect�ngulo (en p�xels)
	 * @param color  	Color del rect�ngulo
	 */
	public void dibujaRect( Rectangle rectangulo, float grosor, Color color ) {
		dibujaRect( rectangulo.getX(), rectangulo.getY(), rectangulo.getX()+rectangulo.getWidth(), rectangulo.getY()+rectangulo.getHeight(), grosor, color );
	}
	
	/** Dibuja un rect�ngulo en la ventana
	 * @param x	Coordenada x de la esquina superior izquierda del rect�ngulo
	 * @param y	Coordenada y de la esquina superior izquierda del rect�ngulo
	 * @param anchura	Anchura del rect�ngulo (en p�xels) 
	 * @param altura	Altura del rect�ngulo (en p�xels)
	 * @param grosor	Grueso del rect�ngulo (en p�xels)
	 * @param color  	Color del rect�ngulo
	 */
	public void dibujaRect( double x, double y, double anchura, double altura, float grosor, Color color ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		graphics.drawRect( (int)Math.round(x), (int)Math.round(y), (int)Math.round(anchura), (int)Math.round(altura) );
		panel.repaint();
	}
	
	/** Dibuja un rect�ngulo azul en la ventana
	 * @param x	Coordenada x de la esquina superior izquierda del rect�ngulo
	 * @param y	Coordenada y de la esquina superior izquierda del rect�ngulo
	 * @param anchura	Anchura del rect�ngulo (en p�xels) 
	 * @param altura	Altura del rect�ngulo (en p�xels)
	 * @param grosor	Grueso del rect�ngulo (en p�xels)
	 */
	public void dibujaRect( double x, double y, double anchura, double altura, float grosor ) {
		dibujaRect( x, y, anchura, altura, grosor, Color.blue );
	}
	
	/** Borra un rect�ngulo en la ventana
	 * @param x	Coordenada x de la esquina superior izquierda del rect�ngulo
	 * @param y	Coordenada y de la esquina superior izquierda del rect�ngulo
	 * @param anchura	Anchura del rect�ngulo (en p�xels) 
	 * @param altura	Altura del rect�ngulo (en p�xels)
	 * @param grosor	Grueso del rect�ngulo (en p�xels)
	 */
	public void borraRect( double x, double y, double anchura, double altura, float grosor ) {
		dibujaRect( x, y, anchura, altura, grosor, Color.white );
	}
	
	/** Dibuja un c�rculo en la ventana
	 * @param x	Coordenada x del centro del c�rculo
	 * @param y	Coordenada y del centro del c�rculo
	 * @param radio	Radio del c�rculo (en p�xels) 
	 * @param grosor	Grueso del c�rculo (en p�xels)
	 * @param color  	Color del c�rculo
	 */
	public void dibujaCirculo( double x, double y, double radio, float grosor, Color color ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		graphics.drawOval( (int)Math.round(x-radio), (int)Math.round(y-radio), (int)Math.round(radio*2), (int)Math.round(radio*2) );
		panel.repaint();
	}
	
	/** Dibuja un c�rculo azul en la ventana
	 * @param x	Coordenada x del centro del c�rculo
	 * @param y	Coordenada y del centro del c�rculo
	 * @param radio	Radio del c�rculo (en p�xels) 
	 * @param grosor	Grueso del c�rculo (en p�xels)
	 */
	public void dibujaCirculo( double x, double y, double radio, float grosor ) {
		dibujaCirculo( x, y, radio, grosor, Color.blue );
	}
	
	/** Borra un c�rculo en la ventana
	 * @param x	Coordenada x del centro del c�rculo
	 * @param y	Coordenada y del centro del c�rculo
	 * @param radio	Radio del c�rculo (en p�xels) 
	 * @param grosor	Grueso del c�rculo (en p�xels)
	 */
	public void borraCirculo( double x, double y, double radio, float grosor ) {
		dibujaCirculo( x, y, radio, grosor, Color.white );
	}
	
	/** Dibuja una l�nea en la ventana
	 * @param linea	a dibujar
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 * @param color  	Color de la l�nea
	 */
	public void dibujaLinea( Line2D linea, float grosor, Color color ) {
		dibujaLinea( linea.getX1(), linea.getY1(), linea.getX2(), linea.getY2(), grosor, color );
	}
	
	/** Dibuja una l�nea en la ventana
	 * @param x	Coordenada x de un punto de la l�nea 
	 * @param y	Coordenada y de un punto de la l�nea
	 * @param x2	Coordenada x del segundo punto de la l�nea 
	 * @param y2	Coordenada y del segundo punto de la l�nea
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 * @param color  	Color de la l�nea
	 */
	public void dibujaLinea( double x, double y, double x2, double y2, float grosor, Color color ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		graphics.drawLine( (int)Math.round(x), (int)Math.round(y), (int)Math.round(x2), (int)Math.round(y2) );
		panel.repaint();
	}
	
	/** Dibuja una l�nea azul en la ventana
	 * @param x	Coordenada x de un punto de la l�nea 
	 * @param y	Coordenada y de un punto de la l�nea
	 * @param x2	Coordenada x del segundo punto de la l�nea 
	 * @param y2	Coordenada y del segundo punto de la l�nea
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 */
	public void dibujaLinea( double x, double y, double x2, double y2, float grosor ) {
		dibujaLinea( x, y, x2, y2, grosor, Color.blue );
	}
	
	/** Borra una l�nea en la ventana
	 * @param x	Coordenada x de un punto de la l�nea 
	 * @param y	Coordenada y de un punto de la l�nea
	 * @param x2	Coordenada x del segundo punto de la l�nea 
	 * @param y2	Coordenada y del segundo punto de la l�nea
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 */
	public void borraLinea( double x, double y, double x2, double y2, float grosor ) {
		dibujaLinea( x, y, x2, y2, grosor, Color.white );
	}

	/** Dibuja una flecha en la ventana
	 * @param linea	a dibujar (el segundo punto es la punta de la flecha)
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 * @param color  	Color de la l�nea
	 */
	public void dibujaFlecha( Line2D linea, float grosor, Color color ) {
		dibujaFlecha( linea.getX1(), linea.getY1(), linea.getX2(), linea.getY2(), grosor, color );
	}
	
	/** Dibuja una flecha en la ventana
	 * @param x	Coordenada x de un punto de la l�nea 
	 * @param y	Coordenada y de un punto de la l�nea
	 * @param x2	Coordenada x del segundo punto de la l�nea (el de la flecha)
	 * @param y2	Coordenada y del segundo punto de la l�nea (el de la flecha)
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 * @param color  	Color de la l�nea
	 */
	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor, Color color ) {
		dibujaFlecha( x, y, x2, y2, grosor, color, 10 );
	}
	
	/** Dibuja una flecha en la ventana
	 * @param x	Coordenada x de un punto de la l�nea 
	 * @param y	Coordenada y de un punto de la l�nea
	 * @param x2	Coordenada x del segundo punto de la l�nea (el de la flecha)
	 * @param y2	Coordenada y del segundo punto de la l�nea (el de la flecha)
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 * @param color  	Color de la l�nea
	 * @param largoFl	Pixels de largo de la flecha
	 */
	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor, Color color, int largoFl ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		graphics.drawLine( (int)Math.round(x), (int)Math.round(y), (int)Math.round(x2), (int)Math.round(y2) );
		double angulo = Math.atan2( y2-y, x2-x ) + Math.PI;
		double angulo1 = angulo - Math.PI / 10;  // La flecha se forma rotando 1/10 de Pi hacia los dos lados
		double angulo2 = angulo + Math.PI / 10;
		graphics.drawLine( (int)Math.round(x2), (int)Math.round(y2), 
				(int)Math.round(x2+largoFl*Math.cos(angulo1)), (int)Math.round(y2+largoFl*Math.sin(angulo1)) );
		graphics.drawLine( (int)Math.round(x2), (int)Math.round(y2), 
				(int)Math.round(x2+largoFl*Math.cos(angulo2)), (int)Math.round(y2+largoFl*Math.sin(angulo2)) );
		panel.repaint();
	}
	
	/** Dibuja una l�nea azul en la ventana
	 * @param x	Coordenada x de un punto de la l�nea 
	 * @param y	Coordenada y de un punto de la l�nea
	 * @param x2	Coordenada x del segundo punto de la l�nea (el de la flecha)
	 * @param y2	Coordenada y del segundo punto de la l�nea (el de la flecha)
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 */
	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor ) {
		dibujaFlecha( x, y, x2, y2, grosor, Color.blue );
	}
	
	/** Borra una l�nea en la ventana
	 * @param x	Coordenada x de un punto de la l�nea 
	 * @param y	Coordenada y de un punto de la l�nea
	 * @param x2	Coordenada x del segundo punto de la l�nea (el de la flecha)
	 * @param y2	Coordenada y del segundo punto de la l�nea (el de la flecha)
	 * @param grosor	Grueso de la l�nea (en p�xels)
	 */
	public void borraFlecha( double x, double y, double x2, double y2, float grosor ) {
		dibujaFlecha( x, y, x2, y2, grosor, Color.white );
	}

	/** Devuelve el objeto de gr�fico sobre el que pintar, correspondiente al 
	 * panel principal de la ventana
	 * @return	Objeto gr�fico principal de la ventana
	 */
	public Graphics2D getGraphics() {
		return graphics;
	}
	

	// M�todos est�ticos
		private static int codTeclaTecleada = 0;
		private static int codTeclaActualmentePulsada = 0;
		private static boolean controlActivo = false;
	// Inicializa el control de teclado
	private static void init() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher( new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					codTeclaActualmentePulsada = e.getKeyCode();
					if (e.getKeyCode() == KeyEvent.VK_CONTROL) controlActivo = true; 
				} else if (e.getID() == KeyEvent.KEY_RELEASED) {
					if (e.getKeyCode() == KeyEvent.VK_CONTROL) controlActivo = false; 
					codTeclaTecleada = e.getKeyCode();
					codTeclaActualmentePulsada = 0;
				} else if (e.getID() == KeyEvent.KEY_TYPED) {
				}
				return false;   // false = enviar el evento al comp
			} } );
	}
	static {  // Inicializar en la carta de la clase
		init();
	}

	/** Indica si la tecla Ctrl est� siendo pulsada en este momento
	 * @return	true si est� pulsada, false en caso contrario
	 */
	public boolean isControlPulsado() {
		return controlActivo;
	}
	
	/** Devuelve el c�digo de la tecla pulsada actualmente
	 * @return	c�digo de tecla pulsada, 0 si no hay ninguna. Si hay varias, se devuelve la �ltima que se puls�.<br/>
	 * 			Si se pulsan varias a la vez, tras soltar la primera este m�todo devuelve 0.
	 */
	public int getCodTeclaQueEstaPulsada() {
		return codTeclaActualmentePulsada;
	}
	
	/** Devuelve el c�digo de la �ltima tecla tecleada (pulsada y soltada). Tras eso, borra la tecla (solo se devuelve una vez)
	 * @return	C�digo de la �ltima tecla tecleada. Si no ha sido tecleada ninguna o ya se ha consultado, se devuelve 0.
	 */
	public int getCodUltimaTeclaTecleada() {
		int ret = codTeclaTecleada;
		codTeclaTecleada = 0;
		return ret;
	}
	
}
