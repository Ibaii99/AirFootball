package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.BaseDeDatos;
import fisicas.FisicasNuevas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;

public class ConfirmacionSalirLiga extends JDialog {

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public ConfirmacionSalirLiga(BaseDeDatos bd, FisicasNuevas f, VentanaLiga v) {
		
		setTitle("ATENCIÓN");
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JTextArea txtrEstaAPunto = new JTextArea();
		txtrEstaAPunto.setEditable(false);
		txtrEstaAPunto.setBackground(null);
		txtrEstaAPunto.setFont(new Font("Liberation Sans", Font.BOLD, 18));
		txtrEstaAPunto.setText("Esta a punto de salir, por favor guarde su \nprogreso o será borrado. \n\nPresione: \n          Ok para continuar y salir \n          Cancel para volver a la ventana liga");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(txtrEstaAPunto, GroupLayout.PREFERRED_SIZE, 422, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(txtrEstaAPunto, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						v.ocultarVentanaLiga();
						setVisible(false);
						Inicio i = new Inicio(bd, f);
						i.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
