/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import entidades.Categoria;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.ArticuloControl;

/**
 *
 * @author Equipo de Pedro
 */
public class FrmSelecionarArticuloVenta extends javax.swing.JDialog {
    private FrmSalida vista;
    private final ArticuloControl CONTROL;
    
    private int totalporpagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga=true;
    private int totalRegistros;
    /**
     * Creates new form FrmSelecionarArticuloCompra
     */
    public FrmSelecionarArticuloVenta(java.awt.Frame parent,FrmSalida frm, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.vista = frm;
         this.setTitle("Selecione un Articulo para Venta");
        this.CONTROL = new ArticuloControl();
        this.paginar();
        this.listar("",false);
        this.primeraCarga = false;
                
        cboNumPagina.setVisible(false);
        filtronumpagina.setVisible(false);
        
        this.setVisible(true);
    }

     
    private void ocultarColumnas(){
    tablaListado.getColumnModel().getColumn(1).setMaxWidth(0);
    tablaListado.getColumnModel().getColumn(1).setMinWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
    
    }
    
    private void paginar(){
   int totalPaginas;
    
    this.totalRegistros = this.CONTROL.total();
    this.totalporpagina = Integer.parseInt((String)cbototalporpagina.getSelectedItem());
    
    totalPaginas = (int)(Math.ceil(this.totalRegistros/this.totalporpagina));
   
    if(totalPaginas == 0){
    totalPaginas =1;
   }
    cboNumPagina.removeAllItems();
    
    for (int i = 1; i <= totalPaginas; i++) {
      cboNumPagina.addItem(Integer.toString(i));
   }
    
  cboNumPagina.setSelectedIndex(0);
    }
    

    private void listar(String texto,boolean paginar)
    {
     this.totalporpagina = Integer.parseInt((String) cbototalporpagina.getSelectedItem());
     if((String)cboNumPagina.getSelectedItem()!= null){
         this.numPagina = Integer.parseInt((String)cboNumPagina.getSelectedItem());
      }
     
    if(paginar==true){
    tablaListado.setModel(this.CONTROL.listararticulosSalida(texto, totalporpagina, numPagina));
    } else{      
    tablaListado.setModel(this.CONTROL.listararticulosSalida(texto,this.totalporpagina,1));
    }
    
    TableRowSorter orden = new TableRowSorter(tablaListado.getModel());
    tablaListado.setRowSorter(orden);
    this.ocultarColumnas();
    lbltotalRegistros.setText("Mostrando " + this.CONTROL.totalMostrado()+ " de un total de " + this.CONTROL.total() + " registros" );
    
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
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        cboNumPagina = new javax.swing.JComboBox<>();
        filtronumpagina = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbototalporpagina = new javax.swing.JComboBox<>();
        btnNuevo1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Descripcion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Codigo");

        cboNumPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPaginaActionPerformed(evt);
            }
        });

        filtronumpagina.setText("# PAGINA");
        filtronumpagina.setEnabled(false);

        jLabel13.setText("Total de Registro por pagina");

        cbototalporpagina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "200", "500" }));
        cbototalporpagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbototalporpaginaActionPerformed(evt);
            }
        });

        btnNuevo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNuevo1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\mas.png")); // NOI18N
        btnNuevo1.setText("Selecionar ");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("Articulos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevo1)
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(jLabel11)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filtronumpagina))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(cbototalporpagina, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbltotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnNuevo1)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbototalporpagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filtronumpagina)
                            .addComponent(jLabel13))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Listado", jPanel1);

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

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        if(tablaListado.getSelectedRowCount()==1){
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            String codigo = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
            String precio = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 5));
            String stock = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 6));
            
            this.vista.agregarDetalle(id, codigo, nombre,stock, precio,"0");

        }
        else{
            this.mensajeError("debe selecionar un articulo para agregar");
        }

    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void cbototalporpaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbototalporpaginaActionPerformed
        // TODO add your handling code here:

        this.paginar();
    }//GEN-LAST:event_cbototalporpaginaActionPerformed

    private void cboNumPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPaginaActionPerformed
        if(this.primeraCarga==false){
            this.listar("", true);

        }
    }//GEN-LAST:event_cboNumPaginaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.listar(txtbuscar.getText(),false);
    }//GEN-LAST:event_btnBuscarActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cbototalporpagina;
    private javax.swing.JLabel filtronumpagina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbltotalRegistros;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
