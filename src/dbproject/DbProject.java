/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vinnie
 */
public class DbProject {

    /**
     * MySQLServer setup thuis:
Connection name: VinniesConnection
Username : root
Password: admin
Windows Service Name: MySQL57

     */
    private static final String URL = "jdbc:mysql://localhost/bieren?"
            + "useSSL=false";            //dataverkeer encrypteren?
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    
    
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            System.out.println("Connectie geopend!");
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
