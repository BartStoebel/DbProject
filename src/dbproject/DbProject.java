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
import java.util.Scanner;

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
    private static final String URL = "jdbc:mysql://localhost/tuincentrum?"
            + "useSSL=false";            //dataverkeer encrypteren?
    private static final String USER = "cursist";
    private static final String PASSWORD = "admin";
    private static final String UPDATE_PRIJS = "update planten set "
            + "verkoopprijs = verkoopprijs * 1";
    private static final String SELECT_NAAM = "select id, naam from leveranciers"
            + " order by id";
    private static final String SELECT_GEMIDDELDE_VERKOOPPRIJS = "select "
            + "avg(verkoopprijs) as gemiddelde from planten";
    private static final String SELECT_ALLE_LEVERANCIERS =
            "select naam, aantalkinderen from leveranciers order by naam";
    private static final String SELECT_LEVERANCIERS_VAN_EEN_WOONPLAATS =
            "select naam from leveranciers where woonplaats = ?";
    private static final String UPDATE_PLANTEN_PRIJS_HACKER = "update planten "
            + "set verkoopprijs = verkoopprijs * 1.1 where naam = ?";
    
    
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("minimum:");
//        double min = Double.parseDouble(scanner.nextLine());
//        System.out.println("Maximum: ");
//        double max = Double.parseDouble(scanner.nextLine());

//        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//                PreparedStatement statement = connection.prepareStatement(UPDATE_PLANTEN_PRIJS_HACKER)){
//            statement.setString(1, woonplaats);
//            statement.executeUpdate();
//        
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
        
        
//        TestMain main = new TestMain();
//        main.main();
//        SchoolTestDb school = new SchoolTestDb();
//        school.main(0.5, 3.2);

        SchoolTestTuincentrum testTuin = new SchoolTestTuincentrum();
        testTuin.test();
    }
    
}
