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
public class Cliente {
    int codigo;
    String nome;
    int telefone;
    String endereco;
    int celular;

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getTelefone() {
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

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getCelular() {
        return celular;
    }
    
    
}
