package ventanas;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entidades.BaseDeDatos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.SQLException;

public class Inicio extends JFrame {
	/**
	 * @author Jorge
	 */

	private static final long serialVersionUID = 1L;

	public Inicio(BaseDeDatos bd, Connection con) {
		setSize(630, 460);
		getContentPane().setLayout(new BorderLayout(0, 0));

		final JPanel panel = new JPanel();

		panel.setLayout(null);

		final JButton bLiga = new JButton("Liga");
		bLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					VentanaLogin v = new VentanaLogin(bd, con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				dispose();
			}
		});
		bLiga.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bLiga.setBounds(getWidth() - 150, getHeight() - 100, 119, 40);
		panel.add(bLiga);

		final JButton bCreacion = new JButton("Creaci�n");
		bCreacion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bCreacion.setBounds(getWidth() - 150, getHeight() - 150, 119, 40);
		panel.add(bCreacion);

		final JButton bAmistoso = new JButton("Amistoso");
		bAmistoso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MenuAmistoso mu;
				try {
					mu = new MenuAmistoso(getSize().width, getSize().height);
					mu.setVisible(true);
				} catch (Exception e1) {
					MenuAmistoso muAlt;
					try {
						muAlt = new MenuAmistoso(630, 460);
						muAlt.setVisible(true);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}

			}
		});
		bAmistoso.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bAmistoso.setBounds(getWidth() - 150, getHeight() - 250, 119, 40);
		panel.add(bAmistoso);

		final JButton bArcade = new JButton("Arcade");
		bArcade.setFont(new Font("Arial Black", Font.PLAIN, 16));
		bArcade.setBounds(getWidth() - 150, getHeight() - 200, 119, 40);
		panel.add(bArcade);

		final JLabel lPortada = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(Inicio.class.getResource("/iconos/PORTADA.jpg"));
		Image image = imageIcon.getImage();

		Image newimg = image.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the
																										// smooth way
		imageIcon = new ImageIcon(newimg);

		lPortada.setIcon(imageIcon);
		lPortada.setBounds(0, 0, this.getWidth(), this.getHeight());
		panel.add(lPortada);

		getContentPane().add(panel);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {

				bLiga.setBounds(getWidth() - 150, getHeight() - 100, 119, 40);
				bCreacion.setBounds(getWidth() - 150, getHeight() - 150, 119, 40);
				bAmistoso.setBounds(getWidth() - 150, getHeight() - 250, 119, 40);
				bArcade.setBounds(getWidth() - 150, getHeight() - 200, 119, 40);
				ImageIcon imageIconR = new ImageIcon(Inicio.class.getResource("/iconos/PORTADA.jpg"));
				Image imagenResiz = imageIconR.getImage();

				Image iResizeo = imagenResiz.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH); // scale
																														// it
																														// the
																														// smooth
																														// way
				ImageIcon iiResizeo = new ImageIcon(iResizeo);

				lPortada.setIcon(iiResizeo);
				lPortada.setBounds(0, 0, getWidth(), getHeight());
				getContentPane().revalidate();
				getContentPane().setSize(getSize());
			}
		});
	}
}
