package ventanas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class Partido extends JFrame {
	/** @author Jorge 
	 * 
	 */

	public Partido() {
		setSize(750, 500);
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(null);
		ImageIcon iconL = new ImageIcon(Partido.class.getResource("/iconos/equipos/atl.png"));
		Image imgL = iconL.getImage();
		BufferedImage biL = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
		Graphics gL = biL.createGraphics();
		gL.drawImage(imgL, 0, 0, 40, 40, null);
		ImageIcon newIconL = new ImageIcon(biL);
		
		ImageIcon iconC = new ImageIcon(Partido.class.getResource("/iconos/campo.png"));
		Image imgC = iconC.getImage();
		BufferedImage biC = new BufferedImage(720, 395, BufferedImage.TYPE_INT_ARGB);
		Graphics gC = biC.createGraphics();
		gC.drawImage(imgC, 0, 0, 720, 395, null);
		ImageIcon newIconC = new ImageIcon(biC);

		ImageIcon iconV = new ImageIcon(Partido.class.getResource("/iconos/equipos/ath.png"));
		Image imgV = iconV.getImage();
		BufferedImage biV = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
		Graphics gV = biV.createGraphics();
		gV.drawImage(imgV, 0, 0, 40, 40, null);
		ImageIcon newIconV = new ImageIcon(biV);

		JLabel lblEscudoL = new JLabel("");
		lblEscudoL.setBounds(164, 13, 40, 40);
		lblEscudoL.setIcon(newIconL);
		getContentPane().add(lblEscudoL);
		JLabel lblEscudoV = new JLabel("");
		lblEscudoV.setBounds(532, 13, 40, 40);
		lblEscudoV.setIcon(newIconV);
		getContentPane().add(lblEscudoV);

		JPanel panel = new JPanel();
		panel.setBounds(182, 13, 373, 40);
		getContentPane().add(panel);
		ImageIcon icon = new ImageIcon(Partido.class.getResource("/iconos/marcadorconnombres.jpg"));
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(373, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 373, 50, null);
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
		
		JLabel lCampo = new JLabel("");
		lCampo.setIcon(newIconC);
		lCampo.setBounds(12, 57, 720, 395);
		getContentPane().add(lCampo);

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
