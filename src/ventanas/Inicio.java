package ventanas;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Inicio extends JFrame {
	/**
	 * @author Jorge
	 */
	private static final long serialVersionUID = 1L;

	public Inicio() {
		getContentPane().setLayout(null);

		JButton bLiga = new JButton("Liga");
		bLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuLiga ml = new MenuLiga();
				ml.setSize(500, 500);
				ml.setVisible(true);
				dispose();
			}
		});
		bLiga.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bLiga.setBounds(502, 304, 119, 40);
		getContentPane().add(bLiga);

		JButton bCreacion = new JButton("Creación");
		bCreacion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bCreacion.setBounds(502, 357, 119, 40);
		getContentPane().add(bCreacion);

		JButton bAmistoso = new JButton("Amistoso");
		bAmistoso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAmistoso mu = new MenuAmistoso();
				dispose();
			}
		});
		bAmistoso.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bAmistoso.setBounds(502, 251, 119, 40);
		getContentPane().add(bAmistoso);

		JButton bArcade = new JButton("Arcade");
		bArcade.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bArcade.setBounds(502, 198, 119, 40);
		getContentPane().add(bArcade);
		
		JLabel lPortada = new JLabel("");
		lPortada.setIcon(new ImageIcon(Inicio.class.getResource("/iconos/PORTADA.jpg")));
		lPortada.setBounds(0, 0, 642, 433);
		getContentPane().add(lPortada);
	}

	public static void main(String[] args) {
		Inicio i = new Inicio();
		i.setSize(630, 460);
		i.setResizable(false);
		i.setVisible(true);
	}
}
