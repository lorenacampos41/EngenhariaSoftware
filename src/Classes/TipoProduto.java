/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author lorena
 */
public class TipoProduto {
    
    String nome;
    
    public void CadastrarTipo(String nome) {                                              
        // ao clicar em cadastrar ele seta no banco de dados os valores dos campos nome, endereco, celular  e telefone;
        Connection con;
        try{
            con = Conexao.getConnection();
            Statement statement = con.createStatement();
           
            String query = "INSERT INTO tipoproduto(" +
               "descricao)VALUES(' " +
               nome + "')" ;                       
                    
            int result = statement.executeUpdate(query);
                if ( result == 1 ){
                  UIManager.put("OptionPane.okButtonText","ok");
                  JOptionPane.showMessageDialog(
                  null, "A inserção foi um sucesso.", "Aviso",JOptionPane.PLAIN_MESSAGE );
                }
        }catch(SQLException ex){
             // a linha a baixo exibe a mensagem de falha ao conectar no banco
            JOptionPane.showMessageDialog(
                  null, "Falha ao conectar banco de dados", "Aviso",
                  JOptionPane.PLAIN_MESSAGE );
        }            
    } 
    
    public int ProcuraIdTipo(String nome){
        Connection con; int cod=0;
         try{
         con = Conexao.getConnection();
         String sql = "select * from tipoproduto where descricao like '%" + nome + "%' ";
         PreparedStatement st = con.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         
         if(resultSet.next()){ 
              return resultSet.getInt("idTipoProduto"); 
         }
         }catch(Exception e){System.out.println("Não foi possivel consultar!");
        }		
        return 0;
    }
    
    
    public String ProcuraTipo(int cod){
        Connection con; 
         try{
         con = Conexao.getConnection();
         String sql = "select * from tipoproduto where idTipoProduto="+cod+"";
         PreparedStatement st = con.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         
         if(resultSet.next()){ 
              return resultSet.getString("descricao"); 
         }
         }catch(Exception e){System.out.println("Não foi possivel consultar!");
        }		
        return null;
    }
    
    
}
