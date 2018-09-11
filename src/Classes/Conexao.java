/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author lorena
 */
public class Conexao {
    
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String URL = "jdbc:mysql://localhost:3306/engenhariasoftware";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    // Conectar ao banco
   public static Connection getConnection() throws SQLException{
	try{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Conectando ao banco");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/engenhariasoftware",
                "root","");
	}catch (ClassNotFoundException e) {
		System.out.println("Não conectado");
		throw new SQLException(e.getMessage());
	}   
    }
}