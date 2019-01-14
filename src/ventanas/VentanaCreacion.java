package ventanas;

import javax.swing.JFrame;

import entidades.BaseDeDatos;
import entidades.Equipo;
import fisicas.FisicasNuevas;
import jugador.Jugador;
import objetos.ObjetoCombobox;

import javax.swing.JPanel;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Robot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class VentanaCreacion extends JFrame {
	private JTextField tfNombre;
	private JTextField tfSiglas;
	private ArrayList<Equipo> listaEquiposEliminados ;
	private int ligaNueva;
	private Jugador j;
	private JComboBox cbSustituye;

	/**
	 * Ventana de modo creaci�n donde podemos dise�ar nuestro propio equipo
	 * 
	 * @author Jorge
	 * @param bd
	 *            Base de datos pasada
	 * @param f
	 *            F�sicas que se van a llevar desde el inicio del juego hasta los
	 *            partidos que se disputen
	 */
	/**
	 * @param bd
	 * @param f
	 */
	public VentanaCreacion(BaseDeDatos bd, FisicasNuevas f, ArrayList<Equipo> listaEquipos, int ligaAanyadir, Jugador j) {
		this.j = j;
		
		if( ligaAanyadir == 0) {
			ligaNueva = j.getCodLiga();
			bd.anyadirLiga("Liga" + ligaNueva, j);
			bd.anyadirTodosLosEquipos(ligaNueva, j);
		}
		else if(ligaAanyadir != 0) ligaNueva = ligaAanyadir;
		
		
		if( listaEquipos == null) this.listaEquiposEliminados = new ArrayList<>();
		else if(listaEquipos != null) this.listaEquiposEliminados = listaEquipos;
		setSize(737, 364);

		JLabel background_1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("iconos/anoeta.png")));
		setContentPane(background_1);
		background_1.setLayout(null);
		((JComponent) getContentPane()).setBorder(new EmptyBorder(10,20, 10, 20));
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("Nombre:");

		tfNombre = new JTextField();

		tfNombre.setColumns(10);

		JLabel lblEscudo = new JLabel("Escudo:");

		JButton btnExaminar = new JButton("Examinar...");

		JLabel lblSiglas = new JLabel("Siglas:");

		tfSiglas = new JTextField();
		tfSiglas.setDocument(new JTextFieldLimit(3));
		tfSiglas.setColumns(10);

		JLabel lblSustituirAEquipo = new JLabel("Sustituir a equipo:");

		cbSustituye = new JComboBox();
		try {
			Logger logger = Logger.getLogger("ventCreacion");
			try {
				logger.addHandler(new FileHandler("Ventana Creacion"));
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			for (Equipo e : bd.devolverTodosLosEquipos(j, ligaNueva)) {
				cbSustituye.addItem(new ObjetoCombobox(1, e.getNombre(), null));
			
			}
		}finally {

		JLabel lblLocalizacionIcono = new JLabel("");
		JFileChooser fc = new JFileChooser();
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileNameExtensionFilter png = new FileNameExtensionFilter("Im�genes(.png)", "png");
				fc.addChoosableFileFilter(png);
				fc.setFileFilter(png);
				int seleccion = fc.showOpenDialog(getContentPane());
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					System.out.println(fichero.getPath());
					System.out.println(fichero.getName());
					lblLocalizacionIcono.setText(fichero.getName());
				}
				fc.setBounds(600, 100, 600, 300);
				fc.setVisible(true);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEscudo, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(lblSiglas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(lblSustituirAEquipo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tfSiglas, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
						.addComponent(tfNombre, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbSustituye, 0, 441, Short.MAX_VALUE)
							.addGap(20))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnExaminar, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
							.addGap(171)
							.addComponent(lblLocalizacionIcono, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)))
					.addGap(43))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLocalizacionIcono, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(btnExaminar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(lblEscudo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSiglas)
						.addComponent(tfSiglas, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSustituirAEquipo, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(cbSustituye, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(86))
		);
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio i = new Inicio(bd, f);
				i.setVisible(true);
				dispose();
			}
		});
		panel_1.add(btnVolver);

		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bd.init();
				} catch (Exception e3) {

				}
				File source = fc.getSelectedFile();
				Path destino = null; // Universalizamos el selector de imagen. Todo icono escogido se mueve a SRC
										// para que otro usuario pueda acceder a �l desde otro ordenador.
				System.out.println(source.toPath());
				
				try {
					destino = Paths.get("src/iconos/equipos/", source.getName());
					System.out.println(destino.toString());
					Files.copy(source.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
					actualiza();
					Thread.sleep(500);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				String icono = "iconos/equipos/" + source.getName();
				Equipo e1 = new Equipo(tfSiglas.getText(), tfNombre.getText(), 0, Color.black, icono, icono, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
				
					try {
						actualiza();
						
						listaEquiposEliminados.add(bd.convertirAEquipo(cbSustituye.getSelectedItem().toString(), j, ligaNueva));
						bd.eliminarEquipo(cbSustituye.getSelectedItem().toString(), j, ligaNueva);
						e1.getBolaEquipo().setRutaImagen(icono);
						bd.anyadirEquipo(e1, ligaNueva, j);
						
						actualiza();
						VentanaCreacion vC = new VentanaCreacion(bd, f, listaEquiposEliminados, ligaNueva,j);
						vC.setVisible(true);
				
						dispose();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		
					
				try {
					bd.close();
				} catch (Exception eee) {
					eee.printStackTrace();
				}
			}
		});
		panel_1.add(btnSiguiente);

		JButton btnActualizarEquipos = new JButton(
				"Actualizar equipos"); /**
										 * Depende del usuario y contrase�a que tengamos en el textfield y el
										 * passwordfield, nos actualizar� los equipos del combobox por los de nuestra
										 * liga ya creada
										 **/
		btnActualizarEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
					cbSustituye.removeAllItems(); 
						try {
							Logger logger = Logger.getLogger("baseDeDatos");
							Statement consulta;
							String comando = "";
							try {
								Class.forName("org.sqlite.JDBC");
								bd.init();
							} catch (Exception e3) {
								// e3.printStackTrace();
							}
							
							String query = "SELECT NOMBRE FROM EQUIPOS" + j.getNombre().toUpperCase() + " WHERE fk_CodLiga="+ ligaNueva +";";
							ResultSet rs = bd.getCon().createStatement().executeQuery(query);
							while (rs.next()) {
								String nomEq = rs.getString("Nombre");
								cbSustituye.addItem(new ObjetoCombobox(1, nomEq, null));
							}
							rs.close();
							bd.close();
							
						
				}catch(Exception u) {u.printStackTrace();}
			
		}});
		panel_1.add(btnActualizarEquipos);
		
		JButton btnListo = new JButton("Listo");
		btnListo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MenuLiga mU = new MenuLiga(700, 700, j, bd, f, ligaNueva);
				mU.setVisible(true);
				setVisible(false);
				dispose();
			}});
		panel_1.add(btnListo);}}
		

	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
//		InputStream is = null;
//		OutputStream os = null;
//		try {
//			is = new FileInputStream(source);
//			os = new FileOutputStream(dest);
//			byte[] buffer = new byte[1024];
//			int length;
//			while ((length = is.read(buffer)) > 0) {
//				os.write(buffer, 0, length);
//			}
//			os.flush();//forzar el guardado
//		} finally {
//			is.close();
//			os.close();
//		}
		
		try{
			FileInputStream fis = new FileInputStream(source); //inFile -> Archivo a copiar
			FileOutputStream fos = new FileOutputStream(dest); //outFile -> Copia del archivo
			FileChannel inChannel = fis.getChannel(); 
			FileChannel outChannel = fos.getChannel(); 
			inChannel.transferTo(0, inChannel.size(), outChannel); 
			fis.close(); 
			fos.close();
			}catch (IOException ioe) {
			System.err.println("Error al Generar Copia");
			}
	}



	/**
	 * Actualiza el Eclipse solo para detectar ya nuestro icono
	 * 
	 * @throws AWTException
	 */
	public void actualiza() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);
	}

}

class JTextFieldLimit extends PlainDocument {
	private int limit;

	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}
