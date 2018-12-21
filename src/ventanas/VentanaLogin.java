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


	public  VentanaLogin(BaseDeDatos bd, FisicasNuevas f) {
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
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addComponent(lblNombreTEXT, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(tFNombre)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
					.addGap(130))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(64)
							.addComponent(tFNombre, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(68)
							.addComponent(lblNombreTEXT, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
					.addGap(39))
		);
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
						//SI el jugador esta entre los nombres de los jugadores de la bd dara error
						if(bd.estaJugadorRegistrado(name)) {
							JOptionPane.showMessageDialog(null, "ESTE JUGADOR YA ESTA REGISTRADO", "ERROR", JOptionPane.WARNING_MESSAGE);
						}
						//SI el jugador no esta entre los jugadores de la BD entonces se creara un nuevo objeto Jugador
						if(!bd.estaJugadorRegistrado(name)) {
							Jugador j = new Jugador(name, passwordField.getPassword(), 0);
							
							bd.anyadirJugador(j);
							bd.crearTablaEquipos(j);
							bd.anyadirTodosLosEquipos(j.getCodLiga(), j);
							//TODO
							setVisible(false);
							MenuLiga mL = new MenuLiga(800, 800, j, bd, f);
							mL.setVisible(true);
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
						if(!bd.estaJugadorEnBaseDeDatos(name, passwordField.getPassword())) {
							JOptionPane.showMessageDialog(null, "ESTE JUGADOR NO ESTA REGISTRADO O LA CONTRASEÑA ES ERRONEA", "ERROR", JOptionPane.WARNING_MESSAGE);
						}
						if(bd.estaJugadorEnBaseDeDatos(name, passwordField.getPassword())) {
							Jugador j = bd.convertirAJugador(name, passwordField.getPassword());
							//TODO
							setVisible(false);
							MenuLiga mL = new MenuLiga(800, 800, j, bd,f);
							mL.setVisible(true);
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
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
						.addGap(5))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnLogin)
							.addComponent(btnRegister)))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
}
