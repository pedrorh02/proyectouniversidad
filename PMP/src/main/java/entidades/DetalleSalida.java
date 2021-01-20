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
public class DetalleSalida {
    private int id;
    private int salidadId;
    private int articuloId;
    private String articuloCodigo;
    private String articuloNombre;
    private int stock;
    private int cantidad;
    private double precio;
    private double descuento;
    private double subTotal;

    public DetalleSalida() {
    }

    public DetalleSalida(int id, int salidaId, int articuloId, String articuloCodigo, String articuloNombre, int stock, int cantidad, double precio, double descuento, double subTotal) {
        this.id = id;
        this.salidadId = salidaId;
        this.articuloId = articuloId;
        this.articuloCodigo = articuloCodigo;
        this.articuloNombre = articuloNombre;
        this.stock = stock;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.subTotal = subTotal;
    }

    public DetalleSalida(int articuloId, String articuloCodigo, String articuloNombre, int stock, int cantidad, double precio, double descuento, double subTotal) {
        this.articuloId = articuloId;
        this.articuloCodigo = articuloCodigo;
        this.articuloNombre = articuloNombre;
        this.stock = stock;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.subTotal = subTotal;
    }

    public DetalleSalida(int articuloId, int cantidad, double precio, double descuento) {
        this.articuloId = articuloId;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalidadId() {
        return salidadId;
    }

    public void setSalidadId(int salidadId) {
        this.salidadId = salidadId;
    }

    public int getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    public String getArticuloCodigo() {
        return articuloCodigo;
    }

    public void setArticuloCodigo(String articuloCodigo) {
        this.articuloCodigo = articuloCodigo;
    }

    public String getArticuloNombre() {
        return articuloNombre;
    }

    public void setArticuloNombre(String articuloNombre) {
        this.articuloNombre = articuloNombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    
    
}
