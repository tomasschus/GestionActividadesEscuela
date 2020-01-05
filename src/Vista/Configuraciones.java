package Vista;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Configuraciones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static String tema;
	private static Scanner s;
	private ButtonGroup bg;
	private JRadioButton rdbtnBlack ;
	private JRadioButton rdbtnBlanco ; 
	private JRadioButton rdbtnRojo ;
	private JRadioButton rdbtnGris ;
	private JButton aplicar;
	private static File f; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		//CargarConfiguraciones();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuraciones frame = new Configuraciones();
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
	public Configuraciones() {
		setTitle("Temas");
		setResizable(false);
		setAutoRequestFocus(false);
		f = new File("Configuracion.conf");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 267, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		CargarConfiguraciones();
		rdbtnBlack = new JRadioButton("Negro");
		rdbtnBlack.setBounds(6, 7, 95, 23);
		contentPane.add(rdbtnBlack);
		
		rdbtnBlanco = new JRadioButton("Blanco");
		rdbtnBlanco.setBounds(161, 7, 94, 23);
		contentPane.add(rdbtnBlanco);
		
		rdbtnRojo = new JRadioButton("Rojo");
		rdbtnRojo.setBounds(6, 33, 95, 23);
		contentPane.add(rdbtnRojo);
		
		rdbtnGris = new JRadioButton("Gris");
		rdbtnGris.setBounds(161, 33, 94, 23);
		contentPane.add(rdbtnGris);
		
		aplicar = new JButton("Aplicar Configuracion");
		aplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aplicarConfiguraciones();
			}
		});
		aplicar.setBounds(35, 80, 187, 23);
		contentPane.add(aplicar);
		
		bg = new ButtonGroup();
		bg.add(rdbtnBlack);
		bg.add(rdbtnBlanco);
		bg.add(rdbtnRojo);
		bg.add(rdbtnGris);
		
		aplicarConfiguraciones();

	}
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		Configuraciones.tema = tema;
	}

	public void aplicarConfiguraciones(){
		if(rdbtnRojo.isSelected()) {
			contentPane.setBackground(Color.RED);
			tema = "rojo";
		}
		else if(rdbtnBlack.isSelected()){
			contentPane.setBackground(Color.black);
			tema= "negro";
		}
		else if(rdbtnGris.isSelected()){
			contentPane.setBackground(Color.gray);
			tema= "gris";
		}
		else if(rdbtnBlanco.isSelected()){
			contentPane.setBackground(new Color(240,240,240));
			tema= "blanco";

		}
		try {
			f.createNewFile();
			FileWriter fw=new FileWriter("Configuracion.conf");
			fw.write("Color de tema: " + tema);
			fw.close();
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void CargarConfiguraciones() {
		try{		
			s = new Scanner(f);
			
			while(s.hasNextLine()){
				String line = s.nextLine();
				String[] sp = line.split(" ");
				tema = sp[3]; 
				System.out.print("tema |" + tema +"|" );
				if(tema.equalsIgnoreCase("rojo") ) {
					contentPane.setBackground(Color.RED);
				}
				else if(tema.equalsIgnoreCase("negro")){
					contentPane.setBackground(Color.black);
				}
				else if(tema.equalsIgnoreCase("gris")){
					contentPane.setBackground(Color.gray);
				}
				else if(tema.equalsIgnoreCase("blanco")){
					contentPane.setBackground(new Color(240,240,240));
				}
			}
			
	 }catch(FileNotFoundException e){
		 System.out.println("El archivo no existe…");
		 try {
			f.createNewFile();
			FileWriter fw=new FileWriter("Configuracion.conf");
			fw.write("Color de tema: blanco");
			fw.close();
			tema = "blanco";
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 }		 
	}
}
