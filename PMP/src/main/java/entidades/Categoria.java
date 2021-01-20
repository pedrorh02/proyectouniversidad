/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author Equipo de Pedro
 */
public class Categoria {
    
    private int id_categoria;
    private String nombre;
    private String descripcion;
    private boolean activo;

    public Categoria(int id_categoria, String nombre) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }
    
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
        return  nombre ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id_categoria;
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + Objects.hashCode(this.descripcion);
        hash = 41 * hash + (this.activo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (this.id_categoria != other.id_categoria) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
