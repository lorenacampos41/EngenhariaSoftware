
package engenhariasoftware;

import Classes.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import view.MenuInicial;

/**
 *
 * @author lorena
 */
public class EngenhariaSoftware {

    public static void main(String args[]) throws SQLException{
        //tentando conectar ao banco de dados
        Connection con;
        con = Conexao.getConnection();
        // iniciando o programa abrindo a tela de Menu Inicial
        //teste
	new MenuInicial().setVisible(true);
        //con.close();
    }
    
}
