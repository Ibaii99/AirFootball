package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import entidades.BaseDeDatos;
import fisicas.FisicasNuevas;
import jugador.Jugador;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CargarCrear extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public CargarCrear(Jugador j, FisicasNuevas f, BaseDeDatos bd) {
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
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter txt = new FileNameExtensionFilter("Archivos(.txt)", "txt");
				fc.addChoosableFileFilter(txt);
				fc.setFileFilter(txt);
				int seleccion = fc.showOpenDialog(getContentPane());
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					System.out.println(fichero.getPath());
					System.out.println(fichero.getName());
					
					try {
						FileReader lector=new FileReader(fichero.getName());
						BufferedReader contenido=new BufferedReader(lector);
						String[] a = fc.getSelectedFile().getPath().toString().split("codLiga=");
						String[] u = a[1].split(".");
						int codLigas = Integer.parseInt(u[0]);
						VentanaLiga vL = new VentanaLiga(bd.convertirAEquipo(contenido.readLine(), j,codLigas ), bd, j, j.devolverPartidosRestantes(codLigas, bd),f);
						setVisible(false);
						vL.setVisible(true);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				fc.setBounds(600, 100, 600, 300);
				fc.setVisible(true);
			}
		});
		
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.anyadirTodosLosEquipos(j.getCodLiga(), j);
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
