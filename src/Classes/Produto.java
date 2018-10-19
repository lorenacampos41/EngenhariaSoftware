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

/**
 *
 * @author lorena
 */
public class Produto {
    String nome;
    double preco;
    int Cod;

    public int getCod() {
        return Cod;
    }

    public void setCod(int Cod) {
        this.Cod = Cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public void CadastraProduto(int codTipo,String nome, Double preco){
         Connection con;
        try{
            con = Conexao.getConnection();
            Statement statement = con.createStatement();
           
            String query = "INSERT INTO produto(" +
               "nome, preco, TipoProduto_idTipoProduto)VALUES(' " +
               nome + "',"+
               preco +","+
               codTipo + ")" ;                       
                    
            int result = statement.executeUpdate(query);
                if ( result == 1 ){
                  JOptionPane.showMessageDialog(
                  null, "A inserção foi um sucesso.", "Aviso",JOptionPane.PLAIN_MESSAGE );
                }
        }catch(SQLException ex){       
            JOptionPane.showMessageDialog(
                  null, "Falha ao conectar banco de dados", "Aviso",
                  JOptionPane.PLAIN_MESSAGE );
        }            
    } 
    
    
    
    public int removerProduto(int cod){
    Connection con;int result = 0;
        try {
            con = Conexao.getConnection();
            Statement statement = con.createStatement();
            String query = "DELETE FROM produto WHERE idProduto="+cod+"";
               
            result = statement.executeUpdate(query);
                if ( result == 1 ){
                  JOptionPane.showMessageDialog(
                  null, "Excluído com sucesso.", "Aviso",
                  JOptionPane.PLAIN_MESSAGE ); return 1;                 
                }}catch(SQLException ex){
                       JOptionPane.showMessageDialog(
                  null, "Falha ao conectar banco de dados", "Aviso",
                  JOptionPane.PLAIN_MESSAGE );
        }  
        return 0;
    }
    
    public int ProcuraIdProduto(String nome){
        Connection con; int cod=0;
         try{
         con = Conexao.getConnection();
         String sql = "select * from produto where nome like '%" + nome + "%' ";
         PreparedStatement st = con.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         
         if(resultSet.next()){ 
              return resultSet.getInt("idProduto"); 
         }
         }catch(Exception e){System.out.println("Não foi possivel consultar!");
        }		
        return 0;
    }
}
   
