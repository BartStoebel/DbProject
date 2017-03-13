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
 * @author Vinnie
 */
public class TestMain {

    private final String URL = "jdbc:mysql://localhost/bieren?"
            + "useSSL=false";
    private final String USER = "cursist";
    private final String PASSWORD = "admin";
    private final String SQL = "delete from bieren where alcohol is null";
    private final String AANTAL_BIEREN_PER_BROUWER = "select brouwers.naam, count(bieren.brouwerid) as aantal\n" +
        "from brouwers " +
        "left join bieren on brouwers.id = bieren.brouwerid " +
        "group by brouwers.naam order by brouwers.naam";
    
    
    public void main() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(AANTAL_BIEREN_PER_BROUWER)){
            while (resultSet.next()){
                System.out.printf("%s  %d%n",resultSet.getString("naam"), resultSet.getInt("aantal"));
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
