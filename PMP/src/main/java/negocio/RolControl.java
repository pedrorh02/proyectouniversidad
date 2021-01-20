/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.RolDAO;
import entidades.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo de Pedro
 */
public class RolControl {
    private final RolDAO DATOS;
    private Rol obj;
    private DefaultTableModel modelotabla;
    public int registrosMostrado;  
    
    public RolControl(){
    this.DATOS = new RolDAO();
    this.obj = new Rol();
    this.registrosMostrado = 0;
    
    }
    
        public DefaultTableModel listar(){
        List<Rol> lista = new ArrayList();
        lista.addAll(DATOS.listar());
        
        String[] titulos = {"Codigo", "Nombre", "Descripcion"};
        this.modelotabla = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        
        
        };
        
        String[] registro = new String[3];
        this.registrosMostrado = 0;
        
        for(Rol item:lista){
           
            registro[0]= Integer.toString(item.getId());
            registro[1]= item.getNombre();
            registro[2]= item.getDescripcion();
         
            this.modelotabla.addRow(registro);
            this.registrosMostrado = this.registrosMostrado +1;
             
        }
        return this.modelotabla;
    
    }
    
        public int total(){
          return DATOS.total();
    
    }
    
      public int totalMostrado(){
      
      return this.registrosMostrado;
      
      }
      
    
    
}
