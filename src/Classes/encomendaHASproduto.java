/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lorena
 */
public class encomendaHASproduto {
    int Encomenda_idEncomenda;
    int Produto_idProduto;
    double Total;
    int qtd;
    
    public void insere(int Encomenda_idEncomenda,int Produto_idProduto,double Total,int qtd){
            Connection con;
        try {
            con = Conexao.getConnection();
            Statement statement = con.createStatement();
            String query = "INSERT INTO encomenda_has_produto(" +
               "Encomenda_idEncomenda, Produto_idProduto, Total, qtd)VALUES(" +
               Encomenda_idEncomenda + ", '" + 
               Produto_idProduto + "','"+
               Total +"','"+
               qtd + "')" ;
            
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
}
