/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.ArticuloDAO;
import Datos.SalidaDao;
import entidades.Articulo;
import entidades.DetalleSalida;
import entidades.Salida;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SalidaControl {

    private final SalidaDao DATOS;
    private final ArticuloDAO DATOSART;
    private Salida obj;
    private DefaultTableModel modelotabla;
    public int registrosMostrado;

    public SalidaControl() {

        this.DATOS = new SalidaDao();
        this.DATOSART = new ArticuloDAO();
        this.obj = new Salida();
        this.registrosMostrado = 0;

    }

    public DefaultTableModel listar(String texto, int totalporpagina, int numpagina) {
        List<Salida> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto, totalporpagina, numpagina));
       
        String[] titulos = {"Codigo", "Usuario ID", "Usuario", "Cliente ID", "Cliente", "Tipo Comprobante", "Serie", "Numero", "fecha","Impuesto","Total", "Estado"};
        this.modelotabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] registro = new String[12];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.registrosMostrado = 0;
        for (Salida item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = Integer.toString(item.getUsuarioId());
            registro[2] = item.getUsuarioNombre();
            registro[3] = Integer.toString(item.getPersonaId());
            registro[4] = item.getPersonaNombre();
            registro[5] = item.getTipoComprobante();
            registro[6] = item.getSerieComprobante();
            registro[7] = item.getNumComprobante();
            registro[8] = sdf.format(item.getFecha());
            registro[9] = Double.toString(item.getImpuesto());
            registro[10] = Double.toString(item.getTotal());
            registro[11] = item.getEstado();
            this.modelotabla.addRow(registro);
            this.registrosMostrado = this.registrosMostrado + 1;

        }
        return this.modelotabla;

    }
 
  public DefaultTableModel listarDetalle(int id) {
        List<DetalleSalida> lista = new ArrayList();
        lista.addAll(DATOS.listarDetalle(id));
       
        String[] titulos = {"Codigo", "Codigo ID", "Articulo","Stock", "Cantidad", "Precio", "Sub Total","Descuento"};
        this.modelotabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] registro = new String[8];
        this.registrosMostrado = 0;
        for (DetalleSalida item : lista) {
            registro[0] = Integer.toString(item.getArticuloId());
            registro[1] = item.getArticuloCodigo();
            registro[2] = item.getArticuloNombre();
            registro[3] = Integer.toString(item.getStock());
            registro[4] = Integer.toString(item.getCantidad());
            registro[5] = Double.toString(item.getPrecio());
            registro[6] = Double.toString(item.getDescuento());
            registro[7] = Double.toString(item.getSubTotal());
            
            this.modelotabla.addRow(registro);
        }
        return this.modelotabla;

    }    
    
    
    
    public Articulo ObtenrArticuloCodigoSalida(String Codigo){
    Articulo art = DATOSART.obtenerArticuloCodigoSalida(Codigo);
    return art;
    
    
    }
    
    
   public String insertar(int persona_id, String tipo_comprobante, String serie, String numComprobante, double impuesto, double total, DefaultTableModel modeloDetalle) {
      
        if (DATOS.existe(serie,numComprobante)) {
            return "El registro ya existe";

        } else {
            obj.setUsuarioId(Variables.usuarioId);
            obj.setPersonaId(persona_id);
            obj.setTipoComprobante(tipo_comprobante);
            obj.setSerieComprobante(serie);
            obj.setNumComprobante(numComprobante);
            obj.setImpuesto(impuesto);
            obj.setTotal(total);
            
            
            List<DetalleSalida> detalles = new ArrayList();
            int articuloID;
            int cantidad;
            double precio;
            double descuento;
            
            for(int i = 0 ; i < modeloDetalle.getRowCount() ; i++ ){
                articuloID = Integer.parseInt(String.valueOf(modeloDetalle.getValueAt(i,0)) );
                
                cantidad = Integer.parseInt(String.valueOf(modeloDetalle.getValueAt(i,4)) );
                precio = Double.parseDouble(String.valueOf(modeloDetalle.getValueAt(i,5)) );
                descuento = Double.parseDouble(String.valueOf(modeloDetalle.getValueAt(i,6)) );
                detalles.add(new DetalleSalida(articuloID,cantidad, precio,descuento));
            }
            
            obj.setDetalles(detalles);
                       
            if (DATOS.insertar(obj)) {
                JOptionPane.showMessageDialog(null,"Prueba");
                return "OK";
            } else {
                return "Error en el registro";

            }

        }

    }

    public String anular(int id) {

        if (DATOS.anular(id)) {
            return "OK";

        } else {
            return "No se puede desactivar el registro";
        }

    }

    public int total() {
        return DATOS.total();

    }

    public int totalMostrado() {

        return this.registrosMostrado;

    }

}
