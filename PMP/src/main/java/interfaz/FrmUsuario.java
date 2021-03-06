/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import entidades.Rol;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.UsuarioControl;

/**
 *
 * @author Equipo de Pedro
 */
public class FrmUsuario extends javax.swing.JInternalFrame {
    
    private final UsuarioControl CONTROL;
    private String accion;
    private String emailAnt;
    
    private int totalporpagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga=true;
    private int totalRegistros;
    
    
    public FrmUsuario() {
        initComponents();
        this.CONTROL = new UsuarioControl();
        this.paginar();
        this.listar("",false);
        this.primeraCarga = false;
        tabGeneral.setEnabledAt(1,false);
        this.accion = "Guardar";
        txtid.setVisible(false);
        cagarRoles();
        cboNumPagina.setVisible(false);
        filtronumpagina.setVisible(false);
        
    }
    
    private void ocultarColumnas(){
    tablaListado.getColumnModel().getColumn(1).setMaxWidth(0);
    tablaListado.getColumnModel().getColumn(1).setMinWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
    
    tablaListado.getColumnModel().getColumn(9).setMaxWidth(0);
    tablaListado.getColumnModel().getColumn(9).setMinWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
    
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
    tablaListado.setModel(this.CONTROL.listar(texto, totalporpagina, numPagina));
    } else{      
    tablaListado.setModel(this.CONTROL.listar(texto,this.totalporpagina,1));
    }
    
    
    
    
    
    TableRowSorter orden = new TableRowSorter(tablaListado.getModel());
    tablaListado.setRowSorter(orden);
    this.ocultarColumnas();
    lbltotalRegistros.setText("Mostrando " + this.CONTROL.totalMostrado()+ " de un total de " + this.CONTROL.total() + " registros" );
    
    }
    
    private void cagarRoles(){
        DefaultComboBoxModel items = this.CONTROL.selecionar();
        cboRol.setModel(items);
    
    }
    
    
    private void limpiar(){
    txtNombre.setText("");
    txtDirecion.setText("");
    txtdnumdocumento.setText("");
    txtid.setText("");
    txttelefono.setText("");
    txtemail.setText("");
    txtclave.setText("");
    this.accion = "Guardar";
  
    
    
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
        cboNumPagina = new javax.swing.JComboBox<>();
        filtronumpagina = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbototalporpagina = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDirecion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboRol = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboTipoDocumento = new javax.swing.JComboBox<>();
        txtdnumdocumento = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        txttelefono = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        txtclave = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(239, 241, 228));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuario");
        setToolTipText("");
        setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filtronumpagina))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(cbototalporpagina, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbltotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbototalporpagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDesactivar)
                            .addComponent(btnActivar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filtronumpagina)
                            .addComponent(jLabel13))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Listado", jPanel1);

        jPanel2.setBackground(new java.awt.Color(239, 241, 228));

        jLabel2.setText("Nombre(*)");

        txtDirecion.setColumns(20);
        txtDirecion.setRows(5);
        jScrollPane2.setViewportView(txtDirecion);

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

        jLabel7.setText("Rol(*)");

        cboRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRolActionPerformed(evt);
            }
        });

        jLabel8.setText("<html>Tipo Documento</html>");

        jLabel9.setText("# Documento(*)");

        jLabel10.setText("Telefono(*)");

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cedula", "Passaporte", " " }));

        jLabel11.setText("Direccion");

        jLabel14.setText("Email(*)");

        jLabel15.setText("Clave(*)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11))
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar))
                            .addComponent(txtdnumdocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(txtemail)
                            .addComponent(txttelefono)
                            .addComponent(txtclave)))
                    .addComponent(jLabel14)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(484, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtdnumdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar))
                .addGap(23, 23, 23))
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
        this.listar(txtbuscar.getText(),false);
        
        
        
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
       
        if(txtNombre.getText().length()== 0 || txtNombre.getText().length()>70){
            JOptionPane.showConfirmDialog(this, "Debes ingresar un Nombre, es Obligatorio, y no debe ser mayor a 20 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
           return;
        }
        if(txtemail.getText().length()== 0 || txtemail.getText().length()>50){
            JOptionPane.showConfirmDialog(this, "Debes ingresar un Email, es Obligatorio, y debe ser menor a 50 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtemail.requestFocus();
           return;
        }
        if(txtclave.getText().length()== 0 || txtclave.getText().length()>128){
            JOptionPane.showConfirmDialog(this, "Debes ingresar la Clave, es Obligatorio, y  debe ser menor a 128 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtclave.requestFocus();
           return;
        }
                
        if(txtDirecion.getText().length()>70){
            JOptionPane.showConfirmDialog(this, "La direcion no debe ser mayor a 70 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtDirecion.requestFocus();
           return;
        }
        
         if(txtdnumdocumento.getText().length()>20){
            JOptionPane.showConfirmDialog(this, "El numero de Documento no debe ser mayor a 20 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtdnumdocumento.requestFocus();
           return;
        }
        
          if(txttelefono.getText().length()>20){
            JOptionPane.showConfirmDialog(this, "Ladescripcion no debe ser mayor a 255 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txttelefono.requestFocus();
           return;
        }
        
        
        String resp;
        if (this.accion.equals("editar")){
            Rol seleccionado =(Rol) cboRol.getSelectedItem();
            resp = this.CONTROL.actualizar(Integer.parseInt(txtid.getText()),seleccionado.getId(),txtNombre.getText(),(String)cboTipoDocumento.getSelectedItem(),txtdnumdocumento.getText(),txtDirecion.getText(),txttelefono.getText(),txtemail.getText(),this.emailAnt, txtclave.getText());
                if(resp.equals("OK")){
                this.mensajeOk("Actualizado correctamente ");
                this.limpiar();
                this.listar("",false);
               tabGeneral.setEnabledAt(0, true);
               tabGeneral.setEnabledAt(1, false);
               tabGeneral.setSelectedIndex(0);   
                
             }else{
                this.mensajeError(resp);
            }
            
        
        }else{//Guardar
               Rol seleccionado =(Rol) cboRol.getSelectedItem();
               resp = this.CONTROL.insertar(seleccionado.getId(),txtNombre.getText(),(String)cboTipoDocumento.getSelectedItem(),txtdnumdocumento.getText(),txtDirecion.getText(),txttelefono.getText(),txtemail.getText(),txtclave.getText());
                if(resp.equals("OK")){
                this.mensajeOk("Registrado correctamente ");
                this.limpiar();
                this.listar("",false);
                 
             }else{
                this.mensajeError(resp);
            }
           
        }
        
        
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       if(tablaListado.getSelectedRowCount()==1){
       String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
       int Rolid = Integer.parseInt(String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1)));
       String Rolnombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 2));
       String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
       String Tipodocumento = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
       String numdocumento = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 5));
       String Direccion = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 6));
       String telefono= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 7));
       String email = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 8));
       this.emailAnt = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),8));
       String clave = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 9));
   
       
       txtid.setText(id);
       Rol selecionado = new Rol(Rolid,Rolnombre);
       cboRol.setSelectedItem(selecionado);
       txtNombre.setText(nombre);
       cboTipoDocumento.setSelectedItem(Tipodocumento);
       txtdnumdocumento.setText(numdocumento);
       txtDirecion.setText(Direccion);
       txttelefono.setText(telefono);
       txtemail.setText(email);
       txtclave.setText(clave);
       
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
       String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
       if(JOptionPane.showConfirmDialog(this,"Desea Activar el registro: " +  nombre + " ?", "Desactivar", JOptionPane.YES_NO_OPTION )== 0){
           String resp = this.CONTROL.activar(Integer.parseInt(id));
            if(resp.equals("OK")){
            this.mensajeOk("Registro Activado");
            this.listar("",false);
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
       String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
       if(JOptionPane.showConfirmDialog(this,"Desea desactivar el registro: " +  nombre + " ?", "Desactivar", JOptionPane.YES_NO_OPTION )== 0){
           String resp = this.CONTROL.desactivar(Integer.parseInt(id));
            if(resp.equals("OK")){
            this.mensajeOk("Registro Desactivado");
            this.listar("",false);
            }else{
            this.mensajeError(resp);
            
            }
         
         }
       
       }else{
       
       this.mensajeError("Selecione 1 registro a Desactivar.");
       
       }    
        
        
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void cboNumPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPaginaActionPerformed
        if(this.primeraCarga==false){
        this.listar("", true);
        
        
        }
        
    }//GEN-LAST:event_cboNumPaginaActionPerformed

    private void cbototalporpaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbototalporpaginaActionPerformed
        // TODO add your handling code here:
        
        this.paginar();
        
    }//GEN-LAST:event_cbototalporpaginaActionPerformed

    private void cboRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboRolActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cboRol;
    private javax.swing.JComboBox<String> cboTipoDocumento;
    private javax.swing.JComboBox<String> cbototalporpagina;
    private javax.swing.JLabel filtronumpagina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbltotalRegistros;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextArea txtDirecion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JPasswordField txtclave;
    private javax.swing.JTextField txtdnumdocumento;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtid;
    private javax.swing.JFormattedTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
