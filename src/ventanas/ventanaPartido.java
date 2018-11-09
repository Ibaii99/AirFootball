package ventanas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Color;

public class ventanaPartido extends JFrame {
	/** @author Jorge 
	 * 
	 */
	private Graphics2D graphics;  // Objeto gr�fico sobre el que dibujar (del buffer)
	private JPanel panel;         // Panel principal
	
	public ventanaPartido() {
		setSize(750, 500);
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(null);
		ImageIcon iconL = new ImageIcon(ventanaPartido.class.getResource("/iconos/equipos/atl.png"));
		Image imgL = iconL.getImage();
		BufferedImage biL = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
		Graphics gL = biL.createGraphics();
		gL.drawImage(imgL, 0, 0, 40, 40, null);
		ImageIcon newIconL = new ImageIcon(biL);
		
		ImageIcon iconC = new ImageIcon(ventanaPartido.class.getResource("/iconos/campo.png"));
		Image imgC = iconC.getImage();
		BufferedImage biC = new BufferedImage(720, 395, BufferedImage.TYPE_INT_ARGB);
		Graphics gC = biC.createGraphics();
		gC.drawImage(imgC, 0, 0, 720, 395, null);
		ImageIcon newIconC = new ImageIcon(biC);

		ImageIcon iconV = new ImageIcon(ventanaPartido.class.getResource("/iconos/equipos/ath.png"));
		Image imgV = iconV.getImage();
		BufferedImage biV = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
		Graphics gV = biV.createGraphics();
		gV.drawImage(imgV, 0, 0, 40, 40, null);
		ImageIcon newIconV = new ImageIcon(biV);

		JLabel lblEscudoL = new JLabel("");
		lblEscudoL.setBounds(164, 13, 40, 40);
		lblEscudoL.setIcon(newIconL);
		getContentPane().add(lblEscudoL);
		JLabel lblEscudoV = new JLabel("");
		lblEscudoV.setBounds(532, 13, 40, 40);
		lblEscudoV.setIcon(newIconV);
		getContentPane().add(lblEscudoV);

		JPanel panel = new JPanel();
		panel.setBounds(182, 13, 373, 40);
		getContentPane().add(panel);
		ImageIcon icon = new ImageIcon(ventanaPartido.class.getResource("/iconos/marcadorconnombres.jpg"));
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(373, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 373, 50, null);
		ImageIcon newIcon = new ImageIcon(bi);
		panel.setLayout(null);

		JLabel label_2 = new JLabel("0");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("DSEG14 Classic", Font.PLAIN, 28));
		label_2.setBounds(153, 5, 23, 32);
		panel.add(label_2);

		JLabel label_1 = new JLabel("0");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("DSEG14 Classic", Font.PLAIN, 28));
		label_1.setBounds(190, 5, 23, 32);
		panel.add(label_1);

		JLabel label = new JLabel("");
		label.setIcon(newIcon);
		label.setBounds(0, 0, 373, 51);
		panel.add(label);
		
		JLabel lCampo = new JLabel("");
		lCampo.setIcon(newIconC);
		lCampo.setBounds(12, 57, 720, 395);
		getContentPane().add(lCampo);

	}

	boolean amistoso;

	public boolean isAmistoso() {
		return amistoso;
	}

	public void setAmistoso(boolean amistoso) {
		this.amistoso = amistoso;
	}

	public boolean isLiga() {
		return liga;
	}

	public void setLiga(boolean liga) {
		this.liga = liga;
	}

	public boolean isArcade() {
		return arcade;
	}

	public void setArcade(boolean arcade) {
		this.arcade = arcade;
	}

	boolean liga;
	boolean arcade;


	
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
