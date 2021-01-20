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
public class DetalleEntrada {
    private int id;
    private int detalleId;
    private int articuloId;
    private String Articulo_codigo;
    private String articuloNombre;
    
    private int cantidad;
    private double precio;
    private double subTotal;

    public DetalleEntrada() {
    }

    public DetalleEntrada(int id, int detalleId, int articuloId, String Articulo_codigo, String articuloNombre, int cantidad, double precio, double subTotal) {
        this.id = id;
        this.detalleId = detalleId;
        this.articuloId = articuloId;
        this.Articulo_codigo = Articulo_codigo;
        this.articuloNombre = articuloNombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
    }

    public DetalleEntrada(int articuloId, int cantidad, double precio) {
        this.articuloId = articuloId;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleEntrada(int articuloId, String Articulo_codigo, String articuloNombre, int cantidad, double precio, double subTotal) {
        this.articuloId = articuloId;
        this.Articulo_codigo = Articulo_codigo;
        this.articuloNombre = articuloNombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
    }

   
  
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(int detalleId) {
        this.detalleId = detalleId;
    }

    public int getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    public String getArticulo_codigo() {
        return Articulo_codigo;
    }

    public void setArticulo_codigo(String Articulo_codigo) {
        this.Articulo_codigo = Articulo_codigo;
    }

    public String getArticuloNombre() {
        return articuloNombre;
    }

    public void setArticuloNombre(String articuloNombre) {
        this.articuloNombre = articuloNombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    
    
}
