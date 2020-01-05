package Vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Modelo.ConexionMySQL;
import javax.swing.JTextField;
import java.awt.List;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class Alumnos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private TextArea textArea;
	private List list;
	private ConexionMySQL sql;
	private JTextField textField_13;
	private int ver;
	private JTextField textField_14;
	Configuraciones cgs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alumnos frame = new Alumnos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * 
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
	
	private void restaurar() {
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		textField_4.setEditable(false);
		textField_5.setEditable(false);
		textField_6.setEditable(false);
		textField_7.setEditable(false);
		textField_8.setEditable(false);
		textField_9.setEditable(false);
		textField_10.setEditable(false);
		textField_11.setEditable(false);
		textField_12.setEditable(false);
		textArea.setEditable(false);
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		textField_9.setText("");
		textField_10.setText("");
		textField_11.setText("");
		textField_12.setText("");
		textField_13.setText("");
		textArea.setText("");
		
	}
	public Alumnos() {
		setAutoRequestFocus(false);
		cgs = new Configuraciones();
		ver = -1;
		try {
			sql = new ConexionMySQL();
			sql.MySQLConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setResizable(false);
		setTitle("Alumnos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cargarTema();

		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Book Antiqua", Font.PLAIN, 23));
		textField.setBounds(116, 75, 283, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		list = new List();
		list.setBounds(542, 53, 216, 437);
		contentPane.add(list);
		
		JButton GUARDAR = new JButton("GUARDAR");
		GUARDAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textField_13.getText()) != ver ) {
					sql.insertDataCliente(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(),
							textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText(), textField_9.getText(), 
							textField_10.getText(), textField_11.getText(), textField_12.getText(), textArea.getText()
							);
				}
				else {
					sql.reemplazarDataCliente(Integer.parseInt(textField_13.getText()), textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(),
							textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText(), textField_9.getText(), 
							textField_10.getText(), textField_11.getText(), textField_12.getText(), textArea.getText()
							);
				}
				listar();
				restaurar();
			}
		});
		GUARDAR.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		GUARDAR.setBounds(35, 559, 144, 41);
		contentPane.add(GUARDAR);
		
		Button verAlumno = new Button("VER ALUMNO");
		verAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*String[] div = list.getSelectedItem().split("|");
				int codid = Integer.parseInt(div[0]); //obtengo id*/
				int codid = Integer.parseInt(list.getSelectedItem().substring(0,1));
				ver = codid;
				String nombre = list.getSelectedItem().substring(4);
				String[] alumno = sql.getDatosAlumno(codid);
				textField_13.setText(Integer.toString(codid));
				textField.setText(nombre);
				textField_1.setText( alumno[0] );
				textField_2.setText( alumno[1] );
				textField_3.setText( alumno[2] );
				textField_4.setText( alumno[3] );
				textField_5.setText( alumno[4] );
				textField_6.setText( alumno[5] );
				textField_7.setText( alumno[6] );
				textField_8.setText( alumno[7] );
				textField_9.setText( alumno[8] );
				textField_10.setText( alumno[9] );
				textField_11.setText( alumno[10] );
				textField_12.setText( alumno[11] );
				textArea.setText( alumno[12] );
				textArea.setEditable(true);
				habilitar();

			}
		});
		verAlumno.setBounds(603, 496, 108, 22);
		contentPane.add(verAlumno);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		lblNewLabel.setBounds(22, 87, 84, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Actividades");
		lblNewLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(24, 152, 98, 22);
		contentPane.add(lblNewLabel_1);
		
		JButton btnDescartar = new JButton("DESCARTAR");
		btnDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ver = -1;
				restaurar();
				listar();
			}
		});
		btnDescartar.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		btnDescartar.setBounds(280, 559, 144, 41);
		contentPane.add(btnDescartar);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(118, 211, 59, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		textField_2.setBounds(118, 242, 59, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		textField_3.setBounds(118, 273, 59, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(118, 304, 59, 20);
		textField_4.setEditable(false);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(118, 335, 59, 20);
		textField_5.setEditable(false);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(118, 366, 59, 20);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(336, 211, 59, 20);
		textField_7.setEditable(false);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setEditable(false);
		textField_8.setBounds(336, 242, 59, 20);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(336, 273, 59, 20);
		textField_9.setEditable(false);
		contentPane.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(336, 304, 59, 20);
		textField_10.setEditable(false);
		contentPane.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setEditable(false);
		textField_11.setBounds(336, 335, 59, 20);
		contentPane.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setEditable(false);
		textField_12.setBounds(336, 366, 59, 20);
		contentPane.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("Book Antiqua", Font.PLAIN, 23));
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBounds(116, 27, 98, 41);
		contentPane.add(textField_13);
		
		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 22));
		textArea.setBounds(35, 433, 483, 120);
		contentPane.add(textArea);
		
		JLabel label = new JLabel("1");
		label.setBounds(35, 214, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setBounds(35, 245, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setBounds(35, 276, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setBounds(35, 307, 46, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setBounds(35, 338, 46, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("6");
		label_5.setBounds(35, 369, 46, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("7");
		label_6.setBounds(280, 214, 46, 14);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("8");
		label_7.setBounds(280, 245, 46, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("9");
		label_8.setBounds(280, 276, 46, 14);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("10");
		label_9.setBounds(280, 307, 46, 14);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("11");
		label_10.setBounds(280, 338, 46, 14);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("12");
		label_11.setBounds(280, 369, 46, 14);
		contentPane.add(label_11);
		
		JButton añadir = new JButton("+");
		añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitar();
				textArea.setEditable(true);
				textField_13.setText(Integer.toString(sql.getIDs()+1));
				
			}
		});
		añadir.setBounds(409, 75, 70, 41);
		contentPane.add(añadir);
		
		listar();
		
		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		ID.setBounds(22, 39, 84, 22);
		contentPane.add(ID);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		lblObservaciones.setBounds(22, 408, 144, 22);
		contentPane.add(lblObservaciones);
		
		JButton eliminar = new JButton("ELIMINAR");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al Alumno?", "Confirmar salida", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(x==0) {
					sql.deleteRecord((textField_13.getText()));
					listar();
					restaurar();
				}
			}
		});
		eliminar.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		eliminar.setBounds(520, 559, 144, 41);
		contentPane.add(eliminar);
		
		JButton btnParametros = new JButton("Parametros");
		btnParametros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "1 - Correcto\r\n" + 
						"2 - Parcialmente Correcto\r\n" + 
						"3 - Incorrecto\r\n" + 
						"9 - No contesto", "Parametros", 
						JOptionPane.DEFAULT_OPTION);
			}
		});
		btnParametros.setBounds(195, 153, 108, 23);
		contentPane.add(btnParametros);
		
		textField_14 = new JTextField();
		textField_14.setBounds(542, 27, 122, 20);
		contentPane.add(textField_14);
		textField_14.setColumns(10);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar(textField_14.getText());
			}
		});
		btnBuscar.setBounds(669, 27, 89, 23);
		contentPane.add(btnBuscar);
		
		
	}

	protected void habilitar() {
		textField.setEditable(true);
		textField_1.setEditable(true);
		textField_2.setEditable(true);
		textField_3.setEditable(true);
		textField_4.setEditable(true);
		textField_5.setEditable(true);
		textField_6.setEditable(true);
		textField_7.setEditable(true);
		textField_8.setEditable(true);
		textField_9.setEditable(true);
		textField_10.setEditable(true);
		textField_11.setEditable(true);
		textField_12.setEditable(true);		
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
