package ventanas;

import javax.swing.JFrame;

import entidades.BaseDeDatos;
import fisicas.FisicasNuevas;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuardarEnArcade extends JFrame {
	private JTextField textField;

	public GuardarEnArcade(BaseDeDatos bd, FisicasNuevas fisicas, int ganadosArcade) {
		setSize(300, 150);
		JLabel lblUsuario = new JLabel("Usuario:"); 
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				bd.init();
				
				bd.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 71, Short.MAX_VALUE)
					.addGap(147))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(textField)
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(215))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}
