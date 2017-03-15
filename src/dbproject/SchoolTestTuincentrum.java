/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bart.Stoebel
 */
public class SchoolTestTuincentrum {
    private final String URL = "jdbc:mysql://localhost/tuincentrum?"
            + "useSSL=false&noAccessToProcedureBodies=true";            //dataverkeer encrypteren?
    private  final String USER = "cursist";
    private final String PASSWORD = "admin";
    private final String CALL_PLANTEN_MET_EEN_WOORD = "{call test(?)}";
    private static final String UPDATE_PRIJS_10_PROCENT = "update planten set "
            + "verkoopprijs=verkoopprijs*1.1 where verkoopprijs>=100";
    private static final String UPDATE_PRIJS_5_PROCENT = "Update planten set "
            + "verkoopprijs=verkoopprijs*1.05 where verkoopprijs < 100";
    
    public void test(){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()){
            //statement.setString(1,  "%bloem");
            connection.setAutoCommit(false);
            statement.executeUpdate(UPDATE_PRIJS_10_PROCENT);
            statement.executeUpdate(UPDATE_PRIJS_5_PROCENT);
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
