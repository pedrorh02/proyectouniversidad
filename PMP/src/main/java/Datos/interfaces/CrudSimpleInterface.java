
package Datos.interfaces;

import java.util.List;

/**
 *
 * @author Equipo de Pedro
 * @param <T>
 */
public interface CrudSimpleInterface<T> {
   public List<T> listar (String texto);
   public boolean insertar(T obj); 
   public boolean actualizar(T obj);
   public boolean desactualizar(int id);   
   public boolean activar(int id);
   public int total(); 
   public boolean existe(String texto); 
}
