/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.interfaces;

import java.util.List;

/**
 *
 * @author Equipo de Pedro
 */
public interface CrudPaginadoINterface<T> {
   public List<T> listar (String texto,int totalporpagina, int numpagina);
   public boolean insertar(T obj); 
   public boolean actualizar(T obj);
   public boolean desactualizar(int id);   
   public boolean activar(int id);
   public int total(); 
   public boolean existe(String texto); 
    
}
