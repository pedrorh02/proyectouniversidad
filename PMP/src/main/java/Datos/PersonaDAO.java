/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.interfaces.CrudPaginadoINterface;
import database.Conexion;
import entidades.Persona;
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
public class PersonaDAO implements CrudPaginadoINterface<Persona>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
        
    public PersonaDAO(){
    CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Persona> listar(String texto,int totalporpagina, int numpagina) {
        
    List<Persona> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select p.id, p.id_tipo_persona ,p.nombre, p.id_tipo_documento, p.num_documento, p.direccion, p.telefono, p.email,"
                    + "p.activo from persona p where p.nombre LIKE ? ORDER BY p.id ASC LIMIT ?|?");
            ps.setString(1, "%" +texto+ "%");
            ps.setInt(2,(numpagina-1)*totalporpagina);
            ps.setInt(3, totalporpagina);
            
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
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
    
      public List<Persona> listarTipo(String texto,int totalporpagina, int numpagina, String tipo_persona ) {
        
    List<Persona> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select p.id, p.id_tipo_persona ,p.nombre, p.id_tipo_documento, p.num_documento, p.direccion, p.telefono, p.email,"
                    + "p.activo from persona p where p.nombre LIKE ? and id_tipo_persona = ? ORDER BY p.id ASC LIMIT ?|?");
            ps.setString(1, "%" +texto+ "%");
            ps.setString(2, tipo_persona);
            ps.setInt(3,(numpagina-1)*totalporpagina);
            ps.setInt(4, totalporpagina);
            
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
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
    public boolean insertar(Persona obj) {
    resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("INSERT into persona(id_tipo_persona,nombre,id_tipo_documento,num_documento,direccion,telefono,email,activo) Values (?,?,?,?,?,?,?,'1')");
           ps.setString(1,obj.getTipoPersona());
           ps.setString(2, obj.getNombre());
           ps.setString(3, obj.getIdTipoDocumento());
           ps.setString(4, obj.getNumdocumento());
           ps.setString(5, obj.getDirecion());
           ps.setString(6, obj.getTelefono());
           ps.setString(7, obj.getEmail());
                                 
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
    public boolean actualizar(Persona obj) {
     resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("update persona set id_tipo_persona = ? ,nombre=?,id_tipo_documento=?,num_documento=?,direccion=?,telefono = ?,email=? where id=?");
           ps.setString(1,obj.getTipoPersona());
           ps.setString(2, obj.getNombre());
           ps.setString(3, obj.getIdTipoDocumento());
           ps.setString(4, obj.getNumdocumento());
           ps.setString(5, obj.getDirecion());
           ps.setString(6, obj.getTelefono());
           ps.setString(7, obj.getEmail());
           ps.setInt(8, obj.getId());
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
           ps = CON.getConexion().prepareStatement("update persona set activo = '0' where id=?");
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
           ps = CON.getConexion().prepareStatement("update persona set activo = '1' where id=?");
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
           ps = CON.getConexion().prepareStatement("Select Count(id) from persona");
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
           
           ps = CON.getConexion().prepareStatement("Select nombre from persona where nombre = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
