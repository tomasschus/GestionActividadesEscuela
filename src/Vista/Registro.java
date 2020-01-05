package Vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.controlador;
import Modelo.ConexionMySQL;
import Modelo.GenerarPDF;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;
import java.awt.Color;
import javax.swing.JTextField;

public class Registro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private controlador cldr;
	private JTextField textField;
	private List list;
	private ConexionMySQL sql;
	Configuraciones cgs;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	
	@SuppressWarnings("deprecation")
	private void listar() {
		this.list.clear();
		String[] lista = sql.getListaAlm();
		int i = 0;
		while(lista[i] != null) {
			this.list.add(lista[i]);
			i++;
		}
	}
	
	@SuppressWarnings("deprecation")
	private void buscar(String palabra) {
		this.list.clear();
		String[] lista = sql.getListaAlm();
		int i = 0;
		while(lista[i] != null) {
			if(lista[i].toLowerCase().contains(palabra.toLowerCase())) {
				this.list.add(lista[i]);
			}
			i++;
		}
	}
	public Registro() throws Exception {
		setAutoRequestFocus(false);
		cgs = new Configuraciones();
		try {
			sql = new ConexionMySQL();
			sql.MySQLConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setResizable(false);
		cldr = new controlador();
		setTitle("Informes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 756, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cargarTema();
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 22));
		spinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinner.setBounds(176, 11, 72, 35);
		contentPane.add(spinner);
		
		JLabel lblActividad = new JLabel("Actividad");
		lblActividad.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblActividad.setBounds(44, 11, 101, 35);
		contentPane.add(lblActividad);
		
		JButton btnCalcularTotalAlumnos = new JButton("Calcular Total Alumnos");
		btnCalcularTotalAlumnos.setBackground(new Color(230, 230, 250));
		btnCalcularTotalAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(null, "Cantidad Alumnos: " + cldr.getCantidadAlumnos(), "Calcular Total Alumnos", 
						JOptionPane.DEFAULT_OPTION);
			}
		});
		btnCalcularTotalAlumnos.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		btnCalcularTotalAlumnos.setBounds(34, 241, 317, 35);
		contentPane.add(btnCalcularTotalAlumnos);
		
		JButton btnCalcularCorrectos = new JButton("Calcular Correctos");
		btnCalcularCorrectos.setBackground(Color.GREEN);
		btnCalcularCorrectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int act = Integer.parseInt(spinner.getValue().toString());
				int correctos = cldr.getCorrectos(act);
				int alum = cldr.getCantidadAlumnos();
				Float porc = (float) ((correctos * 100) / alum);
				JOptionPane.showConfirmDialog(null, "Cantidad de correctas: " + correctos + " ( " + porc + "% )", "Calcular Correctos", 
						JOptionPane.DEFAULT_OPTION);
			}
		});
		btnCalcularCorrectos.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		btnCalcularCorrectos.setBounds(34, 57, 317, 35);
		contentPane.add(btnCalcularCorrectos);
		
		JButton button = new JButton("Calcular Incorrectos");
		button.setBackground(Color.RED);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int act = Integer.parseInt(spinner.getValue().toString());
				int incorrectos = cldr.getIncorrectos(act);
				int alum = cldr.getCantidadAlumnos();
				Float porc = (float) ((incorrectos * 100) / alum);
				JOptionPane.showConfirmDialog(null, "Cantidad de incorrectas: " + incorrectos + " ( " + porc + "% )", "Calcular Inorrectos", 
						JOptionPane.DEFAULT_OPTION);
			}
		});
		button.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		button.setBounds(34, 149, 317, 35);
		contentPane.add(button);
		
		list = new List();
		list.setBounds(449, 57, 291, 435);
		contentPane.add(list);
		listar();

		JButton btnCalcularInorrectosalumno = new JButton("Calcular Incorrectos (alumno)");
		btnCalcularInorrectosalumno.setBackground(Color.RED);
		btnCalcularInorrectosalumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedItem() != null) {
					JOptionPane.showConfirmDialog(null, "" + 
							cldr.getIncorrectosAlumno(Integer.parseInt(list.getSelectedItem().substring(0,1))), 
							"Calcular Incorrectos", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		btnCalcularInorrectosalumno.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		btnCalcularInorrectosalumno.setBounds(34, 195, 317, 35);
		contentPane.add(btnCalcularInorrectosalumno);
		
		JButton button_1 = new JButton("Calcular Correctos (alumno)");
		button_1.setBackground(Color.GREEN);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedItem() != null) {
					JOptionPane.showConfirmDialog(null, "" + 
							cldr.getCorrectosAlumno(Integer.parseInt(list.getSelectedItem().substring(0,1))), 
							"Calcular Correctos", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		
		button_1.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		button_1.setBounds(34, 103, 317, 35);
		contentPane.add(button_1);
		
		JButton btnExportar = new JButton("EXPORTAR");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings
				("unused")
				GenerarPDF pdf = new GenerarPDF(cldr);	
			}
		});
		btnExportar.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		btnExportar.setBackground(Color.ORANGE);
		btnExportar.setBounds(10, 456, 161, 35);
		contentPane.add(btnExportar);
		
		textField = new JTextField();
		textField.setBounds(449, 23, 187, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar(textField.getText());
			}
		});
		btnBuscar.setBounds(646, 22, 89, 23);
		contentPane.add(btnBuscar);
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
