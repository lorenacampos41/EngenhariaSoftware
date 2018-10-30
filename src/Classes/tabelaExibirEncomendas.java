/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lorena
 */
public class tabelaExibirEncomendas {
    
    public void ver_tabela(JTable table){
        
        DefaultTableModel m= new DefaultTableModel(
                new Object [][]{{"1","Pedro"}},
                new Object []{"codigo","nome"}
        );
        
        table.setModel(m);
    }
}
