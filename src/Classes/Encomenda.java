/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author lorena
 */
public class Encomenda {
    int codigo;
    int cod_Cliente;
    String localEntrega;
    String situacao;
    LocalDate dataEntrega;
    LocalTime horaEntrega;

    public int getCodigo() {
        return codigo;
    }

    public int getCod_Cliente() {
        return cod_Cliente;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCod_Cliente(int cod_Cliente) {
        this.cod_Cliente = cod_Cliente;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalTime getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(LocalTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }
    
    
    public int UltimoIdSerido(){
        Connection con; //int cod=0;
         try{
         con = Conexao.getConnection();
         String sql = "SELECT  MAX(idEncomenda) AS ID FROM encomenda";
         PreparedStatement st = con.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         if(resultSet.next()){ 
              return resultSet.getInt("ID"); 
         }
         }catch(Exception e){System.out.println("Não foi possivel consultar!");
        }		
        return 0;
    }
}
