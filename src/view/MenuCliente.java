/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Classes.Cliente;
import Classes.Conexao;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFormattedTextField;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import jdk.nashorn.internal.runtime.JSType;


/**
 *
 * @author lorena
 */
public class MenuCliente extends javax.swing.JFrame {
    
   

    public void CarregarJTable(String sql) {
        Connection con;
    try
    {    
     con = Conexao.getConnection();
     PreparedStatement banco = (PreparedStatement)con.prepareStatement(sql);
     banco.execute(); // cria o vetor

     ResultSet resultado = banco.executeQuery(sql);

     DefaultTableModel model =(DefaultTableModel) jTableClientes.getModel();
     model.setNumRows(0);

     while(resultado.next())
     {
         model.addRow(new Object[] 
         { 
            //retorna os dados da tabela do BD, cada campo e um coluna.
            resultado.getInt("idCliente"),
            resultado.getString("nome"),
            resultado.getString("telefone"),
            resultado.getString("celular"),
            resultado.getString("endereco"),
            resultado.getString("cpf")
            
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
    
    
    
      /**
       * Creates new form MenuCliente
       */
    public MenuCliente() {
        initComponents();
        
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopCliente = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        textEndereco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonNovo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        textTelefone = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        textCelular = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jBuscaNome = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jFormattedCpf = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(749, 501));

        jLabel1.setFont(new java.awt.Font("DokChampa", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user.png"))); // NOI18N
        jLabel1.setText("Clientes");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 0));
        jLabel2.setText("Nome Completo*");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 0, 0));
        jLabel3.setText("Telefone");

        textNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textNome.setEnabled(false);
        textNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNomeActionPerformed(evt);
            }
        });

        jButtonCadastrar.setFont(new java.awt.Font("DokChampa", 0, 10)); // NOI18N
        jButtonCadastrar.setForeground(new java.awt.Color(51, 0, 0));
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.setEnabled(false);
        jButtonCadastrar.setFocusPainted(false);
        jButtonCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCadastrarMouseClicked(evt);
            }
        });

        jTableClientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Telefone", "Celular", "Endereço", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClientes);
        if (jTableClientes.getColumnModel().getColumnCount() > 0) {
            jTableClientes.getColumnModel().getColumn(0).setResizable(false);
            jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableClientes.getColumnModel().getColumn(5).setPreferredWidth(90);
        }

        textEndereco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textEndereco.setEnabled(false);
        textEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEnderecoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 0, 0));
        jLabel4.setText("Endereço");

        jButtonNovo.setFont(new java.awt.Font("DokChampa", 0, 10)); // NOI18N
        jButtonNovo.setForeground(new java.awt.Color(51, 0, 0));
        jButtonNovo.setText("NOVO");
        jButtonNovo.setFocusPainted(false);
        jButtonNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonNovoMouseClicked(evt);
            }
        });

        jButtonEditar.setFont(new java.awt.Font("DokChampa", 0, 10)); // NOI18N
        jButtonEditar.setForeground(new java.awt.Color(51, 0, 0));
        jButtonEditar.setText("EDITAR");
        jButtonEditar.setEnabled(false);
        jButtonEditar.setFocusPainted(false);
        jButtonEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEditarMouseClicked(evt);
            }
        });

        jButtonExcluir.setFont(new java.awt.Font("DokChampa", 0, 10)); // NOI18N
        jButtonExcluir.setForeground(new java.awt.Color(51, 0, 0));
        jButtonExcluir.setText("EXCLUIR");
        jButtonExcluir.setEnabled(false);
        jButtonExcluir.setFocusPainted(false);
        jButtonExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonExcluirMouseClicked(evt);
            }
        });
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        try {
            textTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textTelefone.setEnabled(false);
        textTelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 0));
        jLabel5.setText("Celular");

        try {
            textCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textCelular.setEnabled(false);
        textCelular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 0, 0));
        jLabel6.setText("Clientes cadastrados");

        jBuscaNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBuscaNome.setText("cliente");
        jBuscaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBuscaNomeActionPerformed(evt);
            }
        });

        jButtonPesquisar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/zoom.png"))); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.setFocusPainted(false);
        jButtonPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonPesquisarMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 0, 0));
        jLabel7.setText("CPF*");

        try {
            jFormattedCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedCpf.setEnabled(false);
        jFormattedCpf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExcluir))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonCadastrar)
                                .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonPesquisar))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar))
                .addGap(3, 3, 3)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jDesktopCliente.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopClienteLayout = new javax.swing.GroupLayout(jDesktopCliente);
        jDesktopCliente.setLayout(jDesktopClienteLayout);
        jDesktopClienteLayout.setHorizontalGroup(
            jDesktopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
            .addGroup(jDesktopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopClienteLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jDesktopClienteLayout.setVerticalGroup(
            jDesktopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
            .addGroup(jDesktopClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopClienteLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopCliente)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopCliente)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNomeActionPerformed

    private void textEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textEnderecoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.CarregarJTable("SELECT * FROM cliente ORDER BY idCliente ASC");
    }//GEN-LAST:event_formWindowOpened

    private void jButtonNovoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNovoMouseClicked
        // TODO add your handling code here:
        textNome.setEnabled(true);
        textEndereco.setEnabled(true);
        textTelefone.setEnabled(true);
        jButtonCadastrar.setEnabled(true);
        textCelular.setEnabled(true);
        jFormattedCpf.setEnabled(true);
        textNome.setText("");
        textEndereco.setText("");
        textTelefone.setText("");
        textCelular.setText("");
        jFormattedCpf.setText("");
    }//GEN-LAST:event_jButtonNovoMouseClicked

    private void jButtonCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCadastrarMouseClicked
        // ao clicar em cadastrar ele seta no banco de dados os valores dos campos nome, endereco, celular  e telefone;
        Connection con;
        try {
            con = Conexao.getConnection();
            Statement statement = con.createStatement();
           
            if("".equals(textNome.getText()) || "".equals(jFormattedCpf.getText())){
                JOptionPane.showMessageDialog(
                null, "Preencha os campos obrigatórios (*) \n", "Aviso",
                JOptionPane.PLAIN_MESSAGE );
            }
            if("".equals(textTelefone.getText().replaceAll("[( ) -]","")) && "".equals(textCelular.getText().replaceAll("[( ) -]","")) ){
                JOptionPane.showMessageDialog(
                null, "Preecha pelo menos um número para contato", "Aviso",
                JOptionPane.PLAIN_MESSAGE );
            }else{
                    Cliente cl=new Cliente();
                    String telefone = textTelefone.getText();
                    cl.setTelefone(telefone);
                    String celular =textCelular.getText(); 
                    cl.setCelular(celular);       
                    cl.setCpf(jFormattedCpf.getText());
            String query = "INSERT INTO cliente(" +
               "nome, endereco, telefone, celular,cpf)VALUES(' " +
               textNome.getText() + "', '" + 
               textEndereco.getText() + "','" + cl.getTelefone() + "','" + cl.getCelular() + "','"+
               cl.getCpf() + "')" ;
                        
                    
            int result = statement.executeUpdate(query);
                if ( result == 1 ){
                  JOptionPane.showMessageDialog(
                  null, "A inserção foi um sucesso.", "Aviso",
                  JOptionPane.PLAIN_MESSAGE );
                  textNome.setText("");
                  textEndereco.setText("");
                  textTelefone.setText("");
                  jFormattedCpf.setText(""); 
                  textCelular.setText("");
                  // atualiza a tabela com os dados novos vindo do banco
                  this.CarregarJTable("SELECT * FROM cliente ORDER BY idCliente ASC");
        
                }else {
                  JOptionPane.showMessageDialog(
                  null, "A inserção falhou!. Verifique os campos", "Aviso",
                  JOptionPane.WARNING_MESSAGE );    
                }
          }
        }catch(SQLException ex){
             // a linha a baixo exibe a mensagem de falha ao conectar no banco
            JOptionPane.showMessageDialog(
                  null, "Falha ao conectar banco de dados", "Aviso",
                  JOptionPane.PLAIN_MESSAGE );
        }
            this.CarregarJTable("SELECT * FROM cliente ORDER BY idCliente ASC");
    }//GEN-LAST:event_jButtonCadastrarMouseClicked

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jBuscaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBuscaNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBuscaNomeActionPerformed

    private void jButtonPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPesquisarMouseClicked
        // TODO add your handling code here:
        
        String buscaNome=jBuscaNome.getText();
        this.CarregarJTable("SELECT * FROM cliente WHERE nome like '%"+buscaNome+"%'");
    }//GEN-LAST:event_jButtonPesquisarMouseClicked

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        // TODO add your handling code here:
         if (jTableClientes.getSelectedRow() != -1) {
            jButtonEditar.setEnabled(true);
            jButtonExcluir.setEnabled(true);
        } else {
             jButtonEditar.setEnabled(false);
            jButtonExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_jTableClientesMouseClicked

    
    
    private void jButtonEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditarMouseClicked
        
        // editar cliente ao clicar no botao
        // limpando os campos de cadastrar
        jButtonCadastrar.setEnabled(false);
        textNome.setText("");
                  textEndereco.setText("");
                  textTelefone.setText("");
                  jFormattedCpf.setText(""); 
                  textCelular.setText("");
        jButtonCadastrar.setEnabled(false);
        
        // chamando a janela de alterar
        EditarCliente editarcl=new EditarCliente();
        jDesktopCliente.add(editarcl);
         // pegando a linha selecionada da tabela        
        int linha = jTableClientes.getSelectedRow();//Pega o dado de dentro da celula da tabela
        Cliente cl = new Cliente();
        cl.setNome(String.valueOf(jTableClientes.getValueAt(linha, 1)));
        cl.setEndereco(String.valueOf(jTableClientes.getValueAt(linha, 4)));
        cl.setTelefone(String.valueOf(jTableClientes.getValueAt(linha, 2)));
        cl.setCelular(String.valueOf(jTableClientes.getValueAt(linha, 3)));
        cl.setCpf(String.valueOf(jTableClientes.getValueAt(linha, 5)));
        cl.setCodigo((int)(jTableClientes.getValueAt(linha, 0)));
        editarcl.CarregarCampos(cl);
        editarcl.setVisible(true);
        
        //textNome.setEnabled(true);textEndereco.setEnabled(true);textCelular.setEnabled(true);textTelefone.setEnabled(true);
            //Pega o dado de dentro da celula da tabela
            //String dado = (String) jTableClientes.getValueAt(linha, 1);
            String nome = String.valueOf(jTableClientes.getValueAt(linha, 1)); 
            //textNome.setText(nome); nome= String.valueOf(jTableClientes.getValueAt(linha, 4)); textEndereco.setText(nome);
            // pegando os campos celular e telefone da tabela
            
       
            //converte pra inteiro.
            //int cod = Integer.parseInt(codigo);
           // CadastroCliente cl=new CadastroCliente();
            //jDesktopCliente.add(cl);
            //cl.setVisible(true);
           
            
            
            //int estado = JOptionPane.showConfirmDialog(null, "Dado: " + dado + "\n Codigo: " + cod, "Deseja carregar o cadastro?", JOptionPane.YES_NO_OPTION);
    }//GEN-LAST:event_jButtonEditarMouseClicked

    private void jButtonExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonExcluirMouseClicked
       // desabiltando os campos de cadastrar
        jButtonCadastrar.setEnabled(false);
        textNome.setText("");
                  textEndereco.setText("");
                  textTelefone.setText("");
                  jFormattedCpf.setText(""); 
                  textCelular.setText("");
        jButtonCadastrar.setEnabled(false);
        //pegando valor da linha
        int linha = jTableClientes.getSelectedRow();
            //Pega o dado de dentro da celula da tabela
            String dado = (String) jTableClientes.getValueAt(linha, 1);
            String codigo = String.valueOf(jTableClientes.getValueAt(linha, 0));
            //converte pra inteiro.
            int cod = Integer.parseInt(codigo);
            //Pergunta para o usuario.
             int estado = JOptionPane.showConfirmDialog(null, "Dado: " + dado + "\n Codigo: " + cod, "Deseja carregar o cadastro?", JOptionPane.YES_NO_OPTION);
            System.out.println("Valor do estado: " + estado);// estado =0 confirma excluir
            if (estado==0){
                // se confirmar, exclui:
                Cliente cl=new Cliente();
                if (cl.removerCliente(cod)==1){
                    DefaultTableModel modelo = (DefaultTableModel) jTableClientes.getModel();

                    modelo.removeRow(jTableClientes.getSelectedRow());
                }
                
            }
        
    }//GEN-LAST:event_jButtonExcluirMouseClicked

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jBuscaNome;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JDesktopPane jDesktopCliente;
    private javax.swing.JFormattedTextField jFormattedCpf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JFormattedTextField textCelular;
    private javax.swing.JTextField textEndereco;
    private javax.swing.JTextField textNome;
    private javax.swing.JFormattedTextField textTelefone;
    // End of variables declaration//GEN-END:variables

}
