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
public class FrmSelecionarArticuloCompra extends javax.swing.JDialog {
    private FrmEntradas vista;
    private final ArticuloControl CONTROL;
    private String accion;
    private String nombreanterior;
    
    private String rutaOrigen;
    private String rutaDestino;
    private final String DIRECTORIO = "src/main/java/files/articulos/";
    private String imagen;
    private String imagen_anterior;
    
    private int totalporpagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga=true;
    private int totalRegistros;
    /**
     * Creates new form FrmSelecionarArticuloCompra
     */
    public FrmSelecionarArticuloCompra(java.awt.Frame parent,FrmEntradas frm, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.vista = frm;
         this.setTitle("Selecione un Articulo");
        this.CONTROL = new ArticuloControl();
        this.paginar();
        this.listar("",false);
        this.primeraCarga = false;
        tabGeneral.setEnabledAt(1,false);
        this.accion = "Guardar";
        txtid.setVisible(false);
        cagarCategorias();
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
    tablaListado.setModel(this.CONTROL.listar(texto, totalporpagina, numPagina));
    } else{      
    tablaListado.setModel(this.CONTROL.listar(texto,this.totalporpagina,1));
    }
    
    
    
    
    
    TableRowSorter orden = new TableRowSorter(tablaListado.getModel());
    tablaListado.setRowSorter(orden);
    this.ocultarColumnas();
    lbltotalRegistros.setText("Mostrando " + this.CONTROL.totalMostrado()+ " de un total de " + this.CONTROL.total() + " registros" );
    
    }
    
    private void cagarCategorias(){
        DefaultComboBoxModel items = this.CONTROL.selecionar();
        cboCategoria.setModel(items);
    
    }
    
    private void subirImagen(){
    File origen = new File(this.rutaOrigen);
    File destino = new File(this.rutaDestino);
        try {
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf))> 0){
            out.write(buf,0,len);
            }
            in.close();
            out.close();
            
        } catch (IOException e) {
            
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
            
    
    
    }
    
       
    
    private void limpiar(){
    txtNombre.setText("");
    txtDescripcion.setText("");
    txtCodigoBarra.setText("");
    txtid.setText("");
    txtPrecioVenta.setText("");
    this.imagen = "";
    lblImagen.setIcon(null);
    this.rutaDestino = "";
    this.rutaOrigen = "";
    this.accion = "Guardar";
    txtStock.setText("");
    this.imagen_anterior="";
    
    
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
        btnNuevo1 = new javax.swing.JButton();
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
        jLabel7 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtCodigoBarra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        btnAgregarImagen = new javax.swing.JButton();
        txtStock = new javax.swing.JFormattedTextField();

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

        btnNuevo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNuevo1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\mas.png")); // NOI18N
        btnNuevo1.setText("Selecionar ");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevo))
                            .addComponent(btnNuevo1, javax.swing.GroupLayout.Alignment.TRAILING))
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
                    .addComponent(jLabel5)
                    .addComponent(btnNuevo1))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel7.setText("Categoria(*)");

        jLabel8.setText("Codigo Barra");

        jLabel9.setText("Precio Venta(*)");

        txtPrecioVenta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.000"))));

        jLabel10.setText("Stock(*)");

        jLabel12.setText("Imagen ");

        lblImagen.setBackground(new java.awt.Color(153, 255, 255));
        lblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregarImagen.setText("Agregar Imagen");
        btnAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImagenActionPerformed(evt);
            }
        });

        txtStock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodigoBarra)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNombre)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCancelar)))))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarImagen)
                    .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel12))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarImagen)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar))
                .addContainerGap(127, Short.MAX_VALUE))
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

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if(tablaListado.getSelectedRowCount()==1){
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            int categoriaid = Integer.parseInt(String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1)));
            String categorianombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 2));
            String codigo_barra = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
            this.nombreanterior = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
            String precio_venta = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 5));
            String stock = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 6));
            String descripcion = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 7));
            this.imagen_anterior = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 8));

            txtid.setText(id);
            Categoria selecionado = new Categoria(categoriaid,categorianombre);
            cboCategoria.setSelectedItem(selecionado);
            txtNombre.setText(nombre);
            txtCodigoBarra.setText(codigo_barra);
            txtPrecioVenta.setText(precio_venta);
            txtStock.setText(stock);
            txtDescripcion.setText(descripcion);

            ImageIcon im = new ImageIcon(this.DIRECTORIO+this.imagen_anterior);
            Icon icono = new ImageIcon(im.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(),Image.SCALE_DEFAULT));
            lblImagen.setIcon(icono);
            lblImagen.repaint();

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
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
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
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        if(txtNombre.getText().length()== 0 || txtNombre.getText().length()>100){
            JOptionPane.showConfirmDialog(this, "Debes ingresar un Nombre, es Obligatorio, y no debe ser mayor a 20 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }
        if(txtPrecioVenta.getText().length()<= 0){
            JOptionPane.showConfirmDialog(this, "Debes ingresar el precio de Venta, es Obligatorio, y debe ser mayor a 0 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtPrecioVenta.requestFocus();
            return;
        }
        if(txtStock.getText().length()<= 0){
            JOptionPane.showConfirmDialog(this, "Debes ingresar el Stock, es Obligatorio, y  debe ser mayor a 0 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtStock.requestFocus();
            return;
        }

        if(txtDescripcion.getText().length()>255){
            JOptionPane.showConfirmDialog(this, "Ladescripcion no debe ser mayor a 255 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtDescripcion.requestFocus();
            return;
        }

        String resp;
        if (this.accion.equals("editar")){
            String imagenActual = "";

            if(this.imagen.equals("")){
                imagenActual = this.imagen_anterior;

            }else{
                imagenActual = this.imagen;
            }

            Categoria seleccionado =(Categoria) cboCategoria.getSelectedItem();
            resp = this.CONTROL.actualizar(Integer.parseInt(txtid.getText()),seleccionado.getId_categoria(),txtCodigoBarra.getText(),txtNombre.getText(),nombreanterior,Double.parseDouble(txtPrecioVenta.getText()),Integer.parseInt(txtStock.getText()), txtDescripcion.getText(),imagenActual);
            if(resp.equals("OK")){
                if(!this.imagen.equals("")){
                    this.subirImagen();
                }

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
            Categoria seleccionado =(Categoria) cboCategoria.getSelectedItem();
            resp = this.CONTROL.insertar(seleccionado.getId_categoria(),txtCodigoBarra.getText(),txtNombre.getText(),Double.parseDouble(txtPrecioVenta.getText()),Integer.parseInt(txtStock.getText()), txtDescripcion.getText(),this.imagen);
            if(resp.equals("OK")){
                if(!this.imagen.equals("")){
                    this.subirImagen();
                }
                this.mensajeOk("Registrado correctamente ");
                this.limpiar();
                this.listar("",false);

            }else{
                this.mensajeError(resp);
            }

        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setSelectedIndex(0);
        this.limpiar();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        JFileChooser file = new JFileChooser();
        int estado = file.showOpenDialog(this);
        if(estado == JFileChooser.APPROVE_OPTION){
            this.imagen = file.getSelectedFile().getName();
            this.rutaOrigen = file.getSelectedFile().getAbsolutePath();
            this.rutaDestino = this.DIRECTORIO + this.imagen;

            ImageIcon im = new ImageIcon(this.rutaOrigen);
            Icon icono = new ImageIcon(im.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(),Image.SCALE_DEFAULT));
            lblImagen.setIcon(icono);
            lblImagen.repaint();
        }

    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
          if(tablaListado.getSelectedRowCount()==1){
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            String codigo = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
            String precio = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 5));
            
            this.vista.agregarDetalle(id, codigo, nombre, precio);
            
        }
        else{
        this.mensajeError("debe selecionar un articulo para agregar");
        }
        
        
    }//GEN-LAST:event_btnNuevo1ActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnAgregarImagen;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cbototalporpagina;
    private javax.swing.JLabel filtronumpagina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lbltotalRegistros;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txtCodigoBarra;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtPrecioVenta;
    private javax.swing.JFormattedTextField txtStock;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
