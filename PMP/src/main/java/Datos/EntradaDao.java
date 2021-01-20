/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.interfaces.CrudEntradaInterface;
import java.sql.Connection;
import database.Conexion;
import entidades.DetalleEntrada;
import entidades.Entrada;
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
public class EntradaDao implements CrudEntradaInterface<Entrada, DetalleEntrada>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    
    public EntradaDao()
    {
    CON = Conexion.getInstancia();
    }
    
    
    
    
    @Override
    public List<Entrada> listar(String texto, int totalporpagina, int numpagina) {
        
    List<Entrada> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement("select i.id,i.id_usuario,u.nombre as usuario_nombre, i.id_persona, p.nombre as persona_nombre,  i.tipo_comprobante, i.serie_comprobante, i.numero_comprobante, i.fecha, i.impuesto, i.total, \n" +
            "  i.estado from entrada_inv i Inner Join persona p on i.id_persona = p.id inner join usuario u on i.id_usuario = u.id where i.numero_comprobante LIKE ? ORDER by i.id asc Limit ?| ? ");
            ps.setString(1, "%" +texto+ "%");
            ps.setInt(2,(numpagina-1)*totalporpagina);
            ps.setInt(3, totalporpagina);
            
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new Entrada(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9),rs.getDouble(10),rs.getDouble(11),rs.getString(12)));
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
    public List<DetalleEntrada> listarDetalle(int id) {
        List<DetalleEntrada> registros = new ArrayList();
        try {
            ps = CON.getConexion().prepareStatement(" select a.id_articulo, a.codigo_barra, a.nombre, d.cantidad, d.precio, (d.cantidad*d.precio) as sub_total  from detalle_entrada_inv d inner Join articulo a ON d.articulo_id = a.id_articulo where d.entrada_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add (new DetalleEntrada(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6)));
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
    public boolean insertar(Entrada obj) {
    resp = false;
    Connection conn = null;
        try {
           conn = CON.getConexion();
           conn.setAutoCommit(false);
           String sql = "Insert Into entrada_inv (id_persona,id_usuario,tipo_comprobante,serie_comprobante,numero_comprobante,fecha,impuesto,total,estado)Values (?,?,?,?,?,now(),?,?,?)";
           
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
              String sqldetalle = "insert into detalle_entrada_inv (entrada_id,almacen,articulo_id,cantidad,precio) Values (?,1,?,?,?)";
              ps=conn.prepareStatement(sqldetalle);
              for(DetalleEntrada items: obj.getDetalles())
              {
                  ps.setInt(1, idGenerado);
                  ps.setInt(2,items.getArticuloId());
                  ps.setInt(3, items.getCantidad());
                  ps.setDouble(4, items.getPrecio());
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
            Logger.getLogger(EntradaDao.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
                 JOptionPane.showMessageDialog(null, e.getMessage());
             }finally{
                    try {
                        if(rs!=null) rs.close();
                        if(ps!=null) ps.close();
                        if(conn!=null) conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EntradaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }

         return resp;  
    }

    @Override
    public boolean anular(int id) {
        resp = false;

        try {
            ps = CON.getConexion().prepareStatement("update entrada_inv set estado = 'Anulada' where id=?");
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
           ps = CON.getConexion().prepareStatement("Select Count(id) from entrada_inv");
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
           
           ps = CON.getConexion().prepareStatement("Select id from entrada_inv where serie_comprobante = ? and numero_comprobante = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
