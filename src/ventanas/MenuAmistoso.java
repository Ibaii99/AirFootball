package ventanas;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import jugador.Jugador;
import objetos.ObjetoCombobox;
import objetos.Pelota;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MenuAmistoso extends JFrame {
	/**
	 * @author Jorge
	 * @author ibai
	 *
	 */

	public String equipoL;
	public String equipoV;
	public String rutaImagenL;
	public String rutaImagenV;
	public String siglasL;
	public String siglasV;
	public ImageIcon imageIconL;
	private static FisicasNuevas f = new FisicasNuevas();

	/**
	 * 
	 * @param Anchura
	 *            de la ventana en base a listeners de la ventana Inicio
	 * @param Altura
	 *            de la ventana en base a listeners de la ventana Inicio
	 * 
	 * @param bd
	 *            Base de datos del programa
	 * @param con
	 *            Conexion con el archivo Base de Datos
	 * @param f
	 *            Fisicas con las que funciona el juego
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MenuAmistoso(int anchura, int altura, BaseDeDatos bd, FisicasNuevas f)
			throws ClassNotFoundException, SQLException {
		try {
			setSize(587, 380);
		} catch (Exception e) {
			setSize(626, 460);
		}

		ImageIcon imageIcon = new ImageIcon(MenuAmistoso.class.getResource("/iconos/stadiumAmistoso.png"));
		Image image = imageIcon.getImage();

		Image newimg = image.getScaledInstance(anchura, altura, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		ImageIcon icRsz = new ImageIcon(newimg);
		getContentPane().setLayout(new BorderLayout(0, 0));

		final JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		final JComboBox cbLocal = new JComboBox();

		final JButton btnIniciarAmistoso = new JButton("Iniciar amistoso");
		btnIniciarAmistoso.setBounds(207, 271, 159, 27);
		panel.add(btnIniciarAmistoso);

		btnIniciarAmistoso.setFont(new Font("Arial Black", Font.PLAIN, 13));

		final JLabel lblVisitante = new JLabel("Visitante");
		lblVisitante.setBounds(356, 203, 65, 19);
		panel.add(lblVisitante);
		lblVisitante.setFont(new Font("Arial Black", Font.PLAIN, 13));

		final JComboBox cbVisitante = new JComboBox();
		cbVisitante.setBounds(300, 224, 150, 22);
		panel.add(cbVisitante);

		final JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(155, 203, 40, 19);
		panel.add(lblLocal);
		lblLocal.setFont(new Font("Arial Black", Font.PLAIN, 13));

		final JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 0, 0);
		panel.add(lblFondo);

		lblFondo.setIcon(icRsz);

		final JLabel lblEqL = new JLabel("");
		lblEqL.setBounds(130, 96, 100, 100);

		final JLabel lblEqV = new JLabel("");
		lblEqV.setBounds(334, 96, 100, 100);

		Logger logger = Logger.getLogger("baseDeDatos");

		Statement consulta;

		String comando = "";
		/**
		 * No hago un m�todo para diferentes sentencias SQL porque no realizamos
		 * exactamente la misma en ning�n momento
		 * 
		 */
		try {
			Class.forName("org.sqlite.JDBC");
			bd.init();
		} catch (Exception e3) {
			// e3.printStackTrace();
		}
		String query = "SELECT NOMBRE, ICONO FROM EQUIPOS;";
		ResultSet rs = bd.getCon().createStatement().executeQuery(query);
		while (rs.next()) {

			String nomEq = rs.getString("Nombre");
			String iconEq = rs.getString("Icono");
			cbLocal.addItem(new ObjetoCombobox(1, nomEq, new ImageIcon(iconEq)));
			cbVisitante.addItem(new ObjetoCombobox(2, nomEq, new ImageIcon(iconEq)));
		}
		rs.close();
		bd.close();

		equipoL = "/iconos/equipos/ala.png";
		ImageIcon imgI = new ImageIcon(getClass().getResource(equipoL));

		lblEqL.setIcon(new ImageIcon(getClass().getResource(equipoL)));
		equipoV = "/iconos/equipos/ala.png";
		cbLocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filenameL = cbLocal.getSelectedObjects().toString();
				try {
					try {

						Class.forName("org.sqlite.JDBC");
						bd.init();
						Statement consultaCB;
						consultaCB = bd.getCon().createStatement();
						String comando2 = "SELECT ICONO FROM EQUIPOS WHERE NOMBRE = '"
								+ cbLocal.getSelectedItem().toString() + "'";
						logger.log(Level.INFO, "BD: " + comando2);
						consultaCB.executeUpdate(comando2);
						ResultSet rs2 = bd.getCon().createStatement().executeQuery(comando2);
						while (rs2.next()) {

							equipoL = "/" + rs2.getString("ICONO");
							String iconoL = rs2.getString("ICONO");

						}
						rs2.close();
						bd.close();
						ImageIcon imageIconL = new ImageIcon(getClass().getResource(equipoL));
						resizeo(imageIconL, lblEqL, (int) Math.round(100 * getWidth() / 626),
								(int) Math.round(100 * getHeight() / 460));

						lblEqL.repaint();
					} catch (SQLException i) {

					}
				} catch (Exception e2) {

				}
				lblEqL.repaint();
				revalidate();
			}

		});
		cbVisitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filenameV = cbVisitante.getSelectedObjects().toString();
				try {
					try {

						Class.forName("org.sqlite.JDBC");
						bd.init();
						Statement consultaCB;
						consultaCB = bd.getCon().createStatement();
						String comando2 = "SELECT ICONO FROM EQUIPOS WHERE NOMBRE = '"
								+ cbVisitante.getSelectedItem().toString() + "'";
						logger.log(Level.INFO, "BD: " + comando2);
						consultaCB.executeUpdate(comando2);
						ResultSet rs2 = bd.getCon().createStatement().executeQuery(comando2);
						System.out.println(rs2.toString());

						while (rs2.next()) {

							equipoV = "/" + rs2.getString("ICONO");
							String iconoV = rs2.getString("ICONO");

						}
						rs2.close();

						ImageIcon imageIconV = new ImageIcon(getClass().getResource(equipoV));
						resizeo(imageIconV, lblEqV, (int) Math.round(100 * getWidth() / 626),
								(int) Math.round(100 * getWidth() / 626));

						lblEqV.repaint();
						bd.close();
					} catch (SQLException i) {

					}
				} catch (Exception e2) {

				}
				lblEqL.repaint();
				revalidate();
			}

		});

		cbLocal.setBounds(120, 224, 150, 22);
		panel.add(cbLocal);
		btnIniciarAmistoso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				char[] a = { 'a', 'b', 'c' };

				
				Equipo eLocal = bd.convertirAEquipo(cbLocal.getSelectedItem().toString(), new Jugador("", a, 0),0);
				System.out.println(eLocal.toString());

				Equipo eVisitante = bd.convertirAEquipo(cbVisitante.getSelectedItem().toString(),
						new Jugador("", a, 0), 0);
				System.out.println(eVisitante.toString());
				Pelota pelotaPartido = new Pelota(Color.white, "pelota", 20);

				ventanaPartido partido = new ventanaPartido(eLocal, eVisitante, pelotaPartido, true, false, true, false, f, bd, null, 0, null,0);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				partido.empieza();

			}
		}
		// PRUEBA DE LISTENER DE VENTANAPARTIDO

		);
		panel.add(lblEqL);
		panel.add(lblEqV);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				resizeo(new ImageIcon(Inicio.class.getResource("/iconos/stadiumAmistoso.png")), lblFondo, getWidth(),
						getHeight());
				lblFondo.setBounds(0, 0, getWidth(), getHeight());
				getContentPane().revalidate();
				getContentPane().setSize(getSize());
				// Aqu� hemos intentado meter una constante (getWidth()/626) pero no nos
				// funcionaba, ni haciendo un Math.round.
				int nuevaFuente = (13 * getWidth() / 626);
				int nuevaAnchura = (80 * getWidth() / 626);
				int nuevaAltura = (20 * getWidth() / 626);
				int nuevaAnchuraBtn = (159 * getWidth() / 626);
				int nuevaAlturaBtn = (30 * getWidth() / 626);
				int nuevaAnchuraCb = (150 * getWidth() / 626);
				int nuevaAlturaCb = (22 * getWidth() / 626);
				int ladoIconoH = (int) Math.round(100 * getWidth() / 626);
				int ladoIconoV = (int) Math.round(100 * getHeight() * 1.36 / 626);
				// Inicializaci�n de componentes del NullLayout en funci�n del eje 0,0 de la
				// ventana
				lblLocal.setFont(new Font("Arial Black", Font.PLAIN, nuevaFuente));
				lblVisitante.setFont(new Font("Arial Black", Font.PLAIN, nuevaFuente));
				cbVisitante.setBounds((int) Math.round(((getWidth() / 2) + (getWidth() * 0.01))),
						(int) Math.round(((getHeight() / 2) - (getHeight() * 0.01))), nuevaAnchuraCb, nuevaAlturaCb);
				lblVisitante.setBounds((int) Math.round(((getWidth() / 2) + (getWidth() * 0.05))),
						(int) Math.round(((getHeight() / 2) - (getHeight() * 0.295))), nuevaAnchura, nuevaAltura);
				cbLocal.setBounds((int) Math.round(((getWidth() / 2) - (getWidth() * 0.31))),
						(int) Math.round(((getHeight() / 2) - (getHeight() * 0.01))), nuevaAnchuraCb, nuevaAlturaCb);
				lblLocal.setBounds((int) Math.round(((getWidth() / 2) - (getWidth() * 0.25))),
						(int) Math.round(((getHeight() / 2) - (getHeight() * 0.295))), nuevaAnchura, nuevaAltura);
				btnIniciarAmistoso.setBounds((int) Math.round(((getWidth() / 2) - (getWidth() * 0.15))),
						(int) Math.round(((getHeight() / 2) + (getHeight() * 0.2))), nuevaAnchuraBtn, nuevaAlturaBtn);
				btnIniciarAmistoso.setFont(new Font("Arial Black", Font.PLAIN, nuevaFuente));

				ImageIcon imageIconL = (ImageIcon) lblEqL.getIcon();
				resizeo(imageIconL, lblEqL, ladoIconoH, ladoIconoV);
				lblEqL.setBounds((int) Math.round(((getWidth() / 2) - (getWidth() * 0.29))),
						(int) Math.round(((getHeight() / 2) - (getHeight() * 0.25))), ladoIconoH, ladoIconoV);
				lblEqL.repaint();
				revalidate();
				String localizacion = equipoV;
				ImageIcon imageIconV = new ImageIcon(Inicio.class.getResource(localizacion));
				resizeo(imageIconV, lblEqV, ladoIconoH, ladoIconoV);
				lblEqV.setBounds((int) Math.round(((getWidth() / 2) + (getWidth() * 0.02))),
						(int) Math.round(((getHeight() / 2) - (getHeight() * 0.25))), ladoIconoH, ladoIconoV);
				lblEqV.repaint();
				revalidate();
			}
		});
	}

	/**
	 * @param imageIconL
	 *            Icono del escudo del equipo
	 * @param jlIcono
	 *            JLabel donde se encuentra el icono del escudo
	 * @param anchura
	 *            a la que queremos ajustar el ImageIcon del escudo
	 * @param altura
	 *            a la que queremos ajustar el ImageIcon del escudo
	 */
	public void resizeo(ImageIcon imageIconL, JLabel jlIcono, int anchura, int altura) {
		Image imagenResizL = imageIconL.getImage();
		Image iResizeoL = imagenResizL.getScaledInstance((int) Math.round(anchura), (int) Math.round(altura),
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon iiResizeoL = new ImageIcon(iResizeoL);
		jlIcono.setSize(iiResizeoL.getIconWidth(), iiResizeoL.getIconHeight());
		jlIcono.setIcon(iiResizeoL);
		jlIcono.repaint();
		revalidate();
	}

	public String getEquipoL() {
		return equipoL;
	}

	public void setEquipoL(String equipoL) {
		this.equipoL = equipoL;
	}

	public ImageIcon getImageIconL() {
		return imageIconL;
	}

	public void setImageIconL(ImageIcon imageIconL) {
		this.imageIconL = imageIconL;
	}

	public int anchura;
	public int altura;

	public int getAnchura() {
		return anchura;
	}

	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
}
