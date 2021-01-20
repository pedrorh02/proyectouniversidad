/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import negocio.CategoriaControl;

/**
 *
 * @author Equipo de Pedro
 */
public class FrmCategoria extends javax.swing.JInternalFrame {
    
    private final CategoriaControl CONTROL;
    private String accion;
    private String nombreanterior;
    
    public FrmCategoria() {
        initComponents();
        this.CONTROL = new CategoriaControl();
        this.listar("");
        tabGeneral.setEnabledAt(1,false);
        this.accion = "Guardar";
        txtid.setVisible(false);
    }

    private void listar(String texto)
    {
        
    tablaListado.setModel(this.CONTROL.listar(texto));
    TableRowSorter orden = new TableRowSorter(tablaListado.getModel());
    tablaListado.setRowSorter(orden);
    lbltotalRegistros.setText("Mostrando " + this.CONTROL.totalMostrado()+ " de un total de " + this.CONTROL.total() + " registros" );
    
    }
    
    private void limpiar(){
    txtNombre.setText("");
    txtDescripcion.setText("");
    }
    
    
    private void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje , "Sistema", JOptionPane.ERROR_MESSAGE);
    }
    
    private void mensajeOk(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje , "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
   
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabGeneral = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        lbltotalRegistros = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();

        setBackground(new java.awt.Color(239, 241, 228));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Categoria");

        tabGeneral.setBackground(new java.awt.Color(239, 241, 228));

        jPanel1.setBackground(new java.awt.Color(239, 241, 228));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nombre");

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\busqueda.png")); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListado);

        lbltotalRegistros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltotalRegistros.setText("Registros");

        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\anadir.png")); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\enjuague.png")); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnActivar.setBackground(new java.awt.Color(134, 88, 64));
        btnActivar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnActivar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\me-gusta.png")); // NOI18N
        btnActivar.setText("Activar");
        btnActivar.setBorderPainted(false);
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnDesactivar.setBackground(new java.awt.Color(83, 124, 142));
        btnDesactivar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDesactivar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\disgusto.png")); // NOI18N
        btnDesactivar.setText("Desactivar");
        btnDesactivar.setBorderPainted(false);
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Descripcion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Codigo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbltotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(31, 31, 31))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(135, 135, 135))
                                    .addComponent(txtbuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnBuscar)
                    .addComponent(btnEditar)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActivar)
                        .addComponent(btnDesactivar)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Listado", jPanel1);

        jPanel2.setBackground(new java.awt.Color(239, 241, 228));

        jLabel2.setText("Nombre(*)");

        jLabel3.setText("Descripcion");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\salvar_25.png")); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\eliminar25.png")); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setText("(*) Indica que es un campo obligatorio");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Mantenimiento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.listar(txtbuscar.getText());
        
        
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setSelectedIndex(1);
        this.accion = "Guardar";
        btnGuardar.setText("Guardar");
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
     tabGeneral.setEnabledAt(0, true);
     tabGeneral.setEnabledAt(1, false);
     tabGeneral.setSelectedIndex(0);   
     this.limpiar();
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       
        if(txtNombre.getText().length()== 0 || txtNombre.getText().length()>20){
            JOptionPane.showConfirmDialog(this, "Debes ingresar un Nombre, es Obligatorio, y no debe ser mayor a 20 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
           return;
        }
        if(txtDescripcion.getText().length()>255){
            JOptionPane.showConfirmDialog(this, "Ladescripcion no debe ser mayor a 255 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
           return;
        }
        
        String resp;
        if (this.accion.equals("editar")){
            resp = this.CONTROL.actualizar(Integer.parseInt(txtid.getText()),txtNombre.getText(),nombreanterior, txtDescripcion.getText());
                if(resp.equals("OK")){
                this.mensajeOk("Registrado correctamente ");
                this.limpiar();
                this.listar("");
               tabGeneral.setEnabledAt(0, true);
               tabGeneral.setEnabledAt(1, false);
               tabGeneral.setSelectedIndex(0);   
                
             }else{
                this.mensajeError(resp);
            }
            
        
        }else{//Guardar
                resp = this.CONTROL.insertar(txtNombre.getText(), txtDescripcion.getText());
                if(resp.equals("OK")){
                this.mensajeOk("Registrado correctamente ");
                this.limpiar();
                this.listar("");
                 
             }else{
                this.mensajeError(resp);
            }
           
        }
        
        
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       if(tablaListado.getSelectedRowCount()==1){
       String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
       String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1));
       this.nombreanterior = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1));
       String descripcion = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 2));
       
       txtid.setText(id);
       txtNombre.setText(nombre);
       txtDescripcion.setText(descripcion);
       tabGeneral.setEnabledAt(1, true);
       tabGeneral.setEnabledAt(0, false);
       tabGeneral.setSelectedIndex(1);
       this.accion = "editar";
       btnGuardar.setText("Editar");
       
       
       
       }else{
       
       this.mensajeError("Selecione 1 registro a editar.");
       
       }
        
        
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if(tablaListado.getSelectedRowCount()==1){
       String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
       String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1));
       if(JOptionPane.showConfirmDialog(this,"Desea Activar el registro: " +  nombre + " ?", "Desactivar", JOptionPane.YES_NO_OPTION )== 0){
           String resp = this.CONTROL.activar(Integer.parseInt(id));
            if(resp.equals("OK")){
            this.mensajeOk("Registro Activado");
            this.listar("");
            }else{
            this.mensajeError(resp);
            
            }
        }
       
       }else{
       
       this.mensajeError("Selecione 1 registro a Activar.");
       }  
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
      
     if(tablaListado.getSelectedRowCount()==1){
       String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
       String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1));
       if(JOptionPane.showConfirmDialog(this,"Desea desactivar el registro: " +  nombre + " ?", "Desactivar", JOptionPane.YES_NO_OPTION )== 0){
           String resp = this.CONTROL.desactivar(Integer.parseInt(id));
            if(resp.equals("OK")){
            this.mensajeOk("Registro Desactivado");
            this.listar("");
            }else{
            this.mensajeError(resp);
            
            }
         
         }
       
       }else{
       
       this.mensajeError("Selecione 1 registro a Desactivar.");
       
       }    
        
        
    }//GEN-LAST:event_btnDesactivarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbltotalRegistros;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
