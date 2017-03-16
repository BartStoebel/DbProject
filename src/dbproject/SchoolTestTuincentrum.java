/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bart.Stoebel
 */
public class SchoolTestTuincentrum {
    private final String URL = "jdbc:mysql://localhost/tuincentrum?"
            + "useSSL=false&noAccessToProcedureBodies=true";            //dataverkeer encrypteren?
    private  final String USER = "cursist";
    private final String PASSWORD = "cursist";
    private final String CALL_PLANTEN_MET_EEN_WOORD = "{call test(?)}";
    
    
    public void test(){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                CallableStatement statement = connection.prepareCall(CALL_PLANTEN_MET_EEN_WOORD)){
            statement.setString(1,  "'sering'");
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    System.out.println(resultSet.getString("naam"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
