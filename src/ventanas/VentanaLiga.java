package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidades.BaseDeDatos;
import entidades.Equipo;
import jugador.Jugador;
import objetos.ObjetoCombobox;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.ScrollPane;

public class VentanaLiga extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private BaseDeDatos bd;
	private Jugador j;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 */
	public VentanaLiga(Equipo e, BaseDeDatos bd, Jugador j) {
		System.out.println(e.getNombre());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelClasificacion = new JPanel();
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
		
		anadirATabla();

		panelClasificacion.add(table);

		ScrollPane scrollPane = new ScrollPane();
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
	}

	private void anadirATabla() {
		System.out.println(bd);
//		try {
//			bd.init();
//			Statement consulta;
//			String comando = "";
//			try {
//				Class.forName("org.sqlite.JDBC");
//			} catch (Exception e3) {
//				 e3.printStackTrace();
//			}
//			String query = "SELECT * FROM EQUIPOS" + j.getNombre() + ";";
//			System.out.println(query);
//			ResultSet rs = bd.getCon().createStatement().executeQuery(query);
//			System.out.println(rs.toString());
//			System.out.println(rs);
//			System.out.println(rs.getString("NOMBRE"));
//			
//			while (rs.next()) {
//
//				String nombre = rs.getString("Nombre");
//				int puntos = rs.getInt("Puntos");
//				int victorias = rs.getInt("Victorias totales");
//				int empates = rs.getInt("Empates totales");
//				int derrotas = rs.getInt("Derrotas totales");
//				int gf = rs.getInt("Goles a favor");
//				int gc = rs.getInt("Goles en contra");
//				model.addRow(new Object[] { nombre, puntos, victorias, empates, derrotas, gf, gc });
//				
//			}
//			rs.close();
//			bd.close();
//			model.addRow(new Object[] {""});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
