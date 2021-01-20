/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.ArticuloDAO;
import Datos.CategoriaDAO;
import entidades.Articulo;
import entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ArticuloControl {

    private final ArticuloDAO DATOS;
    private final CategoriaDAO DATOSCAT;
    private Articulo obj;
    private DefaultTableModel modelotabla;
    public int registrosMostrado;

    public ArticuloControl() {

        this.DATOS = new ArticuloDAO();
        this.DATOSCAT = new CategoriaDAO();
        this.obj = new Articulo();
        this.registrosMostrado = 0;

    }

    public DefaultTableModel listar(String texto, int totalporpagina, int numpagina) {
        List<Articulo> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto, totalporpagina, numpagina));
       
        String[] titulos = {"Codigo", "Cod Categoria", "Categoria", "Cod de barra", "Nombre", "Precio Venta", "stock", "Descripcion", "Imagen", "Estado"};
        this.modelotabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String estado;
        String[] registro = new String[10];
        this.registrosMostrado = 0;
        for (Articulo item : lista) {
            if (item.isActivo()) {
                estado = "Activado";
            } else {
                estado = "Desactivado";
            }
            registro[0] = Integer.toString(item.getId_articulo());
            registro[1] = Integer.toString(item.getId_categoria());
            registro[2] = item.getCategorianombre();
            registro[3] = item.getCodigo_barrar();
            registro[4] = item.getNombre();
            registro[5] = Double.toString(item.getPrecio_venta());
            registro[6] = Integer.toString(item.getStock());
            registro[7] = item.getDescripcion();
            registro[8] = item.getImagen();
            registro[9] = estado;
            this.modelotabla.addRow(registro);
            this.registrosMostrado = this.registrosMostrado + 1;

        }
        return this.modelotabla;

    }
    
       public DefaultTableModel listararticulosSalida(String texto, int totalporpagina, int numpagina) {
        List<Articulo> lista = new ArrayList();
        lista.addAll(DATOS.listarArticulosSalida(texto, totalporpagina, numpagina));
       
        String[] titulos = {"Codigo", "Cod Categoria", "Categoria", "Cod de barra", "Nombre", "Precio Venta", "stock", "Descripcion", "Imagen", "Estado"};
        this.modelotabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String estado;
        String[] registro = new String[10];
        this.registrosMostrado = 0;
        for (Articulo item : lista) {
            if (item.isActivo()) {
                estado = "Activado";
            } else {
                estado = "Desactivado";
            }
            registro[0] = Integer.toString(item.getId_articulo());
            registro[1] = Integer.toString(item.getId_categoria());
            registro[2] = item.getCategorianombre();
            registro[3] = item.getCodigo_barrar();
            registro[4] = item.getNombre();
            registro[5] = Double.toString(item.getPrecio_venta());
            registro[6] = Integer.toString(item.getStock());
            registro[7] = item.getDescripcion();
            registro[8] = item.getImagen();
            registro[9] = estado;
            this.modelotabla.addRow(registro);
            this.registrosMostrado = this.registrosMostrado + 1;

        }
        return this.modelotabla;

    }
    
    
    public DefaultComboBoxModel selecionar(){
    
    DefaultComboBoxModel items = new DefaultComboBoxModel();
    List<Categoria> lista = new ArrayList();
    lista = DATOSCAT.selecionar();
    for(Categoria item : lista){
    
        items.addElement(new Categoria(item.getId_categoria(),item.getNombre()));
    
    }
    return items;
    
    }
    
    

    public String insertar(int Id_categoria, String codigo_barra, String nombre, double precio_venta, int stock, String descripcion, String imagen) {

        if (DATOS.existe(nombre)) {
            return "El registro ya existe";

        } else {
            obj.setId_categoria(Id_categoria);
            obj.setCodigo_barrar(codigo_barra);
            obj.setNombre(nombre);
            obj.setPrecio_venta(precio_venta);
            obj.setStock(stock);
            obj.setDescripcion(descripcion);
            obj.setImagen(imagen);
            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro";

            }

        }

    }

    public String actualizar(int id, int Id_categoria, String codigo_barra, String nombre, String nombreAnt, double precio_venta, int stock, String descripcion, String imagen) {
        if (nombre.equals(nombreAnt)) {
            obj.setId_articulo(id);
            obj.setId_categoria(Id_categoria);
            obj.setCodigo_barrar(codigo_barra);
            obj.setNombre(nombre);
            obj.setPrecio_venta(precio_venta);
            obj.setStock(stock);
            obj.setDescripcion(descripcion);
            obj.setImagen(imagen);
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "error en la actualizacion";
            }
        } else {
            if (DATOS.existe(nombre)) {
                return "El registro ya existe";
            } else {
                obj.setId_articulo(id);
                obj.setId_categoria(Id_categoria);
                obj.setCodigo_barrar(codigo_barra);
                obj.setNombre(nombre);
                obj.setPrecio_venta(precio_venta);
                obj.setStock(stock);
                obj.setDescripcion(descripcion);
                obj.setImagen(imagen);
                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Error en la actualizacion";
                }
            }
        }

    }

    public String desactivar(int id) {

        if (DATOS.desactualizar(id)) {
            return "OK";

        } else {
            return "No se puede desactivar el registro";
        }

    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";

        } else {
            return "No se puede Activar el registro";
        }

    }

    public int total() {
        return DATOS.total();

    }

    public int totalMostrado() {

        return this.registrosMostrado;

    }

}
