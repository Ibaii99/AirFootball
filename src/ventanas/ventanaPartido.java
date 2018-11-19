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
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.Equipo;
import objetos.Pelota;

/** @author Jorge 
 *  @author ibai
 */
public class ventanaPartido extends JFrame {

	private boolean liga;
	private boolean arcade;
	private Graphics2D graphics;  // Objeto gr�fico sobre el que dibujar (del buffer)
	private JPanel panelCampo;         // Panel principal
	private boolean amistoso;
	private JLabel lblEquipoLocal=new JLabel("");
	private JLabel lblEquipoVisitante= new JLabel("");
	private JLabel lblPelota = new JLabel("");
	
	
	
	public ventanaPartido(Equipo eLocal, Equipo eVisitante, Pelota p) {
		setSize(750, 500);
		setResizable(false);
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
		panelCampo.setLayout(null);
		
		
		
//		Metodo original con doubles		
//		lblEquipoLocal.setBounds(eLocal.getBolaEquipo().getX(), eLocal.getBolaEquipo().getY(), eLocal.getBolaEquipo().getRadio()*2, eLocal.getBolaEquipo().getRadio()*2);
		getContentPane().setLayout(groupLayout);

	}

	/**	Metodo para actualizar las posiciones de los objetos del campo
	 * @param eLocal		Equipo local
	 * @param eVisitante	Equipo Visitante
	 * @param p				Pelota con la que se juega
	 */
	public void actualizarPosicionObjetos(Equipo eLocal, Equipo eVisitante, Pelota p) {
		panelCampo.removeAll(); // elimino todo los componenetes del panel de juego
		
		// añado todo al panel con las posiciones actualizadas
		lblEquipoLocal.setBounds((int)eLocal.getBolaEquipo().getX(), (int)eLocal.getBolaEquipo().getY(), (int)eLocal.getBolaEquipo().getRadio()*2, (int)eLocal.getBolaEquipo().getRadio()*2);
		panelCampo.add(lblEquipoLocal);
		
		lblEquipoVisitante.setBounds((int)eVisitante.getBolaEquipo().getX(), (int)eVisitante.getBolaEquipo().getY(), (int)eVisitante.getBolaEquipo().getRadio()*2, (int)eVisitante.getBolaEquipo().getRadio()*2);
		panelCampo.add(lblEquipoVisitante);
		
		lblPelota.setBounds((int)p.getX(), (int)p.getY(), (int)p.getRadio()*2, (int)p.getRadio()*2);
		panelCampo.add(lblPelota);
	
	}
	

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


	/** Devuelve la altura del panel de dibujo de la ventana
	 * @return	Altura del panel principal (�ltima coordenada y) en p�xels
	 */
	public int getAlturaCampo() {
		return panelCampo.getHeight()-1;
	}
	
	/** Devuelve la anchura del panel de dibujo de la ventana
	 * @return	Anchura del panel principal (�ltima coordenada x) en p�xels
	 */
	public int getAnchuraCampo() {
		return panelCampo.getWidth()-1;
	}
}