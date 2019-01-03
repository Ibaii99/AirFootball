package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.BaseDeDatos;
import fisicas.FisicasNuevas;
import jugador.Jugador;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CargarGuardar extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public CargarGuardar(Jugador j, FisicasNuevas f, BaseDeDatos bd) {
		setBounds(100, 100, 487, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panelBotones, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panelBotones, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
		);
		
		JButton btnCargar = new JButton("Cargar");
		
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuLiga m = new MenuLiga(700, 700, j, bd, f);
				m.setVisible(true);
				setVisible(false);
			}
		});
		GroupLayout gl_panelBotones = new GroupLayout(panelBotones);
		gl_panelBotones.setHorizontalGroup(
			gl_panelBotones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotones.createSequentialGroup()
					.addComponent(btnCargar, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNuevaPartida, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
		);
		gl_panelBotones.setVerticalGroup(
			gl_panelBotones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotones.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelBotones.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNuevaPartida, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
						.addComponent(btnCargar, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
		);
		panelBotones.setLayout(gl_panelBotones);
		contentPanel.setLayout(gl_contentPanel);
	}
}
