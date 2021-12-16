package com.example.threefx;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class MysqlConnect {

    Connection conn=null;
    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/threefx","root","pa21work.");

            JOptionPane.showMessageDialog(null,"connection Established");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }

    }
}
