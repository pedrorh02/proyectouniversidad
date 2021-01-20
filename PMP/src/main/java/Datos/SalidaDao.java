/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.interfaces.CrudSalidaInterface;
import java.sql.Connection;
import database.Conexion;
import entidades.DetalleSalida;
import entidades.Salida;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo de Pedro
 */
public class SalidaDao implements CrudSalidaInterface<Salida, DetalleSalida>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    
    public SalidaDao()
    {
    CON = Conexion.getInstancia();
    }
    
    
    
    
    @Override
    public List<Salida> listar(String texto, int totalporpagina, int numpagina) {
        
    List<Salida> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select v.id,v.id_usuario,u.nombre as usuario_nombre, v.id_persona, p.nombre as persona_nombre,  v.tipo_comprobante, v.serie_comprobante, v.numero_comprobante, v.fecha, v.impuesto, v.total, \n" +
            "  v.estado from salida_inv v Inner Join persona p on v.id_persona = p.id inner join usuario u on v.id_usuario = u.id where v.numero_comprobante LIKE ? ORDER by v.id asc Limit ?| ? ");
            ps.setString(1, "%" +texto+ "%");
            ps.setInt(2,(numpagina-1)*totalporpagina);
            ps.setInt(3, totalporpagina);
            
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Salida(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9),rs.getDouble(10),rs.getDouble(11),rs.getString(12)));
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
    public List<DetalleSalida> listarDetalle(int id) {
        List<DetalleSalida> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement(" select a.id_articulo, a.codigo_barra, a.nombre,a.stock, d.cantidad, d.precio,d.descuento, ((d.cantidad*d.precio)-d.descuento) as sub_total  from detalle_salida_inv d inner Join articulo a ON d.articulo_id = a.id_articulo where d.salida_inv_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new DetalleSalida(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8)));
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
    public boolean insertar(Salida obj) {
    resp = false;
    Connection conn = null;
        try {
           conn = CON.getConexion();
           conn.setAutoCommit(false);
           String sql = "Insert Into Salida_inv (id_persona,id_usuario,tipo_comprobante,serie_comprobante,numero_comprobante,fecha,impuesto,total,estado)Values (?,?,?,?,?,now(),?,?,?)";
           
           ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           ps.setInt(1,obj.getPersonaId());
           ps.setInt(2,obj.getUsuarioId());
           ps.setString(3, obj.getTipoComprobante());
           ps.setString(4, obj.getSerieComprobante());
           ps.setString(5, obj.getNumComprobante());
           ps.setDouble(6, obj.getImpuesto());
           ps.setDouble(7, obj.getTotal());
           ps.setString(8, "Aceptado");
           
           int filasAfectadas = ps.executeUpdate();
           rs= ps.getGeneratedKeys();
           int idGenerado =0;
           
           if(rs.next()){
           idGenerado = rs.getInt(1);
           }
           
           
           if(filasAfectadas==1){
              String sqldetalle = "insert into detalle_Salida_inv (salida_inv_id,almacen,articulo_id,cantidad,precio,descuento) Values (?,1,?,?,?,?)";
              ps=conn.prepareStatement(sqldetalle);
              for(DetalleSalida items: obj.getDetalles())
              {
                  ps.setInt(1, idGenerado);
                  ps.setInt(2,items.getArticuloId());
                  ps.setInt(3, items.getCantidad());
                  ps.setDouble(4, items.getPrecio());
                  ps.setDouble(5, items.getDescuento());
                  resp = ps.executeUpdate()>0;
              }
            conn.commit();
               
           }else{
           conn.rollback();
           }
             } catch (SQLException e) {
        
                 
        try { 
            if(conn!=null){
            conn.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalidaDao.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
                 JOptionPane.showMessageDialog(null, e.getMessage());
             }finally{
                    try {
                        if(rs!=null) rs.close();
                        if(ps!=null) ps.close();
                        if(conn!=null) conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SalidaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }

         return resp;  
    }

    @Override
    public boolean anular(int id) {
        resp = false;

        try {
            ps = CON.getConexion().prepareStatement("update Salida_inv set estado = 'Anulada' where id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {

                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.getDesconectar();
        }

        return resp;
    
    
    }

    @Override
    public int total() {
       int totalRegistros = 0;
    
        try {
           ps = CON.getConexion().prepareStatement("Select Count(id) from salida_inv");
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
    public boolean existe(String texto1,String texto2) {
        resp = false;
    
        try {
           
           ps = CON.getConexion().prepareStatement("Select id from Salida_inv where serie_comprobante = ? and numero_comprobante = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
           ps.setString(1, texto1);
           ps.setString(2, texto2);
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
