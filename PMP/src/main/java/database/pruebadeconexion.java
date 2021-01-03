
package database;

public class pruebadeconexion {
  
    public static void main(String[] args) {
        Conexion conectar = Conexion.getInstancia();
        conectar.getConexion();
        
        if(conectar.con!=null)
        {
            System.out.println("Conectardo");
        
        
        }else
        {
        
        System.out.println("Desconectado");
        
        }
            
    }
    
}
