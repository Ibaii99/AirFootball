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
		panel.setBounds(182, 13, 373, 40);
		getContentPane().add(panel);
		ImageIcon icon = new ImageIcon(Partido.class.getResource("/iconos/marcadorconnombres.jpg"));
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(373,50,
				BufferedImage.TYPE_INT_ARGB); 
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 373,50, null);
		ImageIcon newIcon = new ImageIcon(bi);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("0");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("DSEG14 Classic", Font.PLAIN, 28));
		label_2.setBounds(153, 5, 23, 32);
		panel.add(label_2);
		
		JLabel label_1 = new JLabel("0");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("DSEG14 Classic", Font.PLAIN, 28));
		label_1.setBounds(190, 5, 23, 32);
		panel.add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(newIcon);
		label.setBounds(0, 0, 373, 51);
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


