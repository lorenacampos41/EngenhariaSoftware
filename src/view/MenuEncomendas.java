/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import Classes.Cliente;
import Classes.Conexao;
import Classes.Encomenda;
import Classes.Produto;
import Classes.TipoProduto;
import Classes.encomendaHASproduto;
import Classes.tabelaExibirEncomendas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.runtime.JSType;

/**
 *
 * @author lorena
 */

public class MenuEncomendas extends javax.swing.JFrame {

    /**
     * Creates new form MenuEncomendas
     */
     tabelaExibirEncomendas texibir = new tabelaExibirEncomendas(); // chama  a classe tabela que contem os botoes
     public double total=0;
     public void CarregarJTable(String sql) {
        Connection con;
        try
        {    
         con = Conexao.getConnection();
         com.mysql.jdbc.PreparedStatement banco = (com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
         banco.execute(); // cria o vetor
         ResultSet resultado = banco.executeQuery(sql);
         DefaultTableModel model =(DefaultTableModel) jTableExibir.getModel();
         model.setNumRows(0);
         while(resultado.next())
         { //JButton buton= new javax.swing.JButton();
           //buton.createImage(5, 5); buton.setText("t");
           
         model.addRow(new Object[] 
         { 
            //retorna os dados da tabela do BD, cada campo e um coluna.
            resultado.getInt("idEncomenda"),
            resultado.getInt("idEncomenda"),// verificar esta linha
            resultado.getString("situacao"),
            resultado.getString("localEntrega"),
            resultado.getDate("dataEntrega"),
            resultado.getTime("horaEntrega")
            
            //resultado.getString("cpf")
            
         }); 
        } 
            banco.close();
            con.close();
        }
        catch (SQLException ex)
        {
            System.out.println("o erro foi " +ex);
        }
    }
     public void CarregarjComboTipo(String sql){
        Connection con;
        try{    
        con = Conexao.getConnection();
        com.mysql.jdbc.PreparedStatement banco = (com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
        banco.execute(); // cria o vetor
        ResultSet resultado = banco.executeQuery(sql);
        jComboBoxTipo.removeAllItems();
            while(resultado.next())
            {
                   jComboBoxTipo.addItem(resultado.getString(2));
            }
        }       
        catch(SQLException ex)
        {
           System.out.println("o erro foi " +ex);           
        }
   }
     public void CarregarjComboProduto(String sql){
        Connection con;
        try{    
        con = Conexao.getConnection();
        com.mysql.jdbc.PreparedStatement banco = (com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
        banco.execute(); // cria o vetor
        ResultSet resultado = banco.executeQuery(sql);
        jComboBoxProduto.removeAllItems();
            while(resultado.next())
            {
                   jComboBoxProduto.addItem(resultado.getString(3));
            }
        }       
        catch(SQLException ex)
        {
           System.out.println("o erro foi " +ex);           
        }
   }
     
    public void CarregarJTableInsereProduto(String sql) {
        Connection con;
    try
    {    
     con = Conexao.getConnection();
     com.mysql.jdbc.PreparedStatement banco = (com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
     banco.execute(); 
     ResultSet resultado = banco.executeQuery(sql);
     DefaultTableModel model =(DefaultTableModel) jTableInsereProduto.getModel();
     //model.setNumRows(0);
     TipoProduto tp=new TipoProduto();
     while(resultado.next())
     {   
         model.addRow(new Object[] 
         { 
            //retorna os dados da tabela do BD, cada campo e um coluna.
            tp.ProcuraTipo(resultado.getInt("TipoProduto_idTipoProduto")),
            resultado.getString("nome"),
            Integer.parseInt(textQtd.getText()),
            resultado.getDouble("preco"),
            resultado.getDouble("preco")*Integer.parseInt(textQtd.getText())
            
         }); 
    } 
     banco.close();
     con.close();
    }
   catch (SQLException ex)
   {
      System.out.println("o erro foi " +ex);
    }
   }
    
    public void CarregarjComboCliente(String sql){
        Connection con;
        try{    
        con = Conexao.getConnection();
        com.mysql.jdbc.PreparedStatement banco = (com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
        banco.execute(); // cria o vetor
        ResultSet resultado = banco.executeQuery(sql);
        jComboBoxCliente.removeAllItems();
        while(resultado.next())
        {
               jComboBoxCliente.addItem(resultado.getString(2));
        }
       } 
        catch(SQLException ex)
        {
           System.out.println("o erro foi " +ex);           
        }
   }


    
    public MenuEncomendas() {
        initComponents();
        CarregarjComboTipo("SELECT * FROM tipoproduto");
        //jPanelCadastroEncomenda.setVisible(false);
        jPanelExibirEncomendas.setVisible(false);
        jPanelAba.setVisible(false);
        jComboBoxCliente.removeAllItems();
        CarregarjComboCliente("SELECT * FROM cliente");
        CarregarJTable("SELECT * FROM encomenda");
        jPanelControle.setVisible(false);
        
    }
    
    public void ProcuraIdCliente(String nome){
        Connection con;
         try{
         con = Conexao.getConnection();
         String sql = "select * from cliente where nome like '%" + nome + "%' ";
         PreparedStatement st = con.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         Cliente cl=new Cliente();
         if(resultSet.next()){ // so espero um resultado por isso uso o IF para verificar 
            cl.setCodigo(resultSet.getInt("idCliente")); 
            textCod.setText(Integer.toString(cl.getCodigo()));
            //System.out.print(cl.getCodigo()); System.out.print("\n");
         }
         st.close(); // fecha consulta
      }
      catch(Exception e){
         //e.printStackTrace();
         System.out.println("Não foi possivel consultar!");
      }
		
    }
    
    
    public void insereEncomenda(Encomenda en){
            Connection con;
        try {
            con = Conexao.getConnection();
            Statement statement = con.createStatement();
            String query = "INSERT INTO encomenda(" +
               "Cliente_idCliente, localEntrega, dataEntrega, situacao, horaEntrega)VALUES( " +
               en.getCod_Cliente() + ", '" + 
               en.getLocalEntrega() + "','"+
               en.getDataEntrega() +"','"+
               en.getSituacao() + "','" +
               en.getHoraEntrega()+ "')" ;
            
            int result = statement.executeUpdate(query);
                if ( result == 1 ){
                  JOptionPane.showMessageDialog(
                  null, "A inserção foi um sucesso.", "Aviso",JOptionPane.PLAIN_MESSAGE );
                }else {
                  JOptionPane.showMessageDialog(
                  null, "A inserção falhou!. Verifique os campos", "Aviso",
                  JOptionPane.WARNING_MESSAGE );    
                }      
        }catch(SQLException ex) {
             JOptionPane.showMessageDialog(
                  null, "Falha ao conectar banco de dados", "Aviso",
                  JOptionPane.PLAIN_MESSAGE );
        }
            
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonTeste = new javax.swing.JButton();
        jPrincipalEncomendas = new javax.swing.JDesktopPane();
        jPanelExibirEncomendas = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableExibir = new javax.swing.JTable();
        jComboExibir = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanelAba = new javax.swing.JTabbedPane();
        jPanelCadastroEncomenda = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textLocalEntrega = new javax.swing.JTextField();
        jDataEntrega = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboSituacao = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jHoraEntrega = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        textCod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxCliente = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonProximo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableInsereProduto = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jComboBoxProduto = new javax.swing.JComboBox<>();
        textQtd = new javax.swing.JTextField();
        jButtonInserir = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        TextTotal = new javax.swing.JTextField();
        jAtualizaTotal = new javax.swing.JButton();
        jPanelControle = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jButtonTeste.setText("t");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPrincipalEncomendas.setPreferredSize(new java.awt.Dimension(700, 600));

        jPanelExibirEncomendas.setPreferredSize(new java.awt.Dimension(672, 609));

        jLabel7.setFont(new java.awt.Font("DokChampa", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/folder_explore.png"))); // NOI18N
        jLabel7.setText("Exibir Encomendas");

        jTableExibir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableExibir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Cliente", "Situação", "Local Entrega", "DataEntrega", "HoraEntrega", "Ver Produtos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableExibir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableExibirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableExibirMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTableExibir);
        if (jTableExibir.getColumnModel().getColumnCount() > 0) {
            jTableExibir.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTableExibir.getColumnModel().getColumn(2).setResizable(false);
            jTableExibir.getColumnModel().getColumn(2).setPreferredWidth(3);
            jTableExibir.getColumnModel().getColumn(4).setResizable(false);
            jTableExibir.getColumnModel().getColumn(4).setPreferredWidth(3);
            jTableExibir.getColumnModel().getColumn(5).setResizable(false);
            jTableExibir.getColumnModel().getColumn(5).setPreferredWidth(3);
            jTableExibir.getColumnModel().getColumn(6).setPreferredWidth(5);
        }

        jComboExibir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboExibir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Enviada", "Não enviada" }));
        jComboExibir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboExibirItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Exibir");

        jLabel14.setText("Ordenar por:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data mais próxima", "Data mais distante" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelExibirEncomendasLayout = new javax.swing.GroupLayout(jPanelExibirEncomendas);
        jPanelExibirEncomendas.setLayout(jPanelExibirEncomendasLayout);
        jPanelExibirEncomendasLayout.setHorizontalGroup(
            jPanelExibirEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelExibirEncomendasLayout.createSequentialGroup()
                .addGap(0, 369, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(348, 348, 348))
            .addGroup(jPanelExibirEncomendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelExibirEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelExibirEncomendasLayout.createSequentialGroup()
                        .addGroup(jPanelExibirEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboExibir, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95)
                        .addGroup(jPanelExibirEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelExibirEncomendasLayout.setVerticalGroup(
            jPanelExibirEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExibirEncomendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanelExibirEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(3, 3, 3)
                .addGroup(jPanelExibirEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboExibir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelExibirEncomendasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        jPanelAba.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAba.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelAba.setPreferredSize(new java.awt.Dimension(664, 564));

        jPanelCadastroEncomenda.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Entrega", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel1.setName(""); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 0));
        jLabel2.setText("Local Entrega");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 0, 0));
        jLabel4.setText("Data de Entrega");

        textLocalEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textLocalEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLocalEntregaActionPerformed(evt);
            }
        });

        try {
            jDataEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jDataEntrega.setText("dd /mm /aaaa    ");
        jDataEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 0));
        jLabel5.setText("Situação");

        jComboSituacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não enviada", "Enviada" }));

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 0, 0));
        jLabel9.setText("Hora:");

        try {
            jHoraEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jHoraEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textLocalEntrega)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jHoraEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 222, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(textLocalEntrega))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHoraEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        textCod.setText("Código");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 0, 0));
        jLabel3.setText("Código");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 0, 0));
        jLabel6.setText("Nome");

        jComboBoxCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxClienteItemStateChanged(evt);
            }
        });
        jComboBoxCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxClienteMouseClicked(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCod, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textCod, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("DokChampa", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jLabel1.setText("Nova Encomenda");

        jButtonProximo.setFont(new java.awt.Font("DokChampa", 0, 10)); // NOI18N
        jButtonProximo.setForeground(new java.awt.Color(51, 0, 0));
        jButtonProximo.setText("PRÓXIMO");
        jButtonProximo.setFocusPainted(false);
        jButtonProximo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonProximoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelCadastroEncomendaLayout = new javax.swing.GroupLayout(jPanelCadastroEncomenda);
        jPanelCadastroEncomenda.setLayout(jPanelCadastroEncomendaLayout);
        jPanelCadastroEncomendaLayout.setHorizontalGroup(
            jPanelCadastroEncomendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadastroEncomendaLayout.createSequentialGroup()
                .addGroup(jPanelCadastroEncomendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadastroEncomendaLayout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCadastroEncomendaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCadastroEncomendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelCadastroEncomendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanelCadastroEncomendaLayout.setVerticalGroup(
            jPanelCadastroEncomendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadastroEncomendaLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(jButtonProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanelAba.addTab("Principal", jPanelCadastroEncomenda);

        jTableInsereProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTableInsereProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria", "Nome", "Quantidade", "Preço Unitário", "Total", "Excluir"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableInsereProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableInsereProdutoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableInsereProduto);
        if (jTableInsereProduto.getColumnModel().getColumnCount() > 0) {
            jTableInsereProduto.getColumnModel().getColumn(5).setResizable(false);
            jTableInsereProduto.getColumnModel().getColumn(5).setPreferredWidth(5);
        }

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel10.setText("Categoria");

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel11.setText("Produto");

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel12.setText("Quantidade");

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cerveja", "Refrigerante" }));
        jComboBoxTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTipoItemStateChanged(evt);
            }
        });
        jComboBoxTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxTipoMouseClicked(evt);
            }
        });

        jComboBoxProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Skol", "Brahma" }));
        jComboBoxProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxProdutoMouseClicked(evt);
            }
        });

        textQtd.setText("qtd");

        jButtonInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButtonInserir.setText("Inserir");
        jButtonInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonInserirMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 0, 0));
        jLabel13.setText("Itens selecionados");

        jButtonCadastrar.setFont(new java.awt.Font("DokChampa", 0, 10)); // NOI18N
        jButtonCadastrar.setForeground(new java.awt.Color(51, 0, 0));
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.setFocusPainted(false);
        jButtonCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCadastrarMouseClicked(evt);
            }
        });

        jButtonVoltar.setFont(new java.awt.Font("DokChampa", 0, 10)); // NOI18N
        jButtonVoltar.setForeground(new java.awt.Color(51, 0, 0));
        jButtonVoltar.setText("VOLTAR");
        jButtonVoltar.setFocusPainted(false);
        jButtonVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVoltarMouseClicked(evt);
            }
        });

        TextTotal.setEditable(false);
        TextTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextTotalActionPerformed(evt);
            }
        });

        jAtualizaTotal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jAtualizaTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/arrow_refresh.png"))); // NOI18N
        jAtualizaTotal.setText("Total:R$");
        jAtualizaTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAtualizaTotalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(jButtonInserir)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jAtualizaTotal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TextTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonVoltar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonCadastrar))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel13))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAtualizaTotal))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanelAba.addTab("Produtos", jPanel3);

        jLabel15.setText("teste");

        javax.swing.GroupLayout jPanelControleLayout = new javax.swing.GroupLayout(jPanelControle);
        jPanelControle.setLayout(jPanelControleLayout);
        jPanelControleLayout.setHorizontalGroup(
            jPanelControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControleLayout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );
        jPanelControleLayout.setVerticalGroup(
            jPanelControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControleLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(418, Short.MAX_VALUE))
        );

        jPrincipalEncomendas.setLayer(jPanelExibirEncomendas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPrincipalEncomendas.setLayer(jPanelAba, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPrincipalEncomendas.setLayer(jPanelControle, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPrincipalEncomendasLayout = new javax.swing.GroupLayout(jPrincipalEncomendas);
        jPrincipalEncomendas.setLayout(jPrincipalEncomendasLayout);
        jPrincipalEncomendasLayout.setHorizontalGroup(
            jPrincipalEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPrincipalEncomendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelExibirEncomendas, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPrincipalEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPrincipalEncomendasLayout.createSequentialGroup()
                    .addContainerGap(133, Short.MAX_VALUE)
                    .addComponent(jPanelAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(144, Short.MAX_VALUE)))
            .addGroup(jPrincipalEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPrincipalEncomendasLayout.createSequentialGroup()
                    .addContainerGap(68, Short.MAX_VALUE)
                    .addComponent(jPanelControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(90, Short.MAX_VALUE)))
        );
        jPrincipalEncomendasLayout.setVerticalGroup(
            jPrincipalEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalEncomendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelExibirEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(jPrincipalEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPrincipalEncomendasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelAba, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPrincipalEncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPrincipalEncomendasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelControle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setForeground(new java.awt.Color(51, 0, 0));
        jMenuBar1.setFont(new java.awt.Font("DokChampa", 0, 10)); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(136, 35));

        jMenu1.setForeground(new java.awt.Color(102, 0, 0));
        jMenu1.setText("Nova Encomenda");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(102, 0, 0));
        jMenu2.setText("Exibir Encomendas");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setForeground(new java.awt.Color(102, 0, 0));
        jMenu3.setText("Controle");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setForeground(new java.awt.Color(102, 0, 0));
        jMenu4.setText("Relatório");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPrincipalEncomendas, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPrincipalEncomendas, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
   
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void textLocalEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLocalEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLocalEntregaActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        jPanelExibirEncomendas.setVisible(false);
        jPanelControle.setVisible(false);
        jPanelAba.setVisible(true);
        //jPanelCadastroEncomenda.setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jComboBoxClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxClienteItemStateChanged
        String nome;
        nome = jComboBoxCliente.getSelectedItem().toString(); 
        ProcuraIdCliente(nome);       
    }//GEN-LAST:event_jComboBoxClienteItemStateChanged

    private void jButtonCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCadastrarMouseClicked
        // ao clicar em cadastrar 
        Encomenda encomenda=new Encomenda();
        int cod=JSType.toInt32(textCod.getText());
        encomenda.setCod_Cliente(cod);
        encomenda.setLocalEntrega(textLocalEntrega.getText());
        encomenda.setSituacao(jComboSituacao.getSelectedItem().toString());
        String dia=jDataEntrega.getText().substring(0,2);
        String mes=jDataEntrega.getText().substring(3,5);
        String ano=jDataEntrega.getText().substring(6,10);
        encomenda.setDataEntrega(LocalDate.of(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia)));
        String hora=jHoraEntrega.getText().substring(0,2);
        String min=jHoraEntrega.getText().substring(3,5);
        String seg="00";
        encomenda.setHoraEntrega(LocalTime.of(Integer.parseInt(hora),Integer.parseInt(min),Integer.parseInt(seg)));
        System.out.print(encomenda.getHoraEntrega());
        insereEncomenda(encomenda);// apos inserir encomenda deve buscar o id desta que foi a ultima a ser inserida
        int idEncomenda=encomenda.UltimoIdSerido();// agora vai inserir  na tabela encoemdnaHASproduto
        encomendaHASproduto ep=new encomendaHASproduto();Produto p=new Produto();
        // o for abaixo é para pegar os valores da jtable 
         for(int x = 0; x < jTableInsereProduto.getRowCount();x++){ 
             // pega codigo do produto para inserir na tabela HAS           
             p.setCod(p.ProcuraIdProduto((String)jTableInsereProduto.getValueAt(x,1)));
             //int Encomenda_idEncomenda,int Produto_idProduto,double Total,int qtd
             //int qtd=Integer.parseInt(jTableInsereProduto.getValueAt(x,2));
             try{ep.insere(idEncomenda, p.getCod(),(double)jTableInsereProduto.getValueAt(x,4),Integer.parseInt((String)jTableInsereProduto.getValueAt(x,2)));
             }catch(ClassCastException e){
                 ep.insere(idEncomenda, p.getCod(),(double)jTableInsereProduto.getValueAt(x,4), (int) jTableInsereProduto.getValueAt(x,2));
             }System.out.print(jTableInsereProduto.getValueAt(x, 1)); // a posicao getValue (linha,coluna)
         }
        //System.out.print(jTableInsereProduto.getComponentListeners().toString());
        //ep.Insere(idEncomenda,);
    }//GEN-LAST:event_jButtonCadastrarMouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        jPanelAba.setVisible(false);
        jPanelControle.setVisible(false);
        //jPanelCadastroEncomenda.setVisible(false);
        jPanelExibirEncomendas.setVisible(true);
                
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        //criar novo usuario caso não exista
        CadastroCliente cadastroCliente = new CadastroCliente();
        jPrincipalEncomendas.add(cadastroCliente);
        cadastroCliente.setVisible(true);
        //jComboBoxCliente.removeAllItems();
        CarregarjComboCliente("SELECT * FROM cliente");
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jComboBoxClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxClienteMouseClicked
        // TODO add your handling code here:
        //CarregarjComboCliente("SELECT * FROM cliente");
    }//GEN-LAST:event_jComboBoxClienteMouseClicked

    private void jComboBoxProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxProdutoMouseClicked
        // TODO add your handling code here:
        String nome;TipoProduto p = new TipoProduto(); 
        nome = jComboBoxTipo.getSelectedItem().toString();
        CarregarjComboProduto("select * from produto where TipoProduto_idTipoProduto="+
                        p.ProcuraIdTipo(nome)+"");
        
    }//GEN-LAST:event_jComboBoxProdutoMouseClicked

    private void jButtonInserirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonInserirMouseClicked
        // TODO add your handling code here:
        // inserindo na tabela os itens quw vai comprar
        String nome;Produto p = new Produto(); 
        nome = jComboBoxProduto.getSelectedItem().toString();
        CarregarJTableInsereProduto("select * from produto where idProduto="+
                        p.ProcuraIdProduto(nome)+"");
        // o codigo abaixo calcula pega todas as linhas  da jTable 
        for(int x = 0; x < jTableInsereProduto.getRowCount();x++){
            // pegar o valor das colunas Total e somar para colocar no Total final
            total=total+(double)jTableInsereProduto.getValueAt(x,4);            
        }  
        TextTotal.setText(String.valueOf(total));
        total=0;
    }//GEN-LAST:event_jButtonInserirMouseClicked

    private void jComboBoxTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxTipoMouseClicked
        // TODO add your handling code here:
        CarregarjComboTipo("select * from tipoproduto");
        String nome;TipoProduto p = new TipoProduto(); 
        nome = jComboBoxTipo.getSelectedItem().toString();
        CarregarjComboProduto("select * from produto where TipoProduto_idTipoProduto="+
                        p.ProcuraIdTipo(nome)+"");

    }//GEN-LAST:event_jComboBoxTipoMouseClicked

    private void jComboBoxTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTipoItemStateChanged
        // TODO add your handling code here:
        String nome;TipoProduto p = new TipoProduto(); 
        nome =jComboBoxTipo.getSelectedObjects().toString();
         CarregarjComboProduto("select * from produto where TipoProduto_idTipoProduto="+
                        p.ProcuraIdTipo(nome)+"");
    }//GEN-LAST:event_jComboBoxTipoItemStateChanged

    private void jTableInsereProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableInsereProdutoMouseClicked
         // TODO add your handling code here:
         // ao clicar em botao excluir (checkbox)
         //int linha_selecionada = jTableInsereProduto.getSelectedRow();
         int coluna_selecionada = jTableInsereProduto.getSelectedColumn();
         if (coluna_selecionada == 5){ // se o botao da coluna 5 estiver ativado
             // remover o item da jTable
            //converte pra inteiro.              
                DefaultTableModel modelo = (DefaultTableModel)jTableInsereProduto.getModel();
                modelo.removeRow(jTableInsereProduto.getSelectedRow());                              
        }     
         
         
    }//GEN-LAST:event_jTableInsereProdutoMouseClicked

    private void jButtonVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVoltarMouseClicked
        // TODO add your handling code here:
        // volta para a aba anterior
        this.jPanelAba.setSelectedIndex(0);
    }//GEN-LAST:event_jButtonVoltarMouseClicked

    private void jButtonProximoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonProximoMouseClicked
        // vai para a aba de produtos
        this.jPanelAba.setSelectedIndex(1);
    }//GEN-LAST:event_jButtonProximoMouseClicked

    private void TextTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTotalActionPerformed

    private void jAtualizaTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAtualizaTotalMouseClicked
        // ao clicar em refresh atualiza ele atualiza as colunas de total recalculando e o total final
        for(int x = 0; x < jTableInsereProduto.getRowCount();x++){ 
            double totalc=0;
        try{//detectando a excecao            
            totalc=(double)jTableInsereProduto.getValueAt(x,3)* (int)jTableInsereProduto.getValueAt(x,2);
            //jTableInsereProduto.setValueAt(totalc, x, 4);//(objeto, linhax, coluna)
            //total=total+(double)jTableInsereProduto.getValueAt(x,4);
        }catch(ClassCastException e){
            totalc=(double)jTableInsereProduto.getValueAt(x,3)* Integer.parseInt((String) jTableInsereProduto.getValueAt(x,2));
        }finally{// bloco que executa apos tratar o erro
            jTableInsereProduto.setValueAt(totalc, x, 4);//(objeto, linhax, coluna)
            total=total+(double)jTableInsereProduto.getValueAt(x,4);
        }  
       }TextTotal.setText(String.valueOf(total));
       total=0;
    }//GEN-LAST:event_jAtualizaTotalMouseClicked

    private void jComboExibirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboExibirItemStateChanged
        // ao selecionar um tipo de exibicao mostrar na tabela as encomendas
        // ao clicar na opcao todas fazer um teste com tabela com botoes dentro da celula
        String nome;
        nome = jComboExibir.getSelectedItem().toString();
        if ("Todas".equals(nome)){
             texibir.ver_tabela(jTableExibir,"select * from encomenda ORDER BY idEncomenda");
        }else{
            TipoProduto p = new TipoProduto(); 
            texibir.ver_tabela(jTableExibir,"select * from encomenda where situacao='" + nome + "' ");
        }
        
        //texibir.ver_tabela(jTableExibir,"select * from encomenda");
    }//GEN-LAST:event_jComboExibirItemStateChanged

    
    private void jTableExibirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableExibirMouseClicked
        // TODO add your handling code here:
        // capturar click no botao de exibir produtos
        // a linha abaixo  captura a posicao x do click do botao
        int coluna_selecionada = jTableExibir.getSelectedColumn();
        if (coluna_selecionada == 6){ 
            //System.out.print("selecionou um botao");
            int linha=jTableExibir.getSelectedRow();// pega a linha do objeto selecionado
            int cod=(int)jTableExibir.getValueAt(linha,0);// pega o codigo que esta na coluna zero
            // abrir tela com informações dos itens da encomenda    
            /*Object value = jTableExibir.getValueAt(1,0);
            if (value instanceof JButton){  
                ((JButton)value).doClick();Action action = null;
                JButton boton = (JButton)value;
                boton.setBorderPainted(true);
            }*/Object value = jTableExibir.getValueAt(linha,coluna_selecionada);
            if (value instanceof JButton){  
                ((JButton)value).doClick();
                JButton boton = (JButton)value;
                boton.setBorderPainted(false);boton.setFocusable(true);
              //  boton.setForeground(Color.yellow);               
            }
        }
        //int column = jTableExibir.getColumnModel().getColumnIndexAtX(evt.getX());
       /* int row= evt.getY()/jTableExibir.getRowHeight();
        if (row<jTableExibir.getRowCount() && row >=0 && column <jTableExibir.getColumnCount() && column >=0){
            Object value = jTableExibir.getValueAt(row,column);
            if (value instanceof JButton){  
                ((JButton)value).doClick();
                JButton boton = (JButton)value;
                
              //  boton.setForeground(Color.yellow);               
            }
        }*/
    }//GEN-LAST:event_jTableExibirMouseClicked

    private void jTableExibirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableExibirMouseEntered
           
    }//GEN-LAST:event_jTableExibirMouseEntered

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        // ordenar por data mais proxima ou mais distante
        String nome1;
        nome1 = jComboExibir.getSelectedItem().toString();
        String nome2;
        nome2 = jComboBox1.getSelectedItem().toString();
        if ("Data mais próxima".equals(nome2)){            
            if ("Todas".equals(nome1)){
             texibir.ver_tabela(jTableExibir,"select * from encomenda ORDER BY dataEntrega");
            }else{
             texibir.ver_tabela(jTableExibir,"select * from encomenda where situacao='" + nome1 + "'ORDER BY dataEntrega");
            }
        }        
        else{
            if ("Todas".equals(nome1)){
             texibir.ver_tabela(jTableExibir,"select * from encomenda ORDER BY dataEntrega DESC");
            }else{
             texibir.ver_tabela(jTableExibir,"select * from encomenda where situacao='" + nome1 + "'ORDER BY dataEntrega DESC");
            }
        }       
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
        // ao clicar em controle exibir o jpanel de controle
        jPanelControle.setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuEncomendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TextTotal;
    private javax.swing.JButton jAtualizaTotal;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonInserir;
    private javax.swing.JButton jButtonProximo;
    public javax.swing.JButton jButtonTeste;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxCliente;
    private javax.swing.JComboBox<String> jComboBoxProduto;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JComboBox<String> jComboExibir;
    private javax.swing.JComboBox<String> jComboSituacao;
    private javax.swing.JFormattedTextField jDataEntrega;
    private javax.swing.JFormattedTextField jHoraEntrega;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jPanelAba;
    private javax.swing.JPanel jPanelCadastroEncomenda;
    private javax.swing.JPanel jPanelControle;
    private javax.swing.JPanel jPanelExibirEncomendas;
    private javax.swing.JDesktopPane jPrincipalEncomendas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableExibir;
    private javax.swing.JTable jTableInsereProduto;
    private javax.swing.JTextField textCod;
    private javax.swing.JTextField textLocalEntrega;
    private javax.swing.JTextField textQtd;
    // End of variables declaration//GEN-END:variables
}
