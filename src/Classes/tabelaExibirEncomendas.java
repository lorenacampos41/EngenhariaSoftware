/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lorena
 */
public class tabelaExibirEncomendas {
    
    public void ver_tabela(JTable table,String sql){
        table.setDefaultRenderer(Object.class, new Render());
        JButton btn1=new JButton("Visualizar Itens");
        Connection con;// conectando ao banco
        try {
            con = Conexao.getConnection();
            Statement statement = con.createStatement();
            // executando a sql do parametro
            PreparedStatement resultadoBanco = (com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
            resultadoBanco.execute(); 
            ResultSet resultado = resultadoBanco.executeQuery(sql);
            // inicializando o modelo da tabela
            DefaultTableModel m= new DefaultTableModel(
                new Object [][]{},
                new Object []{"codigo","cliente","Situacao","LocalEntrega","DataEntrega","HoraEntrega","Ver Produtos"}
                ){
                   // há um problema, ao clicar nos botoes eles aparecem o nome da classe,
                    // entao tem que desabilitar a edicao, as linhas abaixoo fazem isso
                    public boolean isCellEditable(int row,int column){return false;}
                };
                table.setModel(m);
                table.setRowHeight(25);
            // enquanto houver resultados colocar na tabela
            while(resultado.next())
            {  m.addRow(new Object[]{
                resultado.getInt("idEncomenda"),
                resultado.getInt("idEncomenda"),
                resultado.getString("situacao"),
                resultado.getString("localEntrega"),
                btn1            
                });
                /*DefaultTableModel m= new DefaultTableModel(
                new Object [][]{{"1","Pedro"}},
                new Object []{"codigo","nome"}
                );
                table.setModel(m);*/
            } resultadoBanco.close();
            con.close(); 
        }catch(Exception ex){}      
        
        
    }
}
