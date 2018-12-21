package objetos;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidades.BaseDeDatos;
import jugador.Jugador;

public class MyTableCellRender extends DefaultTableCellRenderer {
	/**
	 * @author Jorge
	 */
	private static final long serialVersionUID = 1L;

	public MyTableCellRender() {
		super();
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		// if(Integer.parseInt((String)table.getValueAt(row, 0)) <= 4)
		// {
		// setForeground(Color.black);
		// setBackground(Color.red);
		// }
		
		try {
//			if (value.equals(e.getNombre())) {
//				
//			}
			if (row < 4) { //De momento muestra los equipos clasificados para Champions.
				setForeground(Color.black);
				setBackground(Color.cyan);
			}
			
//			if (row > 3 && row < 6) {
//				setForeground(Color.black);
//				setBackground(Color.orange);
//			}
//			if (row >= 17) {
//				setForeground(Color.black);
//				setBackground(Color.red);
//			}
//
			else {
				setBackground(Color.white);
				setForeground(Color.black);
			}

			
		} catch (Exception s) {
			s.printStackTrace();
		}
		return this;
	}
}