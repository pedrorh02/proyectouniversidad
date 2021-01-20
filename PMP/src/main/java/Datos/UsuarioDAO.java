/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.interfaces.CrudPaginadoINterface;
import database.Conexion;
import entidades.Usuario;
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
public class UsuarioDAO implements CrudPaginadoINterface<Usuario>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
        
    public UsuarioDAO(){
    CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Usuario> listar(String texto,int totalporpagina, int numpagina) {
        
    List<Usuario> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select u.id, u.id_rol, r.nombre as nombre_rol, u.nombre,u.id_tipo_documento, u.num_documento, u.direccion, u.telefono, u.email,"
                    + "u.clave, u.activo from usuario u left join rol r on u.id_rol = r.id where u.nombre LIKE ? ORDER BY u.id ASC LIMIT ?|?");
            ps.setString(1, "%" +texto+ "%");
            ps.setInt(2,(numpagina-1)*totalporpagina);
            ps.setInt(3, totalporpagina);
            
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Usuario(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getBoolean(11)));
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

    public Usuario login(String email, String clave){
       Usuario usu= new Usuario(); 
        
        try {
            ps = CON.getConexion().prepareStatement("select u.id, u.id_rol, r.nombre as nombre_rol, u.nombre,u.id_tipo_documento, u.num_documento, u.direccion, u.telefono, u.email,"
                    + "u.activo from usuario u left join rol r on u.id_rol = r.id where u.email = ? and clave = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ps.setString(1,email);
            ps.setString(2,clave);
            rs = ps.executeQuery();
            
            if(rs.first()){
            
            usu = new Usuario(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getBoolean(10));
            
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
    
        return usu;
    
    }
    
    
    
    @Override
    public boolean insertar(Usuario obj) {
    resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("INSERT into Usuario(id_rol,nombre,id_tipo_documento,num_documento,direccion,telefono,email,clave,activo) Values (?,?,?,?,?,?,?,?,'1')");
           ps.setInt(1,obj.getRolId());
           ps.setString(2, obj.getNombre());
           ps.setString(3, obj.getTipodocumento());
           ps.setString(4, obj.getNumDocumento());
           ps.setString(5, obj.getDirecion());
           ps.setString(6, obj.getTelefono());
           ps.setString(7, obj.getEmail());
           ps.setString(8, obj.getClave());
                      
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
    public boolean actualizar(Usuario obj) {
     resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("update usuario set id_rol = ? ,nombre=?,id_tipo_documento=?,num_documento=?,direccion=?,telefono = ?,email=?,clave=? where id=?");
            ps.setInt(1,obj.getRolId());
           ps.setString(2, obj.getNombre());
           ps.setString(3, obj.getTipodocumento());
           ps.setString(4, obj.getNumDocumento());
           ps.setString(5, obj.getDirecion());
           ps.setString(6, obj.getTelefono());
           ps.setString(7, obj.getEmail());
           ps.setString(8, obj.getClave());
           ps.setInt(9, obj.getId());
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
           ps = CON.getConexion().prepareStatement("update usuario set activo = '0' where id=?");
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
           ps = CON.getConexion().prepareStatement("update usuario set activo = '1' where id=?");
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
           ps = CON.getConexion().prepareStatement("Select Count(id) from usuario");
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

    @Override
    public boolean existe(String texto) {
      resp = false;
    
        try {
           
           ps = CON.getConexion().prepareStatement("Select email from usuario where email = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
           ps.setString(1, texto);
           rs=ps.executeQuery();
           rs.first();
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
