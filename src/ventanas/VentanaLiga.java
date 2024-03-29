package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import fisicas.FisicasNuevas;
import jugador.Jugador;
import objetos.ObjetoCombobox;
import objetos.Pelota;
import objetos.ScrollablePanel;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaLiga extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Jugador j;
	private DefaultTableModel model;
	private JLabel background;
	private BaseDeDatos bd;
	private ArrayList<Equipo> listaPartidos;
	private Equipo eLocal;
	private Equipo eVisitante;
	private boolean isJugadorLocal;
	private VentanaLiga ventana;
	private int ligaSeleccionada;
	
	/** Ventana con la clasificacion de nuestra liga y los datos de nuestros equipos, y la proxima jornada
	 * @param e Equipo
	 * @param bd Base de datos a pasar
	 * @param j Jugador
	 * @param listaPartidos Arraylist de jornadas de nuestro equipo
	 * @param f Fisicas 
	 * @param ligaSeleccionada Codigo de liga seleccionada
	 * @throws SQLException
	 */
	public VentanaLiga(Equipo e, BaseDeDatos bd, Jugador j, ArrayList<Equipo> listaPartidos, FisicasNuevas f, int ligaSeleccionada) throws SQLException {
		this.ligaSeleccionada = ligaSeleccionada;
		ventana = this;
		this.listaPartidos = listaPartidos;
		// La primera ronda de partidos se juega en casa
	
		try {
			bd.init();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 572);
		contentPane = new JPanel();
		JLabel background_1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("iconos/anoeta.png")));
		setContentPane(background_1);
		// getContentPane().add(background_1);
		background_1.setLayout(null);
		((JComponent) getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(new BorderLayout(0, 0));
		// setContentPane(contentPane);
		// getContentPane().add(background);
		ScrollablePanel panelClasificacion = new ScrollablePanel(new BorderLayout());

		panelClasificacion.setScrollableWidth(ScrollablePanel.ScrollableSizeHint.FIT);
		panelClasificacion.setScrollableHeight(ScrollablePanel.ScrollableSizeHint.STRETCH);
		getContentPane().add(panelClasificacion, BorderLayout.CENTER);
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

		anadirATabla(bd, j, e,ligaSeleccionada);

		System.out.println(listaPartidos.get(0));
		

		JScrollPane scrollPane = new JScrollPane(table);
		panelClasificacion.add(scrollPane);

		JPanel panelBotonera = new JPanel();
		getContentPane().add(panelBotonera, BorderLayout.SOUTH);
 		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Liga guardada con �xito");
			}
		});
		panelBotonera.add(btnGuardar);
		
		JButton btnSalirAlMenu = new JButton("Salir al menu");
		btnSalirAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmacionSalirLiga cS = new ConfirmacionSalirLiga(bd, f, ventana);
				cS.setVisible(true);
				
				
			}
		});
		panelBotonera.add(btnSalirAlMenu);

		JPanel panelInformacionEquipo = new JPanel();

		getContentPane().add(panelInformacionEquipo, BorderLayout.NORTH);

		JLabel lblProximoEvento = new JLabel("Proximo Evento: ");
		panelInformacionEquipo.add(lblProximoEvento);

		JPanel panelEquipoJugador = new JPanel();
		panelInformacionEquipo.add(panelEquipoJugador);

		JLabel lblEscudoEquipo = new JLabel();
		ImageIcon imageIconL = null;

		panelEquipoJugador.add(lblEscudoEquipo);
		String nombreEqLocal = "";


		JLabel lblTEXTPuntosEquipo = new JLabel("Puntos: ");
		panelEquipoJugador.add(lblTEXTPuntosEquipo);

		int puntosLocal = 0;
	
		

		JLabel lblVs = new JLabel("VS");
		panelInformacionEquipo.add(lblVs);

		JPanel panelEquipoAdversario = new JPanel();
		panelInformacionEquipo.add(panelEquipoAdversario);
 
		String nombreEqVisitante ;
		JLabel lblEscudoAdversario = new JLabel();
		ImageIcon imageIconV = null;
		int puntosVisit = 0;
		
		try {
			if(listaPartidos.size() >= 19) {
				isJugadorLocal = true;
				
				nombreEqLocal = e.getNombre();
				eLocal = e;
				imageIconL = new ImageIcon(getClass().getClassLoader().getResource(e.getBolaEquipo().getRutaImagen()));
				puntosLocal = e.getPuntos(); 
				
				nombreEqVisitante = listaPartidos.get(0).getNombre();
				eVisitante = bd.convertirAEquipo(nombreEqVisitante, j, ligaSeleccionada);
				puntosVisit = eVisitante.getPuntos();
				imageIconV = new ImageIcon(getClass().getClassLoader().getResource(eVisitante.getBolaEquipo().getRutaImagen()));
			
				resizeo(imageIconL, lblEscudoEquipo, 20, 20);
				resizeo(imageIconV, lblEscudoAdversario, 20, 20);
			} else if(listaPartidos.size() < 19) {
				isJugadorLocal = false;
				
				nombreEqLocal = listaPartidos.get(0).getNombre();
				eLocal = bd.convertirAEquipo(nombreEqLocal, j, ligaSeleccionada);
				puntosLocal = eLocal.getPuntos();
				imageIconL = new ImageIcon(getClass().getClassLoader().getResource(eLocal.getBolaEquipo().getRutaImagen()));
				
				nombreEqVisitante = e.getNombre();
				eVisitante = e;
				puntosVisit = e.getPuntos(); //intercambiamos si jugamos de visitante
				imageIconV = new ImageIcon(getClass().getClassLoader().getResource(e.getBolaEquipo().getRutaImagen()));
				
				resizeo(imageIconL, lblEscudoEquipo, 20, 20);
				resizeo(imageIconV, lblEscudoAdversario, 20, 20);
			
			}
			System.out.println("Partidos restantes "+listaPartidos.size());
			System.out.println(imageIconL);
			System.out.println(imageIconV);
		} catch (Exception ej) {
			ej.printStackTrace();
		}

		JLabel lblPuntosEquipo = new JLabel("" + puntosLocal); 
		panelEquipoJugador.add(lblPuntosEquipo);
		
		JLabel lblNombEquipo = new JLabel(nombreEqLocal);
		panelEquipoJugador.add(lblNombEquipo);
		
		panelEquipoJugador.add(lblEscudoEquipo);
		panelEquipoAdversario.add(lblEscudoAdversario);

		JLabel lblNombAdversario = new JLabel(eVisitante.getNombre());
		panelEquipoAdversario.add(lblNombAdversario);

		JLabel lblTEXTPuntosAdversario = new JLabel("Puntos:");
		panelEquipoAdversario.add(lblTEXTPuntosAdversario);

		JLabel lblPuntosAdversario = new JLabel("" + puntosVisit);
		panelEquipoAdversario.add(lblPuntosAdversario);

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (e.getDerrotasTotales() + e.getVictoriasTotales() + e.getEmpatesTotales() == 38) {
					JOptionPane.showMessageDialog(null, "YA HAS TERMINADO LA LIGA", "ERROR",
							JOptionPane.WARNING_MESSAGE);
				}else {
					ventanaPartido vp = new ventanaPartido(eLocal, eVisitante, new Pelota(Color.BLUE, "", 20), true, false, false, false, f, bd, j, 0, listaPartidos, ligaSeleccionada);
					vp.empieza();
					vp.setVisible(true);
					dispose();
				}
			}
		});
		panelInformacionEquipo.add(btnPlay);
		try {
			bd.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// JLabel background_1 = new JLabel(new
		// ImageIcon(getClass().getClassLoader().getResource("iconos/anoeta.png")));
		// setContentPane(background_1);
		//// getContentPane().add(background_1);
		// background_1.setLayout(null);
		
	}

	/**
	 * M�todo burbuja para a�adir a la tabla a la que se pasa la base de datos, el jugador, y el equipo escogido
	 * @param bd Base de datos
	 * @param j Jugador
	 * @param e Equipo escogido
	 * @throws SQLException 
	 */
	private void anadirATabla(BaseDeDatos bd, Jugador j, Equipo e, int codLiga) throws SQLException {
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
			String query = "SELECT * FROM EQUIPOS" + j.getNombre().toUpperCase() + " WHERE fk_CodLiga="+codLiga+" ORDER BY PUNTOS DESC, \"Goles A Favor Totales\"-\"Goles Encajados Totales\" DESC;"; //M�todo burbuja para ordenar equipos por puntos.
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
			String query2 = "SELECT NOMBRE FROM EQUIPOS" + j.getNombre().toUpperCase() + " WHERE (NOMBRE = '" + e.getNombre() + "' AND fk_CodLiga="+ codLiga +");";

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
	public void ocultarVentanaLiga() {
		setVisible(false);
	}
}

/**
 * @author Jorge
 *  Renderer para definir la celda seg�n la posici�n en la que se encuentren los equipos: Champions, Europa League o descenso
 *  Este renderer adem�s settea el equipo que hayas escogido en negrita
 */
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
		// System.out.println(parent.toString());
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
			parent.setBackground(new Color(153, 0, 0));
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
