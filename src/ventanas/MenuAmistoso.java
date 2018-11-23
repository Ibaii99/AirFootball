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

import entidades.Equipo;
import fisicas.FisicasNuevas;
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
	 *
	 */

	public String equipoL;
	public String equipoV;
	public String rutaImagenL;
	public String rutaImagenV;
	public String siglasL;
	public String siglasV;
	public ImageIcon imageIconL;

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

	public MenuAmistoso(int anchura, int altura) throws ClassNotFoundException, SQLException {
		try {
			setSize(anchura, altura);
		}catch (Exception e) {
			setSize(577, 383);
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

		// Connection conexion;
		// PreparedStatement consulta1;
		// ResultSet rs;

		// CONSULTA SQL FALLA
		// try {
		// Class.forName("org.sqlite.JDBC");
		// conexion = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
		// consulta1 = conexion.prepareStatement("select equipo, icono from equipos");
		// rs = consulta1.executeQuery();
		// while(rs.next()){
		// System.out.println(rs);
		// }
		// }catch(Exception e) {
		// System.out.println("no se ha podido añadir equipos");
		// }

		Logger logger = Logger.getLogger("baseDeDatos");

		Connection con = null;

		Statement consulta;

		String comando = "";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
		} catch (Exception e3) {
			// e3.printStackTrace();
		}
		String query = "SELECT NOMBRE, ICONO FROM EQUIPOS;";
		System.out.println(query);
		con = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
		// Statement st = con.createStatement();
		ResultSet rs = con.createStatement().executeQuery(query);
		System.out.println(rs.toString());
		System.out.println(rs);
		// System.out.println(rs.isClosed());

		System.out.println(rs.getString("NOMBRE"));
		while (rs.next()) {

			String nomEq = rs.getString("Nombre");
			String iconEq = rs.getString("Icono");
			cbLocal.addItem(new ObjetoCombobox(1, nomEq, new ImageIcon(iconEq)));
			cbVisitante.addItem(new ObjetoCombobox(2, nomEq, new ImageIcon(iconEq)));
			// System.out.println(nomEq + " " + iconEq);
		}
		// st.close();
		rs.close();
		// consulta.executeUpdate(comando);
		// comando = "select equipo, icono from equipos";
		// logger.log(Level.INFO, "BD: " + comando);
		// System.out.println(consulta.executeUpdate(comando));

		equipoL = "/iconos/equipos/ala.png";
		ImageIcon imgI = new ImageIcon(getClass().getResource(equipoL));

		lblEqL.setIcon(new ImageIcon(getClass().getResource(equipoL)));
		equipoV = "/iconos/equipos/ala.png";
		cbLocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filenameL = cbLocal.getSelectedObjects().toString();
				try {
					try {
						Connection conCB = null;
						Class.forName("org.sqlite.JDBC");
						conCB = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
						Statement consultaCB;
						consultaCB = conCB.createStatement();
						String comando2 = "SELECT ICONO FROM EQUIPOS WHERE NOMBRE = '"
								+ cbLocal.getSelectedItem().toString() + "'";
						logger.log(Level.INFO, "BD: " + comando2);
						consultaCB.executeUpdate(comando2);
						ResultSet rs2 = conCB.createStatement().executeQuery(comando2);
						System.out.println(rs2.toString());
						// System.out.println(rs2);
						// System.out.println(rs.isClosed());

						while (rs2.next()) {

							equipoL = "/" + rs2.getString("ICONO");
							String iconoL = rs2.getString("ICONO");
							// System.out.println(equipoL);
						}
						rs2.close();
						ImageIcon imageIconL = new ImageIcon(getClass().getResource(equipoL));
						Image imagenResizL = imageIconL.getImage();
						Image iResizeoL = imagenResizL.getScaledInstance((int) Math.round(100 * getWidth() / 626),
								(int) Math.round(100 * getWidth() / 626), java.awt.Image.SCALE_SMOOTH); // scale it the
																										// smooth way
						ImageIcon iiResizeoL = new ImageIcon(iResizeoL);
						lblEqL.setIcon(new ImageIcon(getClass().getResource(equipoL)));
						lblEqL.setIcon(iiResizeoL);
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
						Connection conCB = null;
						Class.forName("org.sqlite.JDBC");
						conCB = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
						Statement consultaCB;
						consultaCB = conCB.createStatement();
						String comando2 = "SELECT ICONO FROM EQUIPOS WHERE NOMBRE = '"
								+ cbVisitante.getSelectedItem().toString() + "'";
						logger.log(Level.INFO, "BD: " + comando2);
						consultaCB.executeUpdate(comando2);
						ResultSet rs2 = conCB.createStatement().executeQuery(comando2);
						System.out.println(rs2.toString());
						// System.out.println(rs2);
						// System.out.println(rs.isClosed());

						while (rs2.next()) {

							equipoV = "/" + rs2.getString("ICONO");
							String iconoV = rs2.getString("ICONO");
							// System.out.println(equipoL);
						}
						rs2.close();
						ImageIcon imageIconV = new ImageIcon(getClass().getResource(equipoV));
						Image imagenResizV = imageIconV.getImage();
						Image iResizeoV = imagenResizV.getScaledInstance((int) Math.round(100 * getWidth() / 626),
								(int) Math.round(100 * getWidth() / 626), java.awt.Image.SCALE_SMOOTH); // scale it the
																										// smooth way
						ImageIcon iiResizeoV = new ImageIcon(iResizeoV);
						lblEqV.setIcon(new ImageIcon(getClass().getResource(equipoV)));
						lblEqV.setIcon(iiResizeoV);
						lblEqV.repaint();
					} catch (SQLException i) {

					}
				} catch (Exception e2) {

				}
				lblEqL.repaint();
				revalidate();
			}

		});
		// ImageIcon imageIconL = new ImageIcon(equipoL);
		// Image imagenResizL = imageIconL.getImage();
		// Image iResizeoL = imagenResizL.getScaledInstance((int) Math.round(100 *
		// getWidth() / 626),
		// (int) Math.round(100 * getWidth() / 626), java.awt.Image.SCALE_SMOOTH); //
		// scale it the smooth way
		// ImageIcon iiResizeoL = new ImageIcon(iResizeoL);
		ImageIcon imageIconV = new ImageIcon(equipoL);
		Image imagenResizV = imageIconV.getImage();
		Image iResizeoV = imagenResizV.getScaledInstance((int) Math.round(100 * getWidth() / 626),
				(int) Math.round(100 * getWidth() / 626), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		ImageIcon iiResizeoV = new ImageIcon(iResizeoV);
		// lblEqL.setIcon(iiResizeoL);

		lblEqV.setIcon(iiResizeoV);
		cbLocal.setBounds(120, 224, 150, 22);
		panel.add(cbLocal);
		btnIniciarAmistoso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.out.println(equipoL);
				String nomEqL = cbLocal.getSelectedItem().toString();
				String nomEqV = cbVisitante.getSelectedItem().toString();
				try {
					try {
						Connection conL = null;
						Class.forName("org.sqlite.JDBC");
						conL = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
						Statement consultaL;
						consultaL = conL.createStatement();
						String comando3 = "SELECT ICONO, SIGLAS FROM EQUIPOS WHERE NOMBRE = '"
								+ cbLocal.getSelectedItem().toString() + "'";
						consultaL.executeUpdate(comando3);
						ResultSet rs3 = conL.createStatement().executeQuery(comando3);
						System.out.println(rs3.toString());
				
						while (rs3.next()) {

							siglasL = "/" + rs3.getString("SIGLAS");
							rutaImagenL = rs3.getString("ICONO");
						}
						
					} catch (SQLException i) {

					}
				} catch (Exception e2) {

				}
				try {
					try {
						Connection conV = null;
						Class.forName("org.sqlite.JDBC");
						conV = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
						Statement consultaV;
						consultaV = conV.createStatement();
						String comando4 = "SELECT ICONO, SIGLAS FROM EQUIPOS WHERE NOMBRE = '"
								+ cbVisitante.getSelectedItem().toString() + "'";
						consultaV.executeUpdate(comando4);
						ResultSet rs3 = conV.createStatement().executeQuery(comando4);
						System.out.println(rs3.toString());
				
						while (rs3.next()) {

							siglasV = "/" + rs3.getString("SIGLAS");
							rutaImagenV = rs3.getString("ICONO");
						}
						
					} catch (SQLException i) {

					}
				} catch (Exception e2) {

				}
				Equipo equipoLocal = new Equipo(nomEqL, siglasL, rutaImagenL, 1, 1);
				Equipo equipoVisitante = new Equipo(nomEqV, siglasV, rutaImagenV, 1, 1);
				Pelota pelotaPartido = new Pelota(getBackground(), "pelota", 1);
				ventanaPartido partido = new ventanaPartido(equipoLocal, equipoVisitante, pelotaPartido, false, true, true, new FisicasNuevas()); // aquï¿½ meter quizï¿½ un
				partido.configuracionAntesDePartido(pelotaPartido, equipoLocal, equipoVisitante);
				partido.setVisible(true);
				// PRUEBA DE LISTENER DE VENTANAPARTIDO 
			}
		});
		panel.add(lblEqL);
		panel.add(lblEqV);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {

				ImageIcon imageIconR = new ImageIcon(Inicio.class.getResource("/iconos/stadiumAmistoso.png"));
				Image imagenResiz = imageIconR.getImage();

				Image iResizeo = imagenResiz.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH); // scale
																														// it
																														// the
																														// smooth
																														// way
				ImageIcon iiResizeo2 = new ImageIcon(iResizeo);
				panel.setSize(getSize());
				lblFondo.setIcon(iiResizeo2);

				lblFondo.setBounds(0, 0, getWidth(), getHeight());
				getContentPane().revalidate();
				getContentPane().setSize(getSize());
				int nuevaFuente = (13 * getWidth() / 626);
				int nuevaAnchura = (80 * getWidth() / 626);
				int nuevaAltura = (20 * getWidth() / 626);
				int nuevaAnchuraBtn = (159 * getWidth() / 626);
				int nuevaAlturaBtn = (30 * getWidth() / 626);
				int nuevaAnchuraCb = (150 * getWidth() / 626);
				int nuevaAlturaCb = (22 * getWidth() / 626);
				int ladoIconoH = (int) Math.round(100 * getWidth() / 626);
				int ladoIconoV = (int) Math.round(100 * getHeight() * 1.36 / 626);
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
				// imageIconL = new ImageIcon(Inicio.class.getResource());
				Image imagenResizL = imageIconL.getImage();
				Image iResizeoL = imagenResizL.getScaledInstance(ladoIconoH, ladoIconoV, java.awt.Image.SCALE_SMOOTH); // scale
																														// it
																														// the
																														// smooth
																														// way
				ImageIcon iiResizeoL = new ImageIcon(iResizeoL);
				lblEqL.setIcon(iiResizeoL);
				lblEqL.setBounds((int) Math.round(((getWidth() / 2) - (getWidth() * 0.29))),
						(int) Math.round(((getHeight() / 2) - (getHeight() * 0.25))), ladoIconoH, ladoIconoV);
				lblEqL.repaint();
				String localizacion = equipoV;
				ImageIcon imageIconV = new ImageIcon(Inicio.class.getResource(localizacion));
				Image imagenResizV = imageIconV.getImage();
				Image iResizeoV = imagenResizV.getScaledInstance(ladoIconoH, ladoIconoV, java.awt.Image.SCALE_SMOOTH); // scale
																														// it
																														// the
																														// smooth
																														// way
				ImageIcon iiResizeoV = new ImageIcon(iResizeoV);
				lblEqV.setIcon(iiResizeoV);
				lblEqV.setBounds((int) Math.round(((getWidth() / 2) + (getWidth() * 0.02))),
						(int) Math.round(((getHeight() / 2) - (getHeight() * 0.25))), ladoIconoH, ladoIconoV);
				lblEqV.repaint();
				revalidate();
			}
		});
	}
	
}
