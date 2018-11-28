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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.BaseDeDatos;
import entidades.Equipo;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JPanel;

public class MenuLiga extends JFrame {
	/**
	 * @author Jorge
	 * @throws SQLException
	 * @author ibai
	 */
	public String equipoL;
	public ImageIcon imageIconL;

	public MenuLiga(int anchura, int altura) throws SQLException, Exception {
		JLabel lblBck = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/iconos/stadiumLiga2.png"))));
		setContentPane(lblBck);

		try {
			setSize(590, 578);
		} catch (Exception e) {
			setSize(500, 500);
		}

		JComboBox cbLiga = new JComboBox();

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
		con.close();
		JLabel lblEligeEquipo = new JLabel("Elige equipo:");
		//TODO
		//TODO
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddhhmmss" );
		JButton btnIniciarLiga = new JButton("Iniciar liga");
		btnIniciarLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date d = new java.util.Date();
				try {
				BaseDeDatos base = new BaseDeDatos(sdf.format( d ), cbLiga.getSelectedItem().toString());
				base.crearTablaLiga();
				Class.forName("org.sqlite.JDBC");
				Connection con1 = DriverManager.getConnection("jdbc:sqlite:airHockey.db");
				System.out.println(cbLiga.getSelectedItem().toString());
				String query1 = "SELECT * FROM EQUIPOS WHERE NOMBRE='" +cbLiga.getSelectedItem().toString() + "'; ";
				System.out.println(query1+"asdafad");
				ResultSet rs1 = con1.createStatement().executeQuery(query1);
				
				Equipo equipo = base.convertirAEquipo(rs1);
				con1.close();
				rs1.close();
				VentanaLiga v = new VentanaLiga(equipo);
				v.setVisible(true);
				setVisible(false);
				
				
				
			
				} catch (Exception e1) {
					
					}
					}});
		lblEligeEquipo.setFont(new Font("Arial Black", Font.PLAIN, Math.round(17 * getWidth() / 630)));
		btnIniciarLiga.setFont(new Font("Arial Black", Font.PLAIN, Math.round(13 * getWidth() / 630)));

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

		JLabel icono = new JLabel("");
		int xIcono = Math.round((getWidth() / 2) - (182 * getWidth() / 630));
		int yIcono = Math.round((getHeight() / 2) - 96 * getHeight() / 630);
		equipoL = "/iconos/equipos/ala.png";
		String newequipoL = "";
		imageIconL = new ImageIcon(getClass().getResource(equipoL));
		icono.setIcon(imageIconL);
		cbLiga.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 * 
			 */
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
					resizeo(equipoL, icono);
				} catch (Exception e) {
					e.printStackTrace();
				}
				revalidate();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(icono, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbLiga, 0, 247, Short.MAX_VALUE)
							.addGap(76))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnIniciarLiga, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
							.addGap(90)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(6))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(226)
					.addComponent(lblEligeEquipo, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addGap(216))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblEligeEquipo, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(cbLiga, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(icono, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(104)
							.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnIniciarLiga)
							.addGap(54))))
		);
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
		
	}
	/**
	 * @param equipo: Ruta de la imagen que queremos resizear
	 * @param icono: JLabel donde se encuentra el icono del equipo
	 */
	public void resizeo(String equipo, JLabel icono) {
		imageIconL = new ImageIcon(getClass().getResource(equipo));
		Image imagenResizL = imageIconL.getImage();
		Image iResizeoL = imagenResizL.getScaledInstance((int) Math.round(200 * getWidth() / 600),
				(int) Math.round(200 * getHeight() / 600), java.awt.Image.SCALE_SMOOTH); // scale it the
																							// smooth way
		ImageIcon iiResizeoL = new ImageIcon(iResizeoL);
		icono.setSize(iiResizeoL.getIconWidth(), iiResizeoL.getIconHeight());
		icono.setIcon(iiResizeoL);
	}
}
