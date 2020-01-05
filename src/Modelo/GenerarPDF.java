/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Controlador.controlador;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/*
 *
 * @author xcheko51x
 * 
 * 
 */
import java.text.SimpleDateFormat;
import java.util.Date;


public class GenerarPDF {

    /**
     * @param args the command line arguments
     */
	
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
       // System.out.println("LA LISTA TIENE: "+tabla.length +" agregadas");
        
        // LLAMADA AL METODO PARA CREAR EL PDF
    }
    

    public static String fechaactual() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		return date;
	}

    public GenerarPDF(controlador cldr) {
    	String[][] tabla = cldr.getTabla();
        try {
			crearPDF(tabla);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public static void crearPDF(String[][] tabla) throws FileNotFoundException, DocumentException {
        // Se crea el documento
        Document documento = new Document();
        documento.setMargins(10,10,10,10);
        
        // El OutPutStream para el fichero donde crearemos el PDF
        FileOutputStream ficheroPDF = new FileOutputStream("Valores.pdf");
        
        // Se asocia el documento de OutPutStream
        PdfWriter.getInstance(documento, ficheroPDF);
        
        // Se abre el documento
        documento.open();
        
        // Parrafo

        Paragraph titulo = new Paragraph("Tabla de valores alumnos         (" + fechaactual() + ")  \n\n",
                FontFactory.getFont("Book Antiqua",
                        20,
                        Font.BOLD,
                        BaseColor.BLUE
                        )
        );
        
        // Añadimos el titulo al documento
        documento.add(titulo);
        // Creamos una tabla
        PdfPTable tabla2 = new PdfPTable(14);
        float[] medidaCeldas = {
        		1f,5f,1f,1f,1f,
        		1f,1f,1f,1f,1f,
        		1f,1f,1f,1f
        		};
        tabla2.setWidths(medidaCeldas);
        tabla2.addCell("ID");
        tabla2.addCell("NOMBRE");
        tabla2.addCell("A1");
        tabla2.addCell("A2");
        tabla2.addCell("A3");
        tabla2.addCell("A4");
        tabla2.addCell("A5");
        tabla2.addCell("A6");
        tabla2.addCell("A7");
        tabla2.addCell("A8");
        tabla2.addCell("A9");
        tabla2.addCell("A10");
        tabla2.addCell("A11");
        tabla2.addCell("A12");
     
        
        for(int i = 0 ; i < tabla.length-1 ; i++) {
        	tabla2.addCell(tabla[i][0]);
            tabla2.addCell(tabla[i][1]);
            tabla2.addCell(tabla[i][2]);
            tabla2.addCell(tabla[i][3]);
            tabla2.addCell(tabla[i][4]);
            tabla2.addCell(tabla[i][5]);
            tabla2.addCell(tabla[i][6]);
            tabla2.addCell(tabla[i][7]);
            tabla2.addCell(tabla[i][8]);
            tabla2.addCell(tabla[i][9]);
            tabla2.addCell(tabla[i][10]);
            tabla2.addCell(tabla[i][11]);
            tabla2.addCell(tabla[i][12]);
            tabla2.addCell(tabla[i][13]);
        }
        
        // Añadimos la tabla al documento
        documento.add(tabla2);
        documento.setMargins(10,10,10,10);
        // Se cierra el documento
        documento.close();
    }
    
}
