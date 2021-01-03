/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo de Pedro
 */
public class Conexion {
      
    private final String base = "db_pmp_production";
    private final String url = "jdbc:postgresql://localhost:5432/"+base;
    private final String username = "postgres";
    private final String pass = "letmein";
    private final String driver = "org.postgresql.Driver"; 
    
    public Connection con;
    public static Conexion instancia;
    
    private Conexion(){
    this.con=null;
    
    }
        
    public Connection getConexion(){
       
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(this.url, this.username, this.pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
    
    
    return con;
    }
    
       
    public void getDesconectar(){
        
        try {
            con.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
   
}


    public synchronized static Conexion getInstancia(){
    
    if(instancia==null){
        instancia = new Conexion();
    }
    return instancia;
    }
        
    
    
    
    
}
