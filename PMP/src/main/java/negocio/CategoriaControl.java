/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.CategoriaDAO;
import entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CategoriaControl {
    private final CategoriaDAO DATOS;
    private Categoria obj;
    private DefaultTableModel modelotabla;
    public int registrosMostrado;
    
    public CategoriaControl(){
    
    this.DATOS = new CategoriaDAO();
    this.obj = new Categoria();
    this.registrosMostrado=0;
    
    }
    
    
    public DefaultTableModel listar(String texto){
        List<Categoria> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        
        String[] titulos = {"Codigo", "Nombre", "Descripcion", "Estado"};
        this.modelotabla = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        
        
        };
        
        String estado;
        String[] registro = new String[4];
        this.registrosMostrado = 0;
        
        for(Categoria item:lista){
            if(item.isActivo()){
                estado = "Activo";
            }else{
                estado = "Desactivado";
            }
            registro[0]= Integer.toString(item.getId_categoria());
            registro[1]= item.getNombre();
            registro[2]= item.getDescripcion();
            registro[3]=estado;
            this.modelotabla.addRow(registro);
            this.registrosMostrado = this.registrosMostrado +1;
             
        }
        return this.modelotabla;
    
    }
    
    public String insertar(String nombre, String descripcion){
       
        if(DATOS.existe(nombre)){
        return "El registro ya existe";
        
        }else{
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            if(DATOS.insertar(obj)){
            return "OK";
            }
            else{
            return "Error en el registro";
            
            }        
        
        }
    
    
    }
    
    
    public String actualizar(int id, String nombre, String nombreAnt, String descripcion){
       if(nombre.equals(nombreAnt)){
         obj.setId_categoria(id);
         obj.setNombre(nombre);
         obj.setDescripcion(descripcion);
         if(DATOS.actualizar(obj)){
          return "OK";
         }else{
         return "error en la actualizacion";
         }
       }else{
         if(DATOS.existe(nombre)){
             return "El registro ya existe";
            }else{
             obj.setId_categoria(id);
             obj.setNombre(nombre);
             obj.setDescripcion(descripcion);
               if(DATOS.actualizar(obj)){
               return "OK";
               }else{
                   return "Error en la actualizacion";
               }
            }         
         }
    
    
    }
    
    public String desactivar(int id){
        
        if(DATOS.desactualizar(id))
        {
        return "OK";
        
        }else{
        return "No se puede desactivar el registro";
        }
    
    }
    
   public String activar(int id){
      if(DATOS.activar(id))
        {
        return "OK";
        
        }else{
        return "No se puede Activar el registro";
        }
    
    }    
   
      public int total(){
          return DATOS.total();
    
    }
    
      public int totalMostrado(){
      
      return this.registrosMostrado;
      
      }
      
      
    
    
}
