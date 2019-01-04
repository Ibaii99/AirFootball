package ventanas;

import javax.swing.JFrame;

import entidades.BaseDeDatos;
import fisicas.FisicasNuevas;
import objetos.ScrollablePanel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class GuardarEnArcade extends JFrame {
	private JTextField textField;
	private JTable table;

	public GuardarEnArcade(BaseDeDatos bd, FisicasNuevas fisicas, int ganadosArcade) {
		setSize(474, 203);

		ScrollablePanel panelClasificacion = new ScrollablePanel(new BorderLayout());

		panelClasificacion.setScrollableWidth(ScrollablePanel.ScrollableSizeHint.FIT);
		panelClasificacion.setScrollableHeight(ScrollablePanel.ScrollableSizeHint.STRETCH);
		getContentPane().add(panelClasificacion, BorderLayout.CENTER);
		DefaultTableModel model = new DefaultTableModel();
		
		table = new JTable(model);
		model.addColumn("Usuario");
		model.addColumn("Partidas ganadas");
		String header[] = {"Usuario", "Partidos ganados"};
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column1 = table.getTableHeader().getColumnModel().getColumn(i);
			column1.setHeaderValue(header[i]);
		}
		table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD));
		anadirATabla(model, bd);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(table);
		panelClasificacion.add(scrollPane);
		getContentPane().add(panelClasificacion);
		
		JPanel panelNorte = new JPanel();
		getContentPane().add(panelNorte, BorderLayout.NORTH);
						JLabel lblUsuario = new JLabel("Usuario:");
						panelNorte.add(lblUsuario);
				
						textField = new JTextField();
						panelNorte.add(textField);
						textField.setColumns(10);
		
				JButton btnGuardar = new JButton("Guardar");
				panelNorte.add(btnGuardar);
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							bd.init();
							bd.getCon().createStatement().executeUpdate("INSERT INTO Arcade(Usuario, 'Partidos ganados') "
									+ "VALUES ('" + textField.getText() + "', " + ganadosArcade + ");");
							bd.close();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "NO SE HA PODIDO INTRODUCIR A LA BASE DE DATOS", "ERROR",
									JOptionPane.WARNING_MESSAGE);
							e1.printStackTrace();
						}
						Inicio i = new Inicio(bd, fisicas);
						i.setVisible(true);
						dispose();
					}
				});
		setVisible(true);
	}

	private void anadirATabla(DefaultTableModel model, BaseDeDatos bd) {
		try {
			bd.init();
			String query = "SELECT * FROM ARCADE ORDER BY 'Partidos Ganados' DESC;";
			ResultSet rs = bd.getCon().createStatement().executeQuery(query);
			while (rs.next()) {
				String nombre = rs.getString("Usuario");
				int ganados = rs.getInt("Partidos Ganados");
				model.addRow(new Object[] { nombre, ganados });
			}
			rs.close();
			bd.close();
		} catch (Exception eee) {
			eee.printStackTrace();
		}

	}
}
