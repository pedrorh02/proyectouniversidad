/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import negocio.EntradaControl;
import negocio.SalidaControl;



/**
 *
 * @author Equipo de Pedro
 */
public class FrmSalida extends javax.swing.JInternalFrame {
    
    private final SalidaControl CONTROL;
    private String accion;
    private String nombreAnt;
    
    private int totalporpagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga=true;
    private int totalRegistros;
    
    public DefaultTableModel modeloDetalle;
    public JFrame contenedor;
    
    
    public FrmSalida(JFrame frmP) {
        initComponents();
        this.contenedor = frmP;
        this.CONTROL = new SalidaControl();
        this.paginar();
        this.listar("",false);
        this.primeraCarga = false;
        tabGeneral.setEnabledAt(1,false);
        this.accion = "Guardar";
        cboNumPagina.setVisible(false);
        filtronumpagina.setVisible(false);
        this.crearDetalles();
        
        
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
    
    private void crearDetalles(){
    
    modeloDetalle = new DefaultTableModel(){
        
        @Override
        public boolean isCellEditable(int fila, int Columna){
             if(Columna == 4){
             return Columna ==4;
             }   
             if(Columna == 5){
             return Columna == 5;
             }
             if(Columna == 6){
             return Columna == 6;
             }
             return Columna == 4; 
        }
        
        @Override
        public Object getValueAt(int row, int col){
        
            if(col == 7){
                Double cant;
                try {
                    cant = Double.parseDouble((String)getValueAt(row,4) ); 
                } catch (Exception e) {
                    cant = 1.0;
                }
                
                Double precioD =Double.parseDouble((String)getValueAt(row,5) ); 
                Double  descuentoD =Double.parseDouble((String)getValueAt(row,6) ); 
                if(cant!=null && precioD!=null && descuentoD != null){
                return String.format("%.3f",cant * precioD - descuentoD);
            
                }else{
                    return 0;
                    }
            }
            return super.getValueAt(row, col);
            
        }
        
        @Override
        public void setValueAt(Object avalue, int row, int col){
            super.setValueAt(avalue, row, col);
           CAlcular_Totales();
           fireTableDataChanged();
        }
     
    
    };
    
    modeloDetalle.setColumnIdentifiers(new Object[]{"ID","Codigo","Articulo","Stock","Cantidad","Precio","Descuento","SubTotal"});
    tablaDetalle.setModel(modeloDetalle);
    
    
    }
    
    
    private void CAlcular_Totales(){
        double total = 0;
        double  subTotal;
        int Items = modeloDetalle.getRowCount();
        if(Items==0){
        total = 0;
        }else{
            for (int i = 0; i < Items; i++) {
                total = total + Double.parseDouble(String.valueOf(modeloDetalle.getValueAt(i,7)));
            }
        
        }
         
        subTotal = total - (total*0.18);        
        
        txtTotal.setText(String.format("%.3f", total));
        txtSubTotal.setText(String.format("%.3f", subTotal));
        txtTotalImpuesto.setText(String.format("%.3f", total*0.18));
    
    }
    
    public void agregarDetalle(String id,String codigo, String nombre,String stock, String precio, String descuento){
        String idtemp;
        boolean existe = false;
        
        for (int i = 0; i < this.modeloDetalle.getRowCount(); i++) {
            idtemp = String.valueOf(this.modeloDetalle.getValueAt(i,0));
            if(idtemp.equals(id)){
            existe = true;
            
            }
        }
        
        
        
        if(existe){
        this.mensajeError("Articulo agregado anteriormente");
        
        }else{
        this.modeloDetalle.addRow(new Object[]{id,codigo,nombre,stock,"1",precio,descuento,precio});
        this.CAlcular_Totales();
        }
    
    
    }
    
    private void limpiar(){
    txtNombrecliente.setText("");
    this.accion = "Guardar";
    txtIdCliente.setText("");
    txtFactura.setText("");
    txtArticulo.setText("");
    txtSubTotal.setText("");
    txtTotal.setText("");
    txtnumOrdCompra.setText("");
    txtNUMComprobante.setText("");
    txtTotalImpuesto.setText("");
    this.crearDetalles();
    btnGuardar.setVisible(true);
  
    
    
    }
    
    private void ocultarColumnas(){
    tablaListado.getColumnModel().getColumn(1).setMaxWidth(0);
    tablaListado.getColumnModel().getColumn(1).setMinWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
    
    tablaListado.getColumnModel().getColumn(3).setMaxWidth(0);
    tablaListado.getColumnModel().getColumn(3).setMinWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
    tablaListado.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
    
    
    
    
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
        btnDesactivar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        cboNumPagina = new javax.swing.JComboBox<>();
        filtronumpagina = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbototalporpagina = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        btnVerdetalle = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombrecliente = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        btnCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cboComprobante = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNUMComprobante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtnumOrdCompra = new javax.swing.JTextField();
        btnProvedor1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        btnProvedor2 = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEleminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtTotalImpuesto = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbxAlmacen = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(239, 241, 228));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Salida");
        setToolTipText("");
        setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N

        tabGeneral.setBackground(new java.awt.Color(239, 241, 228));

        jPanel1.setBackground(new java.awt.Color(239, 241, 228));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nombre");

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\anadir.png")); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnDesactivar.setBackground(new java.awt.Color(83, 124, 142));
        btnDesactivar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDesactivar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\disgusto.png")); // NOI18N
        btnDesactivar.setText("Anular Registro");
        btnDesactivar.setBorderPainted(false);
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Descripcion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Salida");

        btnVerdetalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVerdetalle.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\analisis-de-los-datos.png")); // NOI18N
        btnVerdetalle.setText("Ver detalle");
        btnVerdetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerdetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filtronumpagina))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(cbototalporpagina, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbltotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                                .addComponent(jLabel17)
                                .addGap(67, 67, 67)
                                .addComponent(btnVerdetalle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevo)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnBuscar)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(btnVerdetalle))
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
                            .addComponent(btnDesactivar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filtronumpagina)
                            .addComponent(jLabel13))))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Listado", jPanel1);

        jPanel2.setBackground(new java.awt.Color(239, 241, 228));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Cliente(*)");

        btnGuardar.setBackground(new java.awt.Color(83, 124, 142));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\salvar_25.png")); // NOI18N
        btnGuardar.setText("Registrar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(134, 88, 64));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Equipo de Pedro\\Desktop\\Proyecto Producion en masa\\Iconos\\eliminar25.png")); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("(*) Indica que es un campo obligatorio");

        txtIdCliente.setEditable(false);

        btnCliente.setText("...");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tipo Comprobante");

        cboComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Factura de Crédito Fiscal (Tipo 01)", "​Factura de Consumo (Tipo 02)", "​Notas de Débito (Tipo 03)", "Notas de Crédito (Tipo 04)", "Comprobante de Compras (Tipo 11)", "​Registro Único de Ingresos (Tipo 12)", "Comprobante para Gastos Menores (Tipo 13)" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Factura(*)");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Numero Comprobante");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("<html>Numero Orden Compra</html>");

        btnProvedor1.setText("...");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Articulo");

        txtArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtArticuloKeyReleased(evt);
            }
        });

        btnProvedor2.setText("...");
        btnProvedor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvedor2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Cantidad");

        btnAgregar.setText("Agregar");

        btnEleminar.setText("Eliminar");
        btnEleminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEleminarActionPerformed(evt);
            }
        });

        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaDetalle);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("SubTotal");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Total Impuesto");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Total");

        txtSubTotal.setEditable(false);
        txtSubTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSubTotal.setDoubleBuffered(true);

        txtTotalImpuesto.setEditable(false);
        txtTotalImpuesto.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalImpuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalImpuesto.setDoubleBuffered(true);

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotal.setDoubleBuffered(true);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Almacen");

        cbxAlmacen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Almacen Distribucion", "Almacen Producto Terminados", "Almacen Procesos" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubTotal)
                            .addComponent(txtTotalImpuesto)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProvedor2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEleminar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36)
                                .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtnumOrdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnProvedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(97, 97, 97)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNUMComprobante)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(100, 100, 100))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliente)
                    .addComponent(jLabel3)
                    .addComponent(cboComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtNUMComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnumOrdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnProvedor1)
                        .addComponent(jLabel16)
                        .addComponent(cbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProvedor2)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnAgregar)
                    .addComponent(btnEleminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTotalImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(63, 63, 63))
        );

        tabGeneral.addTab("Nuevo Ingreso", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
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
       
        if(txtNombrecliente.getText().length()== 0 || txtNombrecliente.getText().length()>70){
            JOptionPane.showConfirmDialog(this, "Debes ingresar un Nombre o codigo del provedor, es Obligatorio, y no debe ser mayor a 70 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombrecliente.requestFocus();
           return;
        }
             
        if(txtIdCliente.getText().length()== 0 || txtIdCliente.getText().length()>10){
            JOptionPane.showConfirmDialog(this, "Debes ingresar un Nombre o  codigo del provedor, es Obligatorio, y no debe ser mayor a 20 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtIdCliente.requestFocus();
           return;
        }
        
        if(txtFactura.getText().length()== 0 || txtFactura.getText().length()>30){
            JOptionPane.showConfirmDialog(this, "Debes ingresar el numero de factura del provedor, es Obligatorio, y no debe ser mayor a 30 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtFactura.requestFocus();
           return;
        }
        
        if(txtnumOrdCompra.getText().length()== 0 || txtnumOrdCompra.getText().length()>30){
            JOptionPane.showConfirmDialog(this, "Debes ingresar la Orden de compra, es Obligatorio, y no debe ser mayor a 30 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtnumOrdCompra.requestFocus();
           return;
        }
        
        if(txtNUMComprobante.getText().length()== 0 || txtNUMComprobante.getText().length()>30){
            JOptionPane.showConfirmDialog(this, "Debes ingresar numero Comprobante Fiscal, es Obligatorio, y no debe ser mayor a 30 caracteres","Sistema", JOptionPane.WARNING_MESSAGE);
            txtNUMComprobante.requestFocus();
           return;
        }
        
        
        String resp = "";
           resp = this.CONTROL.insertar(Integer.parseInt(txtIdCliente.getText()),(String)cboComprobante.getSelectedItem(),txtFactura.getText(),txtNUMComprobante.getText(),Double.parseDouble(txtTotalImpuesto.getText()),Double.parseDouble(txtTotal.getText()),modeloDetalle);
                if(resp.equals("OK")){
                this.mensajeOk("Registrado correctamente ");
                this.limpiar();
                this.listar("",false);
                 
             }else{
                this.mensajeError(resp);
            }
          
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
      
     if(tablaListado.getSelectedRowCount()==1){
       String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
       String Comprobante = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),5));
       String factura = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),6));
       String numComprobante = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),7));
       
       if(JOptionPane.showConfirmDialog(this,"Desea Anular el registro: " +  Comprobante + " "+ factura +"-"+ numComprobante + " ?", "Anular", JOptionPane.YES_NO_OPTION )== 0){
           String resp = this.CONTROL.anular(Integer.parseInt(id));
            if(resp.equals("OK")){
            this.mensajeOk("Registro Anulado");
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

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        FrmselecionarClienteVenta frm = new FrmselecionarClienteVenta(contenedor, this, true);
        frm.toFront();
        
        
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnProvedor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvedor2ActionPerformed
        FrmSelecionarArticuloVenta frm = new FrmSelecionarArticuloVenta(contenedor, this, true);
        frm.toFront();
        
    }//GEN-LAST:event_btnProvedor2ActionPerformed

    private void txtArticuloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArticuloKeyReleased
       
        if(txtArticulo.getText().length() > 0 ){
        
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                entidades.Articulo art;
                art = this.CONTROL.ObtenrArticuloCodigoSalida(txtArticulo.getText());
                
                if(art==null){
                this.mensajeError("articulo no existe");
                
                
                }else{
                
                this.agregarDetalle(Integer.toString(art.getId_articulo()), art.getCodigo_barrar(), art.getNombre(),Integer.toString(art.getStock()), Double.toString(art.getPrecio_venta()),"0");
                
                
                }
            }
               
        }else{
        this.mensajeError("Ingrese El codigo a buscar");
        }
        
        
    }//GEN-LAST:event_txtArticuloKeyReleased

    private void btnEleminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEleminarActionPerformed
      
        if(tablaDetalle.getSelectedRowCount() == 1){
            
            this.modeloDetalle.removeRow(tablaDetalle.getSelectedRow());
            this.CAlcular_Totales();
               
        }else{
        this.mensajeError("selecione el articulo a quitar");
        
        
        }
        
        
        
    }//GEN-LAST:event_btnEleminarActionPerformed

    private void btnVerdetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerdetalleActionPerformed
        
        if(tablaListado.getSelectedRowCount() == 1){
        String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),0 ));
        String id_provedor = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),3 ));
        String nombre_provedor = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),4 ));
        String tipo_comprobante = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),5 ));
        String factura = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),6));
        String numero = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),7));
        String impuesto = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),8 ));
        
        txtIdCliente.setText(id);
        txtNombrecliente.setText(id_provedor);
        cboComprobante.setSelectedItem(tipo_comprobante);
        txtFactura.setText(factura);
        txtNUMComprobante.setText(numero);
        txtTotalImpuesto.setText(impuesto);
        
        this.modeloDetalle = CONTROL.listarDetalle(Integer.parseInt(id));
        tablaDetalle.setModel(modeloDetalle);
        this.CAlcular_Totales();
        
        tabGeneral.setEnabledAt(1,true);
        tabGeneral.setEnabledAt(0,false);
        tabGeneral.setSelectedIndex(1);
        btnGuardar.setVisible(false);
        
        }else{
        this.mensajeError("Selecione un registro para ver");
                
        }
             
        
    }//GEN-LAST:event_btnVerdetalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEleminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnProvedor1;
    private javax.swing.JButton btnProvedor2;
    private javax.swing.JButton btnVerdetalle;
    private javax.swing.JComboBox<String> cboComprobante;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cbototalporpagina;
    private javax.swing.JComboBox<String> cbxAlmacen;
    private javax.swing.JLabel filtronumpagina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel lbltotalRegistros;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtFactura;
    public javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtNUMComprobante;
    public javax.swing.JTextField txtNombrecliente;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalImpuesto;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtnumOrdCompra;
    // End of variables declaration//GEN-END:variables
}
