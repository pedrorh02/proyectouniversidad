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
public class Articulo {
    private int id_articulo;
    private int id_categoria;
    private String categorianombre;
    private String codigo_barrar;
    private String nombre;
    private Double precio_venta;
    private int stock;
    private String descripcion;
    private String imagen;
    private boolean activo;

    public Articulo() {
    }

    public Articulo(int id_articulo, int id_categoria,String categorianombre, String codigo_barrar, String nombre, Double precio_venta, int stock, String descripcion, String imagen, boolean activo) {
        this.id_articulo = id_articulo;
        this.id_categoria = id_categoria;
        this.categorianombre = categorianombre;
        this.codigo_barrar = codigo_barrar;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.activo = activo;
    }

    public Articulo(int id_articulo, String codigo_barrar, String nombre, Double precio_venta, int stock) {
        this.id_articulo = id_articulo;
        this.codigo_barrar = codigo_barrar;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.stock = stock;
    }
    

    public String getCategorianombre() {
        return categorianombre;
    }

    public void setCategorianombre(String categorianombre) {
        this.categorianombre = categorianombre;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCodigo_barrar() {
        return codigo_barrar;
    }

    public void setCodigo_barrar(String codigo_barrar) {
        this.codigo_barrar = codigo_barrar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "articulo{" + "id_articulo=" + id_articulo + ", id_categoria=" + id_categoria + ", categorianombre="+ categorianombre + ", codigo_barrar=" + codigo_barrar + ", nombre=" + nombre + ", precio_venta=" + precio_venta + ", stock=" + stock + ", descripcion=" + descripcion + ", imagen=" + imagen + ", activo=" + activo + '}';
    }
   
}
