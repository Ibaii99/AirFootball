package ventanas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.Equipo;
import objetos.Pelota;
import java.awt.event.KeyAdapter;

/** @author Jorge 
 *  @author ibai
 */
public class ventanaPartido extends JFrame {

	private boolean liga;
	private boolean arcade;
	private Graphics2D graphics;  // Objeto gr�fico sobre el que dibujar (del buffer)
	private JPanel panelCampo;         // Panel principal

	private JLabel lblEquipoLocal=new JLabel("");
	private JLabel lblEquipoVisitante= new JLabel("");
	private JLabel lblPelota = new JLabel("");
	private boolean isMultijugador;
	private boolean isAmistoso;
	private boolean isJugadorEquipoLocal;
	private static double VELOCIDAD_CON_MOVIMIENTO = 10;
	
	private Equipo eLocal;
	private Equipo eVisitante;
	private Pelota p;
	// modificar constructor ventana, pone pelota en posicion no correcta
	public ventanaPartido(Equipo eLocal, Equipo eVisitante, Pelota p, boolean esMultijjugador, boolean esAmistoso, boolean esJugadorVSMaquinaEquipoLocal) {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.isJugadorEquipoLocal = esJugadorVSMaquinaEquipoLocal;
		this.isMultijugador = esMultijjugador;
		this.isAmistoso = esAmistoso;
		this.eLocal = eLocal;
		this.eVisitante = eVisitante;
		this.p = p;
;
		setSize(750, 500);
		setResizable(true);
		setVisible(true);
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
		ImageIcon icon = new ImageIcon(ventanaPartido.class.getResource("/iconos/marcadorconnombres.jpg"));
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(373, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 373, 50, null);
		ImageIcon newIcon = new ImageIcon(bi);
		
		panelCampo= new JPanel();
		panelCampo.setBackground(Color.GREEN);
		panelCampo.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel("0");
		label.setForeground(Color.RED);
		label.setFont(new Font("Dialog", Font.PLAIN, 28));
		label.setBounds(346, 5, 75, 36);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(12, 5, 40, 40);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(686, 5, 40, 40);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("0");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 28));
		label_3.setBounds(217, 5, 75, 36);
		panel.add(label_3);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelCampo, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 730, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelCampo, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		


//		Metodo original con doubles		
//		lblEquipoLocal.setBounds(eLocal.getBolaEquipo().getX(), eLocal.getBolaEquipo().getY(), eLocal.getBolaEquipo().getRadio()*2, eLocal.getBolaEquipo().getRadio()*2);
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int tecla = e.getKeyCode();
				
				if(!isMultijugador) {
					Equipo jugador = null;
					
					if(isJugadorEquipoLocal)jugador = eLocal;
					if(!isJugadorEquipoLocal) jugador = eVisitante;
					
					switch (tecla){
					case 37: jugador.getBolaEquipo().addVelocidadX(-VELOCIDAD_CON_MOVIMIENTO); // flecha izquierda
					case 38: jugador.getBolaEquipo().addVelocidadY(VELOCIDAD_CON_MOVIMIENTO);	// flecha arriba
					case 39: jugador.getBolaEquipo().addVelocidadX(VELOCIDAD_CON_MOVIMIENTO);	// flecha derecha
					case 40: jugador.getBolaEquipo().addVelocidadY(-VELOCIDAD_CON_MOVIMIENTO);	// flecha abajo
					default: ; }
				}
				
				else if(isMultijugador) {
					switch (tecla){
					case 37: eLocal.getBolaEquipo().addVelocidadX(-VELOCIDAD_CON_MOVIMIENTO); // flecha izquierda
					case 38: eLocal.getBolaEquipo().addVelocidadY(VELOCIDAD_CON_MOVIMIENTO);	// flecha arriba
					case 39: eLocal.getBolaEquipo().addVelocidadX(VELOCIDAD_CON_MOVIMIENTO);	// flecha derecha
					case 40: eLocal.getBolaEquipo().addVelocidadY(-VELOCIDAD_CON_MOVIMIENTO);	// flecha abajo
					
					case 87: eVisitante.getBolaEquipo().addVelocidadY(VELOCIDAD_CON_MOVIMIENTO);// w
					case 83: eVisitante.getBolaEquipo().addVelocidadY(-VELOCIDAD_CON_MOVIMIENTO);//s
					case 68: eVisitante.getBolaEquipo().addVelocidadX(VELOCIDAD_CON_MOVIMIENTO);//d
					case 65: eVisitante.getBolaEquipo().addVelocidadX(-VELOCIDAD_CON_MOVIMIENTO);//a			
					default: ;
					}
					
				
				}
				
			}
		});
	}

	/**	Metodo para actualizar las posiciones de los objetos del campo
	 * @param eLocal		Equipo local
	 * @param eVisitante	Equipo Visitante
	 * @param p				Pelota con la que se juega
	 */
	public void actualizarPosicionObjetos(Pelota p, Equipo eLocal, Equipo eVisitante) {
		
		// añado todo al panel con las posiciones actualizadas
		lblEquipoLocal.setBounds((int)eLocal.getBolaEquipo().getX(), (int)eLocal.getBolaEquipo().getY(), (int)eLocal.getBolaEquipo().getRadio()*2, (int)eLocal.getBolaEquipo().getRadio()*2);
		panelCampo.add(lblEquipoLocal);
		lblEquipoLocal.setText("EQUIPO LOCAL");
		lblEquipoLocal.setSize((int)eLocal.getBolaEquipo().getRadio()*2, (int)eLocal.getBolaEquipo().getRadio()*2);
		lblEquipoLocal.setVisible(true);
		
		lblEquipoVisitante.setBounds((int)eVisitante.getBolaEquipo().getX(), (int)eVisitante.getBolaEquipo().getY(), (int)eVisitante.getBolaEquipo().getRadio()*2, (int)eVisitante.getBolaEquipo().getRadio()*2);
		panelCampo.add(lblEquipoVisitante);
		lblEquipoVisitante.setText("EQUIPOVISITANTE");
		lblEquipoVisitante.setSize((int)eVisitante.getBolaEquipo().getRadio()*2, (int)eVisitante.getBolaEquipo().getRadio()*2);
		lblEquipoVisitante.setVisible(true);
		
		lblPelota.setBounds((int)p.getX(), (int)p.getY(), (int)p.getRadio()*2, (int)p.getRadio()*2);
		panelCampo.add(lblPelota);
		lblPelota.setText("PELOTA");
		lblPelota.setVisible(true);
		lblPelota.setSize((int)p.getRadio()*2, (int)p.getRadio()*2);
		
		panelCampo.repaint();
		panelCampo.revalidate();
	
	}
	
	/** Metodo que pinta cada label con el color correspondiente
	 * 	o imagen correspondiente
	 * @param eLocal	Equio local
	 * @param eVisitante	Equipo Visitante
	 * @param p			Pelota
	 */
	public void pintarLabels(Pelota p, Equipo eLocal, Equipo eVisitante) {
		lblEquipoLocal.setOpaque(true);
		lblEquipoVisitante.setOpaque(true);
		lblPelota.setOpaque(true);
		if (eLocal.getBolaEquipo().getImagenObjeto() == null) lblEquipoLocal.setBackground(eLocal.getBolaEquipo().getColor());
		if (eVisitante.getBolaEquipo().getImagenObjeto() == null) lblEquipoVisitante.setBackground(eVisitante.getBolaEquipo().getColor());
		if (p.getImagenObjeto()== null) lblPelota.setBackground(p.getColor());
		
		if (eLocal.getBolaEquipo().getImagenObjeto() != null) lblEquipoLocal.setIcon(eLocal.getBolaEquipo().getImagenObjeto());
		if (eVisitante.getBolaEquipo().getImagenObjeto() != null) lblEquipoVisitante.setIcon(eVisitante.getBolaEquipo().getImagenObjeto());
		if (p.getImagenObjeto()!= null) lblPelota.setIcon(p.getImagenObjeto());
		
	}
	
	public Graphics2D getGraphics() {
		return graphics;
	}

	public JPanel getPanelCampo() {
		return panelCampo;
	}

	public JLabel getLblEquipoLocal() {
		return lblEquipoLocal;
	}

	public JLabel getLblEquipoVisitante() {
		return lblEquipoVisitante;
	}

	public JLabel getLblPelota() {
		return lblPelota;
	}

	public boolean isMultijugador() {
		return isMultijugador;
	}

	public boolean isJugadorEquipoLocal() {
		return isJugadorEquipoLocal;
	}

	public static double getVELOCIDAD_CON_MOVIMIENTO() {
		return VELOCIDAD_CON_MOVIMIENTO;
	}

	public Equipo geteLocal() {
		return eLocal;
	}

	public Equipo geteVisitante() {
		return eVisitante;
	}

	public Pelota getP() {
		return p;
	}

	public void moverEquipo() {
		
	}

	public void colocarEnPosInicial(Pelota p, Equipo eLocal, Equipo eVisitante) {
		p.setX((int)getPanelCampo().getSize().getWidth()/2);
		p.setY((int)getPanelCampo().getSize().getHeight()/2);
		
		//TODO editar esto, no son los objetos son los labels
		
		eLocal.getBolaEquipo().setX(getPanelCampo().getSize().getWidth() - getPanelCampo().getSize().getWidth()/4);
		eVisitante.getBolaEquipo().setX(getPanelCampo().getSize().getWidth()/4);
		
		eLocal.getBolaEquipo().setY(getPanelCampo().getSize().getHeight()/2);
		eVisitante.getBolaEquipo().setY(getPanelCampo().getSize().getHeight()/2);
		
	}
	
	public boolean isAmistoso() {
		return isAmistoso;
	}

	public void setAmistoso(boolean amistoso) {
		this.isAmistoso = amistoso;
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
	
	



	
//	/** Dibuja un c�rculo en la ventana
//	 * @param x	Coordenada x del centro del c�rculo
//	 * @param y	Coordenada y del centro del c�rculo
//	 * @param radio	Radio del c�rculo (en p�xels) 
//	 * @param grosor	Grueso del c�rculo (en p�xels)
//	 * @param color  	Color del c�rculo
//	 */
//	public void dibujaCirculo( double x, double y, double radio, float grosor, Color color ) {
//		graphics.setColor( color );
//		graphics.setStroke( new BasicStroke( grosor ));
//		graphics.drawOval( (int)Math.round(x-radio), (int)Math.round(y-radio), (int)Math.round(radio*2), (int)Math.round(radio*2) );
//		panel.repaint();
//	}
//	
//	/** Dibuja un c�rculo azul en la ventana
//	 * @param x	Coordenada x del centro del c�rculo
//	 * @param y	Coordenada y del centro del c�rculo
//	 * @param radio	Radio del c�rculo (en p�xels) 
//	 * @param grosor	Grueso del c�rculo (en p�xels)
//	 */
//	public void dibujaCirculo( double x, double y, double radio, float grosor ) {
//		dibujaCirculo( x, y, radio, grosor, Color.blue );
//	}
//	
//	/** Borra un c�rculo en la ventana
//	 * @param x	Coordenada x del centro del c�rculo
//	 * @param y	Coordenada y del centro del c�rculo
//	 * @param radio	Radio del c�rculo (en p�xels) 
//	 * @param grosor	Grueso del c�rculo (en p�xels)
//	 */
//	public void borraCirculo( double x, double y, double radio, float grosor ) {
//		dibujaCirculo( x, y, radio, grosor, Color.white );
//	}
//	
//	/** Dibuja una l�nea en la ventana
//	 * @param linea	a dibujar
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 * @param color  	Color de la l�nea
//	 */
//	public void dibujaLinea( Line2D linea, float grosor, Color color ) {
//		dibujaLinea( linea.getX1(), linea.getY1(), linea.getX2(), linea.getY2(), grosor, color );
//	}
//	
//	/** Dibuja una l�nea en la ventana
//	 * @param x	Coordenada x de un punto de la l�nea 
//	 * @param y	Coordenada y de un punto de la l�nea
//	 * @param x2	Coordenada x del segundo punto de la l�nea 
//	 * @param y2	Coordenada y del segundo punto de la l�nea
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 * @param color  	Color de la l�nea
//	 */
//	public void dibujaLinea( double x, double y, double x2, double y2, float grosor, Color color ) {
//		graphics.setColor( color );
//		graphics.setStroke( new BasicStroke( grosor ));
//		graphics.drawLine( (int)Math.round(x), (int)Math.round(y), (int)Math.round(x2), (int)Math.round(y2) );
//		panel.repaint();
//	}
//	
//	/** Dibuja una l�nea azul en la ventana
//	 * @param x	Coordenada x de un punto de la l�nea 
//	 * @param y	Coordenada y de un punto de la l�nea
//	 * @param x2	Coordenada x del segundo punto de la l�nea 
//	 * @param y2	Coordenada y del segundo punto de la l�nea
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 */
//	public void dibujaLinea( double x, double y, double x2, double y2, float grosor ) {
//		dibujaLinea( x, y, x2, y2, grosor, Color.blue );
//	}
//	
//	/** Borra una l�nea en la ventana
//	 * @param x	Coordenada x de un punto de la l�nea 
//	 * @param y	Coordenada y de un punto de la l�nea
//	 * @param x2	Coordenada x del segundo punto de la l�nea 
//	 * @param y2	Coordenada y del segundo punto de la l�nea
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 */
//	public void borraLinea( double x, double y, double x2, double y2, float grosor ) {
//		dibujaLinea( x, y, x2, y2, grosor, Color.white );
//	}
//
//	/** Dibuja una flecha en la ventana
//	 * @param linea	a dibujar (el segundo punto es la punta de la flecha)
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 * @param color  	Color de la l�nea
//	 */
//	public void dibujaFlecha( Line2D linea, float grosor, Color color ) {
//		dibujaFlecha( linea.getX1(), linea.getY1(), linea.getX2(), linea.getY2(), grosor, color );
//	}
//	
//	/** Dibuja una flecha en la ventana
//	 * @param x	Coordenada x de un punto de la l�nea 
//	 * @param y	Coordenada y de un punto de la l�nea
//	 * @param x2	Coordenada x del segundo punto de la l�nea (el de la flecha)
//	 * @param y2	Coordenada y del segundo punto de la l�nea (el de la flecha)
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 * @param color  	Color de la l�nea
//	 */
//	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor, Color color ) {
//		dibujaFlecha( x, y, x2, y2, grosor, color, 10 );
//	}
//	
//	/** Dibuja una flecha en la ventana
//	 * @param x	Coordenada x de un punto de la l�nea 
//	 * @param y	Coordenada y de un punto de la l�nea
//	 * @param x2	Coordenada x del segundo punto de la l�nea (el de la flecha)
//	 * @param y2	Coordenada y del segundo punto de la l�nea (el de la flecha)
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 * @param color  	Color de la l�nea
//	 * @param largoFl	Pixels de largo de la flecha
//	 */
//	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor, Color color, int largoFl ) {
//		graphics.setColor( color );
//		graphics.setStroke( new BasicStroke( grosor ));
//		graphics.drawLine( (int)Math.round(x), (int)Math.round(y), (int)Math.round(x2), (int)Math.round(y2) );
//		double angulo = Math.atan2( y2-y, x2-x ) + Math.PI;
//		double angulo1 = angulo - Math.PI / 10;  // La flecha se forma rotando 1/10 de Pi hacia los dos lados
//		double angulo2 = angulo + Math.PI / 10;
//		graphics.drawLine( (int)Math.round(x2), (int)Math.round(y2), 
//				(int)Math.round(x2+largoFl*Math.cos(angulo1)), (int)Math.round(y2+largoFl*Math.sin(angulo1)) );
//		graphics.drawLine( (int)Math.round(x2), (int)Math.round(y2), 
//				(int)Math.round(x2+largoFl*Math.cos(angulo2)), (int)Math.round(y2+largoFl*Math.sin(angulo2)) );
//		panel.repaint();
//	}
//	
//	/** Dibuja una l�nea azul en la ventana
//	 * @param x	Coordenada x de un punto de la l�nea 
//	 * @param y	Coordenada y de un punto de la l�nea
//	 * @param x2	Coordenada x del segundo punto de la l�nea (el de la flecha)
//	 * @param y2	Coordenada y del segundo punto de la l�nea (el de la flecha)
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 */
//	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor ) {
//		dibujaFlecha( x, y, x2, y2, grosor, Color.blue );
//	}
//	
//	/** Borra una l�nea en la ventana
//	 * @param x	Coordenada x de un punto de la l�nea 
//	 * @param y	Coordenada y de un punto de la l�nea
//	 * @param x2	Coordenada x del segundo punto de la l�nea (el de la flecha)
//	 * @param y2	Coordenada y del segundo punto de la l�nea (el de la flecha)
//	 * @param grosor	Grueso de la l�nea (en p�xels)
//	 */
//	public void borraFlecha( double x, double y, double x2, double y2, float grosor ) {
//		dibujaFlecha( x, y, x2, y2, grosor, Color.white );
//	}
//
//	/** Devuelve el objeto de gr�fico sobre el que pintar, correspondiente al 
//	 * panel principal de la ventana
//	 * @return	Objeto gr�fico principal de la ventana
//	 */
//	public Graphics2D getGraphics() {
//		return graphics;
//	}
//	



}