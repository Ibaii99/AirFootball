package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import entidades.BaseDeDatos;
import entidades.Equipo;
import jugador.Jugador;
import objetos.MyTableCellRender;
import objetos.ObjetoCombobox;
import objetos.ScrollablePanel;

import javax.swing.JLabel;
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
		model.addColumn("Nombre");
		model.addColumn("Puntos");
		model.addColumn("Victorias");
		model.addColumn("Empates");
		model.addColumn("Derrotas");
		model.addColumn("Goles a favor");
		model.addColumn("Goles en contra");
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		String header[] = { "Nombre", "Puntos", "Victorias", "Empates", "Derrotas", "Goles a favor",
				"Goles en contra" };

		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column1 = table.getTableHeader().getColumnModel().getColumn(i);
			column1.setHeaderValue(header[i]);
		}
		table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD));
		
		anadirATabla(bd, j);
		
		// model.setRowColour(1, Color.BLUE);

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

		JLabel lblEscudoEquipo = new JLabel("New label");
		panelEquipoJugador.add(lblEscudoEquipo);

		JLabel lblNombEquipo = new JLabel("New label");
		panelEquipoJugador.add(lblNombEquipo);

		JLabel lblTEXTPuntosEquipo = new JLabel("Puntos:");
		panelEquipoJugador.add(lblTEXTPuntosEquipo);

		JLabel lblPuntosEquipo = new JLabel("New label");
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

	private void anadirATabla(BaseDeDatos bd, Jugador j) throws SQLException {
		try {
			
			table.setDefaultRenderer(Object.class, new MyTableCellRender());
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
				model.addRow(new Object[] { nombre, puntos, victorias, empates, derrotas, gf, gc });

			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
