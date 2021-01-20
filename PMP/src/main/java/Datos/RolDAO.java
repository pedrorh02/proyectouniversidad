
package Datos;

import database.Conexion;
import entidades.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class RolDAO {
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public RolDAO(){
    CON = Conexion.getInstancia();
    
    }
    
    public List<Rol> listar() {
        
    List<Rol> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select * from rol where 1=1 ORDER BY id asc");
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Rol(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            ps.close();
            rs.close();
                        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally
        {
        ps = null;
        rs = null;
        CON.getDesconectar();
        
        }
               
     return registros;
    }
    
    
    public List<Rol> selecionar() {
    List<Rol> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select id, nombre from rol where 1=1 ORDER BY id asc");
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Rol(rs.getInt(1),rs.getString(2)));
            }
            ps.close();
            rs.close();
                        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally
        {
        ps = null;
        rs = null;
        CON.getDesconectar();
        
        }
               
     return registros;
    }
    
      public int total() {
      int totalRegistros = 0;
    
        try {
           ps = CON.getConexion().prepareStatement("Select Count(id) from rol");
           rs=ps.executeQuery();
           
           while(rs.next())
           {
           totalRegistros= rs.getInt("count");
           }
           ps.close();
           rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
           ps = null;
           rs = null;
           CON.getDesconectar();
        }
    
    return totalRegistros;
    
    }
}
