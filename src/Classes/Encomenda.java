/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author lorena
 */
public class Encomenda {
    int codigo;
    int cod_Cliente;
    String localEntrega;

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
    
}
