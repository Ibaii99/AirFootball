package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.BaseDeDatos;
import fisicas.FisicasNuevas;
import jugador.Jugador;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class VentanaLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegister;
	private JButton btnLogin;
	private JTextField tFNombre;
	private JPasswordField passwordField;
	private Jugador j;
	private BaseDeDatos bd;
	private FisicasNuevas f;

	public VentanaLogin(BaseDeDatos bd, FisicasNuevas f, boolean isModoLiga) {
		this.bd = bd;
		this.f = f;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNombreTEXT = new JLabel("Nombre:");

		JLabel lblContrasea = new JLabel("Contraseña:");

		tFNombre = new JTextField();
		tFNombre.setColumns(10);

		passwordField = new JPasswordField();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
								.addComponent(lblNombreTEXT, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(tFNombre)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
						.addGap(130)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(64).addComponent(tFNombre,
										GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(68).addComponent(lblNombreTEXT,
										GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(20).addComponent(lblContrasea,
										GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
						.addGap(39)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegister = new JButton("Register");
				btnRegister.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						try {
							bd.init();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String name = tFNombre.getText().toUpperCase();
						// SI el jugador esta entre los nombres de los jugadores de la bd dara error
						if (bd.estaJugadorRegistrado(name)) {
							JOptionPane.showMessageDialog(null, "ESTE JUGADOR YA ESTA REGISTRADO", "ERROR",
									JOptionPane.WARNING_MESSAGE);
						}
						// SI el jugador no esta entre los jugadores de la BD entonces se creara un
						// nuevo objeto Jugador
						if (!bd.estaJugadorRegistrado(name)) {
							j = new Jugador(name, passwordField.getPassword());
							try {
								FileWriter fw = new FileWriter("src/user_properties/" + j.getNombre() + ".properties");
								BufferedWriter writer = new BufferedWriter(fw);
								writer.write("usuario.nombre=" + name);
								writer.write("\r\n");
								writer.write("usuario.pass=" + passwordField.getText());
								writer.close();
							} catch (IOException e3) {
								e3.printStackTrace();
							}
							bd.anyadirJugador(j);

							bd.crearTablaEquipos(j);

							// TODO
							setVisible(false);
							if (isModoLiga) {
								siguienteTransicion();
							} else if (!isModoLiga) {
								VentanaCreacion vc = new VentanaCreacion(bd, f, null, 0, j);
								vc.setVisible(true);
								setVisible(false);
							}

						}
						try {
							bd.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
			}

			{
				btnLogin = new JButton("Login");
				btnLogin.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						try {
							bd.init();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String name = tFNombre.getText().toUpperCase();
						// SI el jugador no esta registrado con ese nombre y contraseña dara error
						if (!bd.estaJugadorEnBaseDeDatos(name, passwordField.getPassword())) {
							JOptionPane.showMessageDialog(null,
									"ESTE JUGADOR NO ESTA REGISTRADO O LA CONTRASEÑA ES ERRONEA", "ERROR",
									JOptionPane.WARNING_MESSAGE);

						}
						if (bd.estaJugadorEnBaseDeDatos(name, passwordField.getPassword())) {
							j = bd.convertirAJugador(name, passwordField.getPassword());

							setVisible(false);
							if (isModoLiga) {
								siguienteTransicion();
							} else if (!isModoLiga) {
								VentanaCreacion vc = new VentanaCreacion(bd, f, null, 0, j);
								vc.setVisible(true);
								setVisible(false);
							}
						}
						try {
							bd.close();
						} catch (SQLException e1) { // EXCEPCION BD
							// TODO Auto-generated catch block
							// e1.printStackTrace();
						}

					}

				});
			}

			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
							.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addGap(5)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(5)
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE).addComponent(btnRegister)
									.addComponent(btnLogin))));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	private void siguienteTransicion() {
		// esto arrancaria menu liga para volver a elegir equipos pero esto solo se
		// tiene que hacer para crear una nueva liga
		CargarCrear cG = new CargarCrear(j, f, bd);
		cG.setVisible(true);
		setVisible(false);

	}
}
