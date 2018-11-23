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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class MenuLiga extends JFrame {
	/**
	 * @author Jorge
	 * @throws SQLException
	 *
	 */
	public String equipoL;
	public ImageIcon imageIconL;
	public MenuLiga(int anchura, int altura) throws SQLException {
		
		try {
			setSize(anchura, anchura);
		} catch (Exception e) {
			setSize(500, 500);
		}
		getContentPane().setLayout(null);

		JComboBox cbLiga = new JComboBox();

		cbLiga.setBounds(Math.round((getWidth()/2)-(75*getWidth()/630)), Math.round(getHeight()/2), Math.round(157*getWidth()/630), Math.round(22*getWidth()/630));

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
			cbLiga.addItem(new ObjetoCombobox(1, nomEq, new ImageIcon(iconEq)));
			// System.out.println(nomEq + " " + iconEq);
		}
		// st.close();
		rs.close();

		JLabel lblEligeEquipo = new JLabel("Elige equipo:");
		lblEligeEquipo.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblEligeEquipo.setBounds(Math.round((getWidth()/2)-(61*getWidth()/630)), Math.round((getHeight()/2)-(208*getHeight()/630)), 123, 29);
		getContentPane().add(lblEligeEquipo);

		JButton btnIniciarLiga = new JButton("Iniciar liga");
		btnIniciarLiga.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnIniciarLiga.setBounds(182, 273, 112, 25);
		getContentPane().add(btnIniciarLiga);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio i = new Inicio();
				i.setSize(660, 480);
				i.setVisible(true);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnVolver.setBounds(Math.round(getWidth()-(130*getWidth()/630)), Math.round(getHeight()-(100*getHeight()/630)), 97, 25);
	//	btnVolver.setBounds(373, 415, 97, 25);
		getContentPane().add(btnVolver);

		JLabel icono = new JLabel("");
		int xIcono = Math.round(182*getWidth()/630);
		int yIcono = Math.round(96*getHeight()/630);
		icono.setBounds(xIcono, yIcono, 112, 112);
		equipoL = "/iconos/equipos/ala.png";
		String newequipoL = "";
		imageIconL = new ImageIcon(getClass().getResource(equipoL));
		icono.setIcon(imageIconL);
		cbLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Connection conCB = null;
					Class.forName("org.sqlite.JDBC");
					conCB = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
					Statement consultaCB;
					consultaCB = conCB.createStatement();
					String comando2 = "SELECT ICONO FROM EQUIPOS WHERE NOMBRE = '" + cbLiga.getSelectedItem().toString()
							+ "'";
					logger.log(Level.INFO, "BD: " + comando2);
					consultaCB.executeUpdate(comando2);
					ResultSet rs2 = conCB.createStatement().executeQuery(comando2);
					System.out.println(rs2.toString());
					while (rs2.next()) {
						equipoL = "/" + rs2.getString("ICONO");
						String iconoL = rs2.getString("ICONO");
						System.out.println(equipoL);
					}
					imageIconL = new ImageIcon(getClass().getResource(equipoL));
					Image imagenResizL = imageIconL.getImage();
					Image iResizeoL = imagenResizL.getScaledInstance((int) Math.round(112 * getWidth() / 600),
							(int) Math.round(112 * getWidth() / 600), java.awt.Image.SCALE_SMOOTH); // scale it the
																									// smooth way
					ImageIcon iiResizeoL = new ImageIcon(iResizeoL);
					icono.setIcon(iiResizeoL);

				} catch (Exception e) {
					e.printStackTrace();
				}
				revalidate();
			}
		});
		getContentPane().add(cbLiga);
		getContentPane().add(icono);

	}
}
