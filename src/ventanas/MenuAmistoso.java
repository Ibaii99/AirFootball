package ventanas;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MenuAmistoso extends JFrame {
	/**
	 * @author Jorge
	 *
	 */
	public int anchura;
	public int altura;
	public int getAnchura() {
		return anchura;
	}
	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	

	public MenuAmistoso(int anchura, int altura) {
		setSize(anchura, altura);
		getContentPane().setLayout(null);
		ImageIcon imageIcon = new ImageIcon(MenuAmistoso.class.getResource("/iconos/stadiumAmistoso.png"));
		Image image = imageIcon.getImage();
		
		Image newimg = image.getScaledInstance(anchura, altura,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon icRsz = new ImageIcon(newimg); 
		
		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 612, 413);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(160, 223, 31, 22);
		panel.add(comboBox);
		
		JButton btnIniciarAmistoso = new JButton("Iniciar amistoso");
		btnIniciarAmistoso.setBounds(207, 271, 170, 25);
		panel.add(btnIniciarAmistoso);
		btnIniciarAmistoso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Partido partido = new Partido(); //aquí meter quizá un constructor entre equipos
			}
		});
		btnIniciarAmistoso.setFont(new Font("Arial Black", Font.PLAIN, 13));
		
		JLabel lblVisitante = new JLabel("Visitante");
		lblVisitante.setBounds(356, 203, 65, 16);
		panel.add(lblVisitante);
		lblVisitante.setFont(new Font("Arial Black", Font.PLAIN, 13));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(375, 224, 31, 22);
		panel.add(comboBox_1);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(155, 204, 42, 16);
		panel.add(lblLocal);
		lblLocal.setFont(new Font("Arial Black", Font.PLAIN, 13));
		
		
		final JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 0, 0);
		panel.add(lblFondo);
		
			lblFondo.setIcon(icRsz);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				ImageIcon imageIconR = new ImageIcon(Inicio.class.getResource("/iconos/stadiumAmistoso.png"));
				Image imagenResiz = imageIconR.getImage();
				
				Image iResizeo = imagenResiz.getScaledInstance(getWidth(), getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				ImageIcon iiResizeo2 = new ImageIcon(iResizeo); 
				panel.setSize(getSize());
				lblFondo.setIcon(iiResizeo2);
				lblFondo.setBounds(0, 0, getWidth(), getHeight());
				getContentPane().revalidate();
				getContentPane().setSize(getSize());
			}
		});
	}
}
