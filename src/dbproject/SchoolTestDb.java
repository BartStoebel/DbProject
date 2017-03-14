/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

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
            + "useSSL=false";
    private final String USER = "root";
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
    
    
    public void main(double min, double max) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery(SQL)){
                ResultSetMetaData metadata = resultSet.getMetaData();
                for (int index = 1; index <=metadata.getColumnCount() ;index++){
                    System.out.printf("%s:  %s%n",metadata.getColumnName(index), 
                    metadata.getColumnTypeName(index));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
