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
public class Cliente {
    int codigo;
    String nome;
    String telefone;
    String endereco;
    String celular;
    String cpf;

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCelular() {
        return celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    public int removerCliente(int cod){
         Connection con;int result = 0;
        try {
            con = Conexao.getConnection();
            Statement statement = con.createStatement();
            String query = "DELETE FROM cliente WHERE idCliente="+cod+"";
               
            result = statement.executeUpdate(query);
                if ( result == 1 ){
                  JOptionPane.showMessageDialog(
                  null, "Excluído com sucesso.", "Aviso",
                  JOptionPane.PLAIN_MESSAGE );
                  
                }    
        }catch(SQLException ex){
             // a linha a baixo exibe a mensagem de falha ao conectar no banco
            JOptionPane.showMessageDialog(
                  null, "Falha ao conectar banco de dados", "Aviso",
                  JOptionPane.PLAIN_MESSAGE );
        }
        return result;
    }
    
    public void Alterar(Cliente cl){
     Connection con;
         try{
         con = Conexao.getConnection();
         String sql = "UPDATE cliente SET nome='lorenaaaaa"+cl.getNome()+",endereco="+
                 cl.getEndereco()+" WHERE idCliente= "+cl.getCodigo()+" ";
         PreparedStatement st = con.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         }catch(Exception e){System.out.println("Não foi possivel alterar os dados!");
        }	
    }
    
    public int ProcuraIdCliente(String nome){
        Connection con; int cod=0;
         try{
         con = Conexao.getConnection();
         String sql = "select * from cliente where nome like '%" + nome + "%' ";
         PreparedStatement st = con.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         
         if(resultSet.next()){ 
              return resultSet.getInt("idCliente"); 
         }
         }catch(Exception e){System.out.println("Não foi possivel consultar!");
        }		
        return 0;
    }
    
}
