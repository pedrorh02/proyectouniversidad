/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.PersonaDAO;
import entidades.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PersonaControl {

    private final PersonaDAO DATOS;
    private Persona obj;
    private DefaultTableModel modelotabla;
    public int registrosMostrado;

    public PersonaControl() {

        this.DATOS = new PersonaDAO();
        this.obj = new Persona();
        this.registrosMostrado = 0;

    }

    public DefaultTableModel listar(String texto, int totalporpagina, int numpagina) {
        List<Persona> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto, totalporpagina, numpagina));
       
        String[] titulos = {"Codigo", "Tipo de persona", "Persona", "Documento", "# Documento", "Direccion", "telefono", "Email", "Estado"};
        this.modelotabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String estado;
        String[] registro = new String[9];
        this.registrosMostrado = 0;
        for (Persona item : lista) {
            if (item.isActivo()) {
                estado = "Activado";
            } else {
                estado = "Desactivado";
            }
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getTipoPersona();
            registro[2] = item.getNombre();
            registro[3] = item.getIdTipoDocumento();
            registro[4] = item.getNumdocumento();
            registro[5] = item.getDirecion();
            registro[6] = item.getTelefono();
            registro[7] = item.getEmail();
            registro[8] = estado;
            this.modelotabla.addRow(registro);
            this.registrosMostrado = this.registrosMostrado + 1;

        }
        return this.modelotabla;

    }
    
    public DefaultTableModel listarTipo(String texto, int totalporpagina, int numpagina, String tipo_persona) {
        List<Persona> lista = new ArrayList();
        lista.addAll(DATOS.listarTipo(texto, totalporpagina, numpagina,tipo_persona));
       
        String[] titulos = {"Codigo", "Tipo de persona", "Persona", "Documento", "# Documento", "Direccion", "telefono", "Email", "Estado"};
        this.modelotabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String estado;
        String[] registro = new String[9];
        this.registrosMostrado = 0;
        for (Persona item : lista) {
            if (item.isActivo()) {
                estado = "Activado";
            } else {
                estado = "Desactivado";
            }
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getTipoPersona();
            registro[2] = item.getNombre();
            registro[3] = item.getIdTipoDocumento();
            registro[4] = item.getNumdocumento();
            registro[5] = item.getDirecion();
            registro[6] = item.getTelefono();
            registro[7] = item.getEmail();
            registro[8] = estado;
            this.modelotabla.addRow(registro);
            this.registrosMostrado = this.registrosMostrado + 1;

        }
        return this.modelotabla;

    }   
    

    public String insertar(String id_tipo_persona, String nombre, String TipoDocumento, String numDocumento, String drieciion, String telefono, String email) {

        if (DATOS.existe(email)) {
            return "El registro ya existe";

        } else {
            obj.setTipoPersona(id_tipo_persona);
            obj.setNombre(nombre);
            obj.setIdTipoDocumento(TipoDocumento);
            obj.setNumdocumento(numDocumento);
            obj.setDirecion(drieciion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro";

            }

        }

    }

    public String actualizar(int id,String id_tipo_persona, String nombre, String nombreAnt, String TipoDocumento, String numDocumento, String drieciion, String telefono, String email) {
        if (nombre.equals(nombreAnt)) {
            obj.setId(id);
            obj.setIdTipoDocumento(id_tipo_persona);
            obj.setNombre(nombre);
            obj.setIdTipoDocumento(TipoDocumento);
            obj.setNumdocumento(numDocumento);
            obj.setDirecion(drieciion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "error en la actualizacion";
            }
        } else {
            if (DATOS.existe(email)) {
                return "El registro ya existe";
            } else {
            obj.setId(id);
            obj.setTipoPersona(id_tipo_persona);
            obj.setNombre(nombre);
            obj.setIdTipoDocumento(TipoDocumento);
            obj.setNumdocumento(numDocumento);
            obj.setDirecion(drieciion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            
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
