package ventanas;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuLiga extends JFrame {
	public MenuLiga() {
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(223, 237, 31, 22);
		getContentPane().add(comboBox);
		
		JLabel lblEligeEquipo = new JLabel("Elige equipo:");
		lblEligeEquipo.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblEligeEquipo.setBounds(182, 208, 123, 29);
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
		btnVolver.setBounds(373, 415, 97, 25);
		getContentPane().add(btnVolver);
	}
}
