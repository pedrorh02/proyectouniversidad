/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Equipo de Pedro
 */
public class Categoria {
    
    private int id_categoria;
    private String nombre;
    private String descripcion;
    private boolean activo;
    
    //contructores

    public Categoria() {
    }

    public Categoria(int id_categoria, String nombre, String descripcion, boolean activo) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }
    
    //metodos set y getter

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    //metodo tostring

    @Override
    public String toString() {
        return "Categoria{" + "id_categoria=" + id_categoria + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + '}';
    }
    
    
}
