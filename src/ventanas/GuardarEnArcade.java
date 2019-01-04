package ventanas;

import javax.swing.JFrame;

import entidades.BaseDeDatos;
import fisicas.FisicasNuevas;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class GuardarEnArcade extends JFrame {
	private JTextField textField;

	public GuardarEnArcade(BaseDeDatos bd, FisicasNuevas fisicas, int ganadosArcade) {
		setSize(436, 150);
		JLabel lblUsuario = new JLabel("Usuario:"); 
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				try {
				bd.init();
				bd.getCon().createStatement().executeUpdate("INSERT INTO Arcade(Usuario, 'Partidos ganados') " + "VALUES ('"+ textField.getText() + "', " + ganadosArcade + ");");
				bd.close();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "NO SE HA PODIDO INTRODUCIR A LA BASE DE DATOS",
							"ERROR", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				Inicio i = new Inicio(bd, fisicas);
				i.setVisible(true);
				dispose();
			}
		});
		
		JList list = new JList();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addGap(97))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(405, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(9)
									.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(27)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}
