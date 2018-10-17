/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Time;
import java.time.LocalDate;
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
    Time horaEntrega;

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

    public Time getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Time horaEntrega) {
        this.horaEntrega = horaEntrega;
    }
    
    
    
}
