package Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;



public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Configuraciones cgs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setResizable(false);
		setAutoRequestFocus(false);
		cgs = new Configuraciones();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		cargarTema();
		contentPane.setLayout(null);
		
		JButton alumnos = new JButton("ALUMNOS");
		alumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Alumnos alm = new Alumnos();
				alm.setVisible(true);
			}
		});
		alumnos.setBounds(10, 71, 132, 45);
		contentPane.add(alumnos);
		
		JButton btnInformes = new JButton("INFORMES");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro reg = null;
				try {
					reg = new Registro();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reg.setVisible(true);
			}
		});
		btnInformes.setBounds(10, 127, 132, 45);
		contentPane.add(btnInformes);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(298, 11, 261, 345);
		contentPane.add(calendar);
		
		JButton btnNewButton = new JButton("TEMAS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 cgs.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 333, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	private void cargarTema() {
		
		if(cgs.getTema().equalsIgnoreCase("rojo") ) {
			contentPane.setBackground(Color.RED);
		}
		else if(cgs.getTema().equalsIgnoreCase("negro")){
			contentPane.setBackground(Color.black);
		}
		else if(cgs.getTema().equalsIgnoreCase("gris")){
			contentPane.setBackground(Color.gray);
		}
		else if(cgs.getTema().equalsIgnoreCase("blanco")){
			contentPane.setBackground(new Color(240,240,240));
		}
	}
}
