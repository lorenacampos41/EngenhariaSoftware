
package engenhariasoftware;

import Classes.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.MenuInicial;

/**
 *
 * @author lorena
 */
public class EngenhariaSoftware {

    public static void main(String args[]) throws SQLException{
        //tentando conectar ao banco de dados
        Connection con;
        try{// abrir tela inicial se conseguir conectar ao banco
        con = Conexao.getConnection();System.out.println("Conectou ao banco");
        new MenuInicial().setVisible(true);
        }catch(SQLException ex){// exibe mensagem caso não conecte ao banco
           JOptionPane.showMessageDialog(
                  null, "Não conectou ao banco de dados: Contate o suporte", "Aviso",
                  JOptionPane.WARNING_MESSAGE );

        }
        // iniciando o programa abrindo a tela de Menu Inicial
        
	
        //con.close();
    }
    
}
