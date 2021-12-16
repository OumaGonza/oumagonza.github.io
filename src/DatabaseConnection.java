
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Benard
 */
public class DatabaseConnection {
    final static String JDBC_DRIVER="com.mysql.jdbc.Driver";
    final static String DB_URL="jdbc:mysql://localhost:3306/mbr";
    
    final static String USER="root";
    final static String PASSWORD="";
    
    public static Connection openConnection(){
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn =(Connection) DriverManager.getConnection(DB_URL,USER,PASSWORD);
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
            
        }
        
    }
    
    
}
