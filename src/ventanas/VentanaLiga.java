package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import entidades.BaseDeDatos;
import entidades.Equipo;
import jugador.Jugador;
import objetos.ObjetoCombobox;
import objetos.ScrollablePanel;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.ScrollPane;

public class VentanaLiga extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Jugador j;
	private DefaultTableModel model;

	private BaseDeDatos bd;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public VentanaLiga(Equipo e, BaseDeDatos bd, Jugador j) throws SQLException {
		try {
			bd.init();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		ScrollablePanel panelClasificacion = new ScrollablePanel(new BorderLayout());
		panelClasificacion.setScrollableWidth(ScrollablePanel.ScrollableSizeHint.FIT);
		panelClasificacion.setScrollableHeight(ScrollablePanel.ScrollableSizeHint.STRETCH);
		contentPane.add(panelClasificacion, BorderLayout.CENTER);
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("");
		model.addColumn("Nombre");
		model.addColumn("Puntos");
		model.addColumn("Victorias");
		model.addColumn("Empates");
		model.addColumn("Derrotas");
		model.addColumn("Goles a favor");
		model.addColumn("Goles en contra");
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		String header[] = { "", "Nombre", "Puntos", "Victorias", "Empates", "Derrotas", "Goles a favor",
				"Goles en contra" };

		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column1 = table.getTableHeader().getColumnModel().getColumn(i);
			column1.setHeaderValue(header[i]);
		}
		table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD));
		// String iUCL = "iconos/UCL.png";
		// String iEL = "iconos/EUROPA.png";
		// String iDesc = "iconos/DESCENSO.png";
		anadirATabla(bd, j, e);

		// try {
		// for (int i = 0; i < 20; i++) {
		// if (i < 4) {
		// ImageIcon icon = new
		// ImageIcon(getClass().getClassLoader().getResource(iUCL));
		// // table.getColumnModel().getColumn(0).setCellRenderer(new UCLRenderer());
		// table.setValueAt(icon, i, 0);
		// } else if (i >= 4 && i < 7) {
		// ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iEL));
		// // table.getColumnModel().getColumn(0).setCellRenderer(new EuropaRenderer());
		// table.setValueAt(icon, i, 0);
		// } else if (i >= 17) {
		// ImageIcon icon = new
		// ImageIcon(getClass().getClassLoader().getResource(iDesc));
		// // table.getColumnModel().getColumn(0).setCellRenderer(new
		// DescensoRenderer());
		// table.setValueAt(icon, i, 0);
		// }
		//
		// }
		// // table.setDefaultRenderer(Object.class, new IconTableCellRenderer());
		// } catch (Exception ee) {
		// ee.printStackTrace();
		// }

		JScrollPane scrollPane = new JScrollPane(table);
		panelClasificacion.add(scrollPane);

		JPanel panelBotonera = new JPanel();
		contentPane.add(panelBotonera, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar");
		panelBotonera.add(btnGuardar);

		JButton btnCargar = new JButton("Cargar");
		panelBotonera.add(btnCargar);

		JPanel panelInformacionEquipo = new JPanel();
		contentPane.add(panelInformacionEquipo, BorderLayout.NORTH);

		JLabel lblProximoEvento = new JLabel("Proximo Evento: ");
		panelInformacionEquipo.add(lblProximoEvento);

		JPanel panelEquipoJugador = new JPanel();
		panelInformacionEquipo.add(panelEquipoJugador);

		JLabel lblEscudoEquipo = new JLabel();
		ImageIcon imageIconL = null;
		try {
			imageIconL = new ImageIcon(getClass().getClassLoader().getResource(e.getBolaEquipo().getRutaImagen()));
		} catch (Exception ej) {
			ej.printStackTrace();
		}
		resizeo(imageIconL, lblEscudoEquipo, 20, 20);
		panelEquipoJugador.add(lblEscudoEquipo);

		JLabel lblNombEquipo = new JLabel(e.getNombre());
		panelEquipoJugador.add(lblNombEquipo);

		JLabel lblTEXTPuntosEquipo = new JLabel("Puntos: ");
		panelEquipoJugador.add(lblTEXTPuntosEquipo);

		JLabel lblPuntosEquipo = new JLabel("" + e.getPuntos());
		panelEquipoJugador.add(lblPuntosEquipo);

		JLabel lblVs = new JLabel("VS");
		panelInformacionEquipo.add(lblVs);

		JPanel panelEquipoAdversario = new JPanel();
		panelInformacionEquipo.add(panelEquipoAdversario);

		JLabel lblEscudoAdversario = new JLabel("New label");
		panelEquipoAdversario.add(lblEscudoAdversario);

		JLabel lblNombAdversario = new JLabel("New label");
		panelEquipoAdversario.add(lblNombAdversario);

		JLabel lblTEXTPuntosAdversario = new JLabel("Puntos:");
		panelEquipoAdversario.add(lblTEXTPuntosAdversario);

		JLabel lblPuntosAdversario = new JLabel("New label");
		panelEquipoAdversario.add(lblPuntosAdversario);

		JButton btnPlay = new JButton("Play");
		panelInformacionEquipo.add(btnPlay);
		try {
			bd.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void anadirATabla(BaseDeDatos bd, Jugador j, Equipo e) throws SQLException {
		try {
			TeamBold tb = new TeamBold();
			System.out.println(bd.getNombre());
			Statement consulta;
			String comando = "";
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			String query = "SELECT * FROM EQUIPOS" + j.getNombre() + ";";
			ResultSet rs = bd.getCon().createStatement().executeQuery(query);
			while (rs.next()) {

				String nombre = rs.getString("Nombre");
				int puntos = rs.getInt("Puntos");
				int victorias = rs.getInt("Victorias totales");
				int empates = rs.getInt("Empates totales");
				int derrotas = rs.getInt("Derrotas totales");
				int gf = rs.getInt("Goles a favor totales");
				int gc = rs.getInt("Goles encajados totales");
				model.addRow(new Object[] { "", nombre, puntos, victorias, empates, derrotas, gf, gc });

			}
			rs.close();
			String query2 = "SELECT NOMBRE FROM EQUIPOS" + j.getNombre() + " WHERE NOMBRE = '" + e.getNombre() + "';";

			ResultSet rs2 = bd.getCon().createStatement().executeQuery(query2);
			System.out.println(rs2);
			String nombre = null;
			table.setDefaultRenderer(Object.class, tb);
			while (rs2.next()) {
				tb.setNombre(rs2.getString("Nombre"));
			}

			rs2.close();

		} catch (Exception en) {
			en.printStackTrace();
		}

	}

	public void resizeo(ImageIcon imageIconL, JLabel jlIcono, int anchura, int altura) {
		Image imagenResizL = imageIconL.getImage();
		Image iResizeoL = imagenResizL.getScaledInstance((int) Math.round(anchura), (int) Math.round(altura),
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon iiResizeoL = new ImageIcon(iResizeoL);
		jlIcono.setSize(iiResizeoL.getIconWidth(), iiResizeoL.getIconHeight());
		jlIcono.setIcon(iiResizeoL);
		jlIcono.repaint();
		revalidate();
	}

}

class TeamBold extends DefaultTableCellRenderer {
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel parent = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		JLabel parent2 = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, 0);
		
		if (value.equals(nombre)) {
			parent.setFont(parent.getFont().deriveFont(Font.BOLD));
		}
		//System.out.println(parent.toString());
		if (row >= 0 && row < 4 && column == 0) {

			parent.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iconos/UCL.png")));
			parent2.setText("" + (row + 1));
			
			parent.setBackground(Color.BLUE);
			parent.setForeground(Color.WHITE);
		} else if (row >= 4 && row < 6 && column == 0) {
			parent.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iconos/EUROPA.png")));
			parent2.setText("" + (row + 1));
			parent.setBackground(Color.orange);
			parent.setForeground(Color.BLACK);
		} else if (row >= 17 && row < 20 && column == 0) {
			parent.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iconos/DESCENSO.png")));
			parent2.setText("" + (row + 1));
			parent.setBackground(new Color(153,0,0));
			parent.setForeground(Color.WHITE);
		} else if (column == 0) {
			parent.setIcon(null);
			parent2.setText("" + (row + 1));
			parent.setForeground(Color.black);
			parent.setBackground(Color.white);
		} else { 
			parent.setIcon(null);
			parent.setForeground(Color.black);
			parent.setBackground(Color.white);
		}
		return parent;
	}

}
