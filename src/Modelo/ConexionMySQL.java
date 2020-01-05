package Modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class ConexionMySQL {

    public String db = "clase";
    public String url = "jdbc:mysql://localhost:80/"+db;
    public String user = "root";
    public String pass = "tomasel10";

    private static Connection Conexion;
    
    public Connection getConexion() {
		return ConexionMySQL.Conexion;
    	
    }
    
    public void MySQLConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/" + db, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
       try {
           Conexion.close();
       } catch (SQLException ex) {
           Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    	
     public void insertDataCliente( String Nombre, String ac1, String ac2 ,String ac3,
    		 String ac4, String ac5, String ac6, String ac7,
    		 String ac8, String ac9, String ac10, String ac11, String ac12, String ob )
     {
    	
    	 //recuperar ultimo id
    	 int ID = getIDs() + 1;
    	 if(ID >= 0) {
    		 if(ID==0) {
    			 ID++;
    		 }
    		 try {
                 String Query = "INSERT INTO " + "alumnos" + " VALUES("
                 		 + "\"" + ID + "\", "
                         + "\"" + Nombre + "\", "
                         + "\"" + ac1 + "\", "
                         + "\"" + ac2 + "\", "
                         + "\"" + ac3 + "\", "
                         + "\"" + ac4 + "\", "
                         + "\"" + ac5 + "\", "
                         + "\"" + ac6 + "\", "
                         + "\"" + ac7 + "\", "
                         + "\"" + ac8 + "\", "
                         + "\"" + ac9 + "\", "
                         + "\"" + ac10 + "\", "
                         + "\"" + ac11 + "\", "
                         + "\"" + ac12 + "\", "
                         + "\"" + ob + "\")"
                         ;

                 Statement st = Conexion.createStatement();
                 st.executeUpdate(Query);
                 JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos (cod error #0x001)");
             }
    	 }
     }
     
     public void reemplazarDataCliente( int ID, String Nombre, String ac1, String ac2 ,
    		 String ac3, String ac4, String ac5, String ac6, 
    		 String ac7, String ac8, String ac9, String ac10, 
    		 String ac11, String ac12, String ob )
     {
    	//recuperar ultimo id
    	 try {
             String Query = "REPLACE INTO " + "alumnos" + " VALUES("
             		 + "\"" + ID + "\", "
                     + "\"" + Nombre + "\", "
                     + "\"" + ac1 + "\", "
                     + "\"" + ac2 + "\", "
                     + "\"" + ac3 + "\", "
                     + "\"" + ac4 + "\", "
                     + "\"" + ac5 + "\", "
                     + "\"" + ac6 + "\", "
                     + "\"" + ac7 + "\", "
                     + "\"" + ac8 + "\", "
                     + "\"" + ac9 + "\", "
                     + "\"" + ac10 + "\", "
                     + "\"" + ac11 + "\", "
                     + "\"" + ac12 + "\", "
                     + "\"" + ob + "\")"
                     ;

             Statement st = Conexion.createStatement();
             st.executeUpdate(Query);
             JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos (cod error #0x001)");
         }
     }
     
     public String[] getDatosAlumno(int ID) {
    	 String[] alumno = new String[13];
    	 try {
    		 String Query = "SELECT * FROM " + "alumnos";
             Statement st = Conexion.createStatement();
             java.sql.ResultSet resultSet;
             resultSet = st.executeQuery(Query);
             
             while (resultSet.next()) {
                 int IDalumno =  Integer.parseInt(resultSet.getString("IDalumno"));
                 if (IDalumno==ID) {
                	 alumno[0]= resultSet.getString("Actividad 1");
                	 alumno[1]= resultSet.getString("Actividad 2");
                	 alumno[2]= resultSet.getString("Actividad 3");
                	 alumno[3]= resultSet.getString("Actividad 4");
                	 alumno[4]= resultSet.getString("Actividad 5");
                	 alumno[5]= resultSet.getString("Actividad 6");
                	 alumno[6]= resultSet.getString("Actividad 7");
                	 alumno[7]= resultSet.getString("Actividad 8");
                	 alumno[8]= resultSet.getString("Actividad 9");
                	 alumno[9]= resultSet.getString("Actividad 10");
                	 alumno[10]=resultSet.getString("Actividad 11");
                	 alumno[11]=resultSet.getString("Actividad 12");
                	 alumno[12]=resultSet.getString("observaciones");
             		 return alumno;
                 }
             }
     	} catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
     	}
		return alumno;
     }
     
     public int getIDs() {
    	 //obtiene ultimo id disponible
    	 //finalidad de agregar un nuevo elemento a la tabla de datos, con nuevo id
    	 
    	 int ID = 0;
    	 try {
    		 String Query = "SELECT * FROM " + "alumnos";
             Statement st = Conexion.createStatement();
             java.sql.ResultSet resultSet;
             resultSet = st.executeQuery(Query);
             
             
             while (resultSet.next()) {
                 ID =  Integer.parseInt(resultSet.getString("IDalumno"));
             }
             return ID;
     	} catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
     	}
    	return ID;
	}

    
	public String[] getListaAlm() {
		String[] list = new String[500];
		try {
   		 String Query = "SELECT * FROM " + "alumnos";
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            int i = 0;
            while (resultSet.next()) {
                list[i] = resultSet.getString("IDalumno") + " | " + resultSet.getString("nombre") ;
                i++;
            }
    		return list;
    	} catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
    	}
		return list;
	}
	public void deleteRecord(String ID) {
        try {
            String Query = "DELETE FROM " + "alumnos" + " WHERE IDalumno = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
	
	public String[][] getDatosAlumnos() {
	   	 String[][] alumnos = new String[500][14];
	   	 int x = 0;
	   	 try {
	   		 	Statement st = Conexion.createStatement();
	            java.sql.ResultSet resultSet;
	   		    String Query = "SELECT * FROM " + "alumnos";
	            resultSet = st.executeQuery(Query);
	            
	            while (resultSet.next()) {
	                
	            	alumnos[x][0]= resultSet.getString("IDalumno");
	            	alumnos[x][1]= resultSet.getString("Nombre");
	            	alumnos[x][2]= resultSet.getString("Actividad 1");
	            	alumnos[x][3]= resultSet.getString("Actividad 2");
	            	alumnos[x][4]= resultSet.getString("Actividad 3");
	               	alumnos[x][5]= resultSet.getString("Actividad 4");
	               	alumnos[x][6]= resultSet.getString("Actividad 5");
	               	alumnos[x][7]= resultSet.getString("Actividad 6");
	               	alumnos[x][8]= resultSet.getString("Actividad 7");
	               	alumnos[x][9]= resultSet.getString("Actividad 8");
	               	alumnos[x][10]= resultSet.getString("Actividad 9");
	               	alumnos[x][11]= resultSet.getString("Actividad 10");
	               	alumnos[x][12]=resultSet.getString("Actividad 11");
	               	alumnos[x][13]=resultSet.getString("Actividad 12");
	            	
	                x++;
	            }
	    	} catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
	    	}
			return alumnos;
	}
}

