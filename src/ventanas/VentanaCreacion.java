package ventanas;

import javax.swing.JFrame;

import entidades.BaseDeDatos;
import fisicas.FisicasNuevas;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class VentanaCreacion extends JFrame {
	private JTextField tfNombre;
	private JTextField tfSiglas;

	public VentanaCreacion(BaseDeDatos bd, FisicasNuevas f) {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		JLabel lblEscudo = new JLabel("Escudo:");
		
		JButton btnExaminar = new JButton("Examinar...");
		
		JLabel lblSiglas = new JLabel("Siglas:");
		
		tfSiglas = new JTextField();
		tfSiglas.setColumns(10);
		
		JLabel lblSustituirAEquipo = new JLabel("Sustituir a equipo:");
		
		JComboBox comboBox = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEscudo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(lblSiglas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(lblNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(lblSustituirAEquipo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tfSiglas, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnExaminar, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
							.addGap(19))
						.addComponent(tfNombre, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
						.addComponent(comboBox, Alignment.TRAILING, 0, 152, Short.MAX_VALUE))
					.addGap(115))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExaminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblEscudo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSiglas)
						.addComponent(lblSiglas))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSustituirAEquipo, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(comboBox))
					.addGap(90))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JButton btnVolver = new JButton("Volver");
		panel_1.add(btnVolver);
		
		JButton btnSiguiente = new JButton("Siguiente");
		panel_1.add(btnSiguiente);
		
	}
}
