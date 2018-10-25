package ventanas;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

public class MenuAmistoso extends JFrame {
	/**
	 * @author Jorge
	 *
	 */
	public MenuAmistoso() {
		setSize(630,460);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(157, 222, 31, 22); 
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(373, 222, 31, 22);
		getContentPane().add(comboBox_1);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblLocal.setBounds(157, 203, 42, 16);
		getContentPane().add(lblLocal); 
		
		JLabel lblVisitante = new JLabel("Visitante");
		lblVisitante.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblVisitante.setBounds(356, 203, 65, 16);
		getContentPane().add(lblVisitante);
		
		JButton btnIniciarAmistoso = new JButton("Iniciar amistoso");
		btnIniciarAmistoso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Partido partido = new Partido(); //aquí meter quizá un constructor entre equipos
			}
		});
		btnIniciarAmistoso.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnIniciarAmistoso.setBounds(209, 317, 170, 25);
		getContentPane().add(btnIniciarAmistoso);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 612, 413);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 612, 413);
		panel.add(lblFondo);
		lblFondo.setIcon(new ImageIcon(MenuAmistoso.class.getResource("/iconos/stadiumAmistoso.png")));
		setVisible(true);
	}
}
