/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.interfaces.CrudSimpleInterface;
import database.Conexion;
import entidades.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Equipo de Pedro
 */
public class CategoriaDAO implements CrudSimpleInterface<Categoria>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
        
    public CategoriaDAO(){
    CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Categoria> listar(String texto) {
        
    List<Categoria> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select * from categoria where nombre LIKE ?");
            ps.setString(1, "%" +texto+ "%");
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Categoria(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
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

    @Override
    public boolean insertar(Categoria obj) {
    resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("INSERT into Categoria(nombre,descripcion,activo) Values (?,?,1)");
           ps.setString(1, obj.getNombre());
           ps.setString(2, obj.getDescripcion());
           if(ps.executeUpdate()>0){
               resp = true;           
           }
           ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
           ps = null;
           CON.getDesconectar();
        }
    
    return resp;
    }

    @Override
    public boolean actualizar(Categoria obj) {
     resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("update Categoria set nombre = ?,descripcion = ? where id=?");
           ps.setString(1, obj.getNombre());
           ps.setString(2, obj.getDescripcion());
           ps.setInt(3, obj.getId_categoria());
           if(ps.executeUpdate()>0){
               resp = true;           
           }
           ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
           ps = null;
           CON.getDesconectar();
        }
    
    return resp;
    
    }

    @Override
    public boolean desactualizar(int id) {
      resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("update Categoria set activo = 0 where id=?");
           ps.setInt(1, id);
           if(ps.executeUpdate()>0){
               resp = true;           
           }
           ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
           ps = null;
           CON.getDesconectar();
        }
    
    return resp;
    
    }

    @Override
    public boolean activar(int id) {
       resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("update Categoria set activo = 1 where id=?");
           ps.setInt(1, id);
           if(ps.executeUpdate()>0){
               resp = true;           
           }
           ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
           ps = null;
           CON.getDesconectar();
        }
    
    return resp;
    
    }

    @Override
    public int total() {
      int totalRegistros = 0;
    
        try {
           ps = CON.getConexion().prepareStatement("Select Count(id) from categoria");
           rs=ps.executeQuery();
           
           while(rs.next())
           {
           totalRegistros= rs.getInt("count(id)");
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

    @Override
    public boolean existe(String texto) {
      resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("Select nombre from categoria where nombre = ?");
           ps.setString(1, texto);
           rs=ps.executeQuery();
           rs.last();
           if(rs.getRow()>0)
           {
           resp= true;
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
    
    return resp;
    }
    
}
