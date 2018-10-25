package ventanas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class Partido extends JFrame {
	public Partido() {
		setSize(750,500);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(322, 13, 80, 45);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 5, 80, 45);
		ImageIcon icon = new ImageIcon(Partido.class.getResource("/iconos/marcador.jpg"));
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(80,45,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 80, 45, null);
		ImageIcon newIcon = new ImageIcon(bi);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("0");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("DSEG14 Classic", Font.PLAIN, 28));
		label_2.setBounds(10, 10, 23, 32);
		panel.add(label_2);
		
		JLabel label_1 = new JLabel("0");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("DSEG14 Classic", Font.PLAIN, 28));
		label_1.setBounds(47, 10, 23, 32);
		panel.add(label_1);
		label.setIcon(newIcon);
		panel.add(label);
	}
	boolean amistoso;
	public boolean isAmistoso() {
		return amistoso;
	}
	public void setAmistoso(boolean amistoso) {
		this.amistoso = amistoso;
	}
	public boolean isLiga() {
		return liga;
	}
	public void setLiga(boolean liga) {
		this.liga = liga;
	}
	public boolean isArcade() {
		return arcade;
	}
	public void setArcade(boolean arcade) {
		this.arcade = arcade;
	}
	boolean liga;
	boolean arcade;
	public static void main(String[] args) {
		Partido p = new Partido();
		p.setVisible(true);
		
	}
}


