/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.interfaces.CrudPaginadoINterface;
import database.Conexion;
import entidades.Articulo;
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
public class ArticuloDAO implements CrudPaginadoINterface<Articulo>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
        
    public ArticuloDAO(){
    CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Articulo> listar(String texto,int totalporpagina, int numpagina) {
        
    List<Articulo> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select a.id_articulo,a.id_Categoria,c.nombre as Categoria_nombre, a.codigo_barra, a.nombre, a.precio_venta, a.stock, a.descripcion, a.imagen, a.activo   from articulo a inner join categoria c on a.id_categoria = c.id_categoria where a.nombre LIKE ? ORDER BY a.id_articulo asc Limit ? | ?");
            ps.setString(1, "%" +texto+ "%");
            ps.setInt(2,(numpagina-1)*totalporpagina);
            ps.setInt(3, totalporpagina);
            
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Articulo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getBoolean(10)));
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
    
       public List<Articulo> listarArticulosSalida(String texto,int totalporpagina, int numpagina) {
        
    List<Articulo> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select a.id_articulo,a.id_Categoria,c.nombre as Categoria_nombre, a.codigo_barra, a.nombre, a.precio_venta, a.stock, a.descripcion, a.imagen, a.activo   from articulo a inner join categoria c on a.id_categoria = c.id_categoria where a.nombre LIKE ? and a.stock > 0 and a.activo = '1' ORDER BY a.id_articulo asc Limit ? | ?");
            ps.setString(1, "%" +texto+ "%");
            ps.setInt(2,(numpagina-1)*totalporpagina);
            ps.setInt(3, totalporpagina);
            
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Articulo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getBoolean(10)));
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

    public Articulo obtenerArticuloCodigoCompra(String Codigo){
        Articulo art = null;
       try { 
            ps = CON.getConexion().prepareStatement("select id_articulo,codigo_barra,nombre,precio_venta,stock from articulo where codigo_barra = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ps.setString(1,Codigo);
            rs = ps.executeQuery();
            
            while(rs.next()){
            art = new Articulo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
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
    return art;
    }
    
      public Articulo obtenerArticuloCodigoSalida(String Codigo){
        Articulo art = null;
       try { 
            ps = CON.getConexion().prepareStatement("select id_articulo,codigo_barra,nombre,precio_venta,stock from articulo where codigo_barra = ? and stock > 0 and activo = '1'",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ps.setString(1,Codigo);
            rs = ps.executeQuery();
            
            while(rs.next()){
            art = new Articulo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
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
    return art;
    }
    
    
    
    @Override
    public boolean insertar(Articulo obj) {
    resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("INSERT into articulo(id_categoria,codigo_barra,nombre,precio_venta,stock,descripcion,imagen,activo) Values (?,?,?,?,?,?,?,'1')");
           ps.setInt(1,obj.getId_categoria());
           ps.setString(2, obj.getCodigo_barrar());
           ps.setString(3, obj.getNombre());
           ps.setDouble(4, obj.getPrecio_venta());
           ps.setInt(5, obj.getStock());
           ps.setString(6, obj.getDescripcion());
           ps.setString(7, obj.getImagen());
           
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
    public boolean actualizar(Articulo obj) {
     resp = false;
    
        try {
           ps = CON.getConexion().prepareStatement("update articulo set id_categoria = ?, codigo_barra = ?, nombre = ?, precio_venta = ?, stock = ? ,descripcion = ? , imagen = ? where id_articulo=?");
           ps.setInt(1,obj.getId_categoria());
           ps.setString(2, obj.getCodigo_barrar());
           ps.setString(3, obj.getNombre());
           ps.setDouble(4, obj.getPrecio_venta());
           ps.setInt(5, obj.getStock());
           ps.setString(6, obj.getDescripcion());
           ps.setString(7, obj.getImagen());
           ps.setInt(8, obj.getId_articulo());
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
           ps = CON.getConexion().prepareStatement("update articulo set activo = '0' where id_articulo=?");
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
           ps = CON.getConexion().prepareStatement("update articulo set activo = '1' where id_articulo=?");
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
           ps = CON.getConexion().prepareStatement("Select Count(id_articulo) from articulo");
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
           
           ps = CON.getConexion().prepareStatement("Select nombre from articulo where nombre = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
