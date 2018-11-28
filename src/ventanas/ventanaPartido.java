package ventanas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
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
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.Equipo;
import fisicas.FisicasNuevas;
import fisicas.HiloJuego;
import objetos.Pelota;
import java.awt.event.KeyAdapter;

/**
 * @author Jorge
 * @author ibai
 */
public class ventanaPartido extends JFrame {

	private boolean liga;
	private boolean arcade;
	private Graphics2D graphics; // Objeto gr�fico sobre el que dibujar (del buffer)
	private JPanel panelCampo; // Panel principal

	private JLabel lblEquipoLocal = new JLabel("");
	private JLabel lblEquipoVisitante = new JLabel("");
	private JLabel lblPelota = new JLabel("");
	private JLabel lblEqL = new JLabel("");
	private JLabel lblEqV = new JLabel("");
	private boolean isMultijugador;
	private boolean isAmistoso;
	private boolean isJugadorEquipoLocal;
	private static double VELOCIDAD_CON_MOVIMIENTO = 3;

	private Font f;
	private Equipo eLocal;
	private Equipo eVisitante;
	private Pelota p;
	private FisicasNuevas fisicas;
	private HiloJuego hiloJuego;
	
	// modificar constructor ventana, pone pelota en posicion no correcta
	public ventanaPartido(Equipo eLocal, Equipo eVisitante, Pelota p, boolean esMultijjugador, boolean esAmistoso,
			boolean esJugadorVSMaquinaEquipoLocal, FisicasNuevas fisicas) {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fisicas = fisicas;
		this.isJugadorEquipoLocal = esJugadorVSMaquinaEquipoLocal;
		this.isMultijugador = esMultijjugador;
		this.isAmistoso = esAmistoso;
		this.eLocal = eLocal;
		this.eVisitante = eVisitante;
		this.p = p;
		

		lblEqV.setBounds(686, 5, 40, 40);
		lblEqL.setBounds(12, 5, 40, 40);

		GraphicsEnvironment ge = null;
		String nombreFont = "DSEG14Classic-Regular.ttf";

		
		try {	
			ImageIcon iconL = new ImageIcon(getClass().getResource("/"+eLocal.getBolaEquipo().getRutaImagen()));
			Image imgL = iconL.getImage();
			BufferedImage biL = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
			Graphics gL = biL.createGraphics();
			gL.drawImage(imgL, 0, 0, 40, 40, null);
			ImageIcon newIconL = new ImageIcon(biL);
			lblEqL.setIcon(newIconL);} catch(NullPointerException e) {}
		

		ImageIcon iconC = new ImageIcon(ventanaPartido.class.getResource("/iconos/campo.png"));
		Image imgC = iconC.getImage();
		BufferedImage biC = new BufferedImage(720, 395, BufferedImage.TYPE_INT_ARGB);
		Graphics gC = biC.createGraphics();
		gC.drawImage(imgC, 0, 0, 720, 395, null);
		ImageIcon newIconC = new ImageIcon(biC);
		

		try {	
			ImageIcon iconV = new ImageIcon(getClass().getResource("/"+eVisitante.getBolaEquipo().getRutaImagen()));
			Image imgV = iconV.getImage();
			BufferedImage biV = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
			Graphics gV = biV.createGraphics();
		 	gV.drawImage(imgV, 0, 0, 40, 40, null);
			ImageIcon newIconV = new ImageIcon(biV);
			lblEqV.setIcon(newIconV);} catch(NullPointerException e) {}

		ImageIcon icon = new ImageIcon(ventanaPartido.class.getResource("/iconos/marcadorconnombres.jpg"));
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(373, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 373, 50, null);
		ImageIcon newIcon = new ImageIcon(bi);
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, ventanaPartido.class.getResourceAsStream(nombreFont));
			f = f.deriveFont(Font.PLAIN, 28);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(900, 600);
		setResizable(true);
		setVisible(true);

		//lblEquipoLocal.setText("EQUIPO LOCAL");

		//lblEquipoVisitante.setText("EQUIPOVISITANTE");
		lblPelota.setText("PELOTA");

		

		panelCampo = new JPanel();
		panelCampo.setBackground(Color.GREEN);
		panelCampo.setLayout(null);
		panelCampo.add(lblEquipoLocal);
		panelCampo.add(lblEquipoVisitante);
		panelCampo.add(lblPelota);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel label = new JLabel("0");
		label.setBounds(422, 5, 75, 36);
		label.setForeground(Color.RED);
		label.setFont(f);
		panel.add(label);


		

		
		panel.add(lblEqL);

		
		panel.add(lblEqV);

		JLabel label_3 = new JLabel("0");
		label_3.setBounds(327, 5, 75, 36);
		label_3.setForeground(Color.RED);
		label_3.setFont(f);
		panel.add(label_3);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
								.addComponent(panelCampo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelCampo, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE).addContainerGap()));
		
		String siglasEqL = eLocal.getSiglas();
		siglasEqL = siglasEqL.substring(1);
		JLabel lblNomEqL = new JLabel(siglasEqL);
		lblNomEqL.setBounds(53, 5, 262, 36);
		panel.add(lblNomEqL);
		
		String siglasEqV = eVisitante.getSiglas();
		siglasEqV = siglasEqV.substring(1);
		JLabel lblNomEqV = new JLabel(siglasEqV);
		lblNomEqV.setBounds(464, 5, 262, 36);
		panel.add(lblNomEqV);
		getContentPane().setLayout(groupLayout);

	
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int tecla = e.getKeyCode();

				if (!isMultijugador) {
					Equipo jugador = null;

					if (isJugadorEquipoLocal)
						jugador = eLocal;
					if (!isJugadorEquipoLocal)
						jugador = eVisitante;

					switch (tecla) {
					case 37:
						
						jugador.getBolaEquipo().addVelocidadX(-VELOCIDAD_CON_MOVIMIENTO); // flecha izquierda
						break;
					case 38:
						jugador.getBolaEquipo().addVelocidadY(-VELOCIDAD_CON_MOVIMIENTO); // flecha arriba
						break;
					case 39:
						jugador.getBolaEquipo().addVelocidadX(VELOCIDAD_CON_MOVIMIENTO); // flecha derecha
						break;
					case 40:
						jugador.getBolaEquipo().addVelocidadY(VELOCIDAD_CON_MOVIMIENTO); // flecha abajo
						break;
					default:
						break;
					}
					// Reseteo de velocidades para que no se queden guardadas
					jugador.getBolaEquipo().setVelX(0);
					jugador.getBolaEquipo().setVelY(0);
				}

				else if (isMultijugador) {
					switch (tecla) {
					case 37:
						if(eLocal.getBolaEquipo().getVelX() > 0)eLocal.getBolaEquipo().setVelX(0);
						
						eLocal.getBolaEquipo().addVelocidadX(-VELOCIDAD_CON_MOVIMIENTO); // flecha izquierda
						break;
					case 38:
						if(eLocal.getBolaEquipo().getVelY() > 0)eLocal.getBolaEquipo().setVelY(0);
						
						eLocal.getBolaEquipo().addVelocidadY(-VELOCIDAD_CON_MOVIMIENTO); // flecha arriba
						break;
					case 39:
						if(eLocal.getBolaEquipo().getVelX() < 0)eLocal.getBolaEquipo().setVelX(0);
						
						eLocal.getBolaEquipo().addVelocidadX(VELOCIDAD_CON_MOVIMIENTO); // flecha derecha
						break;
					case 40:
						if(eLocal.getBolaEquipo().getVelY() < 0)eLocal.getBolaEquipo().setVelY(0);
						
						eLocal.getBolaEquipo().addVelocidadY(VELOCIDAD_CON_MOVIMIENTO); // flecha abajo
						break;
					case 87:
						if(eVisitante.getBolaEquipo().getVelY() > 0)eVisitante.getBolaEquipo().setVelY(0);
						
						eVisitante.getBolaEquipo().addVelocidadY(-VELOCIDAD_CON_MOVIMIENTO); // w
						break;
					case 83:
						if(eVisitante.getBolaEquipo().getVelY() < 0)eVisitante.getBolaEquipo().setVelY(0);
						
						eVisitante.getBolaEquipo().addVelocidadY(VELOCIDAD_CON_MOVIMIENTO); // s
						break;
					case 68:
						if(eVisitante.getBolaEquipo().getVelX() < 0)eVisitante.getBolaEquipo().setVelX(0);
						
						eVisitante.getBolaEquipo().addVelocidadX(VELOCIDAD_CON_MOVIMIENTO); // d
						break;
					case 65:
						if(eVisitante.getBolaEquipo().getVelX() > 0)eVisitante.getBolaEquipo().setVelX(0);
						
						eVisitante.getBolaEquipo().addVelocidadX(-VELOCIDAD_CON_MOVIMIENTO); // a
						break;
					default:
						break;
					}

					// reseteo de velocidades para que no se queden guardadas
//					eVisitante.getBolaEquipo().setVelX(0);
//					eVisitante.getBolaEquipo().setVelY(0);
//					eLocal.getBolaEquipo().setVelX(0);
//					eLocal.getBolaEquipo().setVelY(0);
				}
				actualizarCampo();
			}
			
		});
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		configuracionAntesDePartido();
		hiloJuego = new HiloJuego(p, eLocal, eVisitante, this);
		hiloJuego.start();
	}
	public void degradarVelocidad() {
		eVisitante.getBolaEquipo().setVelYAntes(eVisitante.getBolaEquipo().getVelY());
		eVisitante.getBolaEquipo().setVelXAntes(eVisitante.getBolaEquipo().getVelX());
		eLocal.getBolaEquipo().setVelYAntes(eLocal.getBolaEquipo().getVelY());
		eLocal.getBolaEquipo().setVelXAntes(eLocal.getBolaEquipo().getVelX());
		p.setVelYAntes(p.getVelY());
		p.setVelXAntes(p.getVelX());
		eVisitante.getBolaEquipo().setVelY(eVisitante.getBolaEquipo().getVelY()*fisicas.COEFICIENTE_PERDIDA_EQUIPO);
		eVisitante.getBolaEquipo().setVelX(eVisitante.getBolaEquipo().getVelX()*fisicas.COEFICIENTE_PERDIDA_EQUIPO);
		eLocal.getBolaEquipo().setVelY(eLocal.getBolaEquipo().getVelY()*fisicas.COEFICIENTE_PERDIDA_EQUIPO);
		eLocal.getBolaEquipo().setVelX(eLocal.getBolaEquipo().getVelX()*fisicas.COEFICIENTE_PERDIDA_EQUIPO);
		p.setVelY(p.getVelY()*fisicas.COEFICIENTE_PERDIDA_PELOTA);
		p.setVelX(p.getVelX()*fisicas.COEFICIENTE_PERDIDA_PELOTA);
	}

	
	public void mover(Equipo jugador) {
		fisicas.mueveEquipo(jugador, FisicasNuevas.TIEMPO, this);
	}

	/**
	 * Metodo para actualizar las posiciones de los objetos del campo Despues de
	 * moverlos los actualiza automaticamente para que se aprecie el movimiento
	 * 
	 * @param eLocal
	 *            Equipo local
	 * @param eVisitante
	 *            Equipo Visitante
	 * @param p
	 *            Pelota con la que se juega
	 */
	public void actualizarPosicionObjetos() {
		// añado todo al panel con las posiciones actualizadas

		lblEquipoVisitante.setBounds((int)( eVisitante.getBolaEquipo().getX()-eVisitante.getBolaEquipo().getRadio()), (int) (eVisitante.getBolaEquipo().getY()-eVisitante.getBolaEquipo().getRadio()),
				(int) eVisitante.getBolaEquipo().getRadio() * 2, (int) eVisitante.getBolaEquipo().getRadio() * 2);
		lblEquipoLocal.setBounds((int)( eLocal.getBolaEquipo().getX()-eLocal.getBolaEquipo().getRadio()), (int)( eLocal.getBolaEquipo().getY()-eLocal.getBolaEquipo().getRadio()),
				(int) eLocal.getBolaEquipo().getRadio() * 2, (int) eLocal.getBolaEquipo().getRadio() * 2);
		lblPelota.setBounds((int)( p.getX()-p.getRadio()), (int) (p.getY()-p.getRadio()), (int) p.getRadio() * 2, (int) p.getRadio() * 2);
		actualizarCampo();
	}

	/**
	 * Repintea y revalidea el panel del partido Para actualizar la posicion de los
	 * objetos
	 */
	public void actualizarCampo() {
		panelCampo.repaint();
		panelCampo.revalidate();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo que configura la ventana antes del partido, asigna: La posicion
	 * correspondiente a todos los objetos La posicion correspondiente a los labels
	 * Pone los labels visibles Pone tamaño correspondiente a los label Pone color
	 * o imagen correspondiente a cada label Actualiza el campo para que se muestren
	 * todos los cambios
	 * 
	 * @param p
	 *            Pelota del juego
	 * @param eLocal
	 *            Equipo Local
	 * @param eVisitante
	 *            Equipo Visitante
	 */
	public void configuracionAntesDePartido() {
		colocarEnPosInicial();
		actualizarPosicionObjetos();
		mostrarElementosDeJuego();
		actualizarTamanyoLbl();
		pintarLabels();
		actualizarCampo();
	}

	// ...........................................................................................................//

	/**
	 * Metodo que pinta cada label con el color correspondiente o imagen
	 * correspondiente
	 * 
	 * @param eLocal
	 *            Equio local
	 * @param eVisitante
	 *            Equipo Visitante
	 * @param p
	 *            Pelota
	 */
	private void pintarLabels() {
		elementosAOpaco();
		if (eLocal.getBolaEquipo().getImagenObjeto() == null)
			lblEquipoLocal.setBackground(eLocal.getBolaEquipo().getColor());
		if (eVisitante.getBolaEquipo().getImagenObjeto() == null)
			lblEquipoVisitante.setBackground(eVisitante.getBolaEquipo().getColor());
		if (p.getImagenObjeto() == null)
			lblPelota.setBackground(p.getColor());
		
		if (eLocal.getBolaEquipo().getImagenObjeto() != null)
			lblEquipoLocal.setIcon(new ImageIcon("/"+this.getClass().getResource(eLocal.getBolaEquipo().getRutaImagen())));
		if (eVisitante.getBolaEquipo().getImagenObjeto() != null)
			lblEquipoVisitante.setIcon(new ImageIcon("/"+this.getClass().getResource(eVisitante.getBolaEquipo().getRutaImagen())));
		if (p.getImagenObjeto() != null)
			lblPelota.setIcon(new ImageIcon("/"+this.getClass().getResource(p.getRutaImagen())));
	}

	/**
	 * Metodo para colocar los objetos en la posicion correspondiente segun el campo
	 * 
	 * @param p
	 *            Pelota
	 * @param eLocal
	 *            Equipo Local
	 * @param eVisitante
	 *            Equipo Visitante
	 */
	private void colocarEnPosInicial() {
		p.setX((int) getPanelCampo().getSize().getWidth() / 2);
		p.setY((int) getPanelCampo().getSize().getHeight() / 2);

		// TODO editar esto, no son los objetos son los labels

		eLocal.getBolaEquipo().setX(getPanelCampo().getSize().getWidth() - getPanelCampo().getSize().getWidth() / 4);
		eVisitante.getBolaEquipo().setX(getPanelCampo().getSize().getWidth() / 4);

		eLocal.getBolaEquipo().setY(getPanelCampo().getSize().getHeight() / 2);
		eVisitante.getBolaEquipo().setY(getPanelCampo().getSize().getHeight() / 2);
	}

	/**
	 * Actualiza el tamaño de los labels respecto a los objetos originales
	 * 
	 * @param p
	 *            Pelota
	 * @param eLocal
	 *            Equipo Local
	 * @param eVisitante
	 *            Equipo Visitante
	 */
	private void actualizarTamanyoLbl() {
		lblEquipoLocal.setSize((int) eLocal.getBolaEquipo().getRadio() * 2,
				(int) eLocal.getBolaEquipo().getRadio() * 2);
		lblEquipoVisitante.setSize((int) eVisitante.getBolaEquipo().getRadio() * 2,
				(int) eVisitante.getBolaEquipo().getRadio() * 2);
		lblPelota.setSize((int) p.getRadio() * 2, (int) p.getRadio() * 2);
	}

	/**
	 * Muestra todos los elementos con los que se juega A utilizar antes de los
	 * partidos
	 */
	private void mostrarElementosDeJuego() {
		lblEquipoLocal.setVisible(true);
		lblPelota.setVisible(true);
		lblEquipoVisitante.setVisible(true);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Oculta todos los elementos con los que se juega A utilizar despues de los
	 * partidos
	 */
	public void ocultarElementosDeJuego() {
		lblEquipoLocal.setVisible(false);
		lblPelota.setVisible(false);
		lblEquipoVisitante.setVisible(false);
	}

	// TODO esto es para utilizar si tiene imagen o si tiene tiene color
	// imagen -> transparente || color-> opaco
	/**
	 * Metodo para poner elementos con los que se juega opacos
	 */
	public void elementosAOpaco() {
		lblEquipoLocal.setOpaque(true);
		lblEquipoVisitante.setOpaque(true);
		lblPelota.setOpaque(true);
	}

	/**
	 * Metodo para poner elementos con los que se juega transparentes
	 */
	public void elementosTransparentes() {
		lblEquipoLocal.setOpaque(false);
		lblEquipoVisitante.setOpaque(false);
		lblPelota.setOpaque(false);
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

	
}