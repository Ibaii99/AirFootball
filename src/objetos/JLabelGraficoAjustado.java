package objetos;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Clase mejorada de JLabel para gestionar im�genes ajustadas al JLabel
 */
public class JLabelGraficoAjustado extends JLabel {
	// la posici�n X,Y se hereda de JLabel
	protected int anchuraObjeto; // Anchura definida del objeto en pixels
	protected int alturaObjeto; // Altura definida del objeto en pixels
	protected double zoom; // Zoom del objeto en % (1.0 = 100%)
	protected double radsRotacion; // Rotaci�n del objeto en radianes
	protected float opacidad; // Opacidad del objeto (0.0f a 0.1f)
	protected BufferedImage imagenObjeto; // imagen para el escalado
	private static final long serialVersionUID = 1L; // para serializar
	private Color color;
	/**
	 * Crea un nuevo JLabel gr�fico.<br>
	 * Si no existe el fichero de imagen, se crea un rect�ngulo blanco con borde
	 * rojo
	 * 
	 * @param nombreImagenObjeto
	 *            Nombre fichero donde est� la imagen del objeto. Puede ser tambi�n
	 *            un nombre de recurso desde el paquete de esta clase.
	 * @param anchura
	 *            Anchura del gr�fico en p�xels (si es <= 0 ocupa todo el ancho)
	 * @param altura
	 *            Altura del gr�fico en p�xels (si es <= 0 ocupa todo el alto)
	 */
	public JLabelGraficoAjustado(String nombreImagenObjeto, int anchura, int altura) {
		zoom = 1.0;
		setName(nombreImagenObjeto);
		opacidad = 1.0f;
		setImagen(nombreImagenObjeto); // Cargamos el icono
		setSize(anchura, altura);
	}

	public JLabelGraficoAjustado(String string) {
		setText(string);
	}

	@Override
	public void setSize(int anchura, int altura) {
		if (anchura <= 0 && imagenObjeto != null)
			anchura = imagenObjeto.getWidth();
		if (altura <= 0 && imagenObjeto != null)
			altura = imagenObjeto.getHeight();
		anchuraObjeto = anchura;
		alturaObjeto = altura;
		super.setSize(anchura, altura);
		setPreferredSize(new Dimension(anchura, altura));
	}

	Border bordeError = BorderFactory.createLineBorder(Color.red);

	/**
	 * Cambia la imagen del objeto
	 * 
	 * @param nomImagenObjeto
	 *            Nombre fichero donde est� la imagen del objeto. Puede ser tambi�n
	 *            un nombre de recurso desde el paquete de esta clase.
	 */
	public void setImagen(String nomImagenObjeto) {
		File f = new File(nomImagenObjeto);
		URL imgURL = null;
		try {
			imgURL = f.toURI().toURL();
			if (!f.exists()) {
				imgURL = JLabelGraficoAjustado.class.getResource(nomImagenObjeto).toURI().toURL();
			}
		} catch (Exception e) {
		} // Cualquier error de carga, la imagen se queda nula
		if (imgURL == null) {
			imagenObjeto = null;
		} else {
			try { // guarda la imagen para dibujarla de forma escalada despu�s
				imagenObjeto = ImageIO.read(imgURL);
			} catch (IOException e) {
			} // Error al leer la imagen
		}
		if (imagenObjeto == null) {
			setOpaque(true);
			setBackground(Color.orange);
			setForeground(Color.blue);
			setBorder(bordeError);
			setText(nomImagenObjeto);
		} else {
			if (getBorder() == bordeError)
				setBorder(null);
			setOpaque(false);
			setText("");
		}
		repaint();
	}

	/**
	 * Cambia la imagen del objeto
	 * 
	 * @param urlImagenObjeto
	 *            URL de recurso o fichero donde est� la imagen del objeto.
	 */
	public void setImagen(URL urlImagenObjeto) {
		imagenObjeto = null;
		try { // guarda la imagen para dibujarla de forma escalada despu�s
			imagenObjeto = ImageIO.read(urlImagenObjeto);
		} catch (IOException e) {
		} // Error al leer la imagen - se queda a null
		if (imagenObjeto == null) {
			setOpaque(true);
			setBackground(Color.red);
			setForeground(Color.blue);
			setBorder(bordeError);
			setText("" + urlImagenObjeto.getFile());
		} else {
			if (getBorder() == bordeError)
				setBorder(null);
			setOpaque(false);
			setText("");
		}
		repaint();
	}

	/**
	 * Devuelve la anchura del rect�ngulo gr�fico del objeto
	 * 
	 * @return Anchura
	 */
	public int getAnchuraObjeto() {
		return anchuraObjeto;
	}

	/**
	 * Devuelve la altura del rect�ngulo gr�fico del objeto
	 * 
	 * @return Altura
	 */
	public int getAlturaObjeto() {
		return alturaObjeto;
	}

	/**
	 * Devuelve la rotaci�n del objeto
	 * 
	 * @return Rotaci�n actual del objeto en radianes
	 */
	public double getRotacion() {
		return radsRotacion;
	}

	/**
	 * Modifica la rotaci�n del objeto
	 * 
	 * @param rotacion
	 *            Nueva rotaci�n del objeto (en radianes)
	 */
	public void setRotacion(double rotacion) {
		radsRotacion = rotacion;
		repaint(); // Si no repintamos aqu� Swing no sabe que ha cambiado el dibujo
	}

	/**
	 * Devuelve el zoom del objeto
	 * 
	 * @return Zoom actual del objeto en % (1.0 = 100%)
	 */
	public double getZoom() {
		return zoom;
	}

	/**
	 * Modifica el zoom del objeto
	 * 
	 * @param rotacion
	 *            Nuevo zoom del objeto en % (1.0 = 100%)
	 */
	public void setZoom(double zoom) {
		this.zoom = zoom;
		repaint(); // Si no repintamos aqu� Swing no sabe que ha cambiado el dibujo
	}

	/**
	 * Devuelve la opacidad del objeto
	 * 
	 * @return Opacidad del objeto (0.0f transparente a 1.0f opaco)
	 */
	public float getOpacidad() {
		return opacidad;
	}

	/**
	 * Modifica la opacidad del objeto
	 * 
	 * @param opacidad
	 *            Opacidad del objeto (0.0f transparente a 1.0f opaco)
	 */
	public void setOpacidad(float opacidad) {
		if (opacidad < 0.0f || opacidad > 1.0f)
			return; // No se cambia si el valor es inv�lido
		this.opacidad = opacidad;
		repaint(); // Si no repintamos aqu� Swing no sabe que ha cambiado el dibujo
	}

	/**
	 * Actualiza la posici�n del objeto
	 * 
	 * @param x
	 *            Coordenada x (doble) - se redondea al p�xel m�s cercano
	 * @param y
	 *            Coordenada y (doble) - se redondea al p�xel m�s cercano
	 */
	public void setLocation(double x, double y) {
		setLocation((int) Math.round(x), (int) Math.round(y));
	}

	private boolean horFlip = false; // Flip horizontal
	private boolean vertFlip = false; // Flip vertical

	/**
	 * Provoca que la imagen sea especular
	 * 
	 * @param horFlip
	 *            true para flip horizontal
	 * @param vertFlip
	 *            true para flip vertical
	 */
	public void setFlip(boolean horFlip, boolean vertFlip) {
		this.horFlip = horFlip;
		this.vertFlip = vertFlip;
		repaint();
	}

	// Dibuja este componente de una forma no habitual
	 @Override
	    protected void paintComponent(Graphics gr)
	    {
	        super.paintComponent(gr);
	        Graphics2D g = (Graphics2D)gr;

	        g.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);

	        Shape ring = createRingShape(getWidth()/2, getHeight()/2, getWidth()/2-1, getHeight()/8); 
	        g.setColor(color);
	        g.fill(ring);
	        g.setColor(Color.BLACK);
	        g.draw(ring);

	       /* Shape otherRing = createRingShape(getWidth()/2, getHeight()/2, getWidth()/4-1, getHeight()/4-1); 
	        g.setPaint(new GradientPaint(
	            new Point(250, 40), Color.RED, 
	            new Point(350, 200), Color.GREEN));
	        g.fill(otherRing);
	        g.setColor(Color.BLACK);
	        g.draw(otherRing);*/

	    }

	    private static Shape createRingShape(
	        double centerX, double centerY, double outerRadius, double thickness)
	    {
	        Ellipse2D outer = new Ellipse2D.Double(
	            centerX - outerRadius, 
	            centerY - outerRadius,
	            outerRadius + outerRadius, 
	            outerRadius + outerRadius);
	        Ellipse2D inner = new Ellipse2D.Double(
	            centerX - outerRadius + thickness, 
	            centerY - outerRadius + thickness,
	            outerRadius + outerRadius - thickness - thickness, 
	            outerRadius + outerRadius - thickness - thickness);
	        Area area = new Area(outer);
	        area.subtract(new Area(inner));
	        return area;
	    }

		public Color getColor() {
			return color;
		}

		public void setColor(Color color2) {
			this.color = color2;
		}

	}
