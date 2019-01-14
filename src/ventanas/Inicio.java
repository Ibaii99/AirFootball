package ventanas;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import jugador.Jugador;
import objetos.ObjetoCombobox;
import objetos.Pelota;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inicio extends JFrame {
	/**
	 * @author Jorge
	 */

	private static final long serialVersionUID = 1L;

	public Inicio(BaseDeDatos bd, FisicasNuevas f) {
		setSize(630, 460);
		getContentPane().setLayout(new BorderLayout(0, 0));

		final JPanel panel = new JPanel();

		panel.setLayout(null);

		final JButton bLiga = new JButton("Liga");
		bLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					VentanaLogin v = new VentanaLogin(bd, f, true);
					v.setVisible(true);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);

				dispose();
			}
		});
		bLiga.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bLiga.setBounds(getWidth() - 150, getHeight() - 100, 119, 40);
		panel.add(bLiga);

		final JButton bCreacion = new JButton("Creaci�n");
		bCreacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaLogin v = new VentanaLogin(bd, f, false);
					v.setVisible(true);

				} catch (Exception i) {
					// TODO Auto-generated catch block
					i.printStackTrace();
				}
				setVisible(false);

				dispose();
			}
		});
		bCreacion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bCreacion.setBounds(getWidth() - 150, getHeight() - 150, 119, 40);
		panel.add(bCreacion);

		final JButton bAmistoso = new JButton("Amistoso");
		bAmistoso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuAmistoso mu;
				try {
					mu = new MenuAmistoso(getSize().width, getSize().height, bd, f);
					mu.setVisible(true);
				} catch (Exception e1) {
					MenuAmistoso muAlt;
					try {
						muAlt = new MenuAmistoso(630, 460, bd, f);
						muAlt.setVisible(true);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}
				dispose();
			}
		});
		bAmistoso.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bAmistoso.setBounds(getWidth() - 150, getHeight() - 250, 119, 40);
		panel.add(bAmistoso);

		final JButton bArcade = new JButton("Arcade");
		bArcade.addActionListener(new ActionListener() {
			private Equipo eVisitante;
			private Equipo eLocal;

			public void actionPerformed(ActionEvent arg0) {
				try {
					bd.init();
					String crearTabla = "CREATE TABLE ARCADE ( " + "codPartida INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "Usuario TEXT NOT NULL," + "'Partidos ganados' INTEGER " + ");";
					bd.getCon().createStatement().executeUpdate(crearTabla);

					bd.close();
				} catch (Exception ff) {
//					ff.printStackTrace();
				}
				empiezaPartidoArcade(bd, eLocal, eVisitante, f, 0);
				dispose();
			}
		});
		bArcade.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bArcade.setBounds(getWidth() - 150, getHeight() - 200, 119, 40);
		panel.add(bArcade);

		final JLabel lPortada = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(Inicio.class.getResource("/iconos/PORTADA.jpg"));
		Image image = imageIcon.getImage();

		Image newimg = image.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the
																										// smooth way
		imageIcon = new ImageIcon(newimg);

		lPortada.setIcon(imageIcon);
		lPortada.setBounds(0, 0, this.getWidth(), this.getHeight());
		panel.add(lPortada);

		getContentPane().add(panel);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {

				bLiga.setBounds(getWidth() - 150, getHeight() - 100, 119, 40);
				bCreacion.setBounds(getWidth() - 150, getHeight() - 150, 119, 40);
				bAmistoso.setBounds(getWidth() - 150, getHeight() - 250, 119, 40);
				bArcade.setBounds(getWidth() - 150, getHeight() - 200, 119, 40);
				ImageIcon imageIconR = new ImageIcon(Inicio.class.getResource("/iconos/PORTADA.jpg"));
				Image imagenResiz = imageIconR.getImage();

				Image iResizeo = imagenResiz.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon iiResizeo = new ImageIcon(iResizeo);

				lPortada.setIcon(iiResizeo);
				lPortada.setBounds(0, 0, getWidth(), getHeight());
				getContentPane().revalidate();
				getContentPane().setSize(getSize());
			}
		});
	}

//	public static void main(String[] args) {
//		BaseDeDatos bd = new BaseDeDatos("BETA");
//		Inicio i;
//		try {
//			bd.init();
//			i = new Inicio(bd, new FisicasNuevas());
//			System.out.println(bd.getNombre());
//			i.setVisible(true);
//			bd.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
// 
//	}

	/**
	 * M�todo que declaramos fuera debido a que lo utilizaremos directamente desde
	 * ventanaPartido cuando termine un partido.
	 * 
	 * @param bd
	 *            baseDeDatos a pasar
	 * @param eLocal
	 *            equipo local
	 * @param eVisitante
	 *            equipo visitante
	 * @param f
	 *            f�sicas
	 */
	public void empiezaPartidoArcade(BaseDeDatos bd, Equipo eLocal, Equipo eVisitante, FisicasNuevas f, int ganados) {
		try {
			eLocal = equipoRandom(bd, eLocal);
			eVisitante = equipoRandom(bd, eVisitante);
			Pelota pelotaPartido = new Pelota(Color.white, "pelota", 20);
			ventanaPartido partido = new ventanaPartido(eLocal, eVisitante, pelotaPartido, true, true, false, false, f,
					bd, null, ganados, null,0);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			partido.empieza();
			dispose();
		} catch (Exception eee) {
			eee.printStackTrace();
		}
	}

	/**
	 * Metodo recursivo que crea equipos al azar
	 * 
	 * @param bd
	 *            base de datos a analizar
	 * @param e
	 *            equipo que queremos que se genere aleatoriamente
	 */
	public Equipo equipoRandom(BaseDeDatos bd, Equipo e) {
		try {
			bd.init();
			String query2 = "SELECT NOMBRE FROM EQUIPOS ORDER BY RANDOM() LIMIT 1;";
			ResultSet rs2 = bd.getCon().createStatement().executeQuery(query2);
			while (rs2.next()) {
				String nomEq = rs2.getString("Nombre");
				char[] a = { 'a', 'b', 'c' };
				e = bd.convertirAEquipo(nomEq, new Jugador("", a, 0), 0);
				 System.out.println(e.getNombre());

			}

			rs2.close();
			bd.close();
		} catch (Exception t) {

		}
		return e;
	}
}
