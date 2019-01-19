package ventanas;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import objetos.ObjetoCombobox;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import jugador.Jugador;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuLiga extends JFrame {
	/**
	 * @author Jorge
	 * @throws SQLException
	 * @author ibai
	 */
	public String equipoL;
	public ImageIcon imageIconL;
	private int codLiga;

	public MenuLiga(int anchura, int altura, Jugador j, BaseDeDatos bd, FisicasNuevas f, int cod) {
		this.codLiga = cod;
		try {

			Thread.sleep(500);
			JLabel lblBck = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/iconos/stadiumLiga2.png"))));
			setContentPane(lblBck);

			try {
				setSize(590, 578);
			} catch (Exception e) {
				setSize(500, 500);
			}
			bd.init();
			JComboBox cbLiga = new JComboBox();

			Logger logger = Logger.getLogger("menuLiga");
			logger.addHandler(new FileHandler("menuLiga"));

			for (Equipo eq : bd.devolverTodosLosEquipos(j, codLiga)) {

				cbLiga.addItem(
						new ObjetoCombobox(1, eq.getNombre(), new ImageIcon("/" + eq.getBolaEquipo().getRutaImagen())));
				revalidate();
			}

			JLabel lblEligeEquipo = new JLabel("Elige equipo:");
			// TODO
			// TODO

			JButton btnIniciarLiga = new JButton("Iniciar liga");
			btnIniciarLiga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						bd.init();

						Equipo equipo = bd.convertirAEquipo(cbLiga.getSelectedItem().toString(), j, codLiga);
						System.out.println(equipo.getNombre());
						System.out.println(bd);

						String ruta = j.getNombre().toUpperCase() + "codLiga=" + codLiga + ".txt";

						File archivo = new File(ruta);

						BufferedWriter bw;
						// Escribe El equipo del Jugador
						try {
							if (!archivo.exists()) {
								bw = new BufferedWriter(new FileWriter(archivo));
								bw.write(equipo.getNombre());
								bw.flush();
								bw.close();
							}
						} catch (Exception i) {
							i.printStackTrace();
						}
						// Escribe los rivales que tiene

						String rutaPart = j.getNombre() + codLiga + "Partidos.txt";
						j.setCodLiga(codLiga);

						File archivoPart = new File(rutaPart);
						try {
							if (!archivoPart.exists()) {
								bw = new BufferedWriter(new FileWriter(archivoPart));
								ArrayList<Equipo> listaequipos = bd.devolverTodosLosEquipos(j, codLiga);
								for(int cont = 0; cont < listaequipos.size() ; cont++) {
									if(listaequipos.get(cont).getNombre().equals(cbLiga.getSelectedItem().toString()) )listaequipos.remove(cont);
								}
								Collections.shuffle(listaequipos);
								// Para la primera vuelta
								for (Equipo equi : listaequipos) {
									if (equi.getNombre() != cbLiga.getSelectedItem().toString())
										bw.write(equi.getNombre() + "\n"); // para que se guarden todos los equipos
																			// menos el que tu has elegido
								}
								// Para la segunda vuelta
								for (Equipo equi : listaequipos) {
									if (equi.getNombre() != cbLiga.getSelectedItem().toString())
										bw.write(equi.getNombre() + "\n"); // para que se guarden todos los equipos
																			// menos el que tu has elegido
								}
								bw.flush();
								bw.close();
								VentanaLiga v = new VentanaLiga(equipo, bd, j, listaequipos, f, codLiga);
								v.setVisible(true);

							}
						} catch (Exception i) {
							i.printStackTrace();
						}

						dispose();

						// TODO añadir el codliga y crear el archivo

					} catch (Exception e1) {

					}
				}
			});
			lblEligeEquipo.setFont(new Font("Arial Black", Font.PLAIN, Math.round(17 * getWidth() / 630)));
			btnIniciarLiga.setFont(new Font("Arial Black", Font.PLAIN, Math.round(13 * getWidth() / 630)));

			JButton btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Inicio i = new Inicio(bd, f);
					i.setSize(660, 480);
					i.setVisible(true);
					dispose();
					try {
						bd.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnVolver.setFont(new Font("Arial Black", Font.PLAIN, 13));

			JLabel icono = new JLabel("");
			int xIcono = Math.round((getWidth() / 2) - (182 * getWidth() / 630));
			int yIcono = Math.round((getHeight() / 2) - 96 * getHeight() / 630);

			equipoL = "/iconos/equipos/ala.png";
			String newequipoL = "";
			imageIconL = new ImageIcon(getClass().getResource(equipoL));
			icono.setIcon(imageIconL);
			cbLiga.addActionListener(new ActionListener() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 * 
				 */
				public void actionPerformed(ActionEvent arg0) {
					try {

						equipoL = "/" + bd.convertirAEquipo(cbLiga.getSelectedItem().toString(), j, codLiga)
								.getBolaEquipo().getRutaImagen();
						System.out.println(equipoL);
						revalidate();

						resizeo(equipoL, icono);

					} catch (Exception e) {
						e.printStackTrace();
					}
					revalidate();
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup().addGap(47)
											.addComponent(icono, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cbLiga, 0, 247, Short.MAX_VALUE).addGap(76))
									.addGroup(groupLayout.createSequentialGroup().addContainerGap()
											.addComponent(btnIniciarLiga, GroupLayout.DEFAULT_SIZE, 367,
													Short.MAX_VALUE)
											.addGap(90).addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 97,
													GroupLayout.PREFERRED_SIZE)))
							.addGap(6))
					.addGroup(groupLayout.createSequentialGroup().addGap(226)
							.addComponent(lblEligeEquipo, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE).addGap(216)));
			groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
					.createSequentialGroup().addGap(40)
					.addComponent(lblEligeEquipo, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup().addGap(47).addComponent(cbLiga,
									GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(icono, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup().addGap(104)
									.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE).addGap(10))
							.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnIniciarLiga).addGap(54)))));
			getContentPane().addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					resizeo(equipoL, icono);
					icono.setSize(cbLiga.getHeight(), cbLiga.getHeight());
					lblEligeEquipo.setFont(new Font("Arial Black", Font.PLAIN, Math.round(17 * getWidth() / 630)));
					btnIniciarLiga.setFont(new Font("Arial Black", Font.PLAIN, Math.round(13 * getWidth() / 630)));
					cbLiga.setFont(new Font("Arial Black", Font.PLAIN, Math.round(14 * getWidth() / 630)));
					revalidate();
					icono.repaint();
				}
			});
			getContentPane().setLayout(groupLayout);
		} catch (Exception excep) {
			excep.printStackTrace();
		}
		try {
			bd.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param equipo:
	 *            Ruta de la imagen que queremos resizear
	 * @param icono:
	 *            JLabel donde se encuentra el icono del equipo
	 */
	public void resizeo(String equipo, JLabel icono) {
		System.out.println(equipo);
		imageIconL = new ImageIcon(getClass().getResource(equipo));

		Image imagenResizL = imageIconL.getImage();
		Image iResizeoL = imagenResizL.getScaledInstance((int) Math.round(200 * getWidth() / 600),
				(int) Math.round(200 * getHeight() / 600), java.awt.Image.SCALE_SMOOTH); // scale it the
																							// smooth way
		ImageIcon iiResizeoL = new ImageIcon(iResizeoL);
		icono.setSize(iiResizeoL.getIconWidth(), iiResizeoL.getIconHeight());
		icono.setIcon(iiResizeoL);
//		super.paintComponents(getGraphics());
	}
}
