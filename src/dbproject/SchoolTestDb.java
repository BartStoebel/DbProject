/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bart.Stoebel
 */
public class SchoolTestDb {
    private final String URL = "jdbc:mysql://localhost/bieren?"
            + "useSSL=false&noAccessToProcedureBodies=true";
    private final String USER = "cursist";
    private final String PASSWORD = "admin";
    private final String SQL = "select * from bieren where alcohol is null";
    private final String AANTAL_BIEREN_PER_BROUWER = "select brouwers.brnaam, count(bieren.brouwernr) as aantal " +
        "from brouwers " +
        "left join bieren on brouwers.brouwernr = bieren.brouwernr " +
        "group by brouwers.brnaam order by brouwers.brnaam";
    private final String BIEREN_MIN_MAX_ALCOHOL = "select naam, alcohol "
            + "from bieren "
            + "where alcohol > ? and alcohol < ? "
            + "order by alcohol, naam";
    private final String SP_BIER = "{call bierMinMax(?,?)}";
    private final String UPDATE2 = "update bieren set brouwernr = 2 "
            + "where brouwernr = 1 and alcohol > 8.5";
    private final String UPDATE3 = "update bieren set brouwernr = 3 "
            + "where brouwernr = 1 and alcohol <= 8.5";
    private final String DELETE = "delete from brouwers where brouwernr = 1";
    
    
    
    public void main(double min, double max) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            statement.executeUpdate(UPDATE2);
            statement.executeUpdate(UPDATE3);
            statement.execute(DELETE);
            connection.commit();
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
