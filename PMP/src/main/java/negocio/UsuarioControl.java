/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.RolDAO;
import Datos.UsuarioDAO;
import entidades.Rol;
import entidades.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class UsuarioControl {

    private final UsuarioDAO DATOS;
    private final RolDAO DATOSRol;
    private Usuario obj;
    private DefaultTableModel modelotabla;
    public int registrosMostrado;

    public UsuarioControl() {

        this.DATOS = new UsuarioDAO();
        this.DATOSRol = new RolDAO();
        this.obj = new Usuario();
        this.registrosMostrado = 0;

    }

    public DefaultTableModel listar(String texto, int totalporpagina, int numpagina) {
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto, totalporpagina, numpagina));
       
        String[] titulos = {"Codigo", "Rol Id", "Rol", "Usuario", "Documento", "# documento", "Direccion", "Telefono", "Email","Clave", "Estado"};
        this.modelotabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String estado;
        String[] registro = new String[11];
        this.registrosMostrado = 0;
        for (Usuario item : lista) {
            if (item.isActivo()) {
                estado = "Activado";
            } else {
                estado = "Desactivado";
            }
            registro[0] = Integer.toString(item.getId());
            registro[1] = Integer.toString(item.getRolId());
            registro[2] = item.getRolNombre();
            registro[3] = item.getNombre();
            registro[4] = item.getTipodocumento();
            registro[5] = item.getNumDocumento();
            registro[6] = item.getDirecion();
            registro[7] = item.getTelefono();
            registro[8] = item.getEmail();
            registro[9] = item.getClave();
            registro[10] = estado;
            this.modelotabla.addRow(registro);
            this.registrosMostrado = this.registrosMostrado + 1;

        }
        return this.modelotabla;

    }
    
    public String login(String email, String clave){
    String resp = "0";
    
        Usuario usu = this.DATOS.login(email, encriptar(clave));
        if(usu!=null){
        
            if(usu.isActivo()){
                Variables.usuarioId = usu.getId();
                Variables.rolId = usu.getRolId();
                Variables.rolNombre = usu.getRolNombre();
                Variables.UsuarioNOmbre = usu.getNombre();
                Variables.UsuarioEmail = usu.getEmail();
                resp = "1";
            
            }else{
            
            resp = "2";
            }
        
        }
        
        return resp;
    
    
    }
    
    public DefaultComboBoxModel selecionar(){
    
    DefaultComboBoxModel items = new DefaultComboBoxModel();
    List<Rol> lista = new ArrayList();
    lista = DATOSRol.selecionar();
    for(Rol item : lista){
    
        items.addElement(new Rol(item.getId(),item.getNombre()));
    
    }
    return items;
    
    }
    
    private static String encriptar(String valor){
    
        MessageDigest md;
        
        try {
            md = MessageDigest.getInstance("SHA-256");
            
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        
        byte[] hash = md.digest(valor.getBytes());
        StringBuffer sb = new StringBuffer();
        
        for(byte b: hash){
        
            sb.append(String.format("%02x", b));
        }
        
        return sb.toString();
    
    }
    

    public String insertar(int Rol_id, String nombre, String TipoDocumento, String numDocumento, String drieciion, String telefono, String email, String clave) {

        if (DATOS.existe(email)) {
            return "El registro ya existe";

        } else {
            obj.setRolId(Rol_id);
            obj.setNombre(nombre);
            obj.setTipodocumento(TipoDocumento);
            obj.setNumDocumento(numDocumento);
            obj.setDirecion(drieciion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            obj.setClave(encriptar(clave));
            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro";

            }

        }

    }

    public String actualizar(int id,int Rol_id, String nombre, String TipoDocumento, String numDocumento, String drieciion, String telefono, String email, String emailAnt, String clave) {
        if (email.equals(emailAnt)) {
            obj.setId(id);
            obj.setRolId(Rol_id);
            obj.setNombre(nombre);
            obj.setTipodocumento(TipoDocumento);
            obj.setNumDocumento(numDocumento);
            obj.setDirecion(drieciion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            
            String encriptado;
            if(clave.length()==64){
                encriptado = clave;
            }else{
              encriptado = this.encriptar(clave);
            
            }
            obj.setClave(encriptado);
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
            obj.setRolId(Rol_id);
            obj.setNombre(nombre);
            obj.setTipodocumento(TipoDocumento);
            obj.setNumDocumento(numDocumento);
            obj.setDirecion(drieciion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            
            String encriptado;
            if(clave.length()==64){
                encriptado = clave;
            }else{
              encriptado = this.encriptar(clave);
            }
            obj.setClave(encriptado);
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
