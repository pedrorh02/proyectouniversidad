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
public class Usuario {
    private int id;
    private int rolId;
    private String rolNombre;
    private String nombre;
    private String tipodocumento;
    private String numDocumento;
    private String direcion;
    private String telefono;
    private String email;
    private String clave;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(int id, int rolId, String rolNombre, String nombre, String tipodocumento, String numDocumento, String direcion, String telefono, String email, boolean activo) {
        this.id = id;
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.nombre = nombre;
        this.tipodocumento = tipodocumento;
        this.numDocumento = numDocumento;
        this.direcion = direcion;
        this.telefono = telefono;
        this.email = email;
        this.activo = activo;
    }
    
    

    public Usuario(int id, int rolId, String rolNombre, String nombre, String tipodocumento, String numDocumento, String direcion, String telefono, String email, String clave, boolean activo) {
        this.id = id;
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.nombre = nombre;
        this.tipodocumento = tipodocumento;
        this.numDocumento = numDocumento;
        this.direcion = direcion;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
        
            
    
}
