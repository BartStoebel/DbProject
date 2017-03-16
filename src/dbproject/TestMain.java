/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
    
    private final String BIEREN_MAAND = "Select verkochtsinds, naam " + 
            "from bieren where {fn month(verkochtsinds)} = ?"
            + " order by verkochtsinds";
    
    
    public void main() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-d");
        Locale aLocale = Locale.FRANCE;
        DateTimeFormatter form = DateTimeFormatter.ofPattern("d-MMMM-yyyy", aLocale);
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(BIEREN_MAAND)){
                    statement.setInt(1, 1);
                    try(ResultSet resultSet = statement.executeQuery()){
                        int a = 1;
                        while (resultSet.next()){
                            LocalDate date = LocalDate.parse(resultSet.getDate("verkochtsinds").toString(),formatter);
                            
                            
                            String d = date.format(form);
                            System.out.printf("%d: %s   %s%n",a++, d, resultSet.getString("naam"));
                        }
                    }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
