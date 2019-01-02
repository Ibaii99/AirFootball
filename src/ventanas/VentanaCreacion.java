package ventanas;

import javax.swing.JFrame;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import jugador.Jugador;
import objetos.ObjetoCombobox;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class VentanaCreacion extends JFrame {
	private JTextField tfNombre;
	private JTextField tfSiglas;
	private JTextField tfUsuario;
	private JPasswordField passwordField;

	public VentanaCreacion(BaseDeDatos bd, FisicasNuevas f) {
		JFileChooser fc = new JFileChooser();
		setSize(550, 239);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("Nombre:");

		tfNombre = new JTextField();

		tfNombre.setColumns(10);

		JLabel lblEscudo = new JLabel("Escudo:");

		JButton btnExaminar = new JButton("Examinar...");

		JLabel lblSiglas = new JLabel("Siglas:");

		tfSiglas = new JTextField();
		tfSiglas.setDocument(new JTextFieldLimit(3));
		tfSiglas.setColumns(10);

		JLabel lblSustituirAEquipo = new JLabel("Sustituir a equipo:");

		JComboBox cbSustituye = new JComboBox();
		try {
			Logger logger = Logger.getLogger("baseDeDatos");

			Statement consulta;

			String comando = "";
			try {
				Class.forName("org.sqlite.JDBC");
				bd.init();
			} catch (Exception e3) {
				// e3.printStackTrace();
			}
			String query = "SELECT NOMBRE FROM EQUIPOS;";
			ResultSet rs = bd.getCon().createStatement().executeQuery(query);
			while (rs.next()) {
				String nomEq = rs.getString("Nombre");
				cbSustituye.addItem(new ObjetoCombobox(1, nomEq, null));
			}
			rs.close();
			bd.close();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		JLabel lblLocalizacionIcono = new JLabel("");

		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileNameExtensionFilter png = new FileNameExtensionFilter("Imágenes(.png)", "png");
				fc.addChoosableFileFilter(png);
				FileNameExtensionFilter jpg = new FileNameExtensionFilter("Imágenes(.jpg)", "jpg");
				fc.addChoosableFileFilter(jpg);
				FileNameExtensionFilter jpeg = new FileNameExtensionFilter("Imágenes(.jpeg)", "jpeg");
				fc.addChoosableFileFilter(jpeg);
				fc.setFileFilter(png);
				int seleccion = fc.showOpenDialog(getContentPane());
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					System.out.println(fichero.getPath());
					System.out.println(fichero.getName());
					lblLocalizacionIcono.setText(fichero.getName());
				}
				fc.setBounds(600, 100, 600, 300);
				fc.setVisible(true);
			}
		});

		JLabel lblUsuario = new JLabel("Usuario:");

		tfUsuario = new JTextField();
		tfUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");

		passwordField = new JPasswordField();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(34).addComponent(tfUsuario, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEscudo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176,
												Short.MAX_VALUE)
										.addComponent(lblNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176,
												Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(tfNombre, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(btnExaminar, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblLocalizacionIcono, GroupLayout.PREFERRED_SIZE, 131,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblSiglas, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfSiglas, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
								.addComponent(lblSustituirAEquipo, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
								.addGap(18).addComponent(cbSustituye, 0, 242, Short.MAX_VALUE)))
				.addGap(106)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(5)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(tfNombre)
										.addGroup(gl_panel.createSequentialGroup().addGap(6).addComponent(lblNombre,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblEscudo).addGap(15))
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblLocalizacionIcono, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
												.addComponent(btnExaminar, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(10)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfSiglas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSiglas))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSustituirAEquipo, GroupLayout.DEFAULT_SIZE, 22,
												Short.MAX_VALUE)
										.addComponent(cbSustituye))
						.addGap(6).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblUsuario)
								.addComponent(tfUsuario).addComponent(lblContrasea).addComponent(passwordField))));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JButton btnVolver = new JButton("Volver");
		panel_1.add(btnVolver);

		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bd.init();
				} catch (Exception e3) {

				}
				File source = fc.getSelectedFile();
				Path destino = null; // Universalizamos el selector de imagen. Todo icono escogido se mueve a SRC
										// para que otro usuario pueda acceder a él desde otro ordenador.
				try {
					destino = Paths.get("src\\iconos\\equipos\\", source.getName());
					Files.copy(source.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				Equipo e1 = new Equipo(tfSiglas.getText(), tfNombre.getText(), 0, Color.black,
						"iconos/equipos/" + lblLocalizacionIcono.getName(),
						"iconos/equipos/" + lblLocalizacionIcono.getName(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0);
				
				if (!bd.estaJugadorEnBaseDeDatos(tfUsuario.getText(), passwordField.getPassword())) {
					JOptionPane.showMessageDialog(null, "ESTE JUGADOR NO ESTA REGISTRADO O LA CONTRASEÑA ES ERRONEA",
							"ERROR", JOptionPane.WARNING_MESSAGE);
					System.out.println("No registrado");
				}
				if (bd.estaJugadorEnBaseDeDatos(tfUsuario.getText(), passwordField.getPassword())) {
					Jugador j = bd.convertirAJugador(tfUsuario.getText(), passwordField.getPassword());
					try {

						eliminarEquipo(bd, cbSustituye.getSelectedItem().toString(), j, cbSustituye);
						anyadirEquipo(bd, e1, j);
						VentanaLiga vl = new VentanaLiga(e1, bd, j);
						vl.setVisible(true);
					} catch (SQLException e2) {

					}
				}
				try {
					bd.close();
				} catch (Exception eee) {
					eee.printStackTrace();
				}
			}
		});
		panel_1.add(btnSiguiente);

	}

	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

	public void eliminarEquipo(BaseDeDatos bd, String nombre, Jugador j, JComboBox cb) {
		try {
			Class.forName("org.sqlite.JDBC");
			bd.init();
			String query = "DELETE FROM EQUIPOS" + j.getNombre() + " WHERE NOMBRE = '" + cb.getSelectedItem().toString()
					+ "';";
			System.out.println(query);
			bd.getCon().createStatement().executeUpdate(query); // executeUpdate para inserts y deletes y executeQuery
																// para selects
			bd.close();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}

	public void anyadirEquipo(BaseDeDatos bd, Equipo e, Jugador j) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			bd.init();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		String comando = "INSERT INTO Equipos" + j.getNombre()
				+ " ('fk_CodLiga', 'fk_Nombre_Jugador','Siglas', 'Nombre', 'Puntos', 'Goles Encajados Totales', 'Goles Encajados Local', 'Goles Encajados Visitante', 'Goles A Favor Totales', 'Goles A Favor Local', 'Goles A Favor Visitante', 'Derrotas Totales', 'Derrotas Local', 'Derrotas Visitante', 'Victorias Totales', 'Victorias Local', 'Victorias Visitante', 'Empates Totales', 'Empates Local', 'Empates Visitante', 'Color', 'Icono')";
		comando += " VALUES ('" + 1 + "','" + j.getNombre() + "','" + e.getSiglas() + "','" + e.getNombre() // mira aqui
																											// bien lo
																											// de
																											// CodLiga
																											// lo he
																											// puesto a
																											// 0
				+ "','" + e.getPuntos() + "','" + e.getGolesEnContraTotales() + "','" + e.getGolesEnContraLocal()
				+ "','" + e.getGolesEnContraVisitante() + "','" + e.getGolesAFavorTotales() + "','"
				+ e.getGolesAFavorLocal() + "','" + e.getGolesAFavorVisitante() + "','" + e.getDerrotasTotales() + "','"
				+ e.getDerrotasLocal() + "','" + e.getDerrotasVisitante() + "','" + e.getVictoriasTotales() + "','"
				+ e.getVictoriasLocal() + "','" + e.getVictoriasVisitante() + "','" + e.getEmpatesTotales() + "','"
				+ e.getEmpatesLocal() + "','" + e.getEmpatesVisitante() + "','" + e.getBolaEquipo().getColor().getRGB()
				+ "','" + e.getImagen() + "')";
		System.out.println(comando);
		ResultSet rs = bd.getCon().createStatement().executeQuery(comando);
		bd.close();

	}

}

class JTextFieldLimit extends PlainDocument {
	private int limit;

	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}
