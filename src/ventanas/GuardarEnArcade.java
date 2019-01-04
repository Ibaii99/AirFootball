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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class GuardarEnArcade extends JFrame {
	private JTextField textField;
	private JTable table;

	public GuardarEnArcade(BaseDeDatos bd, FisicasNuevas fisicas, int ganadosArcade) {
		setSize(474, 203);
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
		
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Usuario");
		model.addColumn("Partidas ganadas");
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(49))))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}
