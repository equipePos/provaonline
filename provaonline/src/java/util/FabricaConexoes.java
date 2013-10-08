/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexoes {

    public Connection getConnection() 
    {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	try 
	{
        	return DriverManager.getConnection("jdbc:mysql://localhost:3306/prova", "root", "");
	} 
	catch(SQLException e) 
	{
		throw new RuntimeException(e);
	}
    }
}
