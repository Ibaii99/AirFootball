package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaLiga extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLiga frame = new VentanaLiga();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLiga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelClasificacion = new JPanel();
		contentPane.add(panelClasificacion, BorderLayout.CENTER);
		
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

}
